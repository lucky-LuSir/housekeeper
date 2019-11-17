<template slot-scope="scope">
    <div class="root" ref="myRoot">
        <!-- 条件 -->
        <div class="top_bar">
            <div class="top_refresh">
                <el-button type="primary" size="mini" icon="el-icon-refresh" @click="report(true)" circle/>
                <b style="font-size: 16px;color: #395dd2">最新统计数据</b>
            </div>
            <div class="top_select">
                <el-date-picker
                        v-model="dateValue"
                        type="daterange"
                        align="right"
                        size="small"
                        unlink-panels
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
                <el-button type="primary" size="small" plain  icon="el-icon-search" @click="report">查询</el-button>
                <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportExcel()" >导出</el-button>
            </div>
        </div>
        <!-- 部门树 -->
        <div class="left_bar">
            <el-tree :data="deptTree"
                     :props="defaultProps"
                     :expand-on-click-node="false"
                     :highlight-current="true"
                     @node-click="handleNodeClick"
                     node-key="deptCode"
                     ref="tree"
            >
            </el-tree>
        </div>
        <!-- 数据表头 -->
        <div class="reght_table">
            <el-table :data="tableData" id="record" ref="elTable" v-loading="loading" stripe show-summary :height=floatDivHeight>
                <el-table-column
                        prop="showName"
                        label="名称"
                        width="160">
                </el-table-column>

                <el-table-column
                        v-if="shows"
                        prop="showTime"
                        label="入职时间"
                        width="160">
                </el-table-column>

                <el-table-column
                        v-if="!shows"
                        prop="lowerCount"
                        :label="lowerCountLabel"
                        width="160">
                </el-table-column>

                <el-table-column label="房源" align="center">
                    <el-table-column
                            prop="houseRent"
                            label="已租"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseNow"
                            label="在租"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="noHouseFollow"
                            label="10天未跟进"
                            width="120">
                    </el-table-column>
                </el-table-column>
                <el-table-column label="客户" align="center">
                    <el-table-column
                            prop="customerRent"
                            label="已租到"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="customerNow"
                            label="跟进中"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="noCustomerFollow"
                            label="3天未跟进"
                            width="120">
                    </el-table-column>
                </el-table-column>


            </el-table>
        </div>
    </div>
</template>

<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    export default {
        data() {
            return {

                // 树结构默认指定
                defaultProps: {
                    children: 'children',
                    label: 'deptName'
                },

                // 部门树
                deptTree: [],

                // 报表数据
                tableData: [],

                loading : false,

                shows : false,

                lowerCountLabel : '分部数',

                // 日期
                dateValue: '',

                // 高度
                floatDivHeight: 50
            }
        },
        created:function (){
            var vm = {};
            vm = this;
            var sendObj = {};
            var options = {
                method: 'POST',
                data: sendObj,
                url: "dept/selectTrees",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.deptTree = response.data.result;
                //vm.report();
            }).catch(function (error) {
                vm.$message.error('页面：获取部门树失败！');
            });
        },

        mounted(){

            var vm = {};
            vm  = this;
            //动态调整高度start
            this.floatDivHeight = this.$refs.myRoot.offsetHeight-200;
            //alert(this.floatDivHeight);
            console.log(this.floatDivHeight);
            window.onresize = () => {
                return (() => {
                    this.floatDivHeight = vm.$refs.myRoot.offsetHeight-200;
                })();
            }
            //动态调整高度end
        },

        methods:{

            handleNodeClick() {
                // 请求后台报表接口
                this.report();
            },

            report(dynamic){
                var vm = {};
                vm = this;
                vm.loading = true;
                // 获取日期
                var startDate = null;
                var endDate = null;
                if(vm.dateValue != "" && vm.dateValue != null){
                    var dateValues = vm.dateValue.toString().split(",");
                    startDate = new Date(dateValues[0]);
                    endDate = new Date(dateValues[1]);
                }
                // 获取刷新按钮
                if(dynamic != true){
                    dynamic = null;
                }
                // 获取当前节点部门树选中的数据
                var deptCode = vm.$refs.tree.getCurrentKey();

                var sendObj = {"code":deptCode,"startDate":startDate,"endDate":endDate};

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/record",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$refs.elTable.clearSort();
                    vm.tableData = response.data.result;
                    if(vm.tableData[0].reportType == '3'){
                        vm.shows = true;
                    }else if(vm.tableData[0].reportType == '2'){
                        vm.shows = false;
                        vm.lowerCountLabel = '团队人数';
                    }else if(vm.tableData[0].reportType == '1'){
                        vm.shows = false;
                        vm.lowerCountLabel = '分部数';
                    }
                    let h = vm.floatDivHeight;
                    vm.floatDivHeight = h-1;

                    vm.loading = false
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
            },
            exportExcel(){
                /* 1创建 workbook 对象 from table */
                //alert("导出开始");
                var reports;
                reports = document.querySelector('#record');
                var wb = XLSX.utils.table_to_book(reports)
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '数据统计报表.xlsx')
                } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
                return wbout

            }
        }
    }
</script>

<style scoped>
    .root{
        height: 100vh
    }
    .top_bar{
        width: 100%;
        margin:0px 0px 0px 0px;
        border-width: 0px 0px 1px 0px;
        border-style:inset;
        margin-top: 0px;
        padding:0px 0px 4px 0px;

    }

    .top_refresh{
        float: left;
        margin-left: 20px;
    }

    .top_select{
        float: none;
        margin-left: 205px;
    }

    .left_bar{
        float:left;
        width: 15%;
        border-style: inset;
        border-width:0px 1px 0px 0px;
        overflow-y: auto;
    }

    .reght_table{
        float:left;
        width: 85%;
    }

</style>
<template slot-scope="scope">
    <div class="root" ref="myRoot">

        <!-- 条件 -->
        <el-container>
            <!--子菜单-->
            <el-aside width="200px" class="aside">
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
            </el-aside>
            <el-main style="padding: 0px">
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
                                unlink-panels
                                size="small"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">

                        </el-date-picker>
                        <el-button type="primary" size="small" plain  icon="el-icon-search" @click="report">查询</el-button>
                        <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportExcel()" >导出</el-button>
                    </div>
                </div>
                <!-- 数据表头 -->
                <div class="reght_table">
                    <el-table :data="tableData" id="cusMuchFollowup" ref="elTable" v-loading="loading" show-summary :height=floatDivHeight>
                        <template v-for="(value,key,index) in tableHead">
                            <template v-if="index != 0")>
                                <el-table-column
                                        sortable
                                        :prop="key"
                                        :label="value"
                                        width="170">
                                </el-table-column>
                            </template>
                            <template v-else>
                                <el-table-column
                                        fixed="left"
                                        :prop="key"
                                        :label="value"
                                        width="150">
                                </el-table-column>
                            </template>
                        </template>
                    </el-table>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    import ElMain from "../../../../node_modules/element-ui/packages/main/src/main";
    export default {
        components: {ElMain},
        data() {
            return {

                // 树结构默认指定
                defaultProps: {
                    children: 'children',
                    label: 'deptName'
                },

                // 部门树
                deptTree: [],

                // 报表头
                tableHead: [],

                // 报表数据
                tableData: [],

                loading : false,

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
                vm.report();
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
                    url: "report/cusMuchFollowup",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$refs.elTable.clearSort();
                    vm.tableData = response.data.result.reportEntities;
                    vm.tableHead = response.data.result.tableHeads;
                    vm.loading = false
                    let h = vm.floatDivHeight;
                    vm.floatDivHeight = h-1;
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
            },
            exportExcel(){
                /* 1创建 workbook 对象 from table */
                //alert("导出开始");
                var reports;
                reports = document.querySelector('#cusMuchFollowup');
                var wb = XLSX.utils.table_to_book(reports)
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '多次带看客户报表.xlsx')
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
        padding:0px 0px 4px 0px;

    }

    .top_refresh{
        float: left;
    }

    .top_select{
        margin-left: 150px;
    }

    .left_bar{
        border-style: inset;
        border-width:0px 1px 0px 0px;
        overflow-y: auto;
    }

    .reght_table{
        width: 100%;
    }

</style>
<template>
    <div ref="myRoot">
        <div>
            <div class="top_bar">
                <el-button type="success" size="mini" plain class="navigate_select_button" >查询</el-button>
                <el-button type="success" size="mini" plain class="navigate_select_button" @click="exportExcel()">导出</el-button>
            </div>
            <div>
                <el-table
                        id="odfollowId"
                        size="mini"
                        :height="fullHeight"
                        width="100"
                          class="now_table"
                          :data="orderfollowTbList"
                          border>
                    <el-table-column
                            align="center"
                            label="序号"
                            type="index"
                            width="60px">
                    </el-table-column>
                    <el-table-column
                            label="跟进结果"
                            align="center"
                            prop="remark"
                            width="200px">
                    </el-table-column>
                    <el-table-column
                            label="订单编码"
                            align="center"
                            prop="orderCode"
                            width="160px">
                    </el-table-column>
                    <el-table-column
                            label="跟进人"
                            align="center"
                            prop="empName"
                            width="120px">
                    </el-table-column>
                    <el-table-column
                            label="跟进时间"
                            align="center"
                            min-width="160px">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.followupTime | timeDate }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="所属部门"
                            align="center"
                            prop="createDeptName"
                            min-width="260px">
                    </el-table-column>
                </el-table>
                <div class="page-box" >
                    <el-pagination   background
                                     @size-change="handleSizeChange"
                                     @current-change="handleCurrentChange"
                                     :current-page.sync="currentPage"
                                     :page-size="pageSizeData"
                                     layout="total, prev, pager, next, jumper"
                                     :total="totalCount">
                    </el-pagination >
                </div>
            </div>
        </div>
    </div>
</template>


<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    export default {
        components: {

        },
        created: function () {
            var vm = {};
            vm = this;
            vm.basicReLoadOrderfollow();


        },
        data() {
            return {
                fullHeight: document.documentElement.clientHeight - 185,
                orderfollowTbList:[],
                pageSizeData: 30,
                totalCount: 1,
                currentPage: 1,
                orderCode:null,
        }
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 185;
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        watch: {
            fullHeight (val) {
                if (!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function () {
                        that.timer = false
                    }, 400)
                }
            }
        },
        methods: {
            exportExcel(){
                /* 1创建 workbook 对象 from table */
                var wb = XLSX.utils.table_to_book(document.querySelector('#odfollowId'))
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'sheetjs订单跟进表.xlsx')
                } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
                return wbout

            },
            basicReLoadOrderfollow(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.pageSize = this.pageSizeData;
                    sendObj.start = 0;
                    if(this.orderCode!=null){
                        sendObj.entity.orderCode = this.orderCode;
                    }
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "orderfollowup/query",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.orderfollowTbList = response.data.result.data;
                        vm.totalCount=response.data.result.totalCount;
                        console.log("请求的订单跟进数据");
                        console.log(vm.orderfollowTbList);
                    }).catch(function (error) {
                        alert("页面:获取数据失败!orderfollowup/query");
                    });
                }
            },

            // 分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 分页:改变每页数量
            handleCurrentChange(val) {
                let vm = this;
                var obj = {};
                var sendObj = {};
                sendObj.entity = obj;
                if(this.orderCode!=null){
                    sendObj.entity.orderCode = vm.orderCode;
                }
                sendObj.pageSize = this.pageSizeData;
                sendObj.start = (val - 1) * (sendObj.pageSize);
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "orderfollowup/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.orderPaybackList = response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },

            clearFollowupData(){
                var vm = this;
                vm.orderfollowTbList=[];
            }
        },

    }
</script>


<style scoped>
    .page-box {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        z-index: 200;
    }

    .now_page{
    }
    .top_bar{
        margin: 10px 30px;
     }
    .content_bar{
    }
</style>
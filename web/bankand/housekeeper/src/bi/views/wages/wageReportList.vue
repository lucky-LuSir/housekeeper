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
                        v-model="month"
                        type="month"
                        placeholder="选择月">
                </el-date-picker>
                <el-button type="primary" size="small" plain  icon="el-icon-search" @click="report">查询</el-button>
                <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportExcel()">导出</el-button>
                <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportDetailsExcel()">详情导出</el-button>
                <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="dynamicDetailsExcel()">自定义详情导出</el-button>

            </div>
        </div>
        <!--自定义详情导出面板-->
        <!--房源导出面板-->
        <el-dialog  :close-on-click-modal="false" :visible.sync="exportDynamicDetailsExcelFormVisible" width="250px" min-width="120" append-to-body>
            <el-form :model="wageExportEntity">
                <el-row :gutter="5">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <div class="demonstration">开始时间</div>
                        <el-date-picker
                                v-model="wageExportEntity.startTime"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                        <div class="demonstration">结束时间</div>
                        <el-date-picker
                                v-model="wageExportEntity.endTime"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-col>
                </el-row>
            </el-form>
            <div style="text-align:center;margin-top: 30px;">
                <el-button @click="cleanExportEntity()">清空条件</el-button>
                <el-button type="primary" @click="exportDynamicDetailsExcel()">导出
                </el-button>
            </div>
        </el-dialog>
        <!-- 数据表头 -->
        <div>
            <template>
                <el-table
                        id="financeReportId"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        :data="wageReportList"
                        size="small"
                        :height=floatDivHeight
                        border>
                    <el-table-column  align="center"
                                      prop="userCode"
                                      label="人员编码"
                                      width="180">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="userName"
                                      label="人员姓名"
                                      width="180"
                                      fixed>
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="workNumber"
                                      label="人员工号"
                                      width="120">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="deptName"
                                      label="人员部门"
                                      width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="postName"
                                      label="人员岗位"
                                      width="140">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="wage"
                                      label="工资"
                                      width="140">
                    </el-table-column>

                    <el-table-column align="center"
                                     min-width="180px" label="入职时间"
                                     show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.entryTime!=null">{{ scope.row.entryTime | time}}</template>
                    </el-table-column>
                    <el-table-column align="center"
                                     min-width="180px" label="离职时间"
                                     show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.quitTime!=null">{{ scope.row.quitTime | time}}</template>
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="inserviceTime"
                                      label="在职时间"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="isDeleted"
                                      label="是否离职"
                                      width="130">
                        <template slot-scope="scope">
                            {{scope.row.isDeleted == true ? "是" : "否"}}
                        </template>
                    </el-table-column>

                </el-table>
            </template>
        </div>


        <!-- 分页 -->
        <div class="page-box" >
            <el-pagination   background
                             @size-change="handleSizeChange"
                             @current-change="handleCurrentChange"
                             :current-page.sync="currentPage"
                             :page-sizes="[ 100, 500, 2000]"
                             :page-size="pageSizeData"
                             layout="total, sizes, prev, pager, next, jumper"
                             :total="totalCount">
            </el-pagination >
        </div>
    </div>
</template>

<script scoped>
    export default {
        data() {
            return {
                exportDynamicDetailsExcelFormVisible:false,
                totalCount:0,
                month:'',
                //工资报表数据集合
                wageExportEntity:{
                  startTime:'',
                  endTime:'',
                },

                wageReportList:[
                    {
                        //人员姓名
                        userName:'',
                        userCode:'',
                        //部门名称
                        deptName:'',
                        //岗位
                        postName:'',
                        //工号
                        workNumber:'',
                        //工资
                        wage:'',
                        //离职时间
                        quitTime:'',
                        //入职时间
                        entryTime:'',
                        //是否离职
                        isDeleted:'',
                        //在职时间
                        inserviceTime:'',
                    }
                ],
                pageSizeData: 100,
                currentPage: 0,
                start:1,
                loading : true,

                // 日期
                dateValue: '',

                // 高度
                floatDivHeight: 50
            }
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

        created:function (){
            var vm = {};
            vm = this;
            vm.loading=true;
            // 获取日期
            var sendObj = {};
            sendObj.start = (vm.start - 1) * vm.pageSizeData;
            sendObj.pageSize = this.pageSizeData;
            sendObj.entity = {"inMonth":this.month};

            var options = {
                method: 'POST',
                data: sendObj,
                url: "wages/list",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.loading=false;
                vm.wageReportList=response.data.result.data;
                vm.totalCount=response.data.result.totalCount;
                let h = vm.floatDivHeight;
                vm.floatDivHeight = h-1;
            }).catch(function (error) {
                vm.$message.error('页面：获取提成工资报表失败！');
            });
        },
        methods:{
            // 分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.currentPage = 1;
                this.handleCurrentChange(1);
            },
            // 分页:改变每页数量
            handleCurrentChange(val) {
                let vm = this;
                vm.loading=true;
                let sendObj = {};
                sendObj.pageSize = this.pageSizeData;
                sendObj.start = (val - 1) * (sendObj.pageSize);
                sendObj.entity = {"inMonth":this.month};
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "wages/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.loading=false;
                    vm.wageReportList=response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;
                    let h = vm.floatDivHeight;
                    vm.floatDivHeight = h-1;
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },

            handleNodeClick() {
                // 请求后台报表接口
                this.report();
            },
            exportExcel(){


                var url="/housekeeper/wages/wagesReportExport";
                if (null!=this.month){
                    url=url+"?inMonth="+this.month;
                }
                window.location.href=url;


            },
            exportDetailsExcel(){

                var url="/housekeeper/wages/exportDetailsExcel";
                if (null!=this.month){
                    url=url+"?inMonth="+this.month;
                }
                window.location.href=url;

            },
            dynamicDetailsExcel(){
                this.exportDynamicDetailsExcelFormVisible=true;
            },
            cleanExportEntity(){
              this.wageExportEntity={}
            },
            exportDynamicDetailsExcel(){
                var startTime = this.wageExportEntity.startTime;
                var endTime = this.wageExportEntity.endTime;
                if (startTime < endTime) {
                    var url="/housekeeper/wages/exportDetailsExcel?startTime=" + startTime + "&endTime=" + endTime;
                    // var url="http://localhost:6789"/housekeeper/wages/dynamicDetailsExcel?startTime=" + startTime + "&endTime=" + endTime;

                    window.location.href = url;
                    this.exportDynamicDetailsExcelFormVisible = false;

                } else {
                    alert("日期条件不规范");
                }


            },


            report(dynamic){
                var vm = {};
                vm = this;
                vm.loading=true;
                // 获取日期

                var sendObj = {};
                sendObj.start = (vm.start - 1) * vm.pageSizeData;
                sendObj.pageSize = this.pageSizeData;
                sendObj.entity = {"inMonth":this.month};
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "wages/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.loading=false;
                    vm.wageReportList=response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;
                    let h = vm.floatDivHeight;
                    vm.floatDivHeight = h-1;
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
            },
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
        margin-left: 20px;
    }

    .top_select{
        float: none;
        margin-left: 235px;
    }

    /*分页栏样式*/
    .page-box {
        height: 40px;
        padding-top: 10px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

    .left_bar{
        float:left;
        width: 15%;
        border-style: inset;
        border-width:0px 1px 0px 0px;
    }

</style>
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
                <!--<el-button type="success" icon="el-icon-download" class="navigate_select_button" @click="exportExcel()" >导出</el-button>-->
                <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportExcel()">导出</el-button>
                <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportFocusExcel()">集中获客分成导出</el-button>

            </div>
        </div>
        <!-- 数据表头 -->
        <div>
            <template>
                <el-table
                        id="financeReportId"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        :data="financeWagesReportList"
                        size="small"
                        :height=floatDivHeight
                        border>
                    <el-table-column  align="center"
                                      prop="orderCode"
                                      label="订单编号"
                                      width="180"
                                      fixed>
                    </el-table-column>
                    <el-table-column align="center"
                                     min-width="180px" label="开单时间"
                                     show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.orderTime!=null">{{ scope.row.orderTime | time}}</template>
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="receivableCost"
                                      label="成交支付协议服务费"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="sumPayment"
                                      label="已回款金额"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="回款时间"
                                      prop="payBackTime"
                                      min-width="180px">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="payDescription"
                                      label="回款说明"
                                      width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="sumTax"
                                      label="开票税金"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="backMoney"
                                      label="返佣"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="cusPartTimeMoney"
                                      label="客户兼职分钱"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="ownerPartTimeMoney"
                                      label="业主兼职分钱"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="comeCommission"
                                      label="剔除后实际业绩"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="orderStatus"
                                      label="订单状态"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="parentDeptName"
                                      label="所属分店上级分店"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="cdeptName"
                                      label="所属成交分店"
                                      width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="分店经理工号"
                                      width="130">
                        <template slot-scope="scope" v-if="scope.row.cleaderNumber!=null">{{ "工号:"+scope.row.cleaderNumber}}</template>

                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="cleaderName"
                                      label="分店经理"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="开单人员工号"
                                      width="130">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber!=null">{{ "工号:"+scope.row.clerkNumber}}</template>

                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkName"
                                      label="开单人员"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage"
                                      label="开单人员比例"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="performance"
                                      label="业绩"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="业务员1工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber1!=null">{{ "工号:"+scope.row.clerkNumber1}}</template>
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkName1"
                                      label="业务员1姓名"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage1"
                                      label="业务员1分业绩比例"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="performance1"
                                      label="业务员1业绩"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="业务员2工号"
                                      width="130">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber2!=null">{{ "工号:"+scope.row.clerkNumber2}}</template>
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkName2"
                                      label="业务员2姓名"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage2"
                                      label="业务员2分业绩比例"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="performance2"
                                      label="业务员2业绩"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="业务员3工号"
                                      width="130">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber3!=null">{{ "工号:"+scope.row.clerkNumber3}}</template>

                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkName3"
                                      label="业务员3姓名"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage3"
                                      label="业务员3分业绩比例"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="performance3"
                                      label="业务员3业绩"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="业务员4工号"
                                      width="130">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber4!=null">{{ "工号:"+scope.row.clerkNumber4}}</template>

                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkName4"
                                      label="业务员4姓名"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage4"
                                      label="业务员4分业绩比例"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="performance4"
                                      label="业务员4业绩"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="业务员5工号"
                                      width="130">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber5!=null">{{ "工号:"+scope.row.clerkNumber5}}</template>

                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkName5"
                                      label="业务员5姓名"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage5"
                                      label="业务员5分业绩比例"
                                      width="130">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="performance5"
                                      label="业务员5业绩"
                                      width="130">
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
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    export default {
        data() {
            return {
                totalCount:0,
                //工资报表数据集合
                financeWagesReportList:[
                    {
                        //订单号
                        orderCode:'',
                        //开单时间
                        orderTime:'',
                        //成交支付协议服务费,合同佣金
                        receivableCost:'',
                        //已回款金额
                        sumPayment:'',
                        //回款时间
                        payBackTime:'',
                        //开票税金
                        sumTax:'',
                        //返佣
                        backMoney:'',
                        //客户兼职分钱
                        cusPartTimeMoney:'',
                        //业主兼职分钱
                        ownerPartTimeMoney:'',
                        //剔除后实际业绩
                        comeCommission:'',
                        //订单状态
                        orderStatus:'',
                        //所属分店上级分店
                        parentDeptName:'',
                        //成交所属分店
                        cdeptName:'',
                        //分店经理工号
                        cleaderNumber:'',
                        //分店经理
                        cleaderName:'',
                        //开单员工工号
                        clerkNumber:'',
                        //开单人员
                        clerkName:'',
                        //开单人员比例
                        percentage:'',
                        //业绩
                        performance:'',
                        //业务员1工号
                        clerkNumber1:'',
                        //业务员1
                        clerkName1:'',
                        //业务员1分业绩比例
                        percentage1:'',
                        //业务员1业绩
                        performance1:'',
                        //业务员2工号
                        clerkNumber2:'',
                        //业务员2
                        clerkName2:'',
                        //业务员2分业绩比例
                        percentage2:'',
                        //业务员2业绩
                        performance2:'',
                        //业务员3工号
                        clerkNumber3:'',
                        //业务员3
                        clerkName3:'',
                        //业务员3分业绩比例
                        percentage3:'',
                        //业务员3业绩
                        performance3:'',
                        //业务员4工号
                        clerkNumber4:'',
                        //业务员4
                        clerkName4:'',
                        //业务员4分业绩比例
                        percentage4:'',
                        //业务员4业绩
                        performance4:'',
                        //业务员5工号
                        clerkNumber5:'',
                        //业务员5
                        clerkName5:'',
                        //业务员5分业绩比例
                        percentage5:'',
                        //业务员5业绩
                        performance5:'',
                        //发票号
                        invoiceCode:'',
                        //税额
                        invoiceTax:'',
                        //开票日期
                        invoiceTime:'',
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
            var startDate = null;
            var endDate = null;
            if(vm.dateValue != "" && vm.dateValue != null){
                var dateValues = vm.dateValue.toString().split(",");
                startDate = new Date(dateValues[0]);
                endDate = new Date(dateValues[1]);
            }
            var sendObj = {};
            sendObj.start = (vm.start - 1) * vm.pageSizeData;
            sendObj.pageSize = this.pageSizeData;
            sendObj.entity = {"startDate":startDate,"endDate":endDate};
            var options = {
                method: 'POST',
                data: sendObj,
                url: "wages/financeWagesQuery",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.loading=false;
                vm.financeWagesReportList=response.data.result.data;
                vm.totalCount=response.data.result.totalCount;
                let h = vm.floatDivHeight;
                vm.floatDivHeight = h-1;
            }).catch(function (error) {
                vm.$message.error('页面：获取人事版工资报表失败！');
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
                var startDate = null;
                var endDate = null;
                if(vm.dateValue != "" && vm.dateValue != null){
                    var dateValues = vm.dateValue.toString().split(",");
                    startDate = new Date(dateValues[0]);
                    endDate = new Date(dateValues[1]);
                }
                sendObj.entity = {"startDate":startDate,"endDate":endDate};
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "wages/financeWagesQuery",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.loading=false;
                    vm.financeWagesReportList=response.data.result.data;
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
                let vm = this;
                var startDate = null;
                var endDate = null;
                if(vm.dateValue != "" && vm.dateValue != null){
                    var dateValues = vm.dateValue;
                    startDate = dateValues[0].getTime();
                    endDate = dateValues[1].getTime();
                }
                window.location.href="/housekeeper/wages/financeWagesReportExport"+
                "?startDate="+startDate+"&endDate="+endDate;
                //本地调试打开
                // window.location.href="http://localhost:6789/housekeeper/wages/financeWagesReportExport"+
                // "?startDate="+startDate+"&endDate="+endDate;


            },
            exportFocusExcel(){
                // let vm = this;
                // var startDate = null;
                // var endDate = null;
                // if(vm.dateValue != "" && vm.dateValue != null){
                //     var dateValues = vm.dateValue;
                //     startDate = dateValues[0].getTime();
                //     endDate = dateValues[1].getTime();
                // }
                window.location.href="/housekeeper/wages/focusWageReportExport";
                //本地调试打开
                // window.location.href="http://localhost:6789/housekeeper/wages/financeWagesReportExport"+
                // "?startDate="+startDate+"&endDate="+endDate;


            },
            report(dynamic){
                var vm = {};
                vm = this;
                vm.loading=true;
                // 获取日期
                var startDate = null;
                var endDate = null;
                if(vm.dateValue != "" && vm.dateValue != null){
                    var dateValues = vm.dateValue.toString().split(",");
                    startDate = new Date(dateValues[0]);
                    endDate = new Date(dateValues[1]);
                }
                var sendObj = {};
                sendObj.start = (vm.start - 1) * vm.pageSizeData;
                sendObj.pageSize = this.pageSizeData;
                sendObj.entity = {"startDate":startDate,"endDate":endDate};

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "wages/financeWagesQuery",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.loading=false;
                    vm.financeWagesReportList=response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
            },
            // exportExcel(){
            //     /* 1创建 workbook 对象 from table */
            //     //alert("导出开始");
            //     var reports;
            //     var fix = document.querySelector('.el-table__fixed');
            //     if(fix){
            //         reports = document.querySelector('#financeReportId').removeChild(fix);
            //         document.querySelector('#financeReportId').appendChild(fix);
            //     }else{
            //         reports = document.querySelector('#financeReportId');
            //     }
            //     var wb = XLSX.utils.table_to_book(reports)
            //     /* 2获得 binary string as output */
            //     var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
            //     try {
            //         FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '财务版工资报表.xlsx')
            //     } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
            //     return wbout
            //
            // }
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
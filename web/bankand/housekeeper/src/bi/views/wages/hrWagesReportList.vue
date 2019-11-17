<template slot-scope="scope">
    <div class="root" ref="myRoot">
        <!-- 条件 -->
        <div class="top_bar">
            <div class="top_refresh">
                <el-button type="primary" size="mini" icon="el-icon-refresh" @click="report(true)" circle/>
                <b style="font-size: 16px;color: #395dd2">最新统计数据</b>
            </div>
            <div class="top_select">
                <!--<el-date-picker-->
                        <!--v-model="dateValue"-->
                        <!--type="daterange"-->
                        <!--align="right"-->
                        <!--unlink-panels-->
                        <!--size="small"-->
                        <!--start-placeholder="开始日期"-->
                        <!--end-placeholder="结束日期">-->
                <!--</el-date-picker>-->
                <!--<el-button type="primary" size="small" plain  icon="el-icon-search" @click="report">查询</el-button>-->
                <el-button type="primary" size="small" plain  icon="el-icon-download" @click="backEndExportExcel()">导出</el-button>
                <el-button type="primary" size="small" plain  icon="el-icon-download" @click="hrExportFocusExcel()">集中获客回款分成导出</el-button>

            </div>
        </div>

        <!-- 数据表头 -->
        <div>
            <template>
                <el-table
                        id="personnelReportId"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        :data="personnelReportList"
                        size="small"
                        :height=floatDivHeight
                        border>
                    <el-table-column  align="center"
                                      fixed
                                      prop="orderCode"
                                      label="订单编号"
                                      width="180">
                    </el-table-column>
                    <el-table-column align="center"
                                     min-width="180px" label="开单时间"
                                     show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.orderTime!=null">{{ scope.row.orderTime | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="receivableCost"
                                      label="成交支付协议服务费"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="payPayment"
                                      label="回款金额"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="回款时间"
                                      show-overflow-tooltip
                                      min-width="180px">
                        <template slot-scope="scope" v-if="scope.row.payBackTime!=null">{{ scope.row.payBackTime | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="payDescription"
                                      label="汇款描述"
                                      width="80px">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="payRemark"
                                      label="回款说明"
                                      width="280px">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="sumTax"
                                      label="开票税金"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="backMoney"
                                      label="返佣"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="cusPartTimeMoney"
                                      label="客户兼职分钱"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="ownerPartTimeMoney"
                                      label="业主兼职分钱"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="comeCommission"
                                      label="剔除后实际业绩"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="orderStatus"
                                      label="订单状态"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="parentDeptName"
                                      label="所属分店上级分店"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="dealDeptName"
                                      label="所属成交分店"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="分店经理工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.dealLeaderNumber!=null">{{ "工号:"+scope.row.dealLeaderNumber}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="dealLeaderName"
                                      label="分店经理"
                                      width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      label="开单人员工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber!=null">{{ "工号:"+scope.row.clerkNumber}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="clerkName"
                                      label="开单人员"
                                      width="300">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkDeptName"
                                      label="开单人员所属部门"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="percentage"
                                      label="开单人员比例"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="performance"
                                      label="业绩"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="业务员1工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber1!=null">{{ "工号:"+scope.row.clerkNumber1}}</template>
                    </el-table-column>
                    <el-table-column about="center"
                                     prop="clerkName1"
                                     label="业务员1"
                                     width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkDeptName1"
                                      label="业务员1所属部门"
                                      width="300">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage1"
                                      label="业务员1分业绩比例"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="performance1"
                                      label="业务员1业绩"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="业务员2工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber2!=null">{{ "工号:"+scope.row.clerkNumber2}}</template>
                    </el-table-column>
                    <el-table-column about="center"
                                     prop="clerkName2"
                                     label="业务员2"
                                     width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkDeptName2"
                                      label="业务员2所属部门"
                                      width="300">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage2"
                                      label="业务员2分业绩比例"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="performance2"
                                      label="业务员2业绩"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="业务员3工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber3!=null">{{ "工号:"+scope.row.clerkNumber3}}</template>
                    </el-table-column>
                    <el-table-column about="center"
                                     prop="clerkName3"
                                     label="业务员3"
                                     width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkDeptName3"
                                      label="业务员3所属部门"
                                      width="300">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage3"
                                      label="业务员3分业绩比例"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="performance3"
                                      label="业务员3业绩"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="业务员4工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber4!=null">{{ "工号:"+scope.row.clerkNumber4}}</template>
                    </el-table-column>
                    <el-table-column about="center"
                                     prop="clerkName4"
                                     label="业务员4"
                                     width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkDeptName4"
                                      label="业务员4所属部门"
                                      width="300">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage4"
                                      label="业务员4分业绩比例"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="performance4"
                                      label="业务员4业绩"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      label="业务员5工号"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.clerkNumber5!=null">{{ "工号:"+scope.row.clerkNumber5}}</template>
                    </el-table-column>
                    <el-table-column about="center"
                                     prop="clerkName5"
                                     label="业务员5"
                                     width="200">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="clerkDeptName5"
                                      label="业务员5所属部门"
                                      width="300">
                    </el-table-column>
                    <el-table-column  align="center"
                                      prop="percentage5"
                                      label="业务员5分业绩比例"
                                      width="300">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="performance5"
                                      label="业务员5业绩"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode"
                                      label="发票号"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax"
                                      label="税额"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime"
                                      label="开票时间"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime!=null">{{ scope.row.invoiceTime | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode1"
                                      label="发票号1"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax"
                                      label="税额1"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime1"
                                      label="开票时间1"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime1!=null">{{ scope.row.invoiceTime1 | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode2"
                                      label="发票号2"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax2"
                                      label="税额2"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime2"
                                      label="开票时间2"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime2!=null">{{ scope.row.invoiceTime2 | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode3"
                                      label="发票号3"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax3"
                                      label="税额3"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime3"
                                      label="开票时间3"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime3!=null">{{ scope.row.invoiceTime3 | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode4"
                                      label="发票号4"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax4"
                                      label="税额4"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime4"
                                      label="开票时间4"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime4!=null">{{ scope.row.invoiceTime4 | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode5"
                                      label="发票号5"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax5"
                                      label="税额5"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime5"
                                      label="开票时间5"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime5!=null">{{ scope.row.invoiceTime5 | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode6"
                                      label="发票号6"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax6"
                                      label="税额6"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime6"
                                      label="开票时间6"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime6!=null">{{ scope.row.invoiceTime6 | time}}</template>
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceCode7"
                                      label="发票号7"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTax7"
                                      label="税额7"
                                      width="200">
                    </el-table-column>

                    <el-table-column  align="center"
                                      prop="invoiceTime7"
                                      label="开票时间7"
                                      width="200">
                        <template slot-scope="scope" v-if="scope.row.invoiceTime7!=null">{{ scope.row.invoiceTime7 | time}}</template>
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
                             :page-sizes="[ 100,500,2000,10000]"
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
                personnelReportList:[
                    {
                        //订单号
                        orderCode:'',
                        //开单时间
                        orderTime:'',
                        //成交支付协议服务费,合同佣金
                        receivableCost:'',
                        //回款金额
                        payPayment:'',
                        //回款时间
                        payBackTime:'',
                        //汇款描述
                        payDescription:'',
                        //回款说明
                        payRemark:'',
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
                        dealDeptName:'',
                        //分店经理工号
                        dealLeaderNumber:'',
                        //分店经理
                        dealLeaderName:'',
                        //开单员工工号
                        clerkNumber:'',
                        //开单人员
                        clerkName:'',
                        //开单人员所属部门
                        clerkDeptName:'',
                        //开单人员比例
                        percentage:'',
                        //业绩
                        performance:'',
                        //业务员1工号
                        clerkNumber1:'',
                        //业务员1
                        clerkName1:'',
                        //业务员1所属部门
                        clerkDeptName1:'',
                        //业务员1分业绩比例
                        percentage1:'',
                        //业务员1业绩
                        performance1:'',
                        //业务员2工号
                        clerkNumber2:'',
                        //业务员2
                        clerkName2:'',
                        //业务员2所属部门
                        clerkDeptName2:'',
                        //业务员2分业绩比例
                        percentage2:'',
                        //业务员2业绩
                        performance2:'',
                        //业务员3工号
                        clerkNumber3:'',
                        //业务员3
                        clerkName3:'',
                        //业务员3所属部门
                        clerkDeptName3:'',
                        //业务员3分业绩比例
                        percentage3:'',
                        //业务员3业绩
                        performance3:'',
                        //业务员4工号
                        clerkNumber4:'',
                        //业务员4所属部门
                        clerkDeptName4:'',
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
                        //业务员5所属部门
                        clerkDeptName5:'',
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
                        //发票号1
                        invoiceCode1:'',
                        //税额1
                        invoiceTax1:'',
                        //开票日期1
                        invoiceTime1:'',
                        //发票号2
                        invoiceCode2:'',
                        //税额2
                        invoiceTax2:'',
                        //开票日期2
                        invoiceTime2:'',
                        //发票号3
                        invoiceCode3:'',
                        //税额3
                        invoiceTax3:'',
                        //开票日期3
                        invoiceTime3:'',
                        //发票号4
                        invoiceCode4:'',
                        //税额4
                        invoiceTax4:'',
                        //开票日期4
                        invoiceTime4:'',
                        //发票号5
                        invoiceCode5:'',
                        //税额5
                        invoiceTax5:'',
                        //开票日期5
                        invoiceTime5:'',
                        //发票号6
                        invoiceCode6:'',
                        //税额6
                        invoiceTax6:'',
                        //开票日期6
                        invoiceTime6:'',
                        //发票号7
                        invoiceCode7:'',
                        //税额7
                        invoiceTax7:'',
                        //开票日期7
                        invoiceTime7:''
                    }
                ],
                pageSizeData: 100,
                currentPage: 1,
                start:1,
                loading : true,
                // 日期
                dateValue: '',
                //订单导出
                hrWagesDialogFormVisible:false,

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
            // 获取日期
            var startDate = null;
            var endDate = null;
            if(vm.dateValue != "" && vm.dateValue != null){
                var dateValues = vm.dateValue.toString().split(",");
                startDate = new Date(dateValues[0]);
                endDate = new Date(dateValues[1]);
            }
            var sendObj = {};
            sendObj.startTime=startDate;
            sendObj.endTime = endDate;
            sendObj.start = (vm.start - 1) * vm.pageSizeData;
            sendObj.pageSize = this.pageSizeData;
            var options = {
                method: 'POST',
                data: sendObj,
                url: "report/personnelReportSearch",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.loading=false;
                vm.personnelReportList = response.data.result.data;
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
                let sendObj = {};
                vm.loading=true;
                var startDate = null;
                var endDate = null;
                if(vm.dateValue != "" && vm.dateValue != null){
                    var dateValues = vm.dateValue.toString().split(",");
                    startDate = new Date(dateValues[0]);
                    endDate = new Date(dateValues[1]);
                }
                sendObj.startTime=startDate;
                sendObj.endTime = endDate;
                sendObj.pageSize = this.pageSizeData;
                sendObj.start = (val - 1) * (sendObj.pageSize);
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/hrWageReportSearch",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.loading=false;
                    vm.personnelReportList=response.data.result.data;
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

            report(dynamic){
                var vm = {};
                vm = this;
                vm.loading=true;
                // 获取日期
                // var startDate = null;
                // var endDate = null;
                // if(vm.dateValue != "" && vm.dateValue != null){
                //     var dateValues = vm.dateValue.toString().split(",");
                //     startDate = new Date(dateValues[0]);
                //     endDate = new Date(dateValues[1]);
                // }
                var sendObj = {};
                // sendObj.startTime=startDate;
                // sendObj.endTime = endDate;
                sendObj.start = (vm.start - 1) * vm.pageSizeData;
                sendObj.pageSize = this.pageSizeData;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/hrWageReportSearch",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.loading=false;
                    vm.personnelReportList=response.data.result.data;
                    vm.totalCount=response.data.result.totalCount;
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
            },
            backEndExportExcel(){
                var vm = {};
                vm = this;
                // 获取日期
                // var startDate = null;
                // var endDate = null;
                // if(vm.dateValue != "" && vm.dateValue != null){
                //     var dateValues = vm.dateValue.toString().split(",");
                //     startDate = new Date(dateValues[0]);
                //     endDate = new Date(dateValues[1]);
                // }
                window.location.href="/housekeeper/wages/hrWageReportExport";
            },
            hrExportFocusExcel(){
                var vm = {};
                vm = this;
                // 获取日期
                // var startDate = null;
                // var endDate = null;
                // if(vm.dateValue != "" && vm.dateValue != null){
                //     var dateValues = vm.dateValue.toString().split(",");
                //     startDate = new Date(dateValues[0]);
                //     endDate = new Date(dateValues[1]);
                // }
                window.location.href="/housekeeper/wages/focusPayBackWageReportExport";
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
        margin-left: 235px;
    }

    /*分页栏样式*/
    .page-box {
        height: 40px;
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
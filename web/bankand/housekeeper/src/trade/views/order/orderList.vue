<template slot-scope="scope" xmlns="http://www.w3.org/1999/html"
          xmlns="http://www.w3.org/1999/html">
    <div class="root" ref="myRoot">
        <el-form>

            <!--订单详情面板-->

                <OrderDetail :orderCode="orderCode"
                             v-if="OrderDetailOpenOrClose" ref="refOrderDetail"/>

            <!--订单查询面板-->
            <el-dialog  :close-on-click-modal="false" :visible.sync="searchDialogFormVisible" width="50%" append-to-body>
                <el-form :model="searchOrderCondition">
                    <div>
                        <el-row :gutter="10">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <span style="font-weight: 900;font-size: 15px;">按区域查询</span>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="所属省份" prop="province">
                                    <el-select size="mini" id="provinceNameCpy"
                                               v-model="searchOrderCondition.province"
                                               @visible-change="handNeedProvinceCpy"
                                               @change="changeProvCpy" placeholder="请选择">
                                        <el-option
                                                v-for="(item, indexkey) in itProvinceArrCpy"
                                                :key="item.areaCode"
                                                :value="item.areaCode"
                                                :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item  label="所属城市" prop="city">
                                    <el-select size="mini" id="cityNameCpy" v-model="searchOrderCondition.city"
                                               @focus="getCitieListCpy(searchOrderCondition.province)"
                                               @change="changeCityCpy" placeholder="请选择">
                                        <el-option
                                                v-for="(item, indexkey) in itCityArrCpy"
                                                :key="item.areaCode"
                                                :value="item.areaCode"
                                                :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="所属区域" prop="region">
                                    <el-select size="mini" id="regionNameCpy"
                                               v-model="searchOrderCondition.region"
                                               @focus="getAreaListCpy(searchOrderCondition.city)"
                                               @change="changeRegionCpy" placeholder="请选择">
                                        <el-option
                                                v-for="(item, indexkey) in itAreaArrCpy"
                                                :key="item.areaCode"
                                                :value="item.areaCode"
                                                :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="所属街道" prop="street">
                                    <el-select size="mini" id="streetNameCpy"
                                               v-model="searchOrderCondition.street"
                                               @focus="getStreetListCpy(searchOrderCondition.region)"
                                               placeholder="请选择">
                                        <el-option
                                                v-for="(item, indexkey) in itStreetArrCpy"
                                                :key="item.areaCode"
                                                :value="item.areaCode"
                                                :label="item.name">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <span style="font-weight: 900;font-size: 15px;">按部门或人员查询</span>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="所属部门">
                                    <el-select size="mini" v-model="searchOrderCondition.createDeptCode"
                                               filterable placeholder="请选择">
                                        <el-option
                                                v-for="item in deptEntity"
                                                :key="item.deptCode"
                                                :label="item.deptName"
                                                :value="item.deptCode">
                                            <span style="float: left">{{ item.deptName }}</span>
                                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode
                                                }}</span>
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                <el-form-item label="所属人员" prop="empName" label-width="80px">
                                    <el-input size="mini" v-model="searchOrderCondition.empName"
                                              placeholder="请选择开单人">
                                        <el-button size="mini" slot="append" icon="el-icon-search"
                                                   @click="openUser()"></el-button>
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <span style="font-weight: 900;font-size: 15px;">按订单创建时间查询</span>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <el-date-picker
                                        size="mini"
                                        v-model="searchOrderCondition.orderCreateTimeStartAndEnd"
                                        type="daterange"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        :default-time="['00:00:00', '23:59:59']"
                                        class="contract_start">
                                </el-date-picker>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <span style="font-weight: 900;font-size: 15px;">按预计收款时间查询</span>
                            </el-col>
                        </el-row>

                        <el-row :gutter="5">
                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                <el-date-picker
                                        size="mini"
                                        v-model="searchOrderCondition.expectPaymentTimeStartAndEnd"
                                        type="daterange"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        :default-time="['00:00:00', '23:59:59']"
                                        class="contract_start">
                                </el-date-picker>
                            </el-col>
                        </el-row>
                    </div>
                </el-form>
                <div style="text-align:center;line-height: 10px;">
                    <el-button size="mini" @click="cleanSerchOrder()">清空条件</el-button>
                    <el-button size="mini" type="primary" @click="serchOrderClick()">按照条件查询</el-button>
                </div>
            </el-dialog>

            <!--订单导出面板-->
            <el-dialog  :close-on-click-modal="false" :visible.sync="orderExportDialogFormVisible" width="40%" append-to-body>
                <el-form :model="orderExportEntity">
                    <el-row :gutter="5">
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <span class="demonstration">订单日期开始时间</span>
                            <el-date-picker
                                    v-model="orderExportEntity.orderStartTime"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                            <span class="demonstration">订单日期结束时间</span>
                            <el-date-picker
                                    v-model="orderExportEntity.orderEndTime"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-col>
                    </el-row>

                </el-form>
                <div style="text-align:center;margin-top: 30px;">
                    <el-button @click="cleanrderExportEntity()">清空条件</el-button>
                    <el-button type="primary" @click="orderExport()"
                               v-loading.fullscreen.lock="fullscreenLoading">导出
                    </el-button>
                </div>
            </el-dialog>

            <!--订单跟进新增-->
            <el-dialog  :close-on-click-modal="false" style="height: 800px;width: 100%" append-to-body
                       :visible.sync="OrderFollowupCreateDialogFormVisible">
                <OrderFollowupDetail :orderCode="orderCode"
                                     v-if="OrderFollowupCreateDialogFormVisible"
                                     :OrderFollowupCreateDialogFormVisible="OrderFollowupCreateDialogFormVisible"
                                     v-on:changed="closeOrderFollowupCreate($event)"></OrderFollowupDetail>
            </el-dialog>

            <!--人员查询公共面板-->
                <CommonInternalUser v-if="userVisible" ref="user"
                                    v-on:changed2="closeEmpCommon($event)"></CommonInternalUser>

            <!--订单开票面板-->
            <order-invoice-apply-create v-if="OrderInvoiceApplyCreateOpenOrClose" ref="refOrderInvoiceApplyCreate"
                            @changeOrderInvoiceApplyCreate = "successOrderInvoiceApplyCreate"></order-invoice-apply-create>


            <div class="navigation_div">
                <div class="titles">
                    <span class="navigation-title">按编码查询</span>
                    <el-input
                            placeholder="请输入订单、房源编码、客户编码或手机"
                            class="input-with-select" size="mini" clearable v-model="keyword">
                        <el-button slot="append" type="primary" @click="easySearch()">搜索</el-button>
                    </el-input>
                    <el-button class="navigation_button_back" v-show="OrderStatisticsBtn" size="mini" type="success"
                               @click="orderExportClick(1)">订单统计导出
                    </el-button>
                    <el-button class="navigation_button_back" v-show="OrderDivideIntoBtn" size="mini" type="success"
                               @click="orderExportClick(2)">订单分成导出
                    </el-button>
                    <el-button class="navigation_button_back" v-show="OrderTransDeptBtn" size="mini" type="success"
                               @click="orderExportClick(3)">跨部门订单导出
                    </el-button>
                    <el-button v-show="OrderUpdateLogBtn" size="mini" type="success"
                               @click="orderExportClick(4)">订单修改历史导出
                    </el-button>
                    <file v-show="paybackFileTitleBtn" :title="paybackFileTitle"
                          :url="paybackFileUrl" class="navigation_button_back"
                          v-on:refresh="refresh()"></file>
                    <file v-show="invoiceFileTitleBtn" :title="invoiceFileTitle"
                          :url="invoiceFileUrl" class="navigation_button_back"
                          v-on:refresh="refresh()"></file>
                    <el-button class="navigation_button_back" size="mini" type="primary"
                               @click="searchOrder()">自定义查询订单
                    </el-button>
                </div>
            </div>
            <!-- 列表 -->
            <div>
                <template>
                    <el-table
                            :data="orderList"
                            size="small"
                            :height="fullHeight"
                            border
                            highlight-current-row
                            @current-change="selectTable">
                        <el-table-column align="center"
                                         fixed
                                         prop="orderCode"
                                         label="订单编号"
                                         width="180">
                        </el-table-column>


                        <el-table-column align="center"
                                         min-width="180px" label="跟进时间"
                                         show-overflow-tooltip>
                            <template slot-scope="scope">{{ scope.row.orderTime | time}}</template>
                        </el-table-column>


                        <el-table-column align="center"
                                         prop="orderStatusName"
                                         label="订单状态"
                                         width="120">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="commission"
                                         label="佣金总计"
                                         width="120">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="receivableHos"
                                         label="业主佣金"
                                         width="120">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="receivableCus"
                                         label="客户佣金"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="cusRebate"
                                         label="客户返佣"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="ownerRebate"
                                         label="业主返佣"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="cusParttimeMoney"
                                         label="客户兼职分钱"
                                         width="120">
                        </el-table-column>


                        <el-table-column align="center"
                                         prop="ownerParttimeMoney"
                                         label="业主兼职分钱"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="actualCommision"
                                         label="实际业绩总额"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="actualBranchCommision"
                                         label="业务员实际业绩"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="actualCusServCommision"
                                         label="客服实际业绩"
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="hasCommissionConfirm"
                                         label="是否回收佣金确认书"
                                         width="150">
                            <template slot-scope="scope">
                                {{scope.row.hasCommissionConfirm == true ? "是" : "否"}}
                            </template>
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="cusCommissionNum"
                                         label="客户佣金确认书编号"
                                         width="150">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="ownerCommissionNum"
                                         label="业主佣金确认书编号"
                                         width="150">
                        </el-table-column>


                        <el-table-column align="left"
                                         fixed="right"
                                         label="操作"
                                         width="190"
                                         v-if = "!commissionConfirmBtn">
                            <template slot-scope="scope">
                                <el-button type="text" size="small"
                                           @click=openOrderDetail(scope.row.orderCode)>查看
                                </el-button>
                                <el-button type="text" size="small"
                                           @click=openOrderFollowupDetail(scope.row.orderCode)>订单跟进
                                </el-button>
                                <el-button type="text" size="small"
                                           v-if="orderInvoiceApplyCreateBtn"
                                           v-show="scope.row.orderStatus == '1' ||
                                                   scope.row.orderStatus == '2' ||
                                                   scope.row.orderStatus == '5' ||
                                                   scope.row.orderStatus == '8'"
                                           @click=openOrderInvoiceApplyCreate(scope.row.orderCode,scope.row.commission)>订单开票
                                </el-button>
                            </template>
                        </el-table-column>
                        <el-table-column align="left"
                                         fixed="right"
                                         label="操作"
                                         width="250"
                                         v-if = "commissionConfirmBtn">
                            <template slot-scope="scope">
                                <el-button type="text" size="small"
                                           @click=openOrderDetail(scope.row.orderCode)>查看
                                </el-button>
                                <el-button type="text" size="small"
                                           @click=openOrderFollowupDetail(scope.row.orderCode)>订单跟进
                                </el-button>
                                <el-button type="text" size="small"
                                           v-if="orderInvoiceApplyCreateBtn"
                                           v-show="scope.row.orderStatus == '1' ||
                                                   scope.row.orderStatus == '2' ||
                                                   scope.row.orderStatus == '5' ||
                                                   scope.row.orderStatus == '8'"
                                           @click=openOrderInvoiceApplyCreate(scope.row.orderCode,scope.row.commission)>订单开票
                                </el-button>
                                <el-button type="text" size="small"
                                           v-if = "commissionConfirmBtn"
                                           v-show = "scope.row.hasCommissionConfirm == false"
                                           @click=hasCommissionConfirm(scope.row.orderCode)>佣金确认书
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </div>


            <!-- 分页 -->
            <div class="page-box">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[30]"
                               :page-size="pageSizeData"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>
            </div>

        </el-form>

    </div>
</template>


<script scoped>
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import OrderDetail from "./orderDetail.vue";
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import File from "../../../common/components/orderFile.vue";
    import ElDialog from "../../../../node_modules/element-ui/packages/dialog/src/component";
    import ElForm from "../../../../node_modules/element-ui/packages/form/src/form";
    import ElRow from "element-ui/packages/row/src/row";
    import OrderFollowupDetail from "../orderfollowup/orderFollowupDetail.vue";
    import CommonInternalUser from "../../../hrm/views/emp/CommonInternalUser.vue"
    import orderInvoiceApplyCreate from "../orderInvoice/orderInvoiceApplyCreate.vue"

    export default{
        components: {
            ElRow,
            ElForm,
            ElDialog,
            ElButton,
            ElInput,
            OrderDetail,
            File,
            OrderFollowupDetail,
            CommonInternalUser,
            orderInvoiceApplyCreate
        },
        created: function () {
            var vm = this;
            vm.invoiceFileTitleBtn = vm.$flagMenuStore.judgeMenu("invoice-file-title-btn");
            vm.paybackFileTitleBtn = vm.$flagMenuStore.judgeMenu("payback-file-title-btn");
            vm.OrderStatisticsBtn = vm.$flagMenuStore.judgeMenu("order-statistics-btn");
            vm.OrderDivideIntoBtn = vm.$flagMenuStore.judgeMenu("order-divide-into-btn");
            vm.OrderTransDeptBtn = vm.$flagMenuStore.judgeMenu("order-trans-dept-btn");
            vm.OrderUpdateLogBtn = vm.$flagMenuStore.judgeMenu("order-update-log-btn");
            vm.orderInvoiceApplyCreateBtn = vm.$flagMenuStore.judgeMenu("order-invoice-create");
            vm.commissionConfirmBtn =  vm.$flagMenuStore.judgeMenu("commission-confirm");
            this.loadOrder(vm.searchOrderCondition);
            this.loadDeptList();
        },
        mounted(){

        },
        updated(){

        },
        data(){
            return {
                userVisible:false,
                fullHeight: document.documentElement.clientHeight - 205,
                //人员查询组件dialog
                empCommonDialogFormVisible: false,
                orderInvoiceApplyCreateBtn: false,//订单开票按钮权限
                OrderInvoiceApplyCreateOpenOrClose: false,//订单开票组件打开或关闭
                //订单跟进创建dialog
                OrderFollowupCreateDialogFormVisible: false,
                //订单详情关闭或打开
                OrderDetailOpenOrClose: false,
                //文件dialog
                fielDialogFormVisible: false,
                //订单查询dialog
                searchDialogFormVisible: false,
                //近期应收账款枚举
                paymentDay: [{
                    value: '1',
                    label: '0-30'
                }, {
                    value: '2',
                    label: '30-60'
                }, {
                    value: '3',
                    label: '60-90'
                }, {
                    value: '4',
                    label: '90-180'
                }, {
                    value: '5',
                    label: '180天以上'
                }],
                //订单编号
                orderCode: '',
                pageSizeData: 30,
                totalCount: 1,
                currentPage: 1,
                //列表值
                orderList: [],
                //关键字搜索（订单编号，房源编号，客户编号）
                keyword: '',
                //自定义查询参数aaa
                searchOrderCondition: {
                    province: "",
                    city: "",
                    region: "",
                    street: "",

                    provinceName: "",
                    cityName: "",
                    regionName: "",
                    streetName: "",

                    createDeptCode: "",
                    empCode: "",
                    empName: "",
                    //预计收款时间
                    expectPaymentTimeStartAndEnd: [],
                    //业绩时间
                    orderCreateTimeStartAndEnd: [],
                    //预计收款天数范围
                    paymentDay: "",
                    queryType : '4',//查询类型
                },
                //回款上传按钮的title
                paybackFileTitle: "回款数据上传",
                //回款上传url
                paybackFileUrl: "orderPayback/uploadFile",
                //发票上传按钮的title
                invoiceFileTitle: "发票数据上传",
                //发票上传按钮的url
                invoiceFileUrl: "orderInvoice/uploadFile",
                //所属省份
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                //所属街道
                itStreetArrCpy: [],
                //部门
                deptEntity: [],
                //回款按钮
                paybackFileTitleBtn: false,
                //发票按钮
                invoiceFileTitleBtn: false,
                //订单统计导出
                exportOrderStatistics: false,
                //订单导出
                orderExportDialogFormVisible: false,
                //订单导出参数
                orderExportEntity: {
                    orderStartTime: "",
                    orderEndTime: "",

                },
                orderExportType: 0,
                fullscreenLoading: false,
                OrderStatisticsBtn: false,
                OrderDivideIntoBtn: false,
                OrderTransDeptBtn: false,
                OrderUpdateLogBtn:false,
                commissionConfirmBtn:false
            }
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205;
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

            openUser(){
                var vm = this;
                vm.userVisible=true;
                vm.$nextTick(()=>{
                    vm.$refs.user.empCommonDialogFormVisible = true ;
                })
            },

            orderExportClick(num){
                this.orderExportType = num;
                this.orderExportDialogFormVisible = true;

            },
            orderExport(){
                var orderStartTime = this.orderExportEntity.orderStartTime;
                var orderEndTime = this.orderExportEntity.orderEndTime;
                var orderType = this.orderExportType;
                var href=null;


                if (orderStartTime < orderEndTime) {
                    window.location.href = "/housekeeper/order/orderExport?orderStartTime=" + orderStartTime + "&orderEndTime=" + orderEndTime + "&orderExportType=" + orderType + "";
                    this.orderExportDialogFormVisible = false;
                    this.fullscreenLoading = true;
                    setTimeout(() => {
                        this.fullscreenLoading = false;
                    }, 5000);
                } else {
                    alert("时间不对");
                }
            },
            //清空导出条件
            cleanrderExportEntity(){
                this.orderExportEntity = {};
            },
            //关闭订单跟进新增
            closeOrderFollowupCreate(data){
                this.OrderFollowupCreateDialogFormVisible = data;
            },
            //关闭人员公共面板
            closeEmpCommon(data){
                var ddd = data;
                this.userVisible = data.userVisible;
                this.searchOrderCondition.empCode = data.userCode;
                this.searchOrderCondition.empName = data.userName;
            },
            //刷新
            refresh(){
                var obj = this.searchOrderCondition;
                obj = {
                    keyword: this.keyword
                }
                this.loadOrder(obj);
            },
            //点击自定义查询面板
            searchOrder(){
                this.searchDialogFormVisible = true;
            },
            selectTable(val) {
                var currentRow = this.currentRow = val;
                this.orderCode = currentRow.orderCode;
            },
            //佣金确认书
            hasCommissionConfirm(val){
                var vm = this;
                this.$confirm('佣金确认书确认是否回收?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var obj = {};
                    var sendObj = {};
                    sendObj.entity = {
                        orderCode: val
                    };
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "order/hasCommissionConfirm",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.handleCurrentChange(vm.currentPage);
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });

            },
            //通过自定义查询条件查询订单数据
            serchOrderClick(){
                var obj = this.searchOrderCondition;
                this.loadOrder(obj);
            },
            //清空自定义查询面板页面参数
            cleanSerchOrder(){
                this.searchOrderCondition = {};
            },
            //关键字查询方法
            easySearch(){
                var obj = {
                    keyword: this.keyword
                }
                this.loadOrder(obj);
            },
            //加载订单列表数据
            loadOrder(data){
                var vm = this;
                var obj = {};
                if (data != null) {
                    obj = data;
                }
                var sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 30;
                sendObj.entity = obj;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "order/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.orderList = response.data.result.data;
                    console.log(vm.orderList)
                    vm.totalCount = response.data.result.totalCount;
                    vm.searchDialogFormVisible = false;


                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!user/query');
                });
            },
            //加载部门数据
            loadDeptList(){
                var vm = this;
                var obj = {};
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/findDeptName",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.deptEntity = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!user/query');
                });
            },

            //打开订单详情组件
            openOrderDetail(orderCode) {
                this.OrderDetailOpenOrClose = true;
                //传值给订单详情组件
                this.orderCode = orderCode;
                this.$nextTick(()=>{
                    this.$refs.refOrderDetail.OrderDetailDialogFormVisible = true;
                    this.$refs.refOrderDetail.loadOrderEntity();
                })

            },
            //打开订单详情组件
            openOrderFollowupDetail(orderCode){
                this.OrderFollowupCreateDialogFormVisible = true;
                this.orderCode = orderCode;
            },
            //打开订单开票组件
            openOrderInvoiceApplyCreate(val,val2){
                this.OrderInvoiceApplyCreateOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.refOrderInvoiceApplyCreate.visible = true;
                    this.$refs.refOrderInvoiceApplyCreate.init(val,val2);
                })
            },
            //开票成功 或者取消开票
            successOrderInvoiceApplyCreate(){
                this.OrderInvoiceApplyCreateOpenOrClose = false;
                this.$refs.refOrderInvoiceApplyCreate.visible = false;
            },
            changeRegionCpy(aval){
                //清空code
                this.searchOrderCondition.street = "";
            },
            changeCityCpy(aval){
                //清空code
                this.searchOrderCondition.region = "";
                this.searchOrderCondition.street = "";

            },
            //改变省
            changeProvCpy(aval){
                //清空code
                this.searchOrderCondition.city = "";
                this.searchOrderCondition.region = "";
                this.searchOrderCondition.street = "";

            },
            //改变区域
            changeRegionBase(aval){
                this.baseAreaObj.street = "";
                //赋值县区name
                let areaObj = {};
                areaObj = this.itAreaArrBase.find((item) => {
                    return item.areaCode === aval;
                });
                this.baseAreaObj.regionName = areaObj.name;
                //清空name
                this.baseAreaObj.streetName = "";
            },
            //改变市
            changeCityBase(aval){
                this.baseAreaObj.region = "";
                this.baseAreaObj.street = "";

                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item) => {
                    return item.areaCode === aval;
                });
                this.baseAreaObj.cityName = areaObj.name;
                //清空name
                this.baseAreaObj.regionName = "";
                this.baseAreaObj.streetName = "";
            },
            changeProvBase(aval){
                //清空code
                this.baseAreaObj.city = "";
                this.baseAreaObj.region = "";
                this.baseAreaObj.street = "";

                //赋值省name
                let areaObj = {};
                areaObj = this.itProvinceArrBase.find((item) => {
                    return item.areaCode === aval;
                });
                this.baseAreaObj.provinceName = areaObj.name;
                //清空name
                this.baseAreaObj.cityName = "";
                this.baseAreaObj.regionName = "";
                this.baseAreaObj.streetName = "";


            },
            /*省份查询*/
            handNeedProvinceCpy(val){
                var vm = this;
                var obj = {};
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrCpy = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                        type: 'error'
                    });
                });
            },
            /*市区查询*/
            getCitieListCpy(aVal) {
                if (aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrCpy = response.data.result;
                    }).catch(function (error) {

                    });
                }

            },
            /*区域查询*/
            getAreaListCpy(bVal) {
                if (bVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrCpy = response.data.result;
                    }).catch(function (error) {

                    });
                }
            },
            /*街道查询*/
            getStreetListCpy(aVal){
                if (aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {

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
                let sendObj = {};
                sendObj.pageSize = this.pageSizeData;
                sendObj.start = (val - 1) * (sendObj.pageSize);
                sendObj.keyword = this.keyword;
                sendObj.entity = this.searchOrderCondition
                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "order/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.orderList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    console.log(response.data.result.data)
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },


        }
    }
</script>


<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }




    /*导航栏DIV*/
    .navigation_div {
        margin-top: 20px;
        margin-left: 20px;
        margin-bottom: 20px;
        width: 100%;
    }

    .navigation_button_back {
        display: inline-block;
    }

    .navigation-title {
        font-size: 13px;
    }

    .input-with-select {
        width: 270px;
        margin-right:20px ;
    }

    .contract_start {
        width: 92%;
        height: 26px;
    }

    .page-box {
        height: 40px;
        line-height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

</style>
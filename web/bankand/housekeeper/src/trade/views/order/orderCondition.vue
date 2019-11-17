<template>
    <!--订单查询面板-->
    <el-dialog  :close-on-click-modal="false" :visible.sync="searchDialogFormVisible" width="30%" append-to-body>
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
                            <el-select id="provinceNameCpy"
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
                        <el-form-item label="所属城市" prop="city">
                            <el-select id="cityNameCpy" v-model="searchOrderCondition.city"
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
                            <el-select id="regionNameCpy"
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
                            <el-select id="streetNameCpy"
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
                            <el-select v-model="searchOrderCondition.createDeptCode"
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
                            <el-input v-model="searchOrderCondition.empName"
                                      placeholder="请选择开单人" size="small">
                                <el-button slot="append" icon="el-icon-search"
                                           @click="empCommonDialogFormVisible = true"></el-button>
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
                                size="small"
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
                                size="small"
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
            <el-button @click="cleanSerchOrder()">清空条件</el-button>
            <el-button type="primary" @click="serchOrderClick()">按照条件查询</el-button>
        </div>
    </el-dialog>
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
            CommonInternalUser
        },
        created: function () {
            var vm = this;
            vm.invoiceFileTitleBtn = vm.$flagMenuStore.judgeMenu("invoice-file-title-btn");
            vm.paybackFileTitleBtn = vm.$flagMenuStore.judgeMenu("payback-file-title-btn");
            vm.OrderStatisticsBtn = vm.$flagMenuStore.judgeMenu("order-statistics-btn");
            vm.OrderDivideIntoBtn = vm.$flagMenuStore.judgeMenu("order-divide-into-btn");
            vm.OrderTransDeptBtn = vm.$flagMenuStore.judgeMenu("order-trans-dept-btn");
            this.loadOrder(null);
            this.loadDeptList();
        },
        mounted(){

        },
        updated(){

        },
        data(){
            return {
                //人员查询组件dialog
                empCommonDialogFormVisible: false,
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
                pageSizeData: 10,
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
                    paymentDay: ""
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
            }
        },
        methods: {
            orderExportClick(num){
                this.orderExportType = num;
                this.orderExportDialogFormVisible = true;

            },
            orderExport(){
                var orderStartTime = this.orderExportEntity.orderStartTime;
                var orderEndTime = this.orderExportEntity.orderEndTime;
                var orderType = this.orderExportType;
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
                this.empCommonDialogFormVisible = data.empCommonDialogFormVisible;
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
            hasCommissionConfirm(){
                var vm = this;
                var orderCode = vm.orderCode;
                if (orderCode != null && orderCode != "") {
                    this.$confirm('佣金确认书确认是否回收?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        var obj = {};
                        var sendObj = {};
                        sendObj.entity = {
                            orderCode: orderCode
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
                        }).catch(function (error) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                    });

                } else {
                    vm.$notify({
                        message: "请选择具体订单",
                        title: '操作提示',
                    });
                }

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
                sendObj.pageSize = 10;
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
                })

            },
            //打开订单详情组件
            openOrderFollowupDetail(orderCode){
                this.OrderFollowupCreateDialogFormVisible = true;
                this.orderCode = orderCode;
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

    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 150px 0px;
        max-height: 83vh;
        overflow-y: scroll;
        /*background-color: #2aabd2;*/
    }

    /*导航栏DIV*/
    .navigation_div {
        /*z-index: 200;*/
        margin: -48px 3px 33px 3px;
        width: 100%;
        height: 50px;
    }

    /*    .titles {
            min-height: 50px;
            !*background-color: white;*!
            !*border-bottom: 1px solid rgb(242, 242, 242);*!

        }*/
    .navigation_button_back {
        margin-top: 10px;
        margin-left: 20px;
    }

    .navigation-title {
        margin-left: 20px;
        font-size: 13px;
    }

    .input-with-select {
        width: 270px;
        width: 300px;
        margin-top: 10px;
    }

    .contract_start {
        width: 100%;
        height: 40px;
    }

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
</style>
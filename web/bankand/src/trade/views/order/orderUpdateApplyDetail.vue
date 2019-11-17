<template>
    <el-dialog  :close-on-click-modal="false" :style="{height: fullHeight +40 +'px'}"
                :visible.sync="visible" width="80%" top="2vh"
                :before-close="orderDetailClose"
                append-to-body>
        <el-tabs :style="{height: fullHeight -80 +'px'}"  v-model="dislogType" type="card" @tab-click="typeCheck()">
            <el-tab-pane  style="overflow-y: auto;overflow-x: hidden" label="订单修改申请详情" name="1">
                <el-form  :model="orderUpdateApplyEntity" ref="orderUpdateApplyEntity">
                    <!--客户详情面板-->
                    <cus-details :cusCodeDetails="cusCodeDetails" ref="cusDetailsx"/>

                    <hos-details :houseCodeDetails="houseCodeDetails" ref="hosByDetails"
                                 v-if="houseDetailOpenOrClose"></hos-details>
                    <!-- 开单人查询弹框 -->
                    <CommonInternalUser ref="refCommonInternal"
                                        v-on:changed2="closeEmpCommon($event)"/>

                    <!-- 分成人人员查询弹框 aaaa-->

                    <CommonInternalUser
                            ref="refPeCommon"
                            v-on:changed2="closePeCommon($event)"></CommonInternalUser>

                    <div :style="{height: fullHeight -180 +'px'}" style="overflow-y: auto">
                        <div>
                            <el-input
                                    type="textarea"
                                    :autosize="{ minRows: 2, maxRows: 4}"
                                    readonly
                                    v-model="orderUpdateApplyEntity.updateContent">
                            </el-input>
                        </div>
                        <div>
                            <p class="title_format">基本信息<span class="title_red">（必填)</span></p>
                            <el-row :gutter="5">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="开 单 人" prop="empName"
                                                  label-width="80px">
                                        <el-input v-model="orderUpdateApplyEntity.empName"
                                                  placeholder="请选择开单人"
                                                  size="mini" :disabled="true">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="openEmpCommon()">点击选择
                                            </el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="开单时间" prop="orderTime"
                                                  label-width="80px">
                                        <el-date-picker
                                                size="mini"
                                                v-model="orderUpdateApplyEntity.orderTime"
                                                type="date"
                                                placeholder="选择日期"
                                                :picker-options="pickerOptions0"
                                                class="order_time">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8"></el-col>
                            </el-row>
                        </div>

                        <div style="border-top: 1px dashed gainsboro;margin-top: 10px">
                            <p class="title_format">租赁双方合同概要<span class="title_red">（必填）</span>
                            </p>
                            <el-row :gutter="10">
                                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                    <el-form-item label="出租房源" prop="houseCode"
                                                  label-width="80px">
                                        <el-input placeholder="请选择房源" size="mini"
                                                  v-model="orderUpdateApplyEntity.houseCode"
                                                  :disabled="true">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="houseDetail">点击查看
                                            </el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                    <el-form-item label="承租客户" prop="cusCode"
                                                  label-width="80px">
                                        <el-input placeholder="请选择客户" size="mini"
                                                  v-model="orderUpdateApplyEntity.cusCode"
                                                  :disabled="true">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="cusDetailsClick(orderUpdateApplyEntity.cusCode)">
                                                查看详情
                                            </el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                    <el-form-item style="margin-left: 10px" label="租赁周期"
                                                  label-width="70px">
                                        <el-date-picker
                                                size="mini"
                                                v-model="orderUpdateApplyEntity.contractStart"
                                                type="date"
                                                placeholder="选择日期"
                                                style="float: left;width: 47%;">
                                        </el-date-picker>

                                        <el-date-picker
                                                size="mini"
                                                v-model="orderUpdateApplyEntity.contractEnd"
                                                type="date"
                                                placeholder="选择日期"
                                                style="float: left;width: 48%;margin-left: 10px">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                                    <p style="float: left;line-height: 42px;font-weight: bold;">
                                        付费规则</p>
                                    <el-form-item label="付:" prop="monthCount"
                                                  label-width="50px"
                                                  class="month_count">
                                        <el-input v-model="orderUpdateApplyEntity.monthCount" size="mini">
                                            <el-button slot="append">月</el-button>
                                        </el-input>
                                    </el-form-item>
                                    <el-form-item label="押:" prop="deposit" label-width="50px"
                                                  class="deposit">
                                        <el-input v-model="orderUpdateApplyEntity.deposit" size="mini">
                                            <el-button slot="append">月/元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10" style="margin-top: 10px">
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="租赁面积" prop="acreage"
                                                  label-width="80px">
                                        <el-input v-model="orderUpdateApplyEntity.acreage" size="mini">
                                            <el-button slot="append">㎡</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">

                                </el-col>
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="租赁价格" prop="price" label-width="80px">
                                        <el-input placeholder="请输入内容"
                                                  v-model="orderUpdateApplyEntity.price"
                                                  class="input-with-select" size="mini">
                                            <el-select v-model="orderUpdateApplyEntity.unit" slot="prepend"
                                                       placeholder="请选择"
                                                       class="order_entity_unit">
                                                <el-option label="元/㎡/天"
                                                           value="元/㎡/天"></el-option>
                                                <el-option label="元/㎡/月"
                                                           value="元/㎡/月"></el-option>
                                                <el-option label="元/㎡/年"
                                                           value="元/㎡/年"></el-option>
                                            </el-select>
                                        </el-input>
                                    </el-form-item>
                                </el-col>

                            </el-row>

                            <el-row>
                                <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18">
                                    <el-form-item label="成交状态" prop="orderStatus"
                                                  label-width="80px">
                                        <el-radio v-model="orderUpdateApplyEntity.orderStatus" label="1">
                                            已签租赁合同
                                        </el-radio>
                                        <el-radio v-model="orderUpdateApplyEntity.orderStatus" label="2">
                                            未签租赁合同
                                        </el-radio>
                                        <el-radio v-model="orderUpdateApplyEntity.orderStatus" label="3">
                                            已毁单
                                        </el-radio>
                                        <el-radio v-model="orderUpdateApplyEntity.orderStatus" label="5">
                                            已回款
                                        </el-radio>
                                        <el-radio v-model="orderUpdateApplyEntity.orderStatus" label="8">
                                            回款中
                                        </el-radio>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </div>
                        <div style="border-top: 1px dashed gainsboro;margin-top: 10px">
                            <p class="title_format">业主方佣金信息<span
                                    class="title_red">（必填，没有则填0）</span></p>

                            <el-row :gutter="10">
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="业主佣金" prop="receivableHos"
                                                  label-width="80px">
                                        <el-input placeholder="业主方合同佣金" size="mini"
                                                  v-model="orderUpdateApplyEntity.receivableHos"
                                                  @change="calculateActualCommision()">
                                            <el-button slot="append">元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="业主返佣" prop="ownerRebate"
                                                  label-width="80px">
                                        <el-input placeholder="付给业主的返佣金额" size="mini"
                                                  v-model="orderUpdateApplyEntity.ownerRebate"
                                                  @change="calculateActualCommision()">
                                            <el-button slot="append">元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="业主兼职" prop="ownerParttimeMoney"
                                                  label-width="80px">
                                        <el-input placeholder="提供房源或业主信息的兼职分成" size="mini"
                                                  v-model="orderUpdateApplyEntity.ownerParttimeMoney"
                                                  @change="calculateActualCommision()">
                                            <el-button slot="append">元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </div>
                        <div style="margin-top: 20px">
                            <p class="title_format">客户方佣金信息<span
                                    class="title_red">（必填，没有则填0）</span></p>
                            <el-row :gutter="10">
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="客户佣金" prop="receivableCus"
                                                  label-width="80px">
                                        <el-input placeholder="客户方合同佣金" size="mini"
                                                  v-model="orderUpdateApplyEntity.receivableCus"
                                                  @change="calculateActualCommision()">
                                            <el-button slot="append">元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="客户返佣" prop="cusRebate"
                                                  label-width="80px">
                                        <el-input placeholder="付给客户的返佣金额" size="mini"
                                                  v-model="orderUpdateApplyEntity.cusRebate"
                                                  @change="calculateActualCommision()">
                                            <el-button slot="append">元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                    <el-form-item label="客户兼职" prop="cusParttimeMoney"
                                                  label-width="80px">
                                        <el-input placeholder="提供客户信息的兼职分成" size="mini"
                                                  v-model="orderUpdateApplyEntity.cusParttimeMoney"
                                                  @change="calculateActualCommision()">
                                            <el-button slot="append">元</el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </div>

                        <div style="margin-top: 20px">
                            <el-row :gutter="10">
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">

                                    <div v-if="fileType == 'image'" class="img-div1">
                                        <template>
                                            <i @click="removeCusImg()"
                                               class="el-icon-circle-close"
                                               style="float: right;"
                                               v-if="orderUpdateApplyEntity.cusImageName"></i>
                                            <img height="100%" width="100%"
                                                 :src=" hkpBaseUrl + 'file/read/' + orderUpdateApplyEntity.cusImageName "
                                                 v-if="orderUpdateApplyEntity.cusImageName">
                                            <file @selectFile="selectCusImg($event)"
                                                  class="file_button"
                                                  v-if="!orderUpdateApplyEntity.cusImageName"></file>
                                        </template>
                                    </div>
                                    <div class="cus_commission_num">
                                        <el-form-item label="客户佣金合同编号" prop="cusCommissionNum"
                                                      label-width="134px">
                                            <el-input placeholder="合同编号的数字部门" size="mini"
                                                      v-model="orderUpdateApplyEntity.cusCommissionNum">
                                            </el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                    <div v-if="fileType == 'image'" class="img-div1">
                                        <template>
                                            <i @click="removeOwnerImg()"
                                               class="el-icon-circle-close"
                                               style="float: right;"
                                               v-if="orderUpdateApplyEntity.ownerImageName"></i>
                                            <img height="100%" width="100%"
                                                 :src=" hkpBaseUrl + 'file/read/' + orderUpdateApplyEntity.ownerImageName"
                                                 v-if="orderUpdateApplyEntity.ownerImageName">
                                            <file @selectFile="selectOwnerImg($event)"
                                                  class="file_button"
                                                  v-if="!orderUpdateApplyEntity.ownerImageName"></file>
                                        </template>
                                    </div>
                                    <div class="cus_commission_num">
                                        <el-form-item label="业主佣金合同编号" prop="ownerCommissionNum"
                                                      label-width="134px"
                                                      class="owner_commission_num">
                                            <el-input placeholder="合同编号的数字部门" size="mini"
                                                      v-model="orderUpdateApplyEntity.ownerCommissionNum">
                                            </el-input>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </el-row>
                        </div>


                        <div style="border-top: 1px dashed gainsboro;margin-top: 10px">
                            <el-row>
                                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                    <p style="font-size: 20px;font-weight: bold;margin-left: 10px">
                                        实际业绩：<span
                                            style="color: red">{{orderUpdateApplyEntity.actualCommision}}元</span>
                                    </p>
                                </el-col>
                            </el-row>

                            <el-row :gutter="10" style="font-weight: bold">
                                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                                    <div class="grid-content">分成人</div>
                                </el-col>
                                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                    <div class="grid-content">所属部门</div>
                                </el-col>
                                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                    <div class="grid-content">比例%</div>
                                </el-col>
                                <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                    <div class="grid-content">分成金额（元）</div>
                                </el-col>
                                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                    <div class="grid-content">分成类型</div>
                                </el-col>
                            </el-row>
                            <el-row :gutter="10">
                                <el-form-item
                                        v-for="(areaItem, j) in orderUpdateApplyEntity.cusServicePercentageApplyEntityList"
                                        :key=areaItem.id>

                                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                                        <div class="grid-content">
                                            <el-input size="mini" v-model="areaItem.peEmpName"
                                                      placeholder="请选择开单人" :disabled="true">
                                                <el-button slot="append" icon="el-icon-search"
                                                           @click="test(j)">点击选择
                                                </el-button>
                                            </el-input>
                                        </div>
                                    </el-col>
                                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                        <div class="grid-content bg-purple-light">
                                            <el-input size="mini" v-model="areaItem.peDeptName"
                                                      :disabled="true"></el-input>
                                        </div>
                                    </el-col>
                                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                        <div class="grid-content bg-purple">
                                            <el-input size="mini" v-model="areaItem.percentage"
                                                      :disabled="true"></el-input>
                                        </div>
                                    </el-col>
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                        <div class="grid-content bg-purple-light">
                                            <el-input size="mini" v-model="areaItem.profit"
                                                      :disabled="true"></el-input>
                                        </div>
                                    </el-col>
                                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                        <div class="grid-content bg-purple-light">
                                            <el-select size="mini"
                                                       v-model="areaItem.percentageType"
                                                       filterable
                                                       placeholder="请选择" :disabled="true">
                                                <el-option
                                                        v-for="item in percentageType"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </div>
                                    </el-col>


                                </el-form-item>
                            </el-row>
                        </div>
                        <div style="margin-top: 10px">
                            <el-row :gutter="10">
                                <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                    <el-button type="primary" plain @click="addPeEmpCodo"
                                               size="mini">
                                        添加分成人
                                    </el-button>
                                    <el-button type="primary" plain @click="clearPeEmpCodo"
                                               size="mini">
                                        清空分成人
                                    </el-button>
                                </el-col>
                            </el-row>
                        </div>
                        <el-row :gutter="10" style="font-weight: bold">
                            <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                                <div class="grid-content">分成人</div>
                            </el-col>
                            <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                <div class="grid-content">所属部门</div>
                            </el-col>
                            <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                <div class="grid-content">比例%</div>
                            </el-col>
                            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                <div class="grid-content">分成金额（元）</div>
                            </el-col>
                            <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                <div class="grid-content">分成类型</div>
                            </el-col>
                        </el-row>

                        <el-form-item
                                v-for="(areaItem, i) in orderUpdateApplyEntity.orderPercentageApplyEntityList"
                                :key=areaItem.id>
                            <el-row :gutter="10">
                                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                                    <div class="grid-content">

                                        <el-input size="mini" v-model="areaItem.peEmpName"
                                                  placeholder="请选择分成人" :disabled="true">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="test(i)">点击选择
                                            </el-button>
                                        </el-input>
                                    </div>
                                </el-col>
                                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                    <div class="grid-content">
                                        <el-input size="mini" v-model="areaItem.peDeptName"
                                                  :disabled="true"></el-input>
                                    </div>
                                </el-col>
                                <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                    <div class="grid-content">
                                        <el-input size="mini" v-model="areaItem.percentage"
                                                  @change="calculate(i)"></el-input>
                                    </div>
                                </el-col>
                                <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                    <div class="grid-content">
                                        <el-input size="mini" v-model="areaItem.profit"
                                                  :disabled="true"></el-input>
                                    </div>
                                </el-col>
                                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                                    <div class="grid-content">
                                        <el-select size="mini" v-model="areaItem.percentageType"
                                                   filterable
                                                   placeholder="请选择">
                                            <el-option
                                                    v-for="item in percentageType"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </div>
                                </el-col>

                            </el-row>
                        </el-form-item>
                    </div>
                </el-form>

            </el-tab-pane>
        </el-tabs>
    </el-dialog>
</template>

<script>
    import cusDetails from "../../../cus/views/cusDetails.vue"
    import hosDetails from "../../../hos/views/hosDetails.vue";
    import CommonInternalUser from "../../../hrm/views/emp/CommonInternalUser.vue";
    import CommonHouseList from "../../../hos/common/CommonHouseList.vue";
    import CommonCusList from "../../../cus/common/CommonCusList.vue";
    import File from "../../../common/components/file.vue";
    export default {
        components:{
            cusDetails,
            hosDetails,
            CommonInternalUser,
            CommonHouseList,
            CommonCusList,
            File,
        },
        data() {
            return {
                visible: false,
                orderCode: '',
                dislogType: '1',
                fullHeight: document.documentElement.clientHeight,

                pickerOptions0: {
                    disabledDate(time) {
                        let curDate = (new Date()).getTime();
                        let three = ((new Date()).getDate()) * 24 * 3600 * 1000;
                        let threeMonths = curDate - three;
                        return time.getTime() > Date.now() || time.getTime() < threeMonths;

                    }
                },
                //人员查询组件dialog
                empCommonDialogFormVisible: false,
                peCommonDialogFormVisible: false,
                cusDetailsOpenOrClose: false,//客户详情打开或者关闭
                cusCodeDetails: '',//传值到面板
                //房源详情面板打开或者关闭
                houseDetailOpenOrClose: false,
                houseCodeDetails: '',
                //房源查询组件dialog
                hosCommonDialogFormVisible: false,
                //客户查询组件dialog
                cusCommonDialogFormVisible: false,

                //订单实体
                orderUpdateApplyEntity: {
                    //订单编号
                    orderCode: "",
                    //订单日期
                    orderTime: "",
                    //订单状态
                    orderStatus: "",
                    //订单状态名字
                    orderStatusName: "",
                    //成交人编号
                    empCode: "",
                    //成交人名字
                    empName: "",
                    //房源编码
                    houseCode: "",
                    //业主手机号
                    houseownerPhone: "",
                    //客户编码
                    cusCode: "",
                    //客户手机号
                    cusPhone: "",
                    //出租面积
                    acreage: "",
                    //出租价格
                    price: "",
                    //价格单位
                    unit: "",
                    //合同开始时间和合同结束时间
                    contractStartAndEnd: [],
                    contractStart: "",
                    contractEnd: "",
                    //交月份数
                    monthCount: "",
                    //押金
                    deposit: "",
                    //业主佣金
                    receivableHos: "",
                    //客户佣金
                    receivableCus: "",
                    //佣金总计
                    commission: "",
                    //客户返佣
                    cusRebate: "",
                    //业主返佣
                    ownerRebate: "",
                    //客户兼职分钱
                    cusParttimeMoney: "",
                    //业主兼职分钱
                    ownerParttimeMoney: "",
                    //实际可分佣金总额
                    actualCommision: "",
                    //实际分成佣金总额
                    actualBranchCommision: "",
                    //实际客服分成佣金总额
                    actualCusServCommision: "",
                    //每月租金
                    monthRent: 0,

                    //客户佣金确认书
                    cusImageName: "",
                    //客户佣金确认书编号
                    cusCommissionNum: "",
                    //业主佣金确认书
                    ownerImageName: "",
                    //业主佣金确认书编号
                    ownerCommissionNum: "",

                    //分成人分成明细
                    orderPercentageApplyEntityList: [{
                        //分成编号
                        percentageCode: "",
                        //订单编号
                        orderCode: "",
                        //订单日期
                        orderTime: "",
                        //分成人编码
                        peEmpCode: "",
                        //分成人名字
                        peEmpName: "",
                        //分成人部门编码
                        peDeptCode: "",
                        //分成人部门名字
                        peDeptName: "",
                        //分成名字
                        percentageName: "",
                        //分成类型
                        percentageType: "",
                        //金额分成类型  1,客服固定分成  2,分成人分成
                        moneyPercentType: "",
                        //金额分成类型(中文名)
                        moneyPercentTypeName: "",
                        //分成百分比
                        percentage: "",
                        //分成业绩
                        profit: "",
                    }],
                    //客服分成明细
                    cusServicePercentageApplyEntityList: [{
                        //分成编号
                        percentageCode: "",
                        //订单编号
                        orderCode: "",
                        //订单日期
                        orderTime: "",
                        //分成人编码
                        peEmpCode: "",
                        //分成人名字
                        peEmpName: "",
                        //分成人部门编码
                        peDeptCode: "",
                        //分成人部门名字
                        peDeptName: "",
                        //分成名字
                        percentageName: "",
                        //分成类型
                        percentageType: "",
                        //金额分成类型  1,客服固定分成  2,分成人分成
                        moneyPercentType: "",
                        //金额分成类型(中文名)
                        moneyPercentTypeName: "",
                        //分成百分比
                        percentage: "",
                        //分成业绩
                        profit: "",
                    }],

                    applyReason: '',//订单修改申请理由
                    updateContent: '',//修改内容拼接
                },
                fileType: "image",
                empEntity: [],
                percentageType: [{
                    value: '1',
                    label: '客户分成'
                }, {
                    value: '2',
                    label: '房源分成'
                }, {
                    value: '3',
                    label: '协助带看分成'
                }, {
                    value: '4',
                    label: '协助成交分成'
                }, {
                    value: '5',
                    label: '成交分成'
                }, {
                    value: '6',
                    label: '客服分成'
                }, {
                    value: '7',
                    label: '房源委托分成'
                }, {
                    value: '8',
                    label: '个人获客补贴'
                }, {
                    value: '9',
                    label: '客户推送'
                }, {
                    value: '10',
                    label: '公司获客分成'
                }, {
                    value: '11',
                    label: '大区活动经费'
                }],
            }
        },
        created() {

        },
        methods: {
            //面板类型判断
            typeCheck(){
                var vm = this;
                if (vm.dislogType != null) {
                    switch (vm.dislogType) {
                        case "1":
                            vm.loadOrderEntity();//订单详情
                            break;
                    }
                }
            },
            loadOrderEntity(){
                var vm = this;
                console.log(vm.orderCode)
                var sendObj = {};
                sendObj.entity = {};
                sendObj.entity.orderCode = vm.orderCode;
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "order/update/apply/detail",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.orderUpdateApplyEntity = response.data.result;
                }).catch(function (error) {
                });
            },
            //清空分成人方法
            clearPeEmpCodo(){
                this.orderUpdateApplyEntity.orderPercentageEntityList = [{
                    //分成编号
                    percentageCode: '',
                    //订单编号
                    orderCode: '',
                    //订单日期
                    orderTime: '',
                    //分成人编码
                    peEmpCode: '',
                    //分成人名字
                    peEmpName: '',
                    //分成人部门编码
                    peDeptCode: '',
                    //分成人部门名字
                    peDeptName: '',
                    //分成名字
                    percentageName: '',
                    //分成类型
                    percentageType: '',
                    //金额分成类型  1,客服固定分成  2,分成人分成
                    moneyPercentType: '',
                    //金额分成类型(中文名)
                    moneyPercentTypeName: '',
                    //分成百分比
                    percentage: '',
                    //分成业绩
                    profit: '',
                }]
            },
            //添加分成人方法
            addPeEmpCodo(){
                this.orderUpdateApplyEntity.orderPercentageEntityList.push({
                    //分成编号
                    percentageCode: '',
                    //订单编号
                    orderCode: '',
                    //订单日期
                    orderTime: '',
                    //分成人编码
                    peEmpCode: '',
                    //分成人名字
                    peEmpName: '',
                    //分成人部门编码
                    peDeptCode: '',
                    //分成人部门名字
                    peDeptName: '',
                    //分成名字
                    percentageName: '',
                    //分成类型
                    percentageType: '',
                    //金额分成类型  1,客服固定分成  2,分成人分成
                    moneyPercentType: '',
                    //金额分成类型(中文名)
                    moneyPercentTypeName: '',
                    //分成百分比
                    percentage: '',
                    //分成业绩
                    profit: '',
                });
            },
            openEmpCommon(){
                this.$nextTick(() => {
                    this.$refs.refCommonInternal.empCommonDialogFormVisible = true;
                    this.$refs.refCommonInternal.basicReLoadUser();
                })
            },

            //关闭人员公共面板
            closeEmpCommon(data){
                this.$nextTick(() => {
                    this.$refs.refCommonInternal.empCommonDialogFormVisible = false;
                })
                this.orderUpdateApplyEntity.empName = data.userName;
                this.orderUpdateApplyEntity.empCode = data.userCode;
                this.selectDeptCusServe(data.userCode);
            },
            //关闭人员公共面板
            closePeCommon(data){
                var index = Number(this.index);
                this.$nextTick(()=>{
                    this.$refs.refPeCommon.empCommonDialogFormVisible=false;
                })
                this.orderUpdateApplyEntity.orderPercentageEntityList[index].peEmpCode = data.userCode;
                this.orderUpdateApplyEntity.orderPercentageEntityList[index].peEmpName = data.userName;
                this.orderUpdateApplyEntity.orderPercentageEntityList[index].peDeptCode = data.ownerDeptCode;
                this.orderUpdateApplyEntity.orderPercentageEntityList[index].peDeptName = data.ownerDeptName;
            },
            //关闭房源详情
            closeDialog(){
                this.$nextTick(() => {
                    this.$refs.hosByDetails.closeDialog();
                })
            },
            //房源详情
            houseDetail(){
                this.houseDetailOpenOrClose = true;
                this.houseCodeDetails = this.orderUpdateApplyEntity.houseCode;
                this.$nextTick(() => {
                    this.$refs.visible = true;
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },
            //关闭房源公共查询面板
            closeHosCommon(data){
                this.hosCommonDialogFormVisible = false;
                this.orderUpdateApplyEntity.houseCode = data.houseCode;
            },
            //关闭客户公共查询面板
            closeCusCommon(data){
                this.cusCommonDialogFormVisible = false;
                this.orderUpdateApplyEntity.cusCode = data.cusCode;
            },
            electCusImg(urls){
                if (urls.length == 1) {
                    //this.orderUpdateApplyEntity.cusImage = urls.toString();
                    this.orderUpdateApplyEntity.cusImageName = urls.toString();
                } else {
                    this.$message({
                        showClose: true,
                        message: '仅支持上传一张照片',
                        type: 'warning'
                    });
                    return;
                }
            },
            selectOwnerImg(urls){
                if (urls.length == 1) {
                    this.orderUpdateApplyEntity.ownerImageName = urls.toString();
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeCusImg(){
                this.orderUpdateApplyEntity.cusImageName = '';
            },
            //新增时移除图片
            removeOwnerImg(){
                this.orderUpdateApplyEntity.ownerImageName = '';
            },
            //打开客户详情面板
            cusDetailsClick(obj){
                this.cusDetailsOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.cusDetailsx.visible = true
                    this.$refs.cusDetailsx.cusCodeDetails = obj;//传值给组件
                    this.$refs.cusDetailsx.getCusDetails();
                })
            },
            //关闭客户详情面板
            closeDialog(){
                var vm = this;
                vm.$nextTick(() => {
                    vm.$refs.cusDetailsx.closeDialog();
                })

            },
            orderDetailClose(done){
                var vm = this;
                vm.dislogType='1';
                done()
            },
        }
    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 570px;
        overflow-y: auto;
    }

    .order_basics_message_left {
        float: left;
        width: 100%;
    }

    .order_time {
        width: 100%;
    }

    .contract_start {
        width: 100%;
    }

    .title_format {
        text-align: center;
        font-weight: bold;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    #payment_rules {
        float: left;
        font-size: 14px;
        font-weight: 900;
    }

    .month_count {
        float: left;
        width: 43%;
    }

    .deposit {
        float: left;
        width: 43%;
    }

    .order_basics_message_right {
        float: left;
        width: 50%;
    }

    .cus_commission_num {
        float: left;
        margin-top: 80px;
    }

    .grid-content {
        height: 30px;
        line-height: 30px;
        text-align: center;

    }

    .title {
        font-size: 13px;
        margin-left: 13px;
        margin-top: 10px;
        margin-bottom: 10px;
        font-weight: 900;
    }

    .title_red {
        color: red;
    }

    .order_entity_unit {
        width: 100px;
    }

    .emp-info-root-div {
        border: 1px solid navy;
        background-color: white;
        width: 100%;
    }

    .img-div1 {
        float: left;
        border: 1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }

    .el-form-item {
        margin-bottom: 2px;
    }

    .file_button {
        margin-top: 80px;
        margin-left: 50px;
    }

</style>
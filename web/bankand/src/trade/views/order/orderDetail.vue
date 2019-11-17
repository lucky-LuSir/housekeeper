<template>
    <el-dialog  :close-on-click-modal="false" :style="{height: fullHeight +40 +'px'}" :visible.sync="OrderDetailDialogFormVisible" width="80%" top="2vh"
               @close="OrderDetailClose()"
               append-to-body>
            <el-tabs :style="{height: fullHeight -80 +'px'}"  v-model="dislogType" type="card" @tab-click="typeCheck()">
                <el-tab-pane  style="overflow-y: auto;overflow-x: hidden" label="订单详情" name="1">
                    <el-form :rules="rules" :model="orderEntity" ref="orderEntity">
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

                        <div style="text-align: right;margin-top: 10px;margin-right: 150px" >
                            <el-button size="small" v-show="showEditOrderBtn" type="primary" @click="onEditOrder()">修改申请
                            </el-button>
                            <el-button size="small" type="primary" v-show="showDirectEditOrderBtn"  @click="directEditOrder()">直接修改
                            </el-button>
                        </div>
                        <div :style="{height: fullHeight -180 +'px'}" style="overflow-y: auto">

                            <div>
                                <p class="title_format">基本信息<span class="title_red">（必填)</span></p>
                                <el-row :gutter="5">
                                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                        <el-form-item label="开 单 人" prop="empName"
                                                      label-width="80px">
                                            <el-input v-model="orderEntity.empName"
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
                                                    v-model="orderEntity.orderTime"
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
                                                      v-model="orderEntity.houseCode"
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
                                                      v-model="orderEntity.cusCode"
                                                      :disabled="true">
                                                <el-button slot="append" icon="el-icon-search"
                                                           @click="cusDetailsClick(orderEntity.cusCode)">
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
                                                    v-model="orderEntity.contractStart"
                                                    type="date"
                                                    placeholder="选择日期"
                                                    style="float: left;width: 47%;">
                                            </el-date-picker>

                                            <el-date-picker
                                                    size="mini"
                                                    v-model="orderEntity.contractEnd"
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
                                            <el-input v-model="orderEntity.monthCount" size="mini">
                                                <el-button slot="append">月</el-button>
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="押:" prop="deposit" label-width="50px"
                                                      class="deposit">
                                            <el-input v-model="orderEntity.deposit" size="mini">
                                                <el-button slot="append">月/元</el-button>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row :gutter="10" style="margin-top: 10px">
                                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                        <el-form-item label="租赁面积" prop="acreage"
                                                      label-width="80px">
                                            <el-input v-model="orderEntity.acreage" size="mini">
                                                <el-button slot="append">㎡</el-button>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">

                                    </el-col>
                                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                        <el-form-item label="租赁价格" prop="price" label-width="80px">
                                            <el-input placeholder="请输入内容"
                                                      v-model="orderEntity.price"
                                                      class="input-with-select" size="mini">
                                                <el-select v-model="orderEntity.unit" slot="prepend"
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
                                        <el-form-item label="成交状态"  prop="orderStatus"
                                                      label-width="80px">
                                            <el-radio v-model="orderEntity.orderStatus" label="1">
                                                未签租赁合同
                                            </el-radio>
                                            <el-radio v-model="orderEntity.orderStatus" label="2">
                                                已签租赁合同
                                            </el-radio>
                                            <el-radio v-model="orderEntity.orderStatus" label="3">
                                                已毁单
                                            </el-radio>
                                            <el-radio v-model="orderEntity.orderStatus" label="5">
                                                已回款
                                            </el-radio>
                                            <el-radio v-model="orderEntity.orderStatus" label="8">
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
                                                      v-model="orderEntity.receivableHos"
                                                      @change="calculateActualCommision()">
                                                <el-button slot="append">元</el-button>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                        <el-form-item label="业主返佣" prop="ownerRebate"
                                                      label-width="80px">
                                            <el-input placeholder="付给业主的返佣金额" size="mini"
                                                      v-model="orderEntity.ownerRebate"
                                                      @change="calculateActualCommision()">
                                                <el-button slot="append">元</el-button>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                        <el-form-item label="业主兼职" prop="ownerParttimeMoney"
                                                      label-width="80px">
                                            <el-input placeholder="提供房源或业主信息的兼职分成" size="mini"
                                                      v-model="orderEntity.ownerParttimeMoney"
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
                                                      v-model="orderEntity.receivableCus"
                                                      @change="calculateActualCommision()">
                                                <el-button slot="append">元</el-button>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                        <el-form-item label="客户返佣" prop="cusRebate"
                                                      label-width="80px">
                                            <el-input placeholder="付给客户的返佣金额" size="mini"
                                                      v-model="orderEntity.cusRebate"
                                                      @change="calculateActualCommision()">
                                                <el-button slot="append">元</el-button>
                                            </el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7">
                                        <el-form-item label="客户兼职" prop="cusParttimeMoney"
                                                      label-width="80px">
                                            <el-input placeholder="提供客户信息的兼职分成" size="mini"
                                                      v-model="orderEntity.cusParttimeMoney"
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
                                                   v-if="orderEntity.cusImageName"></i>
                                                <img height="100%" width="100%"
                                                     :src=" hkpBaseUrl + 'file/read/' + orderEntity.cusImageName "
                                                     v-if="orderEntity.cusImageName">
                                                <file @selectFile="selectCusImg($event)"
                                                      class="file_button"
                                                      v-if="!orderEntity.cusImageName"></file>
                                            </template>
                                        </div>
                                        <div class="cus_commission_num">
                                            <el-form-item label="客户佣金合同编号" prop="cusCommissionNum"
                                                          label-width="134px">
                                                <el-input placeholder="合同编号的数字部门" size="mini"
                                                          v-model="orderEntity.cusCommissionNum">
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
                                                   v-if="orderEntity.ownerImageName"></i>
                                                <img height="100%" width="100%"
                                                     :src=" hkpBaseUrl + 'file/read/' + orderEntity.ownerImageName"
                                                     v-if="orderEntity.ownerImageName">
                                                <file @selectFile="selectOwnerImg($event)"
                                                      class="file_button"
                                                      v-if="!orderEntity.ownerImageName"></file>
                                            </template>
                                        </div>
                                        <div class="cus_commission_num">
                                            <el-form-item label="业主佣金合同编号" prop="ownerCommissionNum"
                                                          label-width="134px"
                                                          class="owner_commission_num">
                                                <el-input placeholder="合同编号的数字部门" size="mini"
                                                          v-model="orderEntity.ownerCommissionNum">
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
                                                style="color: red">{{orderEntity.actualCommision}}元</span>
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
                                            v-for="(areaItem, j) in orderEntity.cusServicePercentageEntityList"
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
                                    v-for="(areaItem, i) in orderEntity.orderPercentageEntityList"
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


                <el-tab-pane :style="{height: fullHeight -140 +'px'}" style="overflow-y: auto;" label="回款信息" name="2">
                    <OrderPayback  ref="orderPayBack"/>
                </el-tab-pane>
                <el-tab-pane :style="{height: fullHeight -140 +'px'}" style="overflow-y: auto;" label="订单开票" name="3">
                    <OrderInvoiceList ref="orderInvoice"/>
                </el-tab-pane>
                <el-tab-pane :style="{height: fullHeight -140 +'px'}" style="overflow-y: auto;" label="订单跟进" name="4">
                    <OrderFollowupList ref="orderFollowup"/>
                </el-tab-pane>

                <el-tab-pane :style="{height: fullHeight -140 +'px'}" style="overflow-y: auto;" label="订单开票申请" name="5">
                    <OrderInvoiceApplyList ref="orderInvoiceApplyList"/>
                </el-tab-pane>
            </el-tabs>


    </el-dialog>
</template>


<script>
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import ElForm from "../../../../node_modules/element-ui/packages/form/src/form";
    import ElFormItem from "../../../../node_modules/element-ui/packages/form/src/form-item";
    import File from "../../../common/components/file.vue";
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import CommonInternalUser from "../../../hrm/views/emp/CommonInternalUser.vue";
    import CommonHouseList from "../../../hos/common/CommonHouseList.vue";
    import CommonCusList from "../../../cus/common/CommonCusList.vue";
    import OrderPayback from "../orderPayback/orderPaybackList.vue";
    import OrderInvoiceList from "../orderInvoice/orderInvoiceList.vue";
    import OrderInvoiceApplyList from "../orderInvoice/orderInvoiceApplyList.vue";
    import OrderFollowupList from "../orderfollowup/orderfollowupList.vue";
    import cusDetails from "../../../cus/views/cusDetails.vue"

    import hosDetails from "../../../hos/views/hosDetails.vue";
    import ElRow from "element-ui/packages/row/src/row";
    export default{
        components: {
            ElRow,
            ElButton,
            ElFormItem,
            ElForm,
            ElInput,
            File,
            CommonInternalUser,
            CommonHouseList,
            CommonCusList,
            OrderPayback,
            OrderInvoiceList,
            OrderFollowupList,
            cusDetails,
            OrderFollowupList,
            hosDetails,
            OrderInvoiceApplyList
        },
        created: function () {
            var vm = {};
            vm = this;
            //菜单和按钮权限控制 start
            vm.showEditOrderBtn = vm.$flagMenuStore.judgeMenu("edit-order-btn");
            vm.showDirectEditOrderBtn = vm.$flagMenuStore.judgeMenu("direct-edit-order-btn");
            //菜单和按钮权限控制 end

            this.loadOrderEntity();
        },
        props: ["orderCode"],
        data(){
            //校验租价格积
            let checkUnitAndPrice = (rule, value, callback) => {
                var unit = this.orderEntity.unit;
                if (unit == "") {
                    callback(new Error('请先选择单位'));
                }
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            let checkIntOrFloat = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(value))) {
                        callback(new Error('只能输入正整数或小数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验付款规则
            let checkMonthCount = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^(([1-9])|(1\d)|(2[0-4]))$/.test(value))) {
                        callback(new Error('只能输入1-24'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {
                fullHeight: document.documentElement.clientHeight,
                //用户修改展示按钮
                showEditOrderBtn: false,
                showDirectEditOrderBtn: false,
                activeName2: 'first',
                dislogType: '1',
                rules: {
                    empName: [
                        {required: true, message: '请选择人员', trigger: 'blur'},
                    ],
                    orderTime: [
                        {required: true, message: '请选择开单时间', trigger: 'blur'},
                    ],
                    houseCode: [
                        {required: true, message: '请选择房源', trigger: 'blur'},
                    ],
                    cusCode: [
                        {required: true, message: '请选择客户', trigger: 'blur'},
                    ],
                    orderStatus: [
                        {required: true, message: '请选择订单状态', trigger: 'blur'},
                    ],
                    receivableCus: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    cusRebate: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    cusParttimeMoney: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    ownerRebate: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    receivableHos: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    ownerParttimeMoney: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: checkUnitAndPrice},
                    ],
                    acreage: [
                        {required: true, message: '请输入租赁面积', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],
                    monthCount: [
                        {required: true, message: '请输入1-24', trigger: 'blur'},
                        {validator: checkMonthCount},
                    ],
                    deposit: [
                        {required: true, message: '请输入金额', trigger: 'blur'},
                        {validator: checkIntOrFloat},
                    ],

                },
                pickerOptions0: {
                    disabledDate(time) {
                        let curDate = (new Date()).getTime();
                        let three = ((new Date()).getDate()) * 24 * 3600 * 1000;
                        let threeMonths = curDate - three;
                        return time.getTime() > Date.now() || time.getTime() < threeMonths;

                    }
                },
                cusDetailsOpenOrClose: false,//客户详情打开或者关闭
                cusCodeDetails: '',//传值到面板
                //人员查询组件dialog
                empCommonDialogFormVisible: false,
                peCommonDialogFormVisible: false,
                //房源查询组件dialog
                hosCommonDialogFormVisible: false,
                //客户查询组件dialog
                cusCommonDialogFormVisible: false,
                //订单实体
                orderEntity: {
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
                    orderPercentageEntityList: [{
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
                    cusServicePercentageEntityList: [{
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
                    updateFieldsList: [],//订单修改了哪些字段
                },
                updateFields: [],//订单修改了哪些字段
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
                input5: "",
                ddd: "",
                value6: "",
                radio: "",
                fileType: "image",
                emp: {
                    empCode: '',
                    empName: '',
                },
                f: "",
                houseCodeDetails: '',
                //房源详情面板打开或者关闭
                houseDetailOpenOrClose: false,
                //订单详情dialog
                OrderDetailDialogFormVisible: false
            }
        },
        watch: {
            'orderEntity.empName' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('empName');
                    }
                }
            },

            'orderEntity.orderTime' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    let a;
                    let b;
                    if(oldVal instanceof Date){
                        a = this.formatTime(oldVal.getTime(), 'Y-M-D');
                    }else {
                        a = this.formatTime(oldVal, 'Y-M-D');
                    }
                    if(newVal instanceof Date) {
                        b = this.formatTime(newVal.getTime(), 'Y-M-D');
                    }
                    if(a != b){
                        this.updateFields.push('orderTime');
                    }
                }
            },

            'orderEntity.contractStart' :function (newVal,oldVal) {
                if(newVal){
                    let a;
                    let b;
                    if(oldVal){
                        if(oldVal instanceof Date){
                            a = this.formatTime(oldVal.getTime(), 'Y-M-D');
                        }else {
                            a = this.formatTime(oldVal, 'Y-M-D');
                        }
                        if(newVal instanceof Date) {
                            b = this.formatTime(newVal.getTime(), 'Y-M-D');
                        }
                        if(a != b){
                            this.updateFields.push('contractStart');
                        }
                    }else if(newVal instanceof Date) {
                        this.updateFields.push('contractStart');
                    }
                }
            },
            'orderEntity.contractEnd' :function (newVal,oldVal) {
                if(newVal){
                    let a;
                    let b;
                    if(oldVal){
                        if(oldVal instanceof Date){
                            a = this.formatTime(oldVal.getTime(), 'Y-M-D');
                        }else {
                            a = this.formatTime(oldVal, 'Y-M-D');
                        }
                        if(newVal instanceof Date) {
                            b = this.formatTime(newVal.getTime(), 'Y-M-D');
                        }
                        if(a != b){
                            this.updateFields.push('contractEnd');
                        }
                    }else if(newVal instanceof Date){
                        this.updateFields.push('contractEnd');
                    }
                }
            },

            'orderEntity.monthCount' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('monthCount');
                    }
                }
            },
            'orderEntity.deposit' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('deposit');
                    }
                }
            },

            'orderEntity.acreage' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('acreage');
                    }
                }
            },
            'orderEntity.price' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('price');
                    }
                }
            },
            'orderEntity.unit' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('unit');
                    }
                }
            },

            'orderEntity.orderStatus' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('orderStatus');
                    }
                }
            },

            'orderEntity.receivableHos' :function (newVal,oldVal) {
                if(newVal &&  (oldVal||oldVal==0)){
                    if(newVal != oldVal){
                        this.updateFields.push('receivableHos');
                    }
                }
            },
            'orderEntity.ownerRebate' :function (newVal,oldVal) {
                if(newVal &&  (oldVal||oldVal==0)){
                    if(newVal != oldVal){
                        this.updateFields.push('ownerRebate');
                    }
                }
            },
            'orderEntity.ownerParttimeMoney' :function (newVal,oldVal) {
                if(newVal && (oldVal||oldVal==0)){
                    if(newVal != oldVal){
                        this.updateFields.push('ownerParttimeMoney');
                    }
                }
            },

            'orderEntity.receivableCus' :function (newVal,oldVal) {
                if(newVal &&  (oldVal||oldVal==0)){
                    if(newVal != oldVal){
                        this.updateFields.push('receivableCus');
                    }
                }
            },
            'orderEntity.cusRebate' :function (newVal,oldVal) {
                if(newVal &&  (oldVal||oldVal==0)){
                    if(newVal != oldVal){
                        this.updateFields.push('cusRebate');
                    }
                }
            },
            'orderEntity.cusParttimeMoney' :function (newVal,oldVal) {
                if(newVal &&  (oldVal||oldVal==0)){
                    if(newVal != oldVal){
                        this.updateFields.push('cusParttimeMoney');
                    }
                }
            },

            'orderEntity.cusCommissionNum' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('cusCommissionNum');
                    }
                }
            },
            'orderEntity.ownerCommissionNum' :function (newVal,oldVal) {
                if(newVal && oldVal){
                    if(newVal != oldVal){
                        this.updateFields.push('ownerCommissionNum');
                    }
                }
            },

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
                        case "2":
                            vm.$refs.orderPayBack.orderCodeThis = vm.orderCode;
                            vm.$refs.orderPayBack.basicReLoadOrderfollow();
                            break;
                        case "3":
                            vm.$refs.orderInvoice.orderCode = vm.orderCode;
                            vm.$refs.orderInvoice.loadOrderInvoice();
                            break;
                        case "4":
                            vm.$refs.orderFollowup.orderCode = vm.orderCode;
                            vm.$refs.orderFollowup.basicReLoadOrderfollow();
                            break;
                        case "5":
                            vm.$refs.orderInvoiceApplyList.orderCode = vm.orderCode;
                            vm.$refs.orderInvoiceApplyList.loadOrderInvoice();
                            break;

                    }
                }
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
                this.houseCodeDetails = this.orderEntity.houseCode;
                this.$nextTick(() => {
                    this.$refs.visible = true;
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },

            //直接修改
            directEditOrder(){
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
//                    sendObj.start = 0;
//                    sendObj.pageSize = 50;
                    sendObj.entity = vm.orderEntity;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "order/update",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        let msg = response.data.message;
                        vm.$notify({
                            message: "信息"+msg,
                            title: '操作提示',
                        });
                        vm.OrderDetailClose()
                    }).catch(function (error) {
                        vm.$notify({
                            message: "页面:获取数据失败!order/update",
                            title: '操作提示',
                        });
                    });
                }
            },

            onEditOrder(){

                this.basicEditOrder();
            },
            basicEditOrder(){
                this.$confirm('是否提交修改申请', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$prompt('请填写申请理由原因', '操作提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                    }).then(({value}) => {
                        var vm = {};
                        vm = this;
                        vm.orderEntity.updateFieldsList = vm.updateFields
                        vm.orderEntity.applyReason = value
                        var flag = true;
                        if (flag) {        //flag留后面表单验证
                            var obj = {};
                            var sendObj = {};
                            // sendObj.start = 0;
                            // sendObj.pageSize = 50;
                            vm.$set(vm.orderEntity, 'orderPercentageApplyEntityList', vm.orderEntity.orderPercentageEntityList);
                            vm.$set(vm.orderEntity, 'cusServicePercentageApplyEntityList', vm.orderEntity.cusServicePercentageEntityList);
                            sendObj.entity = vm.orderEntity;
                            var options = {
                                method: 'POST',
                                headers: {'content-type': 'application/json'},
                                data: sendObj,
                                url: "order/update/apply/create",
                            };
                            this.$ajax(
                                options
                            ).then(function (response) {
                                if (response) {
                                    let msg = response.data.message;
                                    vm.$notify({
                                        message: "信息" + msg,
                                        title: '操作提示',
                                    });
                                }
                                vm.OrderDetailClose()
                            }).catch(function (error) {
                                vm.$notify({
                                    message: "页面:获取数据失败!",
                                    title: '操作提示',
                                });
                            });
                        }
                    })
                })
            },
            loadOrderEntity(){
                var vm = {};
                vm = this;
                vm.showOrderPayback = vm.$flagMenuStore.judgeMenu("show-order-payback");
                var sendObj = {};
                sendObj.entity = {};
                sendObj.entity.orderCode = this.orderCode;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "order/detail",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.relaList = [];
                    vm.cusObj = {};
                    vm.orderEntity = response.data.result;
                }).catch(function (error) {
                });
            },
            //添加分成人方法
            addPeEmpCodo(){
                this.orderEntity.orderPercentageEntityList.push({
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
            //关闭订单详情组件
            OrderDetailClose(){
                var vm = this;
                vm.dislogType='1';
                vm.orderEntity = {
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
                    orderPercentageEntityList: [{
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
                    cusServicePercentageEntityList: [{
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
                    updateFields: [],//订单修改了哪些字段
                }
                vm.applyReason = ''
                vm.updateFields = []
                vm.$refs.orderFollowup.clearFollowupData();
                vm.$refs.orderInvoice.clearInvoiceData();
                vm.$refs.orderInvoiceApplyList.clearInvoiceData();
                vm.$refs.orderPayBack.clearPayBackData();
                this.OrderDetailDialogFormVisible = false;

            },
            //清空分成人方法
            clearPeEmpCodo(){
                this.orderEntity.orderPercentageEntityList = [{
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
                this.orderEntity.empName = data.userName;
                this.orderEntity.empCode = data.userCode;
                this.selectDeptCusServe(data.userCode);
            },
            //关闭人员公共面板
            closePeCommon(data){
                var index = Number(this.index);
                this.$nextTick(()=>{
                    this.$refs.refPeCommon.empCommonDialogFormVisible=false;
                })
                this.orderEntity.orderPercentageEntityList[index].peEmpCode = data.userCode;
                this.orderEntity.orderPercentageEntityList[index].peEmpName = data.userName;
                this.orderEntity.orderPercentageEntityList[index].peDeptCode = data.ownerDeptCode;
                this.orderEntity.orderPercentageEntityList[index].peDeptName = data.ownerDeptName;
            },
            //查询分成人部门是否存在客服
            selectDeptCusServe(empCode){
                var vm = this;
                var sendObj = {
                    entity: {
                        empCode: empCode,
                    }
                };
                var option = {
                    method: 'POST',
                    data: sendObj,
                    url: "order/querycusservice",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                    vm.orderEntity.cusServicePercentageEntityList = response.data.result.cusServicePercentageEntityList;
                }).catch(function (error) {

                });
            },
            //关闭房源公共查询面板
            closeHosCommon(data){
                this.hosCommonDialogFormVisible = false;
                this.orderEntity.houseCode = data.houseCode;
            },
            //关闭客户公共查询面板
            closeCusCommon(data){
                this.cusCommonDialogFormVisible = false;
                this.orderEntity.cusCode = data.cusCode;
            },
            //创建订单
            updateOrder(formName){
                this.$refs[formName].validate((valid) => {
                    var vm = this;


                    if (valid) {
                        if (this.checkout()) {
                            if (vm.orderEntity.contractStartAndEnd.length > 0) {
                                vm.orderEntity.contractStart = vm.orderEntity.contractStartAndEnd[0];
                                vm.orderEntity.contractEnd = vm.orderEntity.contractStartAndEnd[1];
                            }
                            var sendObj = {};
                            sendObj.entity = vm.orderEntity;
                            var option = {
                                method: 'POST',
                                data: sendObj,
                                url: "update/create",
                            };
                            this.$ajax(
                                option
                            ).then(function (response) {
                                vm.$notify({
                                    message: response.data.message,
                                    title: '操作提示',
                                });
                                vm.OrderFollowupCreateDialogFormVisible = false;
                                vm.$emit("changed", vm.OrderFollowupCreateDialogFormVisible);
                            }).catch(function (error) {

                            });
                        }
                    } else {
                        vm.$notify({
                            message: '数据填写不完整',
                            title: '操作提示',
                        });
                    }
                });
            },
            checkout(){

                var vm = this;
                var num = 0;
                if (vm.orderEntity.orderPercentageEntityList.length > 0) {
                    for (var i = 0; i < vm.orderEntity.orderPercentageEntityList.length; i++) {
                        if (vm.orderEntity.orderPercentageEntityList[i].peEmpCode != "" && vm.orderEntity.orderPercentageEntityList[i].percentage != "" && vm.orderEntity.orderPercentageEntityList[i].percentageType != "") {
                            num += Number(vm.orderEntity.orderPercentageEntityList[i].percentage);
                        } else {
                            vm.$notify({
                                message: '请选择分成人，或填写分成比例，或选择分成类型',
                                title: '操作提示',
                            });
                        }
                    }
                }
                if (vm.orderEntity.cusRebate != 0 && (vm.orderEntity.cusImageName == "" || vm.orderEntity.cusCommissionNum == "")) {
                    vm.$notify({
                        message: '请上传客户佣金确认书或填写客户佣金确认书编号',
                        title: '操作提示',
                    });
                } else if (vm.orderEntity.ownerRebate != 0 && (vm.orderEntity.ownerImageName == "" || vm.orderEntity.ownerCommissionNum == "")) {
                    vm.$notify({
                        message: '请上传业主佣金确认书或填写业主佣金确认书编号',
                        title: '操作提示',
                    });
                } else if (vm.orderEntity.orderStatus == 2 && vm.orderEntity.contractStart==null && vm.orderEntity.contractStart==null) {
                    vm.$notify({
                        message: '请选择租赁周期',
                        title: '操作提示',
                    });
                } else if (num != 100) {
                    vm.$notify({
                        message: '分成比例未达到100%，请重新确认',
                        title: '操作提示',
                    });
                } else {
                    return true;
                }
            },

            test(i){
                this.$nextTick(()=>{
                    this.$refs.refPeCommon.empCommonDialogFormVisible=true;
                    this.$refs.refPeCommon.basicReLoadUser();
                })
                this.index = i;
            },
            calculate(i){
                var index = i
                var percentage = Number(this.orderEntity.orderPercentageEntityList[index].percentage);
                var actualBranchCommision = Number(this.orderEntity.actualBranchCommision);
                this.orderEntity.orderPercentageEntityList[index].profit = actualBranchCommision * (percentage / 100);
            },
            calculateActualCommision(){
                if (this.orderEntity.empCode != "") {
                    //业主佣金
                    var receivableHos = Number(this.orderEntity.receivableHos);
                    //客户佣金
                    var receivableCus = Number(this.orderEntity.receivableCus);
                    //客户返佣
                    var cusRebate = Number(this.orderEntity.cusRebate);
                    //业主返佣
                    var ownerRebate = Number(this.orderEntity.ownerRebate);
                    //客户兼职分钱
                    var cusParttimeMoney = Number(this.orderEntity.cusParttimeMoney);
                    //业主兼职分钱
                    var ownerParttimeMoney = Number(this.orderEntity.ownerParttimeMoney);
                    //实际可分佣金总额
                    var actualCommision = this.orderEntity.actualCommision = Number((receivableHos + receivableCus) - (cusRebate + ownerRebate + cusParttimeMoney + ownerParttimeMoney));
                    //业务员分成总额
                    var actualBranchCommision = actualCommision;
                    //如果存在客服分成
                    if (this.orderEntity.cusServicePercentageEntityList.length > 0) {
                        var cusServicePercentageEntityListSize = Number(this.orderEntity.cusServicePercentageEntityList.length);
                        for (var i = 0; i < this.orderEntity.cusServicePercentageEntityList.length; i++) {
                            this.orderEntity.cusServicePercentageEntityList[i].profit = actualCommision * (1 / 100);
                        }
                        //业务员分成总额
                        actualBranchCommision = actualCommision - (actualCommision * (cusServicePercentageEntityListSize / 100));
                    }
                    if (this.orderEntity.orderPercentageEntityList.length > 0) {
                        for (var i = 0; i < this.orderEntity.orderPercentageEntityList.length; i++) {
                            this.orderEntity.orderPercentageEntityList[i].profit = actualBranchCommision * (Number(this.orderEntity.orderPercentageEntityList[i].percentage) / 100);
                        }
                    }
                    this.orderEntity.actualBranchCommision = actualBranchCommision;
                } else {
                    vm.$notify({
                        message: '请先选择开单人员',
                        title: '操作提示',
                    });
                }
            },

            selectCusImg(urls){
                if (urls.length == 1) {
                    //this.orderEntity.cusImage = urls.toString();
                    this.orderEntity.cusImageName = urls.toString();
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
                    this.orderEntity.ownerImageName = urls.toString();
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
                this.orderEntity.cusImageName = '';
            },
            //新增时移除图片
            removeOwnerImg(){
                this.orderEntity.ownerImageName = '';
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

            //将时间戳转换为2018-12-19这样的类型
            formatTime (number, format) {
                var vm = this;
                let time = new Date(number)
                let newArr = []
                let formatArr = ['Y', 'M', 'D']
                newArr.push(time.getFullYear())
                newArr.push(vm.formatNumbera(time.getMonth() + 1))
                newArr.push(vm.formatNumbera(time.getDate()))

                for (let i in newArr) {
                    format = format.replace(formatArr[i], newArr[i])
                }
                return format;
            },
            formatNumbera (n) {
                n = n.toString()
                return n[1] ? n : '0' + n;
            }
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
<style>
    .pre-scrollable .el-tabs__item {
        padding: 0 10px;
        height: 40px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        line-height: 40px;
        display: inline-block;
        list-style: none;
        font-size: 14px;
        font-weight: 500;
        color: #303133;
        position: relative;
    }
</style>

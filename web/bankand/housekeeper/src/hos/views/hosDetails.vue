<template>
    <el-dialog  :close-on-click-modal="false" :style="{height: fullHeight +140 +'px'}" top="10px" width="95%"
               :before-close="closeDialog"
               :visible.sync="visible"
               append-to-body>
        <el-container :style="{height: fullHeight -140 + 'px'}" style="border: 1px solid #eee"
                      v-loading="loading"
                      element-loading-text="拼命加载中">
            <el-aside width="30%" class="el-aside">
                <div>
                    <el-row>
                        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                            <el-carousel :interval="3000" height="240px">
                                <el-carousel-item
                                        v-for="(item,index) in houseDetailEntity.fileList"
                                        :key="index">
                                    <div style="position:absolute;color: white;left: 10px;top: 10px;">
                                        {{houseDetailEntity.houseName}}
                                    </div>
                                    <img width="100%" height="100%"
                                         :src="'http://hkp.kufangwuyou.com/housekeeper/file/read/' + item.fileCode ">
                                </el-carousel-item>
                            </el-carousel>
                        </el-col>
                    </el-row>
                </div>
                <el-card class="box-border" shadow="hover">

                    <el-row class="left-title">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            价格: {{houseDetailEntity.price}}㎡/月
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            可租面积: {{houseDetailEntity.area}}m²
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            电量: {{houseDetailEntity.maxElectric}} KVA
                        </el-col>
                    </el-row>

                    <div class="left-tip">
                                <span v-if="houseDetailEntity.hasEia">
                                    <el-tag size="mini">可环评</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasCertificate">
                                    <el-tag size="mini">有产证</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasRegistry">
                                    <el-tag size="mini">可注册</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasCut">
                                    <el-tag size="mini">可分割</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasPlatform">
                                    <el-tag size="mini">有货台</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasElevator">
                                    <el-tag size="mini">有货梯</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasParking" :xs="4" :sm="4" :md="4"
                              :lg="4" :xl="4">
                                    <el-tag size="mini">有停车位</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasInstallCrane" :xs="4" :sm="4"
                              :md="4"
                              :lg="4" :xl="4">
                                    <el-tag size="mini">有行车</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasBathroom" :xs="4" :sm="4" :md="4"
                              :lg="4" :xl="4">
                                    <el-tag size="mini">有卫生间</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasOfficeArea" :xs="4" :sm="4" :md="4"
                              :lg="4" :xl="4">
                                    <el-tag size="mini">有办公区</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasDischargeSewage" :xs="4" :sm="4"
                              :md="4" :lg="4" :xl="4">
                                    <el-tag size="mini">有排污证</el-tag>
                                </span>
                        <span v-if="houseDetailEntity.hasShortLease" :xs="4" :sm="4" :md="4"
                              :lg="4" :xl="4">
                                    <el-tag size="mini">可短租</el-tag>
                                </span>
                    </div>
                </el-card>
                <el-card class="box-border" shadow="hover">
                    <h5 class="el-icon-info" style="font-weight: bold">基本信息</h5>
                    <div>
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                <span style="color: red;font-weight: bolder">房源状态: {{houseDetailEntity.houseStatusName}}</span>
                            </el-col>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                房源类型: {{houseDetailEntity.housePurposeName}}
                            </el-col>

                        </el-row>
                        <!--<el-row>-->
                            <!--<el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"-->
                                    <!--:xl="10">-->
                                <!--<span style="color: red;font-weight: bolder">房源公开隐藏: {{houseDetailEntity.openFlag == true ? "已公开" : "已隐藏"}}</span>-->
                            <!--</el-col>-->
                        <!--</el-row>-->
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                层高: {{houseDetailEntity.layerHeight}}
                            </el-col>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                物业费: {{houseDetailEntity.propertyFee}}元/平方米/月
                            </el-col>


                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                消防等级: {{houseDetailEntity.fireLevelName}}
                            </el-col>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                房源结构: {{houseDetailEntity.houseStructureName}}
                            </el-col>

                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                共有几层: {{houseDetailEntity.severalLayers}}层
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                所在楼层: {{houseDetailEntity.whereLayer}}层
                            </el-col>

                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                最短租期: {{houseDetailEntity.lessLease}}
                            </el-col>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                最长租期:  {{houseDetailEntity.moreLease}}
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                配电: {{houseDetailEntity.maxElectric}}KVA
                            </el-col>

                            <el-col v-if="houseDetailEntity.bearing!=null"
                                    class="left-content"
                                    :xs="10" :sm="10" :md="10" :lg="10" :xl="10">
                                承重: {{houseDetailEntity.bearing}}KG
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="24" :sm="24" :md="24" :lg="24"
                                    :xl="12">
                                创建人: {{houseDetailEntity.createName}}
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="24" :sm="24" :md="24" :lg="24"
                                    :xl="12">
                                创建时间: {{houseDetailEntity.createTime | time}}
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col class="left-content" :xs="24" :sm="24" :md="24" :lg="24"
                                    :xl="12">
                                可入住时间: {{houseDetailEntity.enterTime | time}}
                            </el-col>

                        </el-row>

                    </div>
                </el-card>
                <el-card class="box-border" shadow="hover">
                    <h5 class="el-icon-info" style="font-weight: bold">描述特点 |</h5>
                    <div>
                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否可环评: {{houseDetailEntity.hasEia == true ? "是" : "否"}}
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有产证: {{houseDetailEntity.hasCertificate == true ? "是" : "否"}}
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否可注册: {{houseDetailEntity.hasRegistry == true ? "是" : "否"}}
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否可分割: {{houseDetailEntity.hasCut == true ? "是" : "否"}}
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有货台: {{houseDetailEntity.hasPlatform == true ? "是" : "否"}}
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有货梯: {{houseDetailEntity.hasElevator == true ? "是" : "否"}}
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有停车位: {{houseDetailEntity.hasParking == true ? "是" : "否"}}
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有行车: {{houseDetailEntity.hasInstallCrane == true ? "是" : "否"}}
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有卫生间: {{houseDetailEntity.hasBathroom == true ? "是" : "否"}}
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有办公区: {{houseDetailEntity.hasOfficeArea == true ? "是" : "否"}}
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否有排污证: {{houseDetailEntity.hasDischargeSewage == true ? "是" : "否"}}
                            </el-col>

                            <el-col class="left-content" :xs="10" :sm="10" :md="10" :lg="10"
                                    :xl="10">
                                是否可短租: {{houseDetailEntity.hasShortLease == true ? "是" : "否"}}
                            </el-col>
                        </el-row>

                    </div>
                </el-card>
                <el-card class="box-border" shadow="hover">
                    <h5 class="el-icon-info" style="font-weight: bold">适合行业 |</h5>
                    <el-row>
                        <el-col class="left-content" :xs="22" :sm="22" :md="22" :lg="22"
                                :xl="22">
                            行业: {{houseDetailEntity.forInsdustry}}
                        </el-col>
                    </el-row>
                </el-card>
                <el-card class="box-border" shadow="hover">
                    <h5 class="el-icon-info" style="font-weight: bold">房源特点 |</h5>
                    <el-row>
                        <el-col class="left-content" :xs="22" :sm="22" :md="22" :lg="22"
                                :xl="22">
                            特点: {{houseDetailEntity.features}}
                        </el-col>
                    </el-row>
                </el-card>
                <template v-if="houseDetailEntity.houseLocation!=null">
                    <el-card class="box-border" shadow="hover">
                        <h5 class="el-icon-info" style="font-weight: bold">交通配套 |</h5>
                        <el-row>
                            <el-col class="left-content" :xs="22" :sm="22" :md="22" :lg="22"
                                    :xl="22">
                                {{houseDetailEntity.houseLocation.trafficFacilities}}
                            </el-col>
                        </el-row>
                    </el-card>

                </template>
            </el-aside>
            <el-aside width="70%">
                <div>
                    <el-tabs type="border-card" v-model="dislogType"
                             @tab-click="typeCheck()">
                        <el-tab-pane label="业主和地址信息" name="1">
                            <div :style="{height: fullHeight -185 + 'px'}">
                                <div style="height: 140px;overflow-y: auto">
                                    <h5 class="right-title">
                                        业主信息
                                        <el-button
                                                style="margin-top: 10px;margin-left:10px;padding: 4px 4px"
                                                v-show="houseDetailEntity.houseowner!=null && seeInfo==false"
                                                @click="clickSeePhone"
                                                type="primary" size="mini">查看业主号码与房源地址
                                        </el-button>
                                    </h5>
                                    <el-row>
                                        <el-col class="right-content" :xs="12" :sm="12"
                                                :md="12" :lg="12"
                                                :xl="12">

                                            业主类型: {{houseDetailEntity.houseowner != null
                                            ? (seeInfo == true ? houseDetailEntity.houseowner.houseownerTypeName : "********")
                                            : "********"}}
                                        </el-col>
                                        <el-col class="right-content" :xs="12" :sm="12"
                                                :md="12" :lg="12"
                                                :xl="12">

                                            所属公司:
                                            {{houseDetailEntity.houseowner != null
                                            ? (seeInfo == true ? houseDetailEntity.houseowner.companyName : "********")
                                            : "********"}}
                                        </el-col>
                                    </el-row>

                                    <el-row>
                                        <el-col class="right-content" :xs="12" :sm="12"
                                                :md="12" :lg="12"
                                                :xl="12">

                                            业主姓名:
                                            {{houseDetailEntity.houseowner != null
                                            ? (seeInfo == true ? houseDetailEntity.houseowner.houseownerName : "********")
                                            : "********"}}
                                        </el-col>
                                        <el-col class="right-content" :xs="12" :sm="12"
                                                :md="12" :lg="12"
                                                :xl="12">

                                            业主电话:
                                            {{houseDetailEntity.houseowner != null
                                            ? (seeInfo == true ? houseDetailEntity.houseowner.houseownerPhone : "********")
                                            : "********"}}
                                        </el-col>
                                    </el-row>

                                    <div v-if="houseDetailEntity.houseownerContacts!=null"
                                         v-for="(houseownerContact,index) in houseDetailEntity.houseownerContacts">
                                        <el-row>
                                            <el-col class="right-content" :xs="12" :sm="12"
                                                    :md="12"
                                                    :lg="12"
                                                    :xl="12">
                                                联系人{{index + 1}}名字 : {{seeInfo == true ? houseownerContact.contactName : "********"}}
                                            </el-col>
                                            <el-col class="right-content" :xs="12" :sm="12"
                                                    :md="12"
                                                    :lg="12"
                                                    :xl="12">
                                                联系人{{index + 1}}电话 :
                                                {{seeInfo == true
                                                ? houseownerContact.contactPhone
                                                : "********"}}
                                            </el-col>
                                        </el-row>
                                    </div>
                                </div>
                                <div style="height: 380px;overflow-y: auto">
                                    <h5 class="right-title">
                                        房源地址</h5>
                                    <div>
                                        <el-row>
                                            <el-col class="left-content" :xs="22" :sm="22"
                                                    :md="22" :lg="22"
                                                    :xl="22">
                                                {{seeInfo == true ? houseDetailEntity.houseLocation.provinceName : "********"}}
                                                -
                                                {{seeInfo == true ? houseDetailEntity.houseLocation.cityName : "********"}}
                                                -
                                                {{seeInfo == true ? houseDetailEntity.houseLocation.regionName : "********"}}
                                                -
                                                {{seeInfo == true ? houseDetailEntity.houseLocation.streetName : "********"}}

                                                <template v-if="houseDetailEntity.houseLocation !=null
                                                        && houseDetailEntity.houseLocation.communityName">
                                                    -
                                                    {{seeInfo == true ? houseDetailEntity.houseLocation.communityName : "********"}}
                                                </template>
                                                -
                                                {{seeInfo == true ? houseDetailEntity.houseLocation.detailAddress : "********"}}
                                                -
                                                {{seeInfo == true ? houseDetailEntity.houseLocation.doorNumber : "********"}}
                                            </el-col>
                                        </el-row>
                                    </div>
                                    <div v-if="seeInfo==false">
                                        <div class="backicon"/>
                                        <p class="can-not-see-location">
                                            关键信息不可见!你可以与该房源的经纪人取得联系或申请合作！
                                        </p>
                                    </div>
                                    <div v-else>

                                        <!--地图显示-->
                                        <hos-details-address-map
                                                v-if="isShowFile"
                                                :loccode="sendLocationCode"
                                                :sendLocName="sendLocName"
                                                v-on:handleHosMap="acceptHosMap"
                                                v-on:sureMethod="acceptSure">

                                        </hos-details-address-map>

                                    </div>
                                </div>

                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="房源跟进记录" name="2">
                            <hos-followup-list :houseCodeFollowup="houseCodeFollowup"
                                               ref="followupList"></hos-followup-list>
                        </el-tab-pane>
                        <el-tab-pane label="看房客户列表" name="3">
                            <hos-see-house-cus-list
                                    :houseCodeSeeHouseCusList="houseCodeSeeHouseCusList"
                                    ref="refSeeHouseCusList"></hos-see-house-cus-list>
                        </el-tab-pane>
                        <el-tab-pane label="上下架记录" name="4">
                            <house-up-down-list :houseCodeUpDown="houseCodeUpDown"
                                                ref="refHouseUpDownList"></house-up-down-list>
                        </el-tab-pane>
                        <el-tab-pane label="智能匹配" name="5">
                            <hos-match-cus :houseCodeMatchCus="houseCodeMatchCus"
                                           ref="refMatchCus"></hos-match-cus>
                        </el-tab-pane>
                    </el-tabs>
                </div>
            </el-aside>
        </el-container>
        <div style="margin-top: 5px">
            <el-row>
                <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
                    <img width="55px" height="60px"
                         :src="'http://hkp.kufangwuyou.com/housekeeper/file/read/' + houseDetailEntity.empImg ">
                </el-col>
                <el-col style="padding-top: 10px;font-weight: bold" :xs="4" :sm="4" :md="4"
                        :lg="4"
                        :xl="4">
                    <el-row>
                        <span>{{houseDetailEntity.empName}}</span>
                        <span style="background:lightgrey;font-size: 6px">库房无忧</span>
                        <span>{{houseDetailEntity.empPhone}}</span>
                    </el-row>
                </el-col>
                <el-col style="padding-top: 10px;" :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
                    <el-row>
                        <el-button icon="el-icon-edit" size="mini" type="primary"
                                   @click="checkHosPermissions(checkCode.checkUpdate)" circle/>
                        <span>修改</span>
                    </el-row>
                </el-col>
                <el-col style="padding-top: 10px;" :xs="2" :sm="2" :md="2" :lg="2" :xl="2"
                        v-if="!houseDetailEntity.hasCollect">
                    <el-row>
                        <el-button @click="checkHosPermissions(checkCode.checkCollect)"
                                   icon="el-icon-star-off" size="mini" type="primary" circle/>
                        <span>收藏</span>
                    </el-row>
                </el-col>
                <el-col style="padding-top: 10px;" :xs="2" :sm="2" :md="2" :lg="2" :xl="2"
                        v-if="houseDetailEntity.hasCollect">
                    <el-row>
                        <el-button @click="checkHosPermissions(checkCode.checkCollect)"
                                   icon="el-icon-star-on" size="mini" type="primary" circle/>
                        <span>取消收藏</span>
                    </el-row>
                </el-col>
                <el-col style="padding-top: 10px;" v-if="houseDetailEntity.houseStatus=='2'"
                        :xs="2"
                        :sm="2" :md="2" :lg="2" :xl="2">
                    <el-row>
                        <el-button @click="checkHosPermissions(checkCode.checkUp)" type="primary"
                                   size="mini"
                                   icon="el-icon-upload2" circle/>
                        <span>上架</span>
                    </el-row>
                </el-col>

                <el-col style="padding-top: 10px;" v-if="houseDetailEntity.houseStatus=='1'"
                        :xs="2"
                        :sm="2" :md="2" :lg="2" :xl="2">
                    <el-row>
                        <el-button @click="checkHosPermissions(checkCode.checkDown)" type="primary"
                                   size="mini"
                                   icon="el-icon-download" circle/>
                        <span>下架</span>
                    </el-row>
                </el-col>

                <!--<el-col style="padding-top: 10px;" v-if="houseDetailEntity.openFlag==false"-->
                        <!--:xs="2"-->
                        <!--:sm="2" :md="2" :lg="2" :xl="2">-->
                    <!--<el-row>-->
                        <!--<el-button @click="checkHosPermissions(checkCode.checkShow)" type="success"-->
                                   <!--size="mini"-->
                                   <!--icon="el-icon-view" circle/>-->
                        <!--<span>公开</span>-->
                    <!--</el-row>-->
                <!--</el-col>-->

                <!--<el-col style="padding-top: 10px;" v-if="houseDetailEntity.openFlag==true"-->
                        <!--:xs="2"-->
                        <!--:sm="2" :md="2" :lg="2" :xl="2">-->
                    <!--<el-row>-->
                        <!--<el-button @click="checkHosPermissions(checkCode.checkHide)" type="info"-->
                                   <!--size="mini"-->
                                   <!--icon="el-icon-view"-->
                                   <!--circle/>-->
                        <!--<span>隐藏</span>-->
                    <!--</el-row>-->
                <!--</el-col>-->

                <el-col style="padding-top: 10px;" :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
                    <el-row>
                        <el-button @click="checkHosPermissions(checkCode.checkApply)" type="info"
                                   size="mini" icon="el-icon-phone" circle/>
                        <span>申请合作</span>
                    </el-row>
                </el-col>
                <el-col style="padding-top: 10px;" :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
                    <el-row>
                        <el-button @click="checkHosPermissions(checkCode.checkFollowUp)"
                                   type="primary" size="mini"
                                   icon="el-icon-edit-outline" circle/>
                        <span>新增跟进</span>
                    </el-row>
                </el-col>
                <el-col style="padding-top: 10px;" :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
                    <el-row>
                        <el-button type="primary"
                                   @click="checkHosPermissions(checkCode.checkFollowupOwnerRecord)"
                                   size="mini"
                                   icon="el-icon-message" circle></el-button>
                        <span>业主拜访</span>
                    </el-row>
                </el-col>
            </el-row>
        </div>

        <hos-followup :createHouseFollowups="houseDetailEntity" v-if="hosFollowupVisible"
                      @changeSuccess="successCreateFollowup()" ref="hosfollowup"/>

        <hos-owner-followup-lists
                :houseCodeFollowupOwner="houseCodeFollowupOwner"
                v-if="hosOwnerFollowupVisible"
                ref="hosOwnerFollowup"/>

        <hos-update @closeUpdate="closeUpdate()" ref="hosUpdates"
                    v-if="openUpdatePanel"></hos-update>
        <cooperate-percent @getPercent="getPercent"
                           v-if="cooperateVisible" ref="cooperatePercents"/>
    </el-dialog>
</template>

<script>
    import hosFollowupList from "./hosFollowupList.vue"
    import hosSeeHouseCusList from "./seeHouseCusList.vue"
    import hosMatchCus from "./hosMatchCus.vue"
    import houseUpDownList   from "./houseUpDownList.vue"
    import hosOrderQuery from "./hosOrderQuery.vue"
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElAside from "../../../node_modules/element-ui/packages/aside/src/main";
    import hosDetailsAddressMap from "./hosDetailsAddressMap";
    import hosFollowup from "./hosFollowup.vue";
    import hosOwnerFollowupLists from "./hosOwnerFollowupListForHosDetail.vue";
    import hosOwnerFollowup from "./hosOwnerFollowup.vue";
    import hosUpdate from "./hosCreate.vue"

    import cooperatePercent from "../../cooperate/views/cooperatePercent.vue"
    export default {
        components: {
            ElAside,
            ElCol,
            ElRow,
            ElButton,
            hosFollowupList,
            hosSeeHouseCusList,
            hosMatchCus,
            houseUpDownList,
            hosOrderQuery,
            hosDetailsAddressMap,
            hosFollowup,
            hosOwnerFollowupLists,
            hosOwnerFollowup,
            hosUpdate,
            cooperatePercent
        },
        props: ["houseCodeDetails"],
        data() {
            return {
                cooperateVisible: false,
                cooperatePercents: 50,
                visible: false,
                openUpdatePanel: false,
                fullHeight: document.documentElement.clientHeight,
                hosCodeFollowup: null,
                hosFollowupVisible: false,
                //业主拜访
                hosOwnerFollowupVisible: false,
                isShowFile: false,
                sendLocName: '',
                sendLocationCode: 'aaa',
                seeInfo: false,
                clientHeight: '',
                houseCodeLocationAndOwnerInfo: '',
                houseCodeFollowup: '',//传值给面板
                houseCodeSeeHouseCusList: '',//传值给面板
                houseCodeMatchCus: '',//传值给面板
                houseCodeUpDown: '',//传值给面板
                houseCodeOrderQuery: '',//传值给面板
                houseCodeFollowupOwner: '',

                dislogType: '',//组件类型
                //房源详情entity
                houseDetailEntity: {
                    createName:null,
                    hasCollect: null,
                    area: null,
                    houseCode: '',//房源编码
                    empName: '',//服务专员名称
                    empPhone: '',//服务专员电话
                    ownerName: '',//业主姓名
                    ownerPhone: '',//业主电话
                    ownerTypeName: '',//业主属性
                    companyName: '',//业主公司

                    hasEia: '',//是否可环评
                    hasRegistry: '',// 是否可注册
                    hasCertificate: '',//是否有产证
                    hasCut: '',//是否可分割
                    hasShortLease: '',//是否可短租
                    hasPlatform: '',//是否有货台
                    hasElevator: '',//是否有货梯
                    hasParking: '',//是否有停车位
                    hasInstallCrane: '',//是否有行车
                    hasBathroom: '',//是否有卫生间
                    hasOfficeArea: '',//有无办公区域
                    hasDischargeSewage: '',//有无排污证

                    houseStatusName: '',// 状态
                    layerArea: '',//总面积
                    area: '',//剩余面积
                    housePurposeName: '',//房源用途
                    price: '',//价格
                    severalLayers: '',//共有层数
                    whereLayer: '',//所在层数
                    layerHeight: '',//层高
                    minAcreage: '',//最小分割面积
                    fireLevelName: '',//消防等级
                    maxElectric: '',//用电量
                    houseTypeName: '',//房源类型
                    enterTime: '',//可入住时间
                    houseFrom: '',//房源来源
                    lessLease: '',//最短租期
                    moreLease: '',//最长租期
                    houseStructureName: '',//房源结构
                    industryRestriction: '',//行业限制

                    provinceName: '',//所属省份
                    cityName: '',//所属城市
                    regionName: '',//所属区域
                    streetName: '',//所属街道
                    houseLocation: {
                        provinceName: "",
                        cityName: "",
                        regionName: "",
                        streetName: "",
                        communityName: "",
                        trafficFacilities: ""
                    },//所属地址

                    houseName: '',//房源标题
                    features: '',//房源特色
                    forInsdustry: '',//适合行业
                    houseowner: {
                        houseownerTypeName: '',
                        houseownerName: '',
                        houseownerSexName: '',
                        houseownerTypeName: ''
                    },
                    houseownerContacts: [
                        {
                            contactName: "",
                            contactPhone: ""
                        }
                    ]
                },
                loading: true,
                checkCode: {//客户模块操作权限类型字典
                    checkFollowupRecord: 'checkFollowupRecord',//房源跟进记录
                    checkFollowUp: 'checkFollowUp',//创建房源跟进
                    checkUpdate: 'checkUpdate',//房源信息修改
                    checkUp: 'checkUp',//房源上架
                    checkDown: 'checkDown',//房源下架
                    // checkHide: 'checkHide',//房源隐藏
                    checkShow: 'checkShow',//房源公开
                    checkVisitCustomer: 'checkVisitCustomer',//来访客户
                    checkFollowupOwner: 'checkFollowupOwner',//创建业主拜访
                    checkFollowupOwnerRecord: 'checkFollowupOwnerRecord',//查看业主拜访
                    checkAuthOfAddVisitCustomer: 'checkAuthOfAddVisitCustomer',//新增来访客户
                    checkMatching: 'checkMatching',//智能匹配
                    checkCollect: 'checkCollect',//收藏
                    checkApply: 'checkApply',
                },
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
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        created() {
        },


        methods: {
            getPercent(obj){
                var vm = this;
                vm.cooperatePercents = obj;
                vm.cooperateVisible = false;
                vm.$refs.cooperatePercents.visible = false;
                console.log(vm.houseDetailEntity);
                var sendObj = {};
                sendObj.entity = {
                    "houseCode": vm.houseDetailEntity.houseCode,
                    "receiveCode": vm.houseDetailEntity.empCode,
                    "dividePercentage": vm.cooperatePercents
                }
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cooperate/createhos",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        if (response.data.isSuccess) {
                            vm.$notify.success('合作申请成功')
                        }
                    }
                }).catch(function (error) {
                    vm.$notify.error('合作申请失败');
                })

            },

            //创建合作
            createCooperate(){
                var vm = this;
                vm.cooperateVisible = false;
                vm.$refs.cooperatePercents.visible = false;
                console.log(vm.houseDetailEntity);
                var sendObj = {};
                sendObj.entity = {
                    "hosCode": vm.houseDetailEntity.houseCode,
                    "receiveCode": vm.houseDetailEntity.empCode,
                    "dividePercentage": vm.cooperatePercents
                }
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cooperate/createhos",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        if (response.data.isSuccess) {
                            vm.$notify.success('合作申请成功')
                        }
                    }
                }).catch(function (error) {
                    vm.$notify.error('合作申请失败');
                })
            },
            acceptSure(aVal){
                this.isShowFile = aVal.flagshow;
            },
            acceptHosMap(aVal){
                this.isShowFile = aVal.flagshow;

            },
            /*点击查看业主电话号码*/
            clickSeePhone(){
                var vm = this;
                var obj = {}
                var sendObj = {}
                sendObj.entity = {"houseCode": vm.houseDetailEntity.houseCode}
                var config = {
                    conf: {
                        url: "houseowner/callHouseOwnerPhone",
                        data: sendObj
                    },
                    success(data){
                        vm.$notify({
                            message: data.message,
                            title: '操作提示',
                        });
                        vm.seeInfo = true;
                    }
                };
                this.$request(config);
            },
            houseUp(){
                this.$confirm('是否执行上架', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var vm = this;
                    var obj = {}
                    var sendObj = {}
                    sendObj.entity = {"houseCode": vm.houseDetailEntity.houseCode}
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "house/up",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.getHouseDetails();
                        vm.dislogType = '4'
                        vm.openHouseUpDownLog()
                    });
                })

            },
            /**
             * 填写下架理由
             */
            writeDownReason () {
                this.$prompt('请填写下架原因', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({value}) => {
                    var vm = this;
                    var obj = {}
                    var sendObj = {}
                    sendObj.entity = {
                        "houseCode": vm.houseDetailEntity.houseCode,
                        "downReason": value
                    }
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "house/down",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.getHouseDetails();
                        vm.dislogType = '4'
                        vm.openHouseUpDownLog()
                    });
                })
            },


            houseDown(){
                this.$confirm('是否执行下架?', '操作提示', {
                    type: 'warning',
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(() => {
                    this.writeDownReason();
                })

            },
            //房源公开
            houseOpenFlag(){
                var vm = this;
                this.$confirm('是否公开当前房源', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var obj = {}
                    var sendObj = {}
                    sendObj.entity = {"houseCode": vm.houseDetailEntity.houseCode}
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "house/onOpenFlag",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.getHouseDetails();
                    });
                })

            },

            // //房源隐藏
            // houseOffFlag(){
            //     var vm = this;
            //     this.$confirm('是否隐藏当前房源', '操作提示', {
            //         confirmButtonText: '确定',
            //         cancelButtonText: '取消',
            //         type: 'warning'
            //     }).then(() => {
            //         var obj = {}
            //         var sendObj = {}
            //         sendObj.entity = {"houseCode": vm.houseDetailEntity.houseCode}
            //         var options = {
            //             method: 'POST',
            //             headers: {'content-type': 'application/json'},
            //             data: sendObj,
            //             url: "house/offOpenFlag",
            //         };
            //         this.$ajax(
            //             options
            //         ).then(function (response) {
            //             vm.$notify({
            //                 message: response.data.message,
            //                 title: '操作提示',
            //             });
            //             vm.getHouseDetails();
            //         });
            //     })
            //
            // },

            //房源详情
            getHouseDetails() {
                var vm = this;
                vm.visible = true
                vm.loading = true;
                vm.seeInfo = false;
                var obj = {}
                var sendObj = {"houseCode": vm.houseCodeDetails}

                var config = {
                    conf: {
                        url: "house/detail",
                        data: sendObj
                    },
                    success(data){
                        let houseDetailEntitys = data.result;
                        vm.houseDetailEntity = houseDetailEntitys;
                        vm.houseDetailEntity.layerArea = houseDetailEntitys.layerArea;
                        vm.houseDetailEntity.area = houseDetailEntitys.area;
                        vm.houseDetailEntity.price = houseDetailEntitys.price;
                        vm.houseDetailEntity.layerHeight = houseDetailEntitys.layerHeight + "米";
                        vm.houseDetailEntity.lessLease = houseDetailEntitys.lessLease + "月";
                        vm.houseDetailEntity.moreLease = houseDetailEntitys.moreLease + "月";
                        vm.loading = false;
                        //初始化位置code值
                        vm.sendLocationCode = "";

                        vm.sendLocationCode = vm.houseDetailEntity.locationCode;
                        vm.sendLocName = "位置地图";
                        vm.isShowFile = false;
                        vm.$nextTick(() => {
                            vm.isShowFile = true
                        })

                    },
                    fail(data){

                    }
                };
                this.$request(config);
            },
            //面板类型判断
            typeCheck(){
                var vm = this;
                if (vm.dislogType != null) {
                    switch (vm.dislogType) {
                        case "1":
                            vm.isShowFile = false;
                            vm.$nextTick(() => {
                                vm.isShowFile = true
                            })
                            break;
                        case "2":
                            vm.checkHosPermissions(vm.checkCode.checkFollowupRecord);
                            break;
                        case "3":
                            vm.checkHosPermissions(vm.checkCode.checkVisitCustomer);
                            break;
                        case "4":
                            vm.openHouseUpDownLog();
                            break;
                        case "5":
                            vm.cusMatching();
                            break;
                    }
                }
            },

            //打开房源跟进面板
            houseFollowup(){
                var vm = this;
                vm.houseCodeFollowup = vm.houseCodeDetails;//传值给组件
                vm.$nextTick(() => {
                    vm.$refs.followupList.handleSizeChange(50);
                })
            },
            //打开 查看房源的客户list面板
            visitCustomer(){
                var vm = this;
                vm.houseCodeSeeHouseCusList = vm.houseCodeDetails;//传值给组件
                vm.$nextTick(() => {
                    vm.$refs.refSeeHouseCusList.handleSizeChange(50);
                })
            },
            //打开 房源智能匹配客户
            cusMatching(){
                var vm = this;
                vm.$refs.refMatchCus.houseCodeMatchCus = vm.houseCodeDetails;
                vm.$refs.refMatchCus.handleSizeChange(50);
            },
            openHouseUpDownLog(){
                var vm = this;
                vm.houseCodeUpDown = vm.houseCodeDetails;//传值给组件
                vm.$nextTick(() => {
                    vm.$refs.refHouseUpDownList.handleSizeChange(50);
                })
            },
            //打开 房源的成交记录
            openOrderQuery(){
                var vm = this;
                vm.houseCodeOrderQuery = vm.houseCodeDetails;//传值给组件
                vm.$nextTick(() => {
                    vm.$refs.refHouseOrderQuery.handleSizeChange(50);
                })
            },
            //关闭房源详情面板
            closeDialog(done){
                var vm = this;
                vm.dislogType = '';
                vm.$refs.followupList.closeDialog();
                vm.$refs.refSeeHouseCusList.closeDialog();
                vm.$refs.refMatchCus.closeDialog();
                vm.$refs.refHouseUpDownList.closeDialog();
                vm.$emit("refresh",null);
                done()
            },

            //打开新增跟进面板
            createHosFollowup(){
                var vm = this;
                vm.hosFollowupVisible = true;
                vm.$nextTick(() => {
                    vm.$refs.hosfollowup.visible = true
                })
            },

            createHosCooperate(){
                var vm = this;
                vm.cooperateVisible = true;
                vm.$nextTick(() => {
                    vm.$refs.cooperatePercents.visible = true;
                })
            },
            successCreateFollowup(){
                var vm = this;
                vm.getHouseDetails();
                vm.dislogType = '2';
                vm.typeCheck();
            },
            closeUpdate(){
                var vm = this;
                vm.getHouseDetails();
            },
            followupOwnerRecord(){
                var vm = this;
                vm.hosOwnerFollowupVisible = true;
                vm.houseOwnerCode = vm.houseDetailEntity.houseOwnerCode
                vm.houseCodeFollowupOwner = vm.houseCodeDetails;//传值给组件

                vm.$nextTick(() => {
                    console.log(vm.houseDetailEntity.houseowner)
                    vm.$refs.hosOwnerFollowup.hosOwnerInfo = vm.houseDetailEntity.houseowner;
                    console.log(vm.$refs.hosOwnerFollowup.hosOwnerInfo)
                    vm.$refs.hosOwnerFollowup.hosOwnerFollowupVisible = true;
                    vm.$refs.hosOwnerFollowup.handleSizeChange(30);

                })
            },
            //权限判断
            checkHosPermissions(checkCode){
                var vm = this;
                var sendObj = {};
                console.log(vm.houseDetailEntity.houseCode);
                sendObj.entity = {
                    "houseCode": vm.houseDetailEntity.houseCode,
                    "checkCode": checkCode
                }
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "house/checkAuth",
                };

                this.$ajax(
                    option
                ).then(function (response) {
                    if (response) {
                        if (response.data.code == '200' && response.data.isSuccess) {
                            switch (checkCode) {
                                //上架
                                case vm.checkCode.checkUp:
                                    vm.houseUp();
                                    break;
                                //下架
                                case vm.checkCode.checkDown:
                                    vm.houseDown();
                                    break;
                                // //隐藏
                                // case vm.checkCode.checkHide:
                                //     vm.houseOffFlag();
                                //     break;
                                // //公开
                                // case vm.checkCode.checkShow:
                                //     vm.houseOpenFlag();
                                //     break;
                                //来访客户
                                case vm.checkCode.checkVisitCustomer:
                                    vm.visitCustomer();
                                    break;
                                //业主拜访
                                case vm.checkCode.checkFollowupOwnerRecord:
                                    vm.followupOwnerRecord();
                                    break;
                                //房源跟进
                                case vm.checkCode.checkFollowupRecord:
                                    vm.houseFollowup();
                                    break;
                                case vm.checkCode.checkUpdate:
                                    vm.updateHos();
                                    break;
                                case vm.checkCode.checkMatching:
                                    vm.cusMatching();
                                    break;
                                case vm.checkCode.checkCollect:

                                    if (vm.houseDetailEntity.hasCollect) {
                                        vm.hosCancelCollect();
                                    } else {
                                        vm.hosCollect();
                                    }
                                    break;

                                case vm.checkCode.checkFollowUp:
                                    vm.createHosFollowup();
                                    break;
                                case vm.checkCode.checkApply:
                                    vm.createHosCooperate();
                                    break;

                            }
                        }
                    }
                }).catch(function (error) {
                    vm.$message.error('权限判断失败');
                })
            },
            //收藏
            hosCollect(){
                var vm = this;
                this.$confirm('是否收藏', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var sendObj = {};
                    sendObj.entity = {"houseCode": vm.houseDetailEntity.houseCode}
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseFavorite/create",
                    };

                    this.$ajax(
                        option
                    ).then(function (response) {
                        vm.getHouseDetails();
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                    }).catch(function (error) {
                        vm.$message.error('收藏失败');
                    })
                })
            },
            //取消收藏
            hosCancelCollect(){
                var vm = this;
                this.$confirm('是否收藏', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var sendObj = {};
                    sendObj.entity = {"houseCode": vm.houseDetailEntity.houseCode}
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "houseFavorite/delete",
                    };

                    this.$ajax(
                        option
                    ).then(function (response) {
                        vm.getHouseDetails();
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                    }).catch(function (error) {
                        vm.$message.error('取消收藏失败');
                    })
                })
            },

            updateHos(){
                var vm = this;
                vm.openUpdatePanel = true;
                vm.$nextTick(() => {
                    vm.$refs.hosUpdates.edit(vm.houseDetailEntity.houseCode);
                })
            }
        }
    }
</script>

<style scoped>

    .left-title {
        text-align: left;
        font-size: 15px;
        color: red;
        font-weight: bold;
        margin-top: 5px;

    }

    .left-title-font {
        margin-left: 10px;
        font-size: 10px;
    }

    .left-content {
        text-align: left;
        font-size: 12px;
        margin-top: 2px;
        margin-left: 10px;
    }

    .right-content {
        text-align: left;
        font-size: 15px;
        margin-top: 10px;
        margin-left: 0px;
        padding-left: 100px;
    }

    .left-tip {
        text-align: left;
    }

    .left-tip span {
        margin-top: 10px;
        margin-left: 5px;
    }

    .el-aside {
        overflow-y: auto;
        border: 1px solid #eee;
    }

    .el-table .warning-row {
        background: oldlace;
    }

    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 700px;
        overflow-y: hidden;
        overflow-x: hidden;
    }

    .pre-scrollable-s {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 650px;
        overflow-x: hidden;
        overflow-y: hidden;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }

    .page-box-s {
        height: 30px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        left: 0px;
        bottom: 0px;
        position: absolute;
        z-index: 10;
    }

    .pagex {
        overflow-y: hidden;
        overflow-x: auto;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .box-border {
        box-shadow: 0px 5px 8px 5px rgba(63, 63, 64, 0.18);
        margin: 10px 5px;
        padding: 10px 5px;
        border-radius: 10px;
    }

    .backicon {
        margin: 0 auto;
        width: 100%;
        height: 230px;
        background: url("img/deng.png") no-repeat center 10%;
    }

    .can-not-see-location {
        font-weight: bolder;
        font-size: 16px;
        text-align: center;
    }

    .right-title {
        padding-bottom: 10px;
        border-bottom: 1px solid #eee;
        font-weight: bolder;
    }


</style>


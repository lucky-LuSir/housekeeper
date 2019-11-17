<template>

    <el-dialog  :close-on-click-modal="false" style="height: 105%" top="10px" :before-close="closeDialog" append-to-body :visible.sync="visible" width="80%">

        <!--客户已经录入了， 可以直接到客户详情接口-->
        <cus-details ref="cusDetailsx"  v-if="cusDetailsOpenOrClose"></cus-details>

        <div class="pre-scrollable">
            <common-part-time-search ref="refPartTime" v-if="partTimeOpenOrClose" @closePartTime="" @setPartTime = "setPartTime"></common-part-time-search>
            <el-form  :model="cusEntityAddOrUpdate" ref="cusForm" :rules="rules" label-width="120px">
                <div>
                    <span style="font-size: 14px;font-weight: bold">基础信息:</span>
                    <div>
                        <el-row style="padding-top: 5px">
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                                <el-form-item label="所属公司：" prop="companyName">
                                    <el-input size="mini"  v-model="cusEntityAddOrUpdate.companyName" placeholder="请输入所属公司"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                                <el-form-item label="行业性质：" prop="industry">
                                    <el-select size="mini" v-model="cusEntityAddOrUpdate.industry" placeholder="请选择">
                                    <el-option label="农副食品加工业" value="农副食品加工业"></el-option>
                                    <el-option label="食品加工业" value="食品加工业"></el-option>
                                    <el-option label="酒加工" value="酒加工"></el-option>
                                    <el-option label="烟草制品业" value="烟草制品业"></el-option>
                                    <el-option label="纺织业" value="纺织业"></el-option>
                                    <el-option label="纺织服装、鞋、帽制造业" value="纺织服装、鞋、帽制造业"></el-option>
                                    <el-option label="皮革、毛皮、羽毛、绒制品业" value="皮革、毛皮、羽毛、绒制品业"></el-option>
                                    <el-option label="木材加工及木、竹、藤、棕、草制品业" value="木材加工及木、竹、藤、棕、草制品业"></el-option>
                                    <el-option label="家具制造业" value="家具制造业"></el-option>
                                    <el-option label="造纸及纸制品业" value="造纸及纸制品业"></el-option>
                                    <el-option label="印刷业和记录媒介的复制" value="印刷业和记录媒介的复制"></el-option>
                                    <el-option label="文教体育用品制造业" value="文教体育用品制造业"></el-option>
                                    <el-option label="石油加工、炼焦及核燃料加工业" value="石油加工、炼焦及核燃料加工业"></el-option>
                                    <el-option label="化学原料及化学制品制造业" value="化学原料及化学制品制造业"></el-option>
                                    <el-option label="医药制造业" value="医药制造业"></el-option>
                                    <el-option label="橡胶制品业" value="橡胶制品业"></el-option>
                                    <el-option label="塑料制品业" value="塑料制品业"></el-option>
                                    <el-option label="金属制品业" value="金属制品业"></el-option>
                                    <el-option label="通用设备制造业" value="通用设备制造业"></el-option>
                                    <el-option label="专用设备制造业" value="专用设备制造业"></el-option>
                                    <el-option label="交通运输设备制造业" value="交通运输设备制造业"></el-option>
                                    <el-option label="电气机械及器材制造业" value="电气机械及器材制造业"></el-option>
                                    <el-option label="通信设备、计算机及其他电子设备制造业" value="通信设备、计算机及其他电子设备制造业"></el-option>
                                    <el-option label="仪器仪表及文化、办公用机械制造业" value="仪器仪表及文化、办公用机械制造业"></el-option>
                                    <el-option label="工艺品制造业" value="工艺品制造业"></el-option>
                                    <el-option label="废弃资源和废旧材料回收加工业" value="废弃资源和废旧材料回收加工业"></el-option>
                                    <el-option label="电力、热力的生产和供应业" value="电力、热力的生产和供应业"></el-option>
                                    <el-option label="电商、零售" value="电商、零售"></el-option>
                                    <el-option label="已成交" value="2"></el-option>
                                    <el-option label="物流" value="物流"></el-option>
                                </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                <el-form-item label="产品信息：" prop="products">
                                    <el-input size="mini" v-model="cusEntityAddOrUpdate.products" placeholder="请输入产品信息"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row style="padding-top: 6px">
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                                <el-form-item label="客户来源：" prop="cusFrom">
                                    <el-select size="mini" v-model="cusEntityAddOrUpdate.cusFrom" placeholder="请选择">
                                    <el-option label="官网预约" value="官网预约"></el-option>
                                    <el-option label="线下扫街" value="线下扫街"></el-option>
                                    <el-option label="户外广告" value="户外广告"></el-option>
                                    <el-option label="赶集付费" value="赶集付费"></el-option>
                                    <el-option label="58付费" value="58付费"></el-option>
                                    <el-option label="兼职推荐" value="兼职推荐"></el-option>
                                    <el-option label="网络搜索" value="网络搜索"></el-option>
                                    <el-option label="400客服" value="400客服"></el-option>
                                    <el-option label="名单拨打" value="名单拨打"></el-option>
                                </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="2" :sm="2" :md="2" :lg="2" :xl="2" style="text-align: right">
                                <span>所属兼职：</span>
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6" v-if="cusEntityAddOrUpdate.ptCode == null"  style="text-align: left">
                                <el-button size="mini" type="primary" @click="openPartTime()">选择</el-button>
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6" v-if="cusEntityAddOrUpdate.ptCode != null"  style="text-align: left">
                                <el-button size="mini" type="primary" @click="openPartTime()">{{cusEntityAddOrUpdate.ptName}}</el-button>
                            </el-col>
                        </el-row>
                        <el-row style="padding-top: 5px;text-align: center"  v-if="cusEntityAddOrUpdate.ptCode != null" >
                            <el-col  :xs="2" :sm="2" :md="2" :lg="2" :xl="2" style="text-align: right">
                                <span>分成类型：</span>
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6" style="text-align: left" >
                                <el-select size="mini" v-model="cusEntityAddOrUpdate.divideType"  v-if="cusEntityAddOrUpdate.ptCode != null"  placeholder="请选择">
                                    <el-option label="分成比例" value="ratio"></el-option>
                                    <el-option label="现金奖励" value="cash"></el-option>
                                </el-select>
                            </el-col>
                            <el-col  :xs="2" :sm="2" :md="2" :lg="2" :xl="2" style="text-align: right"  v-if="cusEntityAddOrUpdate.ptCode != null" >
                                <span>兼职分成：</span>
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6" v-if="cusEntityAddOrUpdate.divideType == 'ratio' && cusEntityAddOrUpdate.ptCode != null" style="text-align: left">
                                <el-input size="mini" style="width: 150px"  v-model="cusEntityAddOrUpdate.divideRatio" placeholder="请输入比例"></el-input>%
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6" v-if="cusEntityAddOrUpdate.divideType == 'cash' && cusEntityAddOrUpdate.ptCode != null" style="text-align: left">
                                <el-input size="mini" style="width: 150px" v-model="cusEntityAddOrUpdate.divideCash" placeholder="请输入现金"></el-input>元
                            </el-col>
                        </el-row>
                    </div>

                    <div>
                        <div v-if="type == '1'">
                            <el-col  :xs="2" :sm="2" :md="2" :lg="2" :xl="2"  style="text-align: left">
                                <el-form-item label="主联系人：">
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6"  style="text-align: left">
                                <el-form-item label="手机号：" prop="cusPhone">
                                    <el-input disabled size="mini" style="width: 200px" v-model="cusEntityAddOrUpdate.cusPhone" placeholder="客户手机号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                <el-form-item label="姓名：" prop="cusName">
                                    <el-input disabled size="mini" style="width: 100px" v-model="cusEntityAddOrUpdate.cusName" placeholder="客户姓名"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                <el-form-item label="性别：" prop="cusSex">
                                    <el-radio-group disabled v-model="cusEntityAddOrUpdate.cusSex">
                                        <el-radio :label="Sir">先生</el-radio>
                                        <el-radio :label="Lady">女士</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>
                        </div>
                        <div v-if="type == '2'">
                            <el-col  :xs="2" :sm="2" :md="2" :lg="2" :xl="2"  style="text-align: left">
                                <el-form-item label="主联系人：">
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="6" :sm="6" :md="6" :lg="6" :xl="6"  style="text-align: left">
                                <el-form-item label="手机号：" prop="cusPhone">
                                    <el-input size="mini" style="width: 200px" @change="checkCustomerPhone" v-model="cusEntityAddOrUpdate.cusPhone" placeholder="客户手机号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                <el-form-item label="姓名：" prop="cusName">
                                    <el-input size="mini" style="width: 100px" v-model="cusEntityAddOrUpdate.cusName" placeholder="客户姓名"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                <el-form-item label="性别：" prop="cusSex">
                                    <el-radio-group v-model="cusEntityAddOrUpdate.cusSex">
                                    <el-radio :label="Sir">先生</el-radio>
                                    <el-radio :label="Lady">女士</el-radio>
                                </el-radio-group>
                                </el-form-item>
                            </el-col>
                        </div>
                    </div>
                    <div style="margin-top: 5px">
                        <span style="font-weight: bold;">更多联系人（可添加多个）</span>
                        <el-button size="mini" type="primary" @click="addContactsEntityList()" >添加</el-button>
                    </div>
                    <div  v-for="(item,index) in cusEntityAddOrUpdate.customerContactsEntityList" :key="index">
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                            <el-form-item label="客户手机号：">
                                <el-input size="mini" style="width: 200px"  v-model="item.contactPhone" placeholder="客户手机号"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                            <el-form-item label="客户姓名：">
                                <el-input size="mini" style="width: 100px"  v-model="item.contactName" placeholder="客户姓名"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                            <el-form-item label="性别：">
                                <el-radio-group v-model="item.contactSex">
                                    <el-radio :label="Sir">先生</el-radio>
                                    <el-radio :label="Lady">女士</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-col>
                    </div>
                </div>
                <div style="margin-top: 5px">
                    <span style="font-size: 14px;font-weight: bold">需求信息:</span>
                    <div>
                        <div>
                            <el-row style="padding-top: 5px">
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                                    <el-form-item label="客户类型：" prop="cusType">
                                        <el-radio-group v-model="cusEntityAddOrUpdate.cusType">
                                        <el-radio :label="2">我的</el-radio>
                                        <el-radio :label="1">平台</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                                    <el-form-item label="找房用途：" prop="houseType">
                                        <el-radio-group v-model="cusEntityAddOrUpdate.houseType">
                                        <el-radio :label=twohouseType>生产</el-radio>
                                        <el-radio :label=onehouseType>仓库</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8" style="text-align: left">
                                    <el-form-item label="面积：" prop="needAcreage">
                                        <el-input size="mini" style="width: 120px" v-model="cusEntityAddOrUpdate.needAcreage" placeholder="请输入面积（㎡）"></el-input>㎡
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row style="padding-top: 5px">
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="电量要求：" prop="needVoltage">
                                        <el-input size="mini" style="width: 120px" v-model="cusEntityAddOrUpdate.needVoltage"></el-input>KVA
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="价格：" prop="needPrice">
                                        <el-input size="mini" style="width: 220px" v-model="cusEntityAddOrUpdate.needPrice" >
                                            <el-select style="width: 100px" size="mini" slot="append" prop="priceUnit" v-model="cusEntityAddOrUpdate.priceUnit" @change="selectlayerNum" placeholder="请选择">
                                                <el-option label="元/㎡/天" value="元/㎡/天"></el-option>
                                                <el-option label="元/㎡/月" value="元/㎡/月"></el-option>
                                                <el-option label="元/㎡/年" value="元/㎡/年"></el-option>
                                            </el-select>
                                        </el-input>
                                    </el-form-item>
                                </el-col>

                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item  label="楼层：" prop="layerNumName">
                                        <el-select style="width: 120px" size="mini" v-model="cusEntityAddOrUpdate.layerNumName" @change="selectlayerNum" placeholder="请选择">
                                        <el-option label="底楼" value="底楼"></el-option>
                                        <el-option label="楼上" value="楼上"></el-option>
                                        <el-option label="全部" value="全部"></el-option>
                                    </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row style="padding-top: 5px">
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="层高：" prop="layerHeight">
                                        <el-input size="mini" style="width: 120px" v-model="cusEntityAddOrUpdate.layerHeight" placeholder="请输入需求层高（米）"></el-input>米
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="入住时间：" prop="enterTime">
                                        <el-date-picker
                                                v-model="cusEntityAddOrUpdate.enterTime"
                                                type="date"
                                                size="mini"
                                                style="width: 140px"
                                                placeholder="选择日期">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="期望租期：" prop="expectTerm">
                                        <el-input size="mini" style="width: 120px" v-model="cusEntityAddOrUpdate.expectTerm" placeholder="请输入期望租期（月）"></el-input>月
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <span style="font-size: 14px;font-weight: bold">其他要求:</span>
                            <el-row  style="margin-top: 3px">
                                <el-col  :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
                                    <el-form-item label="消防等级：" prop="fireLevel">
                                        <el-radio-group v-model="cusEntityAddOrUpdate.fireLevel">
                                        <el-radio :label=fireLevel1>甲类</el-radio>
                                        <el-radio :label=fireLevel2>乙类</el-radio>
                                        <el-radio :label=fireLevel3>丙类</el-radio>
                                        <el-radio :label=fireLevel4>丁类</el-radio>
                                        <el-radio :label=fireLevel5>戍类</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="需要办环评：" prop="needEia">
                                        <el-radio-group v-model="cusEntityAddOrUpdate.needEia">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <el-row style="margin-top: 3px">
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="需要办注册：" prop="needRegister">
                                         <el-radio-group v-model="cusEntityAddOrUpdate.needRegister">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="需要有产证：" prop="needCertificate">
                                        <el-radio-group v-model="cusEntityAddOrUpdate.needCertificate">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col  :xs="8" :sm="8" :md="8" :lg="8" :xl="8"  style="text-align: left">
                                    <el-form-item label="需要办公室：" prop="hasOfficeArea">
                                        <el-radio-group v-model="cusEntityAddOrUpdate.hasOfficeArea">
                                        <el-radio :label=true>是</el-radio>
                                        <el-radio :label=false>否</el-radio>
                                    </el-radio-group>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                        </div>
                        <div>
                            <div style="margin-top: 5px">
                                <span style="font-weight: bold;">选择需求区域(可添加多个)</span>
                                <el-button size="mini" type="primary" @click="addAddress()" >添加</el-button>
                            </div>
                            <div style="padding-left: 50px;margin-top: 5px" v-for="(items,index) in cusEntityAddOrUpdate.customerAreaEntityList" :key="index">

                                <el-row :gutter="0">
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                        <el-select id="provinceNameCpy"
                                                   v-model="items.province"
                                                   @visible-change="handNeedProvinceCpy" @change="changeProvCpy($event,index)"
                                                   placeholder="请选择">
                                            <el-option
                                                    v-for="(item, indexkey) in itProvinceArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                    </el-col>
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                        <el-select id="cityNameCpy"
                                                   v-model="items.city"
                                                   @focus="getCitieListCpy(items.province)"
                                                   @change="changeCityCpy($event,index)" placeholder="请选择">
                                            <el-option
                                                    v-for="(item, indexkey) in itCityArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                    </el-col>
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                        <el-select id="regionNameCpy"
                                                   v-model="items.region"
                                                   @focus="getAreaListCpy(items.city)"
                                                   @change="changeCityRegion($event,index)"
                                                   placeholder="请选择">
                                            <el-option
                                                    v-for="(item, indexkey) in itAreaArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                    </el-col>
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                        <el-select id="streetNameCpy"
                                                   v-model="items.street"
                                                   @focus="getStreetListCpy(items.region)"
                                                   @change="changeStreet($event,index)"
                                                   placeholder="请选择">
                                            <el-option
                                                    v-for="(item, indexkey) in itStreetArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                    </el-col>
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                                        <el-select id="communityNameCpy"
                                                   v-model="items.community"
                                                   @focus="getCommunityListCpy(items.street)"
                                                   @change="changeCommunity($event,index)"
                                                   placeholder="请选择">
                                            <el-option
                                                    v-for="(item, indexkey) in itCommunityArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                    </el-col>
                                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" style="text-align: center">
                                        <el-button type="danger" icon="el-icon-delete" circle @click="deleteAreaList(index)"></el-button>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
                        <div>
                            <span style="font-weight: bold">更多客户需求说明：</span>
                            <el-input type="textarea" style="padding-left: 50px;padding-right: 20px" size="mini" v-model="cusEntityAddOrUpdate.description"></el-input>
                        </div>
                        <div style="margin-top: 3px">
                            <el-form-item label="是否需要公开显示客户号码：" label-width="220px" prop="openFlag">
                                <el-radio-group v-model="cusEntityAddOrUpdate.openFlag">
                                    <el-radio :label=true>公开</el-radio>
                                    <el-radio :label=false>隐藏</el-radio>
                                </el-radio-group>
                            </el-form-item>
                            <p style="margin-left: 50px;color: red">
                                <span>1、选择隐藏，客户号码在任何状况下只有我本人可见！</span>
                            </p>
                            <p style="margin-left: 50px;color: red">
                                <span>2、选择公开，客户号码将展示给与我共享信息的部门用户！</span>
                            </p>
                        </div>
                    </div>
                    <div  style="float: none;display: inline-block;margin-bottom: 20px;width: 100%">
                        <div>
                            <span style="font-weight: bold">客户名片（可上传多张）：</span>
                            <el-button size="mini" type="primary" @click="addFile(1)" >添加</el-button>
                        </div>

                        <div v-if="fileType == 'image' && fileItem.fileUse == '1'" class="img-div1" v-for="(fileItem,fileIndex) in
                                cusEntityAddOrUpdate.fileRelationEntityList" :key="fileIndex">
                                <template>
                                    <i @click="removeImg(fileIndex)" class="el-icon-circle-close" style="float: right;"></i>
                                    <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                         v-if="fileItem.fileCode">
                                    <file  @selectFile="selectImage($event,fileIndex)" class="file_button"
                                           v-if="!fileItem.fileCode"></file>
                                </template>
                        </div>
                    </div>
                    <div style="float: none;display: inline-block;margin-bottom: 20px;width: 100%">
                        <div>
                            <span style="font-weight: bold">客户委托协议（可上传多张）：</span>
                            <el-button size="mini" type="primary" @click="addFile(2)" >添加</el-button>
                        </div>
                        <div v-if="fileType == 'image' && fileItem.fileUse == '2'" class="img-div1" v-for="(fileItem,fileIndex) in
                                cusEntityAddOrUpdate.fileRelationEntityList" :key="fileIndex">
                            <template>
                                <i @click="removeImg(fileIndex)" class="el-icon-circle-close" style="float: right;"></i>
                                <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                     v-if="fileItem.fileCode">
                                <file  @selectFile="selectImage($event,fileIndex)" class="file_button"
                                       v-if="!fileItem.fileCode"></file>
                            </template>
                        </div>
                    </div>
                </div>
            </el-form>
        </div>
        <div style="text-align: center">
            <el-button size="mini" type="primary" @click="cancel('cusForm')" >取消</el-button>

            <el-button size="mini" type="primary" @click="addCus('cusForm')" v-if="type == '2'" >确认新增</el-button>
            <el-button size="mini" type="primary" @click="submit('cusForm')" v-if="type == '1'">修改</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import file from "../../common/components/file.vue";
    import commonPartTimeSearch from "../../common/views/CommonPartTimeSearch.vue"


    export default {
        components: {
            file,
            commonPartTimeSearch,
            cusDetails: ()=> import ('./cusDetails.vue'),
        },
        data(){
            return {
                visible: false,

                cusDetailsOpenOrClose: false,//客户详情面板

                type: '',//面板类型 1为修改 2为新增
                cusEntityAddOrUpdate: {
                    industry: null,
                    products: null,
                    cusFrom: null,
                    ptCode: null,
                    ptName: null,
                    divideType: null,
                    divideRatio: null,
                    divideCash: null,
                    cusPhone: null,
                    cusName: null,
                    cusSex: null,
                    cusType: null,
                    houseType: null,
                    needAcreage: null,
                    needVoltage: null,
                    needPrice: null,
                    priceUnit: '元/㎡/天',
                    layerNumName: null,
                    layerHeight: null,
                    enterTime: null,
                    expectTerm: null,
                    fireLevel: null,
                    needEia: null,
                    needRegister: null,
                    needCertificate: null,
                    hasOfficeArea: null,
                    description: null,
                    openFlag: null,
                    customerAreaEntityList: [],
                    fileRelationEntityList: [],
                    customerContactsEntityList: [],
                },//客户实体

                partTimeOpenOrClose: false,//兼职面板
                divideType: '',//兼职分成类型
                Sir: 'Sir',
                Lady: 'Lady',
                onehouseType: '1',//仓库
                twohouseType: '2',//生产

                fireLevel1: '1',//甲
                fireLevel2: '2',//乙
                fireLevel3: '3',//丙
                fireLevel4: '4',//丁
                fireLevel5: '5',//戍

                //所属省份
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                itStreetArrCpy:[],
                itCommunityArrCpy:[],
                fileType :'image',

                rules: {
                    industry:[
                        {required: true, message: '行业性质不完善', trigger: 'change'},
                    ],
                    products:[
                        {required: true, message: '产品信息不完善', trigger: 'blur'},
                    ],
                    cusFrom:[
                        {required: true, message: '客户来自不完善', trigger: 'blur'},
                    ],
                    cusPhone:[
                        {required: true, message: '主联系人手机号不完善', trigger: 'blur'},
                    ],
                    cusName:[
                        {required: true, message: '主联系人姓名不完善', trigger: 'blur'},
                    ],
                    cusSex:[
                        {required: true, message: '主联系人性别不完善', trigger: 'blur'},
                    ],
                    cusType:[
                        {required: true, message: '客户类型不完善', trigger: 'change'},
                    ],
                    houseType:[
                        {required: true, message: '库房用途不完善', trigger: 'change'},
                    ],
                    needAcreage:[
                        {required: true, message: '需求面积不完善', trigger: 'blur'},
                    ],
                    needVoltage:[
                        {required: true, message: '电量要求不完善', trigger: 'blur'},
                    ],
                    needPrice:[
                        {required: true, message: '需求价格不完善', trigger: 'blur'},
                    ],
                    layerNumName:[
                        {required: true, message: '楼层类型不完善', trigger: 'change'},
                    ],
                    layerHeight:[
                        {required: true, message: '层高不完善', trigger: 'blur'},
                    ],
                    enterTime:[
                        {required: true, message: '入住时间不完善', trigger: 'change'},
                    ],
                    expectTerm:[
                        {required: true, message: '期望租期不完善', trigger: 'blur'},
                    ],
                    fireLevel:[
                        {required: true, message: '消防等级不完善', trigger: 'change'},
                    ],
                    needEia:[
                        {required: true, message: '需要环评不完善', trigger: 'change'},
                    ],
                    needRegister:[
                        {required: true, message: '需要可注册不完善', trigger: 'change'},
                    ],
                    needCertificate:[
                        {required: true, message: '需要有产证不完善', trigger: 'change'},
                    ],
                    hasOfficeArea:[
                        {required: true, message: '需要有无办公区域不完善', trigger: 'change'},
                    ],
                    openFlag:[
                        {required: true, message: '是否公开不完善', trigger: 'change'},
                    ],
                },
            }
        },
        mounted(){
        },
        methods: {
            closeDialog(done){
                this.type = ''
                this.cusEntityAddOrUpdate = {
                    industry: null,
                    products: null,
                    cusFrom: null,
                    ptCode: null,
                    ptName: null,
                    divideType: null,
                    divideRatio: null,
                    divideCash: null,
                    cusPhone: null,
                    cusName: null,
                    cusSex: null,
                    cusType: null,
                    houseType: null,
                    needAcreage: null,
                    needVoltage: null,
                    needPrice: null,
                    priceUnit: '元/㎡/天',
                    layerNumName: null,
                    layerHeight: null,
                    enterTime: null,
                    expectTerm: null,
                    fireLevel: null,
                    needEia: null,
                    needRegister: null,
                    needCertificate: null,
                    hasOfficeArea: null,
                    description: null,
                    openFlag: null,
                    customerAreaEntityList: [],
                    fileRelationEntityList: [],
                    customerContactsEntityList: [],
                }
                done()
            },
            access(val){
                if(this.type == '1'){
                    var vm = this;
                    var sendObj = {}
                    sendObj.entity = {"cusCode":val}
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "customer/edit",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        vm.cusEntityAddOrUpdate = response.data.result;
                        let a = vm.cusEntityAddOrUpdate.customerAreaEntityList
                        for(let i=0;i<a.length;i++){
                            vm.handNeedProvinceCpy()
                            vm.getCitieListCpy(a[i].province)
                            vm.getAreaListCpy(a[i].city)
                            vm.getStreetListCpy(a[i].region)
                            vm.getCommunityListCpy(a[i].street)
                        }
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!customer/edit');
                    })
                }
            },
            checkCustomerPhone(val){
                var vm = this;
                var sendObj = {}
                sendObj.cusPhone = val
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/checkCustomerPhone",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    var cusList = response.data.result
                    if(response.data.code =='415' && cusList.length>0){
                        vm.cusEntityAddOrUpdate.cusPhone = ''
                        vm.$confirm('当前客户已经录入，是否查看客户详情','操作提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                            vm.toCusDetails(cusList);
                        })
                    }
                }).catch(function (error) {
                    vm.$notify.error('失败');
                })
            },
            //跳转到客户详情
            toCusDetails(cusList){
                var vm =this;
                vm.cusDetailsOpenOrClose=true;
                vm.$nextTick(() => {
                    try {
                        vm.$refs.cusDetailsx.visible = true;
                        vm.$refs.cusDetailsx.cusCodeDetails = cusList[0].cusCode;//传值给组件
                        vm.$refs.cusDetailsx.getCusDetails();
                    }catch (e) {
                        vm.toCusDetails(cusList);
                    }

                })
            },
            addCus(formName){
                var vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var check = vm.checkFiels();
                        if(!check){
                            return
                        }
                        var sendObj = {}
                        sendObj.entity = this.cusEntityAddOrUpdate
                        var option = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "customer/create",
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            vm.$emit("changeCusAdd");
                            vm.$notify.success("新增成功");
                        }).catch(function (error) {
                            vm.$notify.error('新增失败');
                        })
                    } else {
                        return false;
                    }
                });

            },
            //更多联系人添加面板
            addContactsEntityList(){
                let obj = {}
                let arr = this.cusEntityAddOrUpdate.customerContactsEntityList;
                if(arr.length > 0){
                    obj = arr[arr.length-1]
                    if(obj.contactName.length == 0 || obj.contactSex.length == 0){
                        this.$notify.info("联系人信息不完整");
                        return;
                    }
                    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
                    if (!myreg.test(obj.contactPhone)) {
                        this.$notify.info("手机号不正确");
                        return;
                    }
                }
                arr.push({
                    contactPhone : '',
                    contactName: '',
                    contactSex: ''
                })
            },
            deleteAreaList(ind){
                this.cusEntityAddOrUpdate.customerAreaEntityList.splice(ind, 1);
            },
            //更多需求区域
            addAddress(){
                let obj = {}
                let arr = this.cusEntityAddOrUpdate.customerAreaEntityList;
                if(arr.length > 0){
                    obj = arr[arr.length-1]
                    if(
                        obj.province == null || obj.city == null || obj.region == null ||
                        obj.province.length == 0 || obj.city.length == 0 || obj.region.length == 0){
                        this.$notify.info("需求区域信息不完整");
                        return;
                    }
                }
                arr.push({
                    province: '',
                    city: '',
                    region: '',
                    street:'',
                    community:'',
                    provinceName: '',
                    cityName: '',
                    regionName: '',
                    streetName:'',
                    communityName:''
                })
            },
            //选择层高
            selectlayerNum(){
                let layerNumName =  this.cusEntityAddOrUpdate.layerNumName
                if(layerNumName == '底楼'){
                    this.cusEntityAddOrUpdate.layerNum = 1
                }else if (layerNumName == '楼上'){
                    this.cusEntityAddOrUpdate.layerNum = 2
                } else if(layerNumName == '全部'){
                    this.cusEntityAddOrUpdate.layerNum = 3
                }
                console.log(this.cusEntityAddOrUpdate.layerNum)
            },
            //改变省
            changeProvCpy(a,val){

                let obj = {}
                obj = this.itProvinceArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].provinceName = name;
                //清空code
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].city = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].region = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].cityName = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].regionName = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].street = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].streetName = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].community = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].communityName = null;
            },
            //改变市
            changeCityCpy(a,val){
                let obj = {}
                obj = this.itCityArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].cityName = name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].region = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].regionName = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].street = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].streetName = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].community = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].communityName = null;
            },
            changeCityRegion(a,val){

                let obj = {}
                obj = this.itAreaArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].regionName = name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].street = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].streetName = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].community = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].communityName = null;
            },

            changeStreet(a,val){

                let obj = {}
                obj = this.itStreetArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].streetName = name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].community = null;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].communityName = null;
            },


            changeCommunity(a,val){

                let obj = {}
                obj = this.itCommunityArrCpy.find((i) =>{
                    return i.areaCode === a;
                })
                let name = obj.name;
                this.cusEntityAddOrUpdate.customerAreaEntityList[val].communityName = name;
            },

            /*省份查询*/
            handNeedProvinceCpy(){
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
                if (aVal != null && aVal != "") {
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
                        vm.$message({
                            showClose: true,
                            message: '页面:查询城市信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }

            },
            /*区域查询*/
            getAreaListCpy(bVal) {
                if (bVal != null && bVal != "") {
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
                        vm.$message({
                            showClose: true,
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*街道查询*/
            getStreetListCpy(bVal) {
                if (bVal != null && bVal != "") {
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
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询街道信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*区域查询*/
            getCommunityListCpy(bVal) {
                if (bVal != null && bVal != "") {
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
                        vm.itCommunityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询社区信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            selectImage(urls,ind1){
                if (urls.length == 1) {
                    this.cusEntityAddOrUpdate.fileRelationEntityList[ind1].fileCode = urls.toString();
                    this.cusEntityAddOrUpdate.fileRelationEntityList[ind1].fileType = this.fileType;
                } else {
                    this.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeImg(ind1){
                this.cusEntityAddOrUpdate.fileRelationEntityList.splice(ind1, 1);
            },
            addFile(type){
                let arr = this.cusEntityAddOrUpdate.fileRelationEntityList;
                if(arr.length > 0) {
                    let file = arr[arr.length-1]
                    if (file.fileCode.length == 0) {
                        this.$notify.info("信息不完整");
                        return;
                    }
                }
                this.cusEntityAddOrUpdate.fileRelationEntityList.push({
                    fileCode: '',
                    fileUse: type,
                    fileType: 'image'
                })
            },
            //跳转添加兼职
            openPartTime(){
                this.partTimeOpenOrClose = true
                this.$nextTick(() => {
                    this.$refs.refPartTime.partTimeCommonDialogFormVisible = true
                })
            },
            //添加兼职
            setPartTime(a){
                this.partTimeOpenOrClose = false;
                this.$refs.refPartTime.partTimeCommonDialogFormVisible = false
                if(a == 0){
                    return;
                }
                this.cusEntityAddOrUpdate.ptCode = a.ptCode
                this.cusEntityAddOrUpdate.ptName = a.ptName

            },
            //取消修改
            cancel(formName){
                if(this.type == '1'){
                    this.$refs[formName].resetFields();
                    this.$emit("changeCusUpdate");
                }else  if(this.type == '2'){
                    this.$refs[formName].resetFields();
                    this.$emit("changeCusAdd");
                }
            },
            //修改
            submit(formName){
                var vm = this;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var check = vm.checkFiels();
                        if (!check) {
                            return
                        }
                        var sendObj = {}
                        sendObj.entity = this.cusEntityAddOrUpdate
                        var option = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "customer/update",
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {
                            vm.$emit("changeCusUpdate");
                            vm.$notify.success("修改成功");
                        }).catch(function (error) {
                            vm.$message.error('页面:获取数据失败!customer/edit');
                        })
                    } else {
                        return false;
                    }

                })
            },
            checkFiels(){
                let obj = this.cusEntityAddOrUpdate;
                if(!obj.industry || obj.industry.length == 0){
                    this.$notify.info("行业性质不完善");
                    return false;
                }
                if(!obj.products || obj.products.length == 0){
                    this.$notify.info("产品信息不完善");
                    return false;
                }
                if(!obj.cusFrom || obj.cusFrom.length == 0){
                    this.$notify.info("客户来自不完善");
                    return false;
                }

                if(obj.ptCode && obj.ptCode.length > 0){
                    if(!obj.ptName || obj.ptName.length == 0){
                        this.$notify.info("兼职人name不完善");
                        return false;
                    }
                    if(!obj.divideType || obj.divideType.length == 0){
                        this.$notify.info("分成类型不完善");
                        return false;
                    } else {
                        if(obj.divideType == 'ratio'){
                            if(!obj.divideRatio || obj.divideRatio < 0 || obj.divideRatio >100){
                                this.$notify.info("分成比例不完善");
                                return false;
                            }
                        }
                        if(obj.divideType == 'cash'){
                            if(!obj.divideCash || obj.divideCash < 0){
                                this.$notify.info("现金分成不完善");
                                return false;
                            }
                        }
                    }
                }
                if(!obj.cusPhone || obj.cusPhone.length == 0){
                    this.$notify.info("主联系人手机不完善");
                    return false;
                }
                if(!obj.cusName || obj.cusName.length == 0){
                    this.$notify.info("主联系人姓名不完善");
                    return false;
                }
                if(!obj.cusSex || obj.cusSex.length == 0){
                    this.$notify.info("主联系人性别不完善");
                    return false;
                }
                if(!obj.cusType || obj.cusType.length == 0){
                    this.$notify.info("客户类型不完善");
                    return false;
                }
                if(!obj.houseType || obj.houseType.length == 0){
                    this.$notify.info("找房用途不完善");
                    return false;
                }
                if(!obj.needAcreage || obj.needAcreage.length == 0){
                    this.$notify.info("需要面积不完善");
                    return false;
                }
                if(!obj.needVoltage  || obj.needVoltage.length == 0){
                    this.$notify.info("需求电量不完善");
                    return false;
                }
                if(!obj.needPrice  || obj.needPrice.length == 0){
                    this.$notify.info("需求价格不完善");
                    return false;
                }else {
                    if(obj.priceUnit == '元/㎡/天'){
                        if(obj.needPrice>6 || obj.needPrice<0.1){
                            this.$notify.info("单位元/㎡/天 价格在 0.1到6元之间");
                            return false;
                        }
                    }else if(obj.priceUnit == '元/㎡/月'){
                        if(obj.needPrice>180 || obj.needPrice<3){
                            this.$notify.info("单位元/㎡/月 价格在 3到180元之间");
                            return false;
                        }
                    }else if(obj.priceUnit == '元/㎡/年'){
                        if(obj.needPrice>2190 || obj.needPrice<36.5){
                            this.$notify.info("单位元/㎡/年 价格在 36.5到2190元之间");
                            return false;
                        }
                    }
                }
                if(!obj.layerNumName || obj.layerNumName.length == 0){
                    this.$notify.info("楼层类型不完善");
                    return false;
                }
                if(!obj.layerHeight || obj.layerHeight.length == 0){
                    this.$notify.info("层高不完善");
                    return false;
                }
                if(!obj.enterTime || obj.enterTime.length == 0){
                    this.$notify.info("入住时间不完善");
                    return false;
                }
                if(!obj.expectTerm || obj.expectTerm.length == 0){
                    this.$notify.info("期望租期不完善");
                    return false;
                }
                if(!obj.fireLevel || obj.fireLevel.length == 0){
                    this.$notify.info("消防等级不完善");
                    return false;
                }
                if(obj.needEia == null){
                    this.$notify.info("需要可环评不完善");
                    return false;
                }
                if(obj.needRegister  == null){
                    this.$notify.info("需要可注册不完善");
                    return false;
                }
                if(obj.needCertificate  == null){
                    this.$notify.info("需要有产证不完善");
                    return false;
                }
                if(obj.hasOfficeArea  == null) {
                    this.$notify.info("有办公区域不完善");
                    return false;
                }
                if(obj.openFlag == null){
                    this.$notify.info("是否公开不完善");
                    return false;
                }

                let areaList = this.cusEntityAddOrUpdate.customerAreaEntityList
                let area = {}
                if(areaList.length>0){
                    area = areaList[areaList.length-1]
                    if(
                        area.province == null || area.city == null || area.region == null ||
                        area.province.length == 0 || area.city.length == 0 || area.region.length == 0){
                        if(areaList.length == 1){
                            this.$notify.info("需求区域不完善");
                            return false;
                        }
                        this.cusEntityAddOrUpdate.customerAreaEntityList.splice(areaList.length-1, 1);
                    }
                }else {
                    this.$notify.info("需求区域未填写");
                    return false;
                }

                let fileList = this.cusEntityAddOrUpdate.fileRelationEntityList;
                if(fileList.length > 0) {
                    let file = fileList[fileList.length - 1];
                    if (file.fileCode == null || file.fileCode.length == 0) {
                        this.cusEntityAddOrUpdate.fileRelationEntityList.splice(fileList.length - 1, 1);
                    }
                }

                let contacts = this.cusEntityAddOrUpdate.customerContactsEntityList;
                let c ={}
                if(contacts.length > 0){
                    c = contacts[contacts.length-1]
                    if(
                        c.contactName == null || c.contactSex == null ||
                        c.contactName.length == 0 || c.contactSex.length == 0){
                        this.cusEntityAddOrUpdate.customerContactsEntityList.splice(contacts.length-1, 1);
                    }
                    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
                    if (!myreg.test(obj.contactPhone)) {
                        this.cusEntityAddOrUpdate.customerContactsEntityList.splice(contacts.length-1, 1);
                    }
                }
                return true;
            }
        }

    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 580px;
        font-size: 12px;
        overflow-y: auto;
        overflow-x: hidden;
    }
    .img-div1 {
        float: left;
        border:1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }
    .file_button{
        margin-top: 80px;
        margin-left: 50px;
    }
</style>
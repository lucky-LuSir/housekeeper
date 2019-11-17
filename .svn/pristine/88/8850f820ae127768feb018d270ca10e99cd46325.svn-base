<template>
    <el-dialog  :close-on-click-modal="false" :style="{height: fullHeight +140 +'px'}" style="margin-top: 10px;margin-bottom: 20px;" top="10px;"
               width="70%"
               :title="title"
               :before-close="closeDialog"
               :visible.sync="hctvisible"
               append-to-body
               >

          <span slot="title">
               <span class="word_title">新增房源</span>
                <el-button class="btn_title_1h" @click.prevent="custormAnchor('1hos')" type="primary" plain round size="small">1位置信息</el-button>
                <el-button class="btn_title_2h" @click.prevent="custormAnchor('2hos')" type="primary" plain round size="small">2房源私密信息填写</el-button>
                <el-button class="btn_title_3h" @click.prevent="custormAnchor('3hos')" type="primary" plain round size="small">3房源基本信息</el-button>
                <!--<el-button @click.prevent="custormAnchor('4hos')">4影像资料</el-button>-->
                <!--<el-button @click.prevent="custormAnchor('5hos')">5业主信息</el-button>  :close-on-click-modal="false"-->
           </span>
        <div :style="{height: fullHeight -180 +'px'}" class="house-div">
            <el-form :model="hosForm" ref="hosForm" :rules="rules" label-width="110px" size="mini" label-position="top">
                <!--<el-button @click="jump(1)">1位置信息</el-button>-->
                <!--<el-button @click="jump(2)">2房源信息</el-button>-->
                <!--<el-button @click="jump(3)">3房源参数</el-button>-->
                <!--<el-button @click="jump(4)">4影像资料</el-button>-->
                <!--<el-button @click="jump(5)">5业主信息</el-button>-->




                <!--<ul>-->
                    <!--<li><a href="" @click.prevent="custormAnchor('1hos')">1位置信息</a></li>-->
                    <!--<li><a href="" @click.prevent="custormAnchor('2hos')">2房源信息</a></li>-->
                    <!--<li><a href="" @click.prevent="custormAnchor('3hos')">3房源参数</a></li>-->
                    <!--<li><a href="" @click.prevent="custormAnchor('4hos')">4影像资料</a></li>-->
                    <!--<li><a href="" @click.prevent="custormAnchor('5hos')">5业主信息</a></li>-->
                <!--</ul>-->
                <v-collapse v-model="activeNames">
                    <v-panel id="1hos" header="位置信息" index="1" :style="customPanelStyle" class="d_jump">
                        <!--<div  style="margin-top: 50px">-->
                            <!--<el-row>-->
                                <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">-->
                                    <!--<el-form-item label="房源位置：" prop="locationAlias">-->
                                        <!--<el-input disabled style="width: 300px;"-->
                                                  <!--v-model="hosForm.houseLocation.locationAlias"-->
                                                  <!--placeholder="v2.8.0请选择房源位置">-->
                                            <!--<el-button slot="append" icon="el-icon-search"-->
                                                       <!--@click="openHouseLocationDialog()">点击选择-->
                                            <!--</el-button>-->
                                        <!--</el-input>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                            <!--<el-row>-->
                                <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">-->
                                    <!--<el-form-item labelWidth="325px" label="如果没有你想要的信息，请点击右侧新增：">-->
                                        <!--<el-button size="mini" type="primary" icon="el-icon-search"-->
                                                   <!--@click="openHouseLocationCreateDialog()">点击选择-->
                                        <!--</el-button>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                        <!--</div>-->

                        <div class="totalBlockLoc">

                            <!--右半部分 位置地图-->
                            <div class="rightLoc">

                                <div style="margin-top: 0px;width:100%;">
                                    <div id="maptv"  style="height:100%;width: 100%;">
                                        <div id="mapDivTwo" style="height:500px;width: 100%;"></div>
                                    </div>
                                </div>

                                <el-tag type="info">新增的位置编码为:--{{hosForm.locationCode}}</el-tag>

                                <!--<el-tag type="info">区为:&#45;&#45;{{houseLocationObj.region}}</el-tag>-->
                                <!--<el-tag type="info">街道为:&#45;&#45;{{houseLocationObj.street}}</el-tag>-->


                            </div>

                            <!--左半部分 位置字段行-->
                            <div class="leftLoc">
                                <el-form :model="houseLocationObj" :rules="rulesLoc" ref="ruleFormLoc" >
                                <div style="margin-top: 0px;display: flex;">

                                    <el-select id="cityNameBase" slot="prepend" size="small"  v-model="houseLocationObj.city"  @change="handleAllchangeCity"   placeholder="请选城市" style="width: 110px;background-color: white;">
                                        <el-option
                                                v-for="(item, indexkey) in itCityArrBase"
                                                :key="item.areaCode"
                                                :value="item.areaCode"
                                                :label="item.name">
                                        </el-option>
                                    </el-select>
                                    <el-select v-model="selectWordName"
                                               v-show="true"
                                               class="change-park"
                                               size="small"
                                               placeholder="输入关键字然后选择"
                                               filterable
                                               remote
                                               :remote-method="remoteMethod"
                                               :loading="loadingLocRemote"
                                    >
                                        <el-option
                                                v-for="item in searchPanelList"
                                                :key="item.uid"
                                                :label="item.title"
                                                :value="item.title"
                                                @click.native="changeAddressBase">
                                            <span style="float: left">{{ item.title }}</span>
                                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.address }}</span>
                                        </el-option>
                                    </el-select>

                                    <!--<el-input style="width: 100%;background-color: white;" placeholder="请输入内容"  class="input-with-select">-->


                                        <!--<el-button slot="append" icon="el-icon-location"></el-button>-->
                                    <!--</el-input>-->
                                </div>

                                <div style="margin-top: 15px;width:100%; display: flex;">
                                    <div style="margin-top: 15px;width:25%;display:flex;">
                                        <el-form-item>
                                            <el-button size="small">区</el-button>
                                        </el-form-item>

                                        <el-form-item  prop="region" label="">
                                        <el-select id="regionNameCpy" size="small" v-model="houseLocationObj.region" @focus="getAreaListCpy(houseLocationObj.city)"  @change="changeRegionCpy"    placeholder="请选择" style="width: 110px;background-color: white;margin-left: -6px">
                                            <el-option
                                                    v-for="(item, indexkey) in itAreaArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                        </el-form-item>
                                    </div>

                                    <div style="margin-top: 15px;width:40%;display:flex;">
                                        <el-form-item>
                                        <el-button size="small">街道/镇</el-button>
                                        </el-form-item>

                                        <el-form-item  prop="street" label="">
                                        <el-select id="streetNameCpy" size="small" v-model="houseLocationObj.street" @focus="getStreetListCpy(houseLocationObj.region)"  @change="changeStreetCpy"    placeholder="百度未提供,请补全" style="width: 160px;background-color: white;margin-left: -6px">
                                            <el-option
                                                    v-for="(item, indexkey) in itStreetArrCpy"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                        </el-form-item>
                                    </div>

                                    <div style="margin-top: 15px;width:35%;display:flex;">
                                        <el-form-item>
                                        <el-button size="small">社区</el-button>
                                        </el-form-item>

                                        <el-form-item>
                                        <el-select id="communityCpy" size="small" v-model="houseLocationObj.community" @focus="getCommunityList(houseLocationObj.street)"  @change="changeCommunityBase"    placeholder="有就选,没有不选" style="width: 160px;background-color: white;margin-left: -6px">
                                            <el-option
                                                    v-for="(item, indexkey) in communityList"
                                                    :key="item.areaCode"
                                                    :value="item.areaCode"
                                                    :label="item.name">
                                            </el-option>
                                        </el-select>
                                        </el-form-item>
                                    </div>

                                </div>

                                <div style="margin-top: 15px;width:100%;">
                                    <el-row>
                                        <el-form-item label="详细地址：" prop="detailAddress" label-position="top">
                                            <el-input type="textarea"
                                                      v-model="houseLocationObj.detailAddress"></el-input>
                                        </el-form-item>
                                    </el-row>
                                </div>

                                <div style="margin-top: 15px;width:100%;">
                                    <el-row>
                                        <el-form-item label="地址别名：" prop="locationAlias">
                                            <el-input type="textarea" v-model="houseLocationObj.locationAlias"/>
                                        </el-form-item>
                                    </el-row>
                                </div>

                                <div style="margin-top: 75px;width:100%;">
                                    <el-button class="next_step_btn" @click.prevent="basicNetAddLocation('ruleFormLoc')" type="success"  round size="small">下一步(私密信息)</el-button>
                                </div>

                                </el-form>

                            </div><!--end leftLoc-->


                        </div>




















                    </v-panel>
                    <!--<div v-show="finishLocFlag">-->
                    <v-panel v-show="finishLocFlag" id="2hos" header="房源私密信息填写" index="2" :style="customPanelStyle" class="d_jump">
                        <div class="secret_build_total">
                            <div class="secret_build_left">
                                <div style="height: 20px">选择楼栋名称</div>
                                <div style="margin-top:10px;border: 0px solid red;">
                                    <el-radio-group v-model="firstName"  size="mini">
                                        <el-radio-button label="A" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="B" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="C" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="D" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="E" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="F" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="G" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                        <el-radio-button label="H" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    </el-radio-group>


                                </div>

                                <div style="margin-top:10px;border: 0px solid red;">
                                <el-radio-group v-model="lastName"  size="mini">
                                    <el-radio-button label="1" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="2" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="3" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="4" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="5" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="6" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="7" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                    <el-radio-button label="8" style="width:30px;height:20px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                </el-radio-group>

                                    <!--<h2>拼接：{{fullName}}</h2>-->
                                    <!--<h2>拼接：{{severalComValue}}</h2>-->
                                </div>

                                <div style="margin-top:20px;border: 0px solid red;display: flex;">

                                    <el-button type="primary" @click="btnOneBuild('独栋')" plain size="mini" style="height: 30px;width: 90px;margin-right: 12px;">独栋</el-button>
                                    <!--所在栋-->
                                    <el-form-item  prop="whereBuilding">
                                        <el-input style="width: 287px;" v-model="hosForm.whereBuilding" placeholder="快捷选项不满足时,可自定义输入!"></el-input>
                                    </el-form-item>
                                </div>



                            </div>
                            <div class="secret_build_right">
                                <div style="height: 20px">该栋共有几层</div>
                                    <div style="margin-top:10px;border: 0px solid red;display: flex;">
                                        <el-radio-group v-model="hosForm.severalLayers"  size="mini">
                                            <el-radio-button label="1" style="width:30px;height:60px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                            <el-radio-button label="2" style="width:30px;height:60px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                            <el-radio-button label="3" style="width:30px;height:60px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                            <el-radio-button label="4" style="width:30px;height:60px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                            <el-radio-button label="5" style="width:30px;height:60px;margin-right: 20px;border: 3px solid white;"></el-radio-button>
                                            <el-radio-button label="6" style="width:30px;height:60px;margin-right: 30px;border: 3px solid white;"></el-radio-button>
                                        </el-radio-group>
                                        <el-form-item  prop="severalLayers">
                                            <el-input v-model="hosForm.severalLayers" style="height: 10px;margin-top: 4px;">
                                                <el-button slot="append" >层</el-button>
                                            </el-input>
                                        </el-form-item>


                                    </div>


                            </div>

                        </div>

                        <!--<div class="secret_tupic_total">-->
                            <!--<div class="secret_tupic_left">-->

                            <!--</div>-->
                            <!--<div class="secret_tupic_right"></div>-->
                        <!--</div>-->

                        <el-row style="display: flex;">
                            <div style="width:30%;">
                                <el-form-item label="房东类型：" prop="ownerType">
                                    <el-radio-group v-model="hosForm.ownerType" size="mini">
                                        <el-radio-button :label="1" style="margin-right: 15px;">大房东</el-radio-button>
                                        <el-radio-button :label="2" style="margin-right: 15px;">职业二房东</el-radio-button>
                                        <el-radio-button :label="3" style="margin-right: 15px;">转租户</el-radio-button>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                            <div style="width:20%;">
                                <el-form-item label="房东承诺服务费：" prop="commission">
                                    <el-input v-model="hosForm.commission" style="height: 10px;margin-top: 4px;">
                                        <el-button slot="append" >天</el-button>
                                    </el-input>

                                </el-form-item>
                            </div>

                            <div style="width:50%;display:flex;">
                                <el-form-item label="房东信息(仅自己人可见)：" prop="hhphone">
                                    <el-input v-model="hosForm.hhphone" style="height: 10px;margin-top: 4px;" placeholder="房东号码"></el-input>
                                </el-form-item>
                                <el-form-item   label="姓名:" >
                                    <el-input v-model="hosForm.houseowner.houseownerName" style="height: 10px;margin-top: 4px;" placeholder="房东称呼"></el-input>
                                </el-form-item>

                                <el-form-item   label="性别:" >
                                    <el-radio-group v-model="hosForm.houseowner.houseownerSex" size="mini">
                                        <el-radio-button label="Sir" style="margin-right: 15px;margin-left: 15px;">先生</el-radio-button>
                                        <el-radio-button label="Lady" style="margin-right: 15px;">女士</el-radio-button>
                                    </el-radio-group>
                                </el-form-item>



                            </div>
                        </el-row>

                        <el-row>
                            <el-form-item label="房源来源：" prop="houseFrom">
                                <el-select style="width: 200px" v-model="hosForm.houseFrom"
                                           placeholder="请选择">
                                    <el-option
                                            v-for="item in options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-row>

                        <el-row style="text-align: left;margin-bottom: 30px">
                            <el-button type="primary" plain @click="addHouseownerContacts"
                                       size="small">
                                更多联系人(可添加多个)
                            </el-button>
                            <el-button type="primary" plain @click="clearHouseownerContacts"
                                       size="small">
                                清空联系人
                            </el-button>
                        </el-row>

                        <el-row>
                            <el-form-item
                                    label-width="50px"
                                    v-for="(contact, i) in hosForm.houseownerContacts"
                                    :key=contact.id prop="houseownerContacts">
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="业主号码："
                                                  prop="houseownerContacts.contactPhone">
                                        <el-input size="mini" v-model="contact.contactPhone"
                                                  placeholder="电话号码"/>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="称呼："
                                                  prop="houseownerContacts.contactName">
                                        <el-input size="mini" v-model="contact.contactName"
                                                  placeholder="称呼"/>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="性别："
                                                  prop="houseownerContacts.contactSex">
                                        <el-radio-group size="mini"
                                                        v-model="contact.contactSex">
                                            <el-radio label="Sir">先生</el-radio>
                                            <el-radio label="Lady">女士</el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                            </el-form-item>
                        </el-row>




                        <div style="margin-top: 50px">
                            <!--<el-row>-->
                                <!--<el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">-->
                                    <!--<el-form-item label="推广标题：" prop="houseName">-->
                                        <!--<el-input type="textarea" :rows="2"-->
                                                  <!--placeholder="例:新空出单层1000㎡仓库，价格美丽"-->
                                                  <!--v-model="hosForm.houseName"></el-input>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                            <el-row>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">

                                </el-col>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">

                                </el-col>
                            </el-row>

                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否独栋：" prop="hasAloneBuilding">-->
                                        <!--<el-radio-group style="width: 200px"-->
                                                        <!--v-model="hosForm.hasAloneBuilding">-->
                                            <!--<el-radio :label=true>-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                            </el-row>

                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否单层：" prop="hasMonolayer">-->
                                        <!--<el-radio-group style="width: 200px"-->
                                                        <!--v-model="hosForm.hasMonolayer">-->
                                            <!--<el-radio @change="layerSet()" :label=true>-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio @change="layerSetFalse()" :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                            </el-row>


                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否可环评：" prop="hasEia">-->
                                        <!--<el-radio-group v-model="hosForm.hasEia">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否可分割：" prop="hasCut">-->
                                        <!--<el-radio-group v-model="hosForm.hasCut">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否有产证：" prop="hasCertificate">-->
                                        <!--<el-radio-group v-model="hosForm.hasCertificate">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否可注册：" prop="hasRegistry">-->
                                        <!--<el-radio-group v-model="hosForm.hasRegistry">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否可短租：" prop="hasShortLease">-->
                                        <!--<el-radio-group v-model="hosForm.hasShortLease">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="有无停车位：" prop="hasParking">-->
                                        <!--<el-radio-group v-model="hosForm.hasParking">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否有货台：" prop="hasPlatform">-->
                                        <!--<el-radio-group v-model="hosForm.hasPlatform">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否有货梯：" prop="hasElevator">-->
                                        <!--<el-radio-group v-model="hosForm.hasElevator">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="是否有行车：" prop="hasInstallCrane">-->
                                        <!--<el-radio-group v-model="hosForm.hasInstallCrane">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="有无卫生间：" prop="hasBathroom">-->
                                        <!--<el-radio-group v-model="hosForm.hasBathroom">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="有无办公区：" prop="hasOfficeArea">-->
                                        <!--<el-radio-group v-model="hosForm.hasOfficeArea">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="有无排污证：" prop="hasDischargeSewage">-->
                                        <!--<el-radio-group v-model="hosForm.hasDischargeSewage">-->
                                            <!--<el-radio :label="true">-->
                                                <!--是-->
                                            <!--</el-radio>-->
                                            <!--<el-radio :label="false">-->
                                                <!--否-->
                                            <!--</el-radio>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                </el-col>

                            </el-row>

                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="房源来源：" prop="houseFrom">-->
                                        <!--<el-select style="width: 200px" v-model="hosForm.houseFrom"-->
                                                   <!--placeholder="请选择">-->
                                            <!--<el-option-->
                                                    <!--v-for="item in options"-->
                                                    <!--:key="item.value"-->
                                                    <!--:label="item.label"-->
                                                    <!--:value="item.value">-->
                                            <!--</el-option>-->
                                        <!--</el-select>-->
                                    <!--</el-form-item>-->
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="所属兼职(选填)：" prop="ptName">
                                        <el-input disabled v-model="hosForm.ptName"
                                                  placeholder="请选择兼职人">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="openPartTimeDialog()">点击选择
                                            </el-button>
                                        </el-input>
                                    </el-form-item>


                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item v-if="hosForm.ptName!='' && hosForm.ptName!=null" label="分成类型：" prop="divideType">
                                        <el-select style="width: 200px" v-model="hosForm.divideType"
                                                   placeholder="请选择">
                                            <el-option
                                                    v-for="item in divideTypes"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item v-if="hosForm.divideType=='ratio'" label="兼职分成：" prop="divideRatio">
                                        <el-input  v-model="hosForm.divideRatio">
                                            <template slot="append">%</template>
                                        </el-input>
                                    </el-form-item>
                                    <el-form-item v-if="hosForm.divideType=='cash'" label="兼职分成：" prop="divideCash">
                                        <el-input  v-model="hosForm.divideCash">
                                            <template slot="append">元</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-button class="next_step_btn" @click.prevent="custormAnchor('3hos')" type="success"  round size="small">下一步(基本信息)</el-button>
                            </el-row>
                        </div>
                    </v-panel>
                    <v-panel v-show="finishLocFlag" id="3hos" header="房源基本信息" index="3" :style="customPanelStyle" class="d_jump">

                        <el-row style="display: flex;">
                            <div style="width: 30%">
                                <el-form-item label="房源结构：" prop="houseStructure">
                                    <el-radio-group v-model="hosForm.houseStructure"
                                                    size="mini">
                                        <el-radio-button :label="1" style="margin-right: 15px;">钢结构</el-radio-button>
                                        <el-radio-button :label="2" style="margin-right: 15px;">框架</el-radio-button>
                                        <el-radio-button :label="3" style="margin-right: 15px;">钢混</el-radio-button>
                                        <el-radio-button :label="4" style="margin-right: 15px;">砖混</el-radio-button>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                            <div style="width: 70%">
                                <el-form-item label="消防等级：" prop="fireLevel">
                                    <el-radio-group v-model="hosForm.fireLevel" size="mini">
                                        <el-radio-button :label="1" style="margin-right: 15px;">甲类</el-radio-button>
                                        <el-radio-button :label="2" style="margin-right: 15px;">乙类</el-radio-button>
                                        <el-radio-button :label="3" style="margin-right: 15px;">丙类(消防栓+喷淋)</el-radio-button>
                                        <el-radio-button :label="4" style="margin-right: 15px;">丁戊类(消防栓)</el-radio-button>
                                        <!--<el-radio-button :label="5" style="margin-right: 15px;">戊类</el-radio-button>-->
                                    </el-radio-group>
                                </el-form-item>
                            </div>

                        </el-row>

                        <el-row style="display: flex;">
                            <div style="width: 30%;">
                                <el-form-item label="行业限制说明：" prop="industryRestriction">
                                    <el-input v-model="hosForm.industryRestriction"/>
                                    <el-button type="primary" @click="btnNoLimit('无限制')" plain size="mini" style="height: 30px;width: 90px;margin-right: 12px;">无限制</el-button>
                                </el-form-item>


                            </div>
                            <div style="width: 70%">
                                <el-form-item label="选择特点进行推广展示：">
                                    <el-checkbox v-model="hosForm.hasEia" label="可环评" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>
                                    <el-checkbox v-model="hosForm.hasRegistry" label="可注册" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>

                                    <el-checkbox v-model="hosForm.hasDischargeSewage" label="产证齐全" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>
                                    <el-checkbox v-model="hosForm.hasParking" label="停车位" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>

                                    <el-checkbox v-model="hosForm.hasPlatform" label="有货台" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>
                                    <el-checkbox v-model="hosForm.hasElevator" label="有电梯" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>

                                    <el-checkbox v-model="hosForm.hasInstallCrane" label="有行车" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>
                                    <el-checkbox v-model="hosForm.hasOfficeArea" label="有办公区" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>

                                    <el-checkbox v-model="hosForm.hasWholeRental" label="整栋出租" border size="mini" style="margin: 0px 10px 0px 0px;"></el-checkbox>
                                </el-form-item>

                            </div>

                        </el-row>


                        <div>房源参数</div>
                            <el-form-item
                                    label-width="50px"
                                    v-for="(contact, i) in hosForm.subHosList"
                                    :key=contact.whereLayer prop="houseownerContacts">
                                <div style="width: 100%;display: flex;">
                                    <div style="width: 40%;">

                                        <el-button    icon="el-icon-discount" style="margin:0px 0px 0px 0px;width: 20%;" type="primary" plain >
                                            {{contact.whereLayer}}楼
                                        </el-button>
                                        <el-tag  style="width: 71%;" size="small" placeholder="空楼层信息,需要您添加!"> {{contact.area}}平可租 层高{{contact.layerHeight}}米 {{contact.price}}{{contact.unit}} 租期{{contact.lessLease}}个月~{{contact.moreLease}}个月</el-tag>
                                    </div>
                                    <div style="width: 60%;margin-left: -30px;">
                                        <el-button     icon="el-icon-discount" style="margin:0px 0px 0px 0px;width: 50px;background-color: white;"></el-button>
                                        <el-button @click="contact.floorLineFlag = !(contact.floorLineFlag)"  style="margin:0px 0px 0px -10px;width: 60%;" type="primary" plain >添加房源</el-button>
                                        <el-tag v-show="contact.recordFlag" type="success" effect="light" size="small">我录了此层信息</el-tag>
                                    </div>
                                </div>

                                <!--<div  class="base_floor_total" v-if="contact.floorLineFlag">-->
                                    <!--&lt;!&ndash;第一行&ndash;&gt;-->
                                    <!--<div class="base_floor_line">-->
                                        <!--<div style="width:40%;">-->
                                            <!--<el-form-item label="房源类型：" >-->
                                                <!--<el-radio-group v-model="contact.houseStyle" size="mini">-->
                                                    <!--<el-radio-button :label="1">普通仓库厂房</el-radio-button>-->
                                                    <!--<el-radio-button :label="2">冷链仓库</el-radio-button>-->
                                                    <!--<el-radio-button :label="3">高台仓库</el-radio-button>-->
                                                    <!--<el-radio-button :label="4">危险品库</el-radio-button>-->
                                                <!--</el-radio-group>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="房源用途：" >-->
                                                <!--<el-radio-group v-model="contact.housePurpose" size="mini">-->
                                                    <!--<el-radio-button :label="1">仓储</el-radio-button>-->
                                                    <!--<el-radio-button :label="2">生产</el-radio-button>-->
                                                    <!--<el-radio-button :label="3">仓储和生产</el-radio-button>-->
                                                <!--</el-radio-group>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="租期时长限制：" >-->
                                                <!--<el-input placeholder="最短" v-model="contact.lessLease" style="width: 150px">-->
                                                    <!--<template slot="append" style="background-color: white;">-->
                                                        <!--~-->
                                                    <!--</template>-->
                                                <!--</el-input>-->
                                                <!--<el-input v-model="contact.moreLease" style="width: 150px;margin-left: -10px;" placeholder="最长">-->
                                                    <!--<template slot="append">-->
                                                        <!--月-->
                                                    <!--</template>-->
                                                <!--</el-input>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->





                                    <!--</div>-->


                                    <!--&lt;!&ndash;第二行&ndash;&gt;-->
                                    <!--<div class="base_floor_line">-->
                                        <!--<div style="width:40%;">-->
                                            <!--<el-form-item label="所在层：">-->
                                                <!--<el-input v-model="contact.whereLayer"/>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="可出租面积：" >-->
                                                <!--<el-input-number style="width: 220px" size="mini"-->
                                                                 <!--v-model="contact.area" :min="1"-->
                                                                 <!--controls-position="right">-->
                                                <!--</el-input-number>-->
                                                <!--<el-button>m²</el-button>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="配电量：" >-->
                                                <!--<el-input style="width: 200px" size="mini"-->
                                                          <!--v-model="contact.maxElectric"-->
                                                          <!--controls-position="right">-->
                                                    <!--<template slot="append">KVA</template>-->
                                                <!--</el-input>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                    <!--</div>-->

                                    <!--&lt;!&ndash;第三行&ndash;&gt;-->
                                    <!--<div class="base_floor_line">-->
                                        <!--<div style="width:40%;">-->
                                            <!--<el-form-item label="本层承重：" >-->
                                                <!--<el-input style="width:200px"-->
                                                          <!--v-model="contact.bearing"></el-input>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="本层总面积：" >-->
                                                <!--<el-input-number style="width: 200px" size="mini"-->
                                                                 <!--v-model="contact.layerArea" :min="1"-->
                                                                 <!--controls-position="right"/>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="最小分割面积：" >-->
                                                <!--<el-input-number style="width: 220px" size="mini"-->
                                                                 <!--v-model="contact.minAcreage" :min="1"-->
                                                                 <!--controls-position="right">-->
                                                <!--</el-input-number>-->
                                                <!--<el-button>m²</el-button>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                    <!--</div>-->

                                    <!--&lt;!&ndash;第四行&ndash;&gt;-->
                                    <!--<div class="base_floor_line">-->
                                        <!--<div style="width:40%;">-->
                                            <!--<el-form-item label="房源价格：" >-->
                                                <!--<el-input v-model="contact.price" style="width: 200px">-->
                                                    <!--<el-select v-model="contact.unit" prop="unit"-->
                                                               <!--slot="append"-->
                                                               <!--style="width: 100px"-->
                                                               <!--placeholder="请选择">-->
                                                        <!--<el-option label="元/㎡/天"-->
                                                                   <!--value="元/㎡/天"></el-option>-->
                                                        <!--<el-option label="元/㎡/月"-->
                                                                   <!--value="元/㎡/月"></el-option>-->
                                                        <!--<el-option label="元/㎡/年"-->
                                                                   <!--value="元/㎡/年"></el-option>-->
                                                    <!--</el-select>-->
                                                <!--</el-input>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="层高：" >-->
                                                <!--<el-input-number v-model="contact.layerHeight" :min="1"-->
                                                                 <!--controls-position="right"-->
                                                                 <!--style="width: 200px">-->
                                                <!--</el-input-number>-->
                                                <!--米-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->
                                        <!--<div style="width:30%;">-->
                                            <!--<el-form-item label="可入住时间：" >-->
                                                <!--<el-date-picker-->
                                                        <!--size="mini"-->
                                                        <!--style="width: 200px"-->
                                                        <!--v-model="contact.enterTime"-->
                                                        <!--type="date"-->
                                                        <!--placeholder="可入住时间">-->
                                                <!--</el-date-picker>-->
                                            <!--</el-form-item>-->
                                        <!--</div>-->


                                    <!--</div>-->

                                    <!--&lt;!&ndash;第五行 按钮&ndash;&gt;-->
                                    <!--<div class="base_floor_line">-->
                                        <!--<div style="width:100%;">-->
                                            <!--<el-button class="next_step_btn" style="float: right" @click="btnFuncRecord(contact)" type="primary"  plain round size="small">确定</el-button>-->
                                            <!--<el-button class="next_step_btn" style="float: right" @click="btnClearRecord(contact)" type="warning"  plain round size="small">清空</el-button>-->

                                        <!--</div>-->
                                    <!--</div>-->



                                <!--</div>-->

                                <!--<el-dialog  :close-on-click-modal="false"-->
                                        <!--title="新增楼层"-->
                                        <!--:visible.sync="centerDialogVisible"-->
                                        <!--width="70%"-->
                                        <!--center-->
                                        <!--append-to-body>-->
                                    <!--<span></span>-->

                                    <div  class="base_floor_total" v-if="contact.floorLineFlag">
                                        <el-form :model="contact" :rules="rulesYanFloor" ref="ruleFloorRef">
                                        <!--第一行-->
                                        <div class="base_floor_line">
                                            <div style="width:40%;">
                                                <el-form-item label="房源类型：" prop="houseStyle">
                                                    <el-radio-group v-model="contact.houseStyle" size="mini">
                                                        <el-radio-button :label="1">普通仓库厂房</el-radio-button>
                                                        <el-radio-button :label="2">冷链仓库</el-radio-button>
                                                        <el-radio-button :label="3">高台仓库</el-radio-button>
                                                        <el-radio-button :label="4">危险品库</el-radio-button>
                                                    </el-radio-group>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="房源用途：" prop="housePurpose">
                                                    <el-radio-group v-model="contact.housePurpose" size="mini">
                                                        <el-radio-button :label="1">仓储</el-radio-button>
                                                        <el-radio-button :label="2">生产</el-radio-button>
                                                        <el-radio-button :label="3">仓储和生产</el-radio-button>
                                                    </el-radio-group>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;display:flex;">
                                                <el-form-item label="租期时长最短：" prop="lessLease">
                                                    <el-input placeholder="最短" v-model="contact.lessLease" style="width: 150px" size="mini">
                                                        <template slot="append" style="background-color: white;">
                                                            ~
                                                        </template>
                                                    </el-input>
                                                </el-form-item>

                                                <el-form-item label="最长：" prop="moreLease">
                                                    <el-input v-model="contact.moreLease" style="width: 150px;margin-left: -10px;" placeholder="最长" size="mini">
                                                        <template slot="append">
                                                            月
                                                        </template>
                                                    </el-input>
                                                </el-form-item>

                                            </div>





                                        </div>


                                        <!--第二行-->
                                        <div class="base_floor_line">
                                            <div style="width:40%;">
                                                <el-form-item label="所在层：" prop="whereLayer" size="mini">
                                                    <el-input v-model="contact.whereLayer"/>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="可出租面积：" prop="area">
                                                    <el-input-number style="width: 220px" size="small"
                                                                     v-model="contact.area" :min="0"
                                                                     controls-position="right">
                                                    </el-input-number>
                                                    <el-button size="mini">m²</el-button>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="配电量：" prop="maxElectric">
                                                    <el-input style="width: 200px" size="mini"
                                                              v-model="contact.maxElectric"
                                                              controls-position="right">
                                                        <template slot="append">KVA</template>
                                                    </el-input>
                                                </el-form-item>
                                            </div>
                                        </div>

                                        <!--第三行-->
                                        <div class="base_floor_line">
                                            <div style="width:40%;">
                                                <el-form-item label="本层承重：" prop="bearing" size="mini">
                                                    <el-input style="width:200px"
                                                              v-model="contact.bearing"></el-input>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="本层总面积：" prop="layerArea">
                                                    <el-input-number style="width: 200px" size="mini"
                                                                     v-model="contact.layerArea" :min="0"
                                                                     controls-position="right"/>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="最小分割面积：" prop="minAcreage">
                                                    <el-input-number style="width: 220px" size="mini"
                                                                     v-model="contact.minAcreage" :min="0"
                                                                     controls-position="right">
                                                    </el-input-number>
                                                    <el-button size="mini">m²</el-button>
                                                </el-form-item>
                                            </div>
                                        </div>

                                        <!--第四行-->
                                        <div class="base_floor_line">
                                            <div style="width:40%;">
                                                <el-form-item label="房源价格：" prop="price" >
                                                    <el-input v-model="contact.price" style="width: 200px" size="mini">
                                                        <el-select v-model="contact.unit" prop="unit"
                                                                   size="mini"
                                                                   slot="append"
                                                                   style="width: 100px"
                                                                   placeholder="请选择">
                                                            <el-option label="元/㎡/天"
                                                                       value="元/㎡/天"></el-option>
                                                            <el-option label="元/㎡/月"
                                                                       value="元/㎡/月"></el-option>
                                                            <el-option label="元/㎡/年"
                                                                       value="元/㎡/年"></el-option>
                                                        </el-select>
                                                    </el-input>
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="层高：" prop="layerHeight">
                                                    <el-input-number v-model="contact.layerHeight" :min="0"
                                                                     controls-position="right"
                                                                     style="width: 200px" size="mini">
                                                    </el-input-number>
                                                    米
                                                </el-form-item>
                                            </div>
                                            <div style="width:30%;">
                                                <el-form-item label="可入住时间：" prop="enterTime">
                                                    <el-date-picker
                                                            size="mini"
                                                            style="width: 200px"
                                                            v-model="contact.enterTime"
                                                            type="date"
                                                            placeholder="可入住时间">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </div>


                                        </div>

                                            <div class="base_floor_line">
                                                <div style="width:70%;">
                                                    <el-form-item label="房源描述：" prop="hosDescribe" size="mini" >
                                                        <el-input v-model="contact.hosDescribe" style="width:70%;"/>
                                                    </el-form-item>
                                                </div>

                                            </div>

                                            <div class="base_floor_line" style="height: 260px;">

                                                <el-row>
                                                    <div class="img-tip-two">
                                                        房源内部图片(可上传3~5张，对外展示的推广图)
                                                    </div>
                                                    <p>
                                                        <el-button size="mini" style="color: #5bc0de"
                                                                   @click="addFileListImg(contact)">添加图片
                                                        </el-button>
                                                        <el-button size="mini" style="color: #d58512"
                                                                   @click="deleteFileListImg(contact)">清空图片
                                                        </el-button>
                                                    </p>
                                                    <el-form-item labelWidth="0px" prop="fileList">
                                                        <div style="display: inline-block;margin-bottom: 20px"
                                                             v-if="fileType == 'image'" class="img-div1" v-for="(fileItem,fileIndex) in contact.fileList" :key="fileIndex">
                                                            <i @click="removeFileListImg(fileIndex)"
                                                               class="el-icon-circle-close" style="float: right;"
                                                               v-if="fileItem.fileCode"></i>
                                                            <img height="100%" width="100%"
                                                                 :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                                                 v-if="fileItem.fileCode">
                                                            <file @selectFile="selectFileListImage($event,fileIndex,contact)"
                                                                  class="file_button"
                                                                  v-if="!fileItem.fileCode"></file>
                                                        </div>
                                                    </el-form-item>
                                                </el-row>
                                            </div>

                                        <!--第五行 按钮-->
                                        <div class="base_floor_line">
                                            <div style="width:100%;">
                                                <el-button class="next_step_btn" style="float: right" @click="btnFuncRecord(contact,'ruleFloorRef',i)" type="primary"   round size="small">确定</el-button>
                                                <el-button class="next_step_btn" style="float: right" @click="btnClearRecord(contact)" type="warning"   round size="small">清空</el-button>

                                            </div>
                                        </div>


                                        </el-form>
                                    </div>


                                <!--</el-dialog>-->
                                <!--对话框里的楼层-->




                            </el-form-item>

                        <!--结束for循环楼数量-->












                        <div style="margin-top: 50px">
                            <el-row >
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>

                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <!--<el-form-item label="物业费：" prop="propertyFee">-->
                                        <!--<el-input v-model="hosForm.propertyFee"-->
                                                  <!--style="width: 200px">-->
                                            <!--<template slot="append">元/㎡/月</template>-->
                                        <!--</el-input>-->
                                    <!--</el-form-item>-->
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">

                                </el-col>

                            </el-row>
                            <el-row>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">

                                </el-col>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">

                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">

                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                                    <!--<el-form-item label="适合行业：" prop="forInsdustry">-->
                                        <!--<el-input v-model="hosForm.forInsdustry" type="textarea"/>-->
                                    <!--</el-form-item>-->
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                                    <!--<el-form-item label="房源特色：" prop="features">-->
                                        <!--<el-input v-model="hosForm.features" type="textarea"/>-->
                                    <!--</el-form-item>-->
                                </el-col>
                            </el-row>
                        </div>
                    </v-panel>
                    <v-panel v-show="finishLocFlag" id="4hos"  header="影像资料" index="4" :style="customPanelStyle" class="d_jump">

                        <div style="margin-top: 50px">



                            <el-row>
                                <div class="img-tip-two">
                                    房源外立面/场地,仅自己人可见!(3~5)
                                </div>
                                <p>
                                    <el-button size="mini" style="color: #5bc0de"
                                               @click="addHouseYardImageImg()">添加图片
                                    </el-button>
                                    <el-button size="mini" style="color: #d58512"
                                               @click="deleteHouseYardImage()">清空图片
                                    </el-button>
                                </p>
                                <el-form-item labelWidth="0px" prop="houseYardImage">
                                    <div style="display: inline-block;margin-bottom: 20px"
                                         v-if="fileType == 'image'" class="img-div1" v-for="(fileItem,fileIndex) in
                        hosForm.houseYardImage" :key="fileIndex">
                                        <i @click="removeHouseYardImage(fileIndex)"
                                           class="el-icon-circle-close" style="float: right;"
                                           v-if="fileItem.fileCode"></i>
                                        <img height="100%" width="100%"
                                             :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                             v-if="fileItem.fileCode">
                                        <file @selectFile="selectHouseYardImage($event,fileIndex)"
                                              class="file_button"
                                              v-if="!fileItem.fileCode"></file>
                                    </div>
                                </el-form-item>
                            </el-row>




                            <el-row>
                                <div class="img-tip-two">
                                    房源的出租委托协议(可上传多张)
                                </div>
                                <p>
                                    <el-button size="mini" style="color: #5bc0de"
                                               @click="addhouseProtocolListImg()">添加图片
                                    </el-button>
                                    <el-button size="mini" style="color: #d58512"
                                               @click="deletehouseProtocolListImg()">清空图片
                                    </el-button>
                                </p>

                                <div style="display: inline-block;margin-bottom: 20px"
                                     v-if="fileType == 'image'" class="img-div1" v-for="(fileItem,fileIndex) in
                        hosForm.houseProtocol" :key="fileIndex">
                                    <i @click="removehouseProtocolListImg(fileIndex)"
                                       class="el-icon-circle-close" style="float: right;"
                                       v-if="fileItem.fileCode"></i>
                                    <img height="100%" width="100%"
                                         :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                         v-if="fileItem.fileCode">
                                    <file @selectFile="selecthouseProtocolListImage($event,fileIndex)"
                                          class="file_button"
                                          v-if="!fileItem.fileCode"></file>
                                </div>
                            </el-row>
                        </div>
                    </v-panel>

                    <!--</div>-->
                    <!--<v-panel id="5hos" header="业主信息" index="5" :style="customPanelStyle" class="d_jump">-->
                        <!--<div style="margin-top: 50px">-->
                            <!--&lt;!&ndash;<el-row >&ndash;&gt;-->
                                <!--&lt;!&ndash;<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">&ndash;&gt;-->
                                    <!--&lt;!&ndash;<el-form-item label="是否要公开显示业主号码 ？" labelWidth="200px">&ndash;&gt;-->
                                        <!--&lt;!&ndash;<el-radio-group v-model="hosForm.openFlag">&ndash;&gt;-->
                                            <!--&lt;!&ndash;<el-radio-button :label="true">&ndash;&gt;-->
                                                <!--&lt;!&ndash;公开&ndash;&gt;-->
                                            <!--&lt;!&ndash;</el-radio-button>&ndash;&gt;-->
                                            <!--&lt;!&ndash;<el-radio-button :label="false">&ndash;&gt;-->
                                                <!--&lt;!&ndash;隐藏&ndash;&gt;-->
                                            <!--&lt;!&ndash;</el-radio-button>&ndash;&gt;-->
                                        <!--&lt;!&ndash;</el-radio-group>&ndash;&gt;-->
                                    <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
                                <!--&lt;!&ndash;</el-col>&ndash;&gt;-->
                                <!--&lt;!&ndash;<el-col style="font-size: 14px;color: red;font-weight: bold"&ndash;&gt;-->
                                        <!--&lt;!&ndash;:xs="12" :sm="12" :md="12" :lg="12" :xl="12">&ndash;&gt;-->
                                    <!--&lt;!&ndash;<p>1、选择隐藏，业主号码在任何状况下只有我本人可见 ！</p>&ndash;&gt;-->
                                    <!--&lt;!&ndash;<p>2、选择公开，业主号码将展示给与我共享信息的部门用户 ！</p>&ndash;&gt;-->
                                <!--&lt;!&ndash;</el-col>&ndash;&gt;-->
                            <!--&lt;!&ndash;</el-row>&ndash;&gt;-->
                            <!--&lt;!&ndash;<el-row>&ndash;&gt;-->
                                <!--&lt;!&ndash;<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">&ndash;&gt;-->
                                    <!--&lt;!&ndash;<el-form-item label="联系人：" prop="houseownerName">&ndash;&gt;-->
                                        <!--&lt;!&ndash;<el-input disabled&ndash;&gt;-->
                                                  <!--&lt;!&ndash;v-model="hosForm.houseowner.houseownerName"&ndash;&gt;-->
                                                  <!--&lt;!&ndash;placeholder="请选择业主">&ndash;&gt;-->
                                            <!--&lt;!&ndash;<el-button slot="append" icon="el-icon-search"&ndash;&gt;-->
                                                       <!--&lt;!&ndash;@click="openHouseOwnerDialog()">点击选择&ndash;&gt;-->
                                            <!--&lt;!&ndash;</el-button>&ndash;&gt;-->
                                        <!--&lt;!&ndash;</el-input>&ndash;&gt;-->
                                    <!--&lt;!&ndash;</el-form-item>&ndash;&gt;-->
                                <!--&lt;!&ndash;</el-col>&ndash;&gt;-->
                            <!--&lt;!&ndash;</el-row>&ndash;&gt;-->


                        <!--</div>-->
                    <!--</v-panel>-->
                </v-collapse>
            </el-form>

        </div>
        <div   style="text-align: center;height: 190px;">
            <!--<el-button size="mini" type="primary"-->
                       <!--@click="resetForm('hosForm')">重置-->
            <!--</el-button>-->
            <!--<el-button size="mini" type="success" @click="submitForm('hosForm')">提交-->
            <!--</el-button>-->
            <div class="next_step_total">
                <div class="next_step_right">



                    <el-button v-show="finishLocFlag" class="next_step_btn" @click="submitForm('hosForm')" type="primary" plain round size="small">完成</el-button>
                </div>

            </div>



        </div>
        <part-time ref="partTimes" v-if="partTimeVisible"
                   v-on:setPartTime="closePartTimePanel($event)"
                   v-on:closePartTime="closePartTime()"></part-time>
        <house-owner ref="houseOwners" v-if="houseOwnerVisible"
                     v-on:setHouseOwner="closeHouseOwnerPanel($event)"
                     v-on:closeHouseOwner="closeHouseOwner()">

        </house-owner>



        <house-location ref="houseLocations" v-if="houseLocationVisible"
                        v-on:setHouseLocation="closeHouseLocationPanel($event)"
                        v-on:closeHouseLocation="closeHouseLocation()"></house-location>

        <hos-location-create v-on:setHouseLocationData="setLocationData($event)"
                             ref="houseLocationCreate">

        </hos-location-create>
    </el-dialog>

</template>


<script>

    import partTime from "../../common/views/CommonPartTimeSearch.vue";
    import houseOwner from "../../common/views/CommonHouseOwnerSearch.vue"
    import houseLocation from "../../common/views/CommonHouseLocationSearch.vue"
    import file from "../../common/components/file.vue";
    import hosLocationCreate from  "./hosLocationCreate.vue";
    import ElRow from "element-ui/packages/row/src/row";
    import blueicon from "../../../static/hkpImgIcon/png_blue2loc.png";

    export default{
        components: {
            blueicon,
            ElRow,
            partTime,
            file,
            houseOwner,
            houseLocation,
            hosLocationCreate
        },

        created:function () {

            var vm = this;
            vm.handleAllCity();
        },


        mounted () {
            window.addEventListener('scroll', this.handleScroll, true);
        },

        props:["loccode"],
        data(){

            //校验电话号码
            let checkPhone = (rule, value, callback) => {
                setTimeout(() => {
//                    alert(value);
                    debugger
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                finishLocFlag:false,
                centerDialogVisible: false,
                firstName:'',
                lastName:'',
                selectWordName:'',
                loadingLocRemote:false,
                searchPanelList:[],
                flagUpMap:false,
                cityDingAreaObj:{
                    cityCode:null,
                    cityName:null,
                },
                cityDingwei:"",
                bdmap:"",
                communityList:[],
                itStreetArrCpy:[],
                itAreaArrCpy:[],
                itCityArrBase:[],
                //位置地区
                houseLocationObj: {
                    id: '',
                    locationAlias: '',
                    locationCode: '',
                    province: '',
                    provinceName: '',
                    city: '',
                    cityName: '',
                    region: '',
                    regionName: '',
                    street: '',
                    streetName: '',
                    detailAddress: '',
                    doorNumber: '',
                    longitude: '',
                    latitude: '',
                    community: '',
                    communityName: '',
                    trafficFacilities: '',
                    fileList: null,
                    locRegionList: [
                        {
                            regionBatchCode: '',
                            locationCode: '',
                            locationAlias: '',
                            num: '',
                            longitude: '',
                            latitude: '',
                        }
                    ],
                    hasLocRegion: '',
                },
                houseLocationVisible: false,
                title: "",
                rules: {

                    houseName: [
                        {required: true, message: '请填写房源标题', trigger: 'blur'},
                    ],


                    hasAloneBuilding: [
                        {required: true, message: '请选择是否独栋', trigger: 'blur'},
                    ],
                    //1校验
                    whereBuilding: [
                        {required: true, message: '请填写所在栋', trigger: 'change'},
                    ],
                    hasMonolayer: [
                        {required: true, message: '请填写是否单层', trigger: 'blur'},
                    ],
                    //2校验
                    severalLayers: [
                        {required: true, message: '请填写共有几层', trigger: 'change'},
                    ],
                    //3校验
                    ownerType: [
                        { required: true, message: '请选择房东类型', trigger: 'change' }
                    ],
                    //4校验房东承诺服务费
                    commission: [
                        {required: true, message: '请填写服务费', trigger: 'blur'},
                    ],

                    //5房东手机号
                    hhphone: [
                        {required: true, message: '请填房东手机号', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    //6校验房东姓名
                    houseownerName: [
                        {required: true, message: '请填写房东姓名', trigger: 'blur'},
                    ],

                    //7校验房东姓名
                    houseownerSex: [
                        { required: true, message: '请选择房东性别', trigger: 'change' }
                    ],

                    //8校验房源来源
                    houseFrom: [
                        {required: true, message: '请输入房源来源', trigger: 'blur'},
                    ],

                    //9校验房源结构
                    houseStructure: [
                        {required: true, message: '请输入房源结构', trigger: 'blur'},
                    ],

                    //10校验消防等级
                    fireLevel: [
                        {required: true, message: '请输入消防等级', trigger: 'blur'},
                    ],

                    //11校验行业限制说明
                    industryRestriction: [
                        {required: true, message: '请输入行业限制', trigger: 'change'},
                    ],


                    hasEia: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasCut: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasCertificate: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasRegistry: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasShortLease: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasParking: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasPlatform: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasElevator: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasInstallCrane: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasBathroom: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasOfficeArea: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],
                    hasDischargeSewage: [
                        {required: true, message: '请选择', trigger: 'blur'},
                    ],


//                    area: [
//                        {required: true, message: '请输入层面积', trigger: 'blur'},
//                    ],


                    propertyFee: [
                        {required: true, message: '请输入物业费', trigger: 'blur'},
                    ],







                    forInsdustry: [
                        {required: true, message: '请输入适合行业', trigger: 'blur'},
                    ],
//                    features: [
//                        {required: true, message: '请输入房源特色', trigger: 'blur'},
//                    ],
                    fileList: [
                        {required: true, message: '请上传房源展示封面图片', trigger: 'change'},
                    ],

                    houseYardImage: [
                        {required: true, message: '请上传房源内部图片', trigger: 'change'},
                    ],
                    divideType:[
                        {required: true, message: '请选择分成类型', trigger: 'blur'},
                    ],
                    divideRatio:[
                        {required: true, message: '请输入分成比例', trigger: 'blur'},
                    ],
                    divideCash:[
                        {required: true, message: '请输入分成金额', trigger: 'blur'},
                    ]



                },

                rulesYanFloor:{
                    houseStyle: [
                        {required: true, message: '请输入房源类型', trigger: 'blur'},
                    ],
                    housePurpose: [
                        {required: true, message: '请填写房源用途', trigger: 'blur'},
                    ],
                    lessLease: [
                        {required: true, message: '请输入最短租期', trigger: 'blur'},
                    ],
                    moreLease: [
                        {required: true, message: '请输入最长租期', trigger: 'blur'},
                    ],
                    whereLayer: [
                        {required: true, message: '请填写所在层', trigger: 'blur'},
                    ],
                    area: [
                        { required: true, message: '请输可出租面积', trigger: 'blur' },
                    ],
                    maxElectric:[
                        { required: true, message: '请输配电量', trigger: 'blur' },
                    ],
                    bearing:[
                        {required: true, message: '请输承重', trigger: 'blur'},
                    ],
                    layerArea: [
                        {required: true, message: '请输层面积', trigger: 'blur'},
                    ],
                    minAcreage:[
                        {required: true, message: '请输最小分割面积', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                    ],
                    layerHeight: [
                        {required: true, message: '请输入层高', trigger: 'blur'},
                    ],
                    enterTime: [
                        {required: true, message: '请输入入住时间', trigger: 'blur'},
                    ],
                    hosDescribe: [
                        {required: true, message: '请输入描述', trigger: 'blur'},
                    ],

                },
                rulesLoc:{
                    region:[
                        { required: true, message: '请选择区', trigger: 'change' }
                    ],
                    street:[
                        { required: true, message: '请选择街道/镇', trigger: 'change' }
                    ],
                    detailAddress: [
                        { required: true, message: '请输入详细地址', trigger: 'blur' },
                    ],

                    locationAlias: [
                        { required: true, message: '请输入别名', trigger: 'blur' },
                    ],

                 },
                activeNames: ['1','2','3','4','5'],
                customPanelStyle: {
                    background: '#ffffff',
                    borderRadius: '4px',
                    marginBottom: '24px',
                    border: 0,
//                    'border-bottom':'6px solid #0f0',

                },
                fileType: 'image',
                partTimeVisible: false,
                houseOwnerVisible: false,
                fullHeight: document.documentElement.clientHeight,
                hctvisible: false,
                hosForm: {
                    id: '',
                    locationCode: '',
                    divideType: '',
                    divideRatio:'',
                    divideCash:'',
                    houseCode: '',
                    houseName: '',//房源标题
                    ownerType: "",//房东类型
                    commission:"",//服务费
                    houseStyle: '',
                    houseYardImage: [
                        {
                            fileCode: '',
                            fileName: '',
                            fileType: '',
                            fileUse: '',
                            transferFileType: ''
                        }
                    ],
                    fileList: [
                        {
                            fileCode: '',
                            fileName: '',
                            fileType: '',
                            fileUse: '',
                            transferFileType: ''
                        }
                    ],
                    houseProtocol: [],
                    propertyFee: '',
                    serveralLayers: '',
                    whereBuilding: '',
                    hasAloneBuilding: false,
                    hasMonolayer: '',
                    houseStructure: '',

                    layerArea: '',
                    bearing: '',
                    hasCollect: '',
                    houseCode: '',//房源编码
                    empName: '',//服务专员名称
                    empPhone: '',//服务专员电话
                    ownerCode:null,
                    ownerName: '',//业主姓名
                    ownerPhone: '',//业主电话
                    ownerTypeName: '',//业主属性
                    companyName: '',//业主公司

                    hasEia: false,//是否可环评
                    hasRegistry: false,// 是否可注册
                    hasCertificate: false,//是否有产证
                    hasCut: false,//是否可分割
                    hasShortLease: false,//是否可短租
                    hasPlatform: false,//是否有货台
                    hasElevator: false,//是否有货梯
                    hasParking: false,//是否有停车位
                    hasInstallCrane: false,//是否有行车
                    hasBathroom: '',//是否有卫生间
                    hasOfficeArea: false,//有无办公区域
                    hasDischargeSewage: false,//有无排污证
                    hasWholeRental:false,//整栋出租

                    houseStatusName: '',// 状态
                    layerArea: '',//总面积
                    area: '',//剩余面积
                    housePurpose: '',//
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
                        locationAlias: '',
                        locationCode: '',
                        provinceName: "",
                        cityName: "",
                        regionName: "",
                        streetName: "",
                        communityName: "",
                        trafficFacilities: ""

                    },//所属地址

                    subHosList: [
                        {
                            recordFlag:false,
                            floorLineFlag:false,
                            houseStyle: "",
                            housePurpose: "",
                            whereLayer: "",
                            layerArea: 0,
                            area: 0,
                            minAcreage: 0,
                            price: 0,
                            unit: "",
                            layerHeight: 0,
                            maxElectric: 0,
                            lessLease: 0,
                            moreLease: 0,
                            bearing: 0,
                            hosDescribe:"",
                            fileList: [
                                {
                                    fileCode: '',
                                    fileName: '',
                                    fileType: '',
                                    fileUse: '',
                                    transferFileType: ''
                                }
                            ],
                            enterTime: ""
                        }

                    ],


                    features: '',//房源特色
                    forInsdustry: '',//适合行业
                    hhphone:'',
                    houseowner: {
                        houseownerTypeName: '',
                        houseownerName: '',
                        houseownerCode: '',
                        houseownerSex:'',
                        houseownerPhone:''
                    },
                    houseownerContacts: [],
                    ptName: '',
                    ptCode: ''
                },

                divideTypes: [
                    {
                        value: 'ratio',
                        label: '分成比例',
                    },
                    {
                        value:'cash',
                        label:'现金奖励',
                    }
                ],

                options: [{
                    value: '官网预约',
                    label: '官网预约'
                     },
                    {
                        value: '线下扫街',
                        label: '线下扫街'
                    }, {
                        value: '网络搜索',
                        label: '网络搜索'
                    }, {
                        value: '400客服',
                        label: '400客服'
                    }, {
                        value: '名单拨打',
                        label: '名单拨打'
                    }, {
                        value: 'QQ微信群',
                        label: 'QQ微信群'
                    }, {
                        value: '58付费',
                        label: '58付费'
                    }, {
                        value: '赶集付费',
                        label: '赶集付费'
                    }, {
                        value: '户外广告',
                        label: '户外广告'
                    }, {
                        value: '兼职推荐',
                        label: '兼职推荐'
                    }],
            }

        },

        computed:{
            fullName:function () {
                return  this.firstName + this.lastName ;
            },


            severalComValue() {
                return this.hosForm.severalLayers
            }
        },
        watch: {

            severalComValue(val, oldVal){
                //alert("b.c: "+val, oldVal);
                if(val>0){
                    var vm = this;
                    vm.hosForm.subHosList=[];
                    for(var i=0;i<val;i++){
                        //alert("开始循环:"+i);
                        var hObj = {};

                        hObj = {
                            recordFlag:false,
                            floorLineFlag:false,
                            houseStyle: '',
                            housePurpose: '',
                            layerArea: 0,
                            area: 0,
                            minAcreage: 0,
                            price: 0,
                            unit: "元/㎡/天",
                            layerHeight: 0,
                            maxElectric: 0,
                            lessLease: '',
                            moreLease: '',
                            bearing: '',
                            fileList: [
                                {
                                    fileCode: '',
                                    fileName: '',
                                    fileType: '',
                                    fileUse: '',
                                    transferFileType: ''
                                }
                            ],
                            enterTime: ''
                        }
                        hObj.whereLayer = i+1;
                        vm.hosForm.subHosList.push(hObj);
                    }
                }


            },

            firstName(newName, oldName) {
               this.hosForm.whereBuilding = this.fullName;
            },

            lastName(newName, oldName) {
                this.hosForm.whereBuilding = this.fullName;
            },


            fullHeight (val) {
                if (!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function () {
                        that.timer = false
                    }, 400)
                }
            },

            hasMonolayer(val){
                if (val == true) {
                    this.hosForm.whereLayer = 1
                    this.hosForm.severalLayers = 1
                } else {
                    this.hosForm.whereLayer = ''
                    this.hosForm.servalLayers = ''
                }
            }
        },

        updated() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }

            if(this.flagUpMap==false){

                this.flagUpMap = true;
                var vm = {};
                vm = this;
                var map = new BMap.Map('mapDivTwo', { minZoom : 16, maxZoom : 19, enableMapClick: false });
                //十字架型
//            map.setDefaultCursor("default");
                this.bdmap= map;
                map.centerAndZoom(new BMap.Point(116.404,39.915),11);
                this.dingweiFunc();
                //开始浏览器定位,加载百度初始化方法
            }


        },

        methods: {
            funcseveralComValue(val, oldVal){
//                alert("开始b.c: "+val, oldVal);
                if(val>0){
                    var vm = this;
                    vm.hosForm.subHosList=[];
                    for(var i=0;i<val;i++){
                        //alert("开始循环:"+i);
                        var hObj = {};

                        hObj = {
                            recordFlag:false,
                            floorLineFlag:false,
                            houseStyle: '',
                            housePurpose: '',
                            layerArea: 0,
                            area: 0,
                            minAcreage: 0,
                            price: 0,
                            unit: "元/㎡/天",
                            layerHeight: 0,
                            maxElectric: 0,
                            lessLease: '',
                            moreLease: '',
                            bearing: '',
                            fileList: [
                                {
                                    fileCode: '',
                                    fileName: '',
                                    fileType: '',
                                    fileUse: '',
                                    transferFileType: ''
                                }
                            ],
                            enterTime: ''
                        }
                        hObj.whereLayer = i+1;
                        vm.hosForm.subHosList.push(hObj);
                    }
                }


            },
            handleScroll () {
                var vm = this;
                let clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
                // 设备/屏幕高度
                let scrollObj = document.getElementById("2hos"); // 滚动区域
                let scrollTop = scrollObj.scrollTop; // div 到头部的距离
                let scrollHeight = scrollObj.scrollHeight; // 滚动条的总高度
//                vm.$notify.warning("到头部的距离"+scrollTop);
                if(vm.hosForm.locationCode==''||vm.hosForm.locationCode==null){
                    //vm.$notify.warning("请先录好位置点击--下一步(私密信息)按钮.");
                }



            },
            btnClearRecord(conHobj){
                //基本参数重置
                    conHobj.houseStyle='',
                    conHobj.housePurpose='',
                    conHobj.layerArea=0,
                    conHobj.area=0,
                    conHobj.minAcreage=0,
                    conHobj.price=0,
                    conHobj.unit="元/㎡/天",
                    conHobj.layerHeight=0,
                    conHobj.maxElectric=0,
                    conHobj.lessLease='',
                    conHobj.moreLease='',
                    conHobj.bearing='',
                    conHobj.enterTime=''
                //控制记录
                //alert(conHobj.area);
                conHobj.recordFlag=false;
                conHobj.floorLineFlag=false;
            },
            btnFuncRecord(conHobj,formName,iNum){

                var vm = this;
//                alert(formName);
                console.log("查的房参$refs,formName");
                console.log(vm.$refs.ruleFloorRef[0]);

                var newVal = parseInt(conHobj.price);
                let newUnit = conHobj.unit;
                 if(newUnit=="元/㎡/天") {
                    if (newVal > 10) {
                        vm.$notify.warning('一天不能大于10元,(所选单位:元/㎡/天)');
                        return;
                    }
                }else if(newUnit=="元/㎡/月"){
                    if (newVal > 300) {
                        vm.$notify.warning('一月不能大于300元,(所选单位:元/㎡/月)');
                        return;
                    }
                }else if(newUnit=="元/㎡/年"){
                    if (newVal > 1000) {
                        vm.$notify.warning('一年不能大于1000元,(所选单位:元/㎡/年)');
                        return;
                    }
                }

                this.$nextTick(() => {
                    vm.$refs.ruleFloorRef[0].validate((valid) => {
                        if (valid) {
                            conHobj.recordFlag=true;
                            //收缩参数框
                            conHobj.floorLineFlag=false;
                            vm.centerDialogVisible = false;
                        } else {
                            vm.$notify.warning("请完善各个字段(房源参数)!");
                            return false;
                        }
                    });
                });


            },
            controlFloorLineFlag(floorLineFlag){
                var vm = this;
                if(floorLineFlag==true){
                    floorLineFlag=false;
                }else if(floorLineFlag==false){
                    floorLineFlag=true;
                }else {
                    floorLineFlag=true;
                    vm.$notify.success("可以再次点击,未识别到按钮!");
                }
            },
            openCenterDialog(){
                this.centerDialogVisible = true;
            },
            btnNoLimit(aval){

                this.hosForm.industryRestriction=aval;
            },
            btnOneBuild(aval){

                this.hosForm.hasAloneBuilding=true;
                this.firstName="";
                this.lastName="";
                this.hosForm.whereBuilding=aval;
            },
            //单选后改值调用
            changeAddressBase(aval){
//                alert("点击222");
                //赋值地址name
                var vm = this;
                vm.houseLocationObj.region="";
                vm.houseLocationObj.regionName ="";
                vm.$set(vm.houseLocationObj, 'region', "");
                vm.houseLocationObj.street="";
                vm.houseLocationObj.streetName = "";
                vm.$set(vm.houseLocationObj, 'street', "");
                let areaObj = {};
                areaObj = this.searchPanelList.find((item)=>{
                    return item.title === vm.selectWordName;
                });
                console.log("百度地图单选对象:");
                console.log(areaObj);
                var covertLoc = {
                    "locationAlias":"",
                    "cityName":"",
                    "regionName":"",
                    "streetName":"",
                    "communityName":"",
                    "detailAddress":"",
                    "longitude":null,
                    "latitude":null
                }
                covertLoc.locationAlias = areaObj.title;
                covertLoc.cityName = vm.houseLocationObj.cityName;
                covertLoc.detailAddress = areaObj.address;
                covertLoc.longitude = areaObj.point.lng;
                covertLoc.latitude = areaObj.point.lat;
                this.basicNetSearchTwoVersion(covertLoc);
                //定位到选择对象点的位置
                vm.bdmap.clearOverlays();//清除图层覆盖物
                var spotPoint = new BMap.Point(covertLoc.longitude,covertLoc.latitude);
                vm.bdmap.centerAndZoom(spotPoint, 17);
                var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_redloc.png", new BMap.Size(30,30));
                let aMark = new BMap.Marker(spotPoint,{icon:myIcon});
                vm.bdmap.addOverlay(aMark);    //添加标注
                aMark.enableDragging();   // 点标注可拖拽
                aMark.addEventListener("dragend", function(e){
                    //alert("当前位置：" + e.point.lng + ", " + e.point.lat);
//                    vm.houseLocationObj.longitude = e.point.lng;
//                    vm.houseLocationObj.latitude = e.point.lat;
                });
                aMark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

            },
            //查询补全百度地图单选对象
            basicNetSearchTwoVersion(objaaa){
                var vm = this;
                console.log("单对象:");
                console.log(objaaa);
                if(objaaa.cityName==''||objaaa.cityName==null){
                    vm.$notify.warning('请手动选择城市,(您浏览器未定位到城市)!');
                    return;
                }

                var sendObj = {};
                sendObj.entity = objaaa;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "houseLocation/searchTwoVersion",
                };
                this.$ajax(
                    option
                ).then(function (response) {

                    var locTbObj = [];
                    locTbObj = response.data.result;
                    console.log(locTbObj);
                    //vm.houseLocationObj = locTbObj;//会造成街道下拉框问题
                    vm.houseLocationObj.locationAlias = locTbObj.locationAlias;
                    vm.houseLocationObj.province = locTbObj.province;
                    vm.houseLocationObj.provinceName = locTbObj.provinceName;

                    vm.houseLocationObj.city = locTbObj.city;
                    vm.houseLocationObj.cityName = locTbObj.cityName;
                    if(typeof(locTbObj.region)!="undefined"){
                        vm.houseLocationObj.region = locTbObj.region;
                        vm.houseLocationObj.regionName = locTbObj.regionName;
                    }

                    if(typeof(locTbObj.street)!="undefined"){
                        vm.houseLocationObj.street = locTbObj.street;
                        vm.houseLocationObj.streetName = locTbObj.streetName;
                    }

                    if(typeof(locTbObj.community)!="undefined"){
                        vm.houseLocationObj.community = locTbObj.community;
                        vm.houseLocationObj.communityName = locTbObj.communityName;
                    }


                    vm.houseLocationObj.detailAddress = locTbObj.detailAddress;
                    vm.houseLocationObj.longitude = locTbObj.longitude;
                    vm.houseLocationObj.latitude = locTbObj.latitude;

                    //vm.$notify.success(vm.houseLocationObj.street);

                    vm.itAreaArrCpy =[];
                    if(vm.itAreaArrCpy==null ||vm.itAreaArrCpy.length==0){
                        vm.itAreaArrCpy =[];
                        let newArea = {};
                        newArea.areaCode = locTbObj.region;
                        newArea.name = locTbObj.regionName;
                        vm.itAreaArrCpy.push(newArea);
                    }
                    //给5级省市区选择框列表赋值
                    vm.itStreetArrCpy =[];
                    if(vm.itStreetArrCpy==null ||vm.itStreetArrCpy.length==0){
                        vm.itStreetArrCpy =[];
                        let newArea = {};
                        newArea.areaCode = locTbObj.street;
                        newArea.name = locTbObj.streetName;
                        vm.itStreetArrCpy.push(newArea);
                    }

                    vm.communityList =[];
                    if(vm.communityList==null ||vm.communityList.length==0){
                        vm.communityList =[];
                        let newArea = {};
                        newArea.areaCode = locTbObj.community;
                        newArea.name = locTbObj.communityName;
                        vm.communityList.push(newArea);
                    }

                }).catch(function (error) {
                    alert("searchTwoVersion,请求失败");
//                    this.$notify({
//                        title: '警告:请求失败',
//                        message: 'searchTwoVersion,请求失败',
//                        type: 'warning'
//                    });
                })
            },


            //查询补全百度地图单选对象
            basicNetAddLocation(formName){

                var vm = this;
                console.log("查的位置的$refs");
                console.log(vm.$refs[formName]);
                if(vm.selectWordName ==null || vm.selectWordName ==''){
                    vm.$notify.warning("输入位置关键字然后选择!");
                    return;
                }


                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        var sendObj = {};
                        sendObj.entity = vm.houseLocationObj;
                        var option = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "houseLocation/createTwoVersion",
                        };
                        this.$ajax(
                            option
                        ).then(function (response) {

                            var locTbObj = [];
                            locTbObj = response.data.result;
                            console.log(locTbObj);
                            vm.houseLocationObj = locTbObj;
                            vm.$notify.success(response.data.message);
                            vm.hosForm.locationCode = vm.houseLocationObj.locationCode;
                            vm.finishLocFlag =true;

                            vm.$nextTick(() => {
                                vm.custormAnchor('2hos');
                            });



                        }).catch(function (error) {
                            alert("searchTwoVersion,请求失败");
//                    this.$notify({
//                        title: '警告:请求失败',
//                        message: 'searchTwoVersion,请求失败',
//                        type: 'warning'
//                    });
                        })
                    } else {
                        console.log('error submit!!');
                        vm.$notify.warning("请完善位置各个字段!");
                        return false;
                    }
                });

            },
            remoteMethod(query) {
                var vm = this;
                if (query !== '') {
                    //alert(query);
                    this.selectWordName = query;
                    this.loadingLocRemote = true;
                    setTimeout(() => {
                        this.loadingLocRemote = false;
                        this.handleBaiduSerach();
                    }, 200);
                } else {
                    // alert("超时,未远程搜索");
                    vm.searchPanelList = [];
                }
            },
            handleBaiduSerach(){
                var vm = {};
                vm = this;
                //增加智能搜索
                var local = new BMap.LocalSearch(this.bdmap, {
                    renderOptions:{map: this.bdmap, panel:"r-result"},
                    pageCapacity:10
                });


                //local.search("天安门");
                // alert(this.selectWordName);
                let sumword = this.houseLocationObj.cityName+this.selectWordName;
//                alert(sumword)
                var rr=local.search(sumword);
//                debugger
                local.disableFirstResultSelection();

                local.setSearchCompleteCallback(function(){
                    //alert("百度搜索哈哈哈");
                    var lr = local.getResults();
                    console.log("handleBaiduSerach打印查询对象");
                    console.log(lr.Ar[0]);
                    vm.searchPanelList = lr.Ar;

                    let selectPoint = vm.searchPanelList[0].point;
                    vm.selectPointObj = selectPoint;
                    var pp = new BMap.Point(vm.selectPointObj.lng,vm.selectPointObj.lat);   //获取第一个智能搜索的结果
                    vm.bdmap.centerAndZoom(pp, 18);
                    var myIcon = new BMap.Icon("../../../static/hkpImgIcon/png_loc.png", new BMap.Size(30,30));

                    //alert("清除所有覆盖物");
                    vm.bdmap.clearOverlays();
                    vm.bdmap.removeOverlay(lr.Ar[0].marker);
                    let aMark = new BMap.Marker(pp,{icon:myIcon});
                    //vm.bdmap.addOverlay(aMark);    //添加标注
                    //aMark.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                    let mapvPoi = {};
                    mapvPoi.longitude = vm.selectPointObj.lng;
                    mapvPoi.latitude = vm.selectPointObj.lat;
                    mapvPoi.locationCode = "百度检索点";
                    //vm.houseLocationObj = mapvPoi;
                    let tableList = [];
                    tableList.push(mapvPoi);
                    //vm.showMapvFunc(tableList);
//                    alert("开始清除地图");
                    vm.bdmap.clearOverlays();//清除图层覆盖物
                });


                vm.bdmap.clearOverlays();//清除图层覆盖物
                // console.log("打印查询对象");
                // console.log(local);
                // console.log("打印查询对象rr");
                // console.log(local.getResults());
                // local.searchInBounds(myKeys, map.getBounds());
                //
            },
            dingweiFunc(){
                var vm = {};
                vm = this;
                let map = this.bdmap;
                //浏览器重新定位start
                function myFun(result){
                    var cityName = result.name;
                    vm.bdmap.setCenter(cityName);
                    //alert("当前定位城市:"+cityName);
                    vm.cityDingAreaObj.cityName = cityName;

                    //alert("市名得出市code...");
                    //赋值市name
                    let areaObj = {};
                    areaObj = vm.itCityArrBase.find((item)=>{
                        return item.name === vm.cityDingAreaObj.cityName;
                    });
                    if(typeof(areaObj.areaCode)!="undefined"){
                        vm.cityDingAreaObj.cityCode= areaObj.areaCode;
                        //alert(vm.cityDingAreaObj.cityName+vm.cityDingAreaObj.cityCode);
                        vm.houseLocationObj.city = vm.cityDingAreaObj.cityCode;
                        vm.houseLocationObj.cityName = vm.cityDingAreaObj.cityName;
                    }

                }
                var myCity = new BMap.LocalCity();
                myCity.get(myFun);

            },
            changeCommunityBase(aval){
                //赋值街道name
                let areaObj = {};
                areaObj = this.communityList.find((item) => {
                    return item.areaCode === aval;
                });
                this.houseLocationObj.communityName = areaObj.name;
            },
            /*街道查询*/
            getCommunityList(aVal){
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
                        vm.communityList = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询社区信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            changeStreetCpy(aval){
                //清空code

                var vm = this;
                vm.houseLocationObj.street=aval;
                vm.houseLocationObj.streetName="";
                //赋值区域name
                let areaObj = {};
                areaObj = this.itStreetArrCpy.find((item)=>{
                    return item.areaCode === aval;
                });
                vm.houseLocationObj.streetName= areaObj.name;
//                alert(vm.houseLocationObj.streetName);
                //vm.$notify.success(vm.houseLocationObj.streetName+vm.houseLocationObj.street);
                console.log(vm.houseLocationObj.streetName);
            },
            /*街道查询*/
            getStreetListCpy(aVal){
                if(aVal != "" && aVal != null){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
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
            changeRegionCpy(aval){
                //清空code
                this.houseLocationObj.street="";
                this.houseLocationObj.streetName="";
                //赋值区域name
                let areaObj = {};
                areaObj = this.itAreaArrCpy.find((item)=>{
                    return item.areaCode === aval;
                });
                this.houseLocationObj.regionName= areaObj.name;
            },
            /*区域查询*/
            getAreaListCpy(bVal) {
                if(bVal != "" && bVal != null){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrCpy = response.data.result;
                    }).catch(function (error) {

                        vm.$notify({
                            title: '警告:queryNoPage请求失败',
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'warning'
                        });
                    });
                }
            },
            /*所有市区查询*/
            handleAllCity() {
                var vm=this;
                var obj = { };
                obj.level = 2;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url:"cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itCityArrBase = response.data.result;
//                        vm.$notify({
//                            title:vm.itCityArrBase.length+"___个开启城市",
//                            message: '',
//                            type: 'success'
//                        });


                }).catch(function (error) {
                    vm.$notify({
                        title: '警告',
                        message: '页面:查询市区信息失败!baseArea/queryNoPage',
                        type: 'warning'
                    });
                });

            },
            //从所有城市中选择1个
            handleAllchangeCity(aval){
//                alert("开始选城市");
                var vm = this;
                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                vm.houseLocationObj.cityName= areaObj.name;


            },
            custormAnchor(anchorName) {
                // 找到锚点
                let anchorElement = document.getElementById(anchorName);
                // 如果对应id的锚点存在，就跳转到锚点
                if(anchorElement) { anchorElement.scrollIntoView(); }
            },
            jump (index) {
                let jump = document.querySelectorAll('.d_jump')
                // 获取需要滚动的距离
                let total = jump[index].offsetTop
                // Chrome
                document.body.scrollTop = total
                // Firefox
                document.documentElement.scrollTop = total
                // Safari
                window.pageYOffset = total
            },
            //关闭房源详情面板
            closeDialog(done){
                var vm = this;
//                vm.resetForm("hosForm");
                //alert("关闭对话框...");
                this.flagUpMap=false;

                done();
            },

            resetForm(formName) {
                debugger
                var vm = this;
                this.$refs[formName].resetFields();

                this.selectWordName ="";
                this.houseLocationObj = {
                    id: '',
                    locationAlias: '',
                    locationCode: '',
                    province: '',
                    provinceName: '',
                    city: '',
                    cityName: '',
                    region: '',
                    regionName: '',
                    street: '',
                    streetName: '',
                    detailAddress: '',
                    doorNumber: '',
                    longitude: '',
                    latitude: '',
                    community: '',
                    communityName: '',
                    trafficFacilities: '',
                    fileList: null,
                    locRegionList: [
                        {
                            regionBatchCode: '',
                            locationCode: '',
                            locationAlias: '',
                            num: '',
                            longitude: '',
                            latitude: '',
                        }
                    ],
                    hasLocRegion: '',
                }

                this.firstName="";
                this.lastName="";
                this.hosForm.severalLayers="";
                this.hosForm.houseowner.houseownerPhone="";
                this.hosForm.houseowner.houseownerName="";
                this.hosForm.houseowner.houseownerSex="";

                this.hosForm.hasEia = false;
                this.hosForm.hasRegistry = false;

                this.hosForm.hasDischargeSewage = false;
                this.hosForm.hasParking = false;

                this.hosForm.hasPlatform = false;
                this.hosForm.hasElevator = false;

                this.hosForm.hasInstallCrane = false;
                this.hosForm.hasOfficeArea = false;

                this.hosForm.houseYardImage=[
                    {
                        fileCode: '',
                        fileName: '',
                        fileType: '',
                        fileUse: '',
                        transferFileType: ''
                    }
                ];

                this.hosForm.fileList=[
                    {
                        fileCode: '',
                        fileName: '',
                        fileType: '',
                        fileUse: '',
                        transferFileType: ''
                    }
                ];

                this.hosForm.houseProtocol=[
                    {
                        fileCode: '',
                        fileName: '',
                        fileType: '',
                        fileUse: '',
                        transferFileType: ''
                    }
                ];




            },
            layerSet(){
                var vm = this;
                vm.hosForm.severalLayers = 1;
                vm.hosForm.whereLayer = 1;
            },
            layerSetFalse(){
                var vm = this;
                vm.hosForm.severalLayers = '';
                vm.hosForm.whereLayer = '';
            },
            openHouseLocationDialog(){
                var vm = this;
                vm.houseLocationVisible = true;
                vm.$nextTick(() => {
                    vm.$refs.houseLocations.houseLocationCommonDialogFormVisible = true;
                    vm.$refs.houseLocations.handleSizeChange(30);
                })
            },
            openHouseLocationCreateDialog(){
                var vm = this;
                vm.$nextTick(() => {
                    vm.$refs.houseLocationCreate.houseLocationCreateVisible = true;
                    vm.$refs.houseLocationCreate.title = "新增位置";
                })
            },
            openPartTimeDialog(){
                var vm = this;
                vm.partTimeVisible = true;
                vm.$nextTick(() => {
                    vm.$refs.partTimes.partTimeCommonDialogFormVisible = true;
                    vm.$refs.partTimes.handleSizeChange(30);
                })
            },

            openHouseOwnerDialog(){
                var vm = this;
                vm.houseOwnerVisible = true;
                vm.$nextTick(() => {
                    vm.$refs.houseOwners.houseOwnerCommonDialogFormVisible = true;
                    vm.$refs.houseOwners.handleSizeChange(30);
                })
            },

            //关闭兼职面板
            closePartTimePanel(data){
                this.$refs.partTimes.partTimeCommonDialogFormVisible = false;
                this.hosForm.ptCode = data.ptCode;
                this.hosForm.ptName = data.ptName;
            },

            //关闭业主面板
            closeHouseOwnerPanel(data){
                this.$refs.houseOwners.houseOwnerCommonDialogFormVisible = false;
                this.hosForm.ownerCode = data.houseownerCode;
                this.hosForm.houseowner.houseownerCode = data.houseownerCode;
                this.hosForm.houseowner.houseownerName = data.houseownerName;
            },

            //关闭业主面板
            closeHouseLocationPanel(data){
                this.$refs.houseLocations.houseLocationCommonDialogFormVisible = false;
                this.hosForm.locationCode = data.locationCode;
                this.hosForm.houseLocation.locationAlias = data.locationAlias;
            },

            setLocationData(data){
                this.$refs.houseLocationCreate.houseLocationCreateVisible = false;
                this.hosForm.locationCode = data.locationCode;
                this.hosForm.houseLocation.locationAlias = data.locationAlias;
            },

            //添加图片 最多5张
            addFileListImg(subHosObj){
                subHosObj.fileList.push({
                    fileCode: '',
                    fileType: this.fileType
                })

            },

            //删除图片
            deleteFileListImg(subHosObj){
                subHosObj.fileList = [{
                    fileCode: '',
                    fileType: ''
                }]
            },
            selectFileListImage(urls, ind2,subHosObj){
                if (urls.length == 1) {
                    subHosObj.fileList[ind2].fileCode = urls.toString();
                    subHosObj.fileList[ind2].fileType = this.fileType;
                    subHosObj.fileList[ind2].fileUse = "2";
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },

            //新增时移除图片
            removeFileListImg(ind2){
                this.hosForm.fileList[ind2].fileCode = '';
                this.hosForm.fileList[ind2].fileType = '';
            },

            //添加图片 最多5张
            addHouseYardImageImg(){
                this.hosForm.houseYardImage.push({
                    fileCode: '',
                    fileType: this.fileType
                })

            },

            //删除图片
            deleteHouseYardImage(){
                this.hosForm.houseYardImage = [{
                    fileCode: '',
                    fileType: ''
                }]
            },

            selectHouseYardImage(urls, ind2){
                if (urls.length == 1) {
                    this.hosForm.houseYardImage[ind2].fileCode = urls.toString();
                    this.hosForm.houseYardImage[ind2].fileType = this.fileType;
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },

            //新增时移除图片
            removeHouseYardImage(ind2){
                this.hosForm.houseYardImage[ind2].fileCode = '';
                this.hosForm.houseYardImage[ind2].fileType = '';
            },

            //添加图片 最多5张
            addhouseProtocolListImg(){
                this.hosForm.houseProtocol.push({
                    fileCode: '',
                    fileType: this.fileType
                })

            },

            //删除图片
            deletehouseProtocolListImg(){
                this.hosForm.houseProtocol = [{
                    fileCode: '',
                    fileType: ''
                }]
            },

            selecthouseProtocolListImage(urls, ind2){
                if (urls.length == 1) {
                    this.hosForm.houseProtocol[ind2].fileCode = urls.toString();
                    this.hosForm.houseProtocol[ind2].fileType = this.fileType;
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },

            //新增时移除图片
            removehouseProtocolListImg(ind2){
                this.hosForm.houseProtocol[ind2].fileCode = '';
                this.hosForm.houseProtocol[ind2].fileType = '';
            },


            submitForm(formName){
                var vm = this;
                vm.hosForm.houseowner.houseownerPhone = vm.hosForm.hhphone;
                if(vm.hosForm.locationCode==''||vm.hosForm.locationCode==null){
                    vm.$notify.warning("请先录位置,点--下一步(私密信息)--");
                    vm.custormAnchor('1hos');
                    return;
                }


                if(vm.hosForm.houseowner.houseownerPhone==''||vm.hosForm.houseowner.houseownerPhone==null){
                    vm.$notify.warning("请录入房东号码!");
                    return;
                }

                if(vm.hosForm.houseowner.houseownerName==''||vm.hosForm.houseowner.houseownerName==null){
                    vm.$notify.warning("请录入房东姓名!");
                    return;
                }

                if(vm.hosForm.houseowner.houseownerSex==''||vm.hosForm.houseowner.houseownerSex==null){
                    vm.$notify.warning("请选择房东性别!");
                    return;
                }

                var obj = {
                    entity: vm.hosForm
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (vm.hosForm.id == '' || vm.hosForm.id == null) {


//                            alert("看fileList图片数量:"+vm.hosForm.fileList.length);


                            for ( var i = 0; i <vm.hosForm.houseYardImage.length; i++){
                                if(vm.hosForm.houseYardImage[i].fileCode==''){
                                    vm.hosForm.houseYardImage.splice(i,1);
                                    i=-1;
                                }
                            }

                            for ( var i = 0; i <vm.hosForm.fileList.length; i++){
                                if(vm.hosForm.fileList[i].fileCode==''){
                                    vm.hosForm.fileList.splice(i,1);
                                    i=-1;
                                }
                            }


                            for ( var i = 0; i <vm.hosForm.houseProtocol.length; i++){
                                if(vm.hosForm.houseProtocol[i].fileCode==''){
                                    vm.hosForm.houseProtocol.splice(i,1);
                                    i=-1;
                                }
                            }

                            if(vm.hosForm.houseYardImage.length<3){
                                vm.$notify.warning("外立面,至少传3张图片!已经有:"+vm.hosForm.houseYardImage.length+"张");
                                return;
                            }




//                            for(var i=0;i<vm.hosForm.houseProtocol.length;i++){
//                                vm.hosForm.houseProtocol[i].fileUse = "HouseProtocal";
//                                vm.hosForm.fileList.push(vm.hosForm.houseProtocol[i]);
//                            }


                            for(var i=0;i<vm.hosForm.subHosList.length;i++){
                                if(vm.hosForm.subHosList[i].recordFlag==false){
                                    //delete vm.hosForm.subHosList[i];
                                    vm.hosForm.subHosList.splice(i,1);
                                    i=-1;
                                }

                            }


                            if(vm.hosForm.subHosList.length==0){
                                alert("至少有一层(我已录了此层),当前录过层为:"+vm.hosForm.subHosList.length);
                                vm.funcseveralComValue(vm.hosForm.severalLayers,vm.hosForm.severalLayers);
                                return;
                            }


                            //去除多余的添加图片空框
                            for(var i=0;i<vm.hosForm.subHosList.length;i++){
                                for(var j=0;j<vm.hosForm.subHosList[i].fileList.length;j++){
                                    if(vm.hosForm.subHosList[i].fileList[j].fileCode==''){
                                        vm.hosForm.subHosList[i].fileList.splice(j,1);
                                        j=-1;
                                    }
                                }

                            }

                            for(var i=0;i<vm.hosForm.subHosList.length;i++){
                                if(vm.hosForm.subHosList[i].fileList.length<3){
                                    let ceng = i+1;
                                    vm.$notify.warning("第,"+vm.hosForm.subHosList[i].whereLayer+",层内部推广图,至少传3张图片!已经有:"+vm.hosForm.subHosList[i].fileList.length+"张");
                                    return;
                                }
                            }




//                            for(var i=0;i<vm.hosForm.fileList.length;i++){
//                                vm.hosForm.fileList[i].fileUse = "2";
//                            }


                                var options = {
                                method: "POST",
                                data: obj,
                                url: "house/createTwoVersion"
                            };
                            this.$ajax(
                                options
                            ).then(response => {
                                vm.$notify.success(response.data.message);

                                vm.hctvisible = false;//测试暂停关闭

//                                debugger
                                vm.resetForm(formName);
                                vm.$emit('subToFatherList','canclose');
                            }).catch(error => {
                                vm.$notify({
                                    type: 'info',
                                    message: '新增房源失败'
                                });
                            });
                        } else {
                            var options = {
                                method: "POST",
                                data: obj,
                                url: "house/update"
                            };
                            this.$ajax(
                                options
                            ).then(response => {
                                vm.$notify({
                                    message: '操作成功',
                                    type: 'success'
                                });

                                vm.hctvisible = false;
                                vm.resetForm(formName);
                                vm.$emit('closeUpdate');
                            }).catch(error => {
                                vm.$notify({
                                    type: 'info',
                                    message: '修改房源失败'
                                });
                            });
                        }
                    } else {
                        vm.$notify({
                            message: '数据填写不完整',
                            type: 'warning'
                        });
                    }
                })
            },

            edit(houseCode){
                var vm = this;
                var sendObj = {};
                sendObj.houseCode = houseCode;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "house/editByWeb",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.hctvisible = true;
                    vm.hosForm = response.data.result;
                    vm.title = "修改房源";
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!house/edit');
                })
            },

            addHouseownerContacts(){
                var vm = this;
                if (vm.hosForm.houseowner.houseownerName == '') {
                    vm.$notify({
                        message: '请先填写业主信息，再添加更多联系人!',
                        title: '操作提示'
                    });
                    return
                } else {
                    vm.hosForm.houseownerContacts.push({
                        contactPhone: null,
                        contactName: null,
                        contactSex: null,
                    });
                }

            },

            clearHouseownerContacts(){
                this.hosForm.houseownerContacts = [];
            },

            closeHouseLocation(){
                this.$refs.houseLocations.houseLocationCommonDialogFormVisible = false;
            },

            closeHouseOwner(){
                this.$refs.houseOwners.houseOwnerCommonDialogFormVisible = false;
            },

            closePartTime(){
                this.$refs.partTimes.partTimeCommonDialogFormVisible = false;
            }
        }

    }

</script>


<style scoped>
    .el-input {
        width: 200px;
    }

    .img-div1 {
        float: left;
        border: 1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }

    .file_button {
        margin-top: 80px;
        margin-left: 50px;
    }

    .house-div {
        overflow-y: auto;
        overflow-x: hidden;
        border-top: 1px solid gainsboro;
    }

    .form-tip {
        font-weight: bold;
        text-align: center;
        font-size: 25px;
        margin-bottom: 60px;
        color: white;
        background: #409EFF;
        height: 50px;
        line-height: 50px;
    }

    .img-tip {
        padding-left: 20px;
        font-weight: bolder;
        line-height: 30px;
        font-size: 16px;
        margin-bottom: 10px;
        border-bottom: 1px solid #d7d7d7;
        background: #409EFF;
        color: white;
        border-radius: 4px;
    }

    .img-tip-two {
        padding-left: 20px;
        font-weight: bolder;
        line-height: 30px;
        font-size: 16px;
        margin-bottom: 10px;
        border-bottom: 1px solid #d7d7d7;
        background: white;
        color: black;
        border-radius: 4px;
    }

    .word_demo_dialogtitle_demo{
        margin:0px 0px 0px 0px;
        /*border;*/
        padding: 0px 0px 0px 0px;
        height: auto;
        width: auto;

    }

    .word_title{
        margin:0px 0px 0px 10px;
        /*border;*/
        padding: 0px 0px 0px 0px;
        height: auto;
        width: auto;
        font-size: 20px;
        font-weight:600;
    }

    .btn_title_1h{
        margin:0px 0px 0px 280px;
        /*border;*/
        /*padding: 0px 0px 0px 0px;*/
        height: auto;
        width: auto;
    }

    .btn_title_2h{
        margin:0px 0px 0px 50px;
        /*border;*/
        /*padding: 0px 0px 0px 0px;*/
        height: auto;
        width: auto;
    }
    .btn_title_3h{
        margin:0px 0px 0px 50px;
        /*border;*/
        /*padding: 0px 0px 0px 0px;*/
        height: auto;
        width: auto;
    }

    .totalBlockLoc{
        margin:0px 0px 10px 0px;
        /*border:2px solid red;*/
        padding: 0px 0px 0px 0px;
        height: 530px;
        width: 100%;
        display: flex;
    }

    .leftLoc{
        margin:10px 0px 10px 0px;
        /*border:2px solid blue;*/
        padding: 0px 0px 0px 0px;
        height: 500px;
        width: 50%;
    }

    .rightLoc{
        margin:10px 10px 10px 0px;
        /*border:2px solid #ff8823;*/
        padding: 0px 0px 0px 0px;
        height: 500px;
        width: 50%;
    }

    .next_step_total{
        margin:0px 0px 0px 0px;
        border-top:2px solid gainsboro;
        padding: 0px 0px 0px 0px;
        height: 55px;
        width: 100%;
        /*display: flex;*/
    }

    .next_step_right{
        margin:10px 0px 0px 0px;
        /*border:2px solid blue;*/
        padding: 0px 0px 0px 0px;
        height: 55px;
        width: 555px;
        float: right;
        /*display: flex;*/
    }

    .next_step_btn{
        margin:10px 20px 10px 0px;
        /*border:2px solid red;*/
        /*padding: 0px 0px 0px 0px;*/
        float: right;
        height: 30px;
        width: 180px;
    }

    .secret_build_total{
        margin:0px 0px 0px 0px;
        border-bottom:0.5px solid black;
        padding: 0px 0px 0px 0px;
        height: 215px;
        width: 100%;
        display: flex;
    }

    .secret_build_left{
        margin:10px 0px 10px 0px;
        /*border:2px solid blue;*/
        padding: 0px 50px 0px 0px;
        height: 230px;
        width: 50%;
    }

    .secret_build_right{
        margin:10px 0px 10px 20px;
        /*border:2px solid #ff8823;*/
        padding: 0px 20px 0px 0px;
        height: 230px;
        width: 50%;
    }


    .secret_tupic_total{
        margin:0px 0px 0px 0px;
        border:2px solid greenyellow;
        padding: 0px 0px 0px 0px;
        height: 265px;
        width: 100%;
        display: flex;
    }

    .secret_tupic_left{
        margin:10px 0px 10px 0px;
        border:2px solid blue;
        padding: 0px 50px 0px 0px;
        height: 230px;
        width: 50%;
    }

    .secret_tupic_right{
        margin:10px 0px 10px 20px;
        border:2px solid #ff8823;
        padding: 0px 20px 0px 0px;
        height: 230px;
        width: 50%;
    }

    .base_floor_total{
        margin:20px 0px 0px 0px;
        border:1px solid #8e8b87;
        padding: 0px 0px 0px 0px;
        height: auto;
        width: 100%;
    }
    .base_floor_line{
        margin:10px 0px 10px 10px;
        border:0px solid #ff8823;
        padding: 0px 10px 0px 0px;
        height: 100px;
        width: 100%;
        display: flex;
    }

</style>
<style>
    .el-collapse-item__header {
        height: 36px;
        line-height: 36px;
        cursor: pointer;
        font-size: 15px;
        font-weight: 500;
        -webkit-transition: border-bottom-color .3s;
        transition: border-bottom-color .3s;
        background: #ffffff;
        font-weight: bold;
        color: black;
        padding-left: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        text-align: left;
    }

    .el-collapse-item__arrow{
        float: left;
        text-align: left;
    }

    .ant-collapse > .ant-collapse-item > .ant-collapse-header {
        height: 38px;
        line-height: 38px;
        padding-left: 32px;
        color: rgba(0, 0, 0, 0.85);
        cursor: pointer;
        position: relative;
        transition: all .3s;
        border-bottom:2px solid #409EFF;
    }

    .ant-collapse {
        background-color: #ffffff;
        border-radius: 4px;
        border: 1px solid #d9d9d9;
        border-bottom: 0;
    }
</style>
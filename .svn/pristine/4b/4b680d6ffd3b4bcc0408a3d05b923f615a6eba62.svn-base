<template>
    <el-dialog  :close-on-click-modal="false" :style="{height: fullHeight +140 +'px'}" style="margin-top: 10px" top="10px;"
               width="70%"
               :title="title"
               :before-close="closeDialog"
               :visible.sync="visible"
               append-to-body>
        <div :style="{height: fullHeight -180 +'px'}" class="house-div">
            <el-form :model="hosForm" ref="hosForm" :rules="rules" label-width="110px" size="mini">
                <el-collapse v-model="activeNames">
                    <el-collapse-item title="位置信息" name="1">
                        <div  style="margin-top: 50px">
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="房源位置：" prop="locationAlias">
                                        <el-input disabled style="width: 300px;"
                                                  v-model="hosForm.houseLocation.locationAlias"
                                                  placeholder="请选择房源位置">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="openHouseLocationDialog()">点击选择
                                            </el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item labelWidth="325px" label="如果没有你想要的信息，请点击右侧新增：">
                                        <el-button size="mini" type="primary" icon="el-icon-search"
                                                   @click="openHouseLocationCreateDialog()">点击选择
                                        </el-button>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </div>
                    </el-collapse-item>
                    <el-collapse-item title="房源信息" name="2">
                        <div style="margin-top: 50px">
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                                    <el-form-item label="推广标题：" prop="houseName">
                                        <el-input type="textarea" :rows="2"
                                                  placeholder="例:新空出单层1000㎡仓库，价格美丽"
                                                  v-model="hosForm.houseName"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                    <el-form-item label="房源用途：" prop="housePurpose">
                                        <el-radio-group v-model="hosForm.housePurpose" size="mini">
                                            <el-radio-button :label="1">仓储</el-radio-button>
                                            <el-radio-button :label="2">生产</el-radio-button>
                                            <el-radio-button :label="3">仓储和生产</el-radio-button>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                    <el-form-item label="房源类型：" prop="houseStyle">
                                        <el-radio-group v-model="hosForm.houseStyle" size="mini">
                                            <el-radio-button :label="1">普通仓库厂房</el-radio-button>
                                            <el-radio-button :label="2">冷链仓库</el-radio-button>
                                            <el-radio-button :label="3">高台仓库</el-radio-button>
                                            <el-radio-button :label="4">危险品库</el-radio-button>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否独栋：" prop="hasAloneBuilding">
                                        <el-radio-group style="width: 200px"
                                                        v-model="hosForm.hasAloneBuilding">
                                            <el-radio :label=true>
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="所在栋：" prop="whereBuilding">
                                        <el-input v-model="hosForm.whereBuilding"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否单层：" prop="hasMonolayer">
                                        <el-radio-group style="width: 200px"
                                                        v-model="hosForm.hasMonolayer">
                                            <el-radio @change="layerSet()" :label=true>
                                                是
                                            </el-radio>
                                            <el-radio @change="layerSetFalse()" :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="共几层：" prop="severalLayers">
                                        <el-input v-model="hosForm.severalLayers"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="所在层：" prop="whereLayer">
                                        <el-input v-model="hosForm.whereLayer"/>
                                    </el-form-item>
                                </el-col>
                            </el-row>


                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否可环评：" prop="hasEia">
                                        <el-radio-group v-model="hosForm.hasEia">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否可分割：" prop="hasCut">
                                        <el-radio-group v-model="hosForm.hasCut">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否有产证：" prop="hasCertificate">
                                        <el-radio-group v-model="hosForm.hasCertificate">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否可注册：" prop="hasRegistry">
                                        <el-radio-group v-model="hosForm.hasRegistry">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否可短租：" prop="hasShortLease">
                                        <el-radio-group v-model="hosForm.hasShortLease">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="有无停车位：" prop="hasParking">
                                        <el-radio-group v-model="hosForm.hasParking">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否有货台：" prop="hasPlatform">
                                        <el-radio-group v-model="hosForm.hasPlatform">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否有货梯：" prop="hasElevator">
                                        <el-radio-group v-model="hosForm.hasElevator">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="是否有行车：" prop="hasInstallCrane">
                                        <el-radio-group v-model="hosForm.hasInstallCrane">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="有无卫生间：" prop="hasBathroom">
                                        <el-radio-group v-model="hosForm.hasBathroom">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="有无办公区：" prop="hasOfficeArea">
                                        <el-radio-group v-model="hosForm.hasOfficeArea">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="有无排污证：" prop="hasDischargeSewage">
                                        <el-radio-group v-model="hosForm.hasDischargeSewage">
                                            <el-radio :label="true">
                                                是
                                            </el-radio>
                                            <el-radio :label="false">
                                                否
                                            </el-radio>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>

                            </el-row>

                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
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
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="所属兼职：" prop="ptName">
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
                        </div>
                    </el-collapse-item>
                    <el-collapse-item title="房源参数" name="3">
                        <div style="margin-top: 50px">
                            <el-row >
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="楼层面积：" prop="layerArea">
                                        <el-input-number style="width: 200px" size="mini"
                                                         v-model="hosForm.layerArea" :min="1"
                                                         controls-position="right"/>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="可租面积：" prop="area">
                                        <el-input-number style="width: 200px" size="mini"
                                                         v-model="hosForm.area" :min="1"
                                                         controls-position="right"/>
                                    </el-form-item>
                                </el-col>

                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="最大电量：" prop="maxElectric">
                                        <el-input style="width: 200px" size="mini"
                                                  v-model="hosForm.maxElectric"
                                                  controls-position="right">
                                            <template slot="append">KVA</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>

                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="房源价格：" prop="price">
                                        <el-input v-model="hosForm.price" style="width: 200px">
                                            <el-select v-model="hosForm.unit" prop="unit"
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
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="层高：" prop="layerHeight">
                                        <el-input-number v-model="hosForm.layerHeight" :min="1"
                                                         controls-position="right"
                                                         style="width: 200px">
                                        </el-input-number>
                                        米
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="物业费：" prop="propertyFee">
                                        <el-input v-model="hosForm.propertyFee"
                                                  style="width: 200px">
                                            <template slot="append">元/㎡/月</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="最短租期：" prop="lessLease">
                                        <el-input v-model="hosForm.lessLease" style="width: 200px">
                                            <template slot="append">月</template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="最长租期：" prop="moreLease">
                                        <el-input v-model="hosForm.moreLease" style="width: 200px">
                                            <template slot="append">
                                                月
                                            </template>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="可入住时间：" prop="enterTime">
                                        <el-date-picker
                                                size="mini"
                                                style="width: 200px"
                                                v-model="hosForm.enterTime"
                                                type="date"
                                                placeholder="可入住时间">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                                    <el-form-item label="承重：" prop="bearing">
                                        <el-input style="width:200px"
                                                  v-model="hosForm.bearing"></el-input>
                                    </el-form-item>
                                </el-col>

                            </el-row>
                            <el-row>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                    <el-form-item label="消防等级：" prop="fireLevel">
                                        <el-radio-group v-model="hosForm.fireLevel" size="mini">
                                            <el-radio-button :label="1">甲类</el-radio-button>
                                            <el-radio-button :label="2">乙类</el-radio-button>
                                            <el-radio-button :label="3">丙类</el-radio-button>
                                            <el-radio-button :label="4">丁类</el-radio-button>
                                            <el-radio-button :label="5">戊类</el-radio-button>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                                    <el-form-item label="房源结构：" prop="houseStructure">
                                        <el-radio-group v-model="hosForm.houseStructure"
                                                        size="mini">
                                            <el-radio-button :label="1">钢结构</el-radio-button>
                                            <el-radio-button :label="2">框架</el-radio-button>
                                            <el-radio-button :label="3">钢混</el-radio-button>
                                            <el-radio-button :label="4">砖混</el-radio-button>
                                        </el-radio-group>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                                    <el-form-item label="行业限制：" prop="industryRestriction">
                                        <el-input v-model="hosForm.industryRestriction"
                                                  type="textarea"/>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                                    <el-form-item label="适合行业：" prop="forInsdustry">
                                        <el-input v-model="hosForm.forInsdustry" type="textarea"/>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :xs="23" :sm="23" :md="23" :lg="23" :xl="23">
                                    <el-form-item label="房源特色：" prop="features">
                                        <el-input v-model="hosForm.features" type="textarea"/>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </div>
                    </el-collapse-item>
                    <el-collapse-item title="影像资料" name="4">
                        <div style="margin-top: 50px">
                            <el-row>
                                <div class="img-tip">
                                    房源内部推广图
                                </div>
                                <p>
                                    <el-button size="mini" style="color: #5bc0de"
                                               @click="addFileListImg()">添加图片
                                    </el-button>
                                    <el-button size="mini" style="color: #d58512"
                                               @click="deleteFileListImg()">清空图片
                                    </el-button>
                                </p>
                                <el-form-item labelWidth="0px" prop="fileList">
                                    <div style="display: inline-block;margin-bottom: 20px"
                                         v-if="fileType == 'image'" class="img-div1" v-for="(fileItem,fileIndex) in
                        hosForm.fileList" :key="fileIndex">
                                        <i @click="removeFileListImg(fileIndex)"
                                           class="el-icon-circle-close" style="float: right;"
                                           v-if="fileItem.fileCode"></i>
                                        <img height="100%" width="100%"
                                             :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                                             v-if="fileItem.fileCode">
                                        <file @selectFile="selectFileListImage($event,fileIndex)"
                                              class="file_button"
                                              v-if="!fileItem.fileCode"></file>
                                    </div>
                                </el-form-item>
                            </el-row>
                            <el-row>
                                <div class="img-tip">
                                    房源外部图片(可上传3~5张，隐私图自己看)
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
                        </div>
                    </el-collapse-item>
                    <el-collapse-item title="业主信息" name="5">
                        <div style="margin-top: 50px">
                            <!--<el-row >-->
                                <!--<el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">-->
                                    <!--<el-form-item label="是否要公开显示业主号码 ？" labelWidth="200px">-->
                                        <!--<el-radio-group v-model="hosForm.openFlag">-->
                                            <!--<el-radio-button :label="true">-->
                                                <!--公开-->
                                            <!--</el-radio-button>-->
                                            <!--<el-radio-button :label="false">-->
                                                <!--隐藏-->
                                            <!--</el-radio-button>-->
                                        <!--</el-radio-group>-->
                                    <!--</el-form-item>-->
                                <!--</el-col>-->
                                <!--<el-col style="font-size: 14px;color: red;font-weight: bold"-->
                                        <!--:xs="12" :sm="12" :md="12" :lg="12" :xl="12">-->
                                    <!--<p>1、选择隐藏，业主号码在任何状况下只有我本人可见 ！</p>-->
                                    <!--<p>2、选择公开，业主号码将展示给与我共享信息的部门用户 ！</p>-->
                                <!--</el-col>-->
                            <!--</el-row>-->
                            <el-row>
                                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                                    <el-form-item label="联系人：" prop="houseownerName">
                                        <el-input disabled
                                                  v-model="hosForm.houseowner.houseownerName"
                                                  placeholder="请选择业主">
                                            <el-button slot="append" icon="el-icon-search"
                                                       @click="openHouseOwnerDialog()">点击选择
                                            </el-button>
                                        </el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <div class="img-tip">
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
                            <el-row style="text-align: right;margin-bottom: 30px">
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
                        </div>
                    </el-collapse-item>
                </el-collapse>
            </el-form>

        </div>
        <div slot="footer" class="dialog-footer" style="text-align: center">
            <el-button size="mini" type="primary"
                       @click="resetForm('hosForm')">重置
            </el-button>
            <el-button size="mini" type="success" @click="submitForm('hosForm')">提交
            </el-button>
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

    export default{
        components: {
            ElRow,
            partTime,
            file,
            houseOwner,
            houseLocation,
            hosLocationCreate
        },

        data(){
            return {
                houseLocationVisible: false,
                title: "",
                rules: {

                    houseName: [
                        {required: true, message: '请填写房源标题', trigger: 'blur'},
                    ],
                    housePurpose: [
                        {required: true, message: '请填写房源用途', trigger: 'blur'},
                    ],
                    houseStyle: [
                        {required: true, message: '请输入房源类型', trigger: 'blur'},
                    ],
                    hasAloneBuilding: [
                        {required: true, message: '请选择是否独栋', trigger: 'blur'},
                    ],
                    whereBuilding: [
                        {required: true, message: '请填写所在栋', trigger: 'blur'},
                    ],
                    hasMonolayer: [
                        {required: true, message: '请填写是否单层', trigger: 'blur'},
                    ],
                    severalLayers: [
                        {required: true, message: '请填写共有几层', trigger: 'blur'},
                    ],
                    whereLayer: [
                        {required: true, message: '请填写所在层', trigger: 'blur'},
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
                    houseFrom: [
                        {required: true, message: '请输入房源来源', trigger: 'blur'},
                    ],
                    layerArea: [
                        {required: true, message: '请输入层面积', trigger: 'blur'},
                    ],
                    area: [
                        {required: true, message: '请输入层面积', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                    ],
                    layerHeight: [
                        {required: true, message: '请输入层高', trigger: 'blur'},
                    ],
                    propertyFee: [
                        {required: true, message: '请输入物业费', trigger: 'blur'},
                    ],
                    lessLease: [
                        {required: true, message: '请输入最短租期', trigger: 'blur'},
                    ],
                    moreLease: [
                        {required: true, message: '请输入最长租期', trigger: 'blur'},
                    ],
                    enterTime: [
                        {required: true, message: '请输入入住时间', trigger: 'blur'},
                    ],
                     bearing:[
                     {required: true, message: '请输入承重', trigger: 'blur'},
                     ],
                    fireLevel: [
                        {required: true, message: '请输入消防等级', trigger: 'blur'},
                    ],
                    houseStructure: [
                        {required: true, message: '请输入房源结构', trigger: 'blur'},
                    ],
                    industryRestriction: [
                        {required: true, message: '请输入行业限制', trigger: 'blur'},
                    ],
                    forInsdustry: [
                        {required: true, message: '请输入适合行业', trigger: 'blur'},
                    ],
                    features: [
                        {required: true, message: '请输入房源特色', trigger: 'blur'},
                    ],
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
                activeNames: ['1', '2', '3', '4', '5'],
                fileType: 'image',
                partTimeVisible: false,
                houseOwnerVisible: false,
                fullHeight: document.documentElement.clientHeight,
                visible: false,
                hosForm: {
                    id: '',
                    locationCode: '',
                    divideType: '',
                    divideRatio:'',
                    divideCash:'',
                    houseCode: '',
                    houseName: '',//房源标题
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
                    houseProtocol: [
                        {
                            fileCode: '',
                            fileName: '',
                            fileType: '',
                            fileUse: '',
                            transferFileType: ''
                        }
                    ],
                    propertyFee: '',
                    serveralLayers: '',
                    whereBuilding: '',
                    hasAloneBuilding: '',
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


                    features: '',//房源特色
                    forInsdustry: '',//适合行业
                    houseowner: {
                        houseownerTypeName: '',
                        houseownerName: '',
                        houseownerCode: ''
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
                    value: '线下扫街',
                    label: '线下扫街'
                }, {
                    value: '400电话委托',
                    label: '400电话委托'
                }, {
                    value: '网络搜索',
                    label: '网络搜索'
                }, {
                    value: '赶集付费',
                    label: '赶集付费'
                }, {
                    value: '兼职推荐',
                    label: '兼职推荐'
                }, {
                    value: '官网委托',
                    label: '官网委托'
                }, {
                    value: '兼职推荐',
                    label: '兼职推荐'
                }, {
                    value: '网络搜索',
                    label: '网络搜索'
                }, {
                    value: '58付费',
                    label: '58付费'
                }, {
                    value: '官网预约',
                    label: '官网预约'
                }, {
                    value: '微信/QQ群',
                    label: '微信/QQ群'
                }, {
                    value: '户外广告',
                    label: '户外广告'
                }, {
                    value: '名单拨打',
                    label: '名单拨打'
                }, {
                    value: '400客服',
                    label: '400客服'
                }],
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

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        methods: {
            //关闭房源详情面板
            closeDialog(done){
                var vm = this;
                vm.resetForm("hosForm");
                done();
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
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
                debugger
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
            addFileListImg(){
                this.hosForm.fileList.push({
                    fileCode: '',
                    fileType: this.fileType,
                    fileUse:'2'
                })

            },

            //删除图片
            deleteFileListImg(){
                this.hosForm.fileList = [{
                    fileCode: '',
                    fileType: ''
                }]
            },
            selectFileListImage(urls, ind2){
                if (urls.length == 1) {
                    this.hosForm.fileList[ind2].fileCode = urls.toString();
                    this.hosForm.fileList[ind2].fileType = this.fileType;
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
                    fileType: this.fileType,
                    fileUse:'HouseYard'
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
                    fileType: this.fileType,
                    fileUse:'HouseProtocal'
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
                var obj = {
                    entity: vm.hosForm
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        if (vm.hosForm.id == '' || vm.hosForm.id == null) {




                            var options = {
                                method: "POST",
                                data: obj,
                                url: "house/create"
                            };
                            this.$ajax(
                                options
                            ).then(response => {
                                vm.$notify({
                                    message: '操作成功',
                                    type: 'success'
                                });

                                vm.visible = false;
                                vm.resetForm(formName);
                                vm.$emit('changeSuccess');
                            }).catch(error => {
                                vm.$notify({
                                    type: 'info',
                                    message: '新增房源失败'
                                });
                            });
                        } else {

                            for ( var i = 0; i <vm.hosForm.houseYardImage.length; i++){
                                if(vm.hosForm.houseYardImage[i].fileCode==''){
                                    vm.hosForm.houseYardImage.splice(i,1);
                                    i=-1;
                                }
                            }

                            debugger
                            for ( var i = 0; i <vm.hosForm.fileList.length; i++){
                                if(vm.hosForm.fileList[i].fileCode=='' || vm.hosForm.fileList[i].fileCode ==null){
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

                                vm.visible = false;
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
                    vm.visible = true;
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
        background: #409EFF;
        font-weight: bold;
        color: white;
        padding-left: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
</style>
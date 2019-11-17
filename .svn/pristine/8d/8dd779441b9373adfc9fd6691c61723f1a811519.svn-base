<template>



    <el-dialog  :close-on-click-modal="false" :style="{height: fullHeight +140 +'px'}" style="margin-top: 10px" top="10px;"
               width="65%"
               :title="title"
               :before-close = "closeDialog"
               :visible.sync="houseLocationCreateVisible"
               append-to-body>




        <div :style="{height: fullHeight -180 +'px'}" class="house-div">
            <el-form :model="houseLocation" ref="houseLocation" :rules="rules" label-width="100px"
                     size="mini">
                <el-row>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属省份：" prop="province">
                            <el-select id="provinceName" v-model="houseLocation.province"
                                       @visible-change="handNeedProvince"
                                       @change="changeProvBase"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in provinceList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属城市：" prop="city">
                            <el-select id="cityName" v-model="houseLocation.city"
                                       @focus="getCitieList(houseLocation.province)"
                                       @change="changeCityBase" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in cityList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属区域：" prop="region">
                            <el-select id="regionName" v-model="houseLocation.region"
                                       @focus="getRegionList(houseLocation.city)"
                                       @change="changeRegionBase" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in regionList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属街道：" prop="street">
                            <el-select id="streetName" v-model="houseLocation.street"
                                       @focus="getStreetList(houseLocation.region)"
                                       @change="changeStreetBase" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in streetList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属社区：" prop="community">
                            <el-select id="communityName" v-model="houseLocation.community"
                                       @focus="getCommunityList(houseLocation.street)"
                                       @change="changeCommunityBase"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in communityList"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <el-form-item label="详细地址：" prop="detailAddress">
                            <el-input type="textarea"
                                      v-model="houseLocation.detailAddress"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="经度：" prop="longitude">
                            <el-input disabled v-model="houseLocation.longitude"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="纬度：" prop="latitude">
                            <el-input disabled v-model="houseLocation.latitude"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-button size="small" type="primary" @click="openHouseLocationDialog()">坐标选择</el-button>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="地址别名：" prop="locationAlias">
                            <el-input v-model="houseLocation.locationAlias"/>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form-item label="交通配套：" prop="trafficFacilities">
                            <el-input v-model="houseLocation.trafficFacilities"/>
                        </el-form-item>
                    </el-col>
                </el-row>

                <!--<hos-loc-poi-and-region-add ref="houseLocations" v-if="houseLocationVisible"></hos-loc-poi-and-region-add>-->

                <!--<el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>-->

                <el-dialog  :close-on-click-modal="false"
                        :visible.sync="dialogVisible"
                        style="margin-top: 20px;"
                        top="10px"
                        width="60%"
                        :before-close="handleClose"
                        append-to-body>
                    <hos-loc-poi-and-region-add  ref="houseLocations" v-if="houseLocationVisible" @changeHos="serchHosClick"></hos-loc-poi-and-region-add>

                </el-dialog>

                <!--<v-modal title="地图"-->
                         <!--:visible.sync="dialogVisible">-->
                    <!--<p>对话框的内容</p>-->
                    <!--<hos-loc-poi-and-region-add  ref="houseLocations" v-if="houseLocationVisible" @changeHos="serchHosClick"></hos-loc-poi-and-region-add>-->
                <!--</v-modal>-->

                <el-row style="min-height: 300px">
                    <div class="img-tip">
                        门口视频展示（他人不可见）
                    </div>
                    <p>
                        <el-button size="mini" style="color: #5bc0de"
                                   @click="addVideo()">添加视频
                        </el-button>
                        <el-button size="mini" style="color: #d58512"
                                   @click="deleteVideo()">清空视频
                        </el-button>
                    </p>
                    <div style="display: inline-block;margin-bottom: 20px;"
                         class="img-div1" v-for="(fileItem,fileIndex) in videoList"
                         :key="fileIndex">
                        <i @click="removeVideo(fileIndex)"
                           class="el-icon-circle-close" style="float: right;"
                           v-if="fileItem.fileCode"></i>
                        <img height="100%" width="100%"
                             :src=" hkpBaseUrl + 'file/read/' + fileItem.fileCode"
                             v-if="fileItem.fileCode">
                        <file @selectFile="selectVideo($event,fileIndex)"
                              class="file_button"
                              v-if="!fileItem.fileCode"></file>
                    </div>
                </el-row>

                <el-row>
                    <div class="img-tip">
                        门口照片展示（3张，他人不可见）
                    </div>
                    <p>
                        <el-button size="mini" style="color: #5bc0de"
                                   @click="addFileListImg()">添加图片
                        </el-button>
                        <el-button size="mini" style="color: #d58512"
                                   @click="deleteFileListImg()">清空图片
                        </el-button>
                    </p>
                    <div style="display: inline-block;margin-bottom: 20px"
                         v-if="fileType == 'image'" class="img-div1" v-for="(fileItem,fileIndex) in
                        imgList" :key="fileIndex">
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
                </el-row>
            </el-form>
        </div>
        <div slot="footer" class="dialog-footer" style="text-align: center">
            <el-button size="mini" type="primary"
                       @click="resetForm('houseLocation')">重置
            </el-button>
            <el-button size="mini" type="success" @click="submitForm('houseLocation')">提交
            </el-button>
        </div>
    </el-dialog>

</template>


<script>

    import file from "../../common/components/file.vue";
    import hosLocPoiAndRegionAdd from "./hosLocPoiAndRegionAdd.vue"

    export default{
        components: {
            file,
            hosLocPoiAndRegionAdd
        },

        data(){
            return {
                leftDown:null,
                rightUp:null,
                dialogVisible: false,
                houseLocationVisible: false,
                title: "新增位置",
                provinceList: [
                    {
                        areaCode: '',
                        name: '',
                    }
                ],
                cityList: [
                    {
                        areaCode: '',
                        name: '',
                    }],
                regionList: [
                    {
                        areaCode: '',
                        name: '',
                    }
                ],
                streetList: [
                    {
                        areaCode: '',
                        name: '',
                    }
                ],
                communityList: [
                    {
                        areaCode: '',
                        name: '',
                    }
                ],
                videoList: [
                ],
                imgList: [
                ],
                houseLocationCreateVisible: false,
                title: "",
                rules: {
                    province: [
                        {required: true, message: '请选择省', trigger: 'blur'},
                    ],
                    city: [
                        {required: true, message: '请选择市', trigger: 'blur'},
                    ],
                    region: [
                        {required: true, message: '请选择区域', trigger: 'blur'},
                    ],
                    street: [
                        {required: true, message: '请选择街道', trigger: 'blur'},
                    ],
                    detailAddress: [
                        {required: true, message: '请输入详细地址', trigger: 'blur'},
                    ],
                    /*longitude:[
                     {required: true, message: '请输入坐标位置', trigger: 'blur'},
                     ],
                     latitude:[
                     {required: true, message: '请输入坐标位置', trigger: 'blur'},
                     ],*/
                    locationAlias: [
                        {required: true, message: '请输入地址别名', trigger: 'blur'},
                    ],
                    trafficFacilities: [
                        {required: true, message: '请输入交通配套信息', trigger: 'blur'},
                    ],
                    videoList: [
                        {required: true, message: '请上传视频', trigger: 'blur'},
                    ],
                    imgList: [
                        {required: true, message: '请上传图片', trigger: 'blur'},
                    ],
                },
                areaList:[
                    {
                        areaCode: '',
                        name: '',
                    }
                ],
                fileType: 'image',
                fullHeight: document.documentElement.clientHeight,
                houseLocation: {
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
                    communityName: '',
                    communityName: '',
                    trafficFacilities: '',
                    fileList: [
                        {
                            fileCode: '',
                            fileType: '',
                        }
                    ],
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
            closeDialog(done){
                var vm = this;
                vm.resetForm("houseLocation");
                done();
            },
            //自定义查询
            serchHosClick(obj){
                var vm = this;
                let poiObjregionList = {};
                poiObjregionList = obj;

                vm.leftDown = poiObjregionList.leftDown;
                vm.rightUp = poiObjregionList.rightUp;
                vm.houseLocation.locRegionList = poiObjregionList.regionList;
                vm.houseLocation.longitude = poiObjregionList.poiObj.longitude;
                vm.houseLocation.latitude = poiObjregionList.poiObj.latitude;
                vm.dialogVisible = false;
                vm.houseLocationVisible = false;
            },
            handleClose(done) {
                var vm = this;
                this.$confirm('确认关闭？')
                    .then(_ => {
                        vm.houseLocationVisible = false;
                        done();

                    })
                    .catch(_ => {});
            },
            openHouseLocationDialog(){
                var vm = this;
                if(vm.houseLocation.province ==null || vm.houseLocation.province ==""){
                    alert("请选择上面省市区街道!");
                    return false;
                }

                if(vm.houseLocation.detailAddress ==null || vm.houseLocation.detailAddress ==""){
                    alert("请填入详细地址!");
                    return false;
                }

                let areaObj = {

                    provinceCode:null,
                    cityCode:null,
                    regionCode:null,
                    streetCode:null,

                    provinceName:null,
                    cityName:null,
                    regionName:null,
                    streetName:null,

                };

                areaObj.provinceCode = vm.houseLocation.province;
                areaObj.cityCode = vm.houseLocation.city;
                areaObj.regionCode = vm.houseLocation.region;

                vm.houseLocationVisible = true;
                vm.dialogVisible = true;
                vm.$nextTick(() => {
                    vm.$refs.houseLocations.hkpRegionCityAddList = areaObj;
                    vm.$refs.houseLocations.houseLocationObj = vm.houseLocation;
                    vm.$refs.houseLocations.selectWordName = vm.houseLocation.detailAddress;
                    vm.$refs.houseLocations.remoteMethod(vm.houseLocation.detailAddress);
                })
            },
            addVideo(){
                this.videoList.push({
                    fileCode: '',
                    fileType: 'video'
                })
            },

            deleteVideo(){
                this.videoList = []
            },

            removeVideo(index){
                this.videoList[index].fileCode = '';
                this.videoList[index].fileType = '';
            },

            selectVideo(urls, index){
                if (urls.length == 1) {
                    this.videoList[index].fileCode = urls.toString();
                    this.videoList[index].fileType = "video";
                } else {
                    vm.$notify({
                        message: '仅支持上传一个视频',
                        title: '操作提示'
                    });
                    return;
                }
            },

            //添加图片 最多5张
            addFileListImg(){
                this.imgList.push({
                    fileCode: '',
                    fileType: this.fileType
                })

            },

            //删除图片
            deleteFileListImg(){
                this.imgList = []
            },

            selectFileListImage(urls, index){
                if (urls.length == 1) {
                    this.imgList[index].fileCode = urls.toString();
                    this.imgList[index].fileType = this.fileType;
                } else {
                    vm.$notify({
                        message: '仅支持上传一张照片',
                        title: '操作提示'
                    });
                    return;
                }
            },
            //新增时移除图片
            removeFileListImg(index){
                this.imgList[index].fileCode = '';
                this.imgList[index].fileType = '';
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            submitForm(formName){

                var vm = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var isSubmit = true;
                        vm.imgList.forEach(img=>{
                            if ( img.fileCode==''){
                                vm.$notify({
                                    message: '请上传图片',
                                    title: '操作提示'
                                });
                                isSubmit = false;
                            }
                        });

                        vm.videoList.forEach(video=>{
                            if ( video.fileCode==''){
                                vm.$notify({
                                    message: '请上传视频',
                                    title: '操作提示'
                                });
                                isSubmit = false;
                            }
                        });

                      if (isSubmit){
                          vm.houseLocation.fileList = vm.imgList.concat(vm.videoList);
                          var obj = {
                              entity: vm.houseLocation,
                              leftDown:vm.leftDown,
                              rightUp:vm.rightUp
                          }

                          var options = {
                              method: "POST",
                              data: obj,
                              url: "houseLocation/create"
                          };
                          this.$ajax(
                              options
                          ).then(response => {
                              vm.$notify({
                                  message: '操作成功',
                                  type: 'success'
                              });
                              var data = response.data.result;
                              vm.houseLocationCreateVisible = false;
                              vm.resetForm(formName);
                              vm.$emit('setHouseLocationData', data);
                          }).catch(error => {
                              vm.$notify({
                                  type: 'info',
                                  message: '新增位置失败'
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

            changeStreet(aval){
                this.houseLocation.community = "";
            },

            changeRegion(aval){
                //清空code
                this.houseLocation.street = "";
                this.houseLocation.community = "";
            },
            changeCity(aval){
                //清空code
                this.houseLocation.region = "";
                this.houseLocation.street = "";
                this.houseLocation.community = "";
            },
            changeProv(aval){
                //清空code
                this.houseLocation.city = "";
                this.houseLocation.region = "";
                this.houseLocation.street = "";
                this.houseLocation.community = "";
            },

            changeCommunityBase(aval){
                //赋值街道name
                let areaObj = {};
                areaObj = this.communityList.find((item) => {
                    return item.areaCode === aval;
                });
                this.houseLocation.communityName = areaObj.name;
            },

            /********/
            changeStreetBase(aval){
                //赋值街道name
                let areaObj = {};
                areaObj = this.streetList.find((item) => {
                    return item.areaCode === aval;
                });
                this.houseLocation.streetName = areaObj.name;
                this.houseLocation.communityName = "";
                this.houseLocation.community="";
            },

            changeRegionBase(aval){
                this.houseLocation.street = "";
                //清空name
                this.houseLocation.streetName = "";
                //赋值县区name
                let areaObj = {};
                areaObj = this.regionList.find((item) => {
                    return item.areaCode === aval;
                });
                this.houseLocation.regionName = areaObj.name;
                console.log(this.houseLocation.regionName)
            },
            changeCityBase(aval){
                this.houseLocation.region = "";
                this.houseLocation.street = "";

                //赋值市name
                let areaObj = {};
                areaObj = this.cityList.find((item) => {
                    return item.areaCode === aval;
                });
                this.houseLocation.cityName = areaObj.name;
                //清空name
                this.houseLocation.regionName = "";
                this.houseLocation.streetName = "";
            },
            changeProvBase(aval){
                //清空code
                this.houseLocation.city = "";
                this.houseLocation.region = "";
                this.houseLocation.street = "";

                //赋值省name
                let areaObj = {};
                areaObj = this.provinceList.find((item) => {
                    return item.areaCode === aval;
                });
                this.houseLocation.provinceName = areaObj.name;
                //清空name
                this.houseLocation.cityName = "";
                this.houseLocation.regionName = "";
                this.houseLocation.streetName = "";

            },

            /*省份查询*/
            handNeedProvince(val){
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
                    vm.provinceList = response.data.result;
                }).catch(function (error) {
                    vm.$notify({
                        showClose: true,
                        message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                        type: 'error'
                    });
                });
            },

            /*市区查询*/
            getCitieList(aVal) {
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
                        vm.cityList = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询市区信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }

            },

            /*区域查询*/
            getRegionList(bVal) {
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
                        vm.regionList = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },

            /*街道查询*/
            getStreetList(aVal){
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
                        vm.streetList = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询街道信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
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

            /*****全国地区方法******/

            /*省份查询*/
            handNeedProvinceBase(val){
                var vm = this;
                var obj = {};
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "baseArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrBase = response.data.result;
                }).catch(function (error) {
                    vm.$notify({
                        showClose: true,
                        message: '页面:查询省份信息失败!baseArea/queryNoPage',
                        type: 'error'
                    });
                });
            },

            /*市区查询*/
            getCitieListBase(aVal) {
                if (aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询市区信息失败!baseArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }

            },

            /*区域查询*/
            getRegionListBase(bVal) {
                if (bVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询区域信息失败!baseArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },

            /*街道查询*/
            getStreetListBase(aVal){
                if (aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "baseArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询街道信息失败!baseArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }


            },


        }//end methods

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
        padding-top: 20px;
        padding-left: 0px;
        border-top: 1px solid gainsboro;
    }

    .form-tip {
        font-weight: bold;
        text-align: center;
        font-size: 25px;
        margin-bottom: 20px;
        background-color: #d7d7d7;
        height: 50px;
        line-height: 50px;
    }

    .img-tip {
        padding-left: 20px;
        font-weight: bolder;
        line-height: 40px;
        font-size: 16px;
        margin-bottom: 10px;
        border-bottom: 1px solid #d7d7d7;
        background: #409EFF;
        color: white;
    }
</style>
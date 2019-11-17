

<template>
    <div class="root"
         v-loading="waitFlag"
         element-loading-text="拼命加载中"
         element-loading-spinner="el-icon-loading"
         element-loading-background="rgba(0, 0, 0, 0.6)"><!--根部分-->

        <el-form label-width="100px">
        <!--导航栏-->



        <div class="konge"></div>

        <!--第1个方框 一行搜索-->
        <!--位置信息-->
        <div class="titles">
            <p class="message_title">查看启用地区:</p>
        </div>
        <div class="block_box1">
            <!--空格--><div class="konge_row"></div><!--空格(end)-->

            <el-row>
                <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                    <el-form-item label="所属省份" prop="provinceCode">
                        <el-select id="provinceNameCpy"  v-model="cpyAreaObj.provinceCode" @visible-change="handNeedProvinceCpy" @change="changeProvCpy" placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itProvinceArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属城市" prop="cityCode">
                        <el-select id="cityNameCpy"  v-model="cpyAreaObj.cityCode" @focus="getCitieListCpy(cpyAreaObj.provinceCode)" @change="changeCityCpy"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itCityArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属区域" prop="regionCode">
                        <el-select id="regionNameCpy" v-model="cpyAreaObj.regionCode" @focus="getAreaListCpy(cpyAreaObj.cityCode)"  @change="changeRegionCpy"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itAreaArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属街道" prop="streetCode">
                        <el-select  id="streetNameCpy" v-model="cpyAreaObj.streetCode" @focus="getStreetListCpy(cpyAreaObj.regionCode)" @change="changeStreetCyp"   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itStreetArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                    <el-form-item label="所属社区" prop="streetCode">
                        <el-select  id="communityNameCpy" v-model="cpyAreaObj.communityCode" @focus="getCommunityListCpy(cpyAreaObj.streetCode)"  placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in itCommunityArrCpy"
                                    :key="item.areaCode"
                                    :value="item.areaCode"
                                    :label="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>

        </div>
        <!--第1个方框(end)-->

        <!--第2个方框 一行显示搜索条数-->
            <!--启用功能操作-->
            <div class="titles">
                <p class="message_title">启用功能操作:</p>
            </div>
            <div class="block_box1">
                <!--空格--><div class="konge_row"></div><!--空格(end)-->
                <el-row>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属省份" prop="provinceCode">
                            <el-select id="provinceNameBase" ref="provinceNameBase"  v-model="baseAreaObj.provinceCode" @visible-change="handNeedProvinceBase" @change="changeProvBase" placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArrBase"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name"
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属城市" prop="cityCode">
                            <el-select id="cityNameBase"  v-model="baseAreaObj.cityCode" @focus="getCitieListBase(baseAreaObj.provinceCode)" @change="changeCityBase"   placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itCityArrBase"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <el-form-item label="所属区域" prop="regionCode">
                            <el-select id="regionNameBase" v-model="baseAreaObj.regionCode" @focus="getAreaListBase(baseAreaObj.cityCode)"  @change="changeRegionBase"   placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itAreaArrBase"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>

                </el-row>

            </div>
        <!--第2个方框(end)-->

        <!--第3个方框 一行树状表格-->
            <div class="msg_show_name">
                <span style="margin-right: 15px;margin-left: 28px;">您将启用:</span>
                <el-tag v-show="(baseAreaObj.provinceName)" style="width: 70px">{{baseAreaObj.provinceName}}</el-tag>
                <el-tag v-show="(baseAreaObj.cityName)" style="width: 70px">{{baseAreaObj.cityName}}</el-tag>
                <el-tag v-show="(baseAreaObj.regionName)" style="width: 70px">{{baseAreaObj.regionName}}</el-tag>
                <b style="margin-left: 30px">(及其下辖地区)</b>
                <el-button
                        @click="handConfirmEnable"

                        style="margin-left: 30px;"
                        size="mini"
                        type="warning"
                        plain>确认启用</el-button>
            </div>

            <div class="konge"></div>

            <div class="msg_show_code" style="clear:both">
                <span style="margin-right: 15px;margin-left: 28px;">对应编码:</span>
                <el-tag v-show="(baseAreaObj.provinceCode)" style="width: 70px">{{baseAreaObj.provinceCode}}</el-tag>
                <el-tag v-show="(baseAreaObj.cityCode)" style="width: 70px">{{baseAreaObj.cityCode}}</el-tag>
                <el-tag v-show="(baseAreaObj.regionCode)" style="width: 70px">{{baseAreaObj.regionCode}}</el-tag>
            </div>
        <!--第3个方框(end)-->

        <!--第4个方框 页码条-->
        <div class="block_box4"></div>
        <!--第4个方框(end)-->

        </el-form>
    </div><!--根部分(end)-->
</template>


<script scoped>


    export default {

        created:function () {

        },
        data() {
            return {
                waitFlag:false,
                //公司在用基本地区
                cpyAreaObj:{

                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",
                    communityCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                    streetName:"",
                    communityName:""

                },
                itProvinceArrCpy:[],
                itCityArrCpy:[],
                itAreaArrCpy:[],
                itStreetArrCpy:[],
                itCommunityArrCpy:[],
                //全国基本地区
                baseAreaObj:{

                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",
                    communityCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                    streetName:"",
                    communityName:"",
                },
                itProvinceArrBase:[],
                itCityArrBase:[],
                itAreaArrBase:[],
                itStreetArrBase:[],
                itCommunityArrBase:[],

            }
        },
        methods: {
            handConfirmEnable(){
                //判断选择框绑定的值,从而给请求体对象中的enableFlag 赋值
                var vm = {};
                vm=this;
                var enableFlag=99;//初始化为99
                var provinceObj={};
                provinceObj.areaCode = this.baseAreaObj.provinceCode;
                provinceObj.name = this.baseAreaObj.provinceName;
                var cityObj={};
                cityObj.areaCode = this.baseAreaObj.cityCode;
                cityObj.name = this.baseAreaObj.cityName;
                var regionObj={};
                regionObj.areaCode = this.baseAreaObj.regionCode;
                regionObj.name = this.baseAreaObj.regionName;

                if(regionObj.areaCode !="" && regionObj.areaCode != null){
                    enableFlag=3;
                }else if(regionObj.areaCode ==""){
                    if(cityObj.areaCode !="" && cityObj.areaCode != null){
                        enableFlag=2;
                    }else if(cityObj.areaCode ==""){
                        if(provinceObj.areaCode !="" && provinceObj.areaCode != null){
                            enableFlag=1;
                        }else if(provinceObj.areaCode ==""){
                            this.$notify.error('请选择省市区');
                            this.$notify({
                                title: '提示',
                                message: h('i', { style: 'color: teal'}, '请选择省市区')
                            });
                        }else{
                            this.$notify({
                                showClose: true,
                                message: '启用失败,省编码code='+regionObj.areaCode,
                                type: 'error'
                            });
                        }

                    }else{
                        this.$notify({
                            showClose: true,
                            message: '启用失败,城市编码code='+regionObj.areaCode,
                            type: 'error'
                        });
                    }

                }else{
                    var enableFlag=99;
                    this.$notify({
                        showClose: true,
                        message: '启用失败,县区编码code='+regionObj.areaCode,
                        type: 'error'
                    });
                }
                //enableFlag判断完毕,发送请求

                vm.waitFlag=true;
                var flag = true;
                if (flag) {        //flag留后面表单验证
                    var obj = {};
                    var sendObj = {};
                    sendObj.entity = obj;
                    sendObj.enableFlag = enableFlag;
                    sendObj.provinceObj = provinceObj;
                    sendObj.cityObj = cityObj;
                    sendObj.regionObj = regionObj;

                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "baseArea/enableArea",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.waitFlag =false;
                        vm.notify.success('启用地区成功!');
                    }).catch(function (error) {
                        vm.$notify.error('页面:获取数据失败!baseArea/enableArea');
                    });
                }


            },

            changeStreetCyp(aval){
                this.cpyAreaObj.communityCode="";
            },

            changeRegionCpy(aval){
                //清空code
                this.cpyAreaObj.streetCode="";
                this.cpyAreaObj.communityCode="";
            },
            changeCityCpy(aval){
                //清空code
                this.cpyAreaObj.regionCode="";
                this.cpyAreaObj.streetCode="";
                this.cpyAreaObj.communityCode="";
            },
            changeProvCpy(aval){
                //清空code
                this.cpyAreaObj.cityCode="";
                this.cpyAreaObj.regionCode="";
                this.cpyAreaObj.streetCode="";
                this.cpyAreaObj.communityCode="";
            },

            changeCommunityBase(aval){
                //赋值街道name
                let areaObj = {};
                areaObj = this.itCommunityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.communityName= areaObj.name;
            },

            /********/
            changeStreetBase(aval){
                //赋值街道name
                let areaObj = {};
                areaObj = this.itStreetArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.streetName= areaObj.name;
            },

            changeRegionBase(aval){
                this.baseAreaObj.streetCode="";
                //赋值县区name
                let areaObj = {};
                areaObj = this.itAreaArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.regionName= areaObj.name;
                //清空name
                this.baseAreaObj.streetName="";
            },
            changeCityBase(aval){
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.streetCode="";

                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.cityName= areaObj.name;
                //清空name
                this.baseAreaObj.regionName="";
                this.baseAreaObj.streetName="";
            },
            changeProvBase(aval){
                //清空code
                this.baseAreaObj.cityCode="";
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.streetCode="";

                //赋值省name
                let areaObj = {};
                areaObj = this.itProvinceArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.provinceName= areaObj.name;
                //清空name
                this.baseAreaObj.cityName="";
                this.baseAreaObj.regionName="";
                this.baseAreaObj.streetName="";


            },

            /*省份查询*/
            handNeedProvinceCpy(val){
                var vm=this;
                var obj = { };
                obj.parentCode = 0;
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
                    vm.itProvinceArrCpy = response.data.result;
                }).catch(function (error) {
                    vm.$notify({
                        showClose: true,
                        message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                        type: 'error'
                    });
                });
            },

            /*市区查询*/
            getCitieListCpy(aVal) {
                if(aVal != ""){
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
                        vm.itCityArrCpy = response.data.result;
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
            getAreaListCpy(bVal) {
                if(bVal != ""){
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
                            showClose: true,
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },

            /*街道查询*/
            getStreetListCpy(aVal){
                if(aVal != ""){
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
                        vm.$notify({
                            showClose: true,
                            message: '页面:查询街道信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*街道查询*/
            getCommunityListCpy(aVal){
                if(aVal != ""){
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
                        vm.itCommunityArrCpy = response.data.result;
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
                var vm=this;
                var obj = { };
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url:"baseArea/queryNoPage",
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
                if(aVal != ""){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
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
            getAreaListBase(bVal) {
                if(bVal != ""){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
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
                if(aVal != ""){
                    var vm=this;
                    var obj = { };
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url:"baseArea/queryNoPage",
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
    }//end export

</script>

<style scoped>


    .root {
        min-width: 1000px;
        min-height: 800px;
        margin-top: -17px;
        padding-left: 20px;
        background-color: white;
    }

    /*空格*/
    .konge {
        min-height: 25px;
        min-width: 1000px;
    }
    .konge_row {
        min-height: 15px;
        min-width: 1000px;
        background-color: white;
    }

    .block_box1 {
        min-height:40px;
        background-color: white;
        margin-bottom: 30px;
    }

    .titles{
        min-height: 50px;
        background-color: white;
        margin-top:-20px;
        border-bottom: 1px solid rgb(242,242,242);
    }

    .msg_show_name{
        float: left;
        min-height: 50px;
        background-color: white;
        margin-top:-20px;

        color: #FE980F;
    }

    .msg_show_code{
        float: left;
        min-height: 50px;
        background-color: white;
        margin-top:-20px;
        margin-left: 0px;

        color: #FE980F;
    }

    /*信息标题*/
    .message_title{
        color: #FE980F;
        float: left;
        margin-top: -50px;
        margin-left: 0px;
        height: 50px;
    }

</style>
<template>
    <div>
        <el-row>
            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                <!--<el-form-item label="需求省份" prop="provinceCode">-->
                    <el-select id="provinceName"  v-model="picker.provinceCode" @visible-change="initProvince" @change="provinceChange" placeholder="请选择省、直辖市">
                        <el-option
                                v-for="item in province"
                                :key="item.areaCode"
                                :value="item.areaCode"
                                :label="item.name">
                        </el-option>
                    </el-select>
                <!--</el-form-item>-->
            </el-col>
            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                <!--<el-form-item label="需求城市" prop="cityCode">-->
                    <el-select id="cityName"  v-model="picker.cityCode" @focus="getCityList(picker.provinceCode)" @change="cityChange"  placeholder="请选择城市">
                        <el-option
                                v-for="item in city"
                                :key="item.areaCode"
                                :value="item.areaCode"
                                :label="item.name">
                        </el-option>
                    </el-select>
                <!--</el-form-item>-->
            </el-col>
            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                <!--<el-form-item label="需求区域" prop="regionCode">-->
                    <el-select id="regionName" v-model="picker.regionCode" @focus="getRegionList(picker.cityCode)"  @change="regionChange"  placeholder="请选择区、县">
                        <el-option
                                v-for="item in region"
                                :key="item.areaCode"
                                :value="item.areaCode"
                                :label="item.name">
                        </el-option>
                    </el-select>
                <!--</el-form-item>-->
            </el-col>
            <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                <!--<el-form-item label="所属街道" prop="streetCode">-->
                    <el-select  id="streetName" v-model="picker.streetCode" @focus="getStreetList(picker.regionCode)" @change="streetChange"  placeholder="请选择街道">
                        <el-option
                                v-for="item in street"
                                :key="item.areaCode"
                                :value="item.areaCode"
                                :label="item.name">
                        </el-option>
                    </el-select>
                <!--</el-form-item>-->
            </el-col>

        </el-row>


    </div>
</template>


<script>
    const DEFAULT_CODE = "0"
    export default {
        props:[
            'pick'
        ],
        created:function () {

            this.picker = this.pick;

            let provinceName = this.pick.provinceName;
            let cityName = this.pick.cityName;
            let regionName = this.pick.regionName;
            let streetName = this.pick.streetName;
            let provinceCode = this.pick.provinceCode;
            let cityCode = this.pick.cityCode;
            let regionCode = this.pick.regionCode;
            let streetCode = this.pick.streetCode;

            this.province.push(
                {
                    areaCode:provinceCode,
                    name:provinceName
                }
            );
            this.city.push(
                {
                    areaCode:cityCode,
                    name:cityName
                }
            );
            this.region.push(
                {
                    areaCode:regionCode,
                    name:regionName
                }
            );
            this.street.push(
                {
                    areaCode:streetCode,
                    name:streetName
                }
            )
        },

        data() {
            return {
                province:[],
                city:[],
                region:[],
                street:[],
                picker:{
                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",
                    provinceName:"",
                    cityName:"",
                    regionName:"",
                    streetName:""
                },
            }
        },

        methods: {
            initProvince(val){
                var vm=this;
                var picker = { };
                picker.parentCode = DEFAULT_CODE;
                var param = {};
                param.entity = picker;
                var options = {
                    method: 'POST',
                    data: param,
                    url:"cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.province = response.data.result;

                }).catch(function (error) {
                    alert("post返回msg错误");
                });
            },
            getCityList(val) {
                if(val != ""){

                    var vm=this;

                    var picker = { };
                    picker.parentCode = val;
                    var param = {};
                    param.entity = picker;
                    var options = {
                        method: 'POST',
                        data: param,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.city = response.data.result;
                        vm.$emit("selectProvice",vm.picker)
                    }).catch(function (error) {
                        alert("post返回msg错误");
                    });
                }
            },
            getRegionList(val) {
                if(val != "") {
                    var vm = this;

                    var picker = {};
                    picker.parentCode = val;
                    var param = {};
                    param.entity = picker;
                    var options = {
                        method: 'POST',
                        data: param,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.region = response.data.result;
                        vm.$emit("selectProvice", vm.picker)
                    }).catch(function (error) {
                        alert("post返回msg错误");
                    });
                }
            },
            getStreetList(val){
                if(val != "") {
                    var vm = this;
                    var picker = {};
                    picker.parentCode = val;
                    var param = {};
                    param.entity = picker;
                    var options = {
                        method: 'POST',
                        data: param,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.street = response.data.result;

                        vm.$emit("selectProvice", vm.picker)
                    }).catch(function (error) {
                        alert("post返回msg错误");
                    });
                }
            },
            provinceChange(val){
                if(val != "") {
                    var vm = this;
                    var picker1 = {};
                    picker1.areaCode = val;
                    var param1 = {};
                    param1.entity = picker1;
                    var options1 = {
                        method: 'POST',
                        data: param1,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options1
                    ).then(function (response) {
                        vm.picker.provinceName = response.data.result.name;
                    }).catch(function (error) {
                        alert("post返回msg错误");
                    });
                }

            },
            cityChange(val){
                if(val != "") {
                    var vm = this;

                    var picker1 = {};
                    picker1.areaCode = val;
                    var param1 = {};
                    param1.entity = picker1;
                    var options1 = {
                        method: 'POST',
                        data: param1,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options1
                    ).then(function (response) {
                        vm.picker.cityName = response.data.result.name;
                    }).catch(function (error) {
                        alert("post返回msg错误");
                    });
                }
            },
            regionChange(val){
                if(val != "") {
                    var vm = this;

                    var picker1 = { };
                    picker1.areaCode = val;
                    var param1 = {};
                    param1.entity = picker1;
                    var options1 = {
                        method: 'POST',
                        data: param1,
                        url:"cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options1
                    ).then(function (response) {
                        vm.picker.regionName = response.data.result.name;
                    }).catch(function (error) {
                        alert("post返回msg错误");
                    });
                }
            },
            streetChange(val){
                if (val != "") {
                    var vm = this;
                    var picker1 = {};
                    picker1.areaCode = val;
                    var param1 = {};
                    param1.entity = picker1;
                    var options1 = {
                        method: 'POST',
                        data: param1,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options1
                    ).then(function (response) {
                        vm.picker.streetName = response.data.result.name;
                    }).catch(function (error) {
                        alert("street");
                    });
                    this.$emit("selectProvice", vm.picker)
                }
            }
        }
    }

</script>

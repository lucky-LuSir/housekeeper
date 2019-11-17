<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="visible" title="查询条件"
               width="60%" append-to-body>
        <div class="pre-scrollable">
            <el-form :model="searchCooCondition" ref="searchCooConditions" size="mini">
                <el-row>
                    <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14">
                        <el-form-item prop="cooStatus" label="合作状态">
                            <el-radio-group size="mini"
                                            v-model="searchCooCondition.cooStatus">
                                <el-radio-button>全部</el-radio-button>
                                <el-radio-button :label="1">待审核</el-radio-button>
                                <el-radio-button :label="2">已拒绝</el-radio-button>
                                <el-radio-button :label="3">已接受</el-radio-button>
                                <el-radio-button :label="4">已带看</el-radio-button>
                                <el-radio-button :label="5">已签约</el-radio-button>
                                <el-radio-button :label="6">已结束</el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="0">
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <el-form-item label="所属省份" prop="province">
                            <el-select id="provinceNameCpy"
                                       v-model="searchCooCondition.province"
                                       @visible-change="handNeedProvinceCpy" @change="changeProvCpy"
                                       placeholder="请选择">
                                <el-option
                                        v-for="(item, indexkey) in itProvinceArrCpy"
                                        :key="item.areaCode"
                                        :value="item.areaCode"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属城市" prop="city">
                            <el-select id="cityNameCpy"
                                       v-model="searchCooCondition.city"
                                       @focus="getCitieListCpy(searchCooCondition.province)"
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
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属区域" prop="region">
                            <el-select id="regionNameCpy"
                                       v-model="searchCooCondition.region"
                                       @focus="getAreaListCpy(searchCooCondition.city)"
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
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属街道" prop="street">
                            <el-select id="streetNameCpy"
                                       v-model="searchCooCondition.street"
                                       @focus="getStreetListCpy(searchCooCondition.region)"
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
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <el-form-item label="所属社区" prop="community">
                            <el-select id="communityNameCpy"
                                       v-model="searchCooCondition.community"
                                       @focus="getCommunityListCpy(searchCooCondition.street)"
                                       placeholder="请选择">
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
                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="房源地址" prop="detailAddress">
                            <el-input v-model="searchCooCondition.detailAddress"
                                      placeholder="房源地址"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="房源标题" prop="houseName">
                            <el-input v-model="searchCooCondition.houseName"
                                      placeholder="房源标题"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="50">
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="客户电话" prop="cusPhone">
                            <el-input v-model="searchCooCondition.cusPhone"
                                      placeholder="客户电话"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="业主电话" prop="houseownerPhone">
                            <el-input v-model="searchCooCondition.houseownerPhone"
                                      placeholder="业主电话"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="50">

                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="合作人员" prop="unionName">
                            <el-input v-model="searchCooCondition.unionName"
                                      placeholder="合作人员"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                        <el-form-item label="合作电话" prop="unionPhone">
                            <el-input v-model="searchCooCondition.unionPhone"
                                      placeholder="合作电话"/>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button size="small" plain type="primary"
                       @click="emptyConditions">重置条件
            </el-button>
            <el-button size="small" plain type="success" @click="getCooByCondition()">按条件查询
            </el-button>
        </div>
    </el-dialog>
</template>

<script>
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {ElRow},
        data() {
            return {
                visible: false,
                //所属省份
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                //所属街道
                itStreetArrCpy: [],
                //所属社区
                itCommunityArrCpy: [],
                searchCooCondition: {
                    cooStatus:null,
                    province:null,
                    city:null,
                    region:null,
                    street:null,
                    detailAddress:null,
                    houseName:null,
                    cusPhone:null,
                    houseownerPhone:null,
                    unionName:null,
                    unionPhone:null
                },
            }
        },
        created() {
            this.visible = false;

        },
        mounted() {
        },
        methods: {
            getCooByCondition(){
                this.$emit('changeCoo', this.searchCooCondition)
            },

            //重置条件
            emptyConditions(){

                this.$refs['searchCooConditions'].resetFields();

            },
            //改变省
            changeProvCpy(){
                //清空code
                this.searchCooCondition.city = null;
                this.searchCooCondition.region = null;
                this.searchCooCondition.street = null;
            },
            //改变市
            changeCityCpy(){
                this.searchCooCondition.region = null;
                this.searchCooCondition.street = null;
            },
            //改变区域
            changeRegionCpy(){
                this.searchCooCondition.street = null;
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
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
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
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*街道查询*/
            getStreetListCpy(aVal){
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
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }


            },
            /*社区查询*/
            getCommunityListCpy(aVal){
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
                        vm.itCommunityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
        }
    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        font-size: 12px;
        max-height: 500px;
        overflow-y: scroll;
        overflow-x: hidden;
    }

    .el-radio + .el-radio {
        margin-left: 10px;
    }

    .el-form-item--mini .el-form-item__content, .el-form-item--mini .el-form-item__label {
        line-height: 14px;
    }

    .house-style {
        padding: 0px;
        margin: 0px;
    }
</style>


<template slot-scope="scope">
    <div class="root" ref="myRoot">
        <!-- 省市区条件 -->
        <div class="top_bar">
            <div class="top_area">
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form>
                            <el-form-item prop="provinceCode">
                                <el-select id="provinceNameCpy" size="small" clearable v-model="cpyAreaObj.provinceCode" @visible-change="handNeedProvinceCpy" @change="changeProvCpy" placeholder="省:">
                                    <el-option
                                            v-for="(item, indexkey) in itProvinceArrCpy"
                                            :key="item.areaCode"
                                            :value="item.areaCode"
                                            :label="item.name"
                                    >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form>
                            <el-form-item prop="cityCode">
                                <el-select id="cityNameCpy" size="small" clearable v-model="cpyAreaObj.cityCode" @focus="getCitieListCpy(cpyAreaObj.provinceCode)" @change="changeCityCpy"   placeholder="市:">
                                    <el-option
                                            v-for="(item, indexkey) in itCityArrCpy"
                                            :key="item.areaCode"
                                            :value="item.areaCode"
                                            :label="item.name">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-col>

                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                        <el-form>
                            <el-form-item prop="regionCode">
                                <el-select id="regionNameCpy" size="small" clearable v-model="cpyAreaObj.regionCode" @focus="getAreaListCpy(cpyAreaObj.cityCode)"  @change="changeRegionCpy"   placeholder="区:">
                                    <el-option
                                            v-for="(item, indexkey) in itAreaArrCpy"
                                            :key="item.areaCode"
                                            :value="item.areaCode"
                                            :label="item.name">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-col>
                </el-row>
            </div>
            <div class="top_select">
                <el-button class="top_in_btn" type="danger" size="small" plain  @click="clearQuery()">清空条件</el-button>
                <el-button class="top_in_btn" type="primary" size="small" plain  icon="el-icon-search" plain @click="report()">查询</el-button>
            </div>
        </div>
        <!-- 数据表头 -->
        <div class="reght_table">
            <el-table :data="tableData" id="record" v-loading="loading" stripe show-summary :height=floatDivHeight>
                <el-table-column
                        prop="showName"
                        label="统计数量 区域范围"
                        width="90">
                </el-table-column>

                <el-table-column label="园区数量(单位:个)" align="center">
                    <el-table-column
                            prop="marketParkTotal"
                            label="市场总和"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="parkTotal"
                            label="系统已收录"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="parkHotRent"
                            label="已收录待出租"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="parkRented"
                            label="已收录已出租"
                            width="120">
                    </el-table-column>
                </el-table-column>
                <el-table-column label="房源数量(单位:套)" align="center">
                    <el-table-column
                            prop="marketHouseTotal"
                            label="市场总和"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseTotal"
                            label="系统已收录"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseHotRent"
                            label="已收录待出租"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseRented"
                            label="已收录已出租"
                            width="120">
                    </el-table-column>
                </el-table-column>
                <el-table-column label="房源面积(单位:平米)" align="center">
                    <el-table-column
                            prop="marketHouseAreaTotal"
                            label="市场总和"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseAreaTotal"
                            label="系统已收录"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseAreaHotRent"
                            label="已收录待出租"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="houseAreaRented"
                            label="已收录已出租"
                            width="120">
                    </el-table-column>
                </el-table-column>


            </el-table>
        </div>
    </div>
</template>

<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    export default {
        data() {
            return {

                // 省市区
                cpyAreaObj:{
                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                },

                itProvinceArrCpy:[],
                itCityArrCpy:[],
                itAreaArrCpy:[],

                // 报表数据
                tableData: [],

                // 加载动画
                loading : false,

                // 高度
                floatDivHeight: 50

            }
        },

        created:function (){
            this.report();
        },

        mounted(){

            var vm = {};
            vm  = this;
            //动态调整高度start
            this.floatDivHeight = this.$refs.myRoot.offsetHeight-200;
            //alert(this.floatDivHeight);
            console.log(this.floatDivHeight);
            window.onresize = () => {
                return (() => {
                    this.floatDivHeight = vm.$refs.myRoot.offsetHeight-200;
                })();
            }
            //动态调整高度end
        },

        methods:{
            report(){

                var vm = {};
                vm = this;

                vm.loading = true;

                var areaObj = vm.cpyAreaObj;

                var code = null;

                if(areaObj.regionCode != ''){
                    code = areaObj.regionCode;
                }else if(areaObj.cityCode != ''){
                    code = areaObj.cityCode;
                }else if(areaObj.provinceCode != ''){
                    code = areaObj.provinceCode;
                }

                var sendObj = {"code":code};

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/houseDictionary",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.tableData = response.data.result;
                    vm.loading = false
                    let h = vm.floatDivHeight;
                    vm.floatDivHeight = h-1;
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
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
                    vm.$message({
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
                        vm.$message({
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
                        vm.$message({
                            showClose: true,
                            message: '页面:查询区域信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },

            changeRegionCpy(aval){
                //清空code
                this.cpyAreaObj.streetCode="";
            },

            changeCityCpy(aval){
                //清空code
                this.cpyAreaObj.regionCode="";
                this.cpyAreaObj.streetCode="";

            },

            changeProvCpy(aval){
                //清空code
                this.cpyAreaObj.cityCode="";
                this.cpyAreaObj.regionCode="";
            },

            /*清除查询条件*/
            clearQuery(){
                this.cpyAreaObj.provinceCode="";
                this.cpyAreaObj.cityCode="";
                this.cpyAreaObj.regionCode="";
            },
        }
    }
</script>

<style scoped>
    .root{
        height: 100vh
    }
    .top_bar{
        width: 100%;
        margin:0px 0px 0px 0px;
        border-width: 0px 0px 0px 0px;
        border-style:inset;
        padding:0px 0px 4px 0px;
    }

    .top_area{
        float: left;
        margin-left: 20px;
        margin-right: 20px;
    }

    .top_select{
        float: none;
        margin-left: 20px;
    }

    .reght_table{
        margin:0px 0px 0px 20px;
    }

    .top_in_btn{
        margin-top:4px;
    }

</style>
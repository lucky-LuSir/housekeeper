<template>
    <div class="root" ref="myRoot">
        <!-- 日期条件 -->
        <div class="top_bar">
            <div class="top_area">
                <el-date-picker class="chooseYear"
                        v-model="time"
                        align="right"
                        type="year"
                        size="small"
                        placeholder="选择年">
                </el-date-picker>
            </div>
            <div class="top_area">
                <el-select id="provinceNameBase" size="small" ref="provinceNameBase" v-model="baseAreaObj.provinceCode"
                           @visible-change="handNeedProvinceBase" @change="changeProvBase" placeholder="请选择">
                    <el-option
                            v-for="(item, indexkey) in itProvinceArrBase"
                            :key="item.areaCode"
                            :value="item.areaCode"
                            :label="item.name"
                    >
                    </el-option>
                </el-select>
                <el-select id="cityNameBase" size="small" v-model="baseAreaObj.cityCode"
                           @focus="getCitieListBase(baseAreaObj.provinceCode)" @change="changeCityBase"
                           placeholder="请选择">
                    <el-option
                            v-for="(item, indexkey) in itCityArrBase"
                            :key="item.areaCode"
                            :value="item.areaCode"
                            :label="item.name">
                    </el-option>
                </el-select>
                <el-select id="regionNameCpy" size="small" v-model="baseAreaObj.regionCode"
                           @focus="getAreaListCpy(baseAreaObj.cityCode)" placeholder="请选择">
                    <el-option
                            v-for="(item, indexkey) in itAreaArrCpy"
                            :key="item.areaCode"
                            :value="item.areaCode"
                            :label="item.name">
                    </el-option>
                </el-select>
            </div>
            <div class="top_select">
                <el-button class="top_in_btn" type="danger" size="small" plain  @click="clearQuery()">清空条件</el-button>
                <el-button class="top_in_btn" type="primary" size="small" plain  icon="el-icon-search" plain @click="query()">查询</el-button>
            </div>
        </div>
        <!--echarts-->
        <div class="echarts">
            <div class="myEcharts" ref="myEcharts" id="myEcharts"></div>
        </div>
    </div>
</template>

<script scoped>

    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElRadioButton from "../../../../node_modules/element-ui/packages/radio/src/radio-button";
    import ElFormItem from "../../../../node_modules/element-ui/packages/form/src/form-item";


    export default {
        components: {
            ElFormItem,
            ElRow,
            ElCol,
            ElRadioButton,
        },

        data(){
            return{
                // 日期
                time:"",
                month:'',
                querys:{},
                baseAreaObj:{},
                rules:{
                    hasMonolayer: [
                        {required: true, message: '请选择维度', trigger: 'blur'},
                    ],
                },
                hasMonolayer: '',

                timeStr:'year',
                areaEntity:{},
                province:"",
                city:"",
                chooseFlag:true,
                // 省市区
                cpyAreaObj:{
                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                },
                region:"",
                itProvinceArrCpy: [],
                //所属城市
                itCityArrCpy: [],
                //所属区域
                itAreaArrCpy: [],
                departmentOptions:[],
                props: {
                    value: 'areaCode',
                    label: 'name',
                    children: 'cities',
                    multiple: true,
                    checkStrictly: true
                },

                //全国基本地区
                baseAreaObj:{

                    provinceCode:"",
                    cityCode:"",
                    regionCode:"",
                    streetCode:"",

                    provinceName:"",
                    cityName:"",
                    regionName:"",
                    streetName:"",

                },
                itProvinceArrBase:[],
                itCityArrBase:[],
            }
        },

        mounted(){
            this.query();
        },

        created:function (){
            this.baseAreaObj.provinceName=="";
            this.baseAreaObj.cityName=="";
            this.baseAreaObj.regionName=="";
            this.baseAreaObj.streetName=="";
        },

        methods:{

            query(){
                this.report("year");
            },
            clickQuery(){
                if (this.timeStr=="month"){
                    return;
                }
                this.report("month");
            },
            report(period){
                var vm = {};
                vm = this
                var province = this.baseAreaObj.provinceCode;
                var city = this.baseAreaObj.cityCode;
                var region = this.baseAreaObj.regionCode;
                vm.timeStr=period;

                // 获取日期
                var time = vm.time;
                var sendObj = {
                    "time":time,
                    "timeStr":period,
                    "province":province,
                    "city":city,
                    "region":region,

                };

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/graph",
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    let entrust = response.data.result.entrust;
                    let bespeak = response.data.result.bespeak;
                    let count = response.data.result.count;

                    let xAxis = [];
                    let countNum = [];

                    let entrustNum = [];
                    let bespeakNum = [];
                    count.filter((item, index, arr) => {
                        xAxis.push(item.timeStr);
                        countNum.push(item.num);
                    });
                    entrust.filter((item, index, arr) => {
                        entrustNum.push(item.num);
                    });
                    bespeak.filter((item, index, arr) => {
                        bespeakNum.push(item.num);
                    });
                    // 设置echarts
                    vm.getEcharts(xAxis,countNum,entrustNum,bespeakNum);
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },



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
                    url:"cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrBase = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询省份信息失败!baseArea/queryNoPage',
                        type: 'error'
                    });
                });
            },
            changeProvBase(aval){
                //清空code
                this.baseAreaObj.cityCode="";
                this.baseAreaObj.regionCode="";
                //赋值省name
                let areaObj = {};
                areaObj = this.itProvinceArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                this.baseAreaObj.provinceName= areaObj.name;
                //清空name
                this.baseAreaObj.cityName="";
                this.baseAreaObj.regionName="";
            },
            /*市区查询*/
            getCitieListBase(aVal) {
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
                        vm.itCityArrBase = response.data.result;
                    }).catch(function (error) {
                        vm.$notify({
                            title: '警告',
                            message: '页面:查询市区信息失败!baseArea/queryNoPage',
                            type: 'warning'
                        });
                    });
                }

            },
            changeCityBase(aval){
//                alert("开始选城市");
                var vm = this;
                this.baseAreaObj.regionCode="";
                //赋值市name
                let areaObj = {};
                areaObj = this.itCityArrBase.find((item)=>{
                    return item.areaCode === aval;
                });
                vm.baseAreaObj.cityName= areaObj.name;
                //清空name
                vm.baseAreaObj.regionName="";
                //alert(vm.baseAreaObj.cityName);
//                alert(vm.baseAreaObj.cityCode);

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



            getEcharts(xAxis,countNum,entrustNum,bespeakNum){
                var vm =this;
                let options = {
                    title: {
                        left: 'center',
                        text: '预约委托统计'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        left: '145',
                        data:['合计','预约','委托']
                    },
                    toolbox: {
                        show: true,
                        itemSize: 20,
                        right: 170,
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: xAxis
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    },
                    series: [
                        {
                            name:'合计',
                            type:'line',
                            data: countNum,
                            itemStyle : {
                                normal : {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        textStyle: {
                                            color: 'red'
                                        }
                                    }
                                },
                            }
                        },
                        {
                            name:'预约',
                            type:'line',
                            data: entrustNum,
                            itemStyle : {
                                normal : {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        textStyle: {
                                            color: 'red'
                                        }
                                    }
                                },
                            }
                        },
                        {
                            name:'委托',
                            type:'line',
                            data: bespeakNum,
                            itemStyle : {
                                normal : {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        textStyle: {
                                            color: 'blue'
                                        }
                                    }
                                },
                            }
                        }
                    ]
                };

                var hosAndCusEcharts = this.$refs.myEcharts;
                // hosAndCusEcharts.onclick = function (params) {
                //     console.log(params)
                // }

                hosAndCusEcharts = this.$echarts.init(hosAndCusEcharts);
                hosAndCusEcharts.on('click', function (params) {
                    var month= params.name;
                    vm.time=Date.parse(month);
                    vm.clickQuery();

                });
                hosAndCusEcharts.setOption(options);

                window.addEventListener('resize', function() {
                    document.querySelector("#myEcharts").style.width = (window.innerWidth - 199)  + "px";
                    document.querySelector("#myEcharts").style.height = (window.innerHeight - 157)  + "px";
                    hosAndCusEcharts.resize();
                });

            },

            clearQuery(){
                this.time = "";
                this.baseAreaObj.provinceCode="";
                this.baseAreaObj.provinceName="";
                this.baseAreaObj.cityCode="";
                this.baseAreaObj.cityName="";
                this.baseAreaObj.regionCode="";
                this.baseAreaObj.regionName="";
                this.itAreaArrCpy=[];
                this.itCityArrBase=[];

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
        margin-top:4px;
        float: left;
        margin-left: 20px;
        margin-right: 20px;
    }

    .top_select{
        float: none;
        margin-left: 20px;
    }

    .top_in_btn{
        margin-top:4px;
    }

    .echarts{
        margin-top:20px;
        width: 100%;
    }

    .myEcharts{
        width: 100%;
        height: 600px;
    }
    .chooseYear{
        type:hidden;
    }

</style>
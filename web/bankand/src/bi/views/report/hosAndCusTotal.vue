<template>
    <div class="root" ref="myRoot">
        <!-- 日期条件 -->
        <div class="top_bar">
            <div class="top_area">
                <el-date-picker
                        v-model="year"
                        align="right"
                        type="year"
                        size="small"
                        placeholder="选择年">
                </el-date-picker>
            </div>
            <div class="top_select">
                <el-button class="top_in_btn" type="danger" size="small" plain  @click="clearQuery()">清空条件</el-button>
                <el-button class="top_in_btn" type="primary" size="small" plain  icon="el-icon-search" plain @click="report()">查询</el-button>
            </div>
        </div>
        <!--echarts-->
        <div class="echarts">
            <div class="myEcharts" ref="myEcharts" id="myEcharts"></div>
        </div>
    </div>
</template>

<script scoped>

    export default {

        data(){
            return{
                // 日期
                year:""
            }
        },

        mounted(){
            this.report();
        },

        created:function (){

        },

        methods:{

            report(){
                var vm = {};
                vm = this;

                // 获取日期
                var startDate = vm.year;
                var sendObj = {"startDate":startDate};

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/hosAndCusTotal",
                };

                this.$ajax(
                    options
                ).then(function (response) {
                    let cusTotals = response.data.result.total1;
                    let hosTotals = response.data.result.total2;
                    let xAxis = [];
                    let seriesCus = [];
                    let seriesHos = [];
                    cusTotals.filter((item, index, arr) => {
                        xAxis.push(item.yearMonth);
                        seriesCus.push(item.cusCount);
                    });
                    hosTotals.filter((item, index, arr) => {
                        seriesHos.push(item.hosCount);
                    });
                    // 设置echarts
                    vm.getEcharts(xAxis,seriesCus,seriesHos);
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },

            getEcharts(xAxis,seriesCus,seriesHos){
                let options = {
                    title: {
                        left: 'center',
                        text: '房源客户年度统计'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        left: '145',
                        data:['客户','房源']
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
                            name:'客户',
                            type:'line',
                            data: seriesCus,
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
                            name:'房源',
                            type:'line',
                            data: seriesHos,
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
                hosAndCusEcharts = this.$echarts.init(hosAndCusEcharts);
                hosAndCusEcharts.setOption(options);

                window.addEventListener('resize', function() {
                    document.querySelector("#myEcharts").style.width = (window.innerWidth - 199)  + "px";
                    document.querySelector("#myEcharts").style.height = (window.innerHeight - 157)  + "px";
                        hosAndCusEcharts.resize();
                });

            },

            clearQuery(){
                this.year = "";
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
        height: calc(100vh - 157px);
    }

</style>
<template>

    <el-row>
        <el-col :span="12">
                <div class="contractExpire grid-content bg-purple">
                    <el-row class="contractExpireHeadCus" >
                        <el-col :span="12">
                            <p  class="title">
                                客户合同到期监控图
                                <!--<el-tooltip class="item" effect="dark" placement="top-start" >-->
                                <!--<div style="line-height: 18px;" slot="content">横轴显示从最早有到期合同的月份开始，往后推36个月结束。<br/>纵轴显示到期合同份数。</div>-->
                                <!--<i class="el-icon-diy-wenhao" style="font-size: 15px; cursor: pointer;"></i>-->
                                <!--</el-tooltip>-->

                            </p>
                        </el-col>
                        <el-col :span="12">
                            <div class="desc">
                                <span style="display: inline-block;width: 6px;height: 6px;background: #609dfc;border-radius: 6px;margin-bottom: 2px;  cursor: pointer;"></span>
                                <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">每月合同到期数(份)</span>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row class="contractExpireMain" type="flex">
                        <el-col :span="24">
                            <div class="eChartsBox">
                                <div class="cusContractExpireEcharts" ref="cusContractExpireEcharts" id="cusContractExpireEcharts"></div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
        </el-col>
        <el-col :span="12">
                <div class="contractExpire grid-content bg-purple-light">
                    <el-row class="contractExpireHeadHos" >
                        <el-col :span="12">
                            <p  class="title">
                                房源合同到期监控图
                                <!--<el-tooltip class="item" effect="dark" placement="top-start" >-->
                                <!--<div style="line-height: 18px;" slot="content">横轴显示从最早有到期合同的月份开始，往后推36个月结束。<br/>纵轴显示到期合同份数。</div>-->
                                <!--<i class="el-icon-diy-wenhao" style="font-size: 15px; cursor: pointer;"></i>-->
                                <!--</el-tooltip>-->

                            </p>
                        </el-col>
                        <el-col :span="12">
                            <div class="desc">
                                <span style="display: inline-block;width: 6px;height: 6px;background: #609dfc;border-radius: 6px;margin-bottom: 2px;  cursor: pointer;"></span>
                                <span style="margin-left: 5px; color: rgb(114, 128, 161); font-size: 12px;">每月合同到期数(份)</span>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row class="contractExpireMain"  type="flex">
                        <el-col :span="24">
                            <div class="eChartsBox">
                                <div class="hosContractExpireEcharts" ref="hosContractExpireEcharts" id="hosContractExpireEcharts"></div>
                            </div>
                        </el-col>
                    </el-row>
                </div>
        </el-col>
    </el-row>






</template>
<script>
    import ElDialog from "../../../../node_modules/element-ui/packages/dialog/src/component";
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    export default {
        components: {
            ElButton,
            ElDialog,},
        props: ["id", "param"],
        data() {
            return {


                mmAndYY:'',
                paramContractDatil:{},
                showContract:false,
                contractYear: ""
            };
        },
        created() {},
        mounted() {
            this.$nextTick(() => {
                this.$forceUpdate();
                this.getContractExpireCus();
                this.getContractExpireHos();

            });
        },

        methods: {

            showHosContractExpireEcharts(xAxisMsg = [], seriesMsg = []) {
                let vm = this;
                let contractHosExpireEchartsOptions = {
                    width: "95%",
                    color: ["#02c2ff"],
                    xAxis: [
                        {
                            type: "category",
                            data: xAxisMsg,
                            axisLabel: {
                                interval: 0,
                                // rotate: 40, // 文字倾斜
                                textStyle: {
                                    align: "center"
                                },
                                color: function(value) {
                                    return (value = "#02c2ff");
                                },
                                formatter(value) {
                                    // 不是01月份的全部把年份给替换掉
                                    if (value.slice(0, 2) !== "01") {
                                        return value.replace(value.slice(2, 7), "");
                                    }
                                    if (value.slice(0, 2) === "01") {
                                        // 将年份换行到下面
                                        return value.replace("-", "\n");
                                    }
                                }
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }
                    ],
                    tooltip: {
                        axisPointer: {
                            type: ""
                        },
                        formatter(params) {
                            return (
                                params.name.slice(3, 7) +
                                params.name.slice(2, 3) +
                                params.name.slice(0, 2) +
                                "月" +
                                " <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow;  display: inline-block;'></i>" +
                                "&nbsp;" +
                                params.value +
                                "份&nbsp;&nbsp;&nbsp;"
                            );
                        },
                        textStyle: {
                            align: "left"
                        }
                    },
                    grid: {
                        left: 5,
                        right: 5,
                        top: 30,
                        bottom: 20,
                        containLabel: true,
                        tooltip: {
                            //---鼠标焦点放在图形上，产生的提示框
                            show: true,
                            trigger: "item", //---触发类型
                            textStyle: {
                                color: "yellow"
                            }
                        }
                    },
                    yAxis: [
                        {
                            type: "value",
                            axisLabel: {
                                show: true
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: {
                                show: true
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            data: seriesMsg,
                            type: "bar",
                            barCategoryGap: "45%", //调整柱子宽度
                            itemStyle: {
                                normal: {
                                    barBorderRadius: [10, 10, 0, 0],
                                    color: new this.$echarts.graphic.LinearGradient(
                                        0,
                                        0,
                                        0,
                                        1,
                                        [
                                            { offset: 0, color: "#55c8eb" },
                                            { offset: 0.5, color: "#4387fd" },
                                            { offset: 1, color: "#5866e0" }
                                        ]
                                    ),
                                    opacity: 0.85
                                },
                                emphasis: {
                                    color: "#ff8d1f"
                                }
                            }
                        }
                    ]
                };
                let hosContractExpireEcharts = this.$refs.hosContractExpireEcharts;
                hosContractExpireEcharts = this.$echarts.init(hosContractExpireEcharts);
                hosContractExpireEcharts.setOption(contractHosExpireEchartsOptions);
                // contractExpireEcharts.on('click',function (params) {
                //     vm.showContract = true;
                //     vm.mmAndYY = params.name;
                //
                // })
                window.addEventListener('resize', function() {
                    document.querySelector("#hosContractExpireEcharts").style.width =
                        (window.innerWidth - 199) / 2 + "px",
                        hosContractExpireEcharts.resize();

                });

            },

            showCusContractExpireEcharts(xAxisMsg = [], seriesMsg = []) {
                let vm = this;
                let cusContractExpireEchartsOptions = {
                    width: "95%",
                    color: ["#02c2ff"],
                    xAxis: [
                        {
                            type: "category",
                            data: xAxisMsg,
                            axisLabel: {
                                interval: 0,
                                // rotate: 40, // 文字倾斜
                                textStyle: {
                                    align: "center"
                                },
                                color: function(value) {
                                    return (value = "#02c2ff");
                                },
                                formatter(value) {
                                    // 不是01月份的全部把年份给替换掉
                                    if (value.slice(0, 2) !== "01") {
                                        return value.replace(value.slice(2, 7), "");
                                    }
                                    if (value.slice(0, 2) === "01") {
                                        // 将年份换行到下面
                                        return value.replace("-", "\n");
                                    }
                                }
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }
                    ],
                    tooltip: {
                        axisPointer: {
                            type: ""
                        },
                        formatter(params) {
                            return (
                                params.name.slice(3, 7) +
                                params.name.slice(2, 3) +
                                params.name.slice(0, 2) +
                                "月" +
                                " <br/> " +
                                "<i style='width: 10px; height:10px; background-color: yellow;  display: inline-block;'></i>" +
                                "&nbsp;" +
                                params.value +
                                "份&nbsp;&nbsp;&nbsp;"
                            );
                        },
                        textStyle: {
                            align: "left"
                        }
                    },
                    grid: {
                        left: 5,
                        right: 5,
                        top: 30,
                        bottom: 20,
                        containLabel: true,
                        tooltip: {
                            //---鼠标焦点放在图形上，产生的提示框
                            show: true,
                            trigger: "item", //---触发类型
                            textStyle: {
                                color: "yellow"
                            }
                        }
                    },
                    yAxis: [
                        {
                            type: "value",
                            axisLabel: {
                                show: true
                            },
                            axisTick: {
                                show: false
                            },
                            splitLine: {
                                show: true
                            },
                            axisLine: {
                                symbol: ["none", "arrow"],
                                symbolSize: [5, 7],
                                lineStyle: {
                                    color: "#979797"
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: "2019",
                            data: seriesMsg,
                            type: "bar",
                            barCategoryGap: "45%", //调整柱子宽度
                            itemStyle: {
                                normal: {
                                    barBorderRadius: [10, 10, 0, 0],
                                    color: new this.$echarts.graphic.LinearGradient(
                                        0,
                                        0,
                                        0,
                                        1,
                                        [
                                            { offset: 0, color: "#55c8eb" },
                                            { offset: 0.5, color: "#4387fd" },
                                            { offset: 1, color: "#5866e0" }
                                        ]
                                    ),
                                    opacity: 0.85
                                },
                                emphasis: {
                                    color: "#ff8d1f"
                                }
                            }
                        }
                    ]
                };
                let cusContractExpireEcharts = this.$refs.cusContractExpireEcharts;
                cusContractExpireEcharts = this.$echarts.init(cusContractExpireEcharts);
                cusContractExpireEcharts.setOption(cusContractExpireEchartsOptions);
                // contractExpireEcharts.on('click',function (params) {
                //     vm.showContract = true;
                //     vm.mmAndYY = params.name;
                //
                // })
                // window.onresize = function() {
                //     document.querySelector("#cusContractExpireEcharts").style.width =
                //         window.innerWidth - 320 + "px";
                //     cusContractExpireEcharts.resize();
                //
                // };

                window.addEventListener('resize', function() {
                    document.querySelector("#cusContractExpireEcharts").style.width =
                        (window.innerWidth - 199) / 2 + "px",
                        cusContractExpireEcharts.resize();

                });

            },

            // 通过当前年份来获取合同数
            async getContractExpireCus() {
                // var myDate = new Date();
                // var year = myDate.getFullYear();
                let res = await this.$ajax.post("/report/contractPeriodBoardCus", {
                    entity: {  }
                });
                let result = res.data.result;
                let xAxisMsg = [];
                let seriesMsg = [];
                result.filter((item, index, arr) => {
                    xAxisMsg.push(item.contractMonth + "-" + item.contractYear);
                    seriesMsg.push(item.contractNum);
                });
                this.showCusContractExpireEcharts(xAxisMsg, seriesMsg);
            },
            async getContractExpireHos() {
                // var myDate = new Date();
                // var year = myDate.getFullYear();
                let res = await this.$ajax.post("/report/contractPeriodBoardHos", {
                    entity: {  }
                });
                let result = res.data.result;
                let xAxisMsg = [];
                let seriesMsg = [];
                result.filter((item, index, arr) => {
                    xAxisMsg.push(item.contractMonth + "-" + item.contractYear);
                    seriesMsg.push(item.contractNum);
                });
                this.showHosContractExpireEcharts(xAxisMsg, seriesMsg);
            },



        },
    };
</script>

<style lang="less" scoped>
    .contractExpire {
        // min-width: 1200px;
        background-color: #fff;
        overflow-x: hidden;
        padding-bottom: 20px;
        .contractExpireHeadCus {
            width: 100%;
            text-align: left;
            padding-left: 20px;
            overflow: hidden;
            padding-bottom: 12px;
            .title {
                float: left;
                margin-left: 2%;
            }
            .desc {
                float: right;
                margin-right: 8%;
                line-height: 25px;
                padding-top: 4px;
            }
            .el-date-editor {
                float: right;
                width: 100px;
                margin-right: 25px;
            }
            /*.el-input {*/
                /*min-width: 80px;*/
                /*width: 90px;*/
            /*}*/
        }
        .contractExpireHeadHos {
            width: 100%;
            text-align: left;
            padding-left: 20px;
            overflow: hidden;
            padding-bottom: 12px;
            .title {
                float: left;
                margin-left: 2%;
            }
            .desc {
                float: right;
                margin-right: 8%;
                line-height: 25px;
                padding-top: 4px;
            }
            .el-date-editor {
                float: right;
                width: 100px;
                margin-right: 25px;
            }
            /*.el-input {*/
                /*min-width: 80px;*/
                /*width: 90px;*/
            /*}*/
        }
        .contractExpireMain {
            text-align: left;
            padding-left: 20px;
            padding-right: 20px;
        }
        #hosContractExpireEcharts {
            width: 100%;
            height: 380px;
        }
        #cusContractExpireEcharts {
            width: 100%;
            height: 380px;
        }
    }
    .rece-cost-exception-div {
        height: 50px;
    }

    /*-------------------------------公共元素  每个div结束后分隔的时候用----------------------------------------*/

    .park-data-title-font {
        float: left;
        font-size: medium;
        color: #409eff;
        font-weight: bold;
        line-height: 50px;
        margin-left: 15px;
    }

    .edit-button {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }

    .page_down{
        float: left;
    }
    /* -----------------------------------头部区域---------------------------------------- */
</style>

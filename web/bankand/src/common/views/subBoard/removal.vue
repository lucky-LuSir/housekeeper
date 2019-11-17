<template>
    <div class="root"><!--根部分-->
        <div class="park-data-root-div">
            <div class="park-data-title-div">
                <span v-if="parkObj.pkCode" class="park-data-title-font">园区去化看板：{{ parkObj.parkAddress }}</span>
                <span v-if="!parkObj.pkCode" class="park-data-title-font">全部园区去化看板</span>
                <el-button class="edit-button" size="small" type="warning" plain @click="deleteBoard">删除</el-button>
                <el-button class="edit-button" size="small" type="warning" plain @click="editBoard">配置</el-button>
            </div>

            <div>
                <ul class="ul-park-data">
                    <li style="margin-left: -20px;"><span class="park-info-title">已租含公摊面积：</span>{{ parkObj.rentInvestArea }}㎡</li>
                    <li><span class="park-info-title">总产证面积：</span>{{ parkObj.acreage }}㎡</li>
                    <li><span class="park-info-title">已租产证面积：</span>{{ parkObj.rentArea }}㎡</li>
                    <li><span class="park-info-title">空置产证面积：</span>{{ parkObj.rentableArea }}㎡</li>
                    <li><span class="park-info-title">空置率：</span>{{ parkObj.vacancyPercent }}%</li>
                    <li><span class="park-info-title">入驻的客户：</span>{{ contractCount }}位</li>
                    <div style="clear:both"></div>
                </ul>

                <!--条形图-->
                <div style="width: 90%;margin-left: 20px;">
                    <div v-if="parkObj.pkCode" :id="parkObj.pkCode" style="height: 35px;width: 100%"></div>
                    <div v-if="!parkObj.pkCode" id="allParkChart" style="height: 35px;width: 100%"></div>
                </div>


                <div class="chart-root-div" v-for="(item,index) in buildingList">
                    <div class="building-data-div">
                        <span class="building-number-title">{{ item.bdName}}栋</span>
                        <div :id="item.bdCode" class="listChart"></div>
                    </div>

                    <div class="building-info-div">
                        <ul class="building-ul">
                            <li class="ul-li-box"></li>
                            <li>&nbsp;已租产证面积：{{ (item.acreage - item.rentableArea).toFixed(2) }}㎡</li>
                            <li class="ul-li-box1"></li>
                            <li class="ul-li-font1">&nbsp;空置产证面积：{{ item.rentableArea }}㎡</li>
                            <li class="ul-li-font1">空置率：{{ (item.rentableArea / item.acreage * 100).toFixed(2) }}%
                            </li>
                        </ul>
                    </div>
                </div>

                <!--园区参数弹框-->
                <el-dialog  :close-on-click-modal="false" :title="dialogTitle" :visible.sync="isShowParkParam">
                    <el-form ref="parkParam" :rules="rules" :model="parkParam" label-width="80px">
                        <el-form-item label="入住园区 " prop="pkCode">
                            <el-select
                                    v-model="parkParam.pkCode"
                                    filterable
                                    default-first-option
                                    placeholder="输入园区名称或者地址">
                                <el-option
                                        v-for="item in pkNames"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="onParkParamSubmit('parkParam')">立即配置</el-button>
                            <el-button @click="isShowParkParam= false">取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>


                <div class="comm-box-div" style="margin-top: 20px;"></div>

            </div>
        </div>

    </div>
</template>

<script scoped>
    export default {
        props: ['id', 'param'],

        created: function () {
            //解析参数param,调相应方法
            this.queryList(this.param);
            this.parkObj.pkCode = this.param;
        },

        updated(){
            if (this.parkObj.pkCode) {
                this.showChart(this.parkObj.pkCode);
            }

            if (!this.parkObj.pkCode) {
                this.allParkChart();
            }

            let canArea, acreage, chartId;
            if (this.buildingList.length > 0) {
                for (let i = 0; i < this.buildingList.length; i++) {
                    canArea = this.buildingList[i].rentableArea;
                    acreage = this.buildingList[i].acreage;
                    chartId = this.buildingList[i].bdCode;

                    this.showChartPie(canArea, acreage, chartId);
                }
            }
        },

        data() {
            return {
                isShowParkParam: false,
                pkNames: [],
                dialogTitle: "",
                parkParam: {
                    pkCode: ''
                },
                parkObj: {
                    pkCode: "",
                    parkAddress: "",//园区地址
                    acreage: "",//招商总面积
                    rentableArea: "",//剩余未租面积
                    rentArea: "",//已租面积
                    vacancyPercent: "",//空置率
                    rentInvestArea: ""//已租含公摊面积
                },
                contractCount: "",//园区合同总条数
                buildingList: [],
                rules: {
//                    pkCode: [
//                        {required: true, message: '园区名称不能为空', trigger: 'blur'},
//                    ],
                },
            }
        },

        methods: {
            //eCharts 柱状图 组件
            showChart(pkCode){
                //acreage总面积   rentableArea可租用面积
                let vm = this;
                let num1 = vm.parkObj.rentableArea / vm.parkObj.acreage * 100;
                let numberData = num1.toFixed(2);
                let num2 = 100 - numberData;//计算已租面积 百分比

                let myChart = this.$echarts.init(document.getElementById(pkCode));

                let option = {
                    grid: {
                        x: 10,
                        y: 20,
                        x2: 10,
                        y2: 20,
                        borderWidth: 1
                    },
                    tooltip: {
                        trigger: 'item',
                    },
                    color: ['#9c9c9c', 'rgba(255, 153, 0, 1)'],
                    xAxis: [{
                        type: 'value',
                        show: false
                    }],
                    yAxis: [{
                        type: 'category',
                        show: false,
                        data: ['总面积']
                    }],
                    series: [
                        {
                            name: '可出租',
                            type: 'bar',
                            stack: '总面积',
                            barWidth: 35,

                            data: [{
                                value: '65',
                                label: {
                                    normal: {
                                        color: '#333333',
                                        show: true,
                                        formatter: [
                                            '{c}' + '%'
                                        ].join('\n')
                                    }
                                }
                            }]
                        },
                        {
                            name: '已出租',
                            type: 'bar',
                            stack: '总面积',
                            barWidth: 35,

                            data: [{
                                value: '32',
                                label: {
                                    normal: {
                                        color: '#333333',
                                        show: true,
                                        formatter: [
                                            '{c}' + '%'
                                        ].join('\n')
                                    }
                                }
                            }]
                        },
                    ]
                };

                if (numberData == 100) {
                    option.series[0].data[0].value = numberData;
                    option.series[1].data[0].value = '';
                } else if (numberData == 0) {
                    option.series[0].data[0].value = '';
                    option.series[1].data[0].value = num2.toFixed(2);
                } else {
                    option.series[0].data[0].value = numberData;
                    option.series[1].data[0].value = num2.toFixed(2);
                }
                myChart.setOption(option);
            },

            //eCharts 柱状图 组件
            allParkChart(){
                //acreage总面积   rentableArea可租用面积
                let vm = this;
                let num1 = vm.parkObj.rentableArea / vm.parkObj.acreage * 100;
                let numberData = num1.toFixed(2);
                let num2 = 100 - numberData;//计算已租面积 百分比

                let myChart = this.$echarts.init(document.getElementById("allParkChart"));

                let option = {
                    grid: {
                        x: 10,
                        y: 20,
                        x2: 10,
                        y2: 20,
                        borderWidth: 1
                    },
                    tooltip: {
                        trigger: 'item',
                    },
                    color: ['#9c9c9c', 'rgba(255, 153, 0, 1)'],
                    xAxis: [{
                        type: 'value',
                        show: false
                    }],
                    yAxis: [{
                        type: 'category',
                        show: false,
                        data: ['总面积']
                    }],
                    series: [
                        {
                            name: '可出租',
                            type: 'bar',
                            stack: '总面积',
                            barWidth: 35,

                            data: [{
                                value: '65',
                                label: {
                                    normal: {
                                        color: '#333333',
                                        show: true,
                                        formatter: [
                                            '{c}' + '%'
                                        ].join('\n')
                                    }
                                }
                            }]
                        },
                        {
                            name: '已出租',
                            type: 'bar',
                            stack: '总面积',
                            barWidth: 35,

                            data: [{
                                value: '32',
                                label: {
                                    normal: {
                                        color: '#333333',
                                        show: true,
                                        formatter: [
                                            '{c}' + '%'
                                        ].join('\n')
                                    }
                                }
                            }]
                        },
                    ]
                };

                if (numberData == 100) {
                    option.series[0].data[0].value = numberData;
                    option.series[1].data[0].value = '';
                } else if (numberData == 0) {
                    option.series[0].data[0].value = '';
                    option.series[1].data[0].value = num2.toFixed(2);
                } else {
                    option.series[0].data[0].value = numberData;
                    option.series[1].data[0].value = num2.toFixed(2);
                }
                myChart.setOption(option);
            },

            //eCharts 饼状图 组件
            showChartPie(canArea, acreage, chartId) {

                //canArea：可租面积      acreage：总面积
                let num1 = canArea / acreage * 100;
                let numberData = num1.toFixed(2);
                let num2 = 100 - numberData;

                let myChart = this.$echarts.init(document.getElementById(chartId));

                let option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)",
                        position: function (p) {
                            // 位置回调
                            return [p[0] + 1, p[1] - 10];
                        },
                    },
                    series: [
                        {
                            name: '楼栋面积信息',
                            type: 'pie',
                            center: ['50%', '50%'],
                            data: [],
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#FFF',
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 2,
                                    borderColor: '#ffffff',
                                },
                                emphasis: {
                                    borderWidth: 0,
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            },
                            color: [
                                '#9c9c9c',
                                '#FF8720',
                            ]
                        }
                    ]
                };
                //设置option相应数据
                option.series[0].data = [
                    {value: canArea, name: '空置面积'},
                    {value: acreage - canArea, name: '已租面积'}
                ];
                myChart.setOption(option);
            },

            //接收子组件传递的 pkCode
            queryList(pkCode){
                //园区去化看板
                    this.showPark(pkCode);
                    this.showConCount(pkCode);
            },

            //园区看板数据
            showPark(pkCode){
                let vm = this;
                let obj = {
                    entity: {
                        pkCode: pkCode
                    }
                };

                if (pkCode) {
                    //根据 pkCode 查询园区
                    let options = {
                        method: 'POST',
                        data: obj,
                        url: 'hkp/selectByPkCode'
                    };
                    this.$ajax(
                        options
                    ).then(response => {

                        vm.buildingList = response.data.result.buildingEntities;//获取楼栋集合
                        vm.parkObj.parkAddress = response.data.result.parkAddress;//获取园区地址
                        vm.parkObj.acreage = response.data.result.acreage;//园区招商总面积
                        vm.parkObj.rentableArea = response.data.result.rentableArea;//园区可租用面积
                        vm.parkObj.rentInvestArea = response.data.result.rentInvestArea;//已租含公摊面积

                        vm.parkObj.rentArea = (vm.parkObj.acreage - vm.parkObj.rentableArea).toFixed(2);//计算园区已出租面积
                        let vacancyPercent = vm.parkObj.rentableArea / vm.parkObj.acreage * 100;//计算园区空置率
                        vm.parkObj.vacancyPercent = vacancyPercent.toFixed(2);//园区空置率保存两位小数
                    }).catch(error => {
                        vm.$alert('页面:园区去化看板查询园区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                } else {
                    // pkCode 为空的情况下查询所有园区的面积
                    let options = {
                        method: 'POST',
                        data: obj,
                        url: 'hkp/getAllParkSum'
                    };
                    this.$ajax(
                        options
                    ).then(response => {

                        vm.parkObj.acreage = response.data.result.acreage;//园区招商总面积
                        vm.parkObj.rentableArea = response.data.result.rentableArea;//园区可租用面积

                        vm.parkObj.rentArea = (vm.parkObj.acreage - vm.parkObj.rentableArea).toFixed(2);//计算园区已出租面积
                        let vacancyPercent = vm.parkObj.rentableArea / vm.parkObj.acreage * 100;//计算园区空置率
                        vm.parkObj.vacancyPercent = vacancyPercent.toFixed(2);//园区空置率保存两位小数
                    }).catch(error => {
                        vm.$alert('页面:园区去化看板查询园区信息失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    })
                }


            },

            //根据pkCode查询园区的入驻客户数
            showConCount(pkCode){

                if (pkCode == "") {
                    pkCode = null;
                }
                let vm = this;
                let obj = {
                    entity: {
                        pkCode: pkCode
                    }
                };

                //根据 pkCode 查询园区
                let options = {
                    method: 'POST',
                    data: obj,
                    url: 'contract/queryConByPkCode'
                };
                this.$ajax(
                    options
                ).then(response => {
                    vm.contractCount = response.data.result;
                }).catch(error => {
                    vm.$alert('页面:园区去化看板查看入驻人数失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                            vm.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });
                })
            },

            //通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id: this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '删除看板成功',
                            type: 'success'
                        });
                        this.$router.replace('/back')
                    }).catch(error => {
                        vm.$alert('页面:删除园区去化看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                                vm.$message({
                                    type: 'info',
                                    message: `action: ${ action }`
                                });
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //配置按钮
            editBoard() {
                this.dialogTitle = "园区去化看板参数配置";
                let vm = this;
                vm.isShowParkParam = true;
                //房源搜索栏
                let pkobj = {};
                let options = {
                    method: 'POST',
                    data: pkobj,
                    url: "hkp/list",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    let result = response.data.result;
                    let pkNameList = [];
                    let pkObj = [];
                    for (let s = 0; s < result.length; s++) {
                        let code = result[s].pkCode;
                        let name = result[s].pkName;
                        pkNameList.push(
                            {code: code, name: name}
                        );
                        pkObj[code] = name;
                    }
                    let aObj = {code: "", name: "全部园区"};
                    vm.pkNames = pkNameList;
                    vm.pkNames.unshift(aObj);

                    vm.pkObj = pkObj;
                }).catch(function (error) {
                    vm.$alert('页面:园区去化看板房源搜索失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                            vm.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });
                });
                vm.parkParam.pkCode = vm.parkObj.pkCode;

            },
            onParkParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        this.isShowParkParam = false;

                        let vm = this;
                        //房源搜索栏
                        let param = {};
                        let entity = {
                            id: this.id,
                            param: this.parkParam.pkCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url: "board/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '配置看板成功',
                                type: 'success'
                            });
                            vm.$router.replace('/back');

                        }).catch(function (error) {
                            vm.$alert('页面:配置园区去化看板失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    vm.$message({
                                        type: 'info',
                                        message: `action: ${ action }`
                                    });
                                }
                            });
                        });


                    }
                });
            },
        }
    }
</script>

<style scoped>
    .root {
        margin-right: 15px;
        min-width: 1000px;
        height: auto;
        max-height: 480px;
        background-color: white;

        overflow-x: hidden;
    }

    /*园区去化看板*/
    .park-data-root-div {

    }

    /*园区去化看板标题*/
    .park-data-title-div {
        height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*园区去化看板文字*/
    .park-data-title-font {
        font-size: medium;
        color: #FF9900;
        font-weight: bold;
        line-height: 50px;
        margin-left: 15px;
    }

    /*编辑按钮*/
    .edit-button {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }

    /*园区数据展示ul*/
    .ul-park-data {
        list-style: none;
        margin-left: 10px;
    }

    .ul-park-data li {
        float: left;
        margin-left: 3%;
        color: #919191;
    }

    /*总面积 已出租 空置面积 空置率 洽谈合同的客户  文字样式*/
    .park-info-title {
        color: #6b6b6b;
        font-weight: bold;
    }

    /*eCharts根 div*/
    .chart-root-div {
        width: 300px;
        height: 130px;
        border: solid 1px;
        margin-left: 30px;
        margin-top: 20px;
        float: left;
    }

    /* eCharts */
    .listChart {
        width: 95px;
        height: 95px;
    }

    /*楼栋div*/
    .building-data-div {
        width: 100px;
        height: 100%;
        float: left;
    }

    /*楼栋名称 A栋 B栋*/
    .building-number-title {
        margin-left: 10px;
        line-height: 30px;
        color: #FF9900;
    }

    /*楼栋div*/
    .building-info-div {
        width: 140px;
        height: 100%;
        float: right;
    }

    .building-ul {
        list-style: none;
        margin-left: -100px;
        margin-top: 45px;
        font-size: 13px;
    }

    /*小方框*/
    .ul-li-box {
        float: left;
        width: 17px;
        height: 17px;
        background-color: #FF9900;
    }

    .ul-li-box1 {
        float: left;
        width: 17px;
        height: 17px;
        background-color: #9c9c9c;
        margin-top: 10px;
    }

    /*空置面积  空置率  li*/
    .ul-li-font1 {
        margin-top: 10px;
    }

</style>
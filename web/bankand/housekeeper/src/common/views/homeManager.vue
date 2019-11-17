<template slot-scope="scope">
    <div style="overflow-y: auto;"  ref="myRoot">
        <el-header>
            <el-row :gutter="5">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <p class="title">当月业绩进度榜单</p>
                </el-col>

                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <p class="title">服务品质情况</p>
                </el-col>
            </el-row>

            <el-row :gutter="5">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div class="achievement_div">
                        <div class="content_div">
                            <p class="content_title content_title_achievement">业绩光荣榜</p>
                            <div class="img_list_div">
                                <div class="img_div" v-for="id in achievement">

                                    <p class="content_img"><img width="100%;"
                                                                :src="'http://f.kufangwuyou.com/' + id.userImg"/>
                                    </p>

                                    <p class="content_department">
                                        {{id.ownerDeptName}}({{id.userName}})
                                    </p>

                                </div>
                            </div>
                        </div>
                        <div class="content_div">
                            <p class="content_title bad content_title_achievement">业绩落后榜</p>
                            <div class="img_list_div">

                                <div class="img_div" v-for="id in achievementBad">
                                    <p class="content_img"><img width="100%;"
                                                                :src="'http://f.kufangwuyou.com/' + id.userImg"/>
                                    </p>
                                    <p class="content_department">
                                        {{id.ownerDeptName}}({{id.userName}})
                                    </p>
                                </div>

                            </div>
                        </div>
                    </div>
                </el-col>

                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div class="achievement_div">
                        <div class="content_div">
                            <p class="content_title content_title_service">服务品质最好</p>
                            <div class="img_list_div">

                                <div class="img_div" v-for="id in service">
                                    <p class="content_img"><img width="100%;"
                                                                :src="'http://f.kufangwuyou.com/' + id.userImg"/>
                                    </p>
                                    <p class="content_department">
                                        {{id.ownerDeptName}}({{id.userName}})
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="content_div">
                            <p class="content_title  bad content_title_service">服务品质最差</p>
                            <div class="img_list_div">

                                <div class="img_div" v-for="id in serviceBad">
                                    <p class="content_img"><img width="100%;"
                                                                :src="'http://f.kufangwuyou.com/' + id.userImg"/>
                                    </p>
                                    <p class="content_department">
                                        {{id.ownerDeptName}}({{id.userName}})
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-col>
            </el-row>

            <el-row :gutter="5">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <p class="title">当月业绩进度榜单</p>
                </el-col>

                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <p class="title">我的业务数据(本月)</p>
                </el-col>
            </el-row>

            <el-row :gutter="5">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div class="achievement_div">
                        <div class="content_div">
                            <p class="content_title content_title_standard">标准落地最好</p>
                            <div class="img_list_div">

                                <div class="img_div" v-for="id in standard">
                                    <p class="content_img"><img width="100%;"
                                                                :src="'http://f.kufangwuyou.com/' + id.userImg"/>
                                    </p>
                                    <p class="content_department">{{id.ownerDeptName}}({{id.userName}})</p>
                                </div>

                            </div>
                        </div>
                        <div class="content_div">
                            <p class="content_title  bad content_title_standard">标准落地最差</p>
                            <div class="img_list_div">

                                <div class="img_div" v-for="id in standardBad">
                                    <p class="content_img"><img width="100%;"
                                                                :src="'http://f.kufangwuyou.com/' + id.userImg"/>
                                    </p>
                                    <p class="content_department">{{id.ownerDeptName}}({{id.userName}})</p>
                                </div>

                            </div>
                        </div>
                    </div>
                </el-col>

                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div class="achievement_div">
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <h4 style="font-weight: bold;color: white;">业绩</h4>
                            </div>
                            <tr>
                                <td class="text_padding">业绩指标: <label
                                        class="number_padding">{{myAchievement.totalMoneyValue}}</label>元
                                </td>
                                <td class="text_padding">开单金额: <label
                                        class="number_padding">{{myAchievement.orderProfit}}</label>元
                                </td>
                                <td class="text_padding">分成金额: <label
                                        class="number_padding">{{myAchievement.divideProfit}}</label>元
                                </td>
                            </tr>
                        </el-card>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <h4 style="font-weight: bold;color: white;">房源</h4>
                            </div>
                            <tr>
                                <td class="text_padding">开发指标: <label
                                        class="number_padding">{{myAchievement.defineAddHosCount}}</label>元
                                </td>
                                <td class="text_padding">实际完成: <label
                                        class="number_padding">{{myAchievement.addHosCount}}</label>元
                                </td>
                                <td class="text_padding">拜访指标: <label
                                        class="number_padding">{{myAchievement.defineOwnerVisitCount}}</label>元
                                </td>
                                <td class="text_padding">实际完成: <label
                                        class="number_padding">{{myAchievement.ownerVisitCount}}</label>元
                                </td>
                            </tr>
                        </el-card>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <h4 style="font-weight: bold;color: white;">客户</h4>
                            </div>
                            <tr>
                                <td class="text_padding">开发指标: <label
                                        class="number_padding">{{myAchievement.defineAddCusCount}}</label>元
                                </td>
                                <td class="text_padding">实际完成: <label
                                        class="number_padding">{{myAchievement.addCusCount}}</label>元
                                </td>
                                <td class="text_padding">跟进指标: <label
                                        class="number_padding">{{myAchievement.defineFollowupCount}}</label>元
                                </td>
                                <td class="text_padding">实际完成: <label
                                        class="number_padding">{{myAchievement.followupCount}}</label>元
                                </td>
                            </tr>
                        </el-card>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <h4 style="font-weight: bold;color: white;">兼职</h4>
                            </div>
                            <tr>
                                <td class="text_padding">开发指标: <label
                                        class="number_padding">{{myAchievement.defineAddUserCount}}</label>元
                                </td>
                                <td class="text_padding">实际完成: <label
                                        class="number_padding">{{myAchievement.addUserCount}}</label>元
                                </td>
                                <td class="text_padding_two">推荐客户数量: <label class="number_padding">{{myAchievement.recHosCount}}</label>元
                                </td>
                            </tr>
                            <tr>

                                <td class="text_padding_two">推荐房源数量: <label class="number_padding">{{myAchievement.recCusCount}}</label>元
                                </td>
                                <td class="text_padding_two">推荐成交金额: <label class="number_padding">{{myAchievement.recTurnVolume}}</label>元
                                </td>
                            </tr>
                        </el-card>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <p class="title">客户合同周期提醒</p>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <p class="title">房源合同周期提醒</p>
                </el-col>
            </el-row>
            <el-row>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div class="achievement_div">
                        <div class="content_div">
                            <cus-contract-census ref="cusContract"/>
                        </div>
                    </div>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div class="achievement_div">
                        <div class="content_div">
                            <hos-contract-census ref="refHosContractCensus"/>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </el-header>
    </div>

</template>

<script scoped>
    import ElForm from "../../../node_modules/element-ui/packages/form/src/form";
    import ElMain from "../../../node_modules/element-ui/packages/main/src/main";
    import cusContractCensus from "../../cus/views/cusContractCensus.vue";
    import ElRow from "element-ui/packages/row/src/row";
    import hosContractCensus from "../../hos/views/hosContractCensus"
    export default{
        components: {
            ElRow,
            ElMain,
            ElForm,
            cusContractCensus,
            hosContractCensus,
        },
        created: function () {
            var vm = this;
            this.loadAchievement();
        },
        data(){
            return {
                fullHeight: document.documentElement.clientHeight,
                achievement: [],
                service: [],
                standard: [],
                achievementBad: [],
                serviceBad: [],
                standardBad: [],
                myAchievement: [],

                // 动态高度
                floatDivHeight: ''
            }
        },

        mounted(){
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
        },
        watch: {
            fullHeight (val) {
                if(!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function (){
                        that.timer = false
                    },400)
                }
            }
        },
        methods: {
            //加载订单列表数据
            loadAchievement(){
                var vm = this;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    url: "announcement/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if (!response.data.isException) {

                    }
                    vm.achievement = response.data.result.achievement;
                    vm.service = response.data.result.service;
                    vm.standard = response.data.result.standard;
                    vm.achievementBad = response.data.result.achievementBad;
                    vm.serviceBad = response.data.result.serviceBad;
                    vm.standardBad = response.data.result.standardBad;
                    vm.myAchievement = response.data.result.myAchievement;

                    let h = vm.floatDivHeight;
                    h = 700;
                    vm.floatDivHeight = h - 1 + "px";
                }).catch(function (error) {
                    /* vm.$message.error('页面:获取数据失败!user/query');*/
                });
            },

            //打开房源合同周期
            openHosContractCensus(){
                this.$refs.refHosContractCensus.handleSizeChange(100);
            }
        }
    }

</script>


<style scope>

    .achievement_div {
        box-shadow: 0px 5px 8px 5px rgba(63, 63, 64, 0.18);
        height: 500px;
        width: 100%;
        padding: 5px 5px 5px 5px;
        margin: 5px 5px 5px 5px;
        border-radius: 10px;
    }

    .title {
        width: 90%;
        text-align: center;
        font-size: 20px;
        font-weight: 900;
        padding: 10px 10px 10px 20px;
    }

    .content_div {
        width: 100%;
        height:50%;
        padding: 0px 5px 0px 5px;
    }

    .label_content_div {
        float: left;
        width: 5%;
        height: 12%;
        padding: 0px 0px 0px 5px;
        margin: 5px 5px 5px 0px;
        border-radius: 10px;
        border-style: solid;
        border-color: #10AEFF;
        border-width: 2px;
    }

    .text_content_div {
        float: left;
        width: 95%;
        height: 16%;
        padding: 6px 0px 0px 8px;
        margin: 10px 0px 20px 0px;
        border-radius: 10px;
        border-style: solid;
        border-color: #10AEFF;
        border-width: 0px;
    }

    .text_content_div_two {
        float: left;
        width: 95%;
        height: 23%;
        padding: 0px 0px 0px 8px;
        margin: 0px 0px 20px 0px;
        border-radius: 10px;
        border-style: solid;
        border-color: #10AEFF;
        border-width: 0px;
    }

    .text_padding {
        width: 500px;
        font-size: 16px;
        text-align: left;
        padding: 0px 0px 0px 0px;
        display: table-cell;
    }

    .text_padding_two {
        width: 500px;
        font-size: 16px;
        text-align: left;
        padding: 0px 0px 0px 0px;
        display: table-cell;
    }

    .number_padding {
        font-size: 18px;
        color: red;
        text-align: center;
    }

    .content_title {
        float: left;
        width: 3%;
        font-size: 15px;
        line-height: 24px;
        font-weight: 900;
        color: #10AEFF;
        height: 100%;
    }

    .bad {
        color: black;
    }

    .content_title_achievement {
        line-height: 30px;
    }

    .content_title_service {
        line-height: 30px;
    }

    .content_title_standard {
        line-height: 30px;
    }

    .img_list_div {
        width: 97%;
        float: right;
        height: 100%;
    }

    .img_div {
        height: 78%;
        float: left;
        width: 22%;
        margin-left: 7%;
    }

    .content_img {
        margin-top: 6px;
        margin-bottom: 20px;
        height: 85%;
        width: 100%;
        border-radius: 10px;
    }

    .content_img img {
        height: 110%;
        border-radius: 7px;
    }

    .content_department {
        margin-bottom: 30px;
        padding-bottom: 20px;
    }

    .content_img img {
        height: 100%;
        border-radius: 7px;
    }

    .text {
        font-size: 16px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .box-card {
        width: 100%;
        margin-top: 1px;
    }

    .el-card__header {
        padding: 10px 20px;
        border-bottom: 1px solid #EBEEF5;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        background: #409EFF;
        font-weight: bold;
    }

    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
    }


</style>
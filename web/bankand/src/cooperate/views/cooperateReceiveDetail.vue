<template>
    <el-dialog  :close-on-click-modal="false" :style="{height:fullHeight +140 +'px'}" top="30px" width="85%"
               :visible.sync="visible"
               :before-close="closeDialog"
               title="合作详情"
               append-to-body>
        <el-container :style="{height: fullHeight -140 + 'px'}" style="border: 1px solid #eee"
                      v-loading="loading"
                      element-loading-text="拼命加载中">
            <el-aside width="35%" class="el-aside">
                <el-card class="box-border" :style="{height: fullHeight/2 -100 + 'px'}" shadow="hover">
                    <el-row>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <img  :style="{height: fullHeight/4 + 'px',width:fullHeight/5 +'px'}"
                                  :src="'http://hkp.kufangwuyou.com/housekeeper/file/read/'
                                 + cooperateEntity.cooUser.userImg ">
                        </el-col>
                        <el-col style="padding-top: 10px;font-weight: bold;" :xs="16" :sm="16"
                                :md="16" :lg="16" :xl="16">
                            <el-row style="margin-top: 40px">
                                <span class="el-icon-user" style="margin-left: 20px"/>
                                <span style="font-size: 16px;margin-left: 10px">
                                    {{cooperateEntity.cooUser.userName}}
                                </span>
                                <span style="margin-left: 20px" class="el-icon-unlock"/>
                                <span style="font-size: 14px;margin-left: 10px">实名认证</span>
                            </el-row>
                            <el-row style="margin-top: 40px">
                                <span style="margin-left: 20px" class="el-icon-phone"/>
                                联系电话：
                                <el-tag type="primary" size="small">
                                    {{cooperateEntity.cooUser.userPhone}}
                                </el-tag>
                            </el-row>
                        </el-col>
                    </el-row>
                    <el-row style="margin-top: 10px">
                        <el-tag type="primary" size="small">
                            于{{cooperateEntity.createTime | time}}向您申请
                        </el-tag>
                    </el-row>
                </el-card>
                <el-card class="box-border" :style="{height: fullHeight/2 - 80 + 'px'}" shadow="hover">
                    <el-row>
                        <div>
                            <div style="font-weight: bold;font-size: 14px;margin-top: 10px"
                                 v-if="cooperateEntity.applyType=='1'">
                                <p><span class="el-icon-chat-dot-round"
                                         style="color: darkorange"/>
                                    TA申请与您 {{cooperateEntity.streetName}} {{cooperateEntity.area}}㎡的房源进行合作
                                </p>
                                <p>
                                    期望分成<span style="color: red">{{cooperateEntity.dividePercentage}}</span>%
                                </p>
                                <p style="margin-top: 40px;">
                                    <el-button type="primary" size="small" @click="openHos()">
                                        <span style="font-weight: bold">房源标题:{{cooperateEntity.houseName}}</span>
                                    </el-button>
                                </p>
                            </div>
                            <div style="font-weight: bold;font-size: 14px;"
                                 v-if="cooperateEntity.applyType=='2'">
                                <p>
                                            <span class="el-icon-chat-dot-round"
                                                  style="color: darkorange"/>
                                    TA期望与您的 客户进行合作
                                </p>
                                <p>
                                    期望分成<span style="color: red">{{cooperateEntity.dividePercentage}}</span>%
                                </p>
                                <p style="margin-top: 40px;">
                                    <el-button size="small" type="primary" @click="openCus()">
                                        客户信息:{{cooperateEntity.cusName}}
                                        {{cooperateEntity.products}}
                                        需要{{cooperateEntity.needAcreage}}㎡左右
                                    </el-button>
                                </p>
                            </div>
                        </div>
                    </el-row>
                    <el-row style="margin-bottom: 10px">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="0">
                            <el-row v-if="cooperateEntity.cooStatus=='1'" style="margin-top: 40px">
                                <el-row>
                                    <el-button @click="reply()" icon="el-icon-edit-outline"
                                               type="primary" size="medium"
                                               circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #409EFF;font-weight: bold">答复</span>
                                </el-row>
                            </el-row>
                            <el-row v-if="cooperateEntity.cooStatus=='2'" style="margin-top: 40px">
                                <el-row>
                                    <el-button icon="el-icon-close" type="danger" size="medium"
                                               circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #F56C6C;font-weight: bold">已拒绝</span>
                                </el-row>
                            </el-row>
                            <el-row v-if="cooperateEntity.cooStatus=='3'" style="margin-top: 40px">
                                <el-row>
                                    <el-button icon="el-icon-success" type="success" size="medium"
                                               circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #67C23A;font-weight: bold">已接受</span>
                                </el-row>
                            </el-row>
                            <el-row v-if="cooperateEntity.cooStatus=='4'" style="margin-top: 40px">
                                <el-row>
                                    <el-button icon="el-icon-success" type="success" size="medium"
                                               circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #67C23A;font-weight: bold">已带看</span>
                                </el-row>
                            </el-row>
                            <el-row v-if="cooperateEntity.cooStatus=='5'" style="margin-top: 40px">
                                <el-row>
                                    <el-button icon="el-icon-success" type="success" size="medium"
                                               circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #67C23A;font-weight: bold">已签约</span>
                                </el-row>
                            </el-row>
                            <el-row v-if="cooperateEntity.cooStatus=='6'" style="margin-top: 40px">
                                <el-row>
                                    <el-button icon="el-icon-success" type="success" size="medium"
                                               circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #67C23A;font-weight: bold">已结束</span>
                                </el-row>
                            </el-row>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="0">
                            <el-row v-if="cooperateEntity.cooStatus=='3'" style="margin-top: 40px">
                                <el-row>
                                    <el-button v-if="cooperateEntity.cooOpenFlag==false"
                                               @click="openCoo" icon="el-icon-view"
                                               size="medium" circle/>
                                    <el-button v-if="cooperateEntity.cooOpenFlag==true"
                                               @click="hidenCoo" type="primary" icon="el-icon-view"
                                               size="medium" circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span v-if="cooperateEntity.cooOpenFlag==false"
                                          style="font-weight: bold">公开</span>
                                    <span v-if="cooperateEntity.cooOpenFlag==true"
                                          style="font-weight: bold">隐藏</span>
                                </el-row>
                            </el-row>
                        </el-col>
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="0">
                            <el-row v-if="cooperateEntity.cooStatus=='3'" style="margin-top: 40px">
                                <el-row>
                                    <el-button @click="endCooperate()"
                                               icon="el-icon-turn-off-microphone" type="danger"
                                               size="medium" circle/>
                                </el-row>
                                <el-row style="margin-top: 5px">
                                    <span style="color: #F56C6C;font-weight: bold">结束合作</span>
                                </el-row>
                            </el-row>
                        </el-col>
                    </el-row>

                </el-card>
            </el-aside>

            <el-aside width="65%">
                <div>
                    <!--未拒绝-->
                    <el-tabs v-if="cooperateEntity.cooStatus!='2'" type="border-card"
                             v-model="dislogType"
                             @tab-click="typeCheck()">
                        <el-tab-pane v-if="cooperateEntity.applyType == '1'" label="我有以下几位准客户"
                                     name="1">
                            <hos-match-cus ref="hosMatchCuss"/>
                        </el-tab-pane>
                        <el-tab-pane v-if="cooperateEntity.applyType == '2'" label="我有以下房可以推荐"
                                     name="1">
                            <cus-match-hos ref="cusMatchHoss"/>
                        </el-tab-pane>
                        <el-tab-pane label="合作动态" name="2">
                            <hos-followup-list :houseCodeFollowup="houseCodeFollowup"
                                               v-if="cooperateEntity.applyType == '1'"
                                               ref="hosFollowup"/>
                            <cus-followup-list v-if="cooperateEntity.applyType == '2'"
                                               ref="cusFollowup"/>
                        </el-tab-pane>
                    </el-tabs>
                    <!--已拒绝-->
                    <div v-if="cooperateEntity.cooStatus=='2'">
                        <p style="font-size: 16px;margin-top: 10px;font-weight: bold;color: #2aabd2">
                            对方婉拒了您的合作请求！并给您留言:
                        </p>
                        <el-input style="margin-top: 20px;font-size: 16px;font-weight: bold;"
                                  type="textarea" :rows="16"
                                  v-model="cooperateEntity.rejectReason"/>
                    </div>
                </div>
            </el-aside>
        </el-container>

        <cus-detail ref="cusDetails"/>

        <hos-detail ref="hosDetails"/>

        <coo-reply @reply="getCooperateDetails" ref="cooReplys"/>


    </el-dialog>
</template>

<script>
    import ElCol from "element-ui/packages/col/src/col";
    import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import cusMatchHos from "../../cus/views/cusMatchHos.vue";
    import hosMatchCus from "../../hos/views/hosMatchCus.vue";
    import hosDetail from "../../hos/views/hosDetails.vue";
    import cusDetail from "../../cus/views/cusDetails.vue";
    import ElRow from "element-ui/packages/row/src/row";
    import cusFollowupList from "../../cus/views/cusFollowupListForCusDetail.vue";
    import hosFollowupList from "../../hos/views/hosFollowupList.vue";
    import cooReply from "./cooperateReply.vue"
    export default {
        components: {
            ElRow,
            ElButton,
            ElTag,
            ElCol,
            cusMatchHos,
            hosMatchCus,
            cusDetail,
            hosDetail,
            cusFollowupList,
            hosFollowupList,
            cooReply
        },
        data() {

            return {
                houseCodeFollowup: null,
                dislogType: '',//面板类型
                fullHeight: document.documentElement.clientHeight,
                cooCode: null,
                houseCodeMatchCus: '',
                cooperateEntity: {},
                visible: false,
                cusVisible: false,
                hosVisible: false
            }
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight;
                    that.fullHeight = window.fullHeight
                })()
            }
        },
        watch: {
            fullHeight(val) {
                if (!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function () {
                        that.timer = false
                    }, 400)
                }
            }
        },

        created() {
            this.handleSizeChange(30);
        },


        methods: {
            openCoo(){

                var vm = this;
                var obj = {}
                var sendObj = {}
                sendObj.entity = {"cooCode": vm.cooCode}
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cooperate/openCooFlag",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                    vm.getCooperateDetails();
                });
            },
            hidenCoo(){
                var vm = this;
                var obj = {}
                var sendObj = {}
                sendObj.entity = {"cooCode": vm.cooCode}
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cooperate/closeCooFlag",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                    vm.getCooperateDetails();
                });
            },
            reply(){
                var vm = this;
                vm.$refs.cooReplys.visible = true;
                vm.$refs.cooReplys.cooCode = vm.cooperateEntity.cooCode;
            },


            typeCheck(){
                var vm = this;
                if (vm.dislogType != null) {
                    switch (vm.dislogType) {
                        case "1":
                            if (vm.cooperateEntity.applyType == '1') {
                                vm.cusMatching();
                            } else if (vm.cooperateEntity.applyType == '2') {
                                vm.openCusMatchHos();
                            }
                            break;
                        case "2":
                            if (vm.cooperateEntity.applyType == '1') {
                                vm.findHosFollowup();
                            } else if (vm.cooperateEntity.applyType == '2') {
                                vm.findCusFollowup();
                            }

                            break;
                    }
                }
            },
            //合作详情
            getCooperateDetails(){
                var vm = this;
                vm.visible = true
                vm.loading = true;
                vm.seeInfo = false;
                var obj = {}
                var sendObj = {}
                sendObj.entity = {"cooCode": vm.cooCode}
                var config = {
                    conf: {
                        url: "cooperate/detail",
                        data: sendObj
                    },
                    success(data){
                        vm.cooperateEntity = data.result
                        vm.loading = false;
                    },
                    fail(data){

                    }
                };
                this.$request(config);
            },
            //打开客户智能匹配房源面板
            openCusMatchHos(){
                var vm = this;
                vm.$refs.cusMatchHoss.cusCodeMatchHos = vm.cooperateEntity.cusCode;//传值给组件
                vm.$nextTick(() => {
                    vm.$refs.cusMatchHoss.handleSizeChange(30);
                })
            },
            //打开 房源智能匹配客户
            cusMatching(){
                var vm = this;
                vm.$refs.hosMatchCuss.houseCodeMatchCus = vm.cooperateEntity.houseCode;
                vm.$refs.hosMatchCuss.handleSizeChange(30);
            },

            //关闭房源详情面板
            closeDialog(done){
                var vm = this;
                if (vm.cooperateEntity.cooStatus != '2') {
                    if (vm.cooperateEntity.applyType == '1') {
                        vm.$refs.hosMatchCuss.closeDialog();
                    } else if (vm.cooperateEntity.applyType == '2') {
                        vm.$refs.cusMatchHoss.closeDialog();
                    }
                }
                vm.dislogType = '';
                done()
            },
            openHos(){
                var vm = this;
                vm.$refs.hosDetails.houseCodeDetails = vm.cooperateEntity.houseCode;
                vm.$refs.hosDetails.getHouseDetails();
            },
            openCus(){
                var vm = this;
                vm.$refs.cusDetails.cusCodeDetails = vm.cooperateEntity.cusCode;
                vm.$refs.cusDetails.visible = true;
                vm.$refs.cusDetails.getCusDetails();
            },

            findCusFollowup(){
                var vm = this;
                vm.$refs.cusFollowup.customerQuery.cusCode = vm.cooperateEntity.cusCode;//传值给组件
                vm.$refs.cusFollowup.handleSizeChange(50);
            },
            findHosFollowup(){
                var vm = this;
                vm.$refs.hosFollowup.houseCodeFollowup = vm.cooperateEntity.houseCode;//传值给组件
                vm.$refs.hosFollowup.handleSizeChange(50);
            },
            endCooperate(){
                this.$confirm('您确定要结束合作吗?', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {

                    var vm = this;
                        var obj = {}
                        var sendObj = {}
                        sendObj.entity = {"cooCode": vm.cooperateEntity.cooCode}
                        if (vm.cooperateEntity.cooOpenFlag == true) {
                            this.$alert('已经公开关键信息的合作不能结束', '温馨提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.$message({
                                        type: 'danger',
                                        message: `action: ${ action }`
                                    });
                                }
                            });
                            return
                        }
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "cooperate/end",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                            title: '操作提示',
                        });
                        vm.getCooperateDetails();
                    });
                })
            }
        }
    }
</script>

<style scoped>
    .page-box {
        height: 30px;
        background-color: rgb(242, 242, 242);
        width: 100%;
        bottom: 5px;
        position: fixed;
        z-index: 200;
    }

    .list-root-div {
        height: 150px;
        width: 33%;
        text-align: left;
        box-shadow: 0px 5px 8px 5px rgba(63, 63, 64, 0.18);
        margin: 10px 5px;
        padding: 10px 5px;
        border-radius: 10px;
    }

    .photo-div {
        border-radius: 10px;
    }

    .img1 {
        width: 120px;
        height: 150px;
        margin: 5px;
        border-radius: 10px;
    }

    .name-style {
        font-weight: bold;
        height: 40px;
        font-size: 15px;
    }

    .coo-info-div {
        height: 100%;
        min-width: 60%;
        margin-left: 25px;
        width: 80%;
    }

    .coo-info-col {
        height: 40px;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .green {
        font-size: 16px;
        font-weight: bold;
        color: forestgreen;
    }

    .blue {
        font-size: 16px;
        font-weight: bold;
        color: deepskyblue;
    }

    .red {
        font-size: 16px;
        font-weight: bold;
        color: red;
    }

    .el-card-info {
        width: 360px;
        height: 220px;
        margin-top: 40px;
        margin-bottom: 20px;
        padding: 0px;
        box-shadow: 8px 8px 10px rgba(0, 0, 0, .12), 0 0 10px rgba(0, 0, 0, .04);
    }

    .box-border {
        box-shadow: 0px 5px 8px 5px rgba(63, 63, 64, 0.18);
        margin: 10px 10px;
        text-align: center;
        border-radius: 5px;
    }
</style>
<style>
    .el-tabs--border-card > .el-tabs__content {
        padding: 0px;
    }
</style>
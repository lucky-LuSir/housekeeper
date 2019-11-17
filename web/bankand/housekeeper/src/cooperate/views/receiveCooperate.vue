<template>
    <!--数据表格-->
    <div>
        <cooperate-condition ref="cooCondition" @changeCoo="serchCooperateClick"/>

        <cooperate-receive-detail ref="cooReceiveDetail" v-if="visible"/>
        <div class="hst_first_topbar">
            <el-button class="navigation_button_back" size="mini" plain type="warning" icon="el-icon-delete"
                       @click="emptyCondition()">条件清除
            </el-button>
            <el-button class="navigation_button_back" size="mini" plain type="primary" icon="el-icon-search"
                       @click="searchCoo()">按条件查询
            </el-button>
        </div>

        <div class="form-div">

            <el-row>
                <el-col :span="6" v-for="(item,index) in cooperateList" :key="index"
                        style="margin: 0px 40px">
                    <el-card :body-style="{ padding: '0px'}" class="el-card-info" shadow="hover">
                        <div style="padding: 6px">
                            <div style="position: relative" class="photo-div">
                                <img class="img1"
                                     :src="'http://hkp.kufangwuyou.com/housekeeper/file/read/' + item.cooUser.userImg">
                            </div>

                            <div style="margin-top: 20px;height: 300px;width:210px;float: left;text-align:left;padding-left: 15px;">

                                <div style="position: relative;top: 0px;margin-bottom: 10px"
                                     class="name-style">
                                    <span>
                                        
                                        <i class="el-icon-user"/>
                                        <span>{{item.cooUser.userName}}</span>

                                    </span>

                                    <el-tag v-if="item.cooStatus=='1'"
                                            style="margin-left: 40px"
                                            type="primary"
                                            size="small"
                                            class="el-icon-time">{{item.cooStatusName}}
                                    </el-tag>

                                    <el-tag v-if="item.cooStatus=='2'"
                                            style="margin-left: 40px"
                                            type="danger"
                                            size="small"
                                            class="el-icon-error">{{item.cooStatusName}}
                                    </el-tag>

                                    <el-tag v-if="item.cooStatus=='3'"
                                            style="margin-left: 40px"
                                            type="success"
                                            size="small"
                                            class="el-icon-success">{{item.cooStatusName}}
                                    </el-tag>

                                    <el-tag v-if="item.cooStatus=='4'"
                                            style="margin-left: 40px"
                                            type="warning"
                                            size="small"
                                            class="el-icon-s-custom">{{item.cooStatusName}}
                                    </el-tag>

                                    <el-tag v-if="item.cooStatus=='5'"
                                            style="margin-left: 40px"
                                            type="success"
                                            size="small"
                                            class="el-icon-s-claim">{{item.cooStatusName}}
                                    </el-tag>

                                    <el-tag v-if="item.cooStatus=='6'"
                                            style="margin-left: 40px"
                                            type="info"
                                            size="small"
                                            class="el-icon-circle-close">{{item.cooStatusName}}
                                    </el-tag>
                                </div>
                                <p>
                                    <el-tag type="info" size="mini">
                                        于{{item.createTime | time}}向您申请
                                    </el-tag>
                                </p>
                                <div style="height: 70px">
                                    <div style="width: 200px;font-weight: bold;font-size: 12px;margin-top: 10px"
                                         v-if="item.applyType=='1'">
                                        <p><span class="el-icon-chat-dot-round"
                                                 style="color: darkorange"/>
                                            TA申请与您{{item.streetName}} {{item.area}}㎡的房源进行合作
                                        </p>

                                        <p style="margin-top: 5px;" type="info" size="mini">
                                            房源标题:{{item.houseName}}
                                        </p>
                                    </div>
                                    <div style="width: 200px;font-weight: bold;font-size: 12px;margin-top: 10px"
                                         v-if="item.applyType=='2'">
                                        <p>
                                            <span class="el-icon-chat-dot-round"
                                                  style="color: darkorange"/>
                                            TA申请与您的 客户进行合作
                                        </p>
                                        <p style="margin-top: 5px" type="info" size="mini">
                                            客户信息:{{item.cusName}}
                                        </p>
                                        <p style="margin-top: 5px" type="info" size="mini">
                                            {{item.products}}
                                        </p>
                                        <p style="margin-top: 5px" type="info" size="mini">
                                            需要{{item.needAcreage}}㎡左右
                                        </p>
                                    </div>
                                </div>


                                <div style="text-align: right;padding-right: 20px">
                                    <el-button @click="openCooDetail(item.cooCode)"
                                               type="primary" size="mini" icon="el-icon-view">查看
                                    </el-button>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <!-- 分页 -->
        <div class="page-box">
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-sizes="[30,50]"
                           :page-size="pageSizeData"
                           layout="total, sizes, prev, pager, next"
                           :total="totalCount">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import ElCol from "element-ui/packages/col/src/col";
    import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";
    import cooperateReceiveDetail from "./cooperateReceiveDetail.vue"
    import cooperateCondition from "./cooperateCondition.vue";
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {
            ElRow,
            ElButton,
            ElTag,
            ElCol,
            cooperateCondition,
            cooperateReceiveDetail
        },
        data() {
            return {
                fullHeight: document.documentElement.clientHeight,
                visible: false,

                cooperateList: [],
                //一页数据
                pageSize: 30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                cooperateCondition: {
                    queryType: null,
                    cusPhone: null,
                    houseownerPhone: null,
                    province: null,
                    city: null,
                    region: null,
                    street: null,
                    detailAddress: null,
                    houseName: null,
                    unionName: null,
                    unionPhone: null,
                    cooStatus: null,
                    applyCode: null,
                    receiveCode: null,
                }
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
            search(obj){
                var vm = this;
                vm.cooperateCondition = obj;
            },
            searchCoo(){
                var vm  = this;
                vm.$refs.cooCondition.visible=true;

            },
            emptyCondition(){
                var vm =this;
                this.cooperateCondition={
                    queryType: null,
                    cusPhone: null,
                    houseownerPhone: null,
                    province: null,
                    city: null,
                    region: null,
                    street: null,
                    detailAddress: null,
                    houseName: null,
                    unionName: null,
                    unionPhone: null,
                    cooStatus: null,
                    applyCode: null,
                    receiveCode: null
                };
                this.handleCurrentChange(1);
            },
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                let obj = this.cooperateCondition;
                this.myApply(obj);
            },
            //自定义查询
            serchCooperateClick(obj) {
                this.cooperateCondition = obj;
                this.myApply(this.cooperateCondition);
                this.$nextTick(() => {
                    this.$refs.cooCondition.visible=false;
                })
            },

            myApply(obj) {
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = obj;
                sendObj.entity.receiveCode = this.$cookieStore.getCookie("userCode");
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cooperate/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.cooperateList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {

                    vm.$message.error('页面:获取数据失败!cooperate/query');

                })
            },
            openCooDetail(cooCode){
                var vm  = this;
                vm.visible=true;
                vm.$nextTick(()=>{
                    this.$refs.cooReceiveDetail.visible = true;
                    this.$refs.cooReceiveDetail.cooCode=cooCode;
                    this.$refs.cooReceiveDetail.getCooperateDetails();
                })
            }
        }
    }
</script>

<style scoped>
    .hst_first_topbar {
        margin:15px 0px 0px 40px;
        padding: 0px 0px 0px 0px ;
    }
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
        float: left;
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
        float: left;
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
        margin-top: 30px;
        margin-bottom: 20px;
        padding: 0px;
        box-shadow: 8px 8px 10px rgba(0, 0, 0, .12), 0 0 10px rgba(0, 0, 0, .04);
    }
    .form-div{
        max-height: 540px;
        overflow-y: auto;
        overflow-x: hidden;
    }
</style>
<style>
    .el-tabs--border-card > .el-tabs__content {
        padding: 0px;
    }
</style>
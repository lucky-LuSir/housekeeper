<template>
    <el-dialog  :close-on-click-modal="false" title="业主拜访" align="center" :before-close="closeDialog" top="40px" width="70%"
               :visible.sync="hosOwnerFollowupVisible" append-to-body>
        <div align="right">
            <el-button v-if="hosOwnerInfo" @click="openHosOwnerFollowup" size="small" plain type="primary">新增业主拜访
            </el-button>
        </div>
        <div class="pre-scrollable-s">
            <div class="pagex">

                <el-table
                        :data="houseOwnerFollowupEntity"
                        stripe
                        size="mini"
                        border
                        height="482px"
                        style="width: 100%;font-size: 12px">
                    <el-table-column
                            prop="createTime"
                            label="跟进时间"
                            align="center"
                            width="140"
                            show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.createTime!=null">
                            {{ scope.row.createTime | time}}
                        </template>
                    </el-table-column>

                    <el-table-column
                            prop="houseownerCode"
                            label="业主拜访编码"
                            align="center"
                            width="160"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="followupType"
                            label="业主拜访类型"
                            width="100"
                            align="center"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{ scope.row.followupType == "1" ? "电话拜访" : "上门拜访"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="createName"
                            label="拜访专员"
                            align="center"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="followupContext"
                            label="跟进结果"
                            align="center"
                            show-overflow-tooltip>
                    </el-table-column>
                </el-table>
            </div>
            <!-- 分页 -->
            <div class="page-box-s">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[30,50]"
                               :page-size="pageSizeData"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>
            </div>
        </div>

        <hos-owner-followup @changeSuccess ="handleSizeChange(30)"  v-if="createHosOwnerFollowupVisible"
                            ref="createHosOwnerFollowup"/>

    </el-dialog>
</template>

<script>
    import hosOwnerFollowup from "./hosOwnerFollowup.vue"

    export default {
        props: ["houseCodeFollowupOwner"],
        components: {
            hosOwnerFollowup
        },
        data(){
            return {
                hosOwnerInfo :{},
                hosOwnerFollowupVisible: false,
                createHosOwnerFollowupVisible: false,
                //一页数据
                pageSize: 30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start: 0,
                //跟进数据
                houseOwnerFollowupEntity: [],
                fullHeight: document.documentElement.clientHeight * 0.7,
            }
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight * 0.7
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        watch: {
            fullHeight (val) {
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

        methods: {

            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getOwnerFollowup();
            },
            //查看房源跟进
            getOwnerFollowup(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.houseCode = vm.houseCodeFollowupOwner;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "houseownerFollowup/queryWeb",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.houseOwnerFollowupEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!houseowner/query');
                })
            },
            //关闭面板，清空数据
            closeDialog(done){
                var vm = this;
                vm.houseOwnerFollowupEntity = [];
                done()
            },

            openHosOwnerFollowup(){
                var vm = this;
                vm.createHosOwnerFollowupVisible = true;
                vm.$nextTick(()=>{
                    vm.$refs.createHosOwnerFollowup.hosOwnerFollowupVisible = true;
                    vm.$refs.createHosOwnerFollowup.ownerInfo = vm.hosOwnerInfo;
                })
            }
        }
    }
</script>

<style scoped>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }

    .pre-scrollable-s {
        margin: 10px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 550px;
        overflow-x: scroll;
        overflow-y: auto;
        /*background-color: #2aabd2;*/
    }

    .page-box-s {
        height: 30px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        left: 0px;
        bottom: 0px;
        position: absolute;
        z-index: 10;
    }

    .pagex {
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
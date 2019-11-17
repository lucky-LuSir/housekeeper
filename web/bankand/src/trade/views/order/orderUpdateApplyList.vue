<template>
    <div class="root" ref="myRoot">
        <order-update-apply-detail ref="refApplyDetail" v-if="orderUpdateApplyDetailOpenOfClose"></order-update-apply-detail>
        <el-form>
            <div class="navigation_div">
                <div class="titles">
                    <el-button class="navigation_button_back" size="mini" type="primary"
                               @click="searchOrderUpdateApply()">自定义查询订单修改申请
                    </el-button>
                </div>
            </div>
            <!-- 列表 -->
            <div>
                <template>
                    <el-table
                            :data="orderUpdateApplyList"
                            size="small"
                            :height="fullHeight"
                            border
                            highlight-current-row>
                        <el-table-column align="center"
                                         fixed
                                         prop="orderCode"
                                         label="订单编号"
                                         show-overflow-tooltip
                                         width="160">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="applyStatusName"
                                         label="订单修改申请状态"
                                         show-overflow-tooltip
                                         width="140">
                        </el-table-column>
                        <el-table-column align="center"
                                         prop="auditReason"
                                         label="审核理由"
                                         show-overflow-tooltip
                                         width="140">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="applyReason"
                                         label="申请理由"
                                         show-overflow-tooltip
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="lastUpdateName"
                                         label="最后修改人"
                                         show-overflow-tooltip
                                         width="120">
                        </el-table-column>

                        <el-table-column align="center"
                                         prop="updateContent"
                                         label="修改内容"
                                         show-overflow-tooltip>
                        </el-table-column>

                        <el-table-column align="center"
                                         fixed="right"
                                         label="操作"
                                         width="150">
                            <template slot-scope="scope">
                                <el-button type="text" size="small" v-if="queryDetailBtn"
                                           @click=openOrderUpdateApplyDetail(scope.row.orderCode)>查看
                                </el-button>
                                <el-button type="text" size="small" v-if="auditBtn"
                                           v-show="scope.row.applyStatus!= '3' && scope.row.applyStatus!= '4'"
                                           @click=auditSuccess(scope.row.orderCode)>审核成功
                                </el-button>
                                <el-button type="text" size="small" v-if="auditBtn"
                                           v-show="scope.row.applyStatus!= '3' && scope.row.applyStatus!= '4'"
                                           @click=disAgree(scope.row.orderCode)>驳回
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </div>


            <!-- 分页 -->
            <div class="page-box">
                <el-pagination background
                               @size-change="handleSizeChange"
                               @current-change="handleCurrentChange"
                               :current-page.sync="currentPage"
                               :page-sizes="[30]"
                               :page-size="pageSizeData"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>
            </div>

        </el-form>
    </div>
</template>

<script>
    import orderUpdateApplyDetail from "./orderUpdateApplyDetail"
    export default {

        components: {
            orderUpdateApplyDetail
        },
        data(){
            return {
                fullHeight: document.documentElement.clientHeight - 205,
                queryDetailBtn: false,//查看详情权限
                auditBtn: false,//审核权限 包括成功与驳回
                pageSize: 30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start: 0,
                //订单修改申请查询dialog
                searchDialogFormVisible: false,
                //订单修改申请列表
                orderUpdateApplyList: [],
                //订单修改申请列表查询条件
                searchOrderUpdateApplyCondition: {},
                //订单修改申请查看详情
                orderUpdateApplyDetailOpenOfClose: false
            }
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205;
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
        created() {
            //审核权限
            this.auditBtn = this.$flagMenuStore.judgeMenu("order-update-apply-audit");
            //查看详情权限
            this.queryDetailBtn = this.$flagMenuStore.judgeMenu("order-update-apply-query");
            this.handleSizeChange(30)
        },
        methods: {
            // 分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1);
            },
            // 分页:改变每页数量
            handleCurrentChange(val) {
                this.currentPage = 1;
                this.getOrderUpdateApplyList();
            },
            //获取订单修改申请列表
            getOrderUpdateApplyList(){
                let vm = this;
                let sendObj = {};
                vm.start = (vm.currentPage - 1) * vm.pageSize;
                sendObj.start = vm.start;
                sendObj.pageSize = vm.pageSize;
                sendObj.entity = vm.searchOrderUpdateApplyCondition;
                let options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "order/update/apply/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.orderUpdateApplyList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                }).catch(function (error) {
                    vm.$message.error('页面：获取数据失败！');
                });
            },
            openOrderUpdateApplyDetail(val){
                this.orderUpdateApplyDetailOpenOfClose = true;
                this.$nextTick(()=>{
                    this.$refs.refApplyDetail.visible = true;
                    this.$refs.refApplyDetail.orderCode = val;
                    this.$refs.refApplyDetail.loadOrderEntity();
                })
            },
            searchOrderUpdateApply(){
                this.$notify.info("敬请期待");
            },
            //审核成功
            auditSuccess(val){
                this.$confirm('申请是否通过', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let sendObj = {};
                    sendObj.entity = {"orderCode": val};
                    let options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "order/update/apply/audit",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        if (response.data.code == '200') {
                            vm.$notify.success("审核成功")
                            vm.getOrderUpdateApplyList()
                        }
                    }).catch(function (error) {
                        vm.$message.error('页面：获取数据失败！');
                    });
                })
            },
            //驳回
            disAgree(val){
                this.$confirm('是否驳回申请', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$prompt('请填写驳回原因', '操作提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                    }).then(({value}) => {
                        let vm = this;
                        let sendObj = {};
                        sendObj.entity = {"orderCode":val,"applyStatus":'4',"auditReason":value};
                        let options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "order/update/apply/audit",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            if(response.data.code == '200'){
                                vm.$notify.success("驳回成功")
                                vm.getOrderUpdateApplyList()
                            }
                        }).catch(function (error) {
                            vm.$message.error('页面：获取数据失败！');
                        });
                    })
                })
            }
        }

    }
</script>

<style scoped>
    body {
        margin-left: 200px;
        margin-right: 200px;
    }




    /*导航栏DIV*/
    .navigation_div {
        margin-top: 20px;
        margin-left: 20px;
        margin-bottom: 20px;
        width: 100%;
    }

    .navigation_button_back {
        display: inline-block;
    }

    .navigation-title {
        font-size: 13px;
    }

    .input-with-select {
        width: 270px;
        margin-right:20px ;
    }

    .contract_start {
        width: 92%;
        height: 26px;
    }

    .page-box {
        height: 40px;
        line-height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

</style>
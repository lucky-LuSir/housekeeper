<template>
    <div class="pre-scrollable-s">
        <div class="pagex">
            <el-table
                    :data="orderEntity"
                    stripe
                    size="small"
                    border
                    height="392px"
                    style="width: 100%">
                <el-table-column
                        type="index"
                        label="序号"
                        align="center"
                        width="50">
                </el-table-column>

                <el-table-column align="center"
                      prop="orderCode"
                      label="订单编号"
                      width="160"
                      show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="orderStatusName"
                                  label="订单状态"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column  align="center"
                                  prop="unit"
                                  label="价格单位"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column  align="center"
                                  prop="commission"
                                  label="佣金总计"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column  align="center"
                                  prop="receivableHos"
                                  label="业主佣金"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column  align="center"
                                  prop="receivableCus"
                                  label="客户佣金"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="cusRebate"
                                  label="客户返佣"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="ownerRebate"
                                  label="业主返佣"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="cusParttimeMoney"
                                  label="客户兼职分钱"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>


                <el-table-column  align="center"
                                  prop="ownerParttimeMoney"
                                  label="业主兼职分钱"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="actualCommision"
                                  label="实际业绩总额"
                                  width="100"
                                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="actualBranchCommision"
                                  label="业务员实际业绩"
                                  width="140"
                                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column  align="center"
                                  prop="actualCusServCommision"
                                  label="客服实际业绩"
                                  width="120"
                                  show-overflow-tooltip>
                </el-table-column>

            </el-table>
        </div>
        <!-- 分页 -->
        <div class="page-box-s">
            <el-pagination   background
                             @size-change="handleSizeChange"
                             @current-change="handleCurrentChange"
                             :current-page.sync="currentPage"
                             :page-sizes="[30,50]"
                             :page-size="pageSizeData"
                             layout="total, sizes, prev, pager, next, jumper"
                             :total="totalCount">
            </el-pagination >
        </div>

    </div>
</template>

<script>
    export default {
        components: {

        },
        props: ["houseCodeOrderQuery"],

        data(){
            return{
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
                //成交实体
                orderEntity: [],
            }
        },
        created() {

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
                this.getHouseOrderQuery();
            },
            //房源成交记录
            getHouseOrderQuery() {
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = {"houseCode":vm.houseCodeOrderQuery};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "order/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.orderEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!/order/query');
                })
            },
            //关闭面板，清空数据
            closeDialog(){
                this.orderEntity = [];
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
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 410px;
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
    .pagex{
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
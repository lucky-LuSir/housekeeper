<template>

    <div>
        <div class="hst_first_topbar">
            <el-button class="navigation_button_back" size="small" plain
                       type="hst_first_topbarwarning" icon="el-icon-delete"
                       @click="emptyCondition()">条件清除
            </el-button>

            <el-button class="navigation_button_back" size="small" plain type="primary"
                       icon="el-icon-search"
                       @click="searchOperation()">按条件查询
            </el-button>
        </div>

        <!-- 操作日志按条件查询面板 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="55%" append-to-body>
            <base-operation-condition ref="baseOperationCondition"
                                      v-if="baseOperationConditionOpenOrClose"
                                      @queryOperation="searchOperationClick"/>
        </el-dialog>

        <!--客户详情面板-->
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormOperationDetails" width="50%" append-to-body>
            <!--通过:可以将属性传递给子组件-->
            <base-operation-details :id="id" ref="baseOperationDetails"
                                    v-if="baseOperationDetailsOpenOrClose"/>
        </el-dialog>

        <!--数据表格-->
        <el-table
                :data="baseOperationList"
                border
                stripe
                :height="fullHeight"
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="id"
                    align="center"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="optName"
                    label="操作人"
                    align="center"
                    width="200"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="url"
                    label="操作接口"
                    align="center"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="requestParam"
                    label="请求参数"
                    align="center"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="ip"
                    label="操作ip"
                    align="center"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="clientType"
                    label="客户端类型"
                    align="center"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="操作创建时间"
                    align="center"
                    width="160"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.createTime!=null">
                    {{ scope.row.createTime | time}}
                </template>
            </el-table-column>
            <el-table-column align="center" fixed="right" label="操作" width="160" height="20">
                <template slot-scope="scope">
                    <el-button
                            type="success"
                            size="mini"
                            @click="baseOperationDetailsClick(scope.$id,scope.row)">
                        详情
                    </el-button>
                </template>

            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div class="page-box">
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-sizes="[50]"
                           :page-size="pageSizeData"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="totalCount">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import baseOperationDetails from "./baseOperationDetails.vue"
    import baseOperationCondition from  "./baseOperationCondition.vue";

    export default {

        components: {
            baseOperationDetails,
            baseOperationCondition
        },
        data() {
            return {
                fullHeight: document.documentElement.clientHeight - 185,
                //按条件查询日志面板
                dialogFormVisible: false,
                //按条件查询面板 打开或者关闭
                baseOperationConditionOpenOrClose: false,

                //操作日志详情面板
                dialogFormOperationDetails: false,
                //操作日志详情面板 打开或者关闭
                baseOperationDetailsOpenOrClose: false,
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
                id: '',//日志操作id
                baseOperationList: [],//数据表格  客户list
                searchOperationCondition: {
                    baseOperationQuery: {
                        optName: null,//操作人
                        optCode: null,
                        url: null,
                        createTimeStart: null,
                        createTimeEnd: null
                    },
                },
            }
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 185
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

        created: function () {
            this.handleSizeChange(50);
        },
        mounted: function () {

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
                this.getOperationList();
            },
            //查询日志列表
            getOperationList() {
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = this.searchOperationCondition.baseOperationQuery;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "baseOperation/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.baseOperationList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!baseOperation/query');
                })
            },
            //重置按条件查询条件
            emptyCondition() {
                this.searchOperationCondition.baseOperationQuery.optName = null;
                this.searchOperationCondition.baseOperationQuery.optCode = null,
                this.searchOperationCondition.baseOperationQuery.url = null,
                this.searchOperationCondition.baseOperationQuery.createTimeStart = null,
                this.searchOperationCondition.baseOperationQuery.createTimeEnd = null
                this.handleSizeChange(50);

                this.$nextTick(() => {
                    this.$refs.baseOperationCondition.emptyConditions();
                })

            },
            //打开按条件查询面板
            searchOperation() {
                this.dialogFormVisible = true;
                this.baseOperationConditionOpenOrClose = true;
            },
            //按条件查询
            searchOperationClick(obj) {
                this.searchOperationCondition = obj;
                this.handleSizeChange(50);
                this.dialogFormVisible = false;
            },

            //打开客户详情面板
            baseOperationDetailsClick(id, rows){
                this.baseOperationDetailsOpenOrClose = true;
                this.dialogFormOperationDetails = true;
                this.id = rows.id;//传值给组件
                this.$nextTick(() => {
                    this.$refs.baseOperationDetails.getBaseOperationDetails();
                })
            },
        }
    }
</script>

<style scoped>
    .hst_first_topbar {
        margin: 5px 20px;
        padding: 0px 0px 0px 0px;
    }

    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }

    .page-box {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
</style>
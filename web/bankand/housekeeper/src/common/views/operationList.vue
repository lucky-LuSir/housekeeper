<template>

    <div>
        <div class="hst_first_topbar">
            <el-button class="navigation_button_back" size="small" plain type="hst_first_topbarwarning" icon="el-icon-delete"
                       @click="emptyCondition()">条件清除
            </el-button>

            <el-button class="navigation_button_back" size="small"   plain type="primary" icon="el-icon-search"
                       @click="searchOperation()">按条件查询
            </el-button>
        </div>
        
        <!-- 操作日志按条件查询面板 -->
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="60%" append-to-body>
            <operation-condition  ref="operationCondition" v-if="operationConditionOpenOrClose" @queryOperation="searchOperationClick"/>
        </el-dialog>

        <!--客户详情面板-->
        <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormOperationDetails" width="60%" append-to-body>
            <!--通过:可以将属性传递给子组件-->
            <operation-details :optCodes="optCodes" ref="operationDetails" v-if="operationDetailsOpenOrClose"/>
        </el-dialog>

        <!--数据表格-->
        <el-table
                :data="operationList"
                border
                stripe
                :height="fullHeight"
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="id"
                    align="center"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="optCode"
                    label="操作编码"
                    align="center"
                    width="200"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="optTypeName"
                    label="操作类型"
                    align="center"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="content"
                    label="操作内容"
                    align="center"
                    width="480"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="createName"
                    label="操作人"
                    align="center"
                    width="100"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="操作创建时间"
                    align="center"
                    width="160"
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime | time}}</template>
            </el-table-column>
            <el-table-column
                    prop="bizCode"
                    label="业务编码"
                    align="center"
                    width="180"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="url"
                    label="请求路径"
                    align="center"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="createDeptName"
                    label="操作人所属部门"
                    align="center"
                    width="100"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column align="center" fixed="right" label="操作" width="160" height="20">
                <template slot-scope="scope">
                    <el-button
                            type="success"
                            size="mini"
                            @click="operationDetailsClick(scope.$optCode,scope.row)">
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
    import operationDetails from "./operationDetails.vue"
    import operationCondition from  "./operationCondition.vue";

    export default {

        components: {
            operationDetails,
            operationCondition
        },
        data() {
            return {
                fullHeight: document.documentElement.clientHeight - 185,
                //按条件查询日志面板
                dialogFormVisible: false,
                //按条件查询面板 打开或者关闭
                operationConditionOpenOrClose: false,

                //操作日志详情面板
                dialogFormOperationDetails: false,
                //操作日志详情面板 打开或者关闭
                operationDetailsOpenOrClose: false,
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
                optCode: '',//日志操作code
                operationList: [],//数据表格  客户list
                searchOperationCondition: {
                    operationQuery: {
                        keyword: null,//关键字查
                        optType: null,//查询类型
                        createName: null,//操作人
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
                //sendObj.keyword = this.searchOperationCondition.operationQuery.keyword;
                sendObj.entity = this.searchOperationCondition.operationQuery;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "operation/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.operationList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!operation/query');
                })
            },
            //重置按条件查询条件
            emptyCondition() {
                this.searchOperationCondition.operationQuery.keyword=null;
                this.searchOperationCondition.operationQuery.optType = null;//查询类型
                this.searchOperationCondition.operationQuery.createName = null;

                this.handleSizeChange(10);

                this.$nextTick(() => {
                    this.$refs.operationCondition.emptyConditions();
                })

            },
            //打开按条件查询面板
            searchOperation() {
                this.dialogFormVisible = true;
                this.operationConditionOpenOrClose = true;
            },
            //按条件查询
            searchOperationClick(obj) {
                this.searchOperationCondition = obj;
                this.handleSizeChange(10);
                this.dialogFormVisible = false;
            },

            //打开客户详情面板
            operationDetailsClick(optCode,rows){
                this.operationDetailsOpenOrClose=true;
                this.dialogFormOperationDetails=true;
                this.optCodes=rows.optCode;//传值给组件
                this.$nextTick(() => {
                    this.$refs.operationDetails.getOperationDetails();
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
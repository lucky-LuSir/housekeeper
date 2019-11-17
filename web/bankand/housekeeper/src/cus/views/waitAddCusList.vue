<template>
    <el-dialog title="待新增客户列表" :style="{height: fullHeight +80 +'px'}" top="50px" :before-close="closeDialog" append-to-body
               :visible.sync="visible" width="70%">
        <div>

            <!--客户详情面板-->
            <cus-insert-or-edit ref="cusInsert" @refresh="getWaitAddCusList" v-if="cusInsertOpenOrClose"/>
            <!--数据表格-->
            <el-table
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    :data="waitAddCusList"
                    highlight-current-row
                    @selection-change="handleSelectionChange"
                    :row-style="rowClass"
                    :height="fullHeight-280"
                    border
                    style="width: 100%;font-size: 11px">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="cusName"
                        label="客户名"
                        align="center"
                        width="90"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusPhone"
                        label="客户电话"
                        align="center"
                        width="110"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusSexName"
                        label="性别"
                        align="center"
                        width="70"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="needAcreage"
                        label="需求面积(㎡)"
                        align="center"
                        width="130"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseTypeName"
                        label="找房用途"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                        prop="products"
                        label="产品信息"
                        align="center"
                        width="150"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="description"
                        label="更多描述"
                        align="center"
                        width="200"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="enterTime"
                        label="最晚入住时间"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.enterTime!=null">{{ scope.row.enterTime | time}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="industry"
                        label="客户行业"
                        align="center"
                        width="150"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column align="center" fixed="right" label="操作" width="100" height="10">
                    <template slot-scope="scope">
                        <el-button
                                type="success"
                                size="mini"
                                @click="cusInsertHandle(scope.$index,scope.row)">
                            详 情
                        </el-button>
                    </template>

                </el-table-column>
                <el-table-column align="center" fixed="right" label="删除" width="80" height="10">
                    <template slot-scope="scope">
                        <el-button
                                type="danger"
                                size="mini"
                                @click="cusDeleteClick(scope.$index,scope.row)">
                            删除
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
                               :page-sizes="[30,50]"
                               :page-size="pageSizeData"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="totalCount">
                </el-pagination>
            </div>
        </div>
    </el-dialog>
</template>

<script>
    import cusInsertOrEdit from "./cusInsertOrEdit.vue"

    export default {

        components: {
            cusInsertOrEdit
        },

        data() {
            return {


                visible: false,
                loading: true,
                focusCount: 0,
                selectRow: [],
                selectData: [],
                fullHeight: document.documentElement.clientHeight,
                //客户详情 打开或者关闭
                cusInsertOpenOrClose: false,
                pageSize: 30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start: 0,
                cusPhone: '',//客户code
                waitAddCusList: [],//数据表格  客户list
            }
        },
        created() {
            this.handleSizeChange(30)
        },
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        watch: {
            selectData(data) {
                this.selectRow = [];
                if (data.length > 0) {
                    data.forEach((item, index) => {
                        this.selectRow.push(this.waitAddCusList.indexOf(item));
                    });
                }
            },
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
        methods: {
            //打开客户详情面板
            cusDeleteClick(index, rows) {
                this.$confirm('是否删除当前客户？', '操作提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let cusPhone = rows.cusPhone;
                    vm.loading = true;
                    var sendObj = {}
                    sendObj.entity = {"cusPhone": cusPhone}
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "customer/cusWaitAddDelete",
                    };
                    this.$ajax(
                        option
                    ).then(function (response) {
                        vm.$notify.success(response.data.message);
                        vm.loading = false;
                        vm.handleSizeChange(30);
                    }).catch(function (error) {
                        vm.$notify.error(error);
                    })
                })
            },
            // 选中筛选结果时候
            handleSelectionChange(data) {
                this.selectData = data;
            },
            // 多选高亮选中行
            rowClass({row, rowIndex}) {
                if (this.selectRow.includes(rowIndex)) {
                    return {"background-color": "rgba(185, 221, 249, 0.75)"}
                }
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
                this.getWaitAddCusList();
            },
            //查询客户
            getWaitAddCusList() {
                var vm = this;
                vm.loading = true;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/cusWaitAddQuery",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.waitAddCusList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.loading = false;
                }).catch(function (error) {
                    vm.loading = false;
                    vm.$message.error('页面:获取数据失败!customer/cusWaitAddQuery');
                })
            },
            //打开客户新增面板
            cusInsertHandle(index, rows) {
                this.cusInsertOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.cusInsert.visible = true;
                    this.$refs.cusInsert.cusForm.cusPhone = rows.cusPhone;//传值给组件
                    this.$refs.cusInsert.waitAdd = true;
                    this.$refs.cusInsert.type = "2";
                    this.$refs.cusInsert.getWaitAddCusDetails();
                })
            },
            //关闭面板
            closeDialog(done) {
                this.waitAddCusList = [];
                this.visible = false;
                done()
            },
        }
    }
</script>

<style scoped>
    .page-box {
        height: 30px;
        padding-top: 0px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }
</style>

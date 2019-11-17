<template>
    <div class="pre-scrollable-s">
        <div class="hst_first_topbar">
            <el-button size="mini" plain type="primary" icon="el-icon-search"
                       @click="searchCus()">按条件查询
            </el-button>
        </div>
        <cus-followup-condition ref="cusFollowupCondition" v-if="cusFollowupConditionVisible" @changeCus="serchCusClick"/>
        <!--客户详情面板-->
        <cus-details ref="cusDetailsx" v-show="cusDetailsOpenOrClose"></cus-details>
        <div class="pagex">
            <el-table
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    :data="cusFollowupEntity"
                    highlight-current-row
                    @selection-change="handleSelectionChange"
                    :row-style="rowClass"
                    border
                    size="small"
                    :height="fullHeight-185"
                    style="width: 100%">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
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
                        prop="cusCode"
                        label="客户编码"
                        align="center"
                        width="150"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusName"
                        label="客户姓名"
                        align="center"
                        width="70"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="followupTypeName"
                        label="跟进类型"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="deptName"
                        label="所属部门"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="empName"
                        label="服务专员"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="followupContent"
                        label="跟进结果"
                        align="center"
                        width="400"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="lastUpdateTime"
                        label="更新时间"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.lastUpdateTime!=null">
                        {{ scope.row.lastUpdateTime | time}}
                    </template>
                </el-table-column>
                <el-table-column align="center" fixed="right" label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button
                                type="success"
                                size="mini"
                                @click="cusDetailsClick(scope.$index,scope.row)">
                            详 情
                        </el-button>
                    </template>

                </el-table-column>
            </el-table>
        </div>
        <!-- 分页 -->
        <div class="page-box-s">
            <el-pagination background
                           @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page.sync="currentPage"
                           :page-sizes="[50,100]"
                           :page-size="pageSizeData"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="totalCount">
            </el-pagination>
        </div>

    </div>

</template>

<script>
    import cusFollowupCondition from "./cusFollowupCondition.vue"
    import cusDetails from "./cusDetails.vue"
    export default {
        components: {
            cusFollowupCondition,
            cusDetails
        },
        data(){
            return {
                loading:true,
                selectRow:[],
                selectData:[],
                cusDetailsOpenOrClose:false,
                cusFollowupConditionVisible: false,
                fullHeight: document.documentElement.clientHeight,
                cusCodeFollowup: '',
                //一页数据
                pageSize: 50,
                //一页数据
                pageSizeData: 50,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start: 0,
                //跟进数据
                cusFollowupEntity: [],
                customerQuery: {
                    cusCode: '',
                    followupType: '',
                    empCode: '',
                    empName: '',
                    deptCode: '',
                    deptName: '',
                    createTimeStart: '',
                    createTimeEnd: ''
                },
            }
        },
        created(){
            this.handleSizeChange(50);
        },
        watch: {
            selectData(data) {
                this.selectRow = [];
                if (data.length > 0) {
                    data.forEach((item, index) => {
                        this.selectRow.push(this.cusFollowupEntity.indexOf(item));
                    });
                }
            },

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
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
        },
        methods: {
            // 选中筛选结果时候
            handleSelectionChange(data) {
                this.selectData = data;
            },
            // 多选高亮选中行
            rowClass({row, rowIndex}){
                if(this.selectRow.includes(rowIndex)){
                    return { "background-color": "rgba(185, 221, 249, 0.75)" }
                }
            },

            //按条件查询
            serchCusClick(obj) {
                this.customerQuery = obj;
                this.handleSizeChange(30);
                this.$nextTick(() => {
                    this.$refs.cusFollowupCondition.dialogFormVisible = false;
                })
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
                this.getCusFollowupList();
            },
            getCusFollowupList(){
                var vm = this;
                var sendObj = {};
                vm.loading=true;
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = vm.customerQuery;

                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customerFollowup/select",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.cusFollowupEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.loading=false;
                }).catch(function (error) {
                    vm.loading=false;
                    vm.$message.error('页面:获取数据失败!customerFollowup/select');
                })
            },
            //关闭面板清空数据
            closeDialog(){
                this.cusFollowupEntity = [];
            },
            searchCus() {
                this.cusFollowupConditionVisible = true;
                this.$nextTick(() => {
                    this.$refs.cusFollowupCondition.dialogFormVisible = true;
                })
            },
            //打开客户详情面板
            cusDetailsClick(index,rows){
                this.cusDetailsOpenOrClose=true;
                this.$nextTick(() => {
                    this.$refs.cusDetailsx.visible = true;
                    this.$refs.cusDetailsx.cusCodeDetails = rows.cusCode;//传值给组件
                    this.$refs.cusDetailsx.getCusDetails();
                })
            },
        }
    }
</script>

<style scoped>
    .hst_first_topbar {
        margin: 0px 0px 10px 60px;
        padding: 0px 0px 0px 0px;
    }

    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }

    .pre-scrollable-s {
        margin: 15px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        overflow-x: scroll;
        overflow-y: auto;
    }

    .page-box-s {
        height: 40px;
        padding-top: 0px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

    .pagex {
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
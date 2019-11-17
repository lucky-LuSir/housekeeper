<template>
    <div class="pre-scrollable-s">
        <div class="hst_first_topbar">
            <el-button size="mini" plain type="primary" icon="el-icon-search"
                       @click="searchHosOwner()">按条件查询
            </el-button>
        </div>
        <hos-owner-followup-condition ref="hosOwnerFollowupCondition" v-if="hosOwnerFollowupConditionVisible" @changeHosOwner="serchHosOwnerClick"/>
        <div class="pagex">

            <el-table
                    :data="houseOwnerFollowupEntity"
                    highlight-current-row
                    @selection-change="handleSelectionChange"
                    :row-style="rowClass"
                    size="mini"
                    border
                    :height="fullHeight-190"
                    style="width: 100%;font-size: 12px">
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
                        prop="houseownerCode"
                        label="业主拜访编码"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="followupContext"
                        label="跟进结果"
                        align="center"
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
                        prop="ownerName"
                        label="业主姓名"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="ownerPhone"
                        label="业主电话"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                        prop="createDeptName"
                        label="部门名称"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="createName"
                        label="拜访专员"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="empPhone"
                        label="专员手机号"
                        align="center"
                        width="120"
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
                           :page-sizes="[50]"
                           :page-size="pageSizeData"
                           layout="total, sizes, prev, pager, next, jumper"
                           :total="totalCount">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import hosOwnerFollowupCondition from "./hosOwnerFollowupCondition.vue"
    export default {
        components: {
            hosOwnerFollowupCondition
        },
        created(){
            this.handleSizeChange(50);
        },
        data(){
            return {
                selectRow:[],
                selectData:[],
                hosOwnerInfo: {},
                hosOwnerFollowupConditionVisible:false,
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
                houseOwnerFollowupEntity: [],
                fullHeight: document.documentElement.clientHeight,
                houseOwnerFollowupQuery:{

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

        watch: {
            selectData(data) {
                this.selectRow = [];
                if (data.length > 0) {
                    data.forEach((item, index) => {
                        this.selectRow.push(this.houseOwnerFollowupEntity.indexOf(item));
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
            serchHosOwnerClick(obj) {
                this.houseOwnerFollowupQuery = obj;
                this.handleSizeChange(30);
                this.$nextTick(() => {
                    this.$refs.hosOwnerFollowupCondition.dialogFormVisible = false;
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
                this.getHouseOwnerFollowup();
            },
            //查看房源跟进
            getHouseOwnerFollowup(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = vm.houseOwnerFollowupQuery;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "houseownerFollowup/queryListWeb",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.houseOwnerFollowupEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!houseownerFollowup/queryListWeb');
                })
            },

            searchHosOwner() {
                this.hosOwnerFollowupConditionVisible = true;
                this.$nextTick(() => {
                    this.$refs.hosOwnerFollowupCondition.dialogFormVisible = true;
                })
            },
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
        overflow-x: scroll;
        overflow-y: auto;
    }

    .page-box-s {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        width: 100%;
        bottom: 0px;
        position: absolute;
        position: fixed;
        z-index: 10;
    }

    .pagex {
        overflow-y: hidden;
        overflow-x: hidden;
    }
    .hst_first_topbar {
        margin: 10px 50px;
    }
</style>
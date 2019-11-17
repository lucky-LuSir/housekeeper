<template>
    <div class="pre-scrollable-s">
        <div class="pagex">
            <el-table
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    :data="houseOwnerFollowupEntity"
                    stripe
                    size="mini"
                    border
                    :height="fullHeight-225"
                    style="width: 100%;font-size: 12px">
                <el-table-column
                        prop="createTime"
                        label="跟进时间"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime | time}}</template>
                </el-table-column>

                <el-table-column
                        prop="houseName"
                        label="房源标题"
                        align="center"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="houseAddress"
                        label="房源地址"
                        align="center"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="deptName"
                        label="所属部门"
                        align="center"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="empName"
                        label="服务专员"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="followupContext"
                        label="跟进结果"
                        align="center"
                        width="420"
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
                             :page-sizes="[50]"
                             :page-size="pageSizeData"
                             layout="total, sizes, prev, pager, next, jumper"
                             :total="totalCount">
            </el-pagination >
        </div>
    </div>
</template>

<script>
    export default {
        props:["houseCodeFollowup"],
        data(){
            return {
                loading:true,
                //一页数据
                pageSize:50,
                //一页数据
                pageSizeData: 50,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start:0,
                //跟进数据
                houseOwnerFollowupEntity:[],
                fullHeight: document.documentElement.clientHeight,
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
                this.pageSize=val;
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
                vm.loading=true;
                var sendObj = {};
                this.start = (this.currentPage-1)*this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = {"houseCode":vm.houseCodeFollowup};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "houseFollowup/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.houseOwnerFollowupEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.loading=false;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!houseFollowup/query');
                })
            },
            //关闭面板，清空数据
            closeDialog(){
                this.houseOwnerFollowupEntity = [];
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
        padding: 0px 0px 30px 0px;
        overflow-x: scroll;
        overflow-y: hidden;
    }
    .page-box-s {
        height: 40px;
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
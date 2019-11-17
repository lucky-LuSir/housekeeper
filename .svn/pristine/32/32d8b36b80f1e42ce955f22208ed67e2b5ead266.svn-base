<template>
    <div  class="pre-scrollable-s">
        <div class="pagex">
            <el-table
                    :data="upDownEntity"
                    stripe
                    border
                    size="small"
                    :height="fullHeight-235"
                    style="width: 100%">
                <el-table-column
                        type="index"
                        label="序号"
                        align="center"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="cusCode"
                        label="客户编码"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                        prop="cusEmpName"
                        label="客户所属人"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="empName"
                        label="上下架人"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="createDeptName"
                        label="创建部门"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusUpdownType"
                        label="类型"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="更新时间"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column
                        prop="cusDownType"
                        label="上下架类型"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="dealPeople"
                        label="被谁成交"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusDownReason"
                        label="下架原因"
                        align="center"
                        width="100"
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
        data(){
            return{
                fullHeight : document.documentElement.clientHeight,
                cusCodeUpDown:'',
                //一页数据
                pageSize:30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start:0,
                //跟进数据
                upDownEntity:[],
            }
        },
        created(){

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
        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight =document.documentElement.clientHeight
                    that.fullHeight =window.fullHeight
                })()
            }
        },
        methods:{
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getCusUpDownEntity();
            },
            getCusUpDownEntity(){
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = {"cusCode":vm.cusCodeUpDown}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/upDownQuery",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.upDownEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!customer/upDownQuery');
                })
            },
            //关闭面板清空数据
            closeDialog(){
                this.upDownEntity = [];
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
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 30px 0px;
        overflow-x: scroll;
        overflow-y: auto;
        /*background-color: #2aabd2;*/
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
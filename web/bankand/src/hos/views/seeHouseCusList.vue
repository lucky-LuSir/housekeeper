<template>
    <div class="pre-scrollable-s">
        <!--客户详情面板-->
        <cus-details ref="refocusDetails" v-if="cusDetailsOpenOrClose"></cus-details>

        <div class="pagex">
            <el-table
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    :data="customerEntityTwo"
                    size="small"
                    border
                    :height="fullHeight-225"
                    style="width: 100%">
                <el-table-column
                        prop="createTime"
                        align="center"
                        label="跟进时间"
                        width="160"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.createTime!=null">
                        {{ scope.row.createTime | time}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="cusCode"
                        label="客户编码"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusName"
                        label="客户姓名"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="followupContent"
                        label="客户看房评价"
                        align="center"
                        width="300"
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
                        prop="deptName"
                        label="所属部门"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column align="center"  fixed="right" label="操作" height="20">
                    <template slot-scope="scope" >
                        <el-button
                                type="success"
                                size="mini"
                                @click="toCusDetail(scope.$index,scope.row)">
                            详情
                        </el-button>
                    </template>
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
            cusDetails: ()=>import('../../cus/views/cusDetails.vue')
        },
        props: ["houseCodeSeeHouseCusList"],

        data(){
           return {
               loading:true,
               fullHeight: document.documentElement.clientHeight,
               //客户详情打开或者关闭
               cusDetailsOpenOrClose: false,
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
               //查看房源的客户数据
               customerEntityTwo:[],
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

        methods:{
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize=val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getSeeHouseCusList();
            },
            //查询房源的客户集合
            getSeeHouseCusList(){
                var vm = this;
                vm.loading=true;
                var sendObj = {};
                this.start = (this.currentPage-1)*this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity={"houseCode":vm.houseCodeSeeHouseCusList};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "house/takeLookByCusEvaluate",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.customerEntityTwo= response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.loading=false;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!house/seeHouseCusList');
                })
            },

            //跳转到客户详情
            toCusDetail(index,rows){
                this.cusDetailsOpenOrClose = true;
                this.$nextTick(() => {
                    try {
                        this.$refs.refocusDetails.visible = true
                        this.$refs.refocusDetails.cusCodeDetails = rows.cusCode;
                        this.$refs.refocusDetails.getCusDetails()
                    }catch (e) {
                        this.toCusDetail(index,rows)
                    }
                })
            },
            //关闭面板，清空数据
            closeDialog(){
                this.customerEntityTwo = [];
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
        overflow-y: auto;
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
        overflow-y: auto;
        overflow-x: hidden;
    }
</style>
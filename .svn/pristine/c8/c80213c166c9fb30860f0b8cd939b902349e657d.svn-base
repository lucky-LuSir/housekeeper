<template>
    <div class="pre-scrollable-s">
        <!--客户详情面板-->
        <cus-details ref="refocusDetail"  v-if="cusDetailsOpenOrClose" ></cus-details>
        <div class="pagex">
            <el-table
                    :data="customerEntityHosMatch"
                    stripe
                    size="mini"
                    border
                    :height="fullHeight-225"
                    style="width: 100%">
                <el-table-column
                        type="index"
                        label="序号"
                        align="center"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="cusName"
                        label="客户姓名"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                        prop="cusSexName"
                        label="客户性别"
                        align="center"
                        width="80"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="industry"
                        label="客户行业"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="enterTime"
                        label="最晚入住时间"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.enterTime!=null">{{ scope.row.enterTime | time}}</template>
                </el-table-column>
                <el-table-column
                        prop="houseTypeName"
                        label="库房用途"
                        align="center"
                        width="120"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="fireLevelName"
                        label="消防等级"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="needAcreage"
                        label="需求面积(㎡)"
                        align="center"
                        width="110"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="needPrice"
                        label="期望价格(元/㎡/天)"
                        align="center"
                        width="140"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="customerAreaEntityList"
                        label="需求区域"
                        align="center"
                        width="200"
                        show-overflow-tooltip>
                    <template slot-scope="scope">
                        <span v-for="(i,index) in scope.row.customerAreaEntityList" :key="index">
                            <span>
                                {{index+1 + ':'+i.cityName+i.regionName}}
                            </span>
                        </span>
                    </template>
                </el-table-column>
                <el-table-column align="center"  fixed="right" label="操作" width="100" height="20">
                    <template slot-scope="scope" >
                        <el-button
                                type="success"
                                size="mini"
                                @click="toCusDetails(scope.$index,scope.row)">
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
        data() {
            return {
                fullHeight: document.documentElement.clientHeight,
                cusChooseFollowupTypeOpenOrClose:false,
                houseCodeMatchCus:null,
                //客户详情打开或者关闭
                cusDetailsOpenOrClose: false,
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
                //查看房源的客户数据
                customerEntityHosMatch: [],
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
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getHouseMatchCusList();
            },
            //房源智能匹配客户
            getHouseMatchCusList() {
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize;
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.houseCode = vm.houseCodeMatchCus;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "houseApply/matchCus",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.customerEntityHosMatch = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!/houseApply/matchCus');
                })
            },

            //跳转到客户详情
            toCusDetails(index,rows) {
                this.cusDetailsOpenOrClose = true;
                this.$nextTick(() => {
                    try {
                        this.$refs.refocusDetail.visible = true
                        this.$refs.refocusDetail.cusCodeDetails = rows.cusCode;
                        this.$refs.refocusDetail.getCusDetails()
                    }catch (e) {
                        this.toCusDetails(index,rows)
                    }
                })
            },
            //关闭面板，清空数据
            closeDialog(){
                this.customerEntityHosMatch = [];
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
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
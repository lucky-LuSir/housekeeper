<template>
    <el-dialog :style="{height: fullHeight +100 +'px'}" top="10px" :before-close="closeDialog" append-to-body
               :visible.sync="visible" width="80%">
        <div class="pre-scrollable-s">
            <!--房源详情面板-->
            <hos-details :houseCodeDetails="houseCodeDetails" ref="hosByDetails"
                         v-if="houseDetailOpenOrClose"></hos-details>
            <cus-details ref="cusDetailsx" v-if="cusDetailsOpenOrClose"/>
            <div style="font-weight: bold;text-align: center">
                <p style="font-size: 30px;">发布成功</p>
                <p style="font-size: 20px;">您可以在“客户管理”中对该客户进行跟进、推送等操作</p>
            </div>
            <div>
                <p>以下房源您的客户可能感兴趣</p>
            </div>
            <div class="pagex">
                <el-table
                        :data="matchHosEntity"
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
                            prop="regionName"
                            align="center"
                            label="所在区域"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="streetName"
                            label="所在街道"
                            align="center"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="houseLocation"
                            label="地址"
                            align="center"
                            width="180"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.houseLocation.provinceName+scope.row.houseLocation.cityName+scope.row.houseLocation.regionName+scope.row.houseLocation.streetName}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="area"
                            label="面积(㎡)"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="price"
                            align="center"
                            label="价格(元/㎡/天)"
                            width="150"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="whereLayer"
                            label="所在层数"
                            align="center"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="layerHeight"
                            align="center"
                            label="层高(米)"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="maxElectric"
                            align="center"
                            label="用电量(KVA)"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="houseFrom"
                            align="center"
                            label="房源来源"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="forInsdustry"
                            label="适合行业"
                            align="center"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="features"
                            align="center"
                            label="房源特色"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="createTime"
                            align="center"
                            label="创建时间"
                            width="160"
                            show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime |
                            time}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="houseName"
                            label="房源标题"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="cityName"
                            label="所在城市"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="severalLayers"
                            label="共有层数"
                            align="center"
                            width="100"
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
                            prop="hasEia"
                            align="center"
                            label="是否可环评"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasEia==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="hasCut"
                            label="是否可分割"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasCut==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="hasCertificate"
                            align="center"
                            label="是否有产证"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasCertificate==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="hasRegistry"
                            label="是否可注册"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasRegistry==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="empName"
                            align="center"
                            label="服务专员"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="houseCode"
                            label="仓库编码"
                            align="center"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="lastFollowupTime"
                            label="最新跟进时间"
                            align="center"
                            width="160"
                            show-overflow-tooltip>
                        <template slot-scope="scope" v-if="scope.row.lastFollowupTime!=null">{{
                            scope.row.lastFollowupTime | time}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="houseStatusName"
                            align="center"
                            label="状态"
                            width="120"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="hasShortLease"
                            align="center"
                            label="是否可短租"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasShortLease==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="hasPlatform"
                            align="center"
                            label="是否有货台"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasPlatform==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="hasElevator"
                            label="是否有货梯"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasElevator==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="hasParking"
                            label="是否有停车位"
                            width="120"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            {{scope.row.hasParking==true?"有":"无"}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="housePurposeName"
                            label="库房用途"
                            align="center"
                            width="100"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="houseTypeName"
                            align="center"
                            label="库房类型"
                            width="80"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column align="center" fixed="right" label="操作" width="100" height="20">
                        <template slot-scope="scope">
                            <el-button
                                    type="success"
                                    size="mini"
                                    @click="toHouseDetail(scope.$index,scope.row)">
                                详情
                            </el-button>
                        </template>

                    </el-table-column>
                </el-table>
            </div>
            <div style="text-align: right">
                <el-button @click="toCusDetails"  type="primary" plain round size="small">
                    查看详情
                </el-button>
                <el-button round size="small"  type="primary" plain @click="closeDialogs">
                    完成
                </el-button>
            </div>
            <!-- 分页 -->
            <div class="page-box-s">
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
    import hosDetails from "../../hos/views/hosDetails.vue"
    import cusDetails from "./cusDetails"

    export default {
        components: {
            hosDetails,
            cusDetails
        },
        data() {
            return {
                visible:false,
                fullHeight: document.documentElement.clientHeight,
                cusDetailsOpenOrClose: false,
                cusCodeMatchHos: '',
                //房源详情面板打开或者关闭
                houseDetailOpenOrClose: false,
                houseCodeDetails: '',//传值给面板
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
                //智能匹配的房源list
                matchHosEntity: [],
            }
        },
        watch: {
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
            //分页
            handleSizeChange(val) {
                this.pageSizeData = val;
                this.pageSize = val;
                this.handleCurrentChange(1)
            },
            //改变每页数量
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getMatchHos();
            },
            getMatchHos() {
                var vm = this;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.cusCode = vm.cusCodeMatchHos;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cusApply/matchHos",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.matchHosEntity = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!cusApply/matchHos');
                })
            },
            //打开房源详情
            toHouseDetail(index, rows) {
                this.houseDetailOpenOrClose = true;
                this.houseCodeDetails = rows.houseCode;
                this.$nextTick(() => {
                    this.$refs.hosByDetails.visible = true
                    this.$refs.hosByDetails.getHouseDetails();
                })
            },
            //关闭面板清空数据
            closeDialog(done) {
                this.matchHosEntity = [];
                this.visible=false;
                done()
            },
            closeDialogs() {
                var vm = this;
                vm.matchHosEntity = [];
                vm.visible = false;

            },
            //跳转到客户详情
            toCusDetails() {
                var vm = this;
                vm.cusDetailsOpenOrClose = true;
                vm.$nextTick(() => {
                    try {
                        vm.$refs.cusDetailsx.visible = true;
                        vm.$refs.cusDetailsx.cusCodeDetails = vm.cusCodeMatchHos;//传值给组件
                        vm.$refs.cusDetailsx.getCusDetails();
                    } catch (e) {
                        vm.toCusDetails();
                    }

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

    .pagex {
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
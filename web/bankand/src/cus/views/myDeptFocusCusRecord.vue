<template>
    <el-dialog :style="{height: fullHeight +80 +'px'}" top="50px" :before-close="closeDialog" append-to-body
               :visible.sync="visible" width="70%">
        <div>
            <!--客户详情面板-->
            <cus-details ref="cusDetailsx" @refresh="getFocusmyDeptFocusCusRecord" v-if="cusDetailsOpenOrClose"/>
            <div style="font-weight: bold;text-align: center;margin-bottom:20px">
                <p style="font-size: 20px;">本部门集中获客推送记录</p>
            </div>
            <div style="margin-bottom: 20px">
                <el-input style="width: 300px;"
                          size="small"
                          placeholder="请输入员工姓名"
                          v-model="focusQuery.userName"
                          clearable>
                    <el-button slot="append" icon="el-icon-search" @click="getFocusmyDeptFocusCusRecord()"/>
                </el-input>
            </div>
            <!--数据表格-->
            <el-table
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    :data="myDeptFocusCusRecord"
                    highlight-current-row
                    :row-style="rowClass"
                    :height="fullHeight-280"
                    border
                    style="width: 100%;font-size: 12px">
                <el-table-column prop="focusTime" label="收到客户推送提醒时间" align="center" width="150px">
                    <template slot-scope="scope">
                        <span>{{scope.row.focusTime | time}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="seeCusTime" label="客户号码可查看时间" align="center" width="150px">
                    <template slot-scope="scope">
                        <span>{{scope.row.seeCusTime | time}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="specialPush" label="是否优先推送" align="center" width="150px">
                    <template slot-scope="scope">
                                    <span  v-if="scope.row.specialPush== true">
                                        是
                                    </span>
                        <span  v-if="scope.row.specialPush== false">
                                        否
                                    </span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="followCount"
                        label="客户跟进次数"
                        align="center"
                        width="110"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="userName"
                        label="员工姓名"
                        align="center"
                        width="90"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="ownerDeptName"
                        label="所属部门"
                        align="center"
                        width="90"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="closeTime"
                        label="全平台推送倒计时"
                        align="center"
                        width="140">
                    <template slot-scope="scope" v-if="scope.row.closeTime!=null">
                        <template v-if="scope.row.closeTime!=0">{{ scope.row.closeTime}} 小时</template>
                        <template v-if="scope.row.closeTime==0">已全平台推送</template>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="cusName"
                        label="客户名"
                        align="center"
                        width="90"
                        show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                        prop="cusTypeName"
                        label="客户所属"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>
              <!--  <el-table-column
                        prop="cusPhone"
                        label="客户号码"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>-->
                <el-table-column
                        prop="cusSexName"
                        label="性别"
                        align="center"
                        width="70"
                        show-overflow-tooltip>
                </el-table-column>
              <!--  <el-table-column
                        prop="customerAreaEntityList[0].regionName"
                        label="需求区域"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>-->
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
                        prop="cusStatusName"
                        label="客户状态"
                        align="center"
                        width="100"
                        show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                        prop="lastUpshelfTime"
                        label="最新上架时间"
                        align="center"
                        width="160"
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.lastUpshelfTime!=null">{{ scope.row.lastUpshelfTime |
                        time}}
                    </template>
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
                                @click="cusDetailsClick(scope.$index,scope.row)">
                            详 情
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
    import cusDetails from "./cusDetails.vue"

    export default {

        components: {
            cusDetails
        },
        data() {
            return {
                focusQuery: {
                    userName: null,
                },
                visible: false,
                loading: true,
                focusCount: 0,
                selectRow: [],
                selectData: [],
                fullHeight: document.documentElement.clientHeight,
                //客户详情 打开或者关闭
                cusDetailsOpenOrClose: false,
                pageSize: 30,
                //一页数据
                pageSizeData: 30,
                //总数据
                totalCount: 1,
                //当前页
                currentPage: 1,
                //第几个数
                start: 0,
                cusCode: '',//客户code
                myDeptFocusCusRecord: [],//数据表格  客户list
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
                        this.selectRow.push(this.myDeptFocusCusRecord.indexOf(item));
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
                this.getFocusmyDeptFocusCusRecord();
            },
            //查询客户
            getFocusmyDeptFocusCusRecord() {
                var vm = this;
                vm.loading = true;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.entity = this.focusQuery;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/myDeptFocusCusQuery",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.myDeptFocusCusRecord = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.loading = false;
                }).catch(function (error) {
                    vm.loading = false;
                    vm.$message.error('页面:获取数据失败!customer/queryFocusCus');
                })
            },
            //打开客户详情面板
            cusDetailsClick(index, rows) {
                this.cusDetailsOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.cusDetailsx.visible = true;
                    this.$refs.cusDetailsx.cusCodeDetails = rows.cusCode;//传值给组件
                    this.$refs.cusDetailsx.getCusDetails();
                })
            },
            //关闭面板
            closeDialog(done) {
                this.myDeptFocusCusRecord = [];
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

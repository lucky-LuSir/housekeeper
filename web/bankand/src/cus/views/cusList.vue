<template>
    <div style="margin-top: 15px;">
        <div class="hst_first_topbar">
            <el-button class="navigation_button_back" size="mini" plain type="warning" icon="el-icon-delete"
                       @click="emptyCondition()">条件清除
            </el-button>
            <el-button class="navigation_button_back" size="mini" plain type="primary" icon="el-icon-search"
                       @click="searchCus()">按条件查询
            </el-button>
            <el-button class="navigation_button_back" size="mini" plain type="primary"
                       icon="el-icon-circle-plus-outline" v-if="cusCreateBtn"
                       @click="addCus()">新增客户
            </el-button>
            <el-button class="navigation_button_back" size="mini" plain type="primary"
                       icon="el-icon-circle-plus-outline" v-if="entryType=='1'"
                       @click="waitAddCusOpen()">排队新增列表
            </el-button>

            <el-divider direction="vertical"></el-divider>
            <el-button class="navigation_button_back" round size="mini" type="primary"
                       @click="focusCusList()">平台为您优先推送了{{focusCount}}个他人不可见客户,点击查看！
            </el-button>
            <el-button v-if="focusCusBtn" class="navigation_button_back" round size="mini" type="primary"
                       @click="focusCusRecord()">集中获客推送记录
            </el-button>
            <el-button v-if="myDeptFocusCusBtn" class="navigation_button_back" round size="mini" type="primary"
                       @click="myDeptFocusCusRecord()">本部门集中获客记录
            </el-button>
        </div>
        <!-- 客户按条件查询面板 -->
        <cus-condition ref="cusByCondition" v-if="cusConditionOpenOrClose" @changeCus="serchCusClick"/>
        <!--客户详情面板-->
        <cus-details ref="cusDetailsx" @refresh="getCusList" v-if="cusDetailsOpenOrClose"/>

        <cus-insert-or-edit ref="refCusAdd" v-if="cusUpdateOpenOrClose" @changeCusAdd="addCusSuccess"/>

        <focus-cus-list ref="refFocusCus" v-if="focusCusOpenOrClose"></focus-cus-list>

        <focus-record ref="refFocusCusRecord" v-if="focusCusRecordOpenOrClose"></focus-record>
        <my-dept-focus-cus-record ref="refMyDeptFocusCusRecord"
                                  v-if="myDeptFocusCusRecordOpenOrClose"></my-dept-focus-cus-record>
        <wait-add-cus-list ref="waitAddCus" v-if="waitAddCusOpenOrClose"></wait-add-cus-list>
        <!--数据表格-->
        <el-table
                @row-dblclick="cusDetails"
                v-loading="loading"
                element-loading-text="拼命加载中"
                :data="cusList"
                highlight-current-row
                @selection-change="handleSelectionChange"
                :row-style="rowClass"
                :height="fullHeight-180"
                border
                @sort-change='sortChange'
                :default-sort="{prop: 'daysNotFollowup', order: 'ascending'}"
                style="width: 100%;font-size: 11px">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    prop="daysNotFollowup"
                    label="未跟进天数"
                    align="center"
                    width="110"
                    sortable='custom'
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="cusName"
                    label="客户名"
                    align="center"
                    width="90"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="cusSexName"
                    label="性别"
                    align="center"
                    width="70"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="customerAreaEntityList[0].regionName"
                    label="需求区域"
                    align="center"
                    width="100"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="needAcreage"
                    label="需求面积(㎡)"
                    align="center"
                    width="130"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="houseTypeName"
                    label="找房用途"
                    align="center"
                    width="100"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>

            <el-table-column
                    prop="products"
                    label="产品信息"
                    align="center"
                    width="150"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="更多描述"
                    align="center"
                    width="200"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="cusStatusName"
                    label="客户状态"
                    align="center"
                    width="100"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="cusTypeName"
                    label="客户所属"
                    align="center"
                    width="100"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="创建时间"
                    align="center"
                    width="160"
                    sortable
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.createTime!=null">{{ scope.row.createTime |
                    time}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="lastUpshelfTime"
                    label="最新上架时间"
                    align="center"
                    width="160"
                    sortable
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
                    sortable
                    show-overflow-tooltip>
                <template slot-scope="scope" v-if="scope.row.enterTime!=null">{{ scope.row.enterTime | time}}</template>
            </el-table-column>

            <el-table-column
                    prop="industry"
                    label="客户行业"
                    align="center"
                    width="150"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="cusCode"
                    label="客户编码"
                    align="center"
                    width="200"
                    sortable
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column align="center" fixed="right" label="操作" width="80" height="10">
                <template slot-scope="scope">
                    <el-button
                            type="success"
                            size="mini"
                            @click="cusDetailsClick(scope.$index,scope.row)">
                        详 情
                    </el-button>
                </template>

            </el-table-column>
            <el-table-column  v-if="cusDeleteBtn" align="center" fixed="right" label="删除" width="80" height="10">
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
</template>

<script>
    import cusDetails from "./cusDetails.vue"
    import cusCondition from "./cusCondition.vue";
    import cusAddOrUpdate from "./cusAddOrUpdate.vue"
    import cusInsertOrEdit from "./cusInsertOrEdit.vue"
    import focusCusList from "./focusCusList.vue"
    import focusRecord from "./focusCusRecord"
    import myDeptFocusCusRecord from "./myDeptFocusCusRecord";
    import waitAddCusList from "./waitAddCusList";

    export default {

        components: {
            cusDetails,
            cusCondition,
            cusAddOrUpdate,
            cusInsertOrEdit,
            focusCusList,
            focusRecord,
            myDeptFocusCusRecord,
            waitAddCusList
        },
        data() {
            return {
                cusDeleteBtn:false,
                waitAddCusOpenOrClose: false,
                myDeptFocusCusBtn: false,
                focusCusBtn: false,
                myDeptFocusCusRecordOpenOrClose: false,
                focusCusRecordOpenOrClose: false,
                focusCusOpenOrClose: false,
                loading: true,
                focusCount: 0,
                selectRow: [],
                selectData: [],
                fullHeight: document.documentElement.clientHeight,
                cusCreateBtn: false,//客户创建 权限
                //按条件查询面板 打开或者关闭
                cusConditionOpenOrClose: false,
                //客户详情 打开或者关闭
                cusDetailsOpenOrClose: false,
                //客户新增
                cusUpdateOpenOrClose: false,
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
                cusList: [],//数据表格  客户list
                searchCusCondition: {
                    keyword: null,//关键字查
                    customerQuery: {
                        queryType: null,//查询类型
                        cusStatus: null,//客户状态
                        openFlag: null,//是否公开状态
                        cusPhone: null,//客户手机号
                        empName: null,//经纪人name
                        customerNotFollowupType: null,//未跟进天数枚举
                        customerNotFollowupDay: null,//未跟进天数
                        needAcreageStart: null,//需求最小面积
                        needAcreageEnd: null,//需求最大面积
                        needVoltageStart: null,//最小电量
                        needVoltageEnd: null,//最大电量
                        needPriceStart: null,//最小价格
                        needPriceEnd: null,//最大价格
                        layerHeight: null,//需求层高
                        layerNum: null,//   楼层类型（1. 底楼 2. 楼上 3. 全部）
                        enterTimeStart: null,// 最小入住时间
                        enterTimeEnd: null,// 最大入住时间
                        companyName: null,//客户公司
                        industry: null,//行业性质
                        cusSortType: null,//排序类型
                    },
                },
                entryType: null
            }
        },
        created() {
            this.cusCreateBtn = this.$flagMenuStore.judgeMenu("cus-create");
            this.focusCusBtn = this.$flagMenuStore.judgeMenu("focus-cus");
            this.myDeptFocusCusBtn = this.$flagMenuStore.judgeMenu("my-dept-focus-cus");
            this.cusDeleteBtn = this.$flagMenuStore.judgeMenu("cus-delete");
            this.handleSizeChange(30)
        },
        mounted() {
            const that = this
            var vm = this;
            let entryType = vm.$cookieStore.getCookie("entryType");
            if ((entryType != null)) {
                vm.entryType = entryType;
            }
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
                        this.selectRow.push(this.cusList.indexOf(item));
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
            myDeptFocusCusRecord() {
                var vm = this;
                vm.myDeptFocusCusRecordOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.refMyDeptFocusCusRecord.visible = true;
                    this.$refs.refMyDeptFocusCusRecord.getFocusmyDeptFocusCusRecord();
                })
            },
            focusCusRecord() {
                var vm = this;
                vm.focusCusRecordOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.refFocusCusRecord.visible = true;
                    this.$refs.refFocusCusRecord.getFocusfocusCusRecord();
                })
            },
            focusCusList() {
                var vm = this;
                vm.focusCusOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.refFocusCus.visible = true;
                    this.$refs.refFocusCus.getFocusfocusCusList();
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

            sortChange(column, prop, order) {
                if (column != null) {
                    if (column.prop == "daysNotFollowup") {
                        if (column.order == "ascending") {
                            this.searchCusCondition.customerQuery.cusSortType = "6";
                        } else {
                            this.searchCusCondition.customerQuery.cusSortType = "5";
                        }
                    }
                } else {
                    this.searchCusCondition.customerQuery.cusSortType = "6";
                }
                this.getCusList();
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
                this.getCusList();
            },
            //查询客户
            getCusList() {
                var vm = this;
                vm.loading = true;
                var sendObj = {};
                this.start = (this.currentPage - 1) * this.pageSize
                sendObj.start = this.start;
                sendObj.pageSize = this.pageSize;
                sendObj.keyword = this.searchCusCondition.keyword;
                sendObj.entity = this.searchCusCondition.customerQuery;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/query",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.cusList = response.data.result.data;
                    vm.totalCount = response.data.result.totalCount;
                    vm.pageSizeData = vm.pageSize;
                    vm.loading = false;
                    vm.focusCounts(vm);
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!customer/query');
                })
            },
            focusCounts(vm) {
                var sendObj = {};
                var option = {
                    method: 'POST',
                    data: sendObj,
                    headers: {'content-type': 'application/json'},
                    url: "customer/checkFocusCusNotice",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.focusCount = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!customer/checkFocusCusNotice');
                })
            },
            //重置按条件查询条件
            emptyCondition() {
                this.searchCusCondition.keyword = null;
                this.searchCusCondition.customerQuery.queryType = null;//查询类型
                this.searchCusCondition.customerQuery.cusStatus = null;//客户状态
                this.searchCusCondition.customerQuery.openFlag = null;//是否公开状态
                this.searchCusCondition.customerQuery.cusPhone = null;//客户手机号
                this.searchCusCondition.customerQuery.empName = null;//经纪人name
                this.searchCusCondition.customerQuery.customerNotFollowupType = null;//未跟进天数枚举
                this.searchCusCondition.customerQuery.customerNotFollowupDay = null;//未跟进天数
                this.searchCusCondition.customerQuery.needAcreageStart = null;//需求最小面积
                this.searchCusCondition.customerQuery.needAcreageEnd = null;//需求最大面积
                this.searchCusCondition.customerQuery.needVoltageStart = null;//最小电量
                this.searchCusCondition.customerQuery.needVoltageEnd = null;//最大电量
                this.searchCusCondition.customerQuery.needPriceStart = null;//最小价格
                this.searchCusCondition.customerQuery.needPriceEnd = null;//最大价格
                this.searchCusCondition.customerQuery.layerHeight = null;//需求层高
                this.searchCusCondition.customerQuery.layerNum = null;//   楼层类型（1. 底楼 2. 楼上 3. 全部）
                this.searchCusCondition.customerQuery.enterTimeStart = null;// 最小入住时间
                this.searchCusCondition.customerQuery.enterTimeEnd = null;// 最大入住时间
                this.searchCusCondition.customerQuery.companyName = null;//客户公司
                this.searchCusCondition.customerQuery.industry = null;//行业性质

                this.handleSizeChange(30);

                this.$nextTick(() => {
                    this.$refs.cusByCondition.emptyConditions();
                })

            },
            //打开按条件查询面板
            searchCus() {
                this.cusConditionOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.cusByCondition.dialogFormVisible = true;
                    this.$refs.cusByCondition.loadDeptList();
                })
            },
            //按条件查询
            serchCusClick(obj) {
                this.searchCusCondition = obj;
                this.handleSizeChange(30);
                this.$nextTick(() => {
                    this.$refs.cusByCondition.dialogFormVisible = false;
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

            //打开客户详情面板
            cusDeleteClick(index, rows) {
                this.$confirm('是否删除当前客户？', '操作提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let cusCode = rows.cusCode;
                    vm.loading = true;
                    var sendObj = {}
                    sendObj.entity = {"cusCode": cusCode}
                    var option = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "customer/delete",
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

            //打开客户详情面板
            cusDetails(rows) {
                this.cusDetailsOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.cusDetailsx.visible = true;
                    this.$refs.cusDetailsx.cusCodeDetails = rows.cusCode;//传值给组件
                    this.$refs.cusDetailsx.getCusDetails();
                })
            },
            //关闭面板
            closeDialog() {
                this.$refs.cusDetailsx.closeDialog();
            },
            //新增客户
            addCus() {
                this.cusUpdateOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.refCusAdd.visible = true
                    this.$refs.refCusAdd.type = '2'
                })
            },
            //新增客户
            waitAddCusOpen() {
                this.waitAddCusOpenOrClose = true;
                this.$nextTick(() => {
                    this.$refs.waitAddCus.visible = true
                    this.$refs.waitAddCus.getWaitAddCusList();
                })
            },
            //新增成功
            addCusSuccess() {
                this.handleSizeChange(30);
            },

        }
    }
</script>

<style scoped>
    .hst_first_topbar {
        margin: 0px 0px 10px 15px;
        padding: 0px 0px 0px 0px;
    }

    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }

    .page-box {
        height: 30px;
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
        overflow-x: auto;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }
</style>

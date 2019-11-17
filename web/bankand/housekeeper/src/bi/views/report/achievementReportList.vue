<template slot-scope="scope">
    <div>
        <el-container>
            <!--子菜单-->
            <el-aside width="200px" class="aside">
        <!-- 部门树 -->
        <div class="left_bar">
            <el-tree :data="deptTree"
                     :props="defaultProps"
                     :expand-on-click-node="false"
                     :highlight-current="true"
                     @node-click="handleNodeClick"
                     node-key="deptCode"
                     ref="tree"
            >
            </el-tree>
        </div>
            </el-aside>
            <el-main style="padding: 0px">
                <div class="top_bar">
                    <div class="top_refresh">
                        <el-button type="primary" size="mini" icon="el-icon-refresh" @click="report(true)" circle/>
                        <b style="font-size: 16px;color: #395dd2">最新统计数据</b>
                    </div>
                    <div class="top_select">
                        <el-date-picker
                                v-model="dateValue"
                                type="daterange"
                                align="right"
                                unlink-panels
                                size="small"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                        <el-button type="primary" size="small" plain  icon="el-icon-search" @click="report">查询</el-button>
                        <el-button type="success" size="small" plain  icon="el-icon-download" class="navigate_select_button" @click="exportExcel()" >导出</el-button>
                    </div>
                </div>
                <!-- 数据表头 -->
                <div class="reght_table">
                    <el-table :data="tableData" style="overflow-y: auto" id="reportId" ref="elTable" v-loading="loading" max-height="450" :row-class-name="tableRowClassName">
                        <template v-for="(column,index) in tableHead">
                            <template v-if="index != 0")>
                                <el-table-column
                                        sortable
                                        :prop="column.prop"
                                        :label="column.label"
                                        width="120">
                                </el-table-column>
                            </template>
                            <template v-else>
                                <el-table-column

                                        fixed="left"
                                        :prop="column.prop"
                                        :label="column.label"
                                        width="150">
                                </el-table-column>
                            </template>
                        </template>
                    </el-table>
                    <el-collapse v-model="activeNames" @change="handleChange">
                        <el-collapse-item name="1">
                            <template slot="title">
                                <i class="el-icon-setting"></i> 部门业绩指标
                            </template>
                            <div>
                                <el-cascader
                                        :props="selectProps"
                                        placeholder="选择部门"
                                        :options="deptTree"
                                        filterable
                                        size="mini"
                                        :change-on-select="true"
                                        :show-all-levels="false"
                                        clearable
                                        v-model="ownerCode"
                                ></el-cascader>
                                <el-date-picker
                                        v-model="qaTime"
                                        type="month"
                                        size="mini"
                                        placeholder="选择月">
                                </el-date-picker>
                                <el-button type="success" size="mini" icon="el-icon-search" class="navigate_select_button" @click="queryQuota()">查询</el-button>
                                <el-button size="mini" @click="dialogVisible = true">新增</el-button>
                            </div>
                            <el-table :data="quotaData" max-height="170">
                                <el-table-column
                                        prop="ownerName"
                                        label="所属部门"
                                        width="150">
                                </el-table-column>
                                <el-table-column
                                        prop="qaTime"
                                        label="指标时间"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="hosValue"
                                        label="房源开发"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="hosAuthValue"
                                        label="房源委托"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="hosFollowValue"
                                        label="房源跟进"
                                        width="110">
                                </el-table-column>
                                <el-table-column
                                        prop="cusValue"
                                        label="客户开发"
                                        width="110">
                                </el-table-column>
                                <el-table-column
                                        prop="cusFollowValue"
                                        label="客户跟进"
                                        width="110">
                                </el-table-column>
                                <el-table-column
                                        prop="cusEffectiveValue"
                                        label="有效客户"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="ptValue"
                                        label="兼职开发"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="seeValue"
                                        label="客户带看"
                                        width="110">
                                </el-table-column>
                                <el-table-column
                                        prop="negotiation"
                                        label="客户谈判"
                                        width="100">
                                </el-table-column>
                                <el-table-column
                                        prop="ownerFollowValue"
                                        label="业主拜访"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="signing"
                                        label="签约"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="totalMoneyValue"
                                        label="业绩金额"
                                        width="90">
                                </el-table-column>
                            </el-table>
                        </el-collapse-item>
                    </el-collapse>
                </div>
            </el-main>

        <el-dialog  :close-on-click-modal="false" title="新增业绩指标" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">
            <el-form :model="dialogQuotaObj" :rules="rules" ref="ruleFormRef" label-width="100px"
                     style="text-align: center;">

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="指标时间:" prop="qaTime">
                                <el-date-picker
                                        v-model="dialogQuotaObj.qaTime"
                                        type="month"
                                        placeholder="选择月">
                                </el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="所属部门:" prop="ownerCode">
                                <el-cascader
                                        :props="selectProps"
                                        placeholder="选择部门"
                                        :options="deptTree"
                                        filterable
                                        ref="cascaders"
                                        :change-on-select="true"
                                        :show-all-levels="false"
                                        clearable
                                        v-model="dialogQuotaObj.ownerCode"
                                ></el-cascader>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="房源开发:" prop="hosValue">
                                <el-input v-model="dialogQuotaObj.hosValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="房源委托:" prop="hosAuthValue">
                                <el-input v-model="dialogQuotaObj.hosAuthValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="房源跟进:" prop="hosFollowValue">
                                <el-input v-model="dialogQuotaObj.hosFollowValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="业主拜访:" prop="ownerFollowValue">
                                <el-input v-model="dialogQuotaObj.ownerFollowValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="客户开发:" prop="cusValue">
                                <el-input v-model="dialogQuotaObj.cusValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="客户跟进:" prop="cusFollowValue">
                                <el-input v-model="dialogQuotaObj.cusFollowValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="有效客户:" prop="cusEffectiveValue">
                                <el-input v-model="dialogQuotaObj.cusEffectiveValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="客户带看:" prop="seeValue">
                                <el-input v-model="dialogQuotaObj.seeValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="客户谈判:" prop="negotiation">
                                <el-input v-model="dialogQuotaObj.negotiation"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="兼职开发:" prop="ptValue">
                                <el-input v-model="dialogQuotaObj.ptValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="签约:" prop="signing">
                                <el-input v-model="dialogQuotaObj.signing"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="业绩指标:" prop="totalMoneyValue">
                                <el-input v-model="dialogQuotaObj.totalMoneyValue"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

            </el-form>
            <div style="text-align:center">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addQuota('ruleFormRef')">保存</el-button>
            </div>
        </el-dialog>
        </el-container>
    </div>
</template>

<script scoped>
    import FileSaver from 'file-saver';
    import XLSX from 'xlsx';
    import ElMain from "../../../../node_modules/element-ui/packages/main/src/main";
    export default {
        components: {ElMain},
        data() {
            //校验数字只能为整数
            var checkInteger = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('楼层数只能为正整数'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            return {

                // 树结构默认指定
                defaultProps: {
                    children: 'children',
                    label: 'deptName'
                },

                //  指定
                selectProps: {
                    children: 'children',
                    label: 'deptName',
                    value: 'deptCode'
                },

                // 部门树
                deptTree: [],

                // 报表头
                tableHead: [],

                // 报表数据
                tableData: [],

                // 报表数据
                quotaData: [],

                // 指标实体
                dialogQuotaObj:{
                    qaTime: '', // 指标时间
                    quotaType: '', // 指标类型
                    ownerCode: [], // 所属部门编码
                    ownerName: '', // 所属部门名称
                    hosValue: '', // 房源开发指标
                    hosAuthValue: '', // 签委托房源指标
                    hosFollowValue: '', // 房源跟进指标
                    cusValue: '', // 客户开发指标
                    cusFollowValue: '', // 客户跟进指标
                    cusEffectiveValue: '', // 有效客户指标
                    ptValue: '', // 兼职指标
                    seeValue: '', // 客户带看指标
                    negotiation: '', // 客户谈判
                    ownerFollowValue: '', // 业主拜访
                    signing: '', // 签约指标
                    totalMoneyValue: '' // 业绩指标
                },

                // 指标实体验证
                rules: {
                    qaTime: [
                        {required: true, message: '请选择日期', trigger: 'blur'},
                    ],
                    ownerCode: [
                        {required: true, message: '请选择部门', trigger: 'blur'},
                    ],
                    hosValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    hosAuthValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    hosFollowValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    cusValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    cusFollowValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    cusEffectiveValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    ptValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    seeValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    negotiation: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    ownerFollowValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    signing: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    totalMoneyValue: [
                        {required: true, message: '请输入数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                },

                // 指标查询时间
                qaTime:'',

                // 指标查询编码
                ownerCode:[],

                // 加载动画
                loading : false,

                // 业绩报表查询日期
                dateValue: '',

                // 折叠
                activeNames: ['1'],

                // 新增窗口
                dialogVisible: false
            }
        },
        created:function (){
            var vm = {};
            vm = this;
            var sendObj = {};
            var options = {
                method: 'POST',
                data: sendObj,
                url: "dept/selectTrees",
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.deptTree = response.data.result;
                vm.ownerCode = [vm.deptTree[0].deptCode];
                vm.report();
            }).catch(function (error) {
                vm.$message.error('页面：获取部门树失败！');
            });
        },
        methods:{

            handleNodeClick() {
                // 请求后台报表接口
                this.report();
            },

            report(dynamic){
                var vm = {};
                vm = this;
                vm.loading = true;
                // 获取日期
                var startDate = null;
                var endDate = null;
                if(vm.dateValue != "" && vm.dateValue != null){
                    var dateValues = vm.dateValue.toString().split(",");
                    startDate = new Date(dateValues[0]);
                    endDate = new Date(dateValues[1]);
                }
                // 获取刷新按钮
                if(dynamic != true){
                    dynamic = null;
                }
                // 获取当前节点部门树选中的数据
                var deptCode = vm.$refs.tree.getCurrentKey();

                var sendObj = {"dynamic":dynamic};
                sendObj.entity = {"deptCode":deptCode,"startDate":startDate,"endDate":endDate};

                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "report/countDto",
                   /* url:"report/personnelReportSearch",*/
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$refs.elTable.clearSort();
                    vm.tableData = response.data.result.reportEntities;
                    vm.tableHead = response.data.result.tableHeads;
                    vm.loading = false
                }).catch(function (error) {
                    vm.$message.error('页面：获取报表失败！');
                    vm.loading = false
                });
            },

            addQuota(ruleFormRef){
                var vm = {};
                vm = this;

                vm.$refs[ruleFormRef].validate((valid) => {
                    if (valid) {
                        var obj = {};
                        obj = vm.dialogQuotaObj;
                        obj.ownerCode = obj.ownerCode[obj.ownerCode.length-1];
                        var names = vm.$refs.cascaders.currentLabels;
                        obj.ownerName =names[names.length-1];
                        obj.quotaType = 1;
                        obj.qaTime = obj.qaTime.format("yyyy-MM");
                        var sendObj = {};
                        sendObj.entity = obj;
                        var options = {
                            method: 'POST',
                            headers: {'content-type': 'application/json'},
                            data: sendObj,
                            url: "quota/create",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                            vm.dialogVisible = false;
                            vm.reInitDialogQuotaObj();
                        }).catch(function (error) {
                            vm.dialogVisible = false;
                            vm.reInitDialogQuotaObj();
                            vm.$message.error('页面:获取数据失败!role/create');
                        });
                    } else {
                        vm.$message.error('填写不规范,请检查!');
                        return false;
                    }
                })
            },

            queryQuota(){
                let vm = this;

                var code = vm.ownerCode;
                var time = vm.qaTime;

                if(code.length <= 0){
                    vm.$message.error('请选择指标部门!');
                    return false;
                }

                if(time == null || time == ''){
                    vm.$message.error('请选择指标日期!');
                    return false;
                }
                code = code[code.length-1];
                var sendObj = {"ownerCode": code, "qaTime": time};
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "quota/select",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.quotaData = response.data.result.data;
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!role/select');
                });
            },

            exportExcel(){
                /* 1创建 workbook 对象 from table */
                //alert("导出开始");
                var reports;
                reports = document.querySelector('#reportId');
                var wb = XLSX.utils.table_to_book(reports)
                /* 2获得 binary string as output */
                var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
                try {
                    FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '业绩统计报表.xlsx')
                } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout); console.log("导出excel捕获的异常ttt");}
                return wbout

            },

            handleClose(done) {
                let vm = {};
                vm = this;
                vm.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                        vm.reInitDialogQuotaObj();
                    })
                    .catch(_ => {});
            },

            reInitDialogQuotaObj(){
                this.dialogQuotaObj = {
                        qaTime: '', // 指标时间
                        quotaType: '', // 指标类型
                        ownerDeptCode: [], // 所属者编码
                        ownerDeptName: '', // 所属者名称
                        hosValue: '', // 房源开发指标
                        hosAuthValue: '', // 签委托房源指标
                        hosFollowValue: '', // 房源跟进指标
                        cusValue: '', // 客户开发指标
                        cusFollowValue: '', // 客户跟进指标
                        cusEffectiveValue: '', // 有效客户指标
                        ptValue: '', // 兼职指标
                        seeValue: '', // 客户带看指标
                        negotiation: '', // 客户谈判
                        ownerFollowValue: '', // 业主拜访
                        signing: '', // 签约指标
                        totalMoneyValue: '' // 业绩指标
                }
            },

            handleChange(val) {
                console.log(val);
            },

            // 渲染离职人员样式
            tableRowClassName({row, rowIndex}) {
                if (row.quitTime != null) {
                    return 'report-row';
                }
                return '';
            }
        }
    }
</script>

<style scoped>
    .root{
        width: 100%;
    }
    .top_bar{
        width: 100%;
        margin:0px 0px 0px 0px;
        border-width: 0px 0px 1px 0px;
        border-style:inset;
        padding:0px 0px 20px 0px;

    }

    .top_refresh{
        float: left;
        margin-left: 20px;
    }

    .top_select{
        float: none;
        margin-left: 235px;
    }

    .left_bar{
        border-width:0px 1px 0px 0px;
        overflow-y: auto;
    }

    .reght_table{
    }

    /*每一列的块区域*/
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }



</style>

<!--下面为全局样式，不随便添加-->
<style>
    .el-table .report-row {
        background-color: #ff9492;
    }
</style>
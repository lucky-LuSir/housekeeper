<template slot-scope="scope">
    <div class="root" ref="myRoot">
        <el-container>
            <!--子菜单-->
            <el-aside width="200px" class="aside">
                <el-main style="padding: 2px">
                    <!--按钮-->
                    <div class="top_bar">
                        <div class="top_select">
                            <el-button type="primary" plain icon="el-icon-create" @click="showCreate()">新增部门分成配置
                            </el-button>
                        </div>
                    </div>
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
                </el-main>
            </el-aside>

            <!--部门分成信息列表-->
            <el-main>
                <div class="dept_table">

                <el-table
                        :data="deptList"
                        :highlight-current-row="true"
                        style="width: 100%;text-align: left;"
                        :show-header="true"
                        :border="true"
                        max-height="735"
                >

                    <el-table-column type="selection" width="45">
                    </el-table-column>

                    <el-table-column v-if="false" align="center" label="ID" min-width="50">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.id}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column v-if="false" align="center" label="部门编码" min-width="100">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.deptCode}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="部门名称" min-width="200">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.deptName}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="分成类型" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.profitShareTypeName}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="获客/上架分成" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.acquireCus}}%</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="客户推送" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.cusPush}}%</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="带看" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.followupSee}}%</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="成交" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderDeal}}%</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="房源开发" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.houseDevelop}}%</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" label="房源委托" min-width="150">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.houseEntrust}}%</span>
                        </template>
                    </el-table-column>

                    <el-table-column align="center" fixed="right" label="操作" min-width="150" height="20">
                        <template slot-scope="scope" style="float: right;text-align: right;">
                            <el-button type="primary"
                                       style="text-align: right;" size="mini"
                                       @click="showUpdate(scope.$index, scope.row)">修改
                            </el-button>
                        </template>
                    </el-table-column>

                </el-table>

                <div class="page-box">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="start"
                            :page-sizes="pageSizes"
                            :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>

            </div>
            </el-main>
        </el-container>

        <!--新增/修改-->
        <el-dialog :close-on-click-modal="false" :visible.sync="dialogVisible" width="40%" :before-close="handleClose">

            <el-form label="部门分成比配置" label-width="100px" :model="dialogEntity" :rules="rules" ref="ruleFormRef"
                     style="text-align: center;">

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="分成类型:" prop="profitShareType">
                                <el-select v-model="dialogEntity.profitShareType" placeholder="请选择"
                                           @change="selectChange()" :disabled="disabledProfitShareTypes">
                                    <el-option
                                            v-for="item in profitShareTypes"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col v-show="!selectDept" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="所属部门:" prop="deptCodes">
                                <el-cascader
                                        :props="selectProps"
                                        placeholder="选择部门"
                                        :options="deptTree"
                                        filterable
                                        ref="cascaders"
                                        :change-on-select="true"
                                        :show-all-levels="false"
                                        clearable
                                        v-model="dialogEntity.deptCodes"
                                ></el-cascader>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col v-show="selectDept" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="所属部门:" prop="acquireCus">
                                <el-input v-model="dialogEntity.deptName" disabled></el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item :label="acquireCusLabel" prop="acquireCus">
                                <el-input v-model="dialogEntity.acquireCus" :disabled="disabledAcquireCus">
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="客户推送:" prop="cusPush">
                                <el-input v-model="dialogEntity.cusPush" disabled>
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="带看:" prop="followupSee">
                                <el-input v-model="dialogEntity.followupSee">
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="成交:" prop="orderDeal">
                                <el-input v-model="dialogEntity.orderDeal">
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

                <el-row :gutter="5" style="text-align: left;">

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="房源开发:" prop="houseDevelop">
                                <el-input v-model="dialogEntity.houseDevelop">
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content ">
                            <el-form-item label="房源委托:" prop="houseEntrust">
                                <el-input v-model="dialogEntity.houseEntrust">
                                    <template slot="append">%</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>

                </el-row>

            </el-form>

            <div style="text-align:center">
                <el-button @click="reInitDialogEntity()">取消</el-button>
                <el-button v-show="showCreateBtn" type="primary" @click="create('ruleFormRef')">新增</el-button>
                <el-button v-show="showUpdateBtn" type="primary" @click="update('ruleFormRef')">更新</el-button>
            </div>

        </el-dialog>
    </div>
</template>

<script scoped>
    export default {
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

                // 部门树
                deptTree: [],

                // 树结构默认指定
                defaultProps: {
                    children: 'children',
                    label: 'deptName'
                },

                // 新增窗口
                dialogVisible: false,

                // 获客类型
                profitShareTypes: [{
                    value: 'platform',
                    label: '公司获客'
                }, {
                    value: 'personal',
                    label: '个人获客'
                }],

                //  指定
                selectProps: {
                    children: 'children',
                    label: 'deptName',
                    value: 'deptCode'
                },

                // 新增按钮
                showCreateBtn: false,

                // 更新按钮
                showUpdateBtn: false,

                // 禁用
                disabledAcquireCus: false,

                acquireCusLabel: '获客分成',

                // 禁用
                disabledProfitShareTypes: false,

                // 回显部门名称
                selectDept: false,

                // 新增实体
                dialogEntity: {
                    id: '',
                    deptCode: '',
                    deptCodes: [],
                    deptName: '',
                    profitShareType: '',
                    profitShareTypeName: '',
                    acquireCus: 0,
                    cusPush: 0,
                    followupSee: 0,
                    orderDeal: 0,
                    houseDevelop: 0,
                    houseEntrust: 0
                },

                // 新增验证
                rules: {
                    deptCodes: [
                        {type: 'array', required: true, message: '请选择部门', trigger: 'blur'},
                    ],
                    profitShareType: [
                        {required: true, message: '请选择分成类型', trigger: 'blur'},
                    ],
                    acquireCus: [
                        {required: true, message: '请输入分成数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    cusPush: [
                        {required: true, message: '请输入分成数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    followupSee: [
                        {required: true, message: '请输入分成数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    orderDeal: [
                        {required: true, message: '请输入分成数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    houseDevelop: [
                        {required: true, message: '请输入分成数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ],
                    houseEntrust: [
                        {required: true, message: '请输入分成数值', trigger: 'blur'},
                        {validator: checkInteger},
                    ]
                },

                // 查询参数
                start: 1,
                pageSize: 20,
                total: 0,
                pageSizes: [20, 50],
                keyword: '',

                /*部门列表*/
                deptList: [],
            }
        },

        created: function () {
            var vm = {};
            vm = this;
            // 加载部门树
            vm.deptTrees();
        },

        methods: {

            showCreate() {
                this.dialogVisible = true; // 新增窗口
                this.showCreateBtn = true;
                this.showUpdateBtn = false;
                this.disabledProfitShareTypes = false; // 开启分成类型选择
                this.selectDept = false; // 回显部门名称
            },

            create(ruleFormVal) {
                var vm = {};
                vm = this;

                vm.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {

                        var obj = {};
                        obj = vm.dialogEntity;

                        obj.deptCode = obj.deptCodes[obj.deptCodes.length - 1];

                        // 选择部门名称
                        //var names = vm.$refs.cascaders.currentLabels;
                        obj.deptName = vm.$refs.cascaders.inputValue;

                        var sendObj = {};
                        sendObj.entity = obj;
                        var options = {
                            method: 'POST',
                            data: sendObj,
                            url: "profitShare/create",
                        };

                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                            vm.dialogVisible = false;
                            vm.reInitDialogEntity();
                            vm.basicReLoad();
                        }).catch(function (error) {
                            vm.dialogVisible = false;
                            vm.reInitDialogEntity();
                            vm.$message.error('页面:获取数据失败!role/create');
                        });
                    }
                });
            },

            showUpdate(index, rowsObj) {
                // 赋值
                this.dialogEntity = rowsObj;
                // 级联菜单回显
                var deptCodes = [];
                deptCodes.push(rowsObj.deptCode);
                this.dialogEntity.deptCodes = deptCodes;
                // 类型判断
                this.selectChange();
                // 打开窗口
                this.dialogVisible = true;
                // 禁止修改分成类型
                this.disabledProfitShareTypes = true;
                this.showCreateBtn = false;
                this.showUpdateBtn = true;
                this.selectDept = true;
            },

            update(ruleFormVal) {

                var vm = {};
                vm = this;

                vm.$refs[ruleFormVal].validate((valid) => {
                    if (valid) {
                        var obj = {};
                        obj = vm.dialogEntity;
                        var sendObj = {};
                        sendObj.entity = obj;
                        var options = {
                            method: 'POST',
                            data: sendObj,
                            url: "profitShare/update",
                        };

                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                            vm.dialogVisible = false;
                            vm.reInitDialogEntity();
                            vm.basicReLoad();
                        }).catch(function (error) {
                            vm.dialogVisible = false;
                            vm.reInitDialogEntity();
                            vm.$message.error('页面:获取数据失败!role/create');
                        });
                    }
                });
            },

            selectChange() {
                var vm = {};
                vm = this;
                var obj = vm.dialogEntity;
                if (obj.profitShareType == 'platform') {
                    vm.disabledAcquireCus = false;
                    vm.acquireCusLabel = '上架分成';
                    vm.dialogEntity.cusPush = 10;
                    //vm.dialogEntity.acquireCus = 0;
                } else if (obj.profitShareType == 'personal') {
                    vm.disabledAcquireCus = false;
                    vm.acquireCusLabel = '获客分成';
                    vm.dialogEntity.cusPush = 10;
                    //vm.dialogEntity.acquireCus = 0;
                }
            },

            // 部门树
            deptTrees() {
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
                    // 查询部门分成列表
                    vm.basicReLoad();
                }).catch(function (error) {
                    vm.$message.error('页面：获取部门树失败！');
                });
            },

            handleNodeClick() {
                this.basicReLoad();
            },

            // 查询部门分成比
            basicReLoad() {
                var vm = {};
                vm = this;
                var flag = true;
                if (flag) {

                    var obj = {};
                    var sendObj = {};
                    sendObj.start = (vm.start - 1) * vm.pageSize;
                    sendObj.pageSize = vm.pageSize;
                    sendObj.keyword = this.keyword;

                    // 获取当前节点部门树选中的数据
                    var deptCode = vm.$refs.tree.getCurrentKey();
                    obj.deptCode = deptCode;

                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "profitShare/select",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.deptList = response.data.result.data;
                        vm.total = response.data.result.totalCount;
                    }).catch(function (error) {
                        vm.$message.error('页面:获取数据失败!profitShare/select');
                    });
                }
            },

            // 分页查询
            handleSizeChange(val) {
                this.pageSize = val;
                this.basicReLoad();
            },

            handleCurrentChange(val) {
                this.start = val;
                this.basicReLoad();
            },

            // 关闭
            handleClose(done) {
                let vm = {};
                vm = this;
                vm.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                        vm.reInitDialogEntity();
                    })
                    .catch(_ => {
                    });
            },

            // 关闭dialog重置实体类
            reInitDialogEntity() {
                this.dialogVisible = false
                this.dialogEntity = {
                    id: '',
                    deptCode: '',
                    deptCodes: [],
                    deptName: '',
                    profitShareType: '',
                    profitShareTypeName: '',
                    acquireCus: 0,
                    cusPush: 0,
                    followupSee: 0,
                    orderDeal: 0,
                    houseDevelop: 0,
                    houseEntrust: 0
                }
            },
        }
    }
</script>

<style scoped>

    .root {
        width: 100%;
    }

    .top_bar {
        width: 100%;
        margin: 0px 0px 0px 0px;
        border-width: 0px 0px 1px 0px;
        border-style: inset;
        padding: 5px 0px 5px 10px;

    }

    .left_bar {
        border-width: 0px 1px 0px 0px;
        overflow-y: auto;
    }

    .top_select {
        float: none;
        margin-left: 5px;
    }

    .dept_table {
        background-color: white;
        min-height: 60px;
        line-height: 40px;
    }

    .page-box {
        height: 40px;
        padding-top: 0px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }

</style>
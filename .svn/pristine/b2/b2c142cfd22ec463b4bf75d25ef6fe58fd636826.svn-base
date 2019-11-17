<template>
    <div class="root"><!--根部分-->

        <!-- 找商人员数据看板-->
        <div class="rece-cost-exception-div">
            <span class="park-data-title-font">招商人员任务数据看板： {{ deptName }}</span>

            <el-date-picker style="margin-left: 20px;margin-top: 10px;"
                    v-model="taskTimeList"
                    type="daterange"
                    align="right"
                    size="small"
                    unlink-panels
                    :clearable="false"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    :picker-options="pickerOptions">
            </el-date-picker>
            <el-button style="margin-left: 20px" size="small" type="warning" plain @click="selectBoard">查询</el-button>

            <el-button class="edit-button-delete" size="small" type="warning" plain @click="deleteBoard">删除</el-button>
            <el-button class="edit-button-edit" size="small" type="warning" plain @click="editBoard">配置</el-button>

            <div class="rece-cost-exception-table">
                <el-table
                        :data="taskList"
                        max-height="400"
                        border>
                    <el-table-column
                            label="招商人员"
                            align="center"
                            prop="empName">
                    </el-table-column>
                    <el-table-column
                            label="横幅"
                            align="center"
                            prop="banner">
                    </el-table-column>
                    <el-table-column
                            label="宣传单"
                            align="center"
                            prop="leaflets">
                    </el-table-column>
                    <el-table-column
                            label="朋友圈"
                            align="center"
                            prop="friendsShare">
                    </el-table-column>
                    <el-table-column
                            label="客户跟进"
                            align="center"
                            prop="customerVisits">
                    </el-table-column>
                    <el-table-column
                            label="获客数"
                            align="center">
                        <el-table-column
                                label="主动获客"
                                align="center"
                                prop="initiative">
                        </el-table-column>
                        <el-table-column
                                label="中介获客"
                                align="center"
                                prop="agent">
                        </el-table-column>
                        <el-table-column
                                label="库房无忧获客"
                                align="center"
                                prop="kuFangWuYou">
                        </el-table-column>
                    </el-table-column>
                    <!--<el-table-column
                            label="今日描述"
                            align="center"
                            prop="description">
                    </el-table-column>-->
                </el-table>
            </div>

            <!--部门参数弹框-->
            <el-dialog  :close-on-click-modal="false" :title="dialogTitle" :visible.sync="isShowDeptParam">
                <el-form ref="deptParam" :rules="rules" :model="deptParam" label-width="80px">
                    <el-form-item label="选择部门" prop="deptCode">
                        <el-select
                                v-model="deptParam.deptCode"
                                clearable
                                filterable
                                default-first-option
                                placeholder="输入部门名称">
                            <el-option
                                    v-for="item in deptNames"
                                    :key="item.code"
                                    :label="item.name"
                                    :value="item.code">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="onDeptParamSubmit('deptParam')">立即创建</el-button>
                        <el-button @click="isShowDeptParam= false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>

    </div>
</template>


<script scoped>
    export default {
        props: ['id', 'param'],
        created: function () {
            let param = {};
            param.entity = {
                createDeptCode: this.param,
                taskTimeList: this.taskTimeList
            };
            this.initCreated(param, 1);
        },
        data() {
            return {
                isShowDeptParam: false,
                dialogTitle: "",
                deptNames: [],
                deptParam: {
                    deptCode: ''
                },
                taskList: [],
                deptName: "",
                taskTimeList: [],
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },

                rules: {
                    deptCode: [
                        {required: true, message: '部门名称不能为空', trigger: 'blur'}
                    ],
                },
            }
        },
        methods: {
            //通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id: this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url: "board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$message({
                            showClose: true,
                            message: '删除看板成功',
                            type: 'success'
                        });
                        this.$router.replace('/back')
                    }).catch(error => {
                        vm.$alert('页面:删除招商人员任务数据看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                                vm.$message({
                                    type: 'info',
                                    message: `action: ${ action }`
                                });
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            initCreated(param, flag) {
                let vm = this;
                let option = {
                    method: 'POST',
                    data: param,
                    url: 'task/select'
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.taskList = response.data.result.taskList;
                    if (flag==1){
                        let firstDate = response.data.result.firstDate;
                        let endDate = response.data.result.endDate;
                        vm.taskTimeList.push(firstDate, endDate);
                    }
                }).catch(function (error) {
                    vm.$alert('页面：查询招商人员任务数据看板信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {}
                    });
                });


                let deptParam = {};
                deptParam.entity = {
                    deptCode: vm.param,
                };

                let deptOptions = {
                    method: 'POST',
                    data: deptParam,
                    url: "dept/findByCode",
                };
                this.$ajax(
                    deptOptions
                ).then(function (response) {
                    vm.deptName = response.data.result.deptName;
                }).catch(function (error) {
                    vm.$alert('页面：招商人员任务数据看板查询部门信息失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 查询按钮
            selectBoard() {
                if (this.taskTimeList) {
                    if (this.taskTimeList[0] && this.taskTimeList[1]) {
                        let param = {};
                        param.entity = {
                            createDeptCode: this.param,
                            taskTimeList: this.taskTimeList
                        };
                        this.initCreated(param);
                    }
                } else {
                    this.$message.error('请选择查询日期');
                }
            },

            //配置按钮
            editBoard() {
                this.dialogTitle = "招商人员任务数据看板参数配置";
                let vm = this;
                vm.isShowDeptParam = true;
                //部门搜索栏
                let deptObj = {};
                let deptOptions = {
                    method: 'POST',
                    data: deptObj,
                    url: "dept/list",
                };
                this.$ajax(
                    deptOptions
                ).then(function (response) {
                    let result = response.data.result;
                    let deptNameList = [];
                    let deptObj = [];
                    for (let s = 0; s < result.length; s++) {
                        let code = result[s].deptCode;
                        let name = result[s].deptName;
                        deptNameList.push(
                            {code: code, name: name}
                        );
                        deptObj[code] = name;
                    }
                    vm.deptNames = deptNameList;
                    vm.deptObj = deptObj;
                }).catch(function (error) {
                    vm.$alert('页面:招商人员任务数据看板查询部门列表失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                            vm.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });
                });
                vm.deptParam.deptCode = vm.param;

            },
            onDeptParamSubmit(subForm) {
                this.$refs[subForm].validate((valid) => {
                    if (valid) {
                        this.isShowDeptParam = false;

                        let vm = this;
                        //房源搜索栏
                        let param = {};
                        let entity = {
                            id: vm.id,
                            param: vm.deptParam.deptCode
                        };
                        param.entity = entity;

                        let options = {
                            method: 'POST',
                            data: param,
                            url: "board/update",
                        };
                        this.$ajax(
                            options
                        ).then(function (response) {
                            vm.$message({
                                showClose: true,
                                message: '配置看板成功',
                                type: 'success'
                            });
                            vm.$router.replace('/back');
                        }).catch(function (error) {
                            vm.$alert('页面:配置招商人员任务数据看板失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    vm.$message({
                                        type: 'info',
                                        message: `action: ${ action }`
                                    });
                                }
                            });
                        });
                    }
                });
            },
        }
    }

</script>

<style scoped>


    .root {
        margin-right: 15px;
        min-width: 1000px;
        height: auto;
        max-height: 450px;
        background-color: white;

        overflow-x: hidden;
    }

    /*数据看板*/
    .rece-cost-exception-div {
    }

    /*数据看板表格*/
    .rece-cost-exception-table {
        margin-left: 5px;
        width: 100%;
    }

    /*数据看板文字*/
    .park-data-title-font {
        float: left;
        font-size: medium;
        color: #FF9900;
        font-weight: bold;
        line-height: 50px;
        margin-left: 15px;
    }

    /*编辑按钮*/
    .edit-button-edit {
        float: right;
        margin-top: 10px;
        margin-right: 10px;
    }

    /*删除按钮*/
    .edit-button-delete {
        float: right;
        margin-top: 10px;
        margin-right: 20px;
    }
</style>
<template>
    <div class="root">

        <!-- 招商人员数据看板-->
        <div class="rece-cost-exception-div">
            <span class="park-data-title-font">招商人员去化看板： {{ deptName }}</span>

            <el-date-picker style="margin-left: 20px;"
                    v-model="dateChangeList"
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
                        :data="empBiTableList"
                        max-height="400"
                        border>
                    <el-table-column align="center"
                            label="排名"
                            prop="rankNum">
                    </el-table-column>

                    <el-table-column align="center"
                            label="姓名"
                            prop="empName">
                    </el-table-column>

                    <el-table-column align="center"
                            label="电话"
                            prop="empPhone">
                    </el-table-column>

                    <el-table-column align="center"
                            label="负责园区"
                            prop="pkName">
                    </el-table-column>

                    <el-table-column align="center"
                            label="获客数"
                            prop="cusCount">
                    </el-table-column>

                    <el-table-column align="center"
                            label="所有谈判中"
                            prop="cusNegotiateCount">
                    </el-table-column>

                    <el-table-column align="center"
                            label="入驻客户"
                            prop="cusEntryCount">
                    </el-table-column>

                    <el-table-column align="center"
                            label="去化面积"
                            prop="signAreaMonth">
                    </el-table-column>

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
        components: {},
        created: function () {
            let sendObj = {};
            sendObj.start = 0;
            sendObj.pageSize = 50;
            sendObj.entity = {};
            sendObj.deptCode = this.param;

            this.queryList(sendObj, 1);
        },
        data() {
            return {
                isShowDeptParam: false,
                dialogTitle: "",
                deptNames: [],// 配置按钮集合
                deptParam: {
                    deptCode: ''
                },
                dateChangeList: [],// 日期选择组件绑定数据
                empBiTableList: [],// 表格数据绑定
                deptName: "",

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
            queryList(sendObj, flag){
                let vm = {};
                vm = this;

                let options = {
                    method: 'POST',
                    data: sendObj,
                    url: "empBi/query",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.empBiTableList = response.data.result.empBiList;

                    if(flag == 1){
                        let firstDate = response.data.result.firstDate;
                        let endDate = response.data.result.endDate;
                        vm.dateChangeList.push(firstDate, endDate);
                    }
                }).catch(function (error) {
                    vm.$alert('页面:招商人员数据看板查询统计数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });


                let param = {};
                param.entity = {
                    deptCode: this.param,
                };
                let deptOptions = {
                    method: 'POST',
                    data: param,
                    url: "dept/findByCode",
                };
                this.$ajax(
                    deptOptions
                ).then(function (response) {
                    vm.deptName = response.data.result.deptName;
                }).catch(function (error) {
                    vm.$alert('页面:招商人员数据看板获取部门名称数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                });
            },
            // 日期组件，查询按钮
            selectBoard(){
                let sendObj = {};
                sendObj.start = 0;
                sendObj.pageSize = 50;
                sendObj.entity = {
                    dateStartAndEndList : this.dateChangeList,
                };
                sendObj.deptCode = this.param;
                this.queryList(sendObj);
            },
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
                        this.$router.replace('/back');
                    }).catch(error => {
                        vm.$alert('页面:删除招商人员数据看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
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

            //配置按钮
            editBoard() {
                this.dialogTitle = "招商人员数据看板参数配置";
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
                    vm.$alert('页面:招商人员数据看板获取部门数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
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
                            vm.$alert('页面:招商人员数据看板配置失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
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
        width: 99%;
    }

    /*数据看板文字*/
    .park-data-title-font {
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
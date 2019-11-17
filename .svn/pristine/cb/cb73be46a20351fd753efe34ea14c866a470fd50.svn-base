<template>
    <el-dialog  :close-on-click-modal="false" title="新增拜访" align="center" :before-close="closeDialog" top="100px" width="30%"
               :visible.sync="hosOwnerFollowupVisible" :modal-append-to-body='false'>
        <el-form :rules="rules" align="left" ref="form" :model="houseOwnerFollowup">
            <el-row style="text-align: left">
                <span style="font-weight: bold">业主姓名</span>
                : {{ownerInfo.houseownerName}}
            </el-row>
            <el-row style="text-align: left">
                <span style="font-weight: bold">拜访类型</span> :
                <el-radio-group size="mini" v-model="houseOwnerFollowup.followupType">
                    <el-radio-button label="1">电话拜访</el-radio-button>
                    <el-radio-button label="2">上门拜访</el-radio-button>
                </el-radio-group>
            </el-row>
            <el-row>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <span style="font-weight: bold">服务专员</span> : {{userObj.userName}}
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <span style="font-weight: bold"> 所属部门</span> : {{userObj.ownerDeptName}}
                </el-col>
            </el-row>
            <el-form-item label="跟进结果:" prop="followupContext">
                <el-input type="textarea" v-model="houseOwnerFollowup.followupContext"/>
            </el-form-item>
        </el-form>
        <el-button size="small" plain type="success" @click="submitForm('form')">提交
        </el-button>
    </el-dialog>
</template>

<script>
    import ElDialog from "../../../node_modules/element-ui/packages/dialog/src/component";
    import ElForm from "../../../node_modules/element-ui/packages/form/src/form";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";
    import ElInput from "../../../node_modules/element-ui/packages/input/src/input";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElRadioButton from "../../../node_modules/element-ui/packages/radio/src/radio-button";
    export default {
        components: {
            ElRadioButton,
            ElCol,
            ElRow,
            ElInput,
            ElFormItem,
            ElForm,
            ElDialog
        },
        data() {
            return {
                ownerInfo:{},
                hosOwnerFollowupVisible: false,
                userObj: {
                    userName: this.$cookieStore.getCookie("userName"),
                    ownerDeptName: this.$cookieStore.getCookie("ownerDeptName") != null
                        ? this.$cookieStore.getCookie("ownerDeptName")
                        : "独立经纪人"
                },
                houseOwnerFollowup: {
                    houseownerCode: '',
                    followupType: 1,
                    followupContext: ''
                },
                // 验证
                rules: {
                    followupContext: [
                        {required: true, message: '请填写跟进结果', trigger: 'blur'},
                    ],
                },
            }

        },
        created(){

        },


        methods: {

            closeDialog(done){
                var vm = this;
                vm.clearData(vm);
                done()
            },

            clearData(vm){
                vm.houseOwnerFollowup.followupContext = '';
                vm.houseOwnerFollowup.followupType = 1;
            },

            //添加
            submitForm(formName){
                var vm = this;

                var obj = {}
                obj.entity  = vm.houseOwnerFollowup;
                obj.entity.houseownerCode = vm.ownerInfo.houseownerCode;
                if (vm.houseOwnerFollowup){
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            var options = {
                                method: "POST",
                                data: obj,
                                url: "houseownerFollowup/create"
                            };
                            this.$ajax(
                                options
                            ).then(response => {
                                vm.$notify({
                                    message: '操作成功',
                                    type: 'success'
                                });

                                vm.clearData(vm);
                                vm.hosOwnerFollowupVisible=false;
                                vm.$emit('changeSuccess');
                            }).catch(error => {
                                vm.$notify({
                                    type: 'info',
                                    message: '新增业主拜访失败'
                                });
                            });
                        } else {
                            vm.$notify({
                                message: '数据填写不完整',
                                type: 'warning'
                            });
                        }
                    })
                }

            },

        }
    }
</script>

<style scoped>
    .input-width {
        width: 240px;
    }
</style>
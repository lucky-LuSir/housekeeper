<template>
    <div class="root" ref="myRoot">
        <el-form :model="empObj" :rules="rules" ref="empObj" label-width="110px">
            <div class="back-button-root-div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-button class="back-button" size="small" type="primary"  icon="el-icon-back" plain @click="goBack2()">返回</el-button>
                <span class="back-button-title">人员修改</span>
                <el-button class="save-button" size="small" type="primary" @click="goBack2()" plain>取消</el-button>
                <el-button class="save-button" size="small" type="primary" @click="submitForm('empObj')" plain>保存
                </el-button>
            </div>

            <div style="min-height: 68px"></div>

            <div class="common-title-div" style="margin-top: -10px;">
                <span class="common-title-font">基本信息</span>
            </div>
            <div class="emp-info-root-div">
                <div v-if="fileType == 'image'" class="img-div1" :class="{imgDiv2:!empObj.empImg || !imgCode}">
                    <template>
                        <i @click="removeImg1()" class="el-icon-circle-close" style="float: right;" v-if="imgCode"></i>
                        <img height="100%" width="100%" :src=" hkpBaseUrl + 'file/read/' + empObj.empImg "
                             v-if="imgCode">
                        <file style="margin-top: 10px;" @selectFile="selectImg" class="file-button"
                              v-if="!imgCode"></file>
                    </template>
                </div>

                <div class="emp-info-div">
                    <el-row :gutter="10" class="common-el-row-style">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="姓名：" prop="empName">
                                    <el-input clearable v-model="empObj.empName"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="性别：" prop="sex">
                                    <el-radio-group v-model="empObj.sex">
                                        <el-radio label="1">先生</el-radio>
                                        <el-radio label="2">女士</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="联系电话：" prop="empPhone">
                                    <el-input v-model.number="empObj.empPhone" @blur="checkPhone()"
                                              type="number"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="身份证号：" prop="card">
                                    <el-input clearable v-model="empObj.card"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="出生日期：" prop="birthDate">
                                    <el-date-picker style="width: 100%;"
                                                    v-model="empObj.birthDate"
                                                    type="date"
                                                    placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="婚姻状况：" prop="hasMarry">
                                    <el-radio-group v-model="empObj.hasMarry">
                                        <el-radio :label=true>已婚</el-radio>
                                        <el-radio :label=false>未婚</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="8">
                            <div class="grid-content">
                                <el-form-item label="籍贯：" prop="householdLocation">
                                    <el-input clearable v-model="empObj.householdLocation"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                        <el-col :span="9">
                            <div class="grid-content">
                                <el-form-item label="户籍类型：" prop="householdType">
                                    <el-radio-group v-model="empObj.householdType">
                                        <el-radio label="1">农业</el-radio>
                                        <el-radio label="2">非农业</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="16">
                            <div class="grid-content">
                                <el-form-item label="毕业学校：" prop="graduate">
                                    <el-input clearable v-model="empObj.graduate"></el-input>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>

                    <el-row :gutter="10">
                        <el-col :span="21">
                            <div class="grid-content">
                                <el-form-item label="最高学历：" prop="education">
                                    <el-radio-group v-model="empObj.education">
                                        <el-radio label="1">博士后</el-radio>
                                        <el-radio label="2">博士</el-radio>
                                        <el-radio label="3">硕士</el-radio>
                                        <el-radio label="4">本科</el-radio>
                                        <el-radio label="5">专科</el-radio>
                                        <el-radio label="6">高中</el-radio>
                                        <el-radio label="7">初中</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                            </div>
                        </el-col>
                    </el-row>
                </div>
                <div style="clear: both;"></div>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">紧急联系人</span>
            </div>
            <div class="contact-root-div">
                <el-row :gutter="20">
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="姓名：" prop="urgName">
                                <el-input clearable v-model="empObj.urgName"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="联系电话：" prop="urgPhone">
                                <el-input v-model.number="empObj.urgPhone" type="number"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="两人关系：" prop="urgRelation">
                                <el-input clearable v-model="empObj.urgRelation"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>


            <div class="common-title-div">
                <span class="common-title-font">岗位信息</span>
            </div>
            <div class="contact-root-div">
                <el-row :gutter="20">
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="所属部门：" prop="deptCode">
                                <el-select v-model="empObj.deptCode" @change="queryDept" filterable placeholder="请选择">
                                    <el-option v-for="(item,index) in deptList"
                                               :key="item.code"
                                               :label="item.name"
                                               :value="item.code"
                                    >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="岗位职称：" prop="postCode">
                                <el-select v-model="empObj.postCode" @change="queryPost" filterable placeholder="请选择">
                                    <el-option v-for="(item,index) in postList"
                                               :key="item.code"
                                               :label="item.name"
                                               :value="item.code">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-col>
                    <!--<el-col :span="5" class="common-el-row-style">-->
                    <!--<div class="grid-content">-->
                    <!--<el-form-item label="负责区域：">-->
                    <!--<el-select-->
                    <!--v-model="empObj.manageArea"-->
                    <!--multiple-->
                    <!--filterable-->
                    <!--remote-->
                    <!--reserve-keyword-->
                    <!--placeholder="请输入关键词"-->
                    <!--:remote-method="remoteMethod"-->
                    <!--:loading="loading">-->
                    <!--<el-option-->
                    <!--v-for="item in options4"-->
                    <!--:key="item.value"-->
                    <!--:label="item.label"-->
                    <!--:value="item.value">-->
                    <!--</el-option>-->
                    <!--</el-select>-->
                    <!--</el-form-item>-->
                    <!--</div>-->
                    <!--</el-col>-->
                </el-row>
            </div>

            <div class="common-title-div">
                <span class="common-title-font">劳务信息</span>
            </div>
            <div class="labor-root-div">
                <el-row :gutter="20">
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="合同开始：" prop="contractStartDate">
                                <el-date-picker
                                        v-model="empObj.contractStartDate"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="合同结束：" prop="contractEndDate">
                                <el-date-picker
                                        v-model="empObj.contractEndDate"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7" class="common-el-row-style">
                        <div class="grid-content">
                            <el-form-item label="缴社保地：" prop="socialSecurityCity">
                                <el-input clearable v-model="empObj.socialSecurityCity"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>

                <el-row :gutter="20">
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="开户行：" prop="openBank">
                                <el-input clearable v-model="empObj.openBank"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div class="grid-content">
                            <el-form-item label="银行卡号：" prop="bankCard">
                                <el-input v-model="empObj.bankCard"></el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <div class="has-user">
                <el-row :gutter="18">
                    <el-col :span="8" style="margin-top: 5px;">
                        <div class="grid-content">
                            <el-form-item label="是否生成登陆账号：" prop="hasUser" label-width="160px">
                                <el-radio-group v-model="empObj.hasUser">
                                    <el-radio :label=true>是</el-radio>
                                    <el-radio :label=false>否</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </div>
                    </el-col>
                </el-row>
            </div>

            <!--末尾提交按钮处-->
            <div style="height: 50px;margin-top: 20px;">
                <el-button style="float: left;margin-left: 45%;" type="primary" @click="submitForm('empObj')">提交
                </el-button>
                <el-button style="float: left;" @click="resetForm('empObj')">重置
                </el-button>
            </div>


        </el-form>
    </div>
</template>

<script>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElOption from "../../../../node_modules/element-ui/packages/select/src/option";
    import file from '@/common/components/file.vue'

    export default{
        components: {
            ElOption,
            file
        },
        created: function () {
            var vm = {};
            vm = this;

            let param;
            param = vm.$route.params.paramsObj;
            vm.flag = vm.$route.params.flag;

            if (param) {
                vm.imgCode = vm.$route.params.paramsObj.empImg;
                vm.empObj = param;
            } else {
                vm.goBack1();
            }

            //查询部门
            var options = {
                method: "POST",
                data: {},
                url: "dept/queryDeptList"
            };
            this.$ajax(options).then(function (response) {
                if (!response.data.isException) {
                    let result = response.data.result.deptList;
                    let deptNames = [];
                    let depts = {};
                    for (let i = 0; i < result.length; i++) {
                        let code = result[i].deptCode;
                        let name = result[i].deptName;
                        deptNames.push(
                            {code: code, name: name}
                        );
                        depts[code] = name;
                    }
                    vm.deptList = deptNames;
                    vm.depts = depts;
                }
            }).catch(function (error) {
                vm.$message({
                    showClose: true,
                    message: '页面:部门列表加载失败!dept/queryDeptList',
                    type: 'error'
                })
            })

            //查询职位
            var options1 = {
                method: "POST",
                data: {},
                url: "post/queryPostList"
            };
            this.$ajax(options1).then(function (response) {
                if (!response.data.isException) {
                    let result = response.data.result;
                    let postNames = [];
                    let posts = {};
                    for (let i = 0; i < result.length; i++) {
                        let code = result[i].postCode;
                        let name = result[i].postName;
                        postNames.push(
                            {code: code, name: name}
                        );
                        posts[code] = name;
                    }
                    vm.postList = postNames;
                    vm.posts = posts;
                }
            }).catch(function (error) {
                vm.$message({
                    showClose: true,
                    message: '页面:职位列表加载失败!post/queryPostList',
                    type: 'error'
                })
            })
        },
        mounted(){
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        data(){
            //校验电话号码
            var checkPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //检查人员电话是否存在
            var checkEmpPhone = (rule, value, callback) => {
                setTimeout(() => {
                    let pattern = this.$MOBILE_PATTERN;
                    if (value == this.checkPhoneNum) {
                        callback(new Error('电话号话已存在'));
                    } else if (!pattern.test(value)) {
                        callback(new Error('请输入正确的电话号码'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            //校验身份证
            var checkIdCard = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/.test(value))) {
                        callback(new Error('请输入正确的身份证号'));
                    } else {
                        callback();
                    }
                }, 100);
            };
            // 检验银行卡号只能为数字
            var checkBankCard = (rule, value, callback) => {
                setTimeout(() => {
                    if (!(/^[0-9]\d*$/.test(value))) {
                        callback(new Error('银行卡号只能为数字'));
                    } else {
                        callback();
                    }
                }, 100);
            };

            return {
                flag: "",
                floatDivWidth: "",
                depts: {},
                empObj: {
                    empCode: "",
                    workNumber: "",
                    empName: "",
                    empImg: "",
                    sex: "",
                    empPhone: "",
                    card: "",
                    birthDate: "",
                    householdLocation: "",
                    householdType: "",
                    graduate: "",
                    education: "",
                    hasMarry: "",
                    urgName: "",
                    urgPhone: "",
                    urgRelation: "",
                    deptCode: "",
                    deptName: "",
                    postCode: "",
                    postName: "",
                    manageArea: "",
                    contractStartDate: "",
                    contractEndDate: "",
                    socialSecurityCity: "",
                    openBank: "",
                    bankCard: "",
                    hasUser: "",
                },
                imgCode: "",
                fileType: "image",
                rules: {
                    empName: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                    ],
                    sex: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    empPhone: [
                        {required: true, message: '请输入电话', trigger: 'blur'},
                        {validator: checkEmpPhone}
                    ],
                    card: [
                        {required: true, message: '请输入身份证', trigger: 'blur'},
                        {validator: checkIdCard}
                    ],
                    birthDate: [
                        {required: true, message: '请选择出生日期', trigger: 'change'}
                    ],
                    householdLocation: [
                        {required: true, message: '请输入籍贯', trigger: 'blur'}
                    ],
                    householdType: [
                        {required: true, message: '请选择户籍类型', trigger: 'change'}
                    ],
                    graduate: [
                        {required: true, message: '请输入毕业学校', trigger: 'blur'}
                    ],
                    education: [
                        {required: true, message: '请选择学历', trigger: 'change'}
                    ],
                    hasMarry: [
                        {required: true, message: '请选择婚姻状况', trigger: 'change'}
                    ],
                    urgName: [
                        {required: true, message: '请输入紧急联系人姓名', trigger: 'blur'}
                    ],
                    urgPhone: [
                        {required: true, message: '请输入紧急联系人电话', trigger: 'blur'},
                        {validator: checkPhone}
                    ],
                    urgRelation: [
                        {required: true, message: '请输入与紧急联系人的关系', trigger: 'blur'}
                    ],
                    deptCode: [
                        {required: true, message: '请选择所属部门', trigger: 'change'}
                    ],
                    postCode: [
                        {required: true, message: '请选择岗位职称', trigger: 'change'}
                    ],
                    contractStartDate: [
                        {required: true, message: '请选择合同开始日期', trigger: 'change'}
                    ],
                    contractEndDate: [
                        {required: true, message: '请选择合同结束日期', trigger: 'change'}
                    ],
                    socialSecurityCity: [
                        {required: true, message: '请输入缴纳社保地', trigger: 'blur'}
                    ],
                    openBank: [
                        {required: true, message: '请输入开户行', trigger: 'blur'}
                    ],
                    bankCard: [
                        {required: true, message: '请输入银行卡号', trigger: 'blur'},
                        {min: 12, max: 21, message: '银行卡号长度在 12 到 21 个字符', trigger: 'blur'},
                        {validator: checkBankCard},
                    ],
                    hasUser: [
                        {required: true, message: '请选择是否生成登陆账号', trigger: 'change'},
                    ],
                },//rules
                deptList: [],
                postList: []
            }//return
        },//data
        methods: {
            //头像上传
            selectImg(urls){
                if (urls.length == 1) {
                    this.empObj.empImg = urls.toString();
                    this.imgCode = urls.toString();
                } else {
                    this.$message({
                        showClose: true,
                        message: '仅支持上传一张照片',
                        type: 'warning'
                    });
                    return;
                }
            },
            //编辑时移除图片
            removeImg1(){
                this.empObj.empImg = '';
                this.imgCode = '';
            },
            //change事件
            queryDept(val){
                this.empObj.deptCode = val;
                this.empObj.deptName = this.depts[val];
            },
            queryPost(val){
                this.empObj.postCode = val;
                this.empObj.postName = this.posts[val];
            },
            //返回按钮
            goBack1(){
                this.$router.push({
                    name: "empList",
                })
            },//goback
            //返回按钮
            goBack2(){
                if (this.flag == 1) {
                    this.$router.push({
                        name: "empDetails",
                        params: {
                            empParams: {
                                empCode: this.empObj.empCode,
                                flag: this.flag,
                            }
                        }
                    })
                } else {
                    this.$router.push({
                        name: "empDetails",
                        params: {
                            empParams: {
                                empCode: this.empObj.empCode
                            }
                        }
                    })
                }
            },//goback
            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            //提交
            submitForm(formName){
                let vm = {};
                vm = this;
                let obj = {};
                obj.entity = vm.empObj;

                let param;
                param = vm.$route.params.paramsObj;

                if (param) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            let options = {
                                method: "POST",
                                data: obj,
                                url: "employee/update"
                            };
                            this.$ajax(
                                options
                            ).then(function (response) {
                                if (!response.isException) {
                                    vm.$message({
                                        showClose: true,
                                        message: '人员修改成功',
                                        type: 'success'
                                    });

                                }
                            }).catch(function (error) {
                                vm.$message({
                                    showClose: true,
                                    message: '页面:房源跟进失败!',
                                    type: 'error'
                                });
                            });
                        } else {
                            this.$message({
                                showClose: true,
                                message: '数据填写不完整',
                                type: 'warning'
                            });
                        }
                    });
                }
            },
            //检查手机号是否存在
            checkPhone(){
                let obj = {
                    entity: {
                        empPhone: this.empObj.empPhone
                    }
                }
                let options = {
                    method: "POST",
                    data: obj,
                    url: "employee/details"
                };

                this.$ajax(
                    options
                ).then(response => {
                    if (!response.data.isException) {
                        let empCode = response.data.result.empCode;

                        if (empCode != this.empObj.empCode) {
                            this.checkPhoneNum = response.data.result.empPhone;
                        }
                    }
                })
            },
        }//methods
    }
</script>

<style scoped>
    /*根元素  div*/
    .root {
        min-width: 1000px;
        height: 100%;
        background: rgb(242, 242, 242);
        margin-top: -20px;
    }

    /******************************************************************/
    /*  title  公共div */
    .common-title-div {
        background-color: white;
        margin-top: 10px;
        text-align: left;
        height: 50px;
        width: 100%;
        line-height: 50px;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*  title  公共文字  */
    .common-title-font {
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        margin-left: 20px;
        font-weight: bold;
    }

    .common-el-row-style {
        margin-top: 15px;
    }

    /******************************************************************/
    /*返回按钮  根div*/
    .back-button-root-div {
        background-color: white;
        width: 100%;
        height: 50px;
        position: fixed;
        z-index: 200;
        border-bottom: 1px solid rgb(242, 242, 242);
    }

    /*返回按钮*/
    .back-button {
        float: left;
        margin-top: 10px;
        margin-left: 20px;
    }

    /*人员详情  文字*/
    .back-button-title {
        line-height: 50px;
        margin-left: 20px;
        font-size: 17px;
        color: rgba(255, 153, 0, 1);
        float: left;
        margin-top: -1px;
    }

    .save-button-div {
        margin-right: 250px;
    }

    /*保存按钮*/
    .save-button {
        float: right;
        margin-top: 10px;
        margin-right: 20PX;
    }

    /******************************************************************/
    /*基本信息  根div*/
    .emp-info-root-div {
        background-color: white;
        width: 100%;
    }

    /*照片*/
    .photo-div {
        width: 220px;
        /*height: 100%;*/
        float: left;
        border: solid 1px;
    }

    .img-div1 {
        float: left;
        height: 200px;
        width: 200px;
        margin-top: 30px;
        margin-left: 20px;
    }

    /*判断是是否上传了头像,如果没有加边框,反之不加*/
    .imgDiv2 {
        border: 1px solid rgb(242, 242, 242);
        margin-left: 10px;
    }

    /*圆形头像样式*/
    .img1 {
        width: 100%;
        height: 100%;
        margin-top: 0px;

        /*注意順序*/
        moz-border-radius: 100%;
        /*firefox*/
        webkit-border-radius: 100%;
        /*Safari, Chrome*/
        border-radius: 100%;
    }

    /*基本信息  div*/
    .emp-info-div {
        float: left;
        width: 77%;
        text-align: left;
        /*border: solid 1px red;*/
        margin-left: 10px;
    }

    /*各项输入框div*/
    .grid-content {
        height: 40px;
        margin-bottom: 20px;
        /*border: solid 1px;*/
    }

    /******************************************************************/
    /*紧急联系人  div*/
    .contact-root-div {
        background-color: white;
        height: 75px;
        text-align: left;
    }

    /******************************************************************/
    /*劳务信息  div*/
    .labor-root-div {
        background-color: white;
        height: 140px;
        text-align: left;
    }

    /*是否生成User*/
    .has-user {
        background-color: white;
        height: 50px;
        margin-top: 10px;
        text-align: left;
    }


</style>
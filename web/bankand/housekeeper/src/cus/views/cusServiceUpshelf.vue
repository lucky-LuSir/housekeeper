<template>
    <el-dialog :style="{height: fullHeight +140 +'px'}" style="margin-top: 10px;margin-bottom: 20px;"
               width="40%"
               :title="title"
               :before-close="closeDialog"
               :visible.sync="visible"
               append-to-body
               :close-on-click-modal="false">
        <!--客户已经录入了， 可以直接到客户详情接口-->

        <span slot="title">
                <span class="word_title">客服指定上架</span>
           </span>
        <div :style="{height: fullHeight -680 +'px'}" class="cus-div">
            <el-form :model="cusForm" ref="cusForm" :rules="rules" label-width="110px" size="mini" label-position="top">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <el-form-item prop="noticeDepts" style="margin-left: 30px"
                                  label="选择集中获客指定推送部门" labelWidth="70px">
                        <el-select @visible-change="loadDeptList()"
                                   style="width: 300px"
                                   multiple
                                   v-model="cusForm.noticeDepts"
                                   clearable
                                   filterable placeholder="请选择">
                            <el-option-group
                                    v-for="deptList in deptEntityList"
                                    :key="deptList.label"
                                    :label="deptList.label">
                                <el-option
                                        @click.native="recordDeptHistory(item)"
                                        v-for="item in deptList.deptEntity"
                                        :key="item.deptCode"
                                        :label="item.deptName"
                                        :value="item.deptCode">
                                    <span style="float: left">{{ item.deptName }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode
                                        }}</span>
                                </el-option>
                            </el-option-group>

                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <el-form-item prop="noticeUsers" style="margin-left: 30px"
                                  label="选择集中获客指定推送人" labelWidth="70px">
                        <el-select @visible-change="loadUserList()"
                                   style="width: 300px"
                                   multiple
                                   clearable
                                   v-model="cusForm.noticeUsers"
                                   filterable placeholder="请选择">
                            <el-option
                                    v-for="item in userEntity"
                                    :key="item.userCode"
                                    :label="item.userName"
                                    :value="item.userCode">
                                <span style="float: left">{{ item.userName }}</span>
                                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.ownerDeptName
                                        }}</span>
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-form>

        </div>
        <div style="text-align: center;height: 70px;">
            <div class="next_step_total">
                <div class="next_step_right">
                    <el-button class="next_step_btn" @click="submitForm('cusForm')"
                               type="primary" plain round size="small">执行上架
                    </el-button>
                    <el-button class="next_step_btn" @click="closeDialog"
                               type="primary" plain round size="small">返回
                    </el-button>
                </div>
            </div>
        </div>
    </el-dialog>

</template>


<script>

    import file from "../../common/components/file.vue";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    import ElRadioButton from "../../../node_modules/element-ui/packages/radio/src/radio-button";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";

    export default {
        components: {
            ElFormItem,
            ElRadioButton,
            ElCol,
            ElRow,
            file
        },

        created: function () {
            var vm = this;
        },


        mounted() {
        },

        data() {
            return {
                deptEntityList: [],
                historyDept: {
                    label: '历史选择记录',
                    deptEntity: []
                },
                userEntity: [],
                selectDept: {
                    label: '部门名称',
                    deptEntity: [],
                },
                type: '',//面板类型 1为修改 2为新增
                ptCodeVisible: false,
                props: {
                    value: 'areaCode',
                    label: 'name',
                    children: 'cities',
                    multiple: true,
                    checkStrictly: true
                },
                departmentOptions: [],
                dialogImageUrl: '',
                matchHosOpenOrClose: false,
                dialogVisible: false,
                cusDetailsOpenOrClose: false,
                partTimeOpenOrClose: false,
                finishNeedAreaFlag: false,
                communityList: [],
                itStreetArrCpy: [],
                itAreaArrCpy: [],
                itCityArrBase: [],
                title: "",
                fullHeight: document.documentElement.clientHeight,
                visible: false,
                cusForm: {
                    id: null,
                    cusCode: null,
                    industry: null,
                    products: null,
                    cusFrom: null,
                    ptCode: null,
                    ptName: null,
                    divideType: null,
                    divideRatio: null,
                    divideCash: null,
                    cusPhone: null,
                    cusName: null,
                    cusSex: null,
                    cusType: null,
                    houseType: null,
                    needAcreage: null,
                    needVoltage: null,
                    needPrice: null,
                    priceUnit: '元/㎡/天',
                    layerNum: null,
                    layerNumName: null,
                    layerHeight: null,
                    enterTime: null,
                    expectTerm: null,
                    fireLevel: null,
                    description: null,
                    customerAreaEntityList: [],
                    fileRelationEntityList: [],
                    customerContactsEntityList: [],
                    customerAreaEntityList: [],
                    noticeDepts: [],
                    noticeUsers: []
                },
            }

        },

        computed: {
            fullName: function () {
                return this.firstName + this.lastName;
            },
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
            },
        },

        updated() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
        },

        methods: {
            recordDeptHistory(item) {
                var vm = this;
                if (item != null) {
                    var history = localStorage.getItem("historyDept");
                    if (history == null) {
                        vm.historyDept.deptEntity.push(item);
                        localStorage.setItem("historyDept", JSON.stringify(vm.historyDept), 0);
                    } else {
                        var hisDept = JSON.parse(history);
                        //重复的数据不加入缓存
                        let flag = hisDept.deptEntity.some(d => d.id == item.id);
                        if (flag == false) {
                            if (hisDept.deptEntity.length >= 6) {
                                hisDept.deptEntity.shift();
                            }
                            hisDept.deptEntity.push(item);
                            localStorage.setItem("historyDept", JSON.stringify(hisDept), 0);
                        }
                    }
                }
            },
            //加载部门数据
            loadUserList() {
                var vm = this;
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "user/queryAllUser",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.userEntity = response.data.result;
                }).catch(function (error) {

                });
            },
            //加载部门数据
            loadDeptList() {
                var vm = this;
                var sendObj = {};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "dept/findDeptName",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.deptEntityList = [];
                    let history = localStorage.getItem("historyDept");
                    if (history != null) {
                        console.log(JSON.parse(history))
                        vm.historyDept.deptEntity = JSON.parse(history);
                        console.log(vm.deptEntityList)
                        vm.deptEntityList.push(vm.historyDept.deptEntity);
                    }
                    vm.selectDept.deptEntity = response.data.result;
                    if (vm.selectDept.deptEntity != null) {
                        vm.deptEntityList.push(vm.selectDept);
                    }
                }).catch(function (error) {

                });
            },

            resetForm(formName) {
                var vm = this;
                vm.historyDept.deptEntity = [];
                vm.cusForm.noticeDepts = [];
                vm.cusForm.noticeUsers = [];
                vm.$refs[formName].resetFields();
            },
            //关闭客户详情面板
            closeDialog() {
                var vm = this;
                vm.visible = false;
            },
            submitForm(formName) {
                var vm = this;
                var sendObj = {};
                sendObj.entity = vm.cusForm;
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customer/cusServiceUpshelf",
                };
                this.$ajax(
                    option
                ).then(response => {
                    vm.$notify.success(response.data.message);
                    vm.resetForm(formName);
                    vm.$emit('changeFocusCus');
                }).catch(error => {
                    vm.$notify({
                        type: 'info',
                        message: '上架客户失败'
                    });
                });
            },
        }

    }

</script>


<style scoped>

    .img-div1 {
        float: left;
        border: 1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }

    .cus-div {
        overflow-y: auto;
        overflow-x: hidden;
        border-top: 1px solid gainsboro;
    }

    .word_title {
        margin: 0px 0px 0px 10px;
        padding: 0px 0px 0px 0px;
        height: auto;
        width: auto;
        font-size: 20px;
        font-weight: 600;
    }

    .btn_title_1h {
        margin: 0px 0px 0px 280px;
        height: auto;
        width: auto;
    }

    .btn_title_3h {
        margin: 0px 0px 0px 50px;
        height: auto;
        width: auto;
    }

    .next_step_total {
        margin: 0px 0px 0px 0px;
        border-top: 2px solid gainsboro;
        padding: 0px 0px 0px 0px;
        height: 55px;
        width: 100%;
    }

    .next_step_right {
        margin: 10px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        height: 55px;
        width: 555px;
        float: right;
    }

    .next_step_btn {
        margin: 10px 20px 10px 0px;
        float: right;
        height: 30px;
        width: 120px;
    }

    .file_button {
        margin-top: 80px;
        margin-left: 50px;
    }

</style>
<style>

    .ant-collapse > .ant-collapse-item > {
        height: 38px;
        line-height: 38px;
        padding-left: 32px;
        color: rgba(0, 0, 0, 0.85);
        cursor: pointer;
        position: relative;
        transition: all .3s;
        border-bottom: 2px solid #409EFF;
    }

</style>

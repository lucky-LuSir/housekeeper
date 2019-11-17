<template>
    <el-dialog  :close-on-click-modal="false" title="房源跟进" align="center" :before-close="closeDialog" top="100px" width="30%"
               :visible.sync="visible"
               :modal-append-to-body='false'>
        <el-form align="left" :rules="rules" ref="form" :model="createHouseFollowup">
            <div v-if="houseUpdate">
                <div style="margin-bottom: 10px;font-weight: bold;">
                    此次跟进是否要对以下信息进行修改？
                </div>
                <el-form-item label="层总面积" prop="layerArea">
                    <el-input size="mini" class="input-width"
                              :placeholder="createHouseFollowups.layerArea"
                              v-model="createHouseFollowup.layerArea"/>
                    m²
                </el-form-item>
                <el-form-item label="可租面积" prop="area">
                    <el-input size="mini" class="input-width"
                              :placeholder="createHouseFollowups.area"
                              v-model="createHouseFollowup.area"/>
                </el-form-item>
                <el-form-item label="房源价格" prop="price">
                    <el-input size="mini" style="width: 140px"
                              :placeholder="createHouseFollowups.price"
                              v-model="createHouseFollowup.price"/>

                    <el-select style="width: 100px" size="mini" v-model="createHouseFollowups.unit"
                               filterable placeholder="请选择">
                        <el-option label="元/㎡/天" value="元/㎡/天"/>
                        <el-option label="元/㎡/月" value="元/㎡/月"/>
                        <el-option label="元/㎡/年" value="元/㎡/年"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="入住时间" prop="enterTime">
                    <el-date-picker
                            size="mini"
                            style="width: 240px"
                            v-model="createHouseFollowups.enterTime"
                            type="date"
                            placeholder="入住时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item
                        label-width="50px"
                        v-for="(contact, i) in createHouseFollowup.houseownerContacts"
                        :key=contact.id>
                    <el-row :gutter="10">
                        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                            <el-input size="mini" v-model="contact.contactPhone"
                                      placeholder="电话号码"/>
                        </el-col>
                        <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                            <el-input size="mini" v-model="contact.contactName" placeholder="称呼"/>
                        </el-col>
                        <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                            <el-radio-group size="mini" v-model="contact.contactSex">
                                <el-radio label="Sir">先生</el-radio>
                                <el-radio label="Lady">女士</el-radio>
                            </el-radio-group>
                        </el-col>
                    </el-row>
                </el-form-item>
            </div>
            <div align="left" v-if="houseUpdate==false">
                <p>房源标题: {{createHouseFollowups.houseName}}</p>
                <el-form-item label="跟进结果(必填)">
                    <el-input type="textarea" v-model="followupContext"></el-input>
                </el-form-item>
            </div>
        </el-form>
        <div v-if="houseUpdate">
            <p style="text-align: right;font-size: 12px;text-decoration: underline">
                <a @click="ownerCommunication">如果无需更新,点我可录入与业主沟通结果>></a>
            </p>
        </div>
        <div align="right" slot="footer">
            <el-button v-if="houseUpdate" type="primary" plain @click="addHouseownerContacts"
                       size="small">
                更多联系人(可添加多个)
            </el-button>
            <el-button v-if="houseUpdate" type="primary" plain @click="clearHouseownerContacts"
                       size="small">
                清空联系人
            </el-button>
            <el-button v-if="houseUpdate==false" size="small" plain type="success" @click="back">
                返回跟进修改房源信息
            </el-button>
            <el-button size="small" plain type="success" @click="submitForm('form')">提交
            </el-button>
        </div>
    </el-dialog>
</template>

<script>
    import ElDialog from "../../../node_modules/element-ui/packages/dialog/src/component";
    import ElForm from "../../../node_modules/element-ui/packages/form/src/form";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";
    import ElInput from "../../../node_modules/element-ui/packages/input/src/input";
    import ElRow from "element-ui/packages/row/src/row";
    import ElCol from "element-ui/packages/col/src/col";
    export default {
        components: {
            ElCol,
            ElRow,
            ElInput,
            ElFormItem,
            ElForm,
            ElDialog

        },
        props: ["createHouseFollowups"],
        data() {
            return {
                followupContext: null,
                houseUpdate: true,
                visible: false,
                houseFollowupData: {},

                createHouseFollowup: {
                    houseownerContacts: []
                },

                rules: {
                    layerArea: [
                        {required: true, message: '层总面积不能为空', trigger: 'blur'}
                    ],
                    area: [
                        {required: true, message: '可租面积不能为空', trigger: 'blur'}
                    ],
                    price: [
                        {required: true, message: '租金不能为空', trigger: 'blur'}
                    ],
                    入住时间: [
                        {required: true, message: '入住时间不能为空', trigger: 'blur'}
                    ],
                }
            }
        },
        created(){

        },

        methods: {

            addHouseownerContacts(){
                this.createHouseFollowup.houseownerContacts.push({
                    contactPhone: null,
                    contactName: null,
                    contactSex: null,
                });
            },

            clearHouseownerContacts(){
                this.createHouseFollowup.houseownerContacts = [];
            },

            clearData(vm) {
                vm.houseUpdate = true;
                vm.followupContext = null
                vm.createHouseFollowup = {
                    houseownerContacts: []
                };
            },

            closeDialog(done){
                var vm = this;
                vm.clearData(vm);
                done()
            },
            submitForm(formName){
                var vm = this;
                var obj = {
                    entity: vm.createHouseFollowup
                }
                    vm.createHouseFollowup.unit = vm.createHouseFollowups.unit;
                    vm.createHouseFollowup.id = vm.createHouseFollowups.id;
                    vm.createHouseFollowup.houseCode = vm.createHouseFollowups.houseCode;
                    vm.createHouseFollowup.enterTime = vm.createHouseFollowups.enterTime;
                    vm.createHouseFollowup.followupContext = vm.followupContext;
                    if (vm.houseUpdate == true) {
                        this.$refs[formName].validate((valid) => {
                            if (valid) {
                                var options = {
                                    method: "POST",
                                    data: obj,
                                    url: "house/updateCut"
                                };
                                this.$ajax(
                                    options
                                ).then(response => {
                                    vm.$notify({
                                        message: '操作成功',
                                        type: 'success'
                                    });

                                    vm.clearData(vm);
                                    vm.visible = false;
                                    vm.$emit('changeSuccess');
                                }).catch(error => {
                                    vm.$notify({
                                        type: 'info',
                                        message: '新增房源跟进失败'
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
                else{

                    var options = {
                        method: "POST",
                        data: obj,
                        url: "houseFollowup/create"
                    };

                    this.$ajax(
                        options
                    ).then(response => {
                        vm.$notify({
                            message: '操作成功',
                            type: 'success'
                        });

                        vm.clearData(vm);
                        vm.visible = false;
                        vm.$emit('changeSuccess');

                    }).catch(error => {
                        vm.$notify({
                            type: 'info',
                            message: '新增房源跟进失败'
                        });
                    });
                }
            },

            ownerCommunication(){
                var vm = this;
                vm.houseUpdate = false;
                vm.createHouseFollowup = {
                    houseownerContacts: []
                };
            },
            back(){
                var vm = this;
                vm.houseUpdate = true;
            }
        }
    }
</script>

<style scoped>
    .input-width {
        width: 240px;
    }
</style>
<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible"  center width="40%" append-to-body>
        <div class="pre-scrollable">
            <el-form :model="houseOwnerFollowupQuery" ref="houseOwnerFollowupQuerys"
                     labelWidth="100px">
                <el-row style="margin-bottom: 20px">
                    <span style="font-weight: 900;font-size: 15px;">按条件查询</span>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="业主姓名" prop="ownerName">
                            <el-input style="width:180px;"
                                      v-model="houseOwnerFollowupQuery.ownerName"
                                      placeholder="业主姓名"/>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="业主手机" prop="ownerPhone">
                            <el-input style="width:180px;"
                                      v-model="houseOwnerFollowupQuery.ownerPhone"
                                      placeholder="业主手机"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="服务专员" prop="createName">
                            <el-input style="width:180px;"
                                      v-model="houseOwnerFollowupQuery.createName"
                                      placeholder="服务专员"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="专员手机" prop="empPhone">
                            <el-input style="width:180px;"
                                      v-model="houseOwnerFollowupQuery.empPhone"
                                      placeholder="专员手机"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="选择部门" prop="createDeptCode">
                            <el-select style="width: 180px" @visible-change="loadDeptList()"
                                       v-model="houseOwnerFollowupQuery.createDeptCode"
                                       filterable placeholder="请选择">
                                <el-option
                                        v-for="item in deptEntity"
                                        :key="item.deptCode"
                                        :label="item.deptName"
                                        :value="item.deptCode">
                                    <span style="float: left">{{ item.deptName }}</span>
                                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptCode
                                        }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="拜访类型" prop="followupType">
                            <el-select style="width: 180px" @visible-change="loadDeptList()"
                                       v-model="houseOwnerFollowupQuery.followupType"
                                       filterable placeholder="请选择">
                                <el-option label="电话拜访" value="1"/>
                                <el-option label="上门拜访" value="2"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <el-form-item label="创建开始时间" prop="createTimeStart">
                        <el-date-picker
                                style="width:180px;"
                                v-model="houseOwnerFollowupQuery.createTimeStart"
                                type="date"
                                placeholder="创建开始时间">
                        </el-date-picker>
                    </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="创建结束时间" prop="createTimeEnd">
                            <el-date-picker
                                    style="width:180px;"
                                    v-model="houseOwnerFollowupQuery.createTimeEnd"
                                    type="date"
                                    placeholder="创建结束时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <div style="text-align: center">
            <el-button size="small" plain type="primary"
                       @click="resetForm('houseOwnerFollowupQuerys')">重置条件
            </el-button>
            <el-button size="small" plain type="success" @click="getHosOwnerCondition()">按条件查询
            </el-button>
        </div>

    </el-dialog>
</template>

<script>
    import ElCol from "element-ui/packages/col/src/col";
    import ElRow from "element-ui/packages/row/src/row";
    import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";
    export default {
        components: {
            ElFormItem,
            ElRow,
            ElCol
        },
        data() {
            return {
                dialogFormVisible: false,
                deptEntity: [],
                houseOwnerFollowupQuery: {
                    ownerName:'',
                    createName:'',
                    createDeptCode: '',
                    empPhone:'',
                    ownerPhone: '',
                    followupType: '',
                    createTimeStart: '',
                    createTimeEnd: ''
                },
            }
        },
        created() {

        },
        mounted(){

        },
        methods: {
            //加载部门数据
            loadDeptList(){
                var vm = this;
                var obj = {};
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
                    vm.deptEntity = response.data.result;
                }).catch(function (error) {

                });
            },
            getHosOwnerCondition(){
                this.$emit('changeHosOwner', this.houseOwnerFollowupQuery)
            },
            //重置按条件查询条件
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            //自定义未跟进天数 与枚举
            notFollowupDay(val){
                if (val == "6") {
                    this.notFollowupDayDis = true;
                } else {
                    this.notFollowupDayDis = false;
                    this.searchCusFollowupCondition.houseOwnerFollowupQuery.customerNotFollowupDay = '';
                }
            },
            //改变省
            changeProvCpy(){
                //清空code
                this.searchCusFollowupCondition.houseOwnerFollowupQuery.city = null;
                this.searchCusFollowupCondition.houseOwnerFollowupQuery.region = null;
                this.searchCusFollowupCondition.houseOwnerFollowupQuery.street = null;
            },
            //改变市
            changeCityCpy(){
                this.searchCusFollowupCondition.houseOwnerFollowupQuery.region = null;
                this.searchCusFollowupCondition.houseOwnerFollowupQuery.street = null;
            },
            //改变区域
            changeRegionCpy(){
                this.searchCusFollowupCondition.houseOwnerFollowupQuery.street = null;
            },

            /*省份查询*/
            handNeedProvinceCpy(val){
                var vm = this;
                var obj = {};
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "cpyArea/queryNoPage",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.itProvinceArrCpy = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                        type: 'error'
                    });
                });
            },
            /*市区查询*/
            getCitieListCpy(aVal) {
                if (aVal != null && aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }

            },
            /*区域查询*/
            getAreaListCpy(bVal) {
                if (bVal != null && bVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = bVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itAreaArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
            /*街道查询*/
            getStreetListCpy(aVal){
                if (aVal != null && aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itStreetArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }


            },
            /*社区查询*/
            getCommunityListCpy(aVal){
                if (aVal != null && aVal != "") {
                    var vm = this;
                    var obj = {};
                    obj.parentCode = aVal;
                    var sendObj = {};
                    sendObj.entity = obj;
                    var options = {
                        method: 'POST',
                        data: sendObj,
                        url: "cpyArea/queryNoPage",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.itCommunityArrCpy = response.data.result;
                    }).catch(function (error) {
                        vm.$message({
                            showClose: true,
                            message: '页面:查询省份信息失败!cpyArea/queryNoPage',
                            type: 'error'
                        });
                    });
                }
            },
        }
    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        font-size: 14px;
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
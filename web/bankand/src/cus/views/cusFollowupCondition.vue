<template>
    <el-dialog  :close-on-click-modal="false" :visible.sync="dialogFormVisible" style="height: 105%" center
               width="30%" append-to-body>
        <div class="pre-scrollable">
            <el-form :model="customerQuery" ref="customerQuerys"
                     labelWidth="100px">
                <el-row>
                    <span style="font-weight: 900;font-size: 15px;">按条件查询</span>
                </el-row>
                <el-row style="margin-top: 30px">
                    <el-form-item label="选择部门" prop="createDeptCode">
                        <el-select style="width: 220px" @visible-change="loadDeptList()"
                                   v-model="customerQuery.createDeptCode"
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
                </el-row>


                <el-row>
                    <el-form-item label="服务专员" prop="empName">
                        <el-input style="width:220px;"
                                  v-model="customerQuery.empName"
                                  placeholder="经纪人姓名"></el-input>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-form-item label="跟进类型" prop="followupType">
                        <el-select style="width: 220px" @visible-change="loadDeptList()"
                                   v-model="customerQuery.followupType"
                                   filterable placeholder="请选择">
                            <el-option label="实地带看" value="1"/>
                            <el-option label="电话拜访" value="2"/>
                            <el-option label="上门拜访" value="3"/>
                        </el-select>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-form-item label="创建开始时间" prop="createTimeStart">
                        <el-date-picker
                                v-model="customerQuery.createTimeStart"
                                type="date"
                                placeholder="创建开始时间">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="创建结束时间" prop="createTimeEnd">
                        <el-date-picker
                                v-model="customerQuery.createTimeEnd"
                                type="date"
                                placeholder="创建结束时间">
                        </el-date-picker>
                    </el-form-item>
                </el-row>
            </el-form>
        </div>
        <div style="text-align: center">
            <el-button size="small" plain type="primary"
                       @click="resetForm('customerQuerys')">重置条件
            </el-button>
            <el-button size="small" plain type="success" @click="getCusByCondition()">按条件查询
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
                customerQuery: {
                    createDeptCode: '',
                    empName: '',//经纪人name
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
                    if (!response.data.isException) {

                    }
                    vm.deptEntity = response.data.result;
                }).catch(function (error) {

                });
            },
            getCusByCondition(){
                this.$emit('changeCus', this.customerQuery)
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
                    this.searchCusFollowupCondition.customerQuery.customerNotFollowupDay = '';
                }
            },
            //改变省
            changeProvCpy(){
                //清空code
                this.searchCusFollowupCondition.customerQuery.city = null;
                this.searchCusFollowupCondition.customerQuery.region = null;
                this.searchCusFollowupCondition.customerQuery.street = null;
            },
            //改变市
            changeCityCpy(){
                this.searchCusFollowupCondition.customerQuery.region = null;
                this.searchCusFollowupCondition.customerQuery.street = null;
            },
            //改变区域
            changeRegionCpy(){
                this.searchCusFollowupCondition.customerQuery.street = null;
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
        max-height: 500px;
        font-size: 12px;
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
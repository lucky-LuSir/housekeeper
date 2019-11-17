<template class="condition">
    <div class="pre-scrollable">
        <el-form :model="searchBaseOperationCondition">
            <el-row>
                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                    <el-form-item label="操作人" prop="optCode">
                        <el-select id="optNames"
                                   filterable
                                   v-model="searchBaseOperationCondition.baseOperationQuery.optCode"
                                   @visible-change="queryAllUser" @change="changeUser"
                                   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in optNames"
                                    :key="item.userCode"
                                    :value="item.userCode"
                                    :label="item.userName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                    <el-form-item label="操作接口" prop="url">
                        <el-select id="url"
                                   filterable
                                   v-model="searchBaseOperationCondition.baseOperationQuery.url"
                                   @focus="getUrls()"
                                   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in urls"
                                    :key="item"
                                    :value="item"
                                    :label="item">
                            </el-option>
                        </el-select>
                    </el-form-item>

                </el-col>
                <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
                    <el-form-item  label="操作客户端类型" prop="clientType">
                        <el-select id="clientType"
                                   filterable
                                   v-model="searchBaseOperationCondition.baseOperationQuery.clientType"
                                   @focus="getClientType()"
                                   placeholder="请选择">
                            <el-option
                                    v-for="(item, indexkey) in clientType"
                                    :key="item"
                                    :value="item"
                                    :label="item">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div style="font-weight: bold;margin-bottom: 10px">最早操作时间</div>
                    <el-date-picker
                            v-model="searchBaseOperationCondition.baseOperationQuery.createTimeStart"
                            type="date"
                            placeholder="最早操作时间">
                    </el-date-picker>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <div style="font-weight: bold;margin-bottom: 10px">最晚操作时间</div>
                    <el-date-picker
                            v-model="searchBaseOperationCondition.baseOperationQuery.createTimeEnd"
                            type="date"
                            placeholder="最晚操作时间">
                    </el-date-picker>
                </el-col>
            </el-row>
            <el-row style="margin-top: 20px">
                <el-button size="middle" plain type="primary" @click="emptyConditions()">重置条件
                </el-button>
                <el-button size="middle" plain type="success"
                           @click="getBaseOperationByCondition()">
                    按条件查询
                </el-button>
            </el-row>
        </el-form>

    </div>
</template>

<script>
    import ElCol from "element-ui/packages/col/src/col";
    import ElRow from "element-ui/packages/row/src/row";
    export default {
        components: {
            ElRow,
            ElCol
        },
        data() {
            return {
                //按条件查询面板 里的 自定义未跟进天数
                notFollowupDayDis: false,
                urls: [],
                clientType:[],
                searchBaseOperationCondition: {
                    baseOperationQuery: {
                        url: "",
                        optCode: "",
                        optName: "",
                        createTimeStart: "",
                        createTimeEnd: "",
                        clientType:"",
                    }
                },

                //操作人选址
                optNames: [],
                //操作接口选择
                urls: [],
            }
        },
        created() {

        },
        mounted(){

        },
        methods: {
            getBaseOperationByCondition(){
                this.$emit('queryOperation', this.searchBaseOperationCondition)
            },
            //重置按条件查询条件
            emptyConditions() {
                this.searchBaseOperationCondition.baseOperationQuery.optName = null;
                this.searchBaseOperationCondition.baseOperationQuery.optCode = null;
                this.searchBaseOperationCondition.baseOperationQuery.url = null;
                this.searchBaseOperationCondition.baseOperationQuery.clientType = null;
                this.searchBaseOperationCondition.baseOperationQuery.createTimeStart = null;
                this.searchBaseOperationCondition.baseOperationQuery.createTimeEnd = null;
            },
            /*省份查询*/
            queryAllUser(){
                var vm = this;
                var obj = {};
                obj.parentCode = 0;
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "user/queryAllUser",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.optNames = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询所有用户信息集合失败 ,user/queryAllUser',
                        type: 'error'
                    });
                });
            },

            //改变用户
            changeUser(){
                this.searchBaseOperationCondition.baseOperationQuery.url = null;
            },
            getUrls() {
                var vm = this;
                var obj = {};
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "baseOperation/findUrlsByOptCode",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.urls = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询接口操作集合失败',
                        type: 'error'
                    });
                });

            },
            getClientType() {
                var vm = this;
                var obj = {};
                var sendObj = {};
                sendObj.entity = obj;
                var options = {
                    method: 'POST',
                    data: sendObj,
                    url: "baseOperation/findClientType",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.clientType = response.data.result;
                }).catch(function (error) {
                    vm.$message({
                        showClose: true,
                        message: '页面:查询接口操作集合失败',
                        type: 'error'
                    });
                });

            },

        }
    }
</script>

<style scoped>
    .pre-scrollable {
        height: auto;
        max-height: 1212px;
        overflow-x: hidden;
        overflow-y: hidden;
    }

    .condition {
    }
</style>

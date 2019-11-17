<template>
    <div>
        <div class="pre-scrollable">
            <el-form :model="baseOperationEntity">
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作id" prop="id">
                            <el-input v-model="baseOperationEntity.id"
                                      placeholder="操作id" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作人名" prop="optName">
                            <el-input v-model="baseOperationEntity.optName"
                                      placeholder="操作人名" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <div style="font-weight: bold;margin-bottom: 18px">操作时间</div>
                        <el-form-item prop="createTime">
                            <el-date-picker
                                    v-model="baseOperationEntity.createTime"
                                    type="datetime"
                                    placeholder="操作时间">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="请求路径" prop="url">
                            <el-input v-model="baseOperationEntity.url"
                                      placeholder="请求路径" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作ip" prop="ip">
                            <el-input v-model="baseOperationEntity.ip"
                                      placeholder="操作ip" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="客户端类型" prop="clientType">
                            <el-input v-model="baseOperationEntity.clientType"
                                      placeholder="客户端类型" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18" :offset="2">
                        <el-form-item label="操作参数" prop="requestParam">
                            <el-input type="textarea" v-model="baseOperationEntity.requestParam"
                                      readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </div>
</template>

<script>

    export default {
        components: {},
        props: ["id"],
        data() {
            return {
                baseOperationEntity: {
                    id: "",
                    url: "",
                    optName: "",
                    optCode: "",
                    createTime: "",
                    ip: "",
                    clientType: "",
                    requestParam: "",
                }
            }
        },
        created: function () {

        },
        mounted: function () {

        },
        methods: {

            //获取操作日志详情
            getBaseOperationDetails(){

                var vm = this;
                var sendObj = {}
                sendObj.entity = {"id": vm.id}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "baseOperation/detail",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.baseOperationEntity = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!baseOperation/detail');
                })
            }
        }

    }
</script>

<style scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 530px;
    }
</style>
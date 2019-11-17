<template>
    <div>
        <div class="pre-scrollable">
            <el-form :model="operationEntity">
                <el-row :gutter="20" >
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作id" prop="id">
                            <el-input v-model="operationEntity.id"
                                      placeholder="操作id" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作编码" prop="optCode">
                            <el-input v-model="operationEntity.optCode"
                                      placeholder="操作编码" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作类型" prop="optTypeName">
                            <el-input v-model="operationEntity.optTypeName"
                                      placeholder="操作类型" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作人名" prop="createName">
                            <el-input v-model="operationEntity.createName"
                                      placeholder="操作人名" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="业务编码" prop="bizCode">
                            <el-input v-model="operationEntity.bizCode"
                                      placeholder="业务编码" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="请求路径" prop="url">
                            <el-input v-model="operationEntity.url"
                                      placeholder="请求路径" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作人所属部门" prop="createDeptName">
                            <el-input v-model="operationEntity.createDeptName"
                                      placeholder="操作人所属部门" readonly></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :offset="2">
                        <el-form-item label="操作时间" prop="createTime">
                            <el-input v-model="operationEntity.createTime"
                                      placeholder="操作时间" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18" :offset="2">
                        <el-form-item label="操作内容" prop="content">
                            <el-input type="textarea" v-model="operationEntity.content"
                                      placeholder="操作内容" readonly></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </div>
</template>

<script>

    export default {
        components: {

        },
        props:["optCodes"],
        data() {
            return {
                operationEntity: {
                    id:"",
                    optCode:"",
                    optTypeName:"",
                    bizCode:"",
                    url:"",
                    content:"",
                    createName:"",
                    createDeptName:"",
                    createTime:""
                }
            }
        },
        created: function () {

        },
        mounted: function () {

        },
        methods: {

            //获取操作日志详情
            getOperationDetails(){

                var vm = this;
                var sendObj = {}
                sendObj.entity = {"optCode":vm.optCodes}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "operation/detail",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.operationEntity = response.data.result;
                }).catch(function (error) {
                    vm.$message.error('页面:获取数据失败!operation/detail');
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
        width: 1000px;
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
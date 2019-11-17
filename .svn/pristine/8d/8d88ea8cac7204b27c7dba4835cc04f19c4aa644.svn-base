<template class="condition">
    <div class="pre-scrollable">
        <el-form :model="searchOperationCondition">
            <el-row :gutter="10">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <span style="font-weight: 900;font-size: 15px;">按条件查询</span>
                </el-col>
            </el-row>
            <el-form-item label="关键字查">
                <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10" :offset="0">
                    <el-input v-model="searchOperationCondition.operationQuery.keyword"
                              placeholder="业务编码、操作编码、操作人、操作人部门"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="操作类型">
                <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20" :offset="0">
                    <el-radio-group v-model="searchOperationCondition.operationQuery.optType">
                        <el-radio-button>全部</el-radio-button>
                        <el-radio-button label="seeCusDetail">查看客户详情</el-radio-button>
                        <el-radio-button label="seeHosDetail">查看房源详情</el-radio-button>
                        <el-radio-button label="seeHouseOwnerDetail">查看业主详情</el-radio-button>
                        <el-radio-button label="callCusPhone">拨打客户电话</el-radio-button>
                        <el-radio-button label="callHouseOwnerPhone">拨打业主电话</el-radio-button>
                    </el-radio-group>
                </el-col>
            </el-form-item>
            <el-row :gutter="20">
                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                    <el-form-item label="业务编码" prop="bizCode">
                        <el-input v-model="searchOperationCondition.operationQuery.bizCode"
                                  placeholder="业务编码"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                    <el-form-item label="操作人名" prop="createName">
                        <el-input v-model="searchOperationCondition.operationQuery.createName"
                                  placeholder="操作人名"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="20">
                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                    <el-form-item label="操作人所属部门" prop="createDeptName">
                        <el-input v-model="searchOperationCondition.operationQuery.createDeptName"
                                  placeholder="操作人所属部门"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :xs="11" :sm="11" :md="11" :lg="11" :xl="11">
                    <el-form-item label="操作内容" prop="content">
                        <el-input v-model="searchOperationCondition.operationQuery.content"
                                  placeholder="操作内容"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <el-date-picker
                            v-model="searchOperationCondition.operationQuery.createTimeStart"
                            type="date"
                            placeholder="最早操作时间">
                    </el-date-picker>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                    <el-date-picker
                            v-model="searchOperationCondition.operationQuery.createTimeEnd"
                            type="date"
                            placeholder="最晚操作时间">
                    </el-date-picker>
                </el-col>
            </el-row>
            <el-row>
                <el-button size="middle" plain type="primary" @click="emptyConditions()">重置条件
                </el-button>
                <el-button size="middle" plain type="success" @click="getOperationByCondition()">
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
                searchOperationCondition: {
                    operationQuery: {
                        keyword: "",
                        optType: "",
                        optCode: "",
                        createName: "",
                        createDeptName: "",
                        createTimeStart: "",
                        createTimeEnd: "",
                        content:""
                    }
                },
            }
        },
        created() {

        },
        mounted(){

        },
        methods: {
            getOperationByCondition(){
                this.$emit('queryOperation', this.searchOperationCondition)
            },
            //重置按条件查询条件
            emptyConditions() {
                this.searchOperationCondition.operationQuery.keyword = null;
                this.searchOperationCondition.operationQuery.optType = null;//查询类型
                this.searchOperationCondition.operationQuery.createName = null;
                this.searchOperationCondition.operationQuery.createDeptName = null;
                this.searchOperationCondition.operationQuery.optCode = null;
                this.searchOperationCondition.operationQuery.bizCode = null;
                this.searchOperationCondition.operationQuery.createTimeStart = null;
                this.searchOperationCondition.operationQuery.createTimeEnd = null;
            },

        }
    }
</script>

<style scoped>
    .pre-scrollable {
        height: auto;
        max-height: 550px;
        width: 1000px;
        overflow-x: hidden;
        overflow-y: hidden;
    }

    .condition {
        widows: 600px;
    }
</style>
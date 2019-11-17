<template>
    <div class="root pre-scrollable"  ref="myRoot">
            <el-form>
                <el-row :gutter="2">
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="订单编号" label-width="80px">
                            <el-input v-model="orderFollowupEntity.orderCode"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-form-item label="跟进时间" label-width="80px">
                            <el-date-picker
                                    size="medium"
                                    v-model="orderFollowupEntity.followupTime"
                                    type="date"
                                    placeholder="选择日期"
                                    class="order_time">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="2">
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <el-form-item label="跟进结果" label-width="80px">
                            <el-input
                                    type="textarea"
                                    :autosize="{ minRows: 2, maxRows: 4}"
                                    placeholder="请输入内容"
                                    v-model="orderFollowupEntity.remark">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div style="text-align:center;line-height: 10px;">
                <el-button @click="searchDialogFormVisible = false">清空条件</el-button>

                <el-button type="primary" @click="createOrderFollowup()">提交</el-button>
            </div>
    </div>

</template>




<script scoped>

    export default{
        components: {

            },
        data(){

            return{
                orderFollowupEntity:{
                    //订单编号
                    orderCode:this.orderCode,
                    //订单跟进时间
                    followupTime:'',
                    //结果
                    remark:''
                },
            }
        },
        props:["orderCode","OrderFollowupCreateDialogFormVisible"],
        created:function (){

        },
        methods:{
            createOrderFollowup(){
                var vm=this;
                var sendObj={};
                sendObj.entity=vm.orderFollowupEntity;
                var option={
                    method:'POST',
                    data:sendObj,
                    url:"orderfollowup/create",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                    vm.OrderFollowupCreateDialogFormVisible=false;
                    vm.$emit("changed",vm.OrderFollowupCreateDialogFormVisible);
                }).catch(function (error) {

                });
            }
        }

    }

</script>



<style  scoped>
    .pre-scrollable {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 190px;
        overflow-y: hidden;
        overflow-x: hidden;
    }
</style>
<template>
    <el-dialog  :close-on-click-modal="false" style="height: 105%" top="20px" :visible.sync="visible" :before-close="closeDialog" width="50%" append-to-body>
        <div>
            <div style="margin-top: 30px;text-align: center">
                <el-radio-group v-model="followupType" size="medium">
                    <el-radio-button label="1" style="padding-right: 100px">带看谈判</el-radio-button>
                    <el-radio-button label="2">电话沟通</el-radio-button>
                </el-radio-group>
            </div>
            <div style="margin-top: 50px;text-align: center">
                <span style="font-size: 14px;color: #c0a16b">库房管家温馨提醒</span>
            </div>
            <div style="margin-top: 15px;text-align: center">
                <span style="font-size: 14px;color: #3c3c3c">
                    上传以上过程中的照片、视频、录音作为法律依据以便于再发生纠纷时维护您的合法权益！
                </span>
            </div>
            <div style="margin-top: 50px;text-align: center">
                <el-button size="small" v-show="followupType == '1'" @click="toTakeLookHos()">选择带看房源</el-button>

                <span v-show="followupType == '2'">
                    我想说：
                    <el-input size="mini" style="width: 300px" v-model="visitPhoneContent" placeholder="请输入内容"></el-input>
                    <el-button size="mini" @click="createFollowupFromVisitPhone()" >提交</el-button>
                </span>


            </div>
            <div style="margin-top: 30px;visibility: hidden">
                <span>此处做隐藏的，不用管</span>
            </div>

            <!--带看房源面板-->
                <cus-choose-take-look-hos v-if="chooseTakeLookHosOpenOrClose" ref="refChooseTakeLookCus"
                                          @changeSuccess="successCreateFollowup()" ></cus-choose-take-look-hos>
        </div>
    </el-dialog>
</template>

<script>
    import cusChooseTakeLookHos from "./cusChooseTakeLookHos"
    export default {
        components: {
           cusChooseTakeLookHos,
        },
        data() {
            return {
                visible: false,
                cusCodeChooseFollowupType: '',//客户code

                followupType: '1',//客户跟进类型
                visitPhoneContent: '',//电话拜访内容

                chooseTakeLookHosOpenOrClose: false,//带看房源打开或者关闭
            }
        },
        created(){

        },
        methods:{
            successCreateFollowupFromVisitPhone(){
                this.$emit('changeSuccess')
            },
            //创建客户跟进 电话拜访
            createFollowupFromVisitPhone(){
                var vm = this;
                var sendObj = {};
                sendObj.entity = {"cusCode":vm.cusCodeChooseFollowupType,
                    "followupContent":vm.visitPhoneContent,"followupType":'2'};
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "customerFollowup/create",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    if(response.data.code == '200'){
                        vm.$notify.success("新增跟进成功")
                        vm.successCreateFollowupFromVisitPhone();//跟进成功 关闭面板
                    }
                }).catch(function (error) {
                    vm.$message.error('页面:!customerFollowup/create');
                })
            },
            //跳转到带看房源
            toTakeLookHos(){
                var vm = this;
                vm.chooseTakeLookHosOpenOrClose = true;
                vm.$nextTick(()=>{
                    vm.$refs.refChooseTakeLookCus.visible = true;
                    vm.$refs.refChooseTakeLookCus.cusCodeChooseTakeLookHos = vm.cusCodeChooseFollowupType;
                })
            },
            //关闭带看房源 清空数据
            closeDialog(done){
                this.visitPhoneContent = '';
                done()
            },
            //新增跟进成功 ，关闭面板清空数据 带看类型
            successCreateFollowup(){
                var vm = this;
                vm.chooseTakeLookHosOpenOrClose = false;
                vm.$refs.refChooseTakeLookCus.visible = false;
                vm.$nextTick(() => {
                    vm.$emit('changeSuccess')
                })
            },
        }
    }
</script>

<style scoped>

</style>
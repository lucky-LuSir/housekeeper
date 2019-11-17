<template>
    <el-dialog  :close-on-click-modal="false" style="height: 100%"  top="40px" width="60%"
               :visible.sync="visible"
               :before-close="close"
               append-to-body>
        <div class="pre-scrollable-s" v-html="textLegal">
        </div>
        <div style="margin-bottom: 15px;margin-top: 10px;font-size: 14px">
            <p style="text-align: right;padding: 20px 20px 20px 20px"><el-checkbox v-model="isAgree">我已阅读并知晓协议条款内容</el-checkbox></p>

            <p style="text-align: center"><el-button type="primary" size="medium" v-bind:disabled="!isAgree" @click="agreeProtocol()"  round>确认申请</el-button></p>
        </div>
    </el-dialog>
</template>

<script>
    export default {

        data() {
            return {
                visible: false,
                isAgree: false,
                textLegal: '',//文本协议内容
            }
        },

        created(){

        },



        methods: {
            //我同意
            agreeProtocol(){
                this.$emit("argeeApply");
            },
            getCooProtocol(){
                var vm = this;
                var sendObj = {};
                sendObj.entity = {}
                var option = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "textlegal/querycootext",
                };
                this.$ajax(
                    option
                ).then(function (response) {
                    vm.textLegal = response.data.result.textLegal;
                }).catch(function (error) {
                    vm.$notify.error('合作申请失败');
                })
            },
            close(done){
                this.isAgree=false;
                done();
            }
        }
    }
</script>

<style scoped>
    .pre-scrollable-s {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        max-height: 450px;
        overflow-x: hidden;
        overflow-y: auto;
    }
    .pagex {
        overflow-y: hidden;
        overflow-x: auto;
    }
</style>
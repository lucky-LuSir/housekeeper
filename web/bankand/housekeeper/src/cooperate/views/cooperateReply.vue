<template>
    <!--数据表格-->
    <el-dialog  :close-on-click-modal="false" :visible.sync="visible"
               width="25%" append-to-body>
        <div style="text-align: center;font-size: 20px;font-weight: bold;height: 200px;line-height: 45px">
            <div>
                <p>如果同意合作</p>
                <p>您是否公开客户/业主的号码?</p>
            </div>
            <div>
                <el-radio-group v-model="cooOpenFlag">
                    <el-radio :label="true">公开</el-radio>
                    <el-radio :label="false">隐藏</el-radio>
                </el-radio-group>
            </div>
            <div>
                <el-button @click="writeConfuseReason" size="medium">拒绝</el-button>
                <el-button @click="replyAgree" size="medium">同意</el-button>
            </div>

        </div>
    </el-dialog>
</template>

<script>
    import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
    import ElRadioGroup from "../../../node_modules/element-ui/packages/radio/src/radio-group";
    import ElRadio from "../../../node_modules/element-ui/packages/radio/src/radio";
    export default {
        components: {
            ElRadio,
            ElRadioGroup,
            ElButton

        },
        data() {
            return {
                visible: false,
                cooOpenFlag: true,
                cooCode:null,
            }
        },

        mounted() {

        },
        watch: {

        },

        created() {

        },

        methods: {
            replyAgree(){
                var vm = this;
                var obj = {}
                var sendObj = {}
                sendObj.entity = {"cooCode": vm.cooCode,"cooOpenFlag":vm.cooOpenFlag}
                var options = {
                    method: 'POST',
                    headers: {'content-type': 'application/json'},
                    data: sendObj,
                    url: "cooperate/agree",
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.$notify({
                        message: response.data.message,
                        title: '操作提示',
                    });
                    vm.visible=false;
                    vm.$emit('reply');
                });
            },
            /**
             * 填写拒绝理由
             */
            writeConfuseReason () {
                this.$prompt('请填写原因', '操作提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({value}) => {
                    var vm = this;
                    var obj = {}
                    var sendObj = {}
                    sendObj.entity = {
                        "cooCode": vm.cooCode,
                        "rejectReason": value
                    }
                    var options = {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        data: sendObj,
                        url: "cooperate/refuse",
                    };
                    this.$ajax(
                        options
                    ).then(function (response) {
                        vm.$notify({
                            message: response.data.message,
                            title: '操作提示',
                        });
                        vm.visible=false;
                        vm.$emit('reply');
                    });
                })
            },
        }
    }
</script>

<style scoped>


</style>

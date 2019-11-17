<template>
    <el-dialog  :close-on-click-modal="false" style="height: 100%"  width="40%"
               :visible.sync="visible"
               :before-close="close"
               append-to-body>
        <div style="text-align: center;font-weight: bold;font-size: 20px">
            <p>您期望成交后获得多少比例的分成?</p>
            <p style="color: deepskyblue;font-size: 30px;font-weight: bold;margin: 20px">{{cooperatePercent}}%</p>
        </div>
        <div class="block">
            <el-slider
                    v-model="cooperatePercent">
            </el-slider>
            <p style="text-align: center;margin-top: 50px"><el-button type="primary" size="medium"
                                                     @click="openProtocol()"  round>确认申请</el-button></p>
        </div>
        <cooperate-protocol @argeeApply="agreeProtocol"  ref="cooperateprotocols"/>
    </el-dialog>

</template>

<script>
    import cooperateProtocol  from "./cooperateProtocol.vue"
    export default {
        components: {
            cooperateProtocol
        },
        data() {
            return {
                visible: false,
                cooperatePercent: 50
            }
        },

        created(){

        },


        methods: {

            openProtocol(){
                var vm = this;
                vm.$refs.cooperateprotocols.visible = true;
                vm.$refs.cooperateprotocols.getCooProtocol();

            },

            //我同意
            agreeProtocol(){
                var vm = this;
                vm.$refs.cooperateprotocols.visible = false;
                vm.$emit("getPercent", this.cooperatePercent);
            },
            close(done){
                var vm = this;
                vm.cooperatePercent=50;
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
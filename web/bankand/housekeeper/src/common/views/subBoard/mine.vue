<template>
    <div class="root"><!--根部分-->
        <div class="comm-title-div">
            <span >我的数据</span>
            <el-button class="edit-button" size="small" type="warning" plain @click="deleteBoard">删除</el-button>
        </div>

        <div class="data-content">
            <div class="my-data-div">
                <p class="my-data-font">本月招商面积</p>
                <p class="my-data-entity-data">{{thisMonthAreaSum}}平米</p>
            </div>
            <div class="my-data-div">
                <p class="my-data-font">本月意向客户</p>
                <p class="my-data-entity-data">{{thisMonthFollow}}位</p>
            </div>
            <div  class="my-data-div">
                <p class="my-data-font">本月新签合同</p>
                <p class="my-data-entity-data">{{thisMonthContract}}份</p>
            </div>
        </div>

    </div>
</template>

<script scoped>
    export default {
        props: ['id','param'],
        created:function () {
            let vm = this;
            let sumData = {
                entity:{},
            }

            let options = {
                method: 'POST',
                data: sumData,
                url: 'contract/myData'
            };
            this.$ajax(
                options
            ).then(function (response) {
                vm.thisMonthAreaSum = response.data.result.areaSum;
                vm.thisMonthContract = response.data.result.contractCount;
            }).catch(function (error) {
                vm.$alert('页面:我的数据看板查看入驻人数失败', '系统异常', {
                    confirmButtonText: '确定',
                    callback: action => {
                        vm.$message({
                            type: 'info',
                            message: `action: ${ action }`
                        });
                    }
                });
            });
            let followUrl = "followup/myData";
            vm.methodTemplate(vm,sumData,followUrl);
        },
        data() {
            return {
                thisMonthAreaSum:"",//本月招商总面积
                thisMonthContract:"",//本月新签合同
                thisMonthFollow:"",//本月意向客户
            }
        },
        methods: {
            methodTemplate(vm,dataObj,url){
                let options = {
                    method: "POST",
                    data: dataObj,
                    url: url
                };
                this.$ajax(
                    options
                ).then(function (response) {
                    vm.thisMonthFollow = response.data.result;
                }).catch(function (error) {
                    vm.$alert('页面:我的数据看板查询我的数据失败', '系统异常', {
                        confirmButtonText: '确定',
                        callback: action => {
                            vm.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });
                });
            },
            //通过id删除board
            deleteBoard(){
                this.$confirm('此操作将永久删除该看板, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let vm = this;
                    let param = {};
                    let entity = {
                        id:this.id,
                    };
                    param.entity = entity;

                    let options = {
                        method: 'POST',
                        data: param,
                        url:"board/delete",
                    };
                    this.$ajax(
                        options
                    ).then(response => {
                        if (!response.data.isException) {
                            vm.$message({
                                showClose: true,
                                message: '删除看板成功',
                                type: 'success'
                            });
                            this.$router.replace('/back')
                        }
                    }).catch(error => {
                        vm.$alert('页面:删除我的数据看板失败', '系统异常', {
                            confirmButtonText: '确定',
                            callback: action => {
                                vm.$message({
                                    type: 'info',
                                    message: `action: ${ action }`
                                });
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });



            },
        }
    }
</script>

<style scoped>
    .root {
        margin-right: 15px;
        min-width: 1000px;
        height: auto;
        max-height: 250px;
        background-color: white;

        overflow-x: hidden;
    }
    /*公共元素  每个div结束后分隔的时候用*/
    .comm-box-div {
        height: 10px;
        background-color: #F2F2F2
    }
    /*公共标题栏样式  我的数据 我的看板*/
    .comm-title-div {
        padding: 10px;
        height: 35px;
        line-height: 35px;
        color: #FF9900;
        border-bottom: 1px solid rgb(242, 242, 242);
    }
    /*我的数据下方展示*/
    .data-content {
        height: 175px;
        padding: 30px;
    }
    /*编辑按钮*/
    .edit-button {
        float: right;
        margin-right: 20px;
    }
    /*我的看板 - 本月招商面积、意向客户、本月新签合同*/
    .my-data-div{
        background-color: #71B8FF;
        width: 221px;
        height: 123px;
        text-align: center;
        float: left;
        margin-right: 60px;
    }
    /*我的看板 - 文字样式*/
    .my-data-font{
        color: #FAFAFA;
        line-height: 40px;
        font-size:20px;
    }
    /*我的看板 - 绑定的实体数据样式*/
    .my-data-entity-data{
        color: #FAFAFA;
        font-size:20px
    }

</style>
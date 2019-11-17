<template>
    <div class="root" ref="myRoot"><!--根部分-->
        <!--导航栏-->
        <el-row v-bind:style="{width:floatDivWidth+'px'}">
            <div class="navigation_div" v-bind:style="{width:floatDivWidth+'px'}">
                <el-input size="small"
                          @keyup.enter.native="searchEnterFun"
                          class="navigation_button_input"
                          v-model="keyword"
                          style="margin-left: 20px;margin-top: 10px;width: 300px;"
                          placeholder="请输入用户名称查询">
                    <el-button slot="append" icon="el-icon-search" @click="findReLoadUser"></el-button>
                </el-input>

                <el-button v-show="showAddUserBtn" class="navigation_button_back" @click="toRegistered()" size="small"
                           type="primary" icon="el-icon-circle-plus" plain>
                    新增用户
                </el-button>
                <el-button type="primary" size="small" plain class="navigate_select_button" @click="exportExcel()" icon="el-icon-document" >
                    导出
                </el-button>
                <el-button type="primary" size="small" class="navigation_button_back"  @click="findUnreviewedUser()">
                    未审核用户
                </el-button>
            </div>
        </el-row>

        <div class="user_table" v-loading.fullscreen.lock="loading"
             element-loading-text="拼命加载中">
            <el-table
                    id="focusCusBlackList"
                    :data="focusCusBlackList"
                    :highlight-current-row="true"
                    style="width: 100%;text-align: center;"
                    :show-header="true"
                    :border="true"
                    :height="fullHeight"
                    max-height="735">
                <el-table-column
                        type="selection"
                        width="45">
                </el-table-column>
                <el-table-column align="center"
                                 label="用户编号"
                                 width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userCode }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center"
                        label="员工名称"
                        width="150">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.userName }}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" label="所属部门" width="200">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.ownerDeptName }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="创建时间"
                        align="center"
                        width="160"
                        sortable
                        show-overflow-tooltip>
                    <template slot-scope="scope" v-if="scope.row.enterTime!=null">{{ scope.row.createTime | time}}</template>
                </el-table-column>
                <el-table-column align="center" label="操作" width="300" height="20">
                    <template slot-scope="scope" style="float: right;text-align: right;">
                        <el-button
                                v-show="showDeletedUserBtn"
                                size="mini"
                                type="danger"
                                @click="handleDeletedUser(scope.$index, scope.row)" icon="el-icon-edit">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <template>
                <div class="page-box">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="start"
                            :page-sizes="pageSizes"
                            :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total"
                            >
                    </el-pagination>
                </div>
            </template>
        </div>
    </div><!--根部分(end)-->
</template>


<script scoped>
    import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
    import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
    import File from "../../../common/components/file.vue";

    export default {
        components: {
            ElInput,
            ElButton,
            File,
        },
        created: function () {
            var vm = this;
            vm.basicReLoadUser();
        },
        updated() {
            this.floatDivWidth = this.$refs.myRoot.offsetWidth;
            window.onresize = () => {
                return (() => {
                    this.floatDivWidth = this.$refs.myRoot.offsetWidth;
                })();
            }
        },
        mounted() {
            const that = this
            that.handleChange();
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight - 205
                    that.fullHeight = window.fullHeight
                })()
            }
        },
        watch: {
            fullHeight (val) {
                if(!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function (){
                        that.timer = false
                    },400)
                }
            }
        },
        data() {
            return {
                showDeletedUserBtn:false,
                fullHeight: document.documentElement.clientHeight - 205,
                loading:true,
                start: 1,
                pageSize: 10,
                total: 0,
                pageSizes: [10,20,30],
                floatDivWidth: "",
                /*用户列表*/
                focusCusBlackList: [],
            }
        },
        methods: {

        }
    }

</script>

<style scoped>


    .root {
        min-width: 1000px;
    }

    /*导航栏DIV*/
    .navigation_div {
        width: 100%;
        height: 50px;
        min-width: 1000px;
    }

    /*导航栏返回按钮*/
    .navigation_button_back {
        margin-left: 20px;
    }

    .navigation_button_input {
        margin-left: 20px;
    }

    /*导航栏标题*/
    .navigation_title {
        height: 60px;
        margin-left: 10px;
        color: #FE980F;
        z-index: 300;
    }

    /*导航栏背景控制*/
    .navigation_button_background {
        height: 25px
    }

    /*新增页样式*/
    .konge {
        min-height: 45px;
        min-width: 1000px;
        background-color: rgb(242, 242, 242);
    }

    /*用户表格div*/
    .user_table {
        min-width: 1000px;
        background-color: white;
        min-height: 60px;
        line-height: 40px;
    }
    /*照片*/
    .photo-div {
        width: 220px;
        float: left;
        border: solid 1px;
    }

    .img-div1 {
        float: left;
        height: 200px;
        width: 200px;
        margin-left: 20px;
    }

    /*判断是是否上传了头像,如果没有加边框,反之不加*/
    .imgDiv2 {
        border: 1px solid rgb(242, 242, 242);
        margin-left: 10px;
    }
    .page-box {
        height: 40px;
        background-color: rgb(242, 242, 242);
        text-align: left;
        bottom: 0px;
        width: 100%;
        position: fixed;
        z-index: 200;
    }
    .el-select .el-input {
        width: 130px;
    }
    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

    /*表格*/
    .costTable{
        line-height: 40px;
        text-align: center;
        width: 100%;
    }
    .file_button{
        margin-top: 80px;
        margin-left: 50px;
    }
    .img-div1 {
        float: left;
        border:1px solid #f2f2f2;
        height: 200px;
        width: 200px;
    }
</style>

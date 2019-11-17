<template slot-scope="scope">
    <div class="rootcss" ref="myRoot">
        <!--erp消息通知-->
        <erp-notice ref="erpNoticePanel"/>

        <el-container :style="{height: fullHeight  +'px'}">
            <el-header style="background-color: #409EFF;color: white;text-align: left;
                    height: 60px;border-bottom:1px solid rgba(63,63,64,0.09);
                    box-shadow:0px 5px 10px 0px rgba(63,63,64,0.09);margin-bottom:1px;">
                <div class="mp_title_div">
                    <span class=" mp_title_text">HKPS</span>
                    <span class=" mp_title_text_chname">库房管家后台管理系统</span>
                </div>

                <div class="mp_title_user_div glyphicon glyphicon-user">
                    <el-dropdown>
                                <span class=" mp_logout_text_size" style="color: white;font-weight: bold">{{ userObj.userName }}
                                    <i class="el-icon-arrow-down"></i></span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                <el-button type="text" @click="dialogFormVisible = true">修改密码
                                </el-button>
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    <el-button style="color: white;font-weight: bold" class="logout_button mp_logout_text_size" type="text"
                               @click="logout()">退出
                    </el-button>
                </div>
            </el-header>
            <el-container>
                <el-aside
                          width="200px"
                          class="aside_style"
                          style="background-color: white;">
                    <el-scrollbar>
                    <v-menu style="width: 200px;background-color: #f5f5f5;"
                            :style="{height: fullHeight -61 +'px'}"
                            :collapse="true" mode="inline" :expand="false"
                            :data="inlineMenuData" @item-click="onSubMenuItemclick"/>
                    </el-scrollbar>
                </el-aside>
                <el-container>
                    <el-main style="background-color: #ffffff;color: #000000;padding: 0px;">

                        <el-dropdown trigger="click" class="clearTabs" @command="clearTab">
                            <el-button class="clearAllBtn" size="mini" type="danger">
                                <i class="el-icon-delete"></i>
                            </el-button>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item @click.native="clearTab">关闭全部标签页</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>

                        <el-tabs v-model="activeNameHkp" type="border-card"
                                 :closable="true"
                                 @tab-remove="removeTab" class="mp_tabs"

                                 style="overflow-x: hidden;text-align: left;">
                            <el-tab-pane
                                    v-for="(item, index) in mainTabs"
                                    :key="item.name"
                                    :label="item.title"
                                    :name="item.name"
                                    :closable="item.close">
                                <component :style="{height: fullHeight -105 +'px'}" v-bind:is="item.content">
                                </component>
                            </el-tab-pane>
                        </el-tabs>


                    </el-main>
                </el-container>
            </el-container>
        </el-container>

    </div>


</template>

<script scoped>
    import subButton from "../../../node_modules/element-ui/packages/button/src/button";
    import subDash from './subBoard/subDashBoard.vue';
    import deptList from "../../hrm/views/dept/deptList";
    import postList from "../../hrm/views/post/postList";
    import postDivide from "../../hrm/views/post/postDivide";
    import roleList from "../../hrm/views/role/roleList";
    import roleDivide from "../../hrm/views/role/roleDivide";
    import menuList from "../../hrm/views/menu/menuList";
    import internalUserList from "../../hrm/views/emp/internalUserList";
    import cpyAreaList from "../../hrm/views/cpyArea/cpyAreaList.vue";
    import userList from "../../hrm/views/user/userList";
    import orderList from "../../trade/views/order/orderList";
    import orderCreate from "../../trade/views/order/orderCreate.vue";
    import orderfollowupList from "../../trade/views/orderfollowup/orderfollowupList.vue"
    import achievementReportList from "../../bi/views/report/achievementReportList.vue"
    import orderPaybackList   from "../../trade/views/orderPayback/orderPaybackList.vue"
    import orderInvoiceList   from "../../trade/views/orderInvoice/orderInvoiceList.vue"
    import financeWagesReportList from "../../bi/views/wages/financeWagesReportList.vue"
    import hrWagesReportList from "../../bi/views/wages/hrWagesReportList.vue"
    import cusMuchFollowupReportList from "../../bi/views/report/cusMuchFollowupReportList.vue"
    import dataTransfer from "../../sys/views/transfer/dataTransfer.vue"
    import hosLocRegionList from "../../hos/views/hosLocRegionList.vue"
    import hosList from "../../hos/views/hosList.vue"
    import marketAnalysis from "../../bi/views/report/marketAnalysisReportList.vue"
    import hosLocList from "../../hos/views/hosLocList.vue"
    import convertRate from "../../bi/views/report/convertRateReportList.vue"
    import hosLocMapList from "../../hos/views/hosLocMapList.vue"
    import recordList from "../../bi/views/report/recordReportList.vue"
    import homeManager from "./homeManager.vue"
    import rankingCreate from "../../hrm/views/ranking/rankingCreate.vue"
    import houseDictionaryList from "../../bi/views/report/houseDictionaryReportList.vue"
    import hosFollowupList from "../../hos/views/hosFollowupList.vue"
    import cusList from "../../cus/views/cusList.vue"
    import cusDetails from "../../cus/views/cusDetails.vue"
    import cusFollowupList from "../../cus/views/cusFollowupList.vue"
    import hosDetails from "../../hos/views/hosDetails.vue"
    import hosCondition from "../../hos/views/hosCondition.vue"
    import cusCondition from "../../cus/views/cusCondition.vue"
    import hosLocRegionAddList from "../../hos/views/hosLocRegionAddList.vue"
    import operationList from "./operationList.vue"
    import operationCondition from "./operationCondition.vue"
    import operationDetails from "./operationDetails.vue"
    import baseOperationList from "./baseOperationList.vue"
    import baseOperationDetails from "./baseOperationDetails.vue"
    import seeHouseCusList from "../../hos/views/seeHouseCusList.vue"
    import hosMatchCus from "../../hos/views/hosMatchCus.vue"
    import hosOrderQuery from "../../hos/views/hosOrderQuery.vue"
    import cusUpDownList from "../../cus/views/cusUpDownList.vue"
    import cusSeeHouseList from "../../cus/views/cusSeeHouseList.vue"
    import cusMatchHos from "../../cus/views/cusMatchHos.vue"
    import cusChooseFollowupType from "../../cus/views/cusChooseFollowupType.vue"
    import cusChooseTakeLookHos from "../../cus/views/cusChooseTakeLookHos.vue"
    import cusCreateFollowupTakeLook from "../../cus/views/cusCreateFollowupTakeLook.vue"
    import cusPush from "../../cus/views/cusPush.vue"
    import cusAddOrUpdate from "../../cus/views/cusAddOrUpdate.vue"
    import hosOwnerFollowupList from "../../hos/views/hosOwnerFollowupList.vue"
    import erpNotice from  "./erpNoticePanel.vue"
    import hosContractCensus from "../../hos/views/hosContractCensus"
    import myCooperate from "../../cooperate/views/myCooperate.vue"
    import receiveCooperate from "../../cooperate/views/receiveCooperate.vue"

    import wageReportList from "../../bi/views/wages/wageReportList.vue"
    import cusServiceList from "../../hrm/views/dept/cusServiceList"
    import contractPeriodBoard from "../../bi/views/report/contractPeriodBoard.vue";
    import hosAndCusTotal from "../../bi/views/report/hosAndCusTotal.vue";
    import reportAccess from "../../bi/views/access/reportAccessList";
    import profitShare from "../../hrm/views/dept/deptProfitShareList";
    import entrustAndBespeakGraph from "../../bi/views/report/entrustAndBespeakGraph";

    import cusApplyHidOrPriList from "../../cus/views/cusApplyHidOrPriList.vue"

    import orderUpdateApplyList from "../../trade/views/order/orderUpdateApplyList";
    import cusEntrustList from "../../cus/views/cusEntrustList.vue"
    import cusBespeakList from "../../cus/views/cusBespeakList.vue"
    import focusReportList from "../../bi/views/report/focusReportList.vue";
    import portUserList from "../../hrm/views/user/portUserList";
    export default {
        components: {
            subDash,
            subButton,
            deptList,
            postList,
            postDivide,
            roleList,
            roleDivide,
            menuList,
            internalUserList,
            cpyAreaList,
            userList,
            orderList,
            orderCreate,
            orderfollowupList,
            achievementReportList,
            orderPaybackList,
            orderInvoiceList,
            financeWagesReportList,
            hrWagesReportList,
            cusMuchFollowupReportList,
            dataTransfer,
            hosLocRegionList,
            hosList,
            hosLocList,
            marketAnalysis,
            convertRate,
            hosLocMapList,
            homeManager,
            rankingCreate,
            recordList,
            houseDictionaryList,
            hosFollowupList,
            cusList,
            cusDetails,
            cusFollowupList,
            hosOwnerFollowupList,
            hosDetails,
            hosCondition,
            cusCondition,
            hosLocRegionAddList,
            operationList,
            operationCondition,
            operationDetails,
            seeHouseCusList,
            hosMatchCus,
            hosOrderQuery,
            cusUpDownList,
            cusSeeHouseList,
            cusMatchHos,
            cusChooseFollowupType,
            cusCreateFollowupTakeLook,
            cusChooseTakeLookHos,
            cusPush,
            cusAddOrUpdate,
            erpNotice,
            hosContractCensus,
            myCooperate,
            receiveCooperate,
            wageReportList,
            cusServiceList,
            baseOperationList,
            baseOperationDetails,
            contractPeriodBoard,
            hosAndCusTotal,
            reportAccess,
            profitShare,
            entrustAndBespeakGraph,
            orderUpdateApplyList,
            cusApplyHidOrPriList,
            cusEntrustList,
            cusBespeakList,
            focusReportList,
            portUserList
        },
        created: function () {
            if (!localStorage.getItem("gn_request_token")) {
                this.toLogin();
            }
            var menuTreeList = JSON.parse(localStorage.getItem("menuTreeList"));
            this.inlineMenuData = menuTreeList[0].children;

            this.mainTabs.push({
                title: '首页',
                name: 'hm1',
                content: "homeManager",
                close:'closable'
            });
            this.activeNameHkp = 'hm1';

        },
        data() {
            return {
                userObj: {
                    userName: this.$cookieStore.getCookie("userName"),
                },
                //内联菜单数据
                inlineMenuData: [],
                activeNameHkp: '',
                mainTabs: [],
                tabIndex: 0,
                fullHeight: document.documentElement.clientHeight,

            }
        },

        mounted() {
            const that = this
            window.onresize = () => {
                return (() => {
                    window.fullHeight = document.documentElement.clientHeight
                    that.fullHeight = window.fullHeight
                })()
            }
            this.$refs.erpNoticePanel.erpNotice();
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
        methods: {
            onSubMenuItemclick(aVal) {
                var lastNowMenu = aVal[aVal.length - 1];
                var flag = false;
                for (let oneArr of this.mainTabs) {
                    if (oneArr.content == lastNowMenu.item) {
                        flag = true;
                    }
                }
                if (flag == false) {
                    this.mainTabs.push({
                        title: lastNowMenu.name,
                        name: lastNowMenu.name,
                        content: lastNowMenu.item
                    });
                }

                this.activeNameHkp = lastNowMenu.name;
            },
            openAlert() {
                this.$alert('这是一段内容', '标题名称', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            },
            addTab(targetName) {
                let newTabName = ++this.tabIndex + '';
                this.mainTabs.push({
                    title: 'New Tab',
                    name: newTabName,
                    content: 'sub-' + 'button'
                });
                this.activeNameHkp = newTabName;
            },
            removeTab(targetName) {
                let tabs = this.mainTabs;
                for (let i = 0; i < tabs.length; i++) {
                    if (tabs[i].name === targetName) {
                        //首页不准删除
                        if ('homeManager' === tabs[i].content) {
                            return;
                        }
                    }
                }

                let activeName = this.activeNameHkp;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                            }
                        }
                    });
                }
                this.activeNameHkp = activeName;
                this.mainTabs = tabs.filter(tab => tab.name !== targetName);
            },
            // 退出
            logout() {
                let vm = this;
                let tokenData = localStorage.getItem("gn_request_token");
                let tokenObj = {
                    entity: {
                        token: tokenData
                    }
                }

                this.$confirm('是否退出登陆?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'primary'
                }).then(() => {
                    if (localStorage.getItem("gn_request_token")) {
                        let options = {
                            method: "POST",
                            data: tokenObj,
                            url: "user/logout"
                        };
                        this.$ajax(
                            options
                        ).then(response => {
                            //删除cookie和用户名
                            localStorage.clear();
                            this.$cookieStore.delCookie("userName");
                            this.$cookieStore.delCookie("userCode");
                            this.$cookieStore.delCookie("roleCode");
                            if (!localStorage.getItem("gn_request_token")) {
                                this.toLogin();
                            }
                            vm.$notify({
                                message: response.data.message,
                                title: '操作提示',
                            });
                        }).catch(error => {
                            this.$alert('页面:退出失败', '系统异常', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        });
                    }
                })

            },
            // 返回登陆页
            toLogin() {
                this.$router.push({
                    name: "login"
                })
            },

            clearTab (command) {
                // 删除首页以外的数组值
                this.mainTabs.splice(1, this.mainTabs.length-1)
                // 显示首页
                this.activeNameHkp = this.mainTabs[0].name
                // 页签信息保存在本地
            },

        }
    }
</script>


<style scoped>
    .rootcss {
        min-width: 1210px;
        height: 100%;
        overflow-y: hidden;
        overflow-x: hidden;
    }

    .mp_title_div {
        margin: 0px 0px 0px 0px;
        padding: 0px 0px 0px 0px;
        color: white;
        float: left;
        width: 50%;
    }

    .mp_title_text {
        font-size: 32px;
    }

    .mp_title_text_chname {
        font-size: 14px;
        font-weight: bold;
    }

    .mp_title_user_div {
        padding-top: 5px;
        margin: 0px auto;
        width: 220px;
        float: right;

    }

    .mp_tabs {
        text-align: left;
        width: 100%;

    }


    .el-scrollbar__bar.is-vertical {
        width: 6px;
        top: 2px;
    }

    .el-scrollbar__bar {
        position: absolute;
        right: 2px;
        bottom: 2px;
        z-index: 1;
        border-radius: 4px;
        opacity: 0;
        transition: opacity .12s ease-out;
    }


    .clearAllBtn {
        width: 30px;
        height: 43px;
        padding: 0;
    }
    .clearTabs {
        position: absolute;
        height: 20px;
        right: 0;
        top: 60px;
        z-index: 999;
    }
</style>


<!--以下为全局覆盖样式,特殊使用,不可随意添加0308 strat-->

<!--以上为全局覆盖样式,特殊使用 end-->

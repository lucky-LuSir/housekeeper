import login from './sys/views/login/login.vue'
import loginChen from './sys/views/login/loginChen.vue'
import loginLi from './sys/views/login/loginLi.vue'
import loginLiu from './sys/views/login/loginLiu.vue'
import forgetPwd from './sys/views/login/forgetPwd.vue'

import hkpMainPage from './common/views/hkpMainPage.vue'
import back from './common/views/back.vue'


import deptList from './hrm/views/dept/deptList.vue'
import deptAdd from './hrm/views/dept/deptAdd.vue'
import deptEdit from './hrm/views/dept/deptEdit.vue'
import internalUserList from './hrm/views/emp/internalUserList.vue'
import internalUserDetails from './hrm/views/emp/internalUserDetails.vue'
import internalUserCreate from './hrm/views/emp/internalUserCreate.vue'
import internalUserEdit from './hrm/views/emp/internalUserEdit.vue'

import postCreate from './hrm/views/post/postCreate.vue'
import postList from './hrm/views/post/postList.vue'
import postDivide from './hrm/views/post/postDivide.vue'
import menuList from './hrm/views/menu/menuList.vue'
import cpyList from './hrm/views/cpy/cpyList.vue'
import cpyAreaList from './hrm/views/cpyArea/cpyAreaList.vue'
import roleList from './hrm/views/role/roleList.vue'
import roleDivide from './hrm/views/role/roleDivide.vue'
import userList from './hrm/views/user/userList.vue'


import fileDemo from './common/test/fileDemo.vue'
import cityPickerDemo from './common/test/cityPickerDemo.vue'
import util from './common/test/util.vue'

//配置导航守卫
var pageUrl = {
    requireAuth: true
};

let routes = [
    {
        path: '/', component: hkpMainPage, meta: pageUrl,
        children: [
            {
                path: '/hkpMainPage',
                name: 'hkpMainPage',
                component: hkpMainPage
            },
            {path: 'back', name: 'back', component: back, meta: pageUrl},

        ]
    },
    {
        path: '/login',
        name: 'login',
        component: login
    },
    {
        path: '/hkpMainPage',
        name: 'hkpMainPage',
        component: hkpMainPage
    },
    {
        path: '/loginChen',
        name: 'loginChen',
        component: loginChen
    },
    {
        path: '/loginLi',
        name: 'loginLi',
        component: loginLi
    },
    {
        path: '/loginLiu',
        name: 'loginLiu',
        component: loginLiu
    },
    {
        path: '/forgetPwd',
        name: 'forgetPwd',
        component: forgetPwd
    }

];

export default routes;
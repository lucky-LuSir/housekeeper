import Vue from 'vue'
import ElementUI from 'element-ui'
import 'vue-beauty/package/style/vue-beauty.css'
import 'element-ui/lib/theme-chalk/index.css'
import '../theme/index.css'
import App from './App.vue'
import VueRouter from 'vue-router'
import routes from './routes'
import {addCookie, getCookie, delCookie} from './common/common/js/cookie.js'
import {judgeMenu} from './common/common/js/flagMenu.js'
import store from './store.js' //VUE

import BASE_URL from "../public/hkp_BASE_URL.js"; //配置全局URL
import echarts from 'echarts'

import md5 from 'js-md5';
import './common/assets/icon/iconfont.css'
import './directives.js'
import axios from 'axios'
import vueBeauty from 'vue-beauty'
//插件的包
import PerfectScrollbar from 'perfect-scrollbar';
//对应的css
import "perfect-scrollbar/css/perfect-scrollbar.css";
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'
import VueClipboard from 'vue-clipboard2'


import utils from './utils';   //获取url参数
Vue.prototype.$utils = utils;   //注册全局方法

/**
 * @description 自动判断该更新PerfectScrollbar还是创建它
 * @param {HTMLElement} el - 必填。dom元素
 */
const el_scrollBar = (el) => {
    //在元素上加点私货，名字随便取，确保不会和已有属性重复即可，我取名叫做_ps_
    if (el._ps_ instanceof PerfectScrollbar) {
        el._ps_.update();
    } else {
        //el上挂一份属性
        el._ps_ = new PerfectScrollbar(el, { suppressScrollX: true });
    }
};

//接着，自定义Vue指令,指令名你自己随便编一个，我们假定它叫scrollBar
Vue.directive("scrollBar", {
    //使用inserted钩子函数（初次创建dom）获取使用自定义指令处的dom
    inserted(el, binding, vnode) {
        //判断其样式是否存在position 并且position为"fixed", "absolute"或"relative"
        //如果不符合条件，抛个错误。当然你也可以抛个警告然顺便给其position自动加上"relative"
        //为什么要这么做呢，因为PerfectScrollbar实现原理就是对dom注入两个div，一个是x轴一个是y轴，他们两的position都是absolute。
        //对css稍有常识的人都知道，absolute是相对于所有父节点里设置了position属性的最近的一个节点来定位的，为了能够正确定位，我们要给其设置position属性
        const rules = ["fixed", "absolute", "relative"];
        if (!rules.includes(window.getComputedStyle(el, null).position)) {
            console.error(`perfect-scrollbar所在的容器的position属性必须是以下之一：${rules.join("、")}`)
        }
        //el上挂一份属性
        el_scrollBar(el);
    },
    //更新dom的时候
    componentUpdated(el, binding, vnode, oldVnode) {
        try {
            //vnode.context其实就是vue实例，这里其实无需实例也直接用Vue的静态方法
            //故而也可以写成Vue.nextTick
            vnode.context.$nextTick(
                () => {
                    el_scrollBar(el);
                }
            )
        } catch (error) {
            console.error(error);
            el_scrollBar(el);
        }
    }
})
Vue.use(Viewer);
Viewer.setDefaults({
    'inline':true,
    'button':true, //右上角按钮
    "navbar": true, //底部缩略图
    "title": true, //当前图片标题
    "toolbar": true, //底部工具栏
    "tooltip": true, //显示缩放百分比
    "movable": true, //是否可以移动
    "zoomable": true, //是否可以缩放
    "rotatable": true, //是否可旋转
    "scalable": true, //是否可翻转
    "transition": true, //使用 CSS3 过度
    "fullscreen": true, //播放时是否全屏
    "keyboard": true, //是否支持键盘
    "url": "data-source",
    ready: function (e) {
        console.log(e.type,'组件以初始化');
    },
    show: function (e) {
        console.log(e.type,'图片显示开始');
    },
    shown: function (e) {
        console.log(e.type,'图片显示结束');
    },
    hide: function (e) {
        console.log(e.type,'图片隐藏完成');
    },
    hidden: function (e) {
        console.log(e.type,'图片隐藏结束');
    },
    view: function (e) {
        console.log(e.type,'视图开始');
    },
    viewed: function (e) {
        console.log(e.type,'视图结束');
    },
    zoom: function (e) {
        console.log(e.type,'图片缩放开始');
    },
    zoomed: function (e) {
        console.log(e.type,'图片缩放结束');
    }
});


Vue.prototype.$md5 = md5;
Vue.prototype.$ajax = axios;
Vue.prototype.$request = request;
axios.defaults.baseURL = BASE_URL.BASE_URL; //设置全局URL
axios.defaults.headers.post['Content-Type'] = 'application/json';

/*---------------------------------联调环境,将token放入header中start---------------------------------*/
axios.interceptors.request.use(config => {
    let token = localStorage.getItem("gn_request_token");
    if (token == null) {
        token = '';
    }
    config.headers.gn_request_token = token;
    return config
}, error => {
    console.log('error', error)
});
/*---------------------------------联调环境,将token放入header中end---------------------------------*/

/*---------------------------------http response 拦截器start---------------------------------*/
axios.interceptors.response.use(response => {
    if (!response.data.isSuccess) {
        if (response.data.code && response.data.code === 'U1000'){
            if (BASE_URL.BASE_URL=="/api"){
                window.document.location.href ="http://localhost:8082/login";
            }else if(BASE_URL.BASE_URL=="http://test.hkp.kufangwuyou.com/housekeeper/"){
                window.document.location.href = "http://test.hkp.kufangwuyou.com/login";
            }else if(BASE_URL.BASE_URL=="http://hkp.kufangwuyou.com/housekeeper/"){
                window.document.location.href = "http://hkp.kufangwuyou.com/login";
            }
        }else{
            hkp.$alert(response.data.message, '温馨提示', {
                confirmButtonText: '确定',
            });
        }
        return null;
    } else {
        return response;
    }
}, error => {
    //用户没有登录跳转到指定页面
    //TODO
    if (response.data.code && response.data.code === 'U1000') {
        window.document.location.href = "http://localhost:8082/login";
    }else {
        hkp.$alert("服务器发生未知异常,请联系管理员", '系统异常', {
            confirmButtonText: '确定',
            callback: action => {
            }
        });
    }
});
/*---------------------------------http response 拦截器end---------------------------------*/
/**
 * axios请求方法封装
 * @param config
 */
function request(config) {

    if (!config.conf || !config.conf.url) {
        message.error('参数必须配置规则同axios的配置');
        return;
    }

    if (!config.conf.method) {
        config.conf.method = 'POST';
    }

    if (config.conf.method === 'POST'
        && config.conf.headers
        && !config.conf.headers.contentType) {

        config.conf.headers.contentType = 'application/json;charset=UTF-8';
    }

    axios(config.conf).then((response) => {

        if (response.data.isSuccess
            && config.success
            && (typeof config.success == 'function')) {

            config.success(response.data);

        } else {

            //用户没有登录跳转到指定页面
            //TODO
            if (response.data.code && response.data.code === 'U1000') {
                window.document.location.href = BASE_URL.BASE_URL+"login";
            }

            message.error(response.data.message);
            if (config.fail && (typeof config.fail == 'function')) {
                config.fail(response);
            }

        }
    }).catch((reason) => {
        if (reason.response) {
            message.error(reason.response.data);
        } else {
            message.error(reason);
        }
        if (config.exception && typeof config.exception === 'function') {
            config.exception(reason);
        }
    });
}

export {axios, request};

Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(vueBeauty);
Vue.use(VueClipboard);
/*---------------------------------封装cookie方法---------------------------------*/
//添加使用cookie
Vue.prototype.$cookieStore = {
    addCookie,
    getCookie,
    delCookie
};
/*---------------------------------封装cookie方法---------------------------------*/

/*---------------------------------判断权限全局方法---------------------------------*/
Vue.prototype.$flagMenuStore = {judgeMenu};
/*---------------------------------判断权限全局方法---------------------------------*/

/*---------------------------------添加全局变量,包括baseurl---------------------------------*/
//添加全局变量,包括baseurl
Vue.prototype.hkpGlobal = BASE_URL;
Vue.prototype.hkpBaseUrl = BASE_URL.BASE_URL;
//添加全局变量,包括baseurl  end
/*---------------------------------添加全局变量,包括baseurl---------------------------------*/


Vue.prototype.$echarts = echarts;

/*-------------------------------配置全局日期start-----------------------------------------*/
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}
Vue.filter('time', function (value) {
    let newDate = new Date(parseInt(value));
    return newDate.format('yyyy-MM-dd hh:mm:ss');
})
Vue.filter('timeDate', function (value) {
    let newDate = new Date(parseInt(value));
    return newDate.format('yyyy-MM-dd');
})
/*-------------------------------配置全局日期end-----------------------------------------*/


/*-------------------------------配置数字转换-----------------------------------------*/
Vue.filter('num', function (value) {
    var N = [
        "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
    ];
    var str = value.toString();
    var len = value.toString().length;
    var C_Num = [];
    for (var i = 0; i < len; i++) {
        C_Num.push(N[str.charAt(i)]);
    }
    return C_Num.join('');
})

/*手机号码校验*/
let judgePhone = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
Vue.prototype.$MOBILE_PATTERN = judgePhone;
//座机验证
let landlinePhone = /^0\d{2,3}-\d{7,8}$/;
Vue.prototype.$LANDLINE_PHONE = landlinePhone;
/*-------------------------------配置数字转换end-----------------------------------------*/


const router = new VueRouter({
    mode: 'history',
    routes
})
/*-------------------------------定义导航守卫-----------------------------------------*/
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {  //判断该路由是否需要登录权限
         //let cookie = getCookie("gn_request_token");
        let cookie = localStorage.getItem("gn_request_token");

        if (cookie) {  //cookie不为null,则放行
            next();
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}  //将跳转的路由path作为参数，登录成功后跳转到该路由
            })
        }
    } else {
        next();
    }
})
/*-------------------------------定义导航守卫-----------------------------------------*/


var hkp = new Vue({
    el: '#app',
    store,//vuex第一步引入import,就能用了
    router,
    render: h => h(App)

})

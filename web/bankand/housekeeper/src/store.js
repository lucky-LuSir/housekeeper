import Vue from 'vue'
import Vuex from 'vuex'
import deptStore from './hrm/vuex/deptStore.js'

Vue.use(Vuex)


export default new Vuex.Store({
    modules: {
        deptStore: deptStore
    }
    // ,
    // state,
    // mutations,
    // actions,
    // getters
})


const state = {
    xaa: '初',
    deptObj: {
        parentCode:'',
        deptCode:'',
        deptName:'',
        deptType: null,
        leaderCode:''

    },
    aobj:{
        a:'',
        b:'',
        c:''
    }
}

// mutations里写函数怎样影响state
const mutations = {
    sayha(state) {
        state.xaa = '变哈哈'
    },
    setId(state ,  id) {

        state.id = id

    },
    setAobj(state ,  v) {

        state.aobj = v

    },
    setDeptObj(state ,  value) {

        state.deptObj = value

    }
}

// actions里写有几个函数
const actions = {
    sayha: ({ commit }) => commit('sayha')
}

const getters = {}

export default {
    state,
    mutations,
    actions,
    getters
}
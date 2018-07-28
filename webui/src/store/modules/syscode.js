const state = {
  updateSysCode : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSysCode(state,sysCode){
    state.updateSysCode = sysCode;
  },
  clearUpdateSysCode(state){
    state.updateSysCode = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

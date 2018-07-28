const state = {
  updateZLogInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateZLogInfo(state,zLogInfo){
    state.updateZLogInfo = zLogInfo;
  },
  clearUpdateZLogInfo(state){
    state.updateZLogInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

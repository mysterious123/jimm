const state = {
  updatehistoryInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatehistoryInfo(state,historyInfo){
    state.updatehistoryInfo = historyInfo;
  },
  clearUpdatehistoryInfo(state){
    state.updatehistoryInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

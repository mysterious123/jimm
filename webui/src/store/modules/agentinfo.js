const state = {
  updateAgentInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateAgentInfo(state,agentInfo){
    state.updateAgentInfo = agentInfo;
  },
  clearUpdateAgentInfo(state){
    state.updateAgentInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

const state = {
  updateZAgentInfo : null,
  editAgentActive : "Agent指标"
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateZAgentInfo(state,zAgentInfo){
    state.updateZAgentInfo = zAgentInfo;
  },
  clearUpdateZAgentInfo(state){
    state.updateZAgentInfo = null;
  },
  setEditAgentActive(state,editAgentActive){
    state.editAgentActive = editAgentActive;
  },
  clearEditAgentActive(state){
    state.editAgentActive = "Agent指标";
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

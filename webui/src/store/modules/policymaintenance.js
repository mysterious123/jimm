const state = {
  updatePolicyMaintenance : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatePolicyMaintenance(state,policyMaintenance){
    state.updatePolicyMaintenance = policyMaintenance;
  },
  clearUpdatePolicyMaintenance(state){
    state.updatePolicyMaintenance = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

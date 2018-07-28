const state = {
  updatePolicyPeriod : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatePolicyPeriod(state,policyPeriod){
    state.updatePolicyPeriod = policyPeriod;
  },
  clearUpdatePolicyPeriod(state){
    state.updatePolicyPeriod = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

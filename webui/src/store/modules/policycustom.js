const state = {
  updatePolicyCustom : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatePolicyCustom(state,policyCustom){
    state.updatePolicyCustom = policyCustom;
  },
  clearUpdatePolicyCustom(state){
    state.updatePolicyCustom = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

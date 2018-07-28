const state = {
  updateOperationPolicyParam : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateOperationPolicyParam(state,operationPolicyParam){
    state.updateOperationPolicyParam = operationPolicyParam;
  },
  clearUpdateOperationPolicyParam(state){
    state.updateOperationPolicyParam = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

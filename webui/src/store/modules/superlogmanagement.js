const state = {
  updateSuperLogManagement : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperLogManagement(state,superLogManagement){
    state.updateSuperLogManagement = superLogManagement;
  },
  clearUpdateSuperLogManagement(state){
    state.updateSuperLogManagement = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

const state = {
  updateSuperAutomation : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperAutomation(state,superAutomation){
    state.updateSuperAutomation = superAutomation;
  },
  clearUpdateSuperAutomation(state){
    state.updateSuperAutomation = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

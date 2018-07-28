const state = {
  updateSuperUserGroup : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperUserGroup(state,superUserGroup){
    state.updateSuperUserGroup = superUserGroup;
  },
  clearUpdateSuperUserGroup(state){
    state.updateSuperUserGroup = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

const state = {
  updateSuperUser : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperUser(state,superUser){
    state.updateSuperUser = superUser;
  },
  clearUpdateSuperUser(state){
    state.updateSuperUser = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

const state = {
  updateSuperRole : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperRole(state,superRole){
    state.updateSuperRole = superRole;
  },
  clearUpdateSuperRole(state){
    state.updateSuperRole = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

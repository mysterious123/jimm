const state = {
  updateSuperSeverity : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperSeverity(state,superSeverity){
    state.updateSuperSeverity = superSeverity;
  },
  clearUpdateSuperSeverity(state){
    state.updateSuperSeverity = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

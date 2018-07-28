const state = {
  updateSuperDatasource : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperDatasource(state,superDatasource){
    state.updateSuperDatasource = superDatasource;
  },
  clearUpdateSuperDatasource(state){
    state.updateSuperDatasource = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

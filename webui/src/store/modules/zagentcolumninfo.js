const state = {
  updateZAgentColumnInfo : null,
  search : ""
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateZAgentColumnInfo(state,zAgentColumnInfo){
    state.updateZAgentColumnInfo = zAgentColumnInfo;
  },
  clearUpdateZAgentColumnInfo(state){
    state.updateZAgentColumnInfo = null;
  },
  setSearch(state,search){
    state.search = search;
  },
  clearSearch(state){
    state.search = "";
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

const state = {
  updateZMibOid : null,
  search : ""
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateZMibOid(state,zMibOid){
    state.updateZMibOid = zMibOid;
  },
  clearUpdateZMibOid(state){
    state.updateZMibOid = null;
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

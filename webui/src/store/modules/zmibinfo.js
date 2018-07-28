const state = {
  updatezMibInfo : null,
  editMibActive : "MIB指标"
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatezMibInfo(state,zMibInfo){
    state.updatezMibInfo = zMibInfo;
  },
  clearUpdatezMibInfo(state){
    state.updatezMibInfo = null;
  },
  setEditMibActive(state,editMibActive){
    state.editMibActive = editMibActive;
  },
  clearEditMibActive(state){
    state.editMibActive = "MIB指标";
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

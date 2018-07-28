const state = {
  updateVenderInfo : null,
  venderInfo : null
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateVenderInfo(state,venderInfo){
    state.updateVenderInfo = venderInfo;
  },
  clearUpdateVenderInfo(state){
    state.updateVenderInfo = null;
  },
  setVenderInfo(state,venderInfo){
    state.venderInfo = venderInfo;
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

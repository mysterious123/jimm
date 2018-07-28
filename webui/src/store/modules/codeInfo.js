const state = {
  updatecodeInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatecodeInfo(state,codeInfo){
    state.updatecodeInfo = codeInfo;
  },
  clearUpdatecodeInfo(state){
    state.updatecodeInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations

}

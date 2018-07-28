const state = {
  updatevenderDetailInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatevenderDetailInfo(state,venderDetailInfo){
    state.updatevenderDetailInfo = venderDetailInfo;
  },
  clearUpdatevenderDetailInfo(state){
    state.updatevenderDetailInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

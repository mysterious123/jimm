const state = {
  updateServerGroup : null,
}

const getters = {

}

const actions = {

}
const mutations = {
  setUpdateServerGroup(state,serverGroup){
    state.updateServerGroup = serverGroup;
  },
  clearUpdateServerGroup(state){
    state.updateServerGroup = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

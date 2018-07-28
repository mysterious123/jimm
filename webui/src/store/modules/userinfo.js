const state = {
  updateUserInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateUserInfo(state,userInfo){
    state.updateUserInfo = userInfo;
  },
  clearUpdateUserInfo(state){
    state.updateUserInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

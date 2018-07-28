const state = {
  updateSuperLogo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSuperLogo(state,superLogo){
    state.updateSuperLogo = superLogo;
  },
  clearUpdateSuperLogo(state){
    state.updateSuperLogo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

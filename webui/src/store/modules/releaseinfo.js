const state = {
  updatereleaseInfo : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdatereleaseInfo(state,releaseInfo){
    state.updatereleaseInfo = releaseInfo;
  },
  clearUpdatereleaseInfo(state){
    state.updatereleaseInfo = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

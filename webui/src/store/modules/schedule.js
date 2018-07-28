const state = {
  updateSchedule : null,
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateSchedule(state,schedule){
    state.updateSchedule = schedule;
  },
  clearUpdateSchedule(state){
    state.updateSchedule = null;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

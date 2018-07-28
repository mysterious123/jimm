const state = {
  updateServerInfo : null,
  selectedNode:null,
  treeState:[],
}

const getters = {

}

const actions = {

}

const mutations = {
  setUpdateServerInfo(state,serverInfo){
    state.updateServerInfo = serverInfo;
  },
  clearUpdateServerInfo(state){
    state.updateServerInfo = null;
  },
  setSelectedNode(state,selectedNode){
    state.selectedNode = selectedNode;
  },
  clearSelectedNode(state){
    state.selectedNode = null;
  },
  pushTreeState(state,treeNode){
    state.treeState.push(treeNode);
  },
  removeTreeState(state,treeNode){
    state.treeState = _.filter(state.treeState, function(node) { return node.id !== treeNode.id; });
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

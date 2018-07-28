
var _idMaker = function* () {
  var index = 1;
  while(true)
    yield index++;
};

var newOperationPolicyInfo = {
  policyName: "",
  policyType: "",
  nature: "1",
  createPerson: "",
  createTime: "",
  operationPolicyParams:[],
}

const state = {
  updateOperationPolicyInfo : null,
  newOperationPolicyInfo : _.cloneDeep(newOperationPolicyInfo),
  operation : "update",
  insert_items : [],
  active : "策略管理",
  idMaker : _idMaker(),
}

const getters = {

}

const actions = {

}

const mutations = {
  clearIdMaker(state){
    state.idMaker = _idMaker();
  },
  setUpdateOperationPolicyInfo(state,operationPolicyInfo){
    state.updateOperationPolicyInfo = operationPolicyInfo;
  },
  clearUpdateOperationPolicyInfo(state){
    state.updateOperationPolicyInfo = null;
  },
  setOperation(state,operation){
    state.operation = operation
  },
  clearOperation(state){
    state.operation = 'update'
  },
  addInsertItems(state,item){
    state.insert_items.push(item);
  },
  removeInsertItems(state,item){
    var items = _.cloneDeep(state.insert_items);
    _.remove(items,(insert_item)=>{
      return insert_item.id == item.id;
    })
    state.insert_items = items;
  },
  clearInsertItems(state){
    state.insert_items = [];
  },
  setActive(state,active){
    state.active = active;
  },
  clearActive(state){
    state.active = "策略管理"
  },
  clearNewOperationPolicyInfo(state){
    state.newOperationPolicyInfo = _.cloneDeep(newOperationPolicyInfo)
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}

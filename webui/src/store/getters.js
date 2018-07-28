export const base_tree_setting = state =>{
  return _.cloneDeep({
    view: {
      showLine: false,
      showIcon: false,
      selectedMulti: false,
      dblClickExpand: true
    },
    data: {
      key: {
        name:"name"
      },
      simpleData: {
        enable: true,
        pIdKey: "parentId"
      }
    }
  })
}


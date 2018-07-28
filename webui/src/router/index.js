import Vue from 'vue'
import Router from 'vue-router'

const App = () => import('@/App')
const Login = () => import('@/Login')
const RootRouterView = () => import('@/RootRouterView')
const UserPortal = () => import('@/components/UserPortal')
const serverInfoAdd = () => import('@/components/serverinfo/serverInfoAdd')
const serverInfoEdit = () => import('@/components/serverinfo/serverInfoEdit')
const serverInfoList = () => import('@/components/serverinfo/serverInfoList')
const userInfoAdd = () => import('@/components/userinfo/userInfoAdd')
const userInfoEdit = () => import('@/components/userinfo/userInfoEdit')
const userInfoList = () => import('@/components/userinfo/userInfoList')
const deviceInfoAdd = () => import('@/components/deviceinfo/deviceInfoAdd')
const deviceInfoEdit = () => import('@/components/deviceinfo/deviceInfoEdit')
const deviceInfoList = () => import('@/components/deviceinfo/deviceInfoList')
const communicationLineInfoAdd = () => import('@/components/communicationlineinfo/communicationLineInfoAdd')
const communicationLineInfoEdit = () => import('@/components/communicationlineinfo/communicationLineInfoEdit')
const communicationLineInfoList = () => import('@/components/communicationlineinfo/communicationLineInfoList')
const manageGroupInfoAdd = () => import('@/components/managegroupinfo/manageGroupInfoAdd')
const manageGroupInfoEdit = () => import('@/components/managegroupinfo/manageGroupInfoEdit')
const manageGroupInfoList = () => import('@/components/managegroupinfo/manageGroupInfoList')
const portInfoAdd = () => import('@/components/portinfo/portInfoAdd')
const portInfoEdit = () => import('@/components/portinfo/portInfoEdit')
const portInfoList = () => import('@/components/portinfo/portInfoList')
const portInfoCopy = () => import('@/components/portinfo/portInfoCopy')
const agentInfoAdd = () => import('@/components/agentinfo/agentInfoAdd')
const agentInfoEdit = () => import('@/components/agentinfo/agentInfoEdit')
const agentInfoList = () => import('@/components/agentinfo/agentInfoList')
const venderInfoList = () => import('@/components/venderinfo/venderInfoList')
const codeInfoList = () => import('@/components/codeinfo/codeInfoList')
const zLogInfoAdd = () => import('@/components/zloginfo/zLogInfoAdd')
const zLogInfoEdit = () => import('@/components/zloginfo/zLogInfoEdit')
const zLogInfoList = () => import('@/components/zloginfo/zLogInfoList')
const zAgentInfoAdd = () => import('@/components/zagentinfo/zAgentInfoAdd')
const zAgentInfoEdit = () => import('@/components/zagentinfo/zAgentInfoEdit')
const zAgentInfoList = () => import('@/components/zagentinfo/zAgentInfoList')
const zMibInfoAdd = () => import('@/components/zmibinfo/zMibInfoAdd')
const zMibInfoEdit = () => import('@/components/zmibinfo/zMibInfoEdit')
const zMibInfoList = () => import('@/components/zmibinfo/zMibInfoList')
const zMibOidAdd = () => import('@/components/zmiboid/zMibOidAdd')
const zMibOidEdit = () => import('@/components/zmiboid/zMibOidEdit')
const zMibOidList = () => import('@/components/zmiboid/zMibOidList')
const zAgentColumnInfoAdd = () => import('@/components/zagentcolumninfo/zAgentColumnInfoAdd')
const zAgentColumnInfoEdit = () => import('@/components/zagentcolumninfo/zAgentColumnInfoEdit')
const venderDetailInfoList = () => import('@/components/venderinfo/venderDetailInfoList')
const releaseInfoAdd = () => import('@/components/releaseinfo/releaseInfoAdd')
const releaseInfoEdit = () => import('@/components/releaseinfo/releaseInfoEdit')
const releaseInfoList = () => import('@/components/releaseinfo/releaseInfoList')
const policyMaintenanceAdd = () => import('@/components/policymaintenance/policyMaintenanceAdd')
const policyMaintenanceEdit = () => import('@/components/policymaintenance/policyMaintenanceEdit')
const policyMaintenanceList = () => import('@/components/policymaintenance/policyMaintenanceList')
const policyPeriodAdd = () => import('@/components/policyperiod/policyPeriodAdd')
const policyPeriodEdit = () => import('@/components/policyperiod/policyPeriodEdit')
const policyPeriodList = () => import('@/components/policyperiod/policyPeriodList')
const operationPolicyInfoAdd = () => import('@/components/operationpolicyinfo/operationPolicyInfoAdd')
const operationPolicyInfoEdit = () => import('@/components/operationpolicyinfo/operationPolicyInfoEdit')
const operationPolicyInfoList = () => import('@/components/operationpolicyinfo/operationPolicyInfoList')
const operationPolicyParamAdd = () => import('@/components/operationpolicyparam/operationPolicyParamAdd')
const operationPolicyParamEdit = () => import('@/components/operationpolicyparam/operationPolicyParamEdit')
const operationPolicyParamList = () => import('@/components/operationpolicyparam/operationPolicyParamList')
const policyCustomAdd = () => import('@/components/policycustom/policyCustomAdd')
const policyCustomEdit = () => import('@/components/policycustom/policyCustomEdit')
const policyCustomList = () => import('@/components/policycustom/policyCustomList')
const operationPolicyDeployment = () => import('@/components/operationpolicyinfo/operationPolicyDeployment')
const superDatasourceAdd = () => import('@/components/superdatasource/superDatasourceAdd')
const superDatasourceEdit = () => import('@/components/superdatasource/superDatasourceEdit')
const superDatasourceList = () => import('@/components/superdatasource/superDatasourceList')
const superUserList = () => import('@/components/superuser/superUserList')
const superUserGroupAdd = () => import('@/components/superusergroup/superUserGroupAdd')
const superUserGroupEdit = () => import('@/components/superusergroup/superUserGroupEdit')
const superUserGroupList = () => import('@/components/superusergroup/superUserGroupList')
const superLogoList = () => import('@/components/superlogo/superLogoList')
const superSeverityAdd = () => import('@/components/superseverity/superSeverityAdd')
const superSeverityEdit = () => import('@/components/superseverity/superSeverityEdit')
const superSeverityList = () => import('@/components/superseverity/superSeverityList')
const superAutomationAdd = () => import('@/components/superautomation/superAutomationAdd')
const superAutomationEdit = () => import('@/components/superautomation/superAutomationEdit')
const superAutomationList = () => import('@/components/superautomation/superAutomationList')
const superLogManagementList = () => import('@/components/superlogmanagement/superLogManagementList')
const superRoleAdd = () => import('@/components/superrole/superRoleAdd')
const superRoleEdit = () => import('@/components/superrole/superRoleEdit')
const superRoleList = () => import('@/components/superrole/superRoleList')

const schedule = () => import('@/components/schedule/schedule')

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      component: Login
    },
    {
      path: '/main',
      component: App,
      meta: {
        breadcrumb: '主页'
      },
      children: [
        {
          path: '',
          name: 'UserPortal',
          component: UserPortal,
          meta: {
            breadcrumb: '用户Portal',
          }
        }, {
          path: 'serverInfo',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'serverInfo',
              component: serverInfoList,
            }, {
              path: 'add',
              name: 'serverInfoAdd',
              component: serverInfoAdd,
              meta: {
                breadcrumb: '新增服务器信息',
              }
            }, {
              path: 'edit',
              name: 'serverInfoEdit',
              component: serverInfoEdit,
              meta: {
                breadcrumb: '编辑服务器信息',
              }
            }
          ],
          meta: {
            breadcrumb: '服务器信息管理',
          }
        }, {
          path: 'userInfo',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'userInfo',
              component: userInfoList,
            }, {
              path: 'add',
              name: 'userInfoAdd',
              component: userInfoAdd,
              meta: {
                breadcrumb: '新增联系人',
              }
            }, {
              path: 'edit',
              name: 'userInfoEdit',
              component: userInfoEdit,
              meta: {
                breadcrumb: '编辑联系人',
              }
            }
          ],
          meta: {
            breadcrumb: '联系人管理',
          }
        }, {
          path: 'deviceInfo',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'deviceInfo',
              component: deviceInfoList,
            }, {
              path: 'add',
              name: 'deviceInfoAdd',
              component: deviceInfoAdd,
              meta: {
                breadcrumb: '新增网络设备',
              }
            }, {
              path: 'edit',
              name: 'deviceInfoEdit',
              component: deviceInfoEdit,
              meta: {
                breadcrumb: '编辑网络设备',
              }
            }
          ],
          meta: {
            breadcrumb: '网络设备管理',
          }
        }, {
          path: 'communicationLineInfo',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'communicationLineInfo',
              component: communicationLineInfoList,
            }, {
              path: 'add',
              name: 'communicationLineInfoAdd',
              component: communicationLineInfoAdd,
              meta: {
                breadcrumb: '新增线路',
              }
            }, {
              path: 'edit',
              name: 'communicationLineInfoEdit',
              component: communicationLineInfoEdit,
              meta: {
                breadcrumb: '编辑线路',
              }
            }
          ],
          meta: {
            breadcrumb: '线路管理',
          }
        }, {
          path: 'manageGroupInfo',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'manageGroupInfo',
              component: manageGroupInfoList,
            }, {
              path: 'add',
              name: 'manageGroupInfoAdd',
              component: manageGroupInfoAdd,
              meta: {
                breadcrumb: '新增管理组',
              }
            }, {
              path: 'edit',
              name: 'manageGroupInfoEdit',
              component: manageGroupInfoEdit,
              meta: {
                breadcrumb: '编辑管理组',
              }
            }
          ],
          meta: {
            breadcrumb: '管理组',
          }
        }, {
          path: 'portInfo',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'portInfo',
              component: portInfoList,
            }, {
              path: 'add',
              name: 'portInfoAdd',
              component: portInfoAdd,
              meta: {
                breadcrumb: '新增重要端口',
              }
            }, {
              path: 'edit',
              name: 'portInfoEdit',
              component: portInfoEdit,
              meta: {
                breadcrumb: '编辑重要端口',
              }
            },
            {
              path: 'copy',
              name: 'portInfoCopy',
              component: portInfoCopy,
              meta: {
                breadcrumb: '复制重要端口',
              }
            }
          ],
          meta: {
            breadcrumb: '重要端口管理',
          }
        },{
          path: 'venderInfo',
          component: RootRouterView,
          children: [
            {
              path: 'venderdetailinfo',
              name: 'venderDetailInfoList',
              component: venderDetailInfoList,
              meta: {
                breadcrumb: '设备库详情',
              }
            }, {
              path: '',
              name: 'venderInfo',
              component: venderInfoList
            },
          ],
          meta: {
            breadcrumb: '设备库',
          }
        }, {
          path: 'codeInfo',
          name: 'codeInfo',
          component: codeInfoList,
          meta: {
            breadcrumb: '数据字典',
          }
        },
        {
          path: 'zLogInfo',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'zLogInfoAdd',
              component: zLogInfoAdd,
              meta: {
                breadcrumb: '新增LogInfo'
              }
            }, {
              path: 'edit',
              name: 'zLogInfoEdit',
              component: zLogInfoEdit,
              meta: {
                breadcrumb: '编辑LogInfo理'
              }
            }, {
              path: '',
              name: 'zLogInfo',
              component: zLogInfoList
            }
          ],
          meta: {
            breadcrumb: 'LogInfo管理',
          }
        }, {
          path: 'zAgentInfo',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'zAgentInfoAdd',
              component: zAgentInfoAdd,
              meta: {
                breadcrumb: '新增AgentInfo',
              }
            }, {
              path: 'edit',
              component: RootRouterView,
              children: [
                {
                  path: 'zAgentColumnInfo',
                  component: RootRouterView,
                  children: [
                    {
                      path: 'add',
                      name: 'zAgentColumnInfoAdd',
                      component: zAgentColumnInfoAdd,
                      meta: {
                        breadcrumb: '新增AgentColumnInfo',
                      }
                    }, {
                      path: 'edit',
                      name: 'zAgentColumnInfoEdit',
                      component: zAgentColumnInfoEdit,
                      meta: {
                        breadcrumb: '编辑AgentColumnInfo',
                      }
                    }
                  ]
                }, {
                  path: '',
                  name: 'zAgentInfoEdit',
                  component: zAgentInfoEdit,
                }
              ],
              meta: {
                breadcrumb: '编辑AgentInfo',
              }
            }, {
              path: '',
              name: 'zAgentInfo',
              component: zAgentInfoList
            }
          ],
          meta: {
            breadcrumb: 'AgentInfo管理',
          }
        }, {
          path: 'zMibInfo',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'zMibInfoAdd',
              component: zMibInfoAdd
            }, {
              path: 'edit',
              name: 'zMibInfoEdit',
              component: zMibInfoEdit
            }, {
              path: '',
              name: 'zMibInfo',
              component: zMibInfoList
            }, {
              path: 'zMibOid',
              component: RootRouterView,
              children: [
                {
                  path: 'add',
                  name: 'zMibOidAdd',
                  component: zMibOidAdd
                }, {
                  path: 'edit',
                  name: 'zMibOidEdit',
                  component: zMibOidEdit
                }, {
                  path: '',
                  name: 'zMibOid',
                  component: zMibOidList
                }
              ]
            }
          ]
        }, {
          path: 'releaseInfo',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'releaseInfoAdd',
              component: releaseInfoAdd,
              meta: {
                breadcrumb: '新增生效',
              }
            }, {
              path: 'edit',
              name: 'releaseInfoEdit',
              component: releaseInfoEdit,
              meta: {
                breadcrumb: '编辑生效',
              }
            }, {
              path: '',
              name: 'releaseInfo',
              component: releaseInfoList
            }
          ],
          meta: {
            breadcrumb: '生效管理',
          }
        }, {
          path: 'policyMaintenance',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'policyMaintenanceAdd',
              component: policyMaintenanceAdd,
              meta: {
                breadcrumb: '新增维护期',
              }
            }, {
              path: 'edit',
              name: 'policyMaintenanceEdit',
              component: policyMaintenanceEdit,
              meta: {
                breadcrumb: '编辑维护期',
              }
            }, {
              path: '',
              name: 'policyMaintenance',
              component: policyMaintenanceList
            }
          ],
          meta: {
            breadcrumb: '维护期管理',
          }
        }, {
          path: 'policyPeriod',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'policyPeriodAdd',
              component: policyPeriodAdd,
              meta: {
                breadcrumb: '新增监控时段',
              }
            }, {
              path: 'edit',
              name: 'policyPeriodEdit',
              component: policyPeriodEdit,
              meta: {
                breadcrumb: '编辑监控时段',
              }
            }, {
              path: '',
              name: 'policyPeriod',
              component: policyPeriodList
            },
          ],
          meta: {
            breadcrumb: '监控时段管理',
          }
        }, {
          path: 'operationPolicyInfo',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'operationPolicyInfoAdd',
              component: operationPolicyInfoAdd,
              meta: {
                breadcrumb: '新增策略',
              }
            }, {
              path: 'edit',
              name: 'operationPolicyInfoEdit',
              component: operationPolicyInfoEdit,
              meta: {
                breadcrumb: '编辑策略',
              }
            }, {
              path: '',
              name: 'operationPolicyInfo',
              component: operationPolicyInfoList
            }, {
              path: 'operationPolicyDeployment',
              name: 'operationPolicyDeployment',
              component: operationPolicyDeployment,
              meta: {
                breadcrumb: '策略分配',
              }
            }, {
              path: 'operationPolicyParam',
              component: RootRouterView,
              children: [
                {
                  path: 'add',
                  name: 'operationPolicyParamAdd',
                  component: operationPolicyParamAdd,
                  meta: {
                    breadcrumb: '策略参数新增',
                  }
                }, {
                  path: 'edit',
                  name: 'operationPolicyParamEdit',
                  component: operationPolicyParamEdit,
                  meta: {
                    breadcrumb: '策略参数编辑'
                  }
                }, {
                  path: '',
                  name: 'operationPolicyParam',
                  component: operationPolicyParamList
                },
              ]
            }
          ]
        }, {
          path: 'policyCustom',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'policyCustomAdd',
              component: policyCustomAdd,
              meta: {
                breadcrumb: '新增级别阈值微调',
              }
            }, {
              path: 'edit',
              name: 'policyCustomEdit',
              component: policyCustomEdit,
              meta: {
                breadcrumb: '编辑级别阈值微调',
              }
            }, {
              path: '',
              name: 'policyCustom',
              component: policyCustomList
            }
          ],
          meta: {
            breadcrumb: '级别阈值微调',
          }
        },
        {
          path: 'superDatasource',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'superDatasource',
              component: superDatasourceList,
            }, {
              path: 'add',
              name: 'superDatasourceAdd',
              component: superDatasourceAdd,
              meta: {
                breadcrumb: '新增数据源信息',
              }
            }, {
              path: 'edit',
              name: 'superDatasourceEdit',
              component: superDatasourceEdit,
              meta: {
                breadcrumb: '编辑数据源信息',
              }
            }
          ],
          meta: {
            breadcrumb: '数据源信息管理',
          }
        }, {
          path: 'superUser',
          name: 'superUser',
          component: superUserList,
          meta: {
            breadcrumb: '用户',
          }
        }, {
          path: 'superUserGroup',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'superUserGroupAdd',
              component: superUserGroupAdd,
              meta: {
                breadcrumb: '新增用户组信息',
              }
            }, {
              path: 'edit',
              name: 'superUserGroupEdit',
              component: superUserGroupEdit,
              meta: {
                breadcrumb: '编辑用户组信息',
              }
            }, {
              path: '',
              name: 'superUserGroup',
              component: superUserGroupList
            }
          ],
          meta: {
            breadcrumb: '用户组信息管理',
          }
        }, {
          path: 'superLogo',
          name: 'superLogo',
          component: superLogoList,
          meta: {
            breadcrumb: 'Logo设置',
          }
        }, {
          path: 'superSeverity',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'superSeverityAdd',
              component: superSeverityAdd,
              meta: {
                breadcrumb: '新增级别信息',
              }
            }, {
              path: 'edit',
              name: 'superSeverityEdit',
              component: superSeverityEdit,
              meta: {
                breadcrumb: '编辑级别信息',
              }
            }, {
              path: '',
              name: 'superSeverity',
              component: superSeverityList
            }
          ],
          meta: {
            breadcrumb: '级别设定',
          }
        }, {
          path: 'superAutomation',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'superAutomationAdd',
              component: superAutomationAdd,
              meta: {
                breadcrumb: '新增程序信息',
              }
            }, {
              path: 'edit',
              name: 'superAutomationEdit',
              component: superAutomationEdit,
              meta: {
                breadcrumb: '编辑程序信息',
              }
            }, {
              path: '',
              name: 'superAutomation',
              component: superAutomationList,
            }
          ],
          meta: {
            breadcrumb: '程序信息',
          }
        },
        {
          path: 'superLogManagement',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'superLogManagement',
              component: superLogManagementList,
            }
          ],
          meta: {
            breadcrumb: '日志信息',
          }
        },
        {
          path: 'superRole',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'superRole',
              component: superRoleList,
            }, {
              path: 'edit',
              name: 'superRoleEdit',
              component: superRoleEdit,
              meta: {
                breadcrumb: '编辑角色',
              }
            }, {
              path: 'add',
              name: 'superRoleAdd',
              component: superRoleAdd,
              meta: {
                breadcrumb: '新增角色',
              }
            }
          ],
          meta: {
            breadcrumb: '角色管理',
          }
        },
        {
          path: 'agentInfo',
          component: RootRouterView,
          children: [
            {
              path: 'add',
              name: 'agentInfoAdd',
              component: agentInfoAdd,
              meta: {
                breadcrumb: '新增代理信息',
              }
            }, {
              path: 'edit',
              name: 'agentInfoEdit',
              component: agentInfoEdit,
              meta: {
                breadcrumb: '编辑代理信息',
              }
            },{
              path: '',
              name: 'agentInfo',
              component: agentInfoList,
            }
          ],
          meta: {
            icon: 'dashboard',
            breadcrumb: '代理信息',
            isMenu: true
          }
        },
        {
          path: 'schedule',
          component: RootRouterView,
          children: [
            {
              path: '',
              name: 'schedule',
              component: schedule,
            }
          ],
          meta: {
            breadcrumb: '定时任务信息',
          }
        }
      ]
    }
  ]
})

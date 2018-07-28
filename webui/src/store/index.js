/*
 * Copyright (C) Unpublished JiuDaoTech Software, Inc. All rights reserved.
 * JiuDaoTech Software, Inc., Confidential and Proprietary.
 *
 * This software is subject to copyright protection
 *   under the laws of the Public of China and other countries.
 *
 * Unless otherwise explicitly stated, this software is provided
 *   by JiuDaoTech "AS IS".
 */

import Vue from 'vue'
import Vuex from 'vuex'
import snackbar from './modules/snackbar'
import serverinfo from './modules/serverinfo'
import userinfo from './modules/userinfo'
import deviceinfo from './modules/deviceinfo'
import communicationlineinfo from './modules/communicationlineinfo'
import managegroupinfo from './modules/managegroupinfo'
import portinfo from './modules/portinfo'
import agentinfo from './modules/agentinfo'
import venderinfo from './modules/venderinfo'
import zloginfo from './modules/zloginfo'
import zagentinfo from './modules/zagentinfo'
import zmibinfo from './modules/zmibinfo'
import zmiboid from './modules/zmiboid'
import zagentcolumninfo from './modules/zagentcolumninfo'
import venderdetailinfo from './modules/venderdetailinfo'
import releaseinfo from './modules/releaseinfo'
import codeinfo from './modules/codeinfo'
import policymaintenance from './modules/policymaintenance'
import policyperiod from './modules/policyperiod'
import operationpolicyinfo from './modules/operationpolicyinfo'
import operationpolicyparam from './modules/operationpolicyparam'
import policycustom from './modules/policycustom'
import superdatasource from './modules/superdatasource'
import superuser from './modules/superuser'
import superusergroup from './modules/superusergroup'
import superlogo from './modules/superlogo'
import superseverity from './modules/superseverity'
import superautomation from './modules/superautomation'
import superlogmanagement from './modules/superlogmanagement'
import superrole from './modules/superrole'

import * as actions from './actions'
import * as getters from './getters'
import * as mutations from './mutations'
import * as state from './state'

Vue.use(Vuex)

export default new Vuex.Store({
  actions,
  mutations,
  getters,
  state,
  modules: {
    snackbar,
    serverinfo,
    userinfo,
    deviceinfo,
    communicationlineinfo,
    managegroupinfo,
    portinfo,
    agentinfo,
    venderinfo,
    codeinfo,
    zloginfo,
    zagentinfo,
    zmibinfo,
    zmiboid,
    zagentcolumninfo,
    venderdetailinfo,
    releaseinfo,
    policymaintenance,
    policyperiod,
    operationpolicyinfo,
    operationpolicyparam,
    policycustom,
    superdatasource,
    superuser,
    superusergroup,
    superlogo,
    superseverity,
    superautomation,
    superlogmanagement,
    superrole,
  }
})

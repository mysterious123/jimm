/*
 * Copyright (C) Unpublished JiuDaoTech Software, Inc. All rights reserved.
 * JiuDaoTech Software, Inc., Confidential and Proprietary.
 *
 * This software is subject to copyright protection
 *   under the laws of the Public of China and other countries.
 *
 * Unless otherwise explicitly stated, this software is provided
 *   by JiuDaoTech "AS IS".
 *
 */
const state={
  snackbarVisible:false,
  y:'top',
  x:null,
  mode:'',
  timeout:3000,
  text:'提示',
  color:'info'
}

const mutations={
  closeSnackbar(state){
    state.snackbarVisible = false;
  },
  simpleInfo(state, infoText){
    state.snackbarVisible = true;
    state.color = 'info';
    state.text = infoText;
  },
  simpleSuccess(state, successText){
    state.snackbarVisible = true;
    state.color = 'success';
    state.text = successText;
  },
  simpleError(state, errorText){
    state.snackbarVisible = true;
    state.color = 'error';
    state.text = errorText;
  },

  advancedSnackbar(state, params){
    state.snackbarVisible = true;
    state.y = parames.y || 'top';
    state.x = parames.x || null;
    state.mode = params.mode || '';
    state.timeout = params.timeout || 5000;
    state.text = params.text || '';
    state.color = params.color || 'info';
  }
}

export default{
  namespaced: true,
  state,
  mutations
}

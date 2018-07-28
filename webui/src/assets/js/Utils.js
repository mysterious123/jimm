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

import _axios from "axios";
import store from '@/store';
import router from '@/router';


/**
 * 获取datepicker options
 * @param type
 */
export function getDatePickerOptions(params) {
  return {
    type: params.type || 'day',// 'day', 'min', 'multi-day'
    week: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
    month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
    format: params.formatter || 'YYYY-MM-DD HH:mm:ss',
    placeholder: params.placeholder || '',
    inputStyle: {
      'display': 'inline-block',
      'padding': '6px',
      'line-height': '20px',
      'font-size': '16px',
      'border-bottom': params.inputStyleBorderBottom || '1px solid grey',
      'border-radius': '2px',
      'color': '#5F5F5F',
      'cursor': 'pointer'
    },
    color: {
      checked: '#F50057',
      header: '#3f51b5',
      headerText: '#fff'
    },
    buttons: {
      ok: '确定',
      cancel: '取消'
    },
    overlayOpacity: 0.5, // 0.5 as default
    dismissible: true // as true as default
  };
};

Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1,                 //月份
    "d+": this.getDate(),                    //日
    "h+": this.getHours(),                   //小时
    "m+": this.getMinutes(),                 //分
    "s+": this.getSeconds(),                 //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds()             //毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
  }
  return fmt;
}

/**
 * 根据时间参数计算开始时间
 * @param timeStr
 * @returns {string}
 */
export function getStartRangeTime(timeStr) {
  var end = timeStr.charAt(timeStr.length - 1);
  var start = timeStr.substring(0, timeStr.length - 1);
  var nowDateStart = '';
  var newDate = new Date();
  if (end == "d") {//天计算
    nowDateStart = new Date(newDate.setDate(newDate.getDate() - start));
  } else if (end == "h") {//小时计算
    nowDateStart = new Date(newDate.setHours(newDate.getHours() - start));
  } else if (end == "M") {//月
    nowDateStart = new Date(newDate.setMonth(newDate.getMonth() - start));
  } else if (end == "y") {//年
    nowDateStart = new Date(newDate.setMonth(newDate.getMonth() - start * 12));
  } else if (end == "m") {//分钟
    nowDateStart = new Date(newDate.setMinutes(newDate.getMinutes() - start));
  }
  return nowDateStart;
};

export function setDateTimeRange(params) {
  var formatter = params.formatter || "yyyy-MM-dd hh:mm:ss";
  return getStartRangeTime(params.rangStr).format(formatter) + " - " + new Date().format(formatter);
}

/**
 * 异步通信模块全局配置
 */
export function axios(config){
  var defaultConfig = {
    baseURL: '/core',
    timeout: 30000
  };
  if(config){
    defaultConfig = _.defaults(config,defaultConfig)
  }
  var __axios = _axios.create(defaultConfig);

  //定义全局错误处理
  __axios.interceptors.response.use((response) => {
    return response;
  },(error) =>{
    if(error.response) {
      if (error.response.status === 400) {
        store.commit('snackbar/simpleError', "后台校验数据格式错误");
      }
      if (error.response.status === 999) {
        store.commit('snackbar/simpleError', "会话超时请重新登陆");
        router.app.$session.destroy();
        router.push("/");
      }
      if (error.response.status === 401) {
        store.commit('snackbar/simpleError', error.response.data);
      }
    }
    return Promise.reject(error.response);
  });

  return __axios;
}

export function cacheCode(){
  if(!window.jlog_code){
    return axios().get("/code").then(function (response) {
      window.jlog_code = response.data;
    })
  }else{
    return new Promise((resolve, reject) => {
      resolve();
    })
  }
}

export function getFormatDate(longTime)
{
  var date = new Date(longTime);
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
  var D = date.getDate() + ' ';
  var h = date.getHours() + ':';
  var m = date.getMinutes() + ':';
  var s = date.getSeconds();
  return Y + M + D + h + m + s;
}


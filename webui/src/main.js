import Vue from 'vue'
import RootRouterView from './RootRouterView.vue'
import zh_cn from 'vee-validate/dist/locale/zh_CN'
import Vuetify from 'vuetify'
import VeeValidate, {Validator} from 'vee-validate'
import router from './router'
import {cacheCode} from '@/assets/js/Utils';
import store from '@/store'
import promisefinally from 'promise.prototype.finally'
import VueSession from 'vue-session'

promisefinally.shim();
Validator.localize('zh_cn', zh_cn);

Vue.use(VueSession)
Vue.use(Vuetify)
Vue.use(VeeValidate, {
  errorBagName: 'vee_errors',
  events: "input|blur"
})

Vue.mixin({
  methods: {
    /**
     * 判断是否拥有该权限
     * @param permission
     * @returns {boolean}
     */
    isPermitted : function (permission) {
      try {
        let superPermissions = router.app.$session.get("user").superPermissions;
        if(_.filter(superPermissions,{permission: permission}).length !== 0){
          return true
        }
      }catch (error){
        return false
      }
      return false
    },
    /**
     * 创建一个通用组件配置的对象，目前包含“数据表”参数配置
     * @returns {*}
     */
    getCommonComponentConfig: function () {
      var commonComponentConfig = {
        dataTableConfig: {
          dataTable_noDataText: '暂无数据',
          dataTable_noResultsText: '无匹配数据',
          dataTable_rowsPerPageText: '每页显示条数',
          dataTable_pagination: {
            descending: false,
            page: 1,
            rowsPerPage: 10,
            sortBy: null,
            totalItems: 0
          }
        }
      };
      return _.cloneDeep(commonComponentConfig);
    },
    data_table_pagination: function () {
      return {
        sortBy: 'name',
        rowsPerPage: 11,
        page: 1,
        descending: false,
        totalItems: 0
      };
    },
    closeSnackbar: function () {
      this.$store.commit('snackbar/closeSnackbar')
    },
    simpleInfo: function (msg) {
      this.$store.commit('snackbar/simpleInfo', msg)
    },
    simpleError: function (msg) {
      this.$store.commit('snackbar/simpleError', msg)
    },
    simpleSuccess: function (msg) {
      this.$store.commit('snackbar/simpleSuccess', msg)
    },
    advancedSnackbar: function (params) {
      this.$store.commit('snackbar/advancedSnackbar', params)
    },
    translationCode: function (param) {
      var code = _.filter(window.jlog_code, param)[0];
      return code ? code.name : '';
    },
    pagerouter: function (param) {
      router.push(param)
    }
  }
})



router.beforeEach((to, from, next) => {
  if(router.app.$session.exists() && router.app.$session.get("isLogin")){
    if(to.path === '/'){
      next({name : "UserPortal"});
    }else{
      next()
    }
  }else{
    if(to.path=='/'){
      next();
    }else{
      next('/');
    }
  }
})

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(RootRouterView)
})




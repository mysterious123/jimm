<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superAutomation'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">程序编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="应用名"
        v-model="updateSuperAutomation.appName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('应用名','superautomation_update')[0]||[]"
        data-vv-name="应用名"
        data-vv-scope="superautomation_update"
      ></v-text-field>
      <v-text-field
        label="程序名"
        v-model="updateSuperAutomation.programmeName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('程序名','superautomation_update')[0]||[]"
        data-vv-name="程序名"
        data-vv-scope="superautomation_update"
      ></v-text-field>
      <!--<v-text-field-->
      <!--label="数据源名"-->
      <!--v-model="updateSuperAutomation.datasource"-->
      <!--v-validate="'required'"-->
      <!--:error-messages="vee_errors.collect('数据源名','superautomation_update')[0]||[]"-->
      <!--data-vv-name="数据源名"-->
      <!--data-vv-scope="superautomation_update"-->
      <!--&gt;</v-text-field>-->
      <v-switch label="状态" v-model="updateSuperAutomation.status"></v-switch>
      <!--<v-text-field-->
      <!--label="状态"-->
      <!--v-model="updateSuperAutomation.status"-->
      <!--v-validate="'required'"-->
      <!--:error-messages="vee_errors.collect('状态','superautomation_update')[0]||[]"-->
      <!--data-vv-name="状态"-->
      <!--data-vv-scope="superautomation_update"-->
      <!--&gt;</v-text-field>-->

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superautomation_update()">
        <v-icon>check</v-icon>
        更新

      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.superautomation.updateSuperAutomation) {
        next({name : 'superAutomation'})
      } else {
        next();
      }
    },
    computed: {
      ...mapState('superautomation', ['updateSuperAutomation']),
    },
    methods: {
      superautomation_update(){
        this.$validator.validateAll("superautomation_update").then((result) => {
          if (result) {
            axios().put("/superAutomation", this.updateSuperAutomation).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'superAutomation'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

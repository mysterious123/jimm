<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superDatasource'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">数据源编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="数据源类型"
        v-model="updateSuperDatasource.datasourceType"
        :v-data-params="{type : 'datasource_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('数据源类型','superdatasource_update')[0]||[]"
        data-vv-name="数据源类型"
        data-vv-scope="superdatasource_update"
      ></async-select>
      <async-select
        label="数据库类型"
        v-model="updateSuperDatasource.dbType"
        :v-data-params="{type : 'db_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('数据库类型','superdatasource_update')[0]||[]"
        data-vv-name="数据库类型"
        data-vv-scope="superdatasource_update"
      ></async-select>
      <v-text-field
        label="服务器IP"
        v-model="updateSuperDatasource.serverIp"
        v-validate="'required'"
        :error-messages="vee_errors.collect('服务器IP','superdatasource_update')[0]||[]"
        data-vv-name="服务器IP"
        data-vv-scope="superdatasource_update"
      ></v-text-field>
      <v-text-field
        label="数据库名称"
        v-model="updateSuperDatasource.databaseName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('数据库名称','superdatasource_update')[0]||[]"
        data-vv-name="数据库名称"
        data-vv-scope="superdatasource_update"
      ></v-text-field>
      <v-text-field
        label="端口"
        v-model="updateSuperDatasource.portNum"
        v-validate="'required'"
        :error-messages="vee_errors.collect('端口','superdatasource_update')[0]||[]"
        data-vv-name="端口"
        data-vv-scope="superdatasource_update"
      ></v-text-field>
      <v-text-field
        label="用户名"
        v-model="updateSuperDatasource.userName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('用户名','superdatasource_update')[0]||[]"
        data-vv-name="用户名"
        data-vv-scope="superdatasource_update"
      ></v-text-field>
      <v-text-field
        label="密码"
        type="password"
        v-model="updateSuperDatasource.password"
        v-validate="'required'"
        :error-messages="vee_errors.collect('密码','superdatasource_update')[0]||[]"
        data-vv-name="密码"
        data-vv-scope="superdatasource_update"
      ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superdatasource_update()">
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
  import asyncSelect from '@/components/common/asyncSelect'

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.superdatasource.updateSuperDatasource) {
        next('/superDatasource')
      } else {
        next();
      }
    },
    computed: {
      ...mapState('superdatasource', ['updateSuperDatasource']),
    },
    components: {
      "async-select": asyncSelect
    },
    methods: {
      superdatasource_update(){
        this.$validator.validateAll("superdatasource_update").then((result) => {
          if (result) {
            axios().put("/superDatasource", this.updateSuperDatasource).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'superDatasource'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

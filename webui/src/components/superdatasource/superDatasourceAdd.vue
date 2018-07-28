<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superDatasource'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">数据源新增</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="数据源类型"
        v-model="newSuperDatasource.datasourceType"
        :v-data-params="{type : 'datasource_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('数据源类型','superdatasource_insert')[0]||[]"
        data-vv-name="数据源类型"
        data-vv-scope="superdatasource_insert"
      ></async-select>
      <async-select
        label="数据库类型"
        v-model="newSuperDatasource.dbType"
        :v-data-params="{type : 'db_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('数据库类型','superdatasource_insert')[0]||[]"
        data-vv-name="数据库类型"
        data-vv-scope="superdatasource_insert"
      ></async-select>
      <v-text-field
        label="服务器IP"
        v-model="newSuperDatasource.serverIp"
        v-validate="'required'"
        :error-messages="vee_errors.collect('服务器IP','superdatasource_insert')[0]||[]"
        data-vv-name="服务器IP"
        data-vv-scope="superdatasource_insert"
      ></v-text-field>
      <v-text-field
        label="数据库名称"
        v-model="newSuperDatasource.databaseName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('数据库名称','superdatasource_insert')[0]||[]"
        data-vv-name="数据库名称"
        data-vv-scope="superdatasource_insert"
      ></v-text-field>
      <v-text-field
        label="端口"
        v-model="newSuperDatasource.portNum"
        v-validate="'required'"
        :error-messages="vee_errors.collect('端口','superdatasource_insert')[0]||[]"
        data-vv-name="端口"
        data-vv-scope="superdatasource_insert"
      ></v-text-field>
      <v-text-field
        label="用户名"
        v-model="newSuperDatasource.userName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('用户名','superdatasource_insert')[0]||[]"
        data-vv-name="用户名"
        data-vv-scope="superdatasource_insert"
      ></v-text-field>
      <v-text-field
        label="密码"
        type="password"
        v-model="newSuperDatasource.password"
        v-validate="'required'"
        :error-messages="vee_errors.collect('密码','superdatasource_insert')[0]||[]"
        data-vv-name="密码"
        data-vv-scope="superdatasource_insert"
      ></v-text-field>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="error" dark @click.stop="superdatasource_connecttest()">
        连接测试

      </v-btn>
      <v-btn color="success" dark @click.stop="superdatasource_insert()">
        <v-icon>check</v-icon>
        保存

      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect'

  var newSuperDatasource = {
    datasourceType: "",
    dbType: "",
    serverIp: "",
    databaseName: "",
    portNum: "",
    userName: "",
    password: "",
    status: "",
  }
  export default {
    data(){
      return {
        newSuperDatasource: _.cloneDeep(newSuperDatasource)
      }
    },
    components: {
      "async-select": asyncSelect
    },
    methods: {
      superdatasource_insert(){
        this.$validator.validateAll("superdatasource_insert").then((result) => {
          if (this.newSuperDatasource.status == 1) {
            if (result) {
              axios().post("/superDatasource", this.newSuperDatasource).then(() => {
                this.simpleInfo("操作成功")
                this.pagerouter({name: 'superDatasource'})
              }).catch((error) => {
                this.simpleError("后台异常,操作失败")
              })
            }
          } else {
            this.simpleError("请先测试连接")
          }
        });
      },
      superdatasource_connecttest(){
        this.$validator.validateAll("superdatasource_insert").then((result) => {
          if (result) {
            axios().put("/superDatasource/connectTest", this.newSuperDatasource).then(response => {
              //console.log(response.data);
              if (response.data == 1) {
                this.simpleError("连接失败！")
              } else {
                this.newSuperDatasource.status = 1;
                this.simpleInfo("连接成功！")
              }
            })
          }
        });
      }
    }
  }
</script>

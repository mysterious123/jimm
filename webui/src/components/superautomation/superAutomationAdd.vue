<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superAutomation'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增程序</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
    <v-text-field
       label="应用名"
       v-model="newSuperAutomation.appName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('应用名','superautomation_insert')[0]||[]"
       data-vv-name="应用名"
       data-vv-scope="superautomation_insert"
     ></v-text-field>
    <v-text-field
       label="程序名"
       v-model="newSuperAutomation.programmeName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('程序名','superautomation_insert')[0]||[]"
       data-vv-name="程序名"
       data-vv-scope="superautomation_insert"
     ></v-text-field>
    <!--<v-text-field-->
       <!--label="数据源名"-->
       <!--v-model="newSuperAutomation.datasource"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('数据源名','superautomation_insert')[0]||[]"-->
       <!--data-vv-name="数据源名"-->
       <!--data-vv-scope="superautomation_insert"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="状态"-->
       <!--v-model="newSuperAutomation.status"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('状态','superautomation_insert')[0]||[]"-->
       <!--data-vv-name="状态"-->
       <!--data-vv-scope="superautomation_insert"-->
     <!--&gt;</v-text-field>-->
      <v-switch label="状态" v-model="newSuperAutomation.status"></v-switch>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superautomation_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import {axios} from '@/assets/js/Utils';

  var newSuperAutomation = {
  appName : "",
  programmeName : "",
  datasource : "",
  status : true,
  }
  export default {
    data(){
      return {
        newSuperAutomation : _.cloneDeep(newSuperAutomation)
      }
    },
    methods:{
      superautomation_insert(){
        this.$validator.validateAll("superautomation_insert").then((result) => {
          if (result) {
            axios().post("/superAutomation",this.newSuperAutomation).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'superAutomation'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

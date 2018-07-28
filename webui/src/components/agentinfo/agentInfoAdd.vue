<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'agentInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增代理信息</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="代理名称"
        v-model="newAgentInfo.agentName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('代理名称','agent_insert')[0]||[]"
        data-vv-name="代理名称"
        data-vv-scope="agent_insert"
      ></v-text-field>
      <async-select
        label="代理类型"
        :v-data-params="{type : 'agent_type'}"
        v-model="newAgentInfo.agentType"
        data-vv-name="代理类型"
        data-vv-scope="agent_insert"
      ></async-select>
      <v-text-field
        label="版本"
        v-model="newAgentInfo.agentVersion"
        data-vv-name="版本"
        data-vv-scope="agent_insert"
      ></v-text-field>
      <v-text-field
        label="代理缩写"
        v-model="newAgentInfo.agentAb"
        data-vv-name="代理缩写"
        data-vv-scope="agent_insert"
      ></v-text-field>
      <div id="agentfileDropzone" class="dropzone">
      </div>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="agentinfo_insert()">
        <v-icon>check</v-icon>
        保存


      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect';
  import Dropzone from 'dropzone';
  import 'dropzone/dist/dropzone.css';

  var newAgentInfo = {
    agentName: "",
    agentType: "",
    agentAb: "",
    agentVersion: "",
    founder: "",
    agentRoute: "",
    createTime: "",
  }
  export default {
    data(){
      return {
        newAgentInfo: _.cloneDeep(newAgentInfo)
      }
    },
    components: {
      "async-select": asyncSelect,
    },
    mounted(){
      Dropzone.autoDiscover = false;
      var myDropzone = new Dropzone("#agentfileDropzone", {
        url: "/core/agentInfo/uploadAgentFile",
        addRemoveLinks: true,
        method: 'post',
        maxFilesize: 5,
        dictDefaultMessage:'点击上传代理',
        success: function (file, response, e) {
           vm.newAgentInfo.agentRoute = file.name;
        }
      });
    },
    methods: {
      agentinfo_insert(){
        this.$validator.validateAll("agentinfo_insert").then((result) => {
          if (result) {
            axios().post("/agentInfo", this.newAgentInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'agentInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

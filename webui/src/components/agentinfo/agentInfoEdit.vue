<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'agentInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">代理编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="代理名称"
        v-model="updateAgentInfo.agentName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('代理名称','agentinfo_update')[0]||[]"
        data-vv-name="代理名称"
        data-vv-scope="agentinfo_update"
      ></v-text-field>
      <async-select
        label="代理类型"
        :v-data-params="{type : 'agent_type'}"
        v-model="updateAgentInfo.agentType"
        data-vv-name="代理类型"
        data-vv-scope="agentinfo_update"
      ></async-select>
      <v-text-field
        label="代理缩写"
        v-model="updateAgentInfo.agentAb"
        data-vv-name="代理缩写"
        data-vv-scope="agentinfo_update"
      ></v-text-field>
      <v-text-field
        label="代理版本"
        v-model="updateAgentInfo.agentVersion"
        data-vv-name="代理版本"
        data-vv-scope="agentinfo_update"
      ></v-text-field>
      <div id="agentfileDropzone" class="dropzone">
      </div>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="agentinfo_update()">
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
  import asyncSelect from '@/components/common/asyncSelect';
  import Dropzone from 'dropzone';
  import 'dropzone/dist/dropzone.css'

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.agentinfo.updateAgentInfo) {
        next('/agentInfo')
      } else {
        next();
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
          //vm.newVenderInfo.logoRoute = file.name;
        }
      });
    },
    computed: {
      ...mapState('agentinfo', ['updateAgentInfo']),
    },
    methods: {
      agentinfo_update(){
        this.$validator.validateAll("agentinfo_update").then((result) => {
          if (result) {
            axios().put("/agentInfo", this.updateAgentInfo).then(() => {
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

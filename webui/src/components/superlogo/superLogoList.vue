<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-toolbar-title class="white--text">Logo设置</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="企业名称"
        v-model="newSuperLogo.name"
        v-validate="'required'"
        :error-messages="vee_errors.collect('企业名称','superlogo_insert')[0]||[]"
        data-vv-name="企业名称"
        data-vv-scope="superlogo_insert"
      ></v-text-field>
      <v-text-field
        label="企业简称"
        v-model="newSuperLogo.shortName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('企业简称','superlogo_insert')[0]||[]"
        data-vv-name="企业简称"
        data-vv-scope="superlogo_insert"
      ></v-text-field>
      <div id="logoDropzone"
           class="dropzone"
      >
      </div>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superlogo_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import Dropzone from 'dropzone';
  import 'dropzone/dist/dropzone.css'

  var newSuperLogo = {
    name : "",
    shortName : "",
    logoRoute : "",
  }
  export default {
    mounted() {
      var vm = this;
      Dropzone.autoDiscover = false;
      var myDropzone = new Dropzone("#logoDropzone", {
        url: "/core/superLogo/uploadLogo",
        addRemoveLinks: true,
        method: 'post',
        maxFilesize: 2,
        dictDefaultMessage:'点击上传Logo',
        success: function (file, response, e) {
          vm.newSuperLogo.logoRoute = "logo.png";
        }
      });
    },
    data(){
      return {
        newSuperLogo : _.cloneDeep(newSuperLogo)
      }
    },
    methods:{
      superlogo_insert(){
        this.$validator.validateAll("superlogo_insert").then((result) => {
          if (result) {
            axios().post("/superLogo",this.newSuperLogo).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'superLogo'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

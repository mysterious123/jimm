<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superSeverity'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">superseverityEdit</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
    <v-text-field
       label="级别"
       v-model="updateSuperSeverity.level"
       v-validate="'required'"
       :error-messages="vee_errors.collect('级别','superseverity_update')[0]||[]"
       data-vv-name="级别"
       data-vv-scope="superseverity_update"
     ></v-text-field>
    <v-text-field
       label="级别英文名"
       v-model="updateSuperSeverity.englishName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('级别英文名','superseverity_update')[0]||[]"
       data-vv-name="级别英文名"
       data-vv-scope="superseverity_update"
     ></v-text-field>
    <v-text-field
       label="级别中文名"
       v-model="updateSuperSeverity.chineseName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('级别中文名','superseverity_update')[0]||[]"
       data-vv-name="级别中文名"
       data-vv-scope="superseverity_update"
     ></v-text-field>
    <v-text-field
       label="级别颜色"
       v-model="updateSuperSeverity.levelColour"
       v-validate="'required'"
       :error-messages="vee_errors.collect('级别颜色','superseverity_update')[0]||[]"
       data-vv-name="级别颜色"
       data-vv-scope="superseverity_update"
     ></v-text-field>
    
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superseverity_update()">
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
      if(!store.state.superseverity.updateSuperSeverity) {
        next('/superSeverity')
      }else{
        next();
      }
    },
    computed: {
      ...mapState('superseverity', ['updateSuperSeverity']),
    },
    methods:{
      superseverity_update(){
        this.$validator.validateAll("superseverity_update").then((result) => {
          if (result) {
            axios().put("/superSeverity",this.updateSuperSeverity).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'superSeverity'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

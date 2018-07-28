<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superSeverity'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增级别</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="级别"
        v-model="newSuperSeverity.level"
        v-validate="'required'"
        :error-messages="vee_errors.collect('级别','superseverity_insert')[0]||[]"
        data-vv-name="级别"
        data-vv-scope="superseverity_insert"
      ></v-text-field>
      <v-text-field
        label="级别英文名"
        v-model="newSuperSeverity.englishName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('级别英文名','superseverity_insert')[0]||[]"
        data-vv-name="级别英文名"
        data-vv-scope="superseverity_insert"
      ></v-text-field>
      <v-text-field
        label="级别中文名"
        v-model="newSuperSeverity.chineseName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('级别中文名','superseverity_insert')[0]||[]"
        data-vv-name="级别中文名"
        data-vv-scope="superseverity_insert"
      ></v-text-field>
      <Photoshop v-model="colors"></photoshop>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superseverity_insert()">
        <v-icon>check</v-icon>
        保存


      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {Photoshop} from 'vue-color'

  var newSuperSeverity = {
    level: "",
    englishName: "",
    chineseName: "",
    levelColour: "",
  }
  export default {
    data(){
      return {
        newSuperSeverity: _.cloneDeep(newSuperSeverity),
        colors: {
          hex: '#194d33',
          hsl: {
            h: 150,
            s: 0.5,
            l: 0.2,
            a: 1
          },
          hsv: {
            h: 150,
            s: 0.66,
            v: 0.30,
            a: 1
          },
          rgba: {
            r: 25,
            g: 77,
            b: 51,
            a: 1
          },
          a: 1
        }
      }
    },
    components: {
      Photoshop
    },
    methods: {
      superseverity_insert(){
        this.$validator.validateAll("superseverity_insert").then((result) => {
          if (result) {
            this.newSuperSeverity.levelColour = this.colors.hex;
            axios().post("/superSeverity", this.newSuperSeverity).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'superSeverity'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'zMibInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增MIB指标信息</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="指标类型"
        :v-data-params="{type : 'index_type'}"
        v-model="newZMibInfo.indexType"
        data-vv-name="指标类型"
        data-vv-scope="zmibinfo_insert"
      ></async-select>
      <async-select
        label="厂家品牌"
        v-model="newZMibInfo.venderBrand"
        :v-data-url="'/venderInfo'"
        :v-data-params="{}"
        item-text="venderName"
        item-value="venderName"
        data-vv-name="厂家品牌"
        data-vv-scope="zmibinfo_insert"
      ></async-select>
      <v-text-field
        label="指标名称"
        v-model="newZMibInfo.indexName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('指标名称','zmibinfo_insert')[0]||[]"
        data-vv-name="指标名称"
        data-vv-scope="zmibinfo_insert"
      ></v-text-field>

      <v-text-field
        label="指标组"
        v-model="newZMibInfo.indexGroup"
        v-validate="'required'"
        :error-messages="vee_errors.collect('指标组','zmibinfo_insert')[0]||[]"
        data-vv-name="指标组"
        data-vv-scope="zmibinfo_insert"
      ></v-text-field>
      <v-text-field
        label="单位"
        v-model="newZMibInfo.indexUnit"
        v-validate="'required'"
        :error-messages="vee_errors.collect('单位','zmibinfo_insert')[0]||[]"
        data-vv-name="单位"
        data-vv-scope="zmibinfo_insert"
      ></v-text-field>
      <!--<v-text-field-->
        <!--label="计算公式"-->
        <!--v-model="newZMibInfo.calculationFormula"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('计算公式','zmibinfo_insert')[0]||[]"-->
        <!--data-vv-name="计算公式"-->
        <!--data-vv-scope="zmibinfo_insert"-->
      <!--&gt;</v-text-field>-->
      <!--<v-text-field-->
        <!--label="内容描述"-->
        <!--v-model="newZMibInfo.contentDescribe"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('内容描述','zmibinfo_insert')[0]||[]"-->
        <!--data-vv-name="内容描述"-->
        <!--data-vv-scope="zmibinfo_insert"-->
      <!--&gt;</v-text-field>-->

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zmibinfo_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect';

  var newZMibInfo = {
    indexType : "",
    indexName : "",
    venderBrand : "",
    indexGroup : "",
    calculationFormula : "",
    contentDescribe : "",
    indexUnit:""
  }
  export default {
    data(){
      return {
        newZMibInfo : _.cloneDeep(newZMibInfo),
      }
    },
    components: {
      "async-select": asyncSelect,
    },
    methods:{
      zmibinfo_insert(){
        this.$validator.validateAll("zmibinfo_insert").then((result) => {
          if (result) {
            axios().post("/zMibInfo",this.newZMibInfo).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'zMibInfo'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

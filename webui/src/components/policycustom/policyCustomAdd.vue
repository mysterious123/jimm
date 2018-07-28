<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'policyCustom'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增级别阈值微调</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="关键字"
        v-model="newPolicyCustom.keyName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('关键字','policycustom_insert')[0]||[]"
        data-vv-name="关键字"
        data-vv-scope="policycustom_insert"
      ></v-text-field>
      <async-select
        label="指标类型"
        :v-data-params="{type : 'index_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('指标类型','policycustom_insert')[0]||[]"
        v-model="newPolicyCustom.indexType"
        data-vv-name="指标类型"
        data-vv-scope="policycustom_insert"
        @change="newPolicyCustom.indexName = ''"
      ></async-select>
      <async-select
        label="指标名称"
        :v-data-url="'/zMibInfo'"
        :v-data-params="newPolicyCustom.indexType?{indexType : newPolicyCustom.indexType}:{indexType : -1}"
        :items="initIndexNameItems"
        @update="updateItems"
        v-model="newPolicyCustom.indexName"
        item-text="indexName"
        item-value="id"
        data-vv-name="指标名称"
        data-vv-scope="policycustom_insert">
      </async-select>
      <v-text-field
        label="阀值A"
        v-model="newPolicyCustom.thresholdA"
        v-validate="'required'"
        :error-messages="vee_errors.collect('阀值A','policycustom_insert')[0]||[]"
        data-vv-name="阀值A"
        data-vv-scope="policycustom_insert"
      ></v-text-field>
      <async-select
        label="级别A"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="chineseName"
        v-model="newPolicyCustom.levelA"
        data-vv-name="级别A"
        data-vv-scope="policycustom_insert"
      ></async-select>
      <v-text-field
        label="阀值B"
        v-model="newPolicyCustom.thresholdB"
        v-validate="'required'"
        :error-messages="vee_errors.collect('阀值B','policycustom_insert')[0]||[]"
        data-vv-name="阀值B"
        data-vv-scope="policycustom_insert"
      ></v-text-field>
      <async-select
        label="级别B"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="chineseName"
        v-model="newPolicyCustom.levelB"
        data-vv-name="级别B"
        data-vv-scope="policycustom_insert"
      ></async-select>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="policycustom_insert()">
        <v-icon>check</v-icon>
        保存

      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect';

  var newPolicyCustom = {
    objectType: "",
    keyName: "",
    indexName: "",
    indexType: "",
    nature: "",
    createPerson: "",
    thresholdA: "",
    thresholdB: "",
    levelA: "",
    levelB: "",
  }
  export default {
    data(){
      return {
        newPolicyCustom: _.cloneDeep(newPolicyCustom),
        initIndexNameItems:[]
      }
    },
    components:{
      "async-select": asyncSelect,
    },
    methods: {
      policycustom_insert(){
        this.$validator.validateAll("policycustom_insert").then((result) => {
          if (result) {
            axios().post("/policyCustom", this.newPolicyCustom).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'policyCustom'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      updateItems(data){
        this.initIndexNameItems = data;
      },
    }
  }
</script>

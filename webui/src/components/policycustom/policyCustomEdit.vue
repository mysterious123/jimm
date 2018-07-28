<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'policyCustom'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">级别阈值微调</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <!--<v-text-field-->
      <!--label="对象类型"-->
      <!--v-model="updatePolicyCustom.objectType"-->
      <!--v-validate="'required'"-->
      <!--:error-messages="vee_errors.collect('对象类型','policycustom_update')[0]||[]"-->
      <!--data-vv-name="对象类型"-->
      <!--data-vv-scope="policycustom_update"-->
      <!--&gt;</v-text-field>-->
      <v-text-field
        label="关键字"
        v-model="updatePolicyCustom.keyName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('关键字','policycustom_update')[0]||[]"
        data-vv-name="关键字"
        data-vv-scope="policycustom_update"
      ></v-text-field>

      <!--<v-text-field-->
        <!--label="类型"-->
        <!--v-model="updatePolicyCustom.indexType"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('类型','policycustom_update')[0]||[]"-->
        <!--data-vv-name="类型"-->
        <!--data-vv-scope="policycustom_update"-->
      <!--&gt;</v-text-field>-->
      <async-select
        label="指标类型"
        :v-data-params="{type : 'index_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('指标类型','policycustom_update')[0]||[]"
        v-model="updatePolicyCustom.indexType"
        data-vv-name="指标类型"
        data-vv-scope="policycustom_update"
        @change="newPolicyCustom.indexName = ''"
      ></async-select>
      <!--<v-text-field-->
        <!--label="指标名称"-->
        <!--v-model="updatePolicyCustom.indexName"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('指标名称','policycustom_update')[0]||[]"-->
        <!--data-vv-name="指标名称"-->
        <!--data-vv-scope="policycustom_update"-->
      <!--&gt;</v-text-field>-->
      <async-select
        label="指标名称"
        :v-data-url="'/zMibInfo'"
        :v-data-params="updatePolicyCustom.indexType?{indexType : updatePolicyCustom.indexType}:{indexType : -1}"
        :items="initIndexNameItems"
        @update="updateItems"
        v-model="updatePolicyCustom.indexName"
        item-text="indexName"
        item-value="id"
        data-vv-name="指标名称"
        data-vv-scope="policycustom_update">
      </async-select>
      <v-text-field
        label="阀值A"
        v-model="updatePolicyCustom.thresholdA"
        v-validate="'required'"
        :error-messages="vee_errors.collect('阀值A','policycustom_update')[0]||[]"
        data-vv-name="阀值A"
        data-vv-scope="policycustom_update"
      ></v-text-field>
      <async-select
        label="级别A"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="chineseName"
        v-model="updatePolicyCustom.levelA"
        data-vv-name="级别A"
        data-vv-scope="policycustom_update"
      ></async-select>
      <v-text-field
        label="阀值B"
        v-model="updatePolicyCustom.thresholdB"
        v-validate="'required'"
        :error-messages="vee_errors.collect('阀值B','policycustom_update')[0]||[]"
        data-vv-name="阀值B"
        data-vv-scope="policycustom_update"
      ></v-text-field>
      <async-select
        label="级别B"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="chineseName"
        v-model="updatePolicyCustom.levelB"
        data-vv-name="级别B"
        data-vv-scope="policycustom_update"
      ></async-select>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="policycustom_update()">
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

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.policycustom.updatePolicyCustom) {
        next({name: 'policyCustom'})
      } else {
        next();
      }
    },
    components:{
      "async-select": asyncSelect,
    },
    data(){
      return {
        initIndexNameItems:[]
      }
    },
    computed: {
      ...mapState('policycustom', ['updatePolicyCustom']),
    },
    methods: {
      policycustom_update(){
        this.$validator.validateAll("policycustom_update").then((result) => {
          if (result) {
            axios().put("/policyCustom", this.updatePolicyCustom).then(() => {
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

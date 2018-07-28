<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="$router.go(-1);" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">策略参数编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="指标类型"
        :v-data-params="{type : 'index_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('指标类型','operationpolicyparam_update')[0]||[]"
        v-model="updateOperationPolicyParam.indexType"
        data-vv-name="指标类型"
        data-vv-scope="operationpolicyparam_update"
      ></async-select>
    <!--<v-text-field-->
       <!--label="指标名称"-->
       <!--v-model="updateOperationPolicyParam.indexName"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('指标名称','operationpolicyparam_update')[0]||[]"-->
       <!--data-vv-name="指标名称"-->
       <!--data-vv-scope="operationpolicyparam_update"-->
     <!--&gt;</v-text-field>-->
      <async-select
        label="指标名称"
        :v-data-url="'/zAgentInfo'"
        :v-data-params="updateOperationPolicyParam.indexType?{indexType : updateOperationPolicyParam.indexType}:{indexType : -1}"
        :items="initIndexNameItems"
        @update="updateItems"
        v-model="updateOperationPolicyParam.indexName"
        item-text="indexName"
        item-value="id"
        data-vv-name="指标名称"
        data-vv-scope="operationpolicyparam_update">
      </async-select>
    <v-text-field
       label="阀值A"
       v-model="updateOperationPolicyParam.thresholdA"
       v-validate="'required'"
       :error-messages="vee_errors.collect('阀值A','operationpolicyparam_update')[0]||[]"
       data-vv-name="阀值A"
       data-vv-scope="operationpolicyparam_update"
     ></v-text-field>
      <async-select
        label="级别A"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="id"
        v-model="updateOperationPolicyParam.levelA"
        data-vv-name="级别A"
        data-vv-scope="operationpolicyparam_update"
      ></async-select>
    <v-text-field
       label="阀值B"
       v-model="updateOperationPolicyParam.thresholdB"
       v-validate="'required'"
       :error-messages="vee_errors.collect('阀值B','operationpolicyparam_update')[0]||[]"
       data-vv-name="阀值B"
       data-vv-scope="operationpolicyparam_update"
     ></v-text-field>
    <!--<v-text-field-->
       <!--label="级别A"-->
       <!--v-model="updateOperationPolicyParam.levelA"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('级别A','operationpolicyparam_update')[0]||[]"-->
       <!--data-vv-name="级别A"-->
       <!--data-vv-scope="operationpolicyparam_update"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="级别B"-->
       <!--v-model="updateOperationPolicyParam.levelB"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('级别B','operationpolicyparam_update')[0]||[]"-->
       <!--data-vv-name="级别B"-->
       <!--data-vv-scope="operationpolicyparam_update"-->
     <!--&gt;</v-text-field>-->
      <async-select
        label="级别B"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="id"
        v-model="updateOperationPolicyParam.levelB"
        data-vv-name="级别B"
        data-vv-scope="operationpolicyparam_update"
      ></async-select>
    <v-text-field
       label="采集间隔"
       v-model="updateOperationPolicyParam.collectSpace"
       v-validate="'required'"
       :error-messages="vee_errors.collect('采集间隔','operationpolicyparam_update')[0]||[]"
       data-vv-name="采集间隔"
       data-vv-scope="operationpolicyparam_update"
     ></v-text-field>
    <v-text-field
       label="超时"
       v-model="updateOperationPolicyParam.overTime"
       v-validate="'required'"
       :error-messages="vee_errors.collect('超时','operationpolicyparam_update')[0]||[]"
       data-vv-name="超时"
       data-vv-scope="operationpolicyparam_update"
     ></v-text-field>
    <v-text-field
       label="重试间隔"
       v-model="updateOperationPolicyParam.retrySpace"
       v-validate="'required'"
       :error-messages="vee_errors.collect('重试间隔','operationpolicyparam_update')[0]||[]"
       data-vv-name="重试间隔"
       data-vv-scope="operationpolicyparam_update"
     ></v-text-field>
    <v-text-field
       label="重试次数"
       v-model="updateOperationPolicyParam.retryNum"
       v-validate="'required'"
       :error-messages="vee_errors.collect('重试次数','operationpolicyparam_update')[0]||[]"
       data-vv-name="重试次数"
       data-vv-scope="operationpolicyparam_update"
     ></v-text-field>
    <!--<v-text-field-->
       <!--label="策略编号"-->
       <!--v-model="updateOperationPolicyParam.policyId"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('策略编号','operationpolicyparam_update')[0]||[]"-->
       <!--data-vv-name="策略编号"-->
       <!--data-vv-scope="operationpolicyparam_update"-->
     <!--&gt;</v-text-field>-->

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="operationpolicyparam_update()">
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
      if(!store.state.operationpolicyparam.updateOperationPolicyParam) {
        if(store.state.operationpolicyinfo.operation === 'insert') {
          next({name :'operationPolicyInfo/add'})
        }else{
          next({name :'operationPolicyInfo/edit'})
        }
      }else{
        next();
      }
    },
    data() {
      return {
        initIndexNameItems:[]
      }
    },
    components:{
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('operationpolicyparam', ['updateOperationPolicyParam']),
      ...mapState('operationpolicyinfo', ['operation']),
    },
    methods:{
      operationpolicyparam_update(){
        this.$validator.validateAll("operationpolicyparam_update").then((result) => {
          if (result) {
            if(this.operation !== 'insert') {
              axios().put("/operationPolicyParam", this.updateOperationPolicyParam).then(() => {
                this.simpleInfo("操作成功")
                this.$router.go(-1);
              }).catch((error) => {
                this.simpleError("后台异常,操作失败")
              })
            }else{
              this.simpleInfo("操作成功")
              this.$router.go(-1);
            }
          }
        });
      },
      updateItems(data){
        this.initIndexNameItems = data;
      }
    }
  }
</script>

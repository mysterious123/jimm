<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="$router.go(-1)" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增策略参数</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="指标类型"
        :v-data-params="{type : 'index_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('指标类型','operationpolicyparam_insert')[0]||[]"
        v-model="newOperationPolicyParam.indexType"
        data-vv-name="指标类型"
        data-vv-scope="operationpolicyparam_insert"
        @change="newOperationPolicyParam.indexName = ''"
      ></async-select>
      <async-select
        label="指标名称"
        :v-data-url="'/zMibInfo'"
        :v-data-params="newOperationPolicyParam.indexType?{indexType : newOperationPolicyParam.indexType}:{indexType : -1}"
        :items="initIndexNameItems"
        @update="updateItems"
        v-model="newOperationPolicyParam.indexName"
        item-text="indexName"
        item-value="id"
        data-vv-name="指标名称"
        data-vv-scope="operationpolicyparam_insert">
      </async-select>
      <v-text-field
        label="阀值A"
        v-model="newOperationPolicyParam.thresholdA"
        v-validate="'required'"
        :error-messages="vee_errors.collect('阀值A','operationpolicyparam_insert')[0]||[]"
        data-vv-name="阀值A"
        data-vv-scope="operationpolicyparam_insert"
      ></v-text-field>
      <async-select
        label="级别A"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="id"
        v-model="newOperationPolicyParam.levelA"
        data-vv-name="级别A"
        data-vv-scope="operationpolicyparam_insert"
      ></async-select>
      <v-text-field
        label="阀值B"
        v-model="newOperationPolicyParam.thresholdB"
        v-validate="'required'"
        :error-messages="vee_errors.collect('阀值B','operationpolicyparam_insert')[0]||[]"
        data-vv-name="阀值B"
        data-vv-scope="operationpolicyparam_insert"
      ></v-text-field>
      <async-select
        label="级别B"
        :v-data-url="'/superSeverity'"
        :v-data-params="{}"
        item-text="chineseName"
        item-value="id"
        v-model="newOperationPolicyParam.levelB"
        data-vv-name="级别B"
        data-vv-scope="operationpolicyparam_insert"
      ></async-select>
      <v-text-field
        label="采集间隔"
        v-model="newOperationPolicyParam.collectSpace"
        v-validate="'required'"
        :error-messages="vee_errors.collect('采集间隔','operationpolicyparam_insert')[0]||[]"
        data-vv-name="采集间隔"
        data-vv-scope="operationpolicyparam_insert"
      ></v-text-field>
      <v-text-field
        label="超时"
        v-model="newOperationPolicyParam.overTime"
        v-validate="'required'"
        :error-messages="vee_errors.collect('超时','operationpolicyparam_insert')[0]||[]"
        data-vv-name="超时"
        data-vv-scope="operationpolicyparam_insert"
      ></v-text-field>
      <v-text-field
        label="重试间隔"
        v-model="newOperationPolicyParam.retrySpace"
        v-validate="'required'"
        :error-messages="vee_errors.collect('重试间隔','operationpolicyparam_insert')[0]||[]"
        data-vv-name="重试间隔"
        data-vv-scope="operationpolicyparam_insert"
      ></v-text-field>
      <v-text-field
        label="重试次数"
        v-model="newOperationPolicyParam.retryNum"
        v-validate="'required'"
        :error-messages="vee_errors.collect('重试次数','operationpolicyparam_insert')[0]||[]"
        data-vv-name="重试次数"
        data-vv-scope="operationpolicyparam_insert"
      ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="operationpolicyparam_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import asyncSelect from '@/components/common/asyncSelect';


  var newOperationPolicyParam = {
    id: "",
    indexType: "",
    indexName: "",
    thresholdA: "",
    thresholdB: "",
    levelA: "",
    levelB: "",
    collectSpace: "",
    overTime: "",
    retrySpace: "",
    retryNum: "",
    policyId: "",
  }
  export default {
    beforeRouteEnter (to, from, next) {
      if(!store.state.operationpolicyinfo.updateOperationPolicyInfo) {
        if(store.state.operationpolicyinfo.operation === 'insert') {
          next()
        }else{
          next({name :'operationPolicyInfo'})
        }
      }else{
        next();
      }
    },
    data() {
      return {
        newOperationPolicyParam: _.cloneDeep(newOperationPolicyParam),
        initIndexNameItems:[]
      }
    },
    components:{
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('operationpolicyinfo', ['operation', 'insert_items', 'idMaker','updateOperationPolicyInfo']),
    },
    methods: {
      ...mapMutations('operationpolicyinfo', ['addInsertItems']),
      operationpolicyparam_insert() {
        this.$validator.validateAll("operationpolicyparam_insert").then((result) => {
          if (result) {
            if(this.operation !== 'insert') {
              this.newOperationPolicyParam.policyId = this.updateOperationPolicyInfo.id;
              axios().post("/operationPolicyParam", this.newOperationPolicyParam).then(() => {
                this.simpleInfo("操作成功")
                this.$router.go(-1);
              }).catch((error) => {
                this.simpleError("后台异常,操作失败")
              })
            }else{
              this.newOperationPolicyParam.id = this.idMaker.next().value;
              this.addInsertItems(this.newOperationPolicyParam);
              this.simpleInfo("操作成功")
              this.$router.go(-1);
            }
          }
        });
      },
      updateItems(data){
        this.initIndexNameItems = data;
      },
    }
  }
</script>

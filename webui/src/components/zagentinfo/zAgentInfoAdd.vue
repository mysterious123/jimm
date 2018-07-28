<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'zAgentInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">Agent新增</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
    <v-text-field
       label="指标名称"
       v-model="newZAgentInfo.indexName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('指标名称','zagentinfo_insert')[0]||[]"
       data-vv-name="指标名称"
       data-vv-scope="zagentinfo_insert"
     ></v-text-field>
    <v-text-field
       label="指标简写"
       v-model="newZAgentInfo.indexShorthand"
       v-validate="'required'"
       :error-messages="vee_errors.collect('指标简写','zagentinfo_insert')[0]||[]"
       data-vv-name="指标简写"
       data-vv-scope="zagentinfo_insert"
     ></v-text-field>
      <async-select
        label="监控代理"
        :v-data-url="'/agentInfo'"
        :v-data-params="{}"
        item-text="agentName"
        item-value="agentName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('监控代理','zagentinfo_insert')[0]||[]"
        v-model="newZAgentInfo.monitorAgent"
        data-vv-name="监控代理"
        data-vv-scope="zagentinfo_insert"
      ></async-select>
    <v-text-field
       label="单位"
       v-model="newZAgentInfo.unit"
       data-vv-name="单位"
       data-vv-scope="zagentinfo_insert"
     ></v-text-field>
    <v-text-field
       label="关键字"
       v-model="newZAgentInfo.keyWord"
       data-vv-name="关键字"
       data-vv-scope="zagentinfo_insert"
     ></v-text-field>
    <!--<v-text-field-->
       <!--label="计算公式"-->
       <!--v-model="newZAgentInfo.calculationFormula"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('计算公式','zagentinfo_insert')[0]||[]"-->
       <!--data-vv-name="计算公式"-->
       <!--data-vv-scope="zagentinfo_insert"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="内容描述"-->
       <!--v-model="newZAgentInfo.contentDescribe"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('内容描述','zagentinfo_insert')[0]||[]"-->
       <!--data-vv-name="内容描述"-->
       <!--data-vv-scope="zagentinfo_insert"-->
     <!--&gt;</v-text-field>-->

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zagentinfo_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import {axios} from '@/assets/js/Utils';
import asyncSelect from '@/components/common/asyncSelect';

  var newZAgentInfo = {
  indexName : "",
  indexShorthand : "",
  monitorAgent : "",
  unit : "",
  keyWord : "",
  calculationFormula : "",
  contentDescribe : "",
  }
  export default {
    data(){
      return {
        newZAgentInfo : _.cloneDeep(newZAgentInfo)
      }
    },
    components: {
      "async-select": asyncSelect,
    },
    methods:{
      zagentinfo_insert(){
        this.$validator.validateAll("zagentinfo_insert").then((result) => {
          if (result) {
            axios().post("/zAgentInfo",this.newZAgentInfo).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'zAgentInfo'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

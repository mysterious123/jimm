<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="$router.go(-1)" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">agent表编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
    <!--<v-text-field-->
       <!--label="AGENT编号"-->
       <!--v-model="updateZAgentColumnInfo.agentId"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('AGENT编号','zagentcolumninfo_update')[0]||[]"-->
       <!--data-vv-name="AGENT编号"-->
       <!--data-vv-scope="zagentcolumninfo_update"-->
     <!--&gt;</v-text-field>-->
    <v-text-field
       label="字段名"
       v-model="updateZAgentColumnInfo.columnName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('字段名','zagentcolumninfo_update')[0]||[]"
       data-vv-name="字段名"
       data-vv-scope="zagentcolumninfo_update"
     ></v-text-field>
    <!--<v-text-field-->
       <!--label="字段类型"-->
       <!--v-model="updateZAgentColumnInfo.columnType"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('字段类型','zagentcolumninfo_update')[0]||[]"-->
       <!--data-vv-name="字段类型"-->
       <!--data-vv-scope="zagentcolumninfo_update"-->
     <!--&gt;</v-text-field>-->
      <async-select
        label="字段类型"
        :v-data-params="{type : 'agent_field_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('字段类型','zagentcolumninfo_update')[0]||[]"
        v-model="updateZAgentColumnInfo.columnType"
        data-vv-name="字段类型"
        data-vv-scope="zagentcolumninfo_update"
      ></async-select>
    <v-text-field
       label="正则表达式"
       v-model="updateZAgentColumnInfo.regularExpression"
       v-validate="'required'"
       :error-messages="vee_errors.collect('正则表达式','zagentcolumninfo_update')[0]||[]"
       data-vv-name="正则表达式"
       data-vv-scope="zagentcolumninfo_update"
     ></v-text-field>
    <v-text-field
       label="字段说明"
       v-model="updateZAgentColumnInfo.columnExplain"
       v-validate="'required'"
       :error-messages="vee_errors.collect('字段说明','zagentcolumninfo_update')[0]||[]"
       data-vv-name="字段说明"
       data-vv-scope="zagentcolumninfo_update"
     ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zagentcolumninfo_update()">
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
      if(!store.state.zagentcolumninfo.updateZAgentColumnInfo) {
        next({name:'zAgentColumnInfo'})
      }else{
        next();
      }
      if (!store.state.zagentinfo.updateZAgentInfo) {
        next({name:'zAgentInfo'})
      } else {
        next();
      }
    },
    components: {
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('zagentcolumninfo', ['updateZAgentColumnInfo']),
      ...mapState('zagentinfo', ['updateZAgentInfo']),
    },
    methods:{
      zagentcolumninfo_update(){
        this.$validator.validateAll("zagentcolumninfo_update").then((result) => {
          if (result) {
            this.updateZAgentColumnInfo.agentId = this.updateZAgentInfo.id;
            axios().put("/zAgentColumnInfo",this.updateZAgentColumnInfo).then(()=>{
              this.simpleInfo("操作成功")
              this.$router.go(-1);
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

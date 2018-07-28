<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="$router.go(-1)" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">agent表新增</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="字段名"
        v-model="newZAgentColumnInfo.columnName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('字段名','zagentcolumninfo_insert')[0]||[]"
        data-vv-name="字段名"
        data-vv-scope="zagentcolumninfo_insert"
      ></v-text-field>
      <async-select
        label="字段类型"
        :v-data-params="{type : 'agent_field_type'}"
        v-validate="'required'"
        :error-messages="vee_errors.collect('字段类型','zagentcolumninfo_insert')[0]||[]"
        v-model="newZAgentColumnInfo.columnType"
        data-vv-name="字段类型"
        data-vv-scope="zagentcolumninfo_insert"
      ></async-select>
      <v-text-field
        label="正则表达式"
        v-model="newZAgentColumnInfo.regularExpression"
        data-vv-name="正则表达式"
        data-vv-scope="zagentcolumninfo_insert"
      ></v-text-field>
      <v-text-field
        label="字段说明"
        v-model="newZAgentColumnInfo.columnExplain"
        data-vv-name="字段说明"
        data-vv-scope="zagentcolumninfo_insert"
      ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zagentcolumninfo_insert()">
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

  var newZAgentColumnInfo = {
    agentId: "",
    columnName: "",
    columnType: "",
    regularExpression: "",
    columnExplain: "",
  }
  export default {
    beforeRouteEnter(to, from, next) {
      if (!store.state.zagentinfo.updateZAgentInfo) {
        next({name:'zAgentInfo'})
      } else {
        next();
      }
    },
    data() {
      return {
        newZAgentColumnInfo: _.cloneDeep(newZAgentColumnInfo)
      }
    },
    components: {
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('zagentinfo', ['updateZAgentInfo']),
    },
    methods: {
      zagentcolumninfo_insert() {
        this.$validator.validateAll("zagentcolumninfo_insert").then((result) => {
          if (result) {
            this.newZAgentColumnInfo.agentId = this.updateZAgentInfo.id;
            axios().post("/zAgentColumnInfo", this.newZAgentColumnInfo).then(() => {
              this.simpleInfo("操作成功")
              this.$router.go(-1);
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'zLogInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">LOG事件编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="事件类型"
        :v-data-params="{type : 'log_event_type'}"
        v-model="updateZLogInfo.eventType"
        data-vv-name="事件类型"
        data-vv-scope="zloginfo_update"
      ></async-select>
      <v-text-field
        label="事件子类型"
        v-model="updateZLogInfo.eventSmallType"
        data-vv-name="事件子类型"
        data-vv-scope="zloginfo_update"
      ></v-text-field>
      <async-select
        label="厂家品牌"
        :v-data-url="'/venderInfo'"
        :v-data-params="{}"
        item-text="venderName"
        item-value="venderName"
        v-model="updateZLogInfo.venderBrand"
        data-vv-name="厂家品牌"
        data-vv-scope="zloginfo_update"
      ></async-select>
      <v-text-field
        label="事件组名"
        v-model="updateZLogInfo.eventGroupName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('事件组名','zloginfo_insert')[0]||[]"
        data-vv-name="事件组名"
        data-vv-scope="zloginfo_update"
      ></v-text-field>
      <v-text-field
        label="关键字"
        v-model="updateZLogInfo.keyWord"
        data-vv-name="关键字"
        data-vv-scope="zloginfo_update"
      ></v-text-field>
      <v-text-field
        label="正则表达式"
        v-model="updateZLogInfo.regularExpression"
        data-vv-name="正则表达式"
        data-vv-scope="zloginfo_update"
      ></v-text-field>
      <v-text-field
        label="内容描述"
        v-model="updateZLogInfo.contentDescribe"
        multi-line
        data-vv-name="内容描述"
        data-vv-scope="zloginfo_update"
      ></v-text-field>
      <v-text-field
        label="处理建议"
        v-model="updateZLogInfo.handlePropose"
        multi-line
        data-vv-name="处理建议"
        data-vv-scope="zloginfo_update"
      ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zloginfo_update()">
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
      if (!store.state.zloginfo.updateZLogInfo) {
        next({name: 'zLogInfo'})
      } else {
        next();
      }
    },
    data() {
      return {
        options: []
      }
    },
    computed: {
      ...mapState('zloginfo', ['updateZLogInfo']),
    },
    components: {
      "async-select": asyncSelect,
    },
    methods: {
      zloginfo_update(){
        this.$validator.validateAll("zloginfo_update").then((result) => {
          if (result) {
            axios().put("/zLogInfo", this.updateZLogInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'zLogInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

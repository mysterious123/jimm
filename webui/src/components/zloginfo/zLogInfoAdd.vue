<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'zLogInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">LOG事件新增</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <async-select
        label="事件类型"
        :v-data-params="{type : 'log_event_type'}"
        v-model="newZLogInfo.eventType"
        data-vv-name="事件类型"
        data-vv-scope="zloginfo_insert"
      ></async-select>
      <v-text-field
        label="事件子类型"
        v-model="newZLogInfo.eventSmallType"
        data-vv-name="事件子类型"
        data-vv-scope="zloginfo_insert"
      ></v-text-field>
      <!--<v-select-->
        <!--label="厂家品牌"-->
        <!--:items="options"-->
        <!--v-model="newZLogInfo.venderBrand"-->
        <!--data-vv-name="厂家品牌"-->
        <!--data-vv-scope="zloginfo_insert"-->
      <!--&gt;</v-select>-->
      <async-select
        label="厂家品牌"
        :v-data-url="'/venderInfo'"
        :v-data-params="{}"
        item-text="venderName"
        item-value="id"
        v-model="newZLogInfo.venderBrand"
        data-vv-name="厂家品牌"
        data-vv-scope="zloginfo_insert"
      ></async-select>
      <v-text-field
        label="事件组名"
        v-model="newZLogInfo.eventGroupName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('事件组名','zloginfo_insert')[0]||[]"
        data-vv-name="事件组名"
        data-vv-scope="zloginfo_insert"
      ></v-text-field>
      <!--<v-text-field-->
        <!--label="事件组号"-->
        <!--v-model="newZLogInfo.eventGroupNum"-->
        <!--data-vv-name="事件组号"-->
        <!--data-vv-scope="zloginfo_insert"-->
      <!--&gt;</v-text-field>-->
      <v-text-field
        label="关键字"
        v-model="newZLogInfo.keyWord"
        data-vv-name="关键字"
        data-vv-scope="zloginfo_insert"
      ></v-text-field>
      <v-text-field
        label="正则表达式"
        v-model="newZLogInfo.regularExpression"
        data-vv-name="正则表达式"
        data-vv-scope="zloginfo_insert"
      ></v-text-field>
      <v-text-field
        label="内容描述"
        v-model="newZLogInfo.contentDescribe"
        multi-line
        data-vv-name="内容描述"
        data-vv-scope="zloginfo_insert"
      ></v-text-field>
      <v-text-field
        label="处理建议"
        v-model="newZLogInfo.handlePropose"
        multi-line
        data-vv-name="处理建议"
        data-vv-scope="zloginfo_insert"
      ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zloginfo_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect';

  var newZLogInfo = {
    eventType: "",
    eventSmallType: "",
    venderBrand: "",
    eventGroupName: "",
    eventGroupNum: "",
    keyWord: "",
    regularExpression: "",
    contentDescribe: "",
    handlePropose: "",
  }
  export default {
    data() {
      return {
        newZLogInfo: _.cloneDeep(newZLogInfo),
      }
    },
    components: {
      "async-select": asyncSelect,
    },
    methods: {
      zloginfo_insert() {
        this.$validator.validateAll("zloginfo_insert").then((result) => {
          if (result) {
            axios().post("/zLogInfo", this.newZLogInfo).then(() => {
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

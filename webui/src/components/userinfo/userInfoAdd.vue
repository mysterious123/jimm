<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'userInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增联系人</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="联系人"
        v-model="newUserInfo.userName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('联系人','userinfo_insert')[0]||[]"
        data-vv-name="联系人"
        data-vv-scope="userinfo_insert"
      ></v-text-field>
      <v-text-field
        label="手机号码"
        v-model="newUserInfo.phone"
        v-validate="'numeric'"
        :error-messages="vee_errors.collect('手机号码','userinfo_insert')[0]||[]"
        data-vv-name="手机号码"
        data-vv-scope="userinfo_insert"
      ></v-text-field>
      <v-text-field
        label="邮箱"
        v-model="newUserInfo.email"
        v-validate="'email'"
        :error-messages="vee_errors.collect('邮箱','userinfo_insert')[0]||[]"
        data-vv-name="邮箱"
        data-vv-scope="userinfo_insert"
      ></v-text-field>

      <v-select
        label="管理组"
        v-bind:items="manageGroups"
        v-model="manageGroupIds"
        multiple
        max-height="400"
        persistent-hint
      ></v-select>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="userinfo_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';

  var newUserInfo = {
    userName: "",
    password: "",
    showName: "",
    active: "",
    stat: "",
    phone: "",
    email: "",
    createDate: "",
  }
  export default {
    data(){
      return {
        newUserInfo: _.cloneDeep(newUserInfo),
        manageGroups: []
      }
    },
    mounted(){
      this.queryManageGroups();
    },
    computed: {
      manageGroupIds: {
        get: function () {
          return [];
        },
        set: function (values) {
          this.newUserInfo.manageGroups = values.map((item) => {
            return {id: item}
          })
        }
      }
    },
    methods: {
      userinfo_insert(){
        this.$validator.validateAll("userinfo_insert").then((result) => {
          if (result) {
            axios().post("/userinfo", this.newUserInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'userInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      async queryManageGroups(){
        var result = await axios().get("/manageGroupInfo")
        this.manageGroups = result.data.map((item) => {
          return {
            text: item.manageGroupName,
            value: item.id
          }
        });
      },
    }
  }
</script>

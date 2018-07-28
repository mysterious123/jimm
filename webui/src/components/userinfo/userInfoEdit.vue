<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'userInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">编辑联系人</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-text-field
        label="用户名"
        v-model="updateUserInfo.userName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('用户名','userinfo_update')[0]||[]"
        data-vv-name="用户名"
        data-vv-scope="userinfo_update"
      ></v-text-field>

      <v-text-field
        label="手机号码"
        v-model="updateUserInfo.phone"
        :error-messages="vee_errors.collect('手机号码','userinfo_update')[0]||[]"
        data-vv-name="手机号码"
        data-vv-scope="userinfo_update"
      ></v-text-field>
      <v-text-field
        label="邮箱"
        v-model="updateUserInfo.email"
        v-validate="'email'"
        :error-messages="vee_errors.collect('邮箱','userinfo_update')[0]||[]"
        data-vv-name="邮箱"
        data-vv-scope="userinfo_update"
      ></v-text-field>

      <v-select
        label="管理组"
        v-bind:items="updateManageGroups"
        v-model="updateManageGroupIds"
        multiple
        max-height="400"
        persistent-hint
      ></v-select>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="userinfo_update()">
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

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.userinfo.updateUserInfo) {
        next({name: 'userInfo'})
      } else {
        next();
      }
    },
    data(){
      return {
        updateManageGroups: []
      }
    },
    mounted(){
      this.queryManageGroups();
    },
    computed: {
      ...mapState('userinfo', ['updateUserInfo']),
      updateManageGroupIds: {
        get: function () {
          if (this.updateUserInfo.manageGroups) {
            return this.updateUserInfo.manageGroups.map((item) => {
              return item.id
            })
          } else {
            return []
          }
        },
        set: function (values) {
          this.updateUserInfo.updateManageGroups = values.map((item) => {
            return {id: item}
          })
        }
      },
    },
    methods: {
      userinfo_update(){
        this.$validator.validateAll("userinfo_update").then((result) => {
          if (result) {
            this.updateUserInfo.manageGroups = this.updateUserInfo.updateManageGroups;
            axios().put("/userinfo", this.updateUserInfo).then(() => {
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
        this.updateManageGroups = result.data.map((item) => {
          return {
            text: item.manageGroupName,
            value: item.id
          }
        });
      },
    }
  }
</script>

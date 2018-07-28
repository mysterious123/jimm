<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superUserGroup'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增用户组</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row wrap>
        <v-flex xs12>
          <v-text-field
            label="用户组名称"
            v-model="newSuperUserGroup.groupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('用户组名称','superusergroup_insert')[0]||[]"
            data-vv-name="用户组名称"
            data-vv-scope="superusergroup_insert"
          ></v-text-field>

          <v-text-field
            label="角色"
            v-model="newSuperUserGroup.roleId"
            data-vv-name="角色"
            data-vv-scope="superusergroup_insert"
          ></v-text-field>
        </v-flex>
        <v-flex xs6 pr-2>
          <v-card class="cyan" dark dense>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">系统用户</v-toolbar-title>
            </v-toolbar>
            <v-list two-line subheader style="overflow-y: scroll;height: 300px">
              <v-list-tile avatar v-for="item in sysUsers" v-bind:key="item.title">
                <v-list-tile-avatar>
                  <v-icon class="grey lighten-1 white--text">person_pin</v-icon>
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.userName }}</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-btn icon ripple @click="addUser(item.id)">
                    <v-icon color="grey lighten-1">chevron_right</v-icon>
                  </v-btn>
                </v-list-tile-action>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-flex>
        <v-flex xs6 pr-2>
          <v-card class="cyan" dark dense>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">已选用户</v-toolbar-title>
            </v-toolbar>
            <v-list two-line subheader style="overflow-y: scroll;height: 300px">
              <v-list-tile avatar v-for="item in selectUsers" v-bind:key="item.title">
                <v-list-tile-avatar>
                  <v-icon class="grey lighten-1 white--text">person_pin</v-icon>
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.userName }}</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-btn icon ripple @click="removeUser(item.id)">
                    <v-icon color="grey lighten-1">close</v-icon>
                  </v-btn>
                </v-list-tile-action>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-flex>
      </v-layout>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superusergroup_insert()">
        <v-icon>check</v-icon>
        保存

      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect'

  var newSuperUserGroup = {
    groupName: "",
    createTime: "",
    roleId: "",
  }
  export default {
    mounted() {
      axios().get("superUser").then((data) => {
        this.sysUsers = data.data
      })
    },
    data(){
      return {
        newSuperUserGroup: _.cloneDeep(newSuperUserGroup),
        sysUsers: [],
        selectUsers: []
      }
    },
    computed: {
      superUserIds(){
        return this.selectUsers.map((user) => {
          return {
            userId: user.id,
          };
        })
      }
    },
    components: {
      "async-select": asyncSelect
    },
    methods: {
      superusergroup_insert(){
        this.$validator.validateAll("superusergroup_insert").then((result) => {
          if (result) {
            this.newSuperUserGroup.superUserIds = this.superUserIds;
            axios().post("/superUserGroup", this.newSuperUserGroup).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'superUserGroup'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      addUser(id){
        var clickUser = _.filter(this.sysUsers, function (user) {
          return user.id === id;
        });
        var users = _.filter(this.sysUsers, function (user) {
          return user.id !== id;
        });
        this.sysUsers = users;
        this.selectUsers.push(...clickUser);

      },
      removeUser(id){
        var clickUser = _.filter(this.selectUsers, function (user) {
          return user.id === id;
        });
        var users = _.filter(this.selectUsers, function (user) {
          return user.id !== id;
        });
        this.selectUsers = users;
        this.sysUsers.push(...clickUser);
      }
    }
  }
</script>

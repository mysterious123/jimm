<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'releaseInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">服务器信息</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row wrap>
        <v-flex xs6>
          <v-text-field
            label="服务器说明"
            v-model="newreleaseInfo.serverExplain"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器说明','releaseinfo_insert')[0]||[]"
            data-vv-name="服务器说明"
            data-vv-scope="releaseinfo_insert"
          ></v-text-field>
          <v-text-field
            label="服务器IP"
            v-model="newreleaseInfo.serverIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器IP','releaseinfo_insert')[0]||[]"
            data-vv-name="服务器IP"
            data-vv-scope="releaseinfo_insert"
          ></v-text-field>
          <v-text-field
            label="服务器名"
            v-model="newreleaseInfo.serverName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器名','releaseinfo_insert')[0]||[]"
            data-vv-name="服务器名"
            data-vv-scope="releaseinfo_insert"
          ></v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field
            label="用户名"
            v-model="newreleaseInfo.userName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('用户名','releaseinfo_insert')[0]||[]"
            data-vv-name="用户名"
            data-vv-scope="releaseinfo_insert"
          ></v-text-field>
          <v-text-field
            label="密码"
            v-model="newreleaseInfo.password"
            v-validate="'required'"
            :error-messages="vee_errors.collect('密码','releaseinfo_insert')[0]||[]"
            data-vv-name="密码"
            data-vv-scope="releaseinfo_insert"
          ></v-text-field>
          <v-text-field
            label="生效脚本"
            v-model="newreleaseInfo.effectScript"
            v-validate="'required'"
            :error-messages="vee_errors.collect('生效脚本','releaseinfo_insert')[0]||[]"
            data-vv-name="生效脚本"
            data-vv-scope="releaseinfo_insert"
          ></v-text-field>
        </v-flex>

        <v-flex xs6 pr-2>
          <v-card class="cyan" dark dense>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">服务器组</v-toolbar-title>
            </v-toolbar>
            <v-list two-line subheader style="overflow-y: scroll;height: 300px">
              <ul id="serverGroupTree_left" class="ztree showIcon"></ul>
            </v-list>
          </v-card>
        </v-flex>
        <v-flex xs6 pr-2>
          <v-card class="cyan" dark dense>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">已选组</v-toolbar-title>
            </v-toolbar>
            <v-list subheader style="overflow-y: scroll;height: 300px">
              <v-list-tile avatar v-for="item in selectGroups" v-bind:key="item.title">
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.serverGroupName }}</v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-btn icon ripple @click="removeGroup(item.id)">
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
      <v-btn color="success" dark @click.stop="releaseinfo_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import ztree from 'ztree';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import {base_tree_setting} from '@/assets/js/Constant';

  var newreleaseInfo = {
    serverExplain: "",
    serverIp: "",
    serverName: "",
    userName: "",
    password: "",
    effectScript: "",
  }
  export default {
    mounted(){
      var vm = this;
      this.base_tree_setting.data.key.name = "serverGroupName";
      this.base_tree_setting.callback = {
        beforeClick (treeId, treeNode, clickFlag){
          vm.addServerGroup(treeNode.id);
        }
      }

      this.getServerGroup().then((response) => {
        $.fn.zTree.init($("#serverGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
        this.serverGroups = response.data;
      });
    },
    data(){
      return {
        newreleaseInfo: _.cloneDeep(newreleaseInfo),
        base_tree_setting: _.cloneDeep(base_tree_setting),
        serverGroups: [],
        selectGroups: []
      }
    },
    methods: {
      getServerGroup(){
        return axios().get("/servergroup/level");
      },
      releaseinfo_insert(){
        this.$validator.validateAll("releaseinfo_insert").then((result) => {
          if (result) {
            this.newreleaseInfo.releaseLink = this.releaseLink;
            axios().post("/releaseInfo", this.newreleaseInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'releaseInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      addServerGroup(id){
        var clickGroups = _.filter(this.serverGroups, function (serverGroup) {
          return serverGroup.id === id;
        });
        //限制重复选择
        var flag = 0;
        for (var i = 0; i < this.selectGroups.length; i++) {
          if (this.selectGroups[i].id == id) {
            flag = 1;
          }
        }
        if (flag == 0)
          this.selectGroups.push(...clickGroups);
      },
      removeGroup(id){
        var groups = _.filter(this.selectGroups, function (user) {
          return user.id !== id;
        });
        this.selectGroups = groups;
      }
    },
    computed: {
      releaseLink(){
        return this.selectGroups.map((group) => {
          return {
            serverGroupId: group.id,
          };
        })
      }
    }
  }
</script>

<!--<template>-->
  <!--<v-card>-->
    <!--<v-toolbar class="cyan" dark dense>-->
      <!--<v-btn icon @click="pagerouter({name:'releaseInfo'})" dark>-->
        <!--<v-icon>arrow_back</v-icon>-->
      <!--</v-btn>-->
      <!--<v-toolbar-title class="white&#45;&#45;text">releaseinfoEdit</v-toolbar-title>-->
    <!--</v-toolbar>-->
    <!--<v-card-text>-->
    <!--<v-text-field-->
       <!--label="服务器说明"-->
       <!--v-model="updatereleaseInfo.serverExplain"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('服务器说明','releaseinfo_update')[0]||[]"-->
       <!--data-vv-name="服务器说明"-->
       <!--data-vv-scope="releaseinfo_update"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="服务器IP"-->
       <!--v-model="updatereleaseInfo.serverIp"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('服务器IP','releaseinfo_update')[0]||[]"-->
       <!--data-vv-name="服务器IP"-->
       <!--data-vv-scope="releaseinfo_update"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="服务器名"-->
       <!--v-model="updatereleaseInfo.serverName"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('服务器名','releaseinfo_update')[0]||[]"-->
       <!--data-vv-name="服务器名"-->
       <!--data-vv-scope="releaseinfo_update"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="用户名"-->
       <!--v-model="updatereleaseInfo.userName"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('用户名','releaseinfo_update')[0]||[]"-->
       <!--data-vv-name="用户名"-->
       <!--data-vv-scope="releaseinfo_update"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="密码"-->
       <!--v-model="updatereleaseInfo.password"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('密码','releaseinfo_update')[0]||[]"-->
       <!--data-vv-name="密码"-->
       <!--data-vv-scope="releaseinfo_update"-->
     <!--&gt;</v-text-field>-->
    <!--<v-text-field-->
       <!--label="生效脚本"-->
       <!--v-model="updatereleaseInfo.effectScript"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('生效脚本','releaseinfo_update')[0]||[]"-->
       <!--data-vv-name="生效脚本"-->
       <!--data-vv-scope="releaseinfo_update"-->
     <!--&gt;</v-text-field>-->

    <!--</v-card-text>-->
    <!--<v-card-actions>-->
      <!--<v-spacer></v-spacer>-->
      <!--<v-btn color="success" dark @click.stop="releaseinfo_update()">-->
        <!--<v-icon>check</v-icon>-->
        <!--更新-->
      <!--</v-btn>-->
    <!--</v-card-actions>-->
  <!--</v-card>-->
<!--</template>-->
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
            v-model="updatereleaseInfo.serverExplain"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器说明','releaseinfo_update')[0]||[]"
            data-vv-name="服务器说明"
            data-vv-scope="releaseinfo_update"
          ></v-text-field>
          <v-text-field
            label="服务器IP"
            v-model="updatereleaseInfo.serverIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器IP','releaseinfo_update')[0]||[]"
            data-vv-name="服务器IP"
            data-vv-scope="releaseinfo_update"
          ></v-text-field>
          <v-text-field
            label="服务器名"
            v-model="updatereleaseInfo.serverName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器名','releaseinfo_update')[0]||[]"
            data-vv-name="服务器名"
            data-vv-scope="releaseinfo_update"
          ></v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field
            label="用户名"
            v-model="updatereleaseInfo.userName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('用户名','releaseinfo_update')[0]||[]"
            data-vv-name="用户名"
            data-vv-scope="releaseinfo_update"
          ></v-text-field>
          <v-text-field
            label="密码"
            v-model="updatereleaseInfo.password"
            v-validate="'required'"
            :error-messages="vee_errors.collect('密码','releaseinfo_update')[0]||[]"
            data-vv-name="密码"
            data-vv-scope="releaseinfo_update"
          ></v-text-field>
          <v-text-field
            label="生效脚本"
            v-model="updatereleaseInfo.effectScript"
            v-validate="'required'"
            :error-messages="vee_errors.collect('生效脚本','releaseinfo_update')[0]||[]"
            data-vv-name="生效脚本"
            data-vv-scope="releaseinfo_update"
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
      <v-btn color="success" dark @click.stop="releaseinfo_update()">
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
  import ztree from 'ztree';
  import {base_tree_setting} from '@/assets/js/Constant';

  export default {
    beforeRouteEnter (to, from, next) {
      if(!store.state.releaseinfo.updatereleaseInfo) {
        next({name :'releaseInfo'})
      }else{
        next();
      }
    },
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
      axios().get("releaseInfo/querySelectGroup",{
        params : {
          id : this.updatereleaseInfo.id
        }
      }).then((data)=>{
        this.selectGroups = data.data
      })
    },
    data(){
        return {
          serverGroups: [],
          selectGroups:[],
          base_tree_setting: _.cloneDeep(base_tree_setting),
        }
    },
    computed: {
      ...mapState('releaseinfo', ['updatereleaseInfo']),
      releaseLink(){
        return this.selectGroups.map((group) => {
          return {
            serverGroupId: group.id,
          };
        })
      }
    },
    methods:{
      getServerGroup(){
        return axios().get("/servergroup/level");
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
      },
      releaseinfo_update(){
        this.$validator.validateAll("releaseinfo_update").then((result) => {
          if (result) {
            this.updatereleaseInfo.releaseLink = this.releaseLink;
            axios().put("/releaseInfo",this.updatereleaseInfo).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'releaseInfo'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

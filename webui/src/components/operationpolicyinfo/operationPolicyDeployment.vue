<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="$router.go(-1)" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">策略分配</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-card class="cyan" dark dense>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">服务器列表</v-toolbar-title>
            </v-toolbar>
            <v-list two-line subheader style="overflow-y: scroll;height: 300px">
              <v-list-tile avatar v-for="item in serverList" v-bind:key="item.title" >
                <v-list-tile-avatar>
                  <v-icon class="grey lighten-1 white--text">person_pin</v-icon>
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.serverName }}</v-list-tile-title>
                  <v-list-tile-sub-title>{{ item.serverIp }}</v-list-tile-sub-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-btn icon ripple @click="addServer(item.serverId)">
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
              <v-toolbar-title class="white--text">已选服务器</v-toolbar-title>
            </v-toolbar>
            <v-list two-line subheader style="overflow-y: scroll;height: 300px">
              <v-list-tile avatar v-for="item in selectServers" v-bind:key="item.title" >
                <v-list-tile-avatar>
                  <v-icon class="grey lighten-1 white--text">person_pin</v-icon>
                </v-list-tile-avatar>
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.serverName }}</v-list-tile-title>
                  <v-list-tile-sub-title>{{ item.serverIp }}</v-list-tile-sub-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-btn icon ripple @click="removeServer(item.serverId)">
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
      <v-btn color="success" dark @click.stop="saveServers">
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

  export default {
    beforeRouteEnter(to, from, next) {
      if (!store.state.operationpolicyinfo.updateOperationPolicyInfo) {
        next({name :'operationPolicyInfo'})
      } else {
        next();
      }
    },
    computed: {
      ...mapState('operationpolicyinfo', ['updateOperationPolicyInfo']),
    },
    data(){
      return {
        serverList:[],
        selectServers:[],
      }
    },
    created(){
      this.queryUnSelectServerInfo();
      this.querySelectServerInfo();
    },
    methods:{
      queryUnSelectServerInfo() {
        var vm = this;
        axios().get("/operationPolicyDeployment/queryUnSelectServerInfo",{
          params : {
            policyId : vm.updateOperationPolicyInfo.id
          }
        }).then((response) => {
          this.serverList = response.data
        })
      },
      querySelectServerInfo() {
        var vm = this;
        axios().get("/operationPolicyDeployment/querySelectServerInfo",{
          params : {
            policyId : vm.updateOperationPolicyInfo.id
          }
        }).then((response) => {
          this.selectServers = response.data
        })
      },
      addServer(id){
        var clickServer = _.filter(this.serverList, function(server) { return server.serverId === id; });
        var servers = _.filter(this.serverList, function(server) { return server.serverId !== id; });
        this.serverList = servers;
        this.selectServers.push(...clickServer);

      },
      saveServers(){
        var vm = this;
        axios().post("/operationPolicyDeployment/batchInsert",{
          serverInfos: vm.selectServers,
          policyId: vm.updateOperationPolicyInfo.id
        }).then((response) => {
          this.simpleInfo("操作成功")
          this.pagerouter({name: 'operationPolicyInfo'})
        })
      },
      removeServer(id){
        var clickServer = _.filter(this.selectServers, function(server) { return server.serverId === id; });
        var servers = _.filter(this.selectServers, function(server) { return server.serverId !== id; });
        this.selectServers = servers;
        this.serverList.push(...clickServer);
      }
    }
  }
</script>

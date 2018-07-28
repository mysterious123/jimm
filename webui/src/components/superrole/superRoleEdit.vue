<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'superRole'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">编辑角色</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
    <v-text-field
       label="角色名"
       v-model="updateSuperRole.roleName"
       v-validate="'required'"
       :error-messages="vee_errors.collect('角色名','superrole_update')[0]||[]"
       data-vv-name="角色名"
       data-vv-scope="superrole_update"
     ></v-text-field>
    <!--<v-text-field-->
       <!--label="创建时间"-->
       <!--v-model="updateSuperRole.createTime"-->
       <!--v-validate="'required'"-->
       <!--:error-messages="vee_errors.collect('创建时间','superrole_update')[0]||[]"-->
       <!--data-vv-name="创建时间"-->
       <!--data-vv-scope="superrole_update"-->
     <!--&gt;</v-text-field>-->
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-card>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">菜单</v-toolbar-title>
            </v-toolbar>
            <v-card-text style="overflow-y: scroll;height: 300px">
              <ul id="role_menu_tree" class="ztree showIcon"></ul>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs6>
          <v-card>
            <v-toolbar class="cyan" dark dense>
              <v-toolbar-title class="white--text">权限</v-toolbar-title>
            </v-toolbar>
            <v-card-text style="overflow-y: scroll;height: 300px">
              <ul id="role_permission_tree" class="ztree showIcon"></ul>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="superrole_update()">
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
    data() {
      return {
        base_tree_setting: _.cloneDeep(base_tree_setting)
      }
    },
    mounted() {
      this.base_tree_setting.check = {
        enable: true,//设置zTree的节点上是否显示checkbox/radio框，默认值: false
        chkboxType: {"Y": "ps", "N": "ps"}
      };

      this.base_tree_setting.data.simpleData.pIdKey = "parent";
      let role_menu_tree_setting = _.cloneDeep(this.base_tree_setting);
      role_menu_tree_setting.data.key.name = "text"
      axios().get("/superRole/selectMenuByRoleId", {
        params : {
          id : this.updateSuperRole.id
        }
      }).then((response) => {
        $.fn.zTree.init($("#role_menu_tree"), role_menu_tree_setting, response.data).expandAll(true);
      })


      let role_permission_tree_setting = _.cloneDeep(this.base_tree_setting);
      role_permission_tree_setting.data.key.name = "description";

      axios().get("/superRole/selectPermissionByRoleId", {
        params : {
          id : this.updateSuperRole.id
        }
      }).then((response) => {
        $.fn.zTree.init($("#role_permission_tree"), role_permission_tree_setting, response.data).expandAll(true);
      })

    },
    beforeRouteEnter (to, from, next) {
      if(!store.state.superrole.updateSuperRole) {
        next({name :'superRole'})
      }else{
        next();
      }
    },
    computed: {
      ...mapState('superrole', ['updateSuperRole']),
    },
    methods:{
      superrole_update(){
        this.$validator.validateAll("superrole_update").then((result) => {
          if (result) {
            var menus = $.fn.zTree.getZTreeObj("role_menu_tree").getCheckedNodes(true);
            var permission = $.fn.zTree.getZTreeObj("role_permission_tree").getCheckedNodes(true);
            this.updateSuperRole.superMenus = _.map(menus,(item)=>{
              return {
                id : item.id,
                text : item.text,
                icon : item.icon,
                name : item.name,
                parent : item.parent,
              }
            });

            this.updateSuperRole.superPermissions = _.map(permission,(item)=>{
              return {
                id : item.id,
                description : item.description,
                permission : item.permission,
                type : item.type,
                parent : item.parent,
              }
            })

            axios().put("/superRole",this.updateSuperRole).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'superRole'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>
<style scoped>
  .ztree >>> li a.level0 span {
    font-size: 100%;
    font-weight: normal
  }
</style>

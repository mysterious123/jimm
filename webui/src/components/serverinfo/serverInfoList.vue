<template>
  <v-layout row wrap>
    <v-flex xs2 pr-2>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">服务器组</v-toolbar-title>
        </v-toolbar>
        <v-card-title>
        </v-card-title>
        <v-layout row wrap>
          <v-flex xs12 text-xs-center>
            <template v-for="item in group_op_btn">
              <v-btn icon style="margin: -20px 5px 0 5px" :class="item.class" :disabled="item.disabled"
                     @click.stop="group_op(item)">
                <v-icon>{{item.type}}</v-icon>
              </v-btn>
            </template>
          </v-flex>
          <v-flex xs12>
            <ul id="serverGroupTree_left" class="ztree showIcon"></ul>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
    <v-flex xs10>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">服务器信息</v-toolbar-title>
        </v-toolbar>
        <v-card-title>
          <v-text-field
            append-icon="search"
            label="查询"
            single-line
            hide-details
            v-model="search"
          ></v-text-field>
          <v-spacer></v-spacer>
          <v-btn icon @click="addServerInfo()" v-if="isPermitted('serverInfo/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateServerInfo()" v-if="isPermitted('serverInfo/update')" >
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delServerInfo()" v-if="isPermitted('serverInfo/delete')" >
            <v-icon>delete</v-icon>
          </v-btn>
        </v-card-title>
        <v-data-table
          :headers="headers"
          :items="items"
          :search="search"
          :loading="items_loading"
          :pagination.sync="dataTableConfig.dataTable_pagination"
          :no-data-text="dataTableConfig.dataTable_noDataText"
          :no-result-tex="dataTableConfig.dataTable_noResultsText"
          :rows-per-page-text="dataTableConfig.dataTable_rowsPerPageText"
          class="elevation-1"
          select-all
          selected-key="serverId"
          item-key="serverId"
          v-model="selected"
        >
          <template slot="headers" slot-scope="props">
            <tr>
              <th>
                <v-checkbox
                  primary
                  hide-details
                  @click.native="toggleAll"
                  :input-value="props.all"
                  :indeterminate="props.indeterminate"
                ></v-checkbox>
              </th>
              <th v-for="header in props.headers" :key="header.text"
                  :class="['column sortable', dataTableConfig.dataTable_pagination.descending ? 'desc' : 'asc', header.value === dataTableConfig.dataTable_pagination.sortBy ? 'active' : '']"
                  @click="changeSort(header.value)"
              >
                <v-icon>arrow_upward</v-icon>
                {{ header.text }}
              </th>
            </tr>
          </template>
          <template slot="items" slot-scope="props">
            <tr :active="props.selected" align="center" @click="props.selected = !props.selected">
              <td>
                <v-checkbox
                  primary
                  hide-details
                  :input-value="props.selected"
                ></v-checkbox>
              </td>
              <td>{{props.item.serverIp}}</td>
              <td>{{props.item.serverName}}</td>
              <td>{{props.item.applicationSystem}}</td>
              <td>{{props.item.serverUse}}</td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="servergroup_new_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">新增服务器组</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="服务器组名称"
            v-model="newServerGroup.serverGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器组名称','servergroup_insert')[0]||[]"
            data-vv-name="服务器组名称"
            data-vv-scope="servergroup_insert"
          ></v-text-field>
          <v-text-field
            label="服务器组简称"
            v-model="newServerGroup.iconcls"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器组简称','servergroup_insert')[0]||[]"
            data-vv-name="服务器组简称"
            data-vv-scope="servergroup_insert"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="servergroup_new_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="servergroup_insert()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="servergroup_update_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">更新服务器组</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="服务器组名称"
            v-model="updateServerGroup.serverGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器组名称','servergroup_update')[0]||[]"
            data-vv-name="服务器组名称"
            data-vv-scope="servergroup_update"
          ></v-text-field>
          <v-text-field
            label="服务器组简称"
            v-model="updateServerGroup.iconcls"
            v-validate="'required'"
            :error-messages="vee_errors.collect('服务器组简称','servergroup_update')[0]||[]"
            data-vv-name="服务器组简称"
            data-vv-scope="servergroup_update"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="servergroup_update_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="servergroup_update()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import ztree from 'ztree'
  import {base_tree_setting} from '@/assets/js/Constant';

  var newServerGroup = {
    serverGroupName: "",
    parentId: "",
    iconcls: "",
  }

  export default {
    mounted() {
      var vm = this;
      this.base_tree_setting.data.key.name = "serverGroupName";
      this.base_tree_setting.callback = {
        beforeClick(treeId, treeNode, clickFlag) {
          vm.setSelectedNode(treeNode);
          if (treeNode.level == 0) {
            vm.group_op_btn[0].disabled = false;
            vm.group_op_btn[1].disabled = false;
            vm.group_op_btn[2].disabled = true;
          } else {
            vm.group_op_btn[0].disabled = false;
            vm.group_op_btn[1].disabled = false;
            if (!treeNode.children) {
              vm.group_op_btn[2].disabled = false;
            } else {
              vm.group_op_btn[2].disabled = true;
            }
          }
          var result = "";
          var groupId = vm.findAllLeafNodeId(treeNode, result);
          if(groupId != ""){
            vm.queryItems({
              groupId: groupId,
            });
          }else{
            vm.queryItems({
              groupId:treeNode.id
            });
          }
        },
//          if (treeNode.level != 0) {
//            vm.queryItems({
//              groupId: treeNode.id,
//            });
//          }else{
//            vm.queryItems();
//          }
//        },
        beforeExpand(treeId, treeNode){
          vm.pushTreeState(treeNode);
          return true;
        },
        beforeCollapse(treeId, treeNode){
          vm.removeTreeState(treeNode);
          return true;
        }
      }
      this.getServerGroup().then((response) => {
        $.fn.zTree.init($("#serverGroupTree_left"), this.base_tree_setting, response.data);
        this.setTreeState();
        this.selectNode();
      });
      this.queryItems();
      this.clearUpdateServerInfo();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "服务器IP",
          align: 'left',
          value: "serverIp"
        },
          {
            text: "服务器名称",
            align: 'left',
            value: "serverName"
          },
          {
            text: "应用系统",
            align: 'left',
            value: "applicationSystem"
          },
          {
            text: "服务器用途",
            align: 'left',
            value: "serverUse"
          }
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        group_op_btn: [
          {title: "新增", type: "add", class: "", value: 1, disabled: true},
          {title: "编辑", type: "edit", class: "teal--text darken-1", value: 2, disabled: true},
          {title: "删除", type: "delete", class: "red--text darken-2", value: 3, disabled: true}
        ],
        newServerGroup: _.cloneDeep(newServerGroup),
        updateServerGroup: {},
        servergroup_new_dialog: false,
        servergroup_update_dialog: false,
        base_tree_setting: _.cloneDeep(base_tree_setting),

      }
    },
    methods: {
      ...mapMutations('serverinfo', [
        'setUpdateServerInfo',
        'clearUpdateServerInfo',
        'setSelectedNode',
        "clearSelectedNode",
        "pushTreeState",
        "removeTreeState"
      ]),
      //加载表格数据
      queryItems(data) {
        var vm = this;
        vm.items_loading = true;
        axios().get("/serverinfo/getServerByGroupId",{
          params : data
        }).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      addServerInfo() {
        if (this.selectedNode && this.selectedNode.isParent === false) {
          this.pagerouter({name:'serverInfoAdd'});
        } else {
          this.simpleInfo("请选择一个叶子节点的服务器组");
        }
      },
      updateServerInfo() {
        if (this.selected.length === 1) {
          this.setUpdateServerInfo(this.selected[0]);
          this.pagerouter({name: 'serverInfoEdit'});
        } else {
          this.simpleError("请选择一条记录");
        }
      },
      delServerInfo() {
        if (this.selected.length === 1) {
          axios().delete("/serverinfo", {
            params: {
              serverId: this.selected[0].serverId,
            }
          }).then(() => {
            this.simpleSuccess("删除成功");
            //清空select
            this.selected = [];
            this.queryItems();
          })
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      toggleAll() {
        if (this.selected.length) this.selected = []
        else this.selected = this.items.slice()
      },
      changeSort(column) {
        if (this.dataTableConfig.dataTable_pagination.sortBy === column) {
          this.dataTableConfig.dataTable_pagination.descending = !this.dataTableConfig.dataTable_pagination.descending
        } else {
          this.dataTableConfig.dataTable_pagination.sortBy = column
          this.dataTableConfig.dataTable_pagination.descending = false
        }
      },
      group_op(item) {
        this.$validator.reset();//清除校验信息
        if (item.type == 'add') {
          this.newServerGroup.parentId = $.fn.zTree.getZTreeObj('serverGroupTree_left').getSelectedNodes()[0].id;//获取当前点击的节点作为新建节点的parent
          this.servergroup_new_dialog = true;
        } else if (item.type == 'edit') {
          this.updateServerGroup = $.fn.zTree.getZTreeObj('serverGroupTree_left').getSelectedNodes()[0];
          this.servergroup_update_dialog = true;
        } else if (item.type == 'delete') {
          this.servergroup_delete();
        }
      },
      getServerGroup() {
        return axios().get("/servergroup");
      },
      servergroup_insert() {
        this.$validator.validateAll("servergroup_insert").then((result) => {
          if (result) {
            axios().post("/servergroup", this.newServerGroup).then(() => {
              this.simpleInfo("操作成功");
              this.newServerGroup = _.cloneDeep(newServerGroup);
              this.servergroup_new_dialog = false;
              this.getServerGroup().then((response) => {
                $.fn.zTree.init($("#serverGroupTree_left"), this.base_tree_setting, response.data);
                this.setTreeState();
                this.selectNode();
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      servergroup_update() {
        this.$validator.validateAll("servergroup_update").then((result) => {
          if (result) {
            axios().put("/servergroup", this.updateServerGroup).then(() => {
              this.simpleInfo("操作成功")
              this.servergroup_update_dialog = false;
              this.getServerGroup().then((response) => {
                $.fn.zTree.init($("#serverGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
                this.setTreeState();
                this.selectNode();
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      servergroup_delete() {
        axios().delete("/servergroup", {
          params: {
            id: $.fn.zTree.getZTreeObj('serverGroupTree_left').getSelectedNodes()[0].id
          },
        }).then(() => {
          this.simpleSuccess("删除成功");
          this.getServerGroup().then((response) => {
            $.fn.zTree.init($("#serverGroupTree_left"), this.base_tree_setting, response.data);
            this.resetNodeSelect();
            this.setTreeState();
          });
        })
      },
      resetNodeSelect() {
        this.group_op_btn[0].disabled = true;
        this.group_op_btn[1].disabled = true;
        this.group_op_btn[2].disabled = true;
        this.clearSelectedNode();
      },
      selectNode(){
        if(this.selectedNode && $.fn.zTree.getZTreeObj('serverGroupTree_left')){
          $.fn.zTree.getZTreeObj('serverGroupTree_left').selectNode(this.selectedNode);
          this.base_tree_setting.callback.beforeClick("serverGroupTree_left",
            $.fn.zTree.getZTreeObj('serverGroupTree_left').getNodesByParam("id", this.selectedNode.id, null)[0])
        }
      },
      setTreeState(){
        var tree =  $.fn.zTree.getZTreeObj('serverGroupTree_left');
        if(this.treeState && tree){
          this.treeState.forEach((node)=>{
            var node = tree.getNodeByParam("id",node.id,null);
            if(node){
              tree.expandNode(node);
            }
          })
        }
      },
      findAllLeafNodeId(treeNode, result){
        if (treeNode.isParent) {
          var childrenNodes = treeNode.children;
          if (childrenNodes) {
            for (var i = 0; i < childrenNodes.length; i++) {
              result += childrenNodes[i].id + ",";
              result = this.findAllLeafNodeId(childrenNodes[i], result);
            }
          }
        }
        return result;
      }
    },
    computed: {
      ...mapState('serverinfo', ['selectedNode','treeState']),
    }
  }
</script>

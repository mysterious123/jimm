<template>
  <v-layout row wrap>
    <v-flex xs2 pr-2>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">组织机构</v-toolbar-title>
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
            <ul id="portGroupTree_left" class="ztree showIcon"></ul>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
    <v-flex xs10>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">重要端口</v-toolbar-title>
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
          <v-btn icon @click="addPortInfo()" v-if="isPermitted('portinfo/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updatePortInfo()" v-if="isPermitted('portinfo/update')">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delPortInfo()" v-if="isPermitted('portinfo/delete')">
            <v-icon>delete</v-icon>
          </v-btn>
          <v-btn icon @click="copyPortInfo()" v-if="isPermitted('portinfo/copy')">
            <v-icon>content_copy</v-icon>
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
          selected-key="id"
          item-key="id"
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
              <td>{{props.item.deviceManageIp}}</td>
              <td>{{props.item.portName}}</td>
              <td>{{props.item.portIp}}</td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="portgroup_new_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">新增端口组</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="端口组名称"
            v-model="newPortGroup.portGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('端口组名称','portgroup_insert')[0]||[]"
            data-vv-name="端口组名称"
            data-vv-scope="portgroup_insert"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="portgroup_new_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="portgroup_insert()">
            保存


          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="portgroup_update_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">更新端口组</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="端口组名称"
            v-model="updatePortGroup.portGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('端口组名称','portgroup_update')[0]||[]"
            data-vv-name="端口组名称"
            data-vv-scope="portgroup_update"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="portgroup_update_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="portgroup_update()">
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

  var newPortGroup = {
    portGroupName: "",
    parentId: "",
    iconcls: "",
  }
  export default {
    mounted() {
      var vm = this;
      this.base_tree_setting.data.key.name = "portGroupName";
      this.base_tree_setting.callback = {
        beforeClick (treeId, treeNode, clickFlag){
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
        beforeExpand(treeId, treeNode){
          vm.pushTreeState(treeNode);
          return true;
        },
        beforeCollapse(treeId, treeNode){
          vm.removeTreeState(treeNode);
          return true;
        }
      }
      this.getPortGroup().then((response) => {
        $.fn.zTree.init($("#portGroupTree_left"), this.base_tree_setting, response.data);
        this.setTreeState();
        this.selectNode();
      });
      this.queryItems();
      this.clearUpdatePortInfo();
    },
    data() {
      return {
        search: "",
        headers: [
          {
            text: "设备管理IP",
            align: 'left',
            value: "deviceManageIp"
          },
          {
            text: "端口名称",
            align: 'left',
            value: "portName"
          },
          {
            text: "端口IP",
            align: 'left',
            value: "portIp"
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
        newPortGroup: _.cloneDeep(newPortGroup),
        updatePortGroup: {},
        portgroup_new_dialog: false,
        portgroup_update_dialog: false,
        base_tree_setting: _.cloneDeep(base_tree_setting)
      }
    },
    methods: {
      ...mapMutations('portinfo', [
        'setUpdatePortInfo',
        'clearUpdatePortInfo',
        'setSelectedNode',
        "clearSelectedNode",
        "pushTreeState",
        "removeTreeState"
      ]),
      //加载表格数据
      queryItems(data) {
        var vm = this;
        vm.items_loading = true;
        axios().get("/portinfo/getPortByGroupId", {params: data}).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      addPortInfo(){
        if (this.selectedNode && this.selectedNode.isParent === false) {
          this.pagerouter({name: 'portInfoAdd'});
        } else {
          this.simpleInfo("请先选择一个叶子节点分组");
        }
      },
      updatePortInfo(){
        if (this.selected.length === 1) {
          this.setUpdatePortInfo(this.selected[0]);
          this.pagerouter({name: 'portInfoEdit'});
        } else {
          this.simpleError("请选择一条记录");
        }
      },
      copyPortInfo(){
        if (this.selected.length === 1) {
          this.setUpdatePortInfo(this.selected[0]);
          this.pagerouter({name: 'portInfoCopy'});
        } else {
          this.simpleError("请选择一条记录");
        }
      },
      delPortInfo(){
        if (this.selected.length === 1) {
          axios().delete("/portinfo", {
            params: {
              id: this.selected[0].id,
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
      toggleAll () {
        if (this.selected.length) this.selected = []
        else this.selected = this.items.slice()
      },
      changeSort (column) {
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
          this.newPortGroup.parentId = $.fn.zTree.getZTreeObj('portGroupTree_left').getSelectedNodes()[0].id;//获取当前点击的节点作为新建节点的parent
          this.portgroup_new_dialog = true;
        } else if (item.type == 'edit') {
          this.updatePortGroup = $.fn.zTree.getZTreeObj('portGroupTree_left').getSelectedNodes()[0];
          this.portgroup_update_dialog = true;
        } else if (item.type == 'delete') {
          this.portgroup_delete();
        }
      },
      getPortGroup(){
        return axios().get("/portgroup");
      },
      portgroup_insert(){
        this.$validator.validateAll("portgroup_insert").then((result) => {
          if (result) {
            axios().post("/portgroup", this.newPortGroup).then(() => {
              this.simpleInfo("操作成功");
              this.newPortGroup = _.cloneDeep(newPortGroup);
              this.portgroup_new_dialog = false;
              this.getPortGroup().then((response) => {
                $.fn.zTree.init($("#portGroupTree_left"), this.base_tree_setting, response.data);
                this.setTreeState();
                this.selectNode();
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      portgroup_update(){
        this.$validator.validateAll("portgroup_update").then((result) => {
          if (result) {
            axios().put("/portgroup", this.updatePortGroup).then(() => {
              this.simpleInfo("操作成功")
              this.portgroup_update_dialog = false;
              this.getPortGroup().then((response) => {
                $.fn.zTree.init($("#portGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      portgroup_delete(){
        axios().delete("/portgroup", {
          params: {
            id: $.fn.zTree.getZTreeObj('portGroupTree_left').getSelectedNodes()[0].id
          },
        }).then(() => {
          this.simpleSuccess("删除成功");
          this.getPortGroup().then((response) => {
            $.fn.zTree.init($("#portGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
            this.resetNodeSelect();
            this.setTreeState();
          });
        })
      },
      resetNodeSelect(){
        this.group_op_btn[0].disabled = true;
        this.group_op_btn[1].disabled = true;
        this.group_op_btn[2].disabled = true;
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
      ...mapState('portinfo', ['selectedNode', 'treeState'])
    }

  }
</script>

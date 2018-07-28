<template>
  <v-layout row wrap>
    <v-flex xs2 pr-2>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">管理层级</v-toolbar-title>
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
            <ul id="manageGroup_left" class="ztree showIcon"></ul>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
    <v-flex xs10>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">管理组</v-toolbar-title>
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
          <v-btn icon @click="addManageGroupInfo()" v-if="isPermitted('manageGroupInfo/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateManageGroupInfo()" v-if="isPermitted('manageGroupInfo/update')">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delManageGroupInfo()" v-if="isPermitted('manageGroupInfo/delete')">
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
              <td>{{props.item.manageGroupName}}</td>
              <td>{{props.item.memberNum}}</td>
              <td>{{getDate(props.item.createTime)}}</td>

            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="manageGroup_new_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">新增管理层级</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="管理组名"
            v-model="newManageGroup.manageGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('管理组名','manageGroup_insert')[0]||[]"
            data-vv-name="管理层级名称"
            data-vv-scope="manageGroup_insert"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="manageGroup_new_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="manageGroup_insert()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="manageGroup_update_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">编辑管理层级</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="管理组名"
            v-model="updateManageGroup.manageGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('部门名称','manageGroup_update')[0]||[]"
            data-vv-name="管理组名"
            data-vv-scope="manageGroup_update"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="manageGroup_update_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="manageGroup_update()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
  import {axios, getFormatDate} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import ztree from 'ztree'
  import {base_tree_setting} from '@/assets/js/Constant';

  var newManageGroup = {
    id : "",
    manageGroupName : "",
    parentId : ""
  }
  export default {
    mounted() {
      var vm = this;
      this.base_tree_setting.data.key.name = "manageGroupName";
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
          if (treeNode.level != 0) {
            vm.queryItems({
              manageId: treeNode.id,
            });
          }else{
            vm.queryItems();
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

      this.getManageGroup().then((response) => {
        $.fn.zTree.init($("#manageGroup_left"), this.base_tree_setting, response.data);
        this.setTreeState();
        this.selectNode();
      });
      this.queryItems();
      this.clearUpdateManageGroupInfo();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "管理组名",
          align: 'left',
          value: "manageGroupName"
        },
          {
            text: "组员数",
            align: 'left',
            value: "memberNum"
          },
          {
            text: "创建时间",
            align: 'left',
            value: "createTime"
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
        newManageGroup: _.cloneDeep(newManageGroup),
        updateManageGroup: {},
        manageGroup_new_dialog: false,
        manageGroup_update_dialog: false,
        base_tree_setting: _.cloneDeep(base_tree_setting),
      }
    },
    methods: {
      ...mapMutations('managegroupinfo', [
        'setUpdateManageGroupInfo',
        'clearUpdateManageGroupInfo',
        'setSelectedNode',
        "clearSelectedNode",
        "pushTreeState",
        "removeTreeState"
      ]),
      //加载表格数据
      queryItems(data) {
        var vm = this;
        vm.items_loading = true;
        axios().get("/manageGroupInfo",{
          params : data
        }).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      addManageGroupInfo() {
        if (this.selectedNode && this.selectedNode.level != 0) {
          this.pagerouter({name:'manageGroupInfoAdd'});
        } else {
          this.simpleInfo("请选择一个管理层级");
        }
      },
      updateManageGroupInfo() {
        if (this.selected.length === 1) {
          this.setUpdateManageGroupInfo(this.selected[0]);
          this.pagerouter({name: 'manageGroupInfoEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      delManageGroupInfo() {
        if (this.selected.length === 1) {
          axios().delete("/manageGroupInfo", {
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
      getManageGroup() {
        return axios().get("/manageGroup");
      },
      group_op(item) {
        this.$validator.reset();//清除校验信息
        if (item.type == 'add') {
          this.newManageGroup.parentId = $.fn.zTree.getZTreeObj('manageGroup_left').getSelectedNodes()[0].id;//获取当前点击的节点作为新建节点的parent
          this.manageGroup_new_dialog = true;
        } else if (item.type == 'edit') {
          this.updateManageGroup = $.fn.zTree.getZTreeObj('manageGroup_left').getSelectedNodes()[0];
          this.manageGroup_update_dialog = true;
        } else if (item.type == 'delete') {
          this.manageGroup_delete();
        }
      },
      manageGroup_insert(){
        this.$validator.validateAll("manageGroup_insert").then((result) => {
          if (result) {
            axios().post("/manageGroup", this.newManageGroup).then(() => {
              this.simpleInfo("操作成功");
              this.newManageGroup = _.cloneDeep(newManageGroup);
              this.manageGroup_new_dialog = false;
              this.getManageGroup().then((response) => {
                $.fn.zTree.init($("#manageGroup_left"), this.base_tree_setting, response.data);
                this.setTreeState();
                this.selectNode();
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      manageGroup_update(){
        this.$validator.validateAll("manageGroup_update").then((result) => {
          if (result) {
            axios().put("/manageGroup", this.updateManageGroup).then(() => {
              this.simpleInfo("操作成功");
              this.manageGroup_update_dialog = false;
              this.getManageGroup().then((response) => {
                $.fn.zTree.init($("#manageGroup_left"), this.base_tree_setting, response.data);
                this.setTreeState();
                this.selectNode();
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      manageGroup_delete() {
        axios().delete("/manageGroup", {
          params: {
            id: $.fn.zTree.getZTreeObj('manageGroup_left').getSelectedNodes()[0].id
          },
        }).then(() => {
          this.simpleSuccess("删除成功");
          this.getManageGroup().then((response) => {
            $.fn.zTree.init($("#manageGroup_left"), this.base_tree_setting, response.data);
            this.resetNodeSelect();
            this.setTreeState();
          });
        })
        this.queryItems();
      },
      resetNodeSelect() {
        this.group_op_btn[0].disabled = true;
        this.group_op_btn[1].disabled = true;
        this.group_op_btn[2].disabled = true;
        this.clearSelectedNode();
      },
      selectNode(){
        if(this.selectedNode && $.fn.zTree.getZTreeObj('manageGroup_left')){
          $.fn.zTree.getZTreeObj('manageGroup_left').selectNode(this.selectedNode);
          this.base_tree_setting.callback.beforeClick("manageGroup_left",
            $.fn.zTree.getZTreeObj('manageGroup_left').getNodesByParam("id", this.selectedNode.id, null)[0])
        }
      },
      setTreeState(){
        var tree =  $.fn.zTree.getZTreeObj('manageGroup_left');
        if(this.treeState && tree){
          this.treeState.forEach((node)=>{
            var node = tree.getNodeByParam("id",node.id,null);
            if(node){
              tree.expandNode(node);
            }
          })
        }
      },
      getDate(longTime)
      {
        return getFormatDate(longTime);
      }
    },
    computed: {
      ...mapState('managegroupinfo', ['selectedNode','treeState']),
    }
  }
</script>

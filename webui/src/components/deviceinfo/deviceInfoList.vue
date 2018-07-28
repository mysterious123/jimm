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
            <ul id="deviceGroupTree_left" class="ztree showIcon"></ul>
          </v-flex>
        </v-layout>
      </v-card>
    </v-flex>
    <v-flex xs10>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">网络设备</v-toolbar-title>
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
          <v-btn icon @click="addDeviceInfo()" v-if="isPermitted('devicegroup/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateDeviceInfo()" v-if="isPermitted('devicegroup/update')">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delDeviceInfo()" v-if="isPermitted('devicegroup/delete')">
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
              <td>{{props.item.manageIp}}</td>
              <td>{{props.item.deviceName}}</td>
              <td>{{props.item.deviceOtherName}}</td>
              <td>{{props.item.deviceType}}</td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="devicegroup_new_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">新增网络设备组</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="组织机构名称"
            v-model="newDeviceGroup.networkDeviceGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('网络设备组名称','group_insert')[0]||[]"
            data-vv-name="网络设备组名称"
            data-vv-scope="group_insert"
          ></v-text-field>
          <v-text-field
            label="组织机构简称"
            v-model="newDeviceGroup.iconcls"
            data-vv-name="组织机构简称"
            data-vv-scope="group_insert"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="devicegroup_new_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="devicegroup_insert()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="devicegroup_update_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">更新组织机构</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
            label="组织机构名称"
            v-model="updateDeviceGroup.networkDeviceGroupName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('组织机构名称','devicegroup_update')[0]||[]"
            data-vv-name="网络设备组名称"
            data-vv-scope="group_update"
          ></v-text-field>
          <v-text-field
            label="组织机构简称"
            v-model="updateDeviceGroup.iconcls"
            data-vv-name="组织机构简称"
            data-vv-scope="group_update"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="devicegroup_update_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="devicegroup_update()">
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
  import ztree from 'ztree';
  import {base_tree_setting} from '@/assets/js/Constant';

  var newDeviceGroup = {
    networkDeviceGroupName: "",
    parentId: "",
    iconcls: "",
  }

  export default {
    mounted() {
      var vm = this;
      this.base_tree_setting.data.key.name = "networkDeviceGroupName";
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
      this.getDeviceGroup().then((response) => {
        $.fn.zTree.init($("#deviceGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
      });
      this.queryItems();
      this.clearUpdateDeviceInfo();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "管理IP",
          align: 'left',
          value: "manageIp"
        },
          {
            text: "设备名称",
            align: 'left',
            value: "deviceName"
          },
          {
            text: "设备别名",
            align: 'left',
            value: "deviceOtherName"
          },
          {
            text: "设备类型",
            align: 'left',
            value: "deviceType"
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
        newDeviceGroup: _.cloneDeep(newDeviceGroup),
        updateDeviceGroup: {},
        devicegroup_new_dialog: false,
        devicegroup_update_dialog: false,
        base_tree_setting: _.cloneDeep(base_tree_setting),
      }
    },
    methods: {
      ...mapMutations('deviceinfo', [
        'setUpdateDeviceInfo',
        'clearUpdateDeviceInfo',
        'setSelectedNode',
        "clearSelectedNode",
        "pushTreeState",
        "removeTreeState"
      ]),


      //加载表格数据
      queryItems(data) {
        var vm = this;
        vm.items_loading = true;
        axios().get("/deviceinfo/getDeviceByGroupId", {params: data}).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      addDeviceInfo() {
        if (this.selectedNode && this.selectedNode.isParent === false) {
          this.pagerouter({name: 'deviceInfoAdd'});
        } else {
          this.simpleInfo("请先选择叶子节点的分组");
        }
      },
      updateDeviceInfo(){
        if (this.selected.length === 1) {
          this.setUpdateDeviceInfo(this.selected[0]);
          this.pagerouter({name: 'deviceInfoEdit'});
        } else {
          this.simpleError("请选择一条记录");
        }
      },
      delDeviceInfo(){
        if (this.selected.length === 1) {
          axios().delete("/deviceinfo", {
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
          this.newDeviceGroup.parentId = $.fn.zTree.getZTreeObj('deviceGroupTree_left').getSelectedNodes()[0].id;//获取当前点击的节点作为新建节点的parent
          this.devicegroup_new_dialog = true;
        } else if (item.type == 'edit') {
          this.updateDeviceGroup = $.fn.zTree.getZTreeObj('deviceGroupTree_left').getSelectedNodes()[0];
          this.devicegroup_update_dialog = true;
        } else if (item.type == 'delete') {
          this.devicegroup_delete();
        }
      },
      getDeviceGroup(){
        return axios().get("/devicegroup");
      },
      devicegroup_insert(){
        this.$validator.validateAll("group_insert").then((result) => {
          if (result) {
            axios().post("/devicegroup", this.newDeviceGroup).then(() => {
              this.simpleInfo("操作成功");
              this.newDeviceGroup = _.cloneDeep(newDeviceGroup);
              this.devicegroup_new_dialog = false;
              this.getDeviceGroup().then((response) => {
                $.fn.zTree.init($("#deviceGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      devicegroup_update(){
        this.$validator.validateAll("devicegroup_update").then((result) => {
          if (result) {
            axios().put("/devicegroup", this.updateDeviceGroup).then(() => {
              this.simpleInfo("操作成功")
              this.devicegroup_update_dialog = false;
              this.getDeviceGroup().then((response) => {
                $.fn.zTree.init($("#deviceGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
              });
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      devicegroup_delete(){
        axios().delete("/devicegroup", {
          params: {
            id: $.fn.zTree.getZTreeObj('deviceGroupTree_left').getSelectedNodes()[0].id
          },
        }).then(() => {
          this.simpleSuccess("删除成功");
          this.getDeviceGroup().then((response) => {
            this.resetNodeSelect();
            $.fn.zTree.init($("#deviceGroupTree_left"), this.base_tree_setting, response.data).expandAll(true);
          });
        })
      },
      resetNodeSelect(){
        this.group_op_btn[0].disabled = true;
        this.group_op_btn[1].disabled = true;
        this.group_op_btn[2].disabled = true;
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
      ...mapState('deviceinfo', ['selectedNode', 'treeState'])
    }
  }

</script>

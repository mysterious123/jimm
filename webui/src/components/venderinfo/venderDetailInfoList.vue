<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-btn icon @click="pagerouter({name:'venderInfo'})" dark>
            <v-icon>arrow_back</v-icon>
          </v-btn>
          <v-toolbar-title class="white--text">{{ e1 }}</v-toolbar-title>
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
          <v-btn icon @click="addvenderDetailInfo()" v-if="isPermitted('venderDetailInfo/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updatevenderDetailInfo()" v-if="isPermitted('venderDetailInfo/update')">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delvenderDetailInfo()" v-if="isPermitted('venderDetailInfo/delete')">
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
              <td>{{props.item.deviceType}}</td>
              <td>{{props.item.deviceVersion}}</td>
              <td>{{props.item.objectId}}</td>

            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="venderdetail_new_dialog" persistent>
      <v-card>
        <v-toolbar color="teal lighten-3" dark>
          <v-toolbar-title>信息</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <async-select
            label="设备类型"
            :v-data-params="{type : 'device_type'}"
            v-model="newVenderDetailInfo.deviceType"
            :error-messages="vee_errors.collect('设备类型','venderdetail_insert')[0]||[]"
            data-vv-name="设备类型"
            data-vv-scope="venderdetail_insert"
          ></async-select>
          <v-text-field
            label="设备型号"
            v-model="newVenderDetailInfo.deviceVersion"
            data-vv-name="设备型号"
            data-vv-scope="venderdetail_insert"
          ></v-text-field>
          <v-text-field
            label="ObjectId"
            v-model="newVenderDetailInfo.objectId"
            v-validate="'required|unique'"
            :error-messages="vee_errors.collect('ObjectID','venderdetail_insert')[0]||[]"
            data-vv-name="ObjectID"
            data-vv-scope="venderdetail_insert"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="venderdetail_new_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="venderdetail_insert()">
            保存



          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="venderdetail_update_dialog" persistent>
      <v-card>
        <v-toolbar color="teal lighten-3" dark>
          <v-toolbar-title>信息</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <async-select
            label="设备类型"
            :v-data-params="{type : 'device_type'}"
            v-model="updateVenderDetailInfo.deviceType"
            :error-messages="vee_errors.collect('设备类型','venderdetail_update')[0]||[]"
            data-vv-name="设备类型"
            data-vv-scope="venderdetail_update"
          ></async-select>
          <v-text-field
            label="设备型号"
            v-model="updateVenderDetailInfo.deviceVersion"
            data-vv-name="设备型号"
            data-vv-scope="venderdetail_update"
          ></v-text-field>
          <v-text-field
            label="ObjectId"
            v-model="updateVenderDetailInfo.objectId"
            data-vv-name="ObjectID"
            data-vv-scope="venderdetail_update"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="venderdetail_update_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="venderdetail_update()">
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
  import asyncSelect from '@/components/common/asyncSelect';
  import store from '@/store';
  import {Validator} from 'vee-validate';

  var newVenderDetailInfo = {
    deviceType: "",
    deviceVersion: "",
    objectId: "",
    venderId: ""
  }
  var venderInfo = {
    id: "",
    venderName: "",
  }
  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.venderinfo.venderInfo) {
        next({name: 'venderInfo'})
      } else {
        next();
      }
    },
    mounted() {
      this.queryItems(
      );
      this.clearUpdatevenderDetailInfo();
      Validator.extend('unique', {
        getMessage: field => `该${field}已经存在`,
        validate: function (value) {
          return new Promise((resolve) => {
            axios().get("/venderDetailInfo/checkObjectIdExist",{
              params: {
                objectId:value
              }
            }).then(function (response) {
              resolve({
                valid: response.data === 0
              });
            })
          })
        }
      });
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "设备类型",
          align: 'left',
          value: "deviceType"
        },
          {
            text: "设备型号",
            align: 'left',
            value: "deviceVersion"
          },
          {
            text: "OBJECTID",
            align: 'left',
            value: "objectId"
          }
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        newVenderDetailInfo: {},
        updateVenderDetailInfo: {},
        venderdetail_new_dialog: false,
        venderdetail_update_dialog: false,
        e1: store.state.venderinfo.venderInfo.venderName
      }
    },
    components: {
      "async-select": asyncSelect
    },
    methods: {
      ...mapMutations('venderdetailinfo', [
        'setUpdatevenderDetailInfo',
        'clearUpdatevenderDetailInfo',
      ]),

      //加载表格数据
      queryItems() {
        var vm = this;
        var data = {venderId: store.state.venderinfo.venderInfo.id};
        vm.items_loading = true;
        axios().get("/venderDetailInfo", {params: data}).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      addvenderDetailInfo(){
        this.venderdetail_new_dialog = true;
      },
      venderdetail_insert(){
        this.$validator.validateAll("venderdetail_insert").then((result) => {
          if (result) {
            this.newVenderDetailInfo.venderId = store.state.venderinfo.venderInfo.id;
            axios().post("/venderDetailInfo", this.newVenderDetailInfo).then(() => {
              this.simpleInfo("操作成功");
              this.venderdetail_new_dialog = false;
              this.newVenderDetailInfo = {};
              this.queryItems();
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      updatevenderDetailInfo(){
        if (this.selected.length === 1) {
          this.updateVenderDetailInfo = this.selected[0];
          this.venderdetail_update_dialog = true;
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      venderdetail_update(){
        this.$validator.validateAll("venderdetail_update").then((result) => {
          if (result) {
            axios().put("/venderDetailInfo", this.updateVenderDetailInfo).then(() => {
              this.simpleInfo("编辑成功");
              this.venderdetail_update_dialog = false;
              this.queryItems();

            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      delvenderDetailInfo(){
        if (this.selected.length === 1) {
          axios().delete("/venderDetailInfo", {
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
      }
    }
  }
</script>

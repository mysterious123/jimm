<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">程序</v-toolbar-title>
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
          <v-btn icon @click="pagerouter({name:'superAutomationAdd'})">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateSuperAutomation()">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delSuperAutomation()">
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
              <td>{{props.item.appName}}</td>
              <td>{{props.item.programmeName}}</td>
              <td v-if="props.item.status == 'true'"> <span style="color: #00B83F">开启</span></td>
              <td v-else=""><span style="color: #a92222">关闭</span></td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
  </v-layout>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';

  export default {
    mounted() {
      this.queryItems();
      this.clearUpdateSuperAutomation();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "应用名",
          align: 'left',
          value: "appName"
        },
          {
            text: "程序名",
            align: 'left',
            value: "programmeName"
          },
          {
            text: "状态",
            align: 'left',
            value: "status"
          },
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
      }
    },
    methods: {
      ...mapMutations('superautomation', [
        'setUpdateSuperAutomation',
        'clearUpdateSuperAutomation'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/superAutomation").then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      updateSuperAutomation(){
        if (this.selected.length === 1) {
          this.setUpdateSuperAutomation(this.selected[0]);
          this.pagerouter({name: 'superAutomationEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      delSuperAutomation(){
        if (this.selected.length === 1) {
          axios().delete("/superAutomation", {
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

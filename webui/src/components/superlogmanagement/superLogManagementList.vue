<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">系统日志</v-toolbar-title>
        </v-toolbar>
        <v-card-title>
          <date-picker v-model="timeRange" range type="datetime" format="yyyy-MM-dd HH:mm:ss"></date-picker>
          <v-text-field
            label="操作内容"
            v-model="queryContent"
            style="margin-left: 2em"
          ></v-text-field>
          <v-btn color="primary" @click="queryLogManagement()" left>查询</v-btn>
          <v-spacer></v-spacer>

          <v-btn icon @click="delSuperLogManagement()">
            <v-icon>delete</v-icon>
          </v-btn>
        </v-card-title>
        <v-data-table
          :headers="headers"
          :items="items"
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
              <td>{{props.item.userName}}</td>
              <td>{{getDate(props.item.createTime)}}</td>
              <td>{{props.item.operateContent}}</td>
              <td v-if="props.item.operateResult == '0'"><span style="color: #00B83F">成功</span></td>
              <td v-else=""><span style="color: #a92222">失败</span></td>

            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
  </v-layout>
</template>
<script>
  import {axios, getFormatDate} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import myDatepicker from 'vue2-datepicker'


  export default {
    mounted() {
      this.queryItems();
      this.clearUpdateSuperLogManagement();
    },
    data() {
      return {
//        search: "", //页面查询模型
        headers: [{
          text: "用户名",
          align: 'left',
          value: "userName"
        },
          {
            text: "日期",
            align: 'left',
            value: "createTime"
          },
          {
            text: "操作内容",
            align: 'left',
            value: "operateContent"
          },
          {
            text: "操作结果",
            align: 'left',
            value: "operateResult"
          },
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        dateRangeDropIsActive: false,
        timeRange: '',
        queryContent: ''
//        shortcuts: [
//          {
//            text: 'Today',
//            start: new Date(),
//            end: new Date()
//          }
//        ]
      }
    },
    components: {
      'date-picker': myDatepicker,
    },
    methods: {
      ...mapMutations('superlogmanagement', [
        'setUpdateSuperLogManagement',
        'clearUpdateSuperLogManagement'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/superLogManagement").then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      queryLogManagement(){
        var vm = this;
        var startTime = "";
        var endTime = "";
        vm.items_loading = true;
        if (vm.timeRange.length === 2) {
          startTime = vm.timeRange[0].getTime();
          endTime = vm.timeRange[1].getTime();
        }
        axios().get("/superLogManagement/queryLogManagement?startTime=" + startTime + "&endTime=" + endTime + "&content=" + vm.queryContent).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      updateSuperLogManagement(){
        if (this.selected.length === 1) {
          this.setUpdateSuperLogManagement(this.selected[0]);
          this.pagerouter({name: 'superLogManagementEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      delSuperLogManagement(){
        if (this.selected.length === 1) {
          axios().delete("/superLogManagement", {
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
      getDate(longTime){
        return getFormatDate(longTime);
      }
    }
  }
</script>

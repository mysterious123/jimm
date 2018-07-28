<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">级别阈值微调</v-toolbar-title>
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
          <v-btn icon @click="pagerouter({name:'policyCustomAdd'})" v-if="isPermitted('policyCustom/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updatePolicyCustom()" v-if="isPermitted('policyCustom/update')">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delPolicyCustom()" v-if="isPermitted('policyCustom/delete')">
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
              <td>{{props.item.keyName}}</td>
              <td>{{props.item.indexType}}</td>
              <td>{{props.item.indexName}}</td>
              <td>{{props.item.thresholdA}}</td>
              <td>{{props.item.levelA}}</td>
              <td>{{props.item.thresholdB}}</td>
              <td>{{props.item.levelB}}</td>
              <td>{{props.item.createPerson}}</td>

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
      this.clearUpdatePolicyCustom();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [
          {
            text: "关键字",
            align: 'left',
            value: "keyName"
          },
          {
            text: "指标类型",
            align: 'left',
            value: "indexType"
          },
          {
            text: "指标名称",
            align: 'left',
            value: "indexName"
          },

          {
            text: "阀值A",
            align: 'left',
            value: "thresholdA"
          },
          {
            text: "级别A",
            align: 'left',
            value: "levelA"
          },
          {
            text: "阀值B",
            align: 'left',
            value: "thresholdB"
          },
          {
            text: "级别B",
            align: 'left',
            value: "levelB"
          },
          {
            text: "创建人",
            align: 'left',
            value: "createPerson"
          },
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
      }
    },
    methods: {
      ...mapMutations('policycustom', [
        'setUpdatePolicyCustom',
        'clearUpdatePolicyCustom'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/policyCustom").then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      updatePolicyCustom(){
        if (this.selected.length === 1) {
          this.setUpdatePolicyCustom(this.selected[0]);
          this.pagerouter({name: 'policyCustomEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      delPolicyCustom(){
        if (this.selected.length === 1) {
          axios().delete("/policyCustom", {
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

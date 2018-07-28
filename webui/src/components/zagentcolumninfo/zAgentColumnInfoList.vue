<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-card-title>
          <v-text-field
            append-icon="search"
            label="查询"
            single-line
            hide-details
            v-model="search"
          ></v-text-field>
          <v-spacer></v-spacer>
          <v-btn icon @click="pagerouter({name:'zAgentColumnInfoAdd'})">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateZAgentColumnInfo()">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delZAgentColumnInfo()">
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
              <td>{{props.item.id}}</td>
              <td>{{props.item.columnName}}</td>
              <td>{{props.item.columnType}}</td>
              <td>{{props.item.regularExpression}}</td>
              <td>{{props.item.columnExplain}}</td>

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
  import store from '@/store';

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.zagentinfo.updateZAgentInfo) {
        next({name:'zAgentInfo'})
      } else {
        next();
      }
    },
    mounted() {
      this.queryItems();
      this.clearUpdateZAgentColumnInfo();
    },
    data() {
      return {
        headers: [
          {
            text: "编号",
            align: 'left',
            value: "id"
          },
          {
            text: "字段名",
            align: 'left',
            value: "columnName"
          },
          {
            text: "字段类型",
            align: 'left',
            value: "columnType"
          },
          {
            text: "正则表达式",
            align: 'left',
            value: "regularExpression"
          },
          {
            text: "字段说明",
            align: 'left',
            value: "columnExplain"
          },
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
      }
    },
    computed: {
      search: {
        get() {
          return this.$store.state.zagentcolumninfo.search
        },
        set(value) {
          this.setSearch(value);
        }
      }
    },
    methods: {
      ...mapMutations('zagentcolumninfo', [
        'setUpdateZAgentColumnInfo',
        'clearUpdateZAgentColumnInfo',
        'setSearch'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/zAgentColumnInfo",{
          params:{
            agentId : this.$store.state.zagentinfo.updateZAgentInfo.id
          }
        }).then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      updateZAgentColumnInfo() {
        if (this.selected.length === 1) {
          this.setUpdateZAgentColumnInfo(this.selected[0]);
          this.pagerouter({name: 'zAgentColumnInfoEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      delZAgentColumnInfo() {
        if (this.selected.length === 1) {
          axios().delete("/zAgentColumnInfo", {
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
      }
    }
  }
</script>

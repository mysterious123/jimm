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
          <v-btn icon @click="pagerouter({name:'zMibOidAdd'})">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateZMibOid()">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delZMibOid()">
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
            <!--<td>{{props.item.id}}</td>-->
            <td>{{props.item.oidName}}</td>
            <td>{{props.item.oidUnit}}</td>
            <td>{{props.item.oidValue}}</td>
            <!--<td>{{props.item.smallType}}</td>-->
            <!--<td>{{props.item.bps}}</td>-->
            <!--<td>{{props.item.local}}</td>-->
            <!--<td>{{props.item.endOn}}</td>-->

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
    beforeRouteEnter(to, from, next) {
      if (!store.state.zmibinfo.updatezMibInfo) {
        next({name:'zMibInfo'})
      } else {
        next();
      }
    },
    computed: {
      ...mapState('zmibinfo', ['updatezMibInfo']),
    },
    mounted() {
      this.queryItems();
      this.clearUpdateZMibOid();
    },
    data() {
      return {
        headers:[
//            {
//            text: "OID编号",
//            align: 'left',
//            value: "id"
//          },
          {
            text: "OID名称",
            align: 'left',
            value: "oidName"
          },
          {
            text: "OID单位",
            align: 'left',
            value: "oidUnit"
          },
          {
            text: "OID值",
            align: 'left',
            value: "oidValue"
          },
//          {
//            text: "子类",
//            align: 'left',
//            value: "smallType"
//          },
//          {
//            text: "BPS",
//            align: 'left',
//            value: "bps"
//          },
//          {
//            text: "本端",
//            align: 'left',
//            value: "local"
//          },
//          {
//            text: "对端",
//            align: 'left',
//            value: "endOn"
//          },
          ], //列表表头
        items:[],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
      }
    },
    computed: {
      search:{
        get(){
          return this.$store.state.zmiboid.search
        },
        set(value){
          this.setSearch(value);
        }
      }
    },
    methods : {
     ...mapMutations('zmiboid', [
       'setUpdateZMibOid',
       'clearUpdateZMibOid',
       'setSearch'
     ]),
     //加载表格数据
     queryItems() {
       var vm = this;
       vm.items_loading = true;
       axios().get("/zMibOid",{
         params : {mibId : this.$store.state.zmibinfo.updatezMibInfo.id}
       }).then(function (response) {
         vm.items = response.data;
         vm.items_loading = false;
       })
     },
     updateZMibOid(){
        if(this.selected.length === 1){
          this.setUpdateZMibOid(this.selected[0]);
          this.pagerouter({name:'zMibOidEdit'});
        }else{
          this.simpleInfo("请选择一条记录");
        }
      },
      delZMibOid(){
        if(this.selected.length === 1){
          axios().delete("/zMibOid",{
            params : {
              id : this.selected[0].id,
              }
          }).then(()=> {
            this.simpleSuccess("删除成功");
            //清空select
            this.selected = [];
            this.queryItems();
          })
        }else{
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

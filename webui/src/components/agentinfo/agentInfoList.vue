<template>
  <v-layout row wrap>
    <v-flex d-flex xs12 sm4>
      <div id="myChart"></div>
    </v-flex>
    <v-flex d-flex xs12 sm4>
      <v-layout row wrap>
        <v-flex
          v-bind="{ [`xs${card.flex}`]: true }"
          v-for="card in agentcards"
          :key="card.title"
        >
          <v-card :color="card.color">
            <v-card-title primary-title>
              <div class="headline">{{card.title}}</div>
            </v-card-title>
            <p class="text-xs-center" style="margin-top: -30px">
              <span style="font-size: 5em;cursor:pointer">{{card.count}}</span>
            </p>
          </v-card>
        </v-flex>
      </v-layout>
    </v-flex>
    <v-flex d-flex xs12 sm4>
      <v-layout row wrap>
        <v-flex xs6 v-for="card in cards" :key="card.name">
          <v-card :color="card.color" class="white--text">
            <v-card-title primary-title>
              <div class="headline">{{card.name}}</div>
            </v-card-title>
            <p class="text-xs-center" style="margin-top: -30px">
              <span style="font-size: 5em;cursor:pointer">{{card.count}}</span>
            </p>
          </v-card>
        </v-flex>
      </v-layout>
    </v-flex>

    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">代理注册</v-toolbar-title>
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
          <v-btn v-if="isPermitted('agentInfo/insert')"  icon @click="pagerouter({name:'agentInfoAdd'})">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn v-if="isPermitted('agentInfo/update')" icon @click="updateAgentInfo()">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn v-if="isPermitted('agentInfo/delete')" icon @click="delAgentInfo()">
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
              <td>{{props.item.agentName}}</td>
              <td>{{props.item.agentType}}</td>
              <td>{{props.item.agentAb}}</td>
              <td>{{props.item.agentVersion}}</td>
              <td>{{props.item.founder}}</td>
              <td>{{getDate(props.item.createTime)}}</td>

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
  import VBreadcrumbs from "vuetify/es5/components/VBreadcrumbs/VBreadcrumbs";
  import echarts from 'echarts'
  export default {
    components: {
      VBreadcrumbs,
    },
    mounted() {
      this.queryItems();
      this.clearUpdateAgentInfo();
      this.drawLine();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "代理名称",
          align: 'left',
          value: "agentName"
        },
          {
            text: "代理类型",
            align: 'left',
            value: "agentType"
          },
          {
            text: "代理缩写",
            align: 'left',
            value: "agentAb"
          },
          {
            text: "代理版本",
            align: 'left',
            value: "agentVersion"
          },
          {
            text: "创建人",
            align: 'left',
            value: "founder"
          },
          {
            text: "创建时间",
            align: 'left',
            value: "createTime"
          },
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        cards: [
          {
            name: 'OS',
            count: 0,
            color: "green",
          }, {
            name: 'MW',
            count: 0,
            color: "cyan",
          }, {
            name: 'DB',
            count: 0,
            color: "blue darken-3",
          }, {
            name: 'CUS',
            count: 0,
            color: "blue-grey darken",
          }
        ],
        agentcards: [
          {title: '已注册代理数', flex: 12, count: 0, color: "blue-grey darken"},
          {title: '在线', flex: 6, count: 0, color: "green"},
          {title: '离线', flex: 6, count: 0, color: "red"}
        ],
      }
    },
    methods: {
      ...mapMutations('agentinfo', [
        'setUpdateAgentInfo',
        'clearUpdateAgentInfo'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        var osList = [],
          mwList = [],
          dbList = [],
          cusList = [];
        axios().get("/agentInfo").then(function (response) {
          vm.items = response.data;
          vm.agentcards[0].count = response.data.length;
          for (var i = 0; i < response.data.length; i++) {
            if (response.data[i].agentType == '操作系统')
              osList.push(response.data[i])
            else if (response.data[i].agentType == '数据库')
              dbList.push(response.data[i])
            else if (response.data[i].agentType == '中间件')
              mwList.push(response.data[i])
            else if (response.data[i].agentType == '自定义')
              cusList.push(response.data[i])
          }
          vm.cards[0].count = osList.length;
          vm.cards[1].count = mwList.length;
          vm.cards[2].count = dbList.length;
          vm.cards[3].count = cusList.length;
          vm.items_loading = false;
        })
      },
      updateAgentInfo(){
        if (this.selected.length === 1) {
          this.setUpdateAgentInfo(this.selected[0]);
          this.pagerouter({name: 'agentInfoEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      agentinfo_update(){
        this.$validator.validateAll("agentinfo_update").then((result) => {
          if (result) {
            axios().put("/agentInfo", this.updateAgentInfoA).then(() => {
              this.simpleInfo("操作成功");
              this.agentinfo_update_dialog = false;
              this.selected = [];
              this.queryItems();
              this.drawLine();
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      delAgentInfo(){
        if (this.selected.length === 1) {
          axios().delete("/agentInfo", {
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
      drawLine(){
        var myChart = echarts.init(document.getElementById('myChart'));
        myChart.setOption({
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            data: ['操作系统', '数据库', '中间件', '自定义', '其他']
          },
          series: [
            {
              name: '代理类型',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '30',
                    fontWeight: 'bold'
                  }
                }
              },
              data: []
            }
          ]
        });
        this.queryPieCharts();
      },
      queryPieCharts(){
        axios().get("/agentInfo/queryTypeCounts").then(function (response) {
          var myChart = echarts.init(document.getElementById('myChart'));
          myChart.setOption({
            series: [{
              data: response.data
            }]
          });
        })
      },
      getDate(longTime){
        return getFormatDate(longTime);
      }
    }
  }
</script>

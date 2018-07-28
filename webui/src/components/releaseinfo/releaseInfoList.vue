<template>
  <v-layout row wrap>
    <v-tabs dark grow>
      <v-tabs-bar class="cyan" dark>
        <v-tabs-slider class="yellow"></v-tabs-slider>
        <v-tabs-item href="#tab-1">
          发布对象


        </v-tabs-item>
        <v-tabs-item href="#tab-2">
          发布历史


        </v-tabs-item>
      </v-tabs-bar>
      <v-tabs-items>
        <v-tabs-content
          :id="'tab-1'"
        >
          <v-flex xs12>
            <v-card>
              <v-card-title>
                <v-spacer></v-spacer>
                <v-btn icon @click="pagerouter({name:'releaseInfoAdd'})" v-if="isPermitted('releaseInfo/insert')">
                  <v-icon>add</v-icon>
                </v-btn>
                <v-btn icon @click="updatereleaseInfo()" v-if="isPermitted('releaseInfo/update')">
                  <v-icon>edit</v-icon>
                </v-btn>
                <v-btn icon @click="delreleaseInfo()" v-if="isPermitted('releaseInfo/delete')">
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
                    <td>{{props.item.serverExplain}}</td>
                    <td>{{props.item.serverIp}}</td>
                    <td>{{props.item.serverName}}</td>
                    <td>{{props.item.userName}}</td>

                  </tr>
                </template>
              </v-data-table>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" dark @click="confirm_release()">
                  生效


                </v-btn>
                <v-btn color="success" dark>
                  结果导出


                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
          <v-dialog v-model="confirm_dialog" persistent max-width="290">
            <v-card>
              <v-card-title class="headline">提示</v-card-title>
              <v-card-text>是否确认生效?</v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" flat @click.native="begin_release()">确定</v-btn>
                <v-btn color="green darken-1" flat @click.native.stop="confirm_dialog = false">取消</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="release_progress_dialog" persistent max-width="290">
            <div style="text-align: center">
              <v-progress-circular
                v-bind:size="100"
                v-bind:width="15"
                v-bind:rotate="-90"
                v-bind:value="value"
                color="primary"
              >
                {{ value }}


              </v-progress-circular>
            </div>
          </v-dialog>
        </v-tabs-content>
      </v-tabs-items>
      <v-tabs-items>
        <v-tabs-content :id="'tab-2'">
          <v-flex xs12>
            <v-card>
              <v-data-table
                :headers="historyHeaders"
                :items="historyItems"
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
                <template slot="items" slot-scope="props">
                  <tr :active="props.selected" align="center" @click="props.selected = !props.selected">
                    <td>
                      <v-checkbox
                        primary
                        hide-details
                        :input-value="props.selected"
                      ></v-checkbox>
                    </td>
                    <td>{{props.item.releasePerson}}</td>
                    <td>{{getDate(props.item.releaseTime)}}</td>
                    <td v-if="props.item.releaseResault == 0"><span style="color: #00B83F">成功</span></td>
                    <td v-else=""><span style="color: #a92222">失败</span> </td>
                  </tr>
                </template>
              </v-data-table>
            </v-card>
          </v-flex>
        </v-tabs-content>
      </v-tabs-items>
    </v-tabs>
  </v-layout>
</template>
<script>
  import {axios, getFormatDate} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  var newReleaseHistory = {
    releasePerson: "",
    releaseTime: "",
    releaseResault: "",
  }
  export default {
    mounted() {
      this.queryItems();
      this.clearUpdatereleaseInfo();
      this.queryReleaseHistorys();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "服务器说明",
          align: 'left',
          value: "serverExplain"
        },
          {
            text: "服务器IP",
            align: 'left',
            value: "serverIp"
          },
          {
            text: "服务器名",
            align: 'left',
            value: "serverName"
          },
          {
            text: "用户名",
            align: 'left',
            value: "userName"
          }
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        confirm_dialog: false,
        historyHeaders: [{
          text: "生效人",
          align: 'center',
          value: "releasePerson"
        },
          {
            text: "生效时间",
            align: 'center',
            value: "releaseTime"
          },
          {
            text: "生效结果",
            align: 'center',
            value: "releaseResault"
          },
        ], //列表表头
        historyItems: [],
        newReleaseHistory: _.cloneDeep(newReleaseHistory),
        release_progress_dialog: false,
        interval: {},
        value: 0

      }
    },
    beforeDestroy () {
      clearInterval(this.interval)
    },
    methods: {
      ...mapMutations('releaseinfo', [
        'setUpdatereleaseInfo',
        'clearUpdatereleaseInfo'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/releaseInfo").then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      updatereleaseInfo(){
        if (this.selected.length === 1) {
          this.setUpdatereleaseInfo(this.selected[0]);
          this.pagerouter({name: 'releaseInfoEdit'});
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      delreleaseInfo(){
        if (this.selected.length === 1) {
          axios().delete("/releaseInfo", {
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
      confirm_release(){
        this.confirm_dialog = true;
      },
      begin_release(){
        this.confirm_dialog = false;
        this.release_progress_dialog = true;
        this.interval = setInterval(() => {
          if (this.value === 100) {
            clearInterval(this.interval);
            this.value = 0;
            this.release_progress_dialog = false;
            axios().post("/releaseHistory", this.newReleaseHistory).then(() => {
              this.simpleInfo("操作成功");
              this.queryReleaseHistorys();
              this.active = this.tab-2;//tab跳转
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
          this.value += 10
        }, 1000);

      },
      queryReleaseHistorys(){
        var vm = this;
        vm.items_loading = true;
        axios().get("/releaseHistory").then(function (response) {
          vm.historyItems = response.data;
          vm.items_loading = false;
        })
      },
      getDate(longTime)
      {
        return getFormatDate(longTime);
      }
    }
  }
</script>

<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">数据字典</v-toolbar-title>
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
          <v-btn icon @click="addcodeInfo()" v-if="isPermitted('codeInfo/insert')">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updatecodeInfo()" v-if="isPermitted('codeInfo/update')">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delcodeInfo()" v-if="isPermitted('codeInfo/delete')">
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
              <td>{{props.item.type}}</td>
              <td>{{props.item.code}}</td>
              <td>{{props.item.name}}</td>
              <td>{{props.item.value}}</td>

            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="create_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">新增系统参数</span>
        </v-card-title>
        <v-card-text>
          <v-text-field label="字典类别" required v-model="newCode.type" v-validate="'required'" data-vv-name="字典类别"
                        data-vv-scope="code_create"
                        :error-messages="vee_errors.collect('字典类别','code_create')[0]||[]"></v-text-field>
          <v-text-field label="字典代码" required v-model="newCode.code" v-validate="'required'" data-vv-name="字典代码"
                        data-vv-scope="code_create"
                        :error-messages="vee_errors.collect('字典代码','code_create')[0]||[]"></v-text-field>
          <v-text-field label="字典名称" required v-model="newCode.name" v-validate="'required'" data-vv-name="字典名称"
                        data-vv-scope="code_create"
                        :error-messages="vee_errors.collect('字典名称','code_create')[0]||[]"></v-text-field>
          <v-text-field label="字典值" v-model="newCode.value"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" @click.native="createCode()">新增</v-btn>
          <v-btn color="error" @click.native="create_dialog = false">取消</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="update_dialog" persistent>
      <v-card>
        <v-card-title>
          <span class="headline">编辑系统参数</span>
        </v-card-title>
        <v-card-text>
          <v-text-field label="字典类别" required v-model="newCode.type" v-validate="'required'" data-vv-name="字典类别"
                        data-vv-scope="code_update"
                        :error-messages="vee_errors.collect('字典类别','code_update')[0]||[]"></v-text-field>
          <v-text-field label="字典代码" required v-model="newCode.code" v-validate="'required'" data-vv-name="字典代码"
                        data-vv-scope="code_update"
                        :error-messages="vee_errors.collect('字典代码','code_update')[0]||[]"></v-text-field>
          <v-text-field label="字典名称" required v-model="newCode.name" v-validate="'required'" data-vv-name="字典名称"
                        data-vv-scope="code_update"
                        :error-messages="vee_errors.collect('字典名称','code_update')[0]||[]"></v-text-field>
          <v-text-field label="字典值" v-model="newCode.value"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" @click.native="updateCode()">保存</v-btn>
          <v-btn color="error" @click.native="update_dialog = false">取消</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  var newEmptyCode = {
    type: '',
    code: '',
    name: '',
    value: ''
  };
  export default {
    mounted() {
      this.queryItems();
      this.clearUpdatecodeInfo();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "字典类别",
          align: 'left',
          value: "type"
        },
          {
            text: "字典代码",
            align: 'left',
            value: "code"
          },
          {
            text: "字典名称",
            align: 'left',
            value: "name"
          },
          {
            text: "字典值",
            align: 'left',
            value: "value"
          }
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        newCode: _.cloneDeep(newEmptyCode),
        create_dialog: false,
        update_dialog: false
      }
    },
    methods: {
      ...mapMutations('codeinfo', [
        'setUpdatecodeInfo',
        'clearUpdatecodeInfo'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/codeInfo").then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      addcodeInfo(){
        this.create_dialog = true;
      },
      createCode() {
        this.$validator.validateAll("code_create").then((result) => {
          if (result) {
            var vmE = this;
            axios().post('/codeInfo', {
              type: this.newCode.type,
              code: this.newCode.code,
              name: this.newCode.name,
              value: this.newCode.value
            })
              .then(function (response) {
                vmE.simpleSuccess('新增成功');
                vmE.create_dialog = false;
                vmE.newCode = _.cloneDeep(newEmptyCode);
                vmE.queryItems();
              });
          }
        });
      },
      updatecodeInfo(){
        if (this.selected.length === 1) {
          this.newCode = this.selected[0]
          this.update_dialog = true;
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      updateCode() {
        this.$validator.validateAll("code_update").then((result) => {
          if (result) {
            var vmE = this;
            axios().put('/codeInfo/', {
              id: this.newCode.id,
              type: this.newCode.type,
              code: this.newCode.code,
              name: this.newCode.name,
              value: this.newCode.value
            })
              .then(function (response) {
                vmE.simpleSuccess('保存成功！');
                vmE.update_dialog = false;
                vmE.newCode = _.cloneDeep(newEmptyCode);
              });
          }
        });
      },
      delcodeInfo(){
        if (this.selected.length === 1) {
          axios().delete("/codeInfo", {
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

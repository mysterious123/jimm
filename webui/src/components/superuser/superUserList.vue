<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card>
        <v-toolbar class="cyan" dark dense>
          <v-toolbar-title class="white--text">用户</v-toolbar-title>
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
          <v-btn icon @click="addSuperUser()">
            <v-icon>add</v-icon>
          </v-btn>
          <v-btn icon @click="updateSuperConfigUser()">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn icon @click="delSuperUser()">
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
              <td>{{props.item.userName}}</td>
              <td>{{props.item.groupName}}</td>
              <td>{{getDate(props.item.createTime)}}</td>
            </tr>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
    <v-dialog v-model="userinfo_new_dialog" persistent>
      <v-card>
        <v-toolbar color="teal lighten-3" dark>
          <v-toolbar-title>用户信息</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-text-field
            label="用户名"
            v-model="newSuperUser.userName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('用户名','superuser_insert')[0]||[]"
            data-vv-name="用户名"
            data-vv-scope="superuser_insert"
          ></v-text-field>
          <v-text-field
            label="密码"
            type="password"
            v-model="newSuperUser.password"
            v-validate="'required'"
            :error-messages="vee_errors.collect('密码','superuser_insert')[0]||[]"
            data-vv-name="密码"
            data-vv-scope="superuser_insert"
          ></v-text-field>
          <v-select
            label="用户组"
            :items="options"
            v-model="newSuperUser.groupName"
            data-vv-name="用户组"
            data-vv-scope="superuser_insert"
          ></v-select>
          <v-select
            label="角色"
            :items="superRoles"
            multiple
            v-model="newSuperRolesID"
            data-vv-name="角色"
            data-vv-scope="superuser_insert"
          ></v-select>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error" @click.native.stop="userinfo_new_dialog = false">取消</v-btn>
          <v-btn color="success" @click.stop="superuser_insert()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="userinfo_update_dialog" persistent>
      <v-card>
        <v-toolbar color="teal lighten-3" dark>
          <v-toolbar-title>用户信息</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-text-field
            label="用户名"
            v-model="updateSuperUser.userName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('用户名','superuser_update')[0]||[]"
            data-vv-name="用户名"
            data-vv-scope="superuser_update"
          ></v-text-field>
          <!--<v-text-field-->
            <!--label="密码"-->
            <!--type="password"-->
            <!--v-model="updateSuperUser.password"-->
            <!--v-validate="'required'"-->
            <!--:error-messages="vee_errors.collect('密码','superuser_update')[0]||[]"-->
            <!--data-vv-name="密码"-->
            <!--data-vv-scope="superuser_update"-->
          <!--&gt;</v-text-field>-->
          <v-select
            label="用户组"
            :items="options"
            v-model="updateSuperUser.groupName"
            data-vv-name="用户组"
            data-vv-scope="superuser_update"
          ></v-select>
          <v-select
            label="角色"
            :items="superRoles"
            multiple
            v-model="updateSuperRolesID"
            data-vv-name="角色"
            data-vv-scope="superuser_update"
          ></v-select>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error" @click.native.stop="userinfo_update_dialog = false">取消</v-btn>
          <v-btn color="success" @click.stop="superuser_update()">
            保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
  import {axios, getFormatDate} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';

  export default {
    mounted() {
      this.queryItems();
      this.clearUpdateSuperUser();
      this.queryOptions();
      this.queryRoles();
    },
    data() {
      return {
        search: "", //页面查询模型
        headers: [{
          text: "用户名",
          align: 'left',
          value: "userName"
        },
          {
            text: "用户组",
            align: 'left',
            value: "groupName"
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
        userinfo_new_dialog: false,
        userinfo_update_dialog: false,
        newSuperUser: {},
        updateSuperUser: {},
        select: [],
        options: [],
        superRoles: [],
        updateSuperRoles: [],
      }
    },
    computed: {
      updateSuperRolesID: {
        get: function () {
          if(this.updateSuperRoles) {
            return this.updateSuperRoles.map((item) => {
              return item.id
            })
          }else{
            return []
          }
        },
        set: function (values) {
          this.updateSuperUser.superRoles = values.map((item) => {
            return {id : item}
          })
        }
      },
      newSuperRolesID: {
        get: function () {
          return [];
        },
        set: function (values) {
          this.newSuperUser.superRoles = values.map((item) => {
            return {id : item}
          })
        }
      }
    },
    methods: {
      ...mapMutations('superuser', [
        'setUpdateSuperUser',
        'clearUpdateSuperUser'
      ]),
      //加载表格数据
      queryItems() {
        var vm = this;
        vm.items_loading = true;
        axios().get("/superUser/queryUserInfo").then(function (response) {
          vm.items = response.data;
          vm.items_loading = false;
        })
      },
      queryOptions() {
        var vm = this;
        axios().get("/superUserGroup").then(function (response) {
          response.data.forEach(function (v, i) {
            vm.options.push(v.groupName);
          })
        })
      },
      addSuperUser(){
        this.userinfo_new_dialog = true;
        this.newSuperUser = {};
      },
      superuser_insert(){
        this.$validator.validateAll("superuser_insert").then((result) => {
          if (result) {
            axios().post("/superUser", this.newSuperUser).then(() => {
              this.simpleInfo("操作成功");
              this.userinfo_new_dialog = false;
              this.queryItems();
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      updateSuperConfigUser(){
        if (this.selected.length === 1) {
          this.updateSuperUser = this.selected[0];
          this.userinfo_update_dialog = true;
          this.updateSuperRoles = this.updateSuperUser.superRoles;
        } else {
          this.simpleInfo("请选择一条记录");
        }
      },
      superuser_update(){
        this.$validator.validateAll("superuser_update").then((result) => {
          if (result) {
            axios().put("/superUser", this.updateSuperUser).then(() => {
              this.simpleInfo("操作成功");
              this.userinfo_update_dialog = false;
              this.selected = [];
              this.queryItems();
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      delSuperUser(){
        if (this.selected.length === 1) {
          axios().delete("/superUser", {
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
      getDate(longTime)
      {
        return getFormatDate(longTime);
      },
      async queryRoles(){
        var result = await axios().get("/superRole")
        this.superRoles = result.data.map((item)=>{
          return {
            text : item.roleName,
            value : item.id
          }
        });
      },
    }
  }
</script>

<template>
  <v-card>
  <v-toolbar class="cyan" dark dense>
  <v-btn icon @click="pagerouter({name:'serverInfo'})" dark>
  <v-icon>arrow_back</v-icon>
  </v-btn>
  <v-toolbar-title class="white--text">编辑服务器信息</v-toolbar-title>
  </v-toolbar>
    <v-stepper v-model="infostep" vertical>
      <v-stepper-step step="1" v-bind:complete="infostep > 1" editable>
        基本信息
        <small>服务器基本信息</small>
      </v-stepper-step>
      <v-stepper-content step="1">
        <v-card>
          <v-card-text>
            <v-layout row wrap>
              <v-flex xs12>
                <input-tag
                  label="服务器IP"
                  v-model="updateServerInfo.serverIp"
                  data-vv-name="服务器IP"
                  data-vv-scope="serverinfo_update_baseinfo">
                </input-tag>
              </v-flex>
              <v-flex xs6 pr-2>
                <v-text-field
                  label="应用系统"
                  data-vv-name="应用系统"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <async-select
                  label="状态属性"
                  :v-data-params="{type : 'base_status'}"
                  v-model="updateServerInfo.stateNature"
                  data-vv-name="状态属性"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></async-select>
                <async-select
                  label="管理部门"
                  :v-data-params="{type : 'manage_department'}"
                  v-model="updateServerInfo.department"
                  data-vv-name="管理部门"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></async-select>
                <v-text-field
                  label="型号"
                  v-model="updateServerInfo.version"
                  data-vv-name="型号"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <v-text-field
                  label="机房"
                  v-model="updateServerInfo.computerRoom"
                  data-vv-name="机房"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <v-text-field
                  label="资产编号"
                  v-model="updateServerInfo.assetId"
                  data-vv-name="资产编号"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <v-text-field
                  label="维护商"
                  v-model="updateServerInfo.maintainer"
                  data-vv-name="维护商"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <v-text-field
                  label="时段策略"
                  v-model="updateServerInfo.timeTactics"
                  data-vv-name="时段策略"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <v-text-field
                  label="备注"
                  v-model="updateServerInfo.remarks"
                  data-vv-name="备注"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
              </v-flex>
              <v-flex xs6 >
                <v-text-field
                  label="服务器名称"
                  v-model="updateServerInfo.serverName"
                  data-vv-name="服务器名称"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <v-text-field
                  label="服务器用途"
                  v-model="updateServerInfo.serverUse"
                  data-vv-name="服务器用途"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <async-select
                  label="所属机构"
                  :v-data-params="{type : 'base_orgnization'}"
                  v-model="updateServerInfo.attributeOrganization"
                  data-vv-name="所属机构"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></async-select>
                <async-select
                  label="厂商"
                  :v-data-url="'/venderInfo'"
                  :v-data-params="{}"
                  item-text="venderName"
                  item-value="venderName"
                  v-model="updateServerInfo.firm"
                  data-vv-name="厂商"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></async-select>
                <async-select
                  label="网络区域"
                  :v-data-params="{type : 'net_area'}"
                  v-model="updateServerInfo.networkZone"
                  data-vv-name="网络区域"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></async-select>
                <v-text-field
                  label="机柜"
                  v-model="updateServerInfo.computerCounter"
                  data-vv-name="机柜"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
                <date-picker :date="maintainTime"
                             v-model="updateServerInfo.maintainTime"
                             :option="dateMaintainTimePickerOption"></date-picker>
                <v-text-field
                  label="服务器策略"
                  v-model="updateServerInfo.serverTactics"
                  data-vv-name="服务器策略"
                  data-vv-scope="serverinfo_update_baseinfo"
                ></v-text-field>
              </v-flex>
            </v-layout>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" dark @click.native="infostep = 2">
              <v-icon>arrow_forward</v-icon>
              下一步
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>
      <v-stepper-step step="2" editable>
        软件信息
        <small>软件基本信息</small>
      </v-stepper-step>
      <v-stepper-content step="2">
        <v-card class="mb-5">
          <v-card-text>
            <v-layout row wrap>
              <v-flex xs6 pr-2>
                <async-select
                  label="操作系统类型"
                  :v-data-params="{type : 'os_type'}"
                  v-model="updateServerInfo.osType"
                  data-vv-name="操作系统类型"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
                <async-select
                  label="操作系统位数"
                  :v-data-params="{type : 'os_bit'}"
                  v-model="updateServerInfo.osBit"
                  data-vv-name="操作系统位数"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>

                <v-text-field
                  label="操作系统CPU"
                  v-model="updateServerInfo.osCpu"
                  data-vv-name="操作系统CPU"
                  data-vv-scope="serverinfo_update_soft"
                ></v-text-field>
                <v-text-field
                  label="操作系统内存"
                  v-model="updateServerInfo.osMemory"
                  data-vv-name="操作系统内存"
                  data-vv-scope="serverinfo_update_soft"
                ></v-text-field>
                <async-select
                  label="数据库位数"
                  :v-data-params="{type : 'db_bit'}"
                  v-model="updateServerInfo.dbBit"
                  data-vv-name="数据库位数"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
                <async-select
                  label="中间件类型"
                  :v-data-params="{type : 'mw_type'}"
                  v-model="updateServerInfo.mwType"
                  data-vv-name="中间件类型"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
                <async-select
                  label="中间件位数"
                  :v-data-params="{type : 'mw_bit'}"
                  v-model="updateServerInfo.mwBit"
                  data-vv-name="中间件位数"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  label="操作详细版本"
                  v-model="updateServerInfo.osDetailVersion"
                  data-vv-name="操作详细版本"
                  data-vv-scope="serverinfo_update_soft"
                ></v-text-field>
                <v-text-field
                  label="操作系统补丁版本"
                  v-model="updateServerInfo.osPatchVersion"
                  data-vv-name="操作系统补丁版本"
                  data-vv-scope="serverinfo_update_soft"
                ></v-text-field>
                <async-select
                  label="操作系统管理组"
                  :v-data-url="'/manageGroupInfo'"
                  :v-data-params="{}"
                  item-text="manageGroupName"
                  item-value="manageGroupName"
                  v-model="updateServerInfo.osManageGroup"
                  data-vv-name="操作系统管理组"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
                <async-select
                  label="数据库类型"
                  :v-data-params="{type : 'db_type'}"
                  v-model="updateServerInfo.dbType"
                  data-vv-name="数据库类型"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
                <async-select
                  label="数据库管理组"
                  :v-data-url="'/manageGroupInfo'"
                  :v-data-params="{}"
                  item-text="manageGroupName"
                  item-value="manageGroupName"
                  v-model="updateServerInfo.dbManageGroup"
                  data-vv-name="数据库管理组"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
                <v-text-field
                  label="中间件版本"
                  v-model="updateServerInfo.mwVersion"
                  data-vv-name="中间件版本"
                  data-vv-scope="serverinfo_update_soft"
                ></v-text-field>
                <async-select
                  label="中间件管理组"
                  :v-data-url="'/manageGroupInfo'"
                  :v-data-params="{}"
                  item-text="manageGroupName"
                  item-value="manageGroupName"
                  v-model="updateServerInfo.mwManageGroup"
                  data-vv-name="中间件管理组"
                  data-vv-scope="serverinfo_update_soft"
                ></async-select>
              </v-flex>
            </v-layout>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" dark @click.native="infostep = 1">
              <v-icon>arrow_back</v-icon>
              上一步
            </v-btn>
            <v-btn color="success" dark @click.stop="serverinfo_insert()">
              <v-icon>check</v-icon>
              更新
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>
    </v-stepper>
  </v-card>
</template>
<script>
  import {axios, getDatePickerOptions} from '@/assets/js/Utils';
  import inputTag from '@/components/common/inputTag';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import asyncSelect from '@/components/common/asyncSelect';
  import myDatepicker from '@/components/common/vuetify-datepicker-es6';
  import {Validator} from 'vee-validate';
  const dateRangePickerFormatter = 'YYYY-MM-DD';

  export default {
    beforeRouteEnter (to, from, next) {
      if(!store.state.serverinfo.updateServerInfo) {
        next({name : 'serverInfo'})
      }else{
        next();
      }
    },
    data() {
      return {
        infostep: 1,
        dateMaintainTimePickerOption: getDatePickerOptions({
          placeholder: '维保时间',
          formatter: dateRangePickerFormatter
        }),
        maintainTime: {
          time: ''
        },
      }
    },
    components: {
      "input-tag": inputTag,
      "async-select": asyncSelect,
      'date-picker': myDatepicker,
    },
    computed: {
      ...mapState('serverinfo', ['updateServerInfo']),
    },
    methods:{
      serverinfo_update(){
        this.$validator.validateAll("serverinfo_update").then((result) => {
          if (result) {
            axios().put("/serverinfo",this.updateServerInfo).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'serverInfo'})
            })
          }
        });
      }
    }
  }
</script>

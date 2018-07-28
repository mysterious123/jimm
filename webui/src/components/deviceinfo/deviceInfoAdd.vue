<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'deviceInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">设备新增</v-toolbar-title>
    </v-toolbar>
    <v-stepper v-model="infostep" vertical>
      <v-stepper-step step="1" v-bind:complete="infostep > 1" editable>
        基本信息
        <small>网络设备基本信息</small>
      </v-stepper-step>
      <v-stepper-content step="1">
        <v-card>
          <v-card-text>
            <v-layout row wrap>
              <v-flex xs6 pr-2>
                <v-text-field
                  label="管理IP"
                  v-model="newDeviceInfo.manageIp"
                  v-validate="'required|unique'"
                  :error-messages="vee_errors.collect('管理IP','deviceinfo_insert')[0]||[]"
                  data-vv-name="管理IP"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-text-field
                  label="Community String RO"
                  v-model="newDeviceInfo.communityStringRo"
                  v-validate="'required'"
                  :error-messages="vee_errors.collect('Community String RO','deviceinfo_insert')[0]||[]"
                  data-vv-name="Community String RO"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-text-field
                  label="Community String RW"
                  v-model="newDeviceInfo.communityStringRw"
                  :error-messages="vee_errors.collect('Community String RW','deviceinfo_insert')[0]||[]"
                  data-vv-name="Community String RW"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <async-select
                  label="SNMP版本"
                  :v-data-params="{type : 'snmp_version'}"
                  v-validate="'required'"
                  v-model="newDeviceInfo.snmpVersion"
                  :error-messages="vee_errors.collect('SNMP版本','deviceinfo_insert')[0]||[]"
                  data-vv-name="SNMP版本"
                  data-vv-scope="deviceinfo_insert"
                ></async-select>
                <v-text-field
                  label="SNMP端口"
                  v-validate="'required'"
                  v-model="newDeviceInfo.snmpPort"
                  :error-messages="vee_errors.collect('SNMP端口','deviceinfo_insert')[0]||[]"
                  data-vv-name="SNMP端口"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-card-actions>
                  <v-btn color="primary" @click.native="">
                    测试
                  </v-btn>
                  <v-btn color="primary" @click.stop="deviceinfo_poll()">
                    POLL
                  </v-btn>
                </v-card-actions>
              </v-flex>
            </v-layout>
            <v-layout row wrap>
              <v-flex xs6 pr-2>
                <v-text-field
                  label="设备名称"
                  v-model="newDeviceInfo.deviceName"
                  :error-messages="vee_errors.collect('设备名称','deviceinfo_insert')[0]||[]"
                  data-vv-name="设备名称"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-text-field
                  label="设备别名"
                  v-model="newDeviceInfo.deviceOtherName"
                  :error-messages="vee_errors.collect('设备别名','deviceinfo_insert')[0]||[]"
                  data-vv-name="设备别名"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <async-select
                  label="网络区域"
                  :v-data-params="{type : 'net_area'}"
                  v-model="newDeviceInfo.networkZone"
                  :error-messages="vee_errors.collect('网络区域','deviceinfo_insert')[0]||[]"
                  data-vv-name="网络区域"
                  data-vv-scope="deviceinfo_insert"
                ></async-select>
                <v-text-field
                  label="监控策略"
                  v-model="newDeviceInfo.monitorTactics"
                  :error-messages="vee_errors.collect('监控策略','deviceinfo_insert')[0]||[]"
                  data-vv-name="监控策略"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <async-select
                  label="管理部门"
                  :v-data-params="{type : 'manage_department'}"
                  v-model="newDeviceInfo.department"
                  :error-messages="vee_errors.collect('管理部门','deviceinfo_insert')[0]||[]"
                  data-vv-name="管理部门"
                  data-vv-scope="deviceinfo_insert"
                ></async-select>
                <async-select
                  label="所属机构"
                  :v-data-params="{type : 'base_orgnization'}"
                  v-model="newDeviceInfo.attributeOrganization"
                  :error-messages="vee_errors.collect('所属机构','deviceinfo_insert')[0]||[]"
                  data-vv-name="所属机构"
                  data-vv-scope="deviceinfo_insert"
                ></async-select>
                <async-select
                  label="状态属性"
                  :v-data-params="{type : 'base_status'}"
                  v-model="newDeviceInfo.stat"
                  :error-messages="vee_errors.collect('状态属性','deviceinfo_insert')[0]||[]"
                  data-vv-name="状态属性"
                  data-vv-scope="deviceinfo_insert"
                ></async-select>
                <v-text-field
                  label="时段策略"
                  v-model="newDeviceInfo.timeTactics"
                  :error-messages="vee_errors.collect('时段策略','deviceinfo_insert')[0]||[]"
                  data-vv-name="时段策略"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
              </v-flex>
              <v-text-field
                label="备注"
                textarea=""
                v-model="newDeviceInfo.remarks"
                :error-messages="vee_errors.collect('备注','deviceinfo_insert')[0]||[]"
                data-vv-name="备注"
                data-vv-scope="deviceinfo_insert"
              ></v-text-field>
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
        设备信息
        <small>设备基本信息</small>
      </v-stepper-step>
      <v-stepper-content step="2">
        <v-card class="mb-5">
          <v-card-text>
            <v-layout row wrap>
              <v-flex xs6 pr-2>
                <v-text-field
                  label="厂商"
                  v-model="newDeviceInfo.firm"
                  :error-messages="vee_errors.collect('厂商','deviceinfo_insert')[0]||[]"
                  data-vv-name="厂商"
                  data-vv-scope="deviceinfo_insert"
                  disabled
                ></v-text-field>
                <v-text-field
                  label="设备类型"
                  v-model="newDeviceInfo.deviceType"
                  :error-messages="vee_errors.collect('设备类型','deviceinfo_insert')[0]||[]"
                  data-vv-name="设备类型"
                  data-vv-scope="deviceinfo_insert"
                  disabled
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  label="ObjectID"
                  v-model="newDeviceInfo.objectId"
                  :error-messages="vee_errors.collect('ObjectID','deviceinfo_insert')[0]||[]"
                  data-vv-name="ObjectID"
                  data-vv-scope="deviceinfo_insert"
                  disabled
                ></v-text-field>
                <v-text-field
                  label="设备型号"
                  v-model="newDeviceInfo.deviceVersion"
                  :error-messages="vee_errors.collect('设备型号','deviceinfo_insert')[0]||[]"
                  data-vv-name="设备型号"
                  data-vv-scope="deviceinfo_insert"
                  disabled
                ></v-text-field>
              </v-flex>
              <v-text-field
                label="软件版本"
                v-model="newDeviceInfo.softwareVersion"
                :error-messages="vee_errors.collect('软件版本','deviceinfo_insert')[0]||[]"
                data-vv-name="软件版本"
                data-vv-scope="deviceinfo_insert"
                disabled
              ></v-text-field>
            </v-layout>
            <v-layout row wrap>
              <v-flex xs6 pr-2>
                <v-text-field
                  label="供应商"
                  v-model="newDeviceInfo.supplier"
                  :error-messages="vee_errors.collect('供应商','deviceinfo_insert')[0]||[]"
                  data-vv-name="供应商"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-text-field
                  label="设备级别"
                  v-model="newDeviceInfo.deviceLevel"
                  :error-messages="vee_errors.collect('设备级别','deviceinfo_insert')[0]||[]"
                  data-vv-name="设备级别"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <date-picker :date="groundingDate"
                             v-model="newDeviceInfo.groundingTime"
                             v-validate="'required'"
                             data-vv-name="上架时间"
                             data-vv-scope="dateRangerPicker"
                             :error-messages="vee_errors.collect('上架时间','dateRangerPicker')[0]||[]"
                             :option="dateGroundingPickerOption"></date-picker>
                <v-text-field
                  label="机房"
                  v-model="newDeviceInfo.computerRoom"
                  :error-messages="vee_errors.collect('机房','deviceinfo_insert')[0]||[]"
                  data-vv-name="机房"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <date-picker :date="startDate"
                             v-model="newDeviceInfo.maintenanceStartTime"
                             v-validate="'required'"
                             data-vv-name="维保开始时间"
                             data-vv-scope="dateRangerPicker"
                             :error-messages="vee_errors.collect('维保开始时间','dateRangerPicker')[0]||[]"
                             :option="dateStartPickerOption"></date-picker>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  label="服务商"
                  v-model="newDeviceInfo.serviceProvider"
                  :error-messages="vee_errors.collect('服务商','deviceinfo_insert')[0]||[]"
                  data-vv-name="服务商"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-radio-group v-model="newDeviceInfo.whetherDot" row>
                  <v-radio label="不是网点" value="1" ></v-radio>
                  <v-radio label="是网点" value="0"></v-radio>
                </v-radio-group>
                <v-text-field
                  label="资产编号"
                  v-model="newDeviceInfo.assetId"
                  :error-messages="vee_errors.collect('资产编号','deviceinfo_insert')[0]||[]"
                  data-vv-name="资产编号"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <v-text-field
                  label="机柜编号"
                  v-model="newDeviceInfo.computerCounter"
                  :error-messages="vee_errors.collect('机柜编号','deviceinfo_insert')[0]||[]"
                  data-vv-name="机柜编号"
                  data-vv-scope="deviceinfo_insert"
                ></v-text-field>
                <date-picker :date="endDate"
                             v-model="newDeviceInfo.maintenanceEndTime"
                             v-validate="'required'"
                             data-vv-name="维保结束时间"
                             data-vv-scope="dateRangerPicker"
                             :error-messages="vee_errors.collect('维保结束时间','dateRangerPicker')[0]||[]"
                             :option="dateEndPickerOption"></date-picker>
              </v-flex>
            </v-layout>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" dark @click.native="infostep = 3">
              <v-icon>arrow_forward</v-icon>
              下一步
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>
      <v-stepper-step step="3" editable>
        端口信息
        <small>端口基本信息</small>
      </v-stepper-step>
      <v-stepper-content step="3">
        <v-card class="mb-5">
          <v-card>
            <v-data-table
              :headers="headers"
              :items="items"
              :loading="items_loading"
              :pagination.sync="dataTableConfig.dataTable_pagination"
              :no-data-text="dataTableConfig.dataTable_noDataText"
              :no-result-text="dataTableConfig.dataTable_noResultsText"
              :rows-per-page-text="dataTableConfig.dataTable_rowsPerPageText"
              class="elevation-1"
              select-all
              v-model="selected">
              <template slot="headers" slot-scope="props">
                <tr>
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
                <tr :active="props.selected" @click="props.selected = !props.selected">
                  <td>
                    <v-checkbox
                      primary
                      hide-details
                      :input-value="props.selected"
                    ></v-checkbox>
                  </td>
                </tr>
              </template>
            </v-data-table>
          </v-card>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" dark @click.stop="deviceinfo_insert()">
              <v-icon>check</v-icon>
              保存
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-stepper-content>
    </v-stepper>
  </v-card>
</template>
<script>
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import asyncSelect from '@/components/common/asyncSelect'
  import myDatepicker from '@/components/common/vuetify-datepicker-es6';
  import {getDatePickerOptions, setDateTimeRange, axios} from '@/assets/js/Utils';
  import {Validator} from 'vee-validate';
  const dateRangePickerFormatter = 'YYYY/MM/DD HH:mm:ss';

  var newDeviceInfo = {
    manageIp: "",
    deviceName: "",
    deviceOtherName: "",
    deviceType: "",
    deviceVersion: "",
    deviceLevel: "",
    snmpVersion: "",
    snmpPort: "",
    communityStringRo: "",
    communityStringRw: "",
    department: "",
    attributeOrganization: "",
    networkZone: "",
    stat: "",
    monitorTactics: "",
    timeTactics: "",
    remarks: "",
    firm: "",
    objectId: "",
    softwareVersion: "",
    supplier: "",
    serviceProvider: "",
    whetherDot: "1",
    assetId: "",
    groundingTime: "",
    computerRoom: "",
    computerCounter: "",
    maintenanceStartTime: "",
    maintenanceEndTime: "",
    importantPort: "",
    portStat: "",
    portName: "",
    portOtherName: "",
    portIp: "",
    bandwidth: "",
    userId: "",
    groupId: "",
  }
  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.deviceinfo.selectedNode) {
        next({name:'deviceInfo'})
      } else {
        next();
      }
    },
    mounted(){
      Validator.extend('unique', {
        getMessage: field => `该${field}已经存在`,
        validate: function (value) {
          return new Promise((resolve) => {
            axios().get("/deviceinfo/checkIpExist",{
              params: {
                manageIp:value
              }
            }).then(function (response) {
              resolve({
                valid: response.data === 0
              });
            })
          })
        }
      });
    },
    data(){
      return {
        infostep: 1,
        newDeviceInfo: _.cloneDeep(newDeviceInfo),
        headers: [{
          text: "重要端口",
          align: 'left',
          value: "importantPort"
        },
          {
            text: "端口状态",
            align: 'left',
            value: "portStat"
          },
          {
            text: "端口名称",
            align: 'left',
            value: "portName"
          },
          {
            text: "端口别名",
            align: 'left',
            value: "portOtherName"
          },
          {
            text: "端口IP",
            align: 'left',
            value: "portIp"
          },
          {
            text: "带宽（MB）",
            align: 'left',
            value: "bandwidth"
          }
        ], //列表表头
        items: [],  //列表数据
        items_loading: false, //列表加载
        selected: [],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        dateStartPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '开始日期',
          formatter: dateRangePickerFormatter
        }),
        startDate: {
          time: ''
        },
        dateEndPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '结束日期',
          formatter: dateRangePickerFormatter
        }),
        endDate: {
          time: ''
        },
        dateGroundingPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '上架时间',
          formatter: dateRangePickerFormatter
        }),
        groundingDate: {
          time: ''
        },
      }
    },
    computed: {
      ...mapState('deviceinfo', ['selectedNode']),
    },
    components: {
      "async-select": asyncSelect,
      'date-picker': myDatepicker,
    },
    methods: {
      deviceinfo_insert(){
        this.$validator.validateAll("deviceinfo_insert").then((result) => {
          if (result) {
            this.newDeviceInfo.groupId = this.selectedNode.id;
            axios().post("/deviceinfo", this.newDeviceInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'deviceInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      deviceinfo_poll(){
        this.$validator.validateAll("deviceinfo_insert").then((result) => {
          if (result) {
            axios().post("/deviceinfo/devicepoll", this.newDeviceInfo).then((response) => {
              this.simpleInfo("操作成功");
              this.newDeviceInfo.deviceName = response.data.deviceName;
              this.newDeviceInfo.objectId = response.data.objectId;
              this.newDeviceInfo.softwareVersion = response.data.softwareVersion;
              this.newDeviceInfo.deviceType = response.data.deviceType;
              this.newDeviceInfo.deviceVersion = response.data.deviceVersion;
              this.newDeviceInfo.firm = response.data.firm;
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

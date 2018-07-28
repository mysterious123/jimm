<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'communicationLineInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增通讯线路管理</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <span class="title" style="color: #1976d2">线路信息</span>
      <v-layout row wrap>
        <v-flex xs6 pr-2>

          <v-text-field
            label="线路名称"
            v-model="newCommunicationLineInfo.lineName"
            v-validate="'required|unique'"
            :error-messages="vee_errors.collect('线路名称','communicationlineinfo_insert')[0]||[]"
            data-vv-name="线路名称"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>

          <async-select
            label="网络区域"
            :v-data-params="{type : 'net_area'}"
            v-model="newCommunicationLineInfo.networkZone"
            data-vv-name="网络区域"
            data-vv-scope="communicationlineinfo_insert"
          ></async-select>
          <async-select
            label="管理部门"
            :v-data-params="{type : 'manage_department'}"
            v-model="newCommunicationLineInfo.department"
            data-vv-name="管理部门"
            data-vv-scope="communicationlineinfo_insert"
          ></async-select>
          <async-select
            label="状态属性"
            :v-data-params="{type : 'base_status'}"
            v-model="newCommunicationLineInfo.stat"
            data-vv-name="状态属性"
            data-vv-scope="communicationlineinfo_insert"
          ></async-select>

          <async-select
            label="服务商"
            :v-data-params="{type : 'line_facilitator'}"
            v-model="newCommunicationLineInfo.facilitator"
            data-vv-name="服务商"
            data-vv-scope="communicationlineinfo_insert"
          ></async-select>

          <!--<v-text-field-->
          <!--label="开通时间"-->
          <!--v-model="newCommunicationLineInfo.openTime"-->
          <!--v-validate="'required'"-->
          <!--:error-messages="vee_errors.collect('开通时间','communicationlineinfo_insert')[0]||[]"-->
          <!--data-vv-name="开通时间"-->
          <!--data-vv-scope="communicationlineinfo_insert"-->
          <!--&gt;</v-text-field>-->
          <date-picker :date="openTime"
                       v-model="newCommunicationLineInfo.openTime"
                       :option="dateOpenTimePickerOption"></date-picker>

          <v-text-field
            label="监控策略"
            v-model="newCommunicationLineInfo.monitorTactics"
            data-vv-name="监控策略"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>

        </v-flex>
        <v-flex xs6 pr-2>
          <v-text-field
            label="线路别名"
            v-model="newCommunicationLineInfo.lineOtherName"
            data-vv-name="线路别名"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
          <async-select
            label="线路类型"
            :v-data-params="{type : 'line_type'}"
            v-model="newCommunicationLineInfo.lineType"
            data-vv-name="线路类型"
            data-vv-scope="communicationlineinfo_insert"
          ></async-select>

          <v-text-field
            label="带宽（MB）"
            v-model="newCommunicationLineInfo.bandwidth"
            data-vv-name="带宽（MB）"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
          <async-select
            label="所属机构"
            :v-data-params="{type : 'line_orgnization'}"
            v-model="newCommunicationLineInfo.attributeOrganization"
            data-vv-name="所属机构"
            data-vv-scope="communicationlineinfo_insert"
          ></async-select>
          <v-text-field
            label="线路编号"
            v-model="newCommunicationLineInfo.lineId"
            data-vv-name="线路编号"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
          <date-picker :date="duedate"
                       v-model="newCommunicationLineInfo.duedate"
                       :option="dateDueDatePickerOption"></date-picker>
          <v-text-field
            label="时段策略"
            v-model="newCommunicationLineInfo.timeTactics"
            data-vv-name="时段策略"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
        </v-flex>
      </v-layout>


      <span class="title" style="color: #1976d2">本端信息</span>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="本端设备管理IP"
            v-model="newCommunicationLineInfo.localDeviceManageIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('本端设备管理IP','communicationlineinfo_insert')[0]||[]"
            data-vv-name="本端设备管理IP"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>

          <v-text-field
            label="本端设备端口IP"
            v-model="newCommunicationLineInfo.localDevicePortIp"
            data-vv-name="本端设备端口IP"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
        </v-flex>
        <v-flex xs6 pr-2>
          <v-text-field
            label="本端端口名称"
            v-model="newCommunicationLineInfo.localPortIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('本端端口名称','communicationlineinfo_insert')[0]||[]"
            data-vv-name="本端端口名称"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
          <v-text-field
            label="本端设备名称"
            v-model="newCommunicationLineInfo.localDeviceName"
            data-vv-name="本端设备名称"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
        </v-flex>
      </v-layout>
      <span class="title" style="color: #1976d2">对端信息</span>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="对端设备管理IP"
            v-model="newCommunicationLineInfo.endDeviceManageIp"
            data-vv-name="对端设备管理IP"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
          <v-text-field
            label="对端设备端口IP"
            v-model="newCommunicationLineInfo.endDevicePortIp"
            data-vv-name="对端设备端口IP"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>

        </v-flex>
        <v-flex xs6 pr-2>
          <v-text-field
            label="对端端口名称"
            v-model="newCommunicationLineInfo.endPortName"
            data-vv-name="对端端口名称"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
          <v-text-field
            label="对端设备名称"
            v-model="newCommunicationLineInfo.endDeviceName"
            data-vv-name="对端设备名称"
            data-vv-scope="communicationlineinfo_insert"
          ></v-text-field>
        </v-flex>
      </v-layout>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="communicationlineinfo_insert()">
        <v-icon>check</v-icon>
        保存

      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {getDatePickerOptions, axios} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect';
  import myDatepicker from '@/components/common/vuetify-datepicker-es6';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import {Validator} from 'vee-validate';
  const dateRangePickerFormatter = 'YYYY-MM-DD';

  var newCommunicationLineInfo = {
    lineName: "",
    lineOtherName: "",
    lineType: "",
    bandwidth: "",
    networkZone: "",
    department: "",
    stat: "",
    attributeOrganization: "",
    facilitator: "",
    lineId: "",
    openTime: "",
    duedate: "",
    monitorTactics: "",
    timeTactics: "",
    localDeviceManageIp: "",
    localDevicePortIp: "",
    localDeviceName: "",
    endDeviceManageIp: "",
    endPortName: "",
    endDevicePortIp: "",
    endDeviceName: "",
    userId: "",
    groupId: "",
  }
  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.communicationlineinfo.selectedNode) {
        next({name: 'communicationLineInfo'})
      } else {
        next();
      }
    },
    mounted(){
      Validator.extend('unique', {
        getMessage: field => `该${field}已经存在`,
        validate: function (value) {
          return new Promise((resolve) => {
            axios().get("/communicationLineInfo/checkLineNameExist",{
              params: {
                lineName:value
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
        newCommunicationLineInfo: _.cloneDeep(newCommunicationLineInfo),
        dateOpenTimePickerOption: getDatePickerOptions({
          placeholder: '开通时间',
          formatter: dateRangePickerFormatter
        }),
        openTime: {
          time: ''
        },
        dateDueDatePickerOption: getDatePickerOptions({
          placeholder: '到期时间',
          formatter: dateRangePickerFormatter
        }),
        duedate: {
          time: ''
        },
      }
    },
    components: {
      "async-select": asyncSelect,
      'date-picker': myDatepicker,
    },
    computed: {
      ...mapState('communicationlineinfo', ['selectedNode']),
    },
    methods: {
      communicationlineinfo_insert(){
        this.$validator.validateAll("communicationlineinfo_insert").then((result) => {
          if (result) {
            this.newCommunicationLineInfo.groupId = this.selectedNode.id;
            axios().post("/communicationLineInfo", this.newCommunicationLineInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'communicationLineInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

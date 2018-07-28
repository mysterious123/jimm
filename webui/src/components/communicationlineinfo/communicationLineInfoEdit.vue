<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'communicationLineInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">线路编辑</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <span class="title" style="color: #1976d2">线路信息</span>
      <v-layout row wrap>
        <v-flex xs6 pr-2>

          <v-text-field
            label="线路名称"
            v-model="updatecommunicationLineInfo.lineName"
            v-validate="'required|unique'"
            :error-messages="vee_errors.collect('线路名称','communicationlineinfo_update')[0]||[]"
            data-vv-name="线路名称"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>

          <async-select
            label="网络区域"
            :v-data-params="{type : 'net_area'}"
            v-model="updatecommunicationLineInfo.networkZone"
            data-vv-name="网络区域"
            data-vv-scope="communicationlineinfo_update"
          ></async-select>
          <async-select
            label="管理部门"
            :v-data-params="{type : 'manage_department'}"
            v-model="updatecommunicationLineInfo.department"
            data-vv-name="管理部门"
            data-vv-scope="communicationlineinfo_update"
          ></async-select>
          <async-select
            label="状态属性"
            :v-data-params="{type : 'base_status'}"
            v-model="updatecommunicationLineInfo.stat"
            data-vv-name="状态属性"
            data-vv-scope="communicationlineinfo_update"
          ></async-select>

          <async-select
            label="服务商"
            :v-data-params="{type : 'line_facilitator'}"
            v-model="updatecommunicationLineInfo.facilitator"
            data-vv-name="服务商"
            data-vv-scope="communicationlineinfo_update"
          ></async-select>

          <date-picker :date="openTime"
                       v-model="updatecommunicationLineInfo.openTime"
                       :option="dateOpenTimePickerOption"></date-picker>

          <v-text-field
            label="监控策略"
            v-model="updatecommunicationLineInfo.monitorTactics"
            data-vv-name="监控策略"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>

        </v-flex>
        <v-flex xs6 pr-2>
          <v-text-field
            label="线路别名"
            v-model="updatecommunicationLineInfo.lineOtherName"
            data-vv-name="线路别名"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <async-select
            label="线路类型"
            :v-data-params="{type : 'line_type'}"
            v-model="updatecommunicationLineInfo.lineType"
            data-vv-name="线路类型"
            data-vv-scope="communicationlineinfo_update"
          ></async-select>

          <v-text-field
            label="带宽（MB）"
            v-model="updatecommunicationLineInfo.bandwidth"
            data-vv-name="带宽（MB）"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <async-select
            label="所属机构"
            :v-data-params="{type : 'line_orgnization'}"
            v-model="updatecommunicationLineInfo.attributeOrganization"
            data-vv-name="所属机构"
            data-vv-scope="communicationlineinfo_update"
          ></async-select>
          <v-text-field
            label="线路编号"
            v-model="updatecommunicationLineInfo.lineId"
            data-vv-name="线路编号"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <date-picker :date="duedate"
                       v-model="updatecommunicationLineInfo.duedate"
                       :option="dateDueDatePickerOption"></date-picker>
          <v-text-field
            label="时段策略"
            v-model="updatecommunicationLineInfo.timeTactics"
            data-vv-name="时段策略"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
        </v-flex>
      </v-layout>

      <span class="title" style="color: #1976d2">本端信息</span>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="本端设备管理IP"
            v-model="updatecommunicationLineInfo.localDeviceManageIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('本端设备管理IP','communicationlineinfo_update')[0]||[]"
            data-vv-name="本端设备管理IP"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <v-text-field
            label="本端设备端口IP"
            v-model="updatecommunicationLineInfo.localDevicePortIp"
            data-vv-name="本端设备端口IP"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
        </v-flex>
        <v-flex xs6 pr-2>
          <v-text-field
            label="本端端口名称"
            v-model="updatecommunicationLineInfo.localPortIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('本端端口名称','communicationlineinfo_update')[0]||[]"
            data-vv-name="本端端口名称"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <v-text-field
            label="本端设备名称"
            v-model="updatecommunicationLineInfo.localDeviceName"
            data-vv-name="本端设备名称"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
        </v-flex>
      </v-layout>
      <span class="title" style="color: #1976d2">对端信息</span>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="对端设备管理IP"
            v-model="updatecommunicationLineInfo.endDeviceManageIp"
            data-vv-name="对端设备管理IP"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <v-text-field
            label="对端设备端口IP"
            v-model="updatecommunicationLineInfo.endDevicePortIp"
            data-vv-name="对端设备端口IP"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
        </v-flex>
        <v-flex xs6 pr-2>
          <v-text-field
            label="对端端口名称"
            v-model="updatecommunicationLineInfo.endPortName"
            data-vv-name="对端端口名称"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
          <v-text-field
            label="对端设备名称"
            v-model="updatecommunicationLineInfo.endDeviceName"
            data-vv-name="对端设备名称"
            data-vv-scope="communicationlineinfo_update"
          ></v-text-field>
        </v-flex>
      </v-layout>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="communicationlineinfo_update()">
        <v-icon>check</v-icon>
        更新
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios, getDatePickerOptions} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import asyncSelect from '@/components/common/asyncSelect';
  import myDatepicker from '@/components/common/vuetify-datepicker-es6';
  import {Validator} from 'vee-validate';
  const dateRangePickerFormatter = 'YYYY-MM-DD';

  export default {
    beforeRouteEnter (to, from, next) {
      if (!store.state.communicationlineinfo.updatecommunicationLineInfo) {
        next({name: 'communicationLineInfo'})
      } else {
        next();
      }
    },
//    created(){
//      Validator.extend('unique', {
//        getMessage: field => `该${field}已经存在`,
//        validate: function (value) {
//          return new Promise((resolve) => {
//            axios().get("/communicationLineInfo/checkLineNameExist",{
//              params: {
//                lineName:value
//              }
//            }).then(function (response) {
//              resolve({
//                valid: response.data === 0
//              });
//            })
//          })
//        }
//      });
//    },
    data(){
      return {
        dateOpenTimePickerOption: getDatePickerOptions({
          placeholder: '开通时间',
          formatter: dateRangePickerFormatter
        }),
        openTime: {
          time: store.state.communicationlineinfo.updatecommunicationLineInfo.openTime
        },
        dateDueDatePickerOption: getDatePickerOptions({
          placeholder: '到期时间',
          formatter: dateRangePickerFormatter
        }),
        duedate: {
          time: store.state.communicationlineinfo.updatecommunicationLineInfo.duedate
        },
      }
    },
    computed: {
      ...mapState('communicationlineinfo', ['updatecommunicationLineInfo']),
    },
    components: {
      "async-select": asyncSelect,
      'date-picker': myDatepicker,
    },
    methods: {
      communicationlineinfo_update(){
        this.$validator.validateAll("communicationlineinfo_update").then((result) => {
          if (result) {
            axios().put("/communicationLineInfo", this.updatecommunicationLineInfo).then(() => {
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

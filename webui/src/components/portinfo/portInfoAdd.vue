<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'portInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">重要端口信息</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="设备管理IP"
            v-model="newPortInfo.deviceManageIp"
            v-validate="'required'"
            :error-messages="vee_errors.collect('设备管理IP','portinfo_insert')[0]||[]"
            data-vv-name="设备管理IP"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <v-text-field
            label="端口名称"
            v-model="newPortInfo.portName"

            :error-messages="vee_errors.collect('端口名称','portinfo_insert')[0]||[]"
            data-vv-name="端口名称"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <v-text-field
            label="端口IP"
            v-model="newPortInfo.portIp"
            v-validate="'required|unique'"
            :error-messages="vee_errors.collect('端口IP','portinfo_insert')[0]||[]"
            data-vv-name="端口IP"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <v-text-field
            label="带宽（MB）"
            v-model="newPortInfo.bandwidth"
            :error-messages="vee_errors.collect('带宽（MB）','portinfo_insert')[0]||[]"
            data-vv-name="带宽（MB）"
            data-vv-scope="portinfo_insert"
          ></v-text-field>

        </v-flex>
        <v-flex xs6>
          <v-text-field
            label="设备名称"
            v-model="newPortInfo.deviceName"
            :error-messages="vee_errors.collect('设备名称','portinfo_insert')[0]||[]"
            data-vv-name="设备名称"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <v-text-field
            label="端口VLAN号"
            v-model="newPortInfo.portVlan"
            data-vv-name="端口VLAN号"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <async-select
            label="管理部门"
            :v-data-params="{type : 'manage_department'}"
            v-model="newPortInfo.department"
            data-vv-name="管理部门"
            data-vv-scope="portinfo_insert"
          ></async-select>

        </v-flex>
      </v-layout>

      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="对端设备管理IP "
            v-model="newPortInfo.endDeviceManageIp"
            :error-messages="vee_errors.collect('对端设备管理IP ','portinfo_insert')[0]||[]"
            data-vv-name="对端设备管理IP "
            data-vv-scope="portinfo_insert"
          ></v-text-field>

          <v-text-field
            label="对端端口名称 "
            v-model="newPortInfo.endPortName"
            :error-messages="vee_errors.collect('对端端口名称 ','portinfo_insert')[0]||[]"
            data-vv-name="对端端口名称 "
            data-vv-scope="portinfo_insert"
          ></v-text-field>

          <v-text-field
            label="对端端口VLAN号"
            v-model="newPortInfo.endPortVlan"
            :error-messages="vee_errors.collect('对端端口VLAN号','portinfo_insert')[0]||[]"
            data-vv-name="对端端口VLAN号"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field
            label="对端设备名称 "
            v-model="newPortInfo.endDeviceName"
            :error-messages="vee_errors.collect('对端设备名称 ','portinfo_insert')[0]||[]"
            data-vv-name="对端设备名称 "
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <v-text-field
            label="对端端口IP "
            v-model="newPortInfo.serverGroupId"
            :error-messages="vee_errors.collect('对端端口IP ','portinfo_insert')[0]||[]"
            data-vv-name="对端端口IP "
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <async-select
            label="对端管理部门"
            :v-data-params="{type : 'manage_department'}"
            v-model="newPortInfo.endDepartment"
            data-vv-name="对端管理部门"
            data-vv-scope="portinfo_insert"
          ></async-select>
        </v-flex>
      </v-layout>
      <v-layout row wrap>
        <v-flex xs6 pr-2>
          <v-text-field
            label="监控策略"
            v-model="newPortInfo.monitorPolicy"
            :error-messages="vee_errors.collect('监控策略','portinfo_insert')[0]||[]"
            data-vv-name="监控策略"
            data-vv-scope="portinfo_insert"
          ></v-text-field>

          <async-select
            label="状态属性"
            :v-data-params="{type : 'base_status'}"
            v-model="newPortInfo.stat"
            data-vv-name="状态属性"
            data-vv-scope="portinfo_insert"
          ></async-select>
        </v-flex>
        <v-flex xs6>
          <v-text-field
            label="时段策略"
            v-model="newPortInfo.timePolicy"
            :error-messages="vee_errors.collect('时段策略','portinfo_insert')[0]||[]"
            data-vv-name="时段策略"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
          <v-text-field
            label="重要端口用途"
            v-model="newPortInfo.importantPortUse"
            :error-messages="vee_errors.collect('重要端口用途','portinfo_insert')[0]||[]"
            data-vv-name="重要端口用途"
            data-vv-scope="portinfo_insert"
          ></v-text-field>
        </v-flex>
        <v-text-field
          label="备注"
          v-model="newPortInfo.remarks"
          :error-messages="vee_errors.collect('备注','portinfo_insert')[0]||[]"
          data-vv-name="备注"
          data-vv-scope="portinfo_insert"
          textarea
        ></v-text-field>
      </v-layout>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="portinfo_insert()">
        <v-icon>check</v-icon>
        保存


      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import asyncSelect from '@/components/common/asyncSelect';
  import {Validator} from 'vee-validate';


  var newPortInfo = {
    importantStat: "",
    deviceManageIp: "",
    portName: "",
    portIp: "",
    bandwidth: "",
    endDeviceManageIp: "",
    endDeviceName: "",
    endPortName: "",
    serverGroupId: "",
    endPortVlan: "",
    endDepartment: "",
    monitorPolicy: "",
    timePolicy: "",
    stat: "",
    importantPortUse: "",
    remarks: "",
    userId: "",
    groupId: "",
    deviceId: "",
  }
  export default {
    data(){
      return {
        newPortInfo: _.cloneDeep(newPortInfo)
      }
    },
    mounted(){
      var vm = this;
      Validator.extend('unique', {
        getMessage: field => `设备管理ip+端口ip已经存在`,
        validate: function (value) {
          return new Promise((resolve) => {
            axios().get("/portinfo/checkPortIpExist",{
                params:{
                  deviceManageIp:vm.newPortInfo.deviceManageIp,
                  portIp:value
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
    components: {
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('portinfo', ['selectedNode']),
    },
    methods: {
      portinfo_insert(){
        this.$validator.validateAll("portinfo_insert").then((result) => {
          if (result) {
            this.newPortInfo.groupId = this.selectedNode.id;
            axios().post("/portinfo", this.newPortInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'portInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

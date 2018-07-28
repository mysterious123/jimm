<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'policyMaintenance'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">维护期修改</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row>
        <v-flex xs8>
          <v-text-field
            label="维护期名称"
            v-model="updatePolicyMaintenance.maintenanceName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('维护期名称','policymaintenance_update')[0]||[]"
            data-vv-name="维护期名称"
            data-vv-scope="policymaintenance_update"
          ></v-text-field>
        </v-flex>
        <v-flex xs4 offset-xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'开启'" v-model="updatePolicyMaintenance.status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <date-picker :date="startDate"
                   v-model="updatePolicyMaintenance.startDate"
                   v-validate="'required'"
                   data-vv-name="开始日期"
                   data-vv-scope="dateRangerPicker"
                   :error-messages="vee_errors.collect('开始日期','dateRangerPicker')[0]||[]"
                   :option="dateStartPickerOption"></date-picker>
      <date-picker :date="endDate"
                   v-model="updatePolicyMaintenance.endDate"
                   v-validate="'required'"
                   data-vv-name="结束日期"
                   data-vv-scope="dateRangerPicker"
                   :error-messages="vee_errors.collect('结束日期','dateRangerPicker')[0]||[]"
                   :option="dateEndPickerOption"></date-picker>
      <v-text-field
        label="备注"
        v-model="updatePolicyMaintenance.remark"
        v-validate="'required'"
        :error-messages="vee_errors.collect('备注','policymaintenance_update')[0]||[]"
        data-vv-name="备注"
        data-vv-scope="policymaintenance_update"
      ></v-text-field>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="policymaintenance_update()">
        <v-icon>check</v-icon>
        更新

      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {getDatePickerOptions, setDateTimeRange, axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import myDatepicker from '@/components/common/vuetify-datepicker-es6';

  const dateRangePickerFormatter = 'YYYY/MM/DD HH:mm:ss';

  export default {
    data(){
      return {
        dateStartPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '开始日期',
          formatter: dateRangePickerFormatter
        }),
        startDate: {
          time: store.state.policymaintenance.updatePolicyMaintenance.startDate
        },
        dateEndPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '结束日期',
          formatter: dateRangePickerFormatter
        }),
        endDate: {
          time: store.state.policymaintenance.updatePolicyMaintenance.endDate
        },

      }
    },
    components: {
      'date-picker': myDatepicker,
    },
    beforeRouteEnter (to, from, next) {
      if (!store.state.policymaintenance.updatePolicyMaintenance) {
        next({name:'policyMaintenance'})
      } else {
        next();
      }
    },
    computed: {
      ...mapState('policymaintenance', ['updatePolicyMaintenance']),
    },
    methods: {
      policymaintenance_update(){
        this.$validator.validateAll("policymaintenance_update").then((result) => {
          if (result) {
              console.log(this.updatePolicyMaintenance.status);
            axios().put("/policyMaintenance", this.updatePolicyMaintenance).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'policyMaintenance'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

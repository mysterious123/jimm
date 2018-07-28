<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'policyPeriod'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">新增监控时段</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row>
        <v-flex xs8>
      <v-text-field
        label="时段策略名称"
        v-model="newPolicyPeriod.periodName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('时段策略名称','policyperiod_insert')[0]||[]"
        data-vv-name="时段策略名称"
        data-vv-scope="policyperiod_insert"
      ></v-text-field>
        </v-flex>
        <v-flex xs4 offset-xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'全局'" v-model="newPolicyPeriod.nature"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周一开始"
            v-model="newPolicyPeriod.startperiod1"
            :error-messages="vee_errors.collect('周一开始','policyperiod_insert')[0]||[]"
            data-vv-name="周一开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周一结束"
            v-model="newPolicyPeriod.endperiod1"
            :error-messages="vee_errors.collect('周一结束','policyperiod_insert')[0]||[]"
            data-vv-name="周一结束"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period1status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周二开始"
            v-model="newPolicyPeriod.startperiod2"
            data-vv-name="周二开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周二结束"
            v-model="newPolicyPeriod.endperiod2"
            data-vv-name="周二结束"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period2status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周三开始"
            v-model="newPolicyPeriod.startperiod3"
            data-vv-name="周三开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周三结束"
            v-model="newPolicyPeriod.endperiod3"
            data-vv-name="周三结束"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period3status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周四开始"
            v-model="newPolicyPeriod.startperiod4"
            data-vv-name="周四开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周四结束"
            v-model="newPolicyPeriod.endperiod4"
            data-vv-name="周四结束"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period4status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周五开始"
            v-model="newPolicyPeriod.startperiod5"
            data-vv-name="周一开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周五结束"
            v-model="newPolicyPeriod.endperiod5"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period5status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周六开始"
            v-model="newPolicyPeriod.startperiod6"
            data-vv-name="周六开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周六结束"
            v-model="newPolicyPeriod.endperiod6"
            data-vv-name="周六结束"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period6status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周日开始"
            v-model="newPolicyPeriod.startperiod7"
            data-vv-name="周日开始"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周日结束"
            v-model="newPolicyPeriod.endperiod7"
            data-vv-name="周日结束"
            data-vv-scope="policyperiod_insert"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="newPolicyPeriod.period7status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="policyperiod_insert()">
        <v-icon>check</v-icon>
        保存
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';

  var newPolicyPeriod = {
    periodName : "",
    startperiod1 : "",
    startperiod2 : "",
    startperiod3 : "",
    startperiod4 : "",
    startperiod5 : "",
    startperiod6 : "",
    startperiod7 : "",
    endperiod1 : "",
    endperiod2 : "",
    endperiod3 : "",
    endperiod4 : "",
    endperiod5 : "",
    endperiod6 : "",
    endperiod7 : "",
    period1status : true,
    period2status : true,
    period3status : true,
    period4status : true,
    period5status : true,
    period6status : true,
    period7status : true,
    nature : true,
    createUser : "",
  }
  export default {
    data(){
      return {
        newPolicyPeriod : _.cloneDeep(newPolicyPeriod),
        ex11: true,
      }
    },
    methods:{
      policyperiod_insert(){
        this.$validator.validateAll("policyperiod_insert").then((result) => {
          if (result) {
            axios().post("/policyPeriod",this.newPolicyPeriod).then(()=>{
              this.simpleInfo("操作成功")
              this.pagerouter({name:'policyPeriod'})
            }).catch((error)=>{
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

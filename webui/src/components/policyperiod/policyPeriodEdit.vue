<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'policyPeriod'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">监控时段修改</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <v-layout row>
        <v-flex xs8>
          <v-text-field
            label="时段策略名称"
            v-model="updatePolicyPeriod.periodName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('时段策略名称','policyperiod_update')[0]||[]"
            data-vv-name="时段策略名称"
            data-vv-scope="policyperiod_update"
          ></v-text-field>
        </v-flex>
        <v-flex xs4 offset-xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'全局'" v-model="updatePolicyPeriod.nature"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周一开始"
            v-model="updatePolicyPeriod.startperiod1"
            :error-messages="vee_errors.collect('周一开始','policyperiod_update')[0]||[]"
            data-vv-name="周一开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周一结束"
            v-model="updatePolicyPeriod.endperiod1"
            :error-messages="vee_errors.collect('周一结束','policyperiod_update')[0]||[]"
            data-vv-name="周一结束"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period1status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周二开始"
            v-model="updatePolicyPeriod.startperiod2"
            data-vv-name="周二开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周二结束"
            v-model="updatePolicyPeriod.endperiod2"
            data-vv-name="周二结束"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period2status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周三开始"
            v-model="updatePolicyPeriod.startperiod3"
            data-vv-name="周三开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周三结束"
            v-model="updatePolicyPeriod.endperiod3"
            data-vv-name="周三结束"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period3status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周四开始"
            v-model="updatePolicyPeriod.startperiod4"
            data-vv-name="周四开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周四结束"
            v-model="updatePolicyPeriod.endperiod4"
            data-vv-name="周四结束"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period4status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周五开始"
            v-model="updatePolicyPeriod.startperiod5"
            data-vv-name="周五开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周五结束"
            v-model="updatePolicyPeriod.endperiod5"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period5status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周六开始"
            v-model="updatePolicyPeriod.startperiod6"
            data-vv-name="周六开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周六结束"
            v-model="updatePolicyPeriod.endperiod6"
            data-vv-name="周六结束"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period6status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout row justify-space-between>
        <v-flex xs2>
          <v-text-field
            label="周日开始"
            v-model="updatePolicyPeriod.startperiod7"
            data-vv-name="周日开始"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-text-field
            label="周日结束"
            v-model="updatePolicyPeriod.endperiod7"
            data-vv-name="周日结束"
            data-vv-scope="policyperiod_update"
            value="00:00:00"
            type="time"
          ></v-text-field>
        </v-flex>
        <v-flex xs2>
          <v-card flat>
            <v-card-text>
              <v-switch v-bind:label="'是否监控'" v-model="updatePolicyPeriod.period7status"></v-switch>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="policyperiod_update()">
        <v-icon>check</v-icon>
        更新
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';

  export default {
    beforeRouteEnter (to, from, next) {
      if(!store.state.policyperiod.updatePolicyPeriod) {
        next({name : 'policyPeriod'})
      }else{
        next();
      }
    },
    computed: {
      ...mapState('policyperiod', ['updatePolicyPeriod']),
    },
    methods:{
      policyperiod_update(){
        this.$validator.validateAll("policyperiod_update").then((result) => {
          if (result) {
            axios().put("/policyPeriod",this.updatePolicyPeriod).then(()=>{
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

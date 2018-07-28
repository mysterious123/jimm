<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'operationPolicyInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">策略编辑</v-toolbar-title>
    </v-toolbar>
    <v-tabs dark grow v-model="active">
      <v-tabs-bar class="cyan">
        <v-tabs-item
          v-for="tab in table_tabs"
          :key="tab"
          :href="'#' + tab"
          ripple
        >
          {{tab}}
        </v-tabs-item>
        <v-tabs-slider class="yellow"></v-tabs-slider>
      </v-tabs-bar>
      <v-tabs-items>
        <v-tabs-content
          v-for="tab in table_tabs"
          :key="tab"
          :id="tab"
        >
          <v-card flat v-if="tab=='策略管理'">
            <v-card-text>
              <v-text-field
                label="策略名称"
                v-model="updateOperationPolicyInfo.policyName"
                v-validate="'required'"
                :error-messages="vee_errors.collect('策略名称','operationpolicyinfo_update')[0]||[]"
                data-vv-name="策略名称"
                data-vv-scope="operationpolicyinfo_update"
              ></v-text-field>
              <async-select
                label="策略类型"
                :v-data-params="{type : 'policy_type'}"
                v-validate="'required'"
                :error-messages="vee_errors.collect('策略类型','operationpolicyinfo_update')[0]||[]"
                v-model="updateOperationPolicyInfo.policyType"
                data-vv-name="策略类型"
                data-vv-scope="operationpolicyinfo_update"
              ></async-select>
              <!--<v-text-field-->
                <!--label="属性"-->
                <!--v-model="updateOperationPolicyInfo.nature"-->
                <!--v-validate="'required'"-->
                <!--:error-messages="vee_errors.collect('属性','operationpolicyinfo_update')[0]||[]"-->
                <!--data-vv-name="属性"-->
                <!--data-vv-scope="operationpolicyinfo_update"-->
              <!--&gt;</v-text-field>-->
              <v-radio-group v-model="updateOperationPolicyInfo.nature" column>
                <v-radio label="public" value="1" ></v-radio>
                <v-radio label="private" value="0"></v-radio>
              </v-radio-group>

            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="success" dark @click.stop="operationpolicyinfo_update()">
                <v-icon>check</v-icon>
                更新
              </v-btn>
            </v-card-actions>
          </v-card>
          <v-card flat v-if="tab=='策略参数'">
            <operation-policy-param-list></operation-policy-param-list>
          </v-card>
        </v-tabs-content>
      </v-tabs-items>
    </v-tabs>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import store from '@/store';
  import operationPolicyParamList from "@/components/operationpolicyparam/operationPolicyParamList"
  import asyncSelect from '@/components/common/asyncSelect';


  export default {
    beforeRouteEnter(to, from, next) {
      if (!store.state.operationpolicyinfo.updateOperationPolicyInfo) {
        next({name: 'operationPolicyInfo'})
      } else {
        next();
      }
    },
    created() {
      this.setOperation("update");
    },
    components:{
      "operation-policy-param-list" : operationPolicyParamList,
      "async-select": asyncSelect,
    },
    data() {
      return {
        table_tabs: ['策略管理', '策略参数']
      }
    },
    computed: {
      ...mapState('operationpolicyinfo', ['operation','updateOperationPolicyInfo']),
      active : {
        get() {
          return this.$store.state.operationpolicyinfo.active
        },
        set(value) {
          this.setActive(value);
        }
      },
    },
    methods: {
      ...mapMutations('operationpolicyinfo', [
        'setOperation',
        'setActive',
      ]),
      operationpolicyinfo_update() {
        this.$validator.validateAll("operationpolicyinfo_update").then((result) => {
          if (result) {
            axios().put("/operationPolicyInfo", this.updateOperationPolicyInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'operationPolicyInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

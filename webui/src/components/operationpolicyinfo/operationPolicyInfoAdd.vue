<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'operationPolicyInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">策略新增</v-toolbar-title>
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
                v-model="newOperationPolicyInfo.policyName"
                v-validate="'required'"
                :error-messages="vee_errors.collect('策略名称','operationpolicyinfo_insert')[0]||[]"
                data-vv-name="策略名称"
                data-vv-scope="operationpolicyinfo_insert"
              ></v-text-field>
              <async-select
                label="策略类型"
                :v-data-params="{type : 'policy_type'}"
                v-validate="'required'"
                :error-messages="vee_errors.collect('策略类型','operationpolicyinfo_insert')[0]||[]"
                v-model="newOperationPolicyInfo.policyType"
                data-vv-name="策略类型"
                data-vv-scope="operationpolicyinfo_insert"
              ></async-select>
              <!--<v-text-field-->
                <!--label="属性"-->
                <!--v-model="newOperationPolicyInfo.nature"-->
                <!--v-validate="'required'"-->
                <!--:error-messages="vee_errors.collect('属性','operationpolicyinfo_insert')[0]||[]"-->
                <!--data-vv-name="属性"-->
                <!--data-vv-scope="operationpolicyinfo_insert"-->
              <!--&gt;</v-text-field>-->
              <v-radio-group v-model="newOperationPolicyInfo.nature" column>
                <v-radio label="public" value="1" ></v-radio>
                <v-radio label="private" value="0"></v-radio>
              </v-radio-group>
              <!--<v-text-field-->
                <!--label="创建人"-->
                <!--v-model="newOperationPolicyInfo.createPerson"-->
                <!--v-validate="'required'"-->
                <!--:error-messages="vee_errors.collect('创建人','operationpolicyinfo_insert')[0]||[]"-->
                <!--data-vv-name="创建人"-->
                <!--data-vv-scope="operationpolicyinfo_insert"-->
              <!--&gt;</v-text-field>-->
              <!--<v-text-field-->
                <!--label="创建时间"-->
                <!--v-model="newOperationPolicyInfo.createTime"-->
                <!--v-validate="'required'"-->
                <!--:error-messages="vee_errors.collect('创建时间','operationpolicyinfo_insert')[0]||[]"-->
                <!--data-vv-name="创建时间"-->
                <!--data-vv-scope="operationpolicyinfo_insert"-->
              <!--&gt;</v-text-field>-->

            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="success" dark @click.stop="operationpolicyinfo_insert()">
                <v-icon>check</v-icon>
                保存
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
  import operationPolicyParamList from "@/components/operationpolicyparam/operationPolicyParamList"
  import asyncSelect from '@/components/common/asyncSelect';

  //  var newOperationPolicyInfo = {
//    policyName: "",
//    policyType: "",
//    nature: "",
//    createPerson: "",
//    createTime: "",
//    operationPolicyParams:[],
//  }
  export default {
    created(){
      this.setOperation("insert");
    },
    data() {
      return {
        table_tabs: ['策略管理', '策略参数']
      }
    },
    components:{
      "operation-policy-param-list" : operationPolicyParamList,
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('operationpolicyinfo', ['operation','insert_items','newOperationPolicyInfo']),
      active : {
        get() {
          return this.$store.state.operationpolicyinfo.active
        },
        set(value) {
          this.setActive(value);
        }
      }
    },
    methods: {
      ...mapMutations('operationpolicyinfo', [
        'setOperation',
        'setActive',
      ]),
      operationpolicyinfo_insert() {
        this.$validator.validateAll("operationpolicyinfo_insert").then((result) => {
          if (result) {
            this.newOperationPolicyInfo.operationPolicyParams = this.insert_items;
            axios().post("/operationPolicyInfo", this.newOperationPolicyInfo).then(() => {
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

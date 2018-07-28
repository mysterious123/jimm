<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'zMibInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">修改MIB指标信息</v-toolbar-title>
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
          <v-card flat v-if="tab=='MIB指标'">
            <v-card-text>
              <async-select
                label="指标类型"
                :v-data-params="{type : 'index_type'}"
                v-model="updatezMibInfo.indexType"
                data-vv-name="指标类型"
                data-vv-scope="zmibinfo_update"
              ></async-select>
              <v-text-field
                label="指标名称"
                v-model="updatezMibInfo.indexName"
                v-validate="'required'"
                :error-messages="vee_errors.collect('指标名称','zmibinfo_update')[0]||[]"
                data-vv-name="指标名称"
                data-vv-scope="zmibinfo_update"
              ></v-text-field>
              <async-select
                label="厂家品牌"
                v-model="updatezMibInfo.venderBrand"
                :v-data-url="'/venderInfo'"
                :v-data-params="{}"
                item-text="venderName"
                item-value="venderName"
                data-vv-name="厂家品牌"
                data-vv-scope="zmibinfo_update"
              ></async-select>
              <v-text-field
                label="指标组"
                v-model="updatezMibInfo.indexGroup"
                v-validate="'required'"
                :error-messages="vee_errors.collect('指标组','zmibinfo_update')[0]||[]"
                data-vv-name="指标组"
                data-vv-scope="zmibinfo_update"
              ></v-text-field>
              <v-text-field
                label="单位"
                v-model="updatezMibInfo.indexUnit"
                v-validate="'required'"
                :error-messages="vee_errors.collect('单位','zmibinfo_update')[0]||[]"
                data-vv-name="单位"
                data-vv-scope="zmibinfo_update"
              ></v-text-field>
              <v-text-field
                label="计算公式"
                v-model="updatezMibInfo.calculationFormula"
                v-validate="'required'"
                :error-messages="vee_errors.collect('计算公式','zmibinfo_update')[0]||[]"
                data-vv-name="计算公式"
                data-vv-scope="zmibinfo_update"
              ></v-text-field>
              <v-text-field
                label="内容描述"
                v-model="updatezMibInfo.contentDescribe"
                data-vv-name="内容描述"
                data-vv-scope="zmibinfo_update"
              ></v-text-field>

            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="success" dark @click.stop="zmibinfo_update()">
                <v-icon>check</v-icon>
                更新
              </v-btn>
            </v-card-actions>
          </v-card>
          <z-mib-oid-list v-if="tab=='OID'"></z-mib-oid-list>
        </v-tabs-content>
      </v-tabs-items>
    </v-tabs>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import zMibOidList from '@/components/zmiboid/zMibOidList';
  import store from '@/store';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import asyncSelect from '@/components/common/asyncSelect';


  export default {
    beforeRouteEnter(to, from, next) {
      if (!store.state.zmibinfo.updatezMibInfo) {
        next({name:'zMibInfo'})
      } else {
        next();
      }
    },
    data() {
      return {
        table_tabs: ['MIB指标', 'OID'],
      }
    },
    components: {
      "z-mib-oid-list": zMibOidList,
      "async-select": asyncSelect,
    },
    computed: {
      ...mapState('zmibinfo', ['updatezMibInfo']),
      active: {
        get() {
          return this.$store.state.zmibinfo.editMibActive
        },
        set(value) {
          this.setEditMibActive(value);
        }
      }
    },
    methods: {
      ...mapMutations('zmibinfo', [
        'setEditMibActive'
      ]),
      zmibinfo_update() {
        this.$validator.validateAll("zmibinfo_update").then((result) => {
          if (result) {
            axios().put("/zMibInfo", this.updatezMibInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'zMibInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

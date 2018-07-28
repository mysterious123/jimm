<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="pagerouter({name:'zAgentInfo'})" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">Agent编辑</v-toolbar-title>
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
          <v-card flat v-if="tab=='Agent指标'">
            <v-card-text>
              <v-text-field
                label="指标名称"
                v-model="updateZAgentInfo.indexName"
                v-validate="'required'"
                :error-messages="vee_errors.collect('指标名称','zagentinfo_update')[0]||[]"
                data-vv-name="指标名称"
                data-vv-scope="zagentinfo_update"
              ></v-text-field>
              <v-text-field
                label="指标简写"
                v-model="updateZAgentInfo.indexShorthand"
                v-validate="'required'"
                :error-messages="vee_errors.collect('指标简写','zagentinfo_update')[0]||[]"
                data-vv-name="指标简写"
                data-vv-scope="zagentinfo_update"
              ></v-text-field>
              <!--<v-text-field-->
                <!--label="监控代理"-->
                <!--v-model="updateZAgentInfo.monitorAgent"-->
                <!--v-validate="'required'"-->
                <!--:error-messages="vee_errors.collect('监控代理','zagentinfo_update')[0]||[]"-->
                <!--data-vv-name="监控代理"-->
                <!--data-vv-scope="zagentinfo_update"-->
              <!--&gt;</v-text-field>-->
              <async-select
                label="监控代理"
                :v-data-url="'/agentInfo'"
                :v-data-params="{}"
                item-text="agentName"
                item-value="agentName"
                v-validate="'required'"
                :error-messages="vee_errors.collect('监控代理','zagentinfo_update')[0]||[]"
                v-model="updateZAgentInfo.monitorAgent"
                data-vv-name="监控代理"
                data-vv-scope="zagentinfo_update"
              ></async-select>
              <v-text-field
                label="单位"
                v-model="updateZAgentInfo.unit"
                v-validate="'required'"
                :error-messages="vee_errors.collect('单位','zagentinfo_update')[0]||[]"
                data-vv-name="单位"
                data-vv-scope="zagentinfo_update"
              ></v-text-field>
              <v-text-field
                label="关键字"
                v-model="updateZAgentInfo.keyWord"
                data-vv-scope="zagentinfo_update"
              ></v-text-field>
              <v-text-field
                label="计算公式"
                v-model="updateZAgentInfo.calculationFormula"
                v-validate="'required'"
                :error-messages="vee_errors.collect('计算公式','zagentinfo_update')[0]||[]"
                data-vv-name="计算公式"
                data-vv-scope="zagentinfo_update"
              ></v-text-field>
              <v-text-field
                label="内容描述"
                v-model="updateZAgentInfo.contentDescribe"
                v-validate="'required'"
                :error-messages="vee_errors.collect('内容描述','zagentinfo_update')[0]||[]"
                data-vv-name="内容描述"
                data-vv-scope="zagentinfo_update"
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="success" dark @click.stop="zagentinfo_update()">
                <v-icon>check</v-icon>
                更新
              </v-btn>
            </v-card-actions>
          </v-card>
          <v-card v-if="tab=='ColumnInfo'">
            <z-agent-column-info-list></z-agent-column-info-list>
          </v-card>
        </v-tabs-content>
      </v-tabs-items>
    </v-tabs>
  </v-card>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import zAgentColumnInfoList from '@/components/zagentcolumninfo/zAgentColumnInfoList';
  import store from '@/store';
  import asyncSelect from '@/components/common/asyncSelect';


  export default {
    beforeRouteEnter(to, from, next) {
      if (!store.state.zagentinfo.updateZAgentInfo) {
        next({name:'zAgentInfo'})
      } else {
        next();
      }
    },
    data() {
      return {
        table_tabs: ['Agent指标', 'ColumnInfo']
      }
    },
    components: {
      "z-agent-column-info-list": zAgentColumnInfoList,
      "async-select": asyncSelect
    },
    computed: {
      ...mapState('zagentinfo', ['updateZAgentInfo']),
      active: {
        get() {
          return this.$store.state.zagentinfo.editAgentActive
        },
        set(value) {
          this.setEditAgentActive(value);
        }
      }
    },
    methods: {
      ...mapMutations('zagentinfo', [
        'setEditAgentActive'
      ]),
      zagentinfo_update() {
        this.$validator.validateAll("zagentinfo_update").then((result) => {
          if (result) {
            axios().put("/zAgentInfo", this.updateZAgentInfo).then(() => {
              this.simpleInfo("操作成功")
              this.pagerouter({name: 'zAgentInfo'})
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

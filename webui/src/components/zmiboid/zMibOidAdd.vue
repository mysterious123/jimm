<template>
  <v-card>
    <v-toolbar class="cyan" dark dense>
      <v-btn icon @click="$router.go(-1)" dark>
        <v-icon>arrow_back</v-icon>
      </v-btn>
      <v-toolbar-title class="white--text">zmiboidAdd</v-toolbar-title>
    </v-toolbar>
    <v-card-text>
      <!--<v-text-field-->
      <!--label="MIB编号"-->
      <!--v-model="newZMibOid.mibId"-->
      <!--v-validate="'required'"-->
      <!--:error-messages="vee_errors.collect('MIB编号','zmiboid_insert')[0]||[]"-->
      <!--data-vv-name="MIB编号"-->
      <!--data-vv-scope="zmiboid_insert"-->
      <!--&gt;</v-text-field>-->
      <v-text-field
        label="OID名称"
        v-model="newZMibOid.oidName"
        v-validate="'required'"
        :error-messages="vee_errors.collect('OID名称','zmiboid_insert')[0]||[]"
        data-vv-name="OID名称"
        data-vv-scope="zmiboid_insert"
      ></v-text-field>
      <v-text-field
        label="OID单位"
        v-model="newZMibOid.oidUnit"
        v-validate="'required'"
        :error-messages="vee_errors.collect('OID单位','zmiboid_insert')[0]||[]"
        data-vv-name="OID单位"
        data-vv-scope="zmiboid_insert"
      ></v-text-field>
      <v-text-field
        label="OID值"
        v-model="newZMibOid.oidValue"
        v-validate="'required'"
        :error-messages="vee_errors.collect('OID值','zmiboid_insert')[0]||[]"
        data-vv-name="OID值"
        data-vv-scope="zmiboid_insert"
      ></v-text-field>
      <v-layout row wrap>
        <v-flex xs3>
          <v-switch label="子类" v-model="newZMibOid.smallType" value="1"></v-switch>
        </v-flex>
        <v-flex xs3>
          <v-switch label="BPS" v-model="newZMibOid.bps" value="1" ></v-switch>
        </v-flex>
        <v-flex xs3>
          <v-switch label="本端" v-model="newZMibOid.local" value="1"></v-switch>
        </v-flex>
        <v-flex xs3>
          <v-switch label="对端" v-model="newZMibOid.endOn" value="1"></v-switch>
        </v-flex>
      </v-layout>
      <!--<v-text-field-->
        <!--label="子类"-->
        <!--v-model="newZMibOid.smallType"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('子类','zmiboid_insert')[0]||[]"-->
        <!--data-vv-name="子类"-->
        <!--data-vv-scope="zmiboid_insert"-->
      <!--&gt;</v-text-field>-->
      <!--<v-text-field-->
        <!--label="BPS"-->
        <!--v-model="newZMibOid.bps"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('BPS','zmiboid_insert')[0]||[]"-->
        <!--data-vv-name="BPS"-->
        <!--data-vv-scope="zmiboid_insert"-->
      <!--&gt;</v-text-field>-->
      <!--<v-text-field-->
        <!--label="本端"-->
        <!--v-model="newZMibOid.local"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('本端','zmiboid_insert')[0]||[]"-->
        <!--data-vv-name="本端"-->
        <!--data-vv-scope="zmiboid_insert"-->
      <!--&gt;</v-text-field>-->
      <!--<v-text-field-->
        <!--label="对端"-->
        <!--v-model="newZMibOid.endOn"-->
        <!--v-validate="'required'"-->
        <!--:error-messages="vee_errors.collect('对端','zmiboid_insert')[0]||[]"-->
        <!--data-vv-name="对端"-->
        <!--data-vv-scope="zmiboid_insert"-->
      <!--&gt;</v-text-field>-->

    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="success" dark @click.stop="zmiboid_insert()">
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


  var newZMibOid = {
    mibId: "",
    oidName: "",
    oidUnit: "",
    oidValue: "",
    smallType: "0",
    bps: "0",
    local: "0",
    endOn: "0",
  }
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
        newZMibOid: _.cloneDeep(newZMibOid)
      }
    },
    computed: {
      ...mapState('zmibinfo', ['updatezMibInfo']),
    },
    methods: {
      zmiboid_insert() {
        this.$validator.validateAll("zmiboid_insert").then((result) => {
          if (result) {
            this.newZMibOid.mibId = this.updatezMibInfo.id;
            axios().post("/zMibOid", this.newZMibOid).then(() => {
              this.simpleInfo("操作成功")
              this.$router.go(-1);
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      }
    }
  }
</script>

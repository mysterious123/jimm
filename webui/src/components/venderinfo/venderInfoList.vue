<template>
  <v-layout column>
    <v-flex xs12 sm10 offset-sm1>
      <v-toolbar class="cyan" dark dense>
        <v-toolbar-title class="white--text">设备库</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click="addVenderInfo()" v-if="isPermitted('venderInfo/insert')">
          <v-icon>add</v-icon>
        </v-btn>
      </v-toolbar>
      <v-container fluid grid-list-md class="grey lighten-4">
        <v-layout row wrap>
          <v-flex
            v-bind="{ [`xs${3}`]:true }"
            v-for="item in venderInfo"
            :key="item.venderName"
          >
            <v-card>
              <v-card-title>
                <span class="headline" v-text="item.venderName"></span>
              </v-card-title>
              <v-card-media
                :src="'/core/images/logo/'+item.logoRoute"
                height="200px"
                @click="updateVenderDetail(item)"
              >
              </v-card-media>
              <v-card-actions class="white">
                <v-spacer></v-spacer>
                <v-btn icon @click="deleteVenderInfo(item.id)" v-if="isPermitted('venderInfo/delete')">
                  <v-icon>delete</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-flex>
    <v-dialog v-model="venderinfo_new_dialog" persistent>
      <v-card>
        <v-toolbar color="teal lighten-3" dark>
          <v-toolbar-title>设备库信息</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-text-field
            label="厂家名"
            v-model="newVenderInfo.venderName"
            v-validate="'required'"
            :error-messages="vee_errors.collect('厂家名','vender_insert')[0]||[]"
            data-vv-name="厂家名"
            data-vv-scope="vender_insert"
          ></v-text-field>

          <div id="myAwesomeDropzone"
               class="dropzone"
          >
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="blue--text darken-1" flat @click.native.stop="venderinfo_new_dialog = false">取消</v-btn>
          <v-btn class="blue--text darken-1" flat @click.stop="venderinfo_insert()">
            保存


          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import Dropzone from 'dropzone';
  import 'dropzone/dist/dropzone.css'
  import VCardTitle from "vuetify/es5/components/VCard/VCardTitle";
  var newVenderInfo = {
    venderName: "",
    logoRoute: ""
  }
  export default {
    components: {VCardTitle}, mounted() {
      var vm = this;
      Dropzone.autoDiscover = false;
      var myDropzone = new Dropzone("#myAwesomeDropzone", {
        url: "/core/venderInfo/uploadLogo",
        addRemoveLinks: true,
        method: 'post',
        maxFilesize: 2,
        success: function (file, response, e) {
          vm.newVenderInfo.logoRoute = file.name;
        }
      });
      this.queryItems();
    },
    data: () => ({
      newVenderInfo: _.cloneDeep(newVenderInfo),
      venderinfo_new_dialog: false,
      newVenderInfo: {},
      venderInfo: {},
    }),
    methods: {
      ...mapMutations('venderinfo', [
        'setUpdateVenderInfo',
        'clearUpdateVenderInfo',
        'setVenderInfo'
      ]),
      queryItems(data) {
        var vm = this;
        axios().get("/venderInfo").then(function (response) {
          vm.venderInfo = response.data;
        })
      },
      addVenderInfo(){
        this.venderinfo_new_dialog = true;
      },
      venderinfo_insert(){
        this.$validator.validateAll("vender_insert").then((result) => {
          if (result) {
            axios().post("/venderInfo", this.newVenderInfo).then(() => {
              this.simpleInfo("操作成功");
              this.venderinfo_new_dialog = false;
              this.queryItems();
            }).catch((error) => {
              this.simpleError("后台异常,操作失败")
            })
          }
        });
      },
      updateVenderDetail(item){
        this.setVenderInfo(item);
        this.pagerouter({name: 'venderDetailInfoList'});
      },
      deleteVenderInfo(id){
        axios().delete("/venderInfo", {
          params: {
            id: id,
          }
        }).then(() => {
          this.simpleSuccess("删除成功");
          //清空select
          this.queryItems();
        })
      }
    }
  }
</script>
<!--<style lang="stylus">-->
<!--@import "../../assets/css/dropzone/dropzone.css"-->
<!--</style>-->


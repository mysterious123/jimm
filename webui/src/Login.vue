<template>
  <v-app id="app">
    <div id="particles">
    </div>
    <v-flex xs4 sm4 offset-sm4>
      <div id="inspire_login">
          <span class="addition-text" style="z-index:100"><h4 class="cyan--text">JIM</h4>
          <h6 class="cyan--text">智运维，简单无处不在</h6></span>
        <v-card flat id="main-inspire">
          <v-card-media
            height="50px"
          >
            <v-container fill-height fluid>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline cyan--text">登录</span>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-media>
          <v-card-text>
            <v-container fluid>
              <v-layout row>
                <v-flex xs12>
                  <v-text-field
                    @keyup.enter="login()"
                    name="username"
                    label="用户名"
                    v-model="username"
                    v-validate="'required'"
                    data-vv-name="用户名"
                    data-vv-scope="userLogin"
                    :error-messages="vee_errors.collect('用户名','userLogin')[0]||[]"
                  ></v-text-field>
                  <v-text-field
                    @keyup.enter="login()"
                    name="password"
                    label="密码"
                    v-model="password"
                    :append-icon="e3 ? 'visibility' : 'visibility_off'"
                    :append-icon-cb="() => (e3 = !e3)"
                    type="password"
                    :type="e3 ? 'password' : 'text'"
                    v-validate="'required'"
                    data-vv-name="密码"
                    data-vv-scope="userLogin"
                    :error-messages="vee_errors.collect('密码','userLogin')[0]||[]"
                  ></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-btn color="cyan" @click.native.stop="login()" dark block>登录</v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </v-flex>
    <div id="footer" class="primary--text"><p>© 2017 Jiudao Technology. All Rights Reservaed.</p></div>
  </v-app>
</template>

<script>
  import router from '@/router'
  import particles from 'particles.js'
  import {axios} from '@/assets/js/Utils';
  import store from '@/store'

  export default {
    mounted() {
      particlesJS.load('particles', './static/data/particles.data');
    },
    data() {
      return {
        e3: true,
        username: '',
        password: ''
      }
    },
    methods: {
      login: function () {
        let vm = this;
        this.$validator.validateAll("userLogin").then((result) => {
          if (result) {
            axios().post('/login', {
              userName: vm.username,
              password: vm.password
            }).then(function (response) {
              if (response.data && response.data.success === true) {
                vm.$session.start();
//                vm.$session.set('roleMenus', response.data.roleMenus);
//                vm.$session.set('roleMethods', response.data.roleMethods);
                vm.$session.set('user', response.data.user);
                vm.$session.set('isLogin', true);
                router.push({
                  name: 'UserPortal'
                });
              } else {
                vm.simpleError("登录失败，用户名或密码不正确！");
              }
            });
          }
        });
      }
    }
  }
</script>

<style lang="stylus">
  @import './stylus/main'
  @import "~material-design-icons-iconfont/dist/fonts/material-icons.css"
  @import "~font-awesome/css/font-awesome.min.css"
</style>

<style>

  #particles {
    position: fixed;
    width: 100%;
    height: 100%;
    background-color: #f7fafc;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50% 50%;
  }

  .addition-text {
    position: relative;
    text-align: center;
    color: white;
  }

  #inspire_login {
    margin-top: 30%;
  }

  #footer {
    position: relative;
    text-align: center;
  }

  #main-inspire {
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.2);
  }
</style>



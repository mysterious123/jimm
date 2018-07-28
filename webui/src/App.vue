<template>

  <v-app>
    <div @click="hiddenMenu()">
      <v-navigation-drawer
        width="200"
        persistent
        fixed
        :clipped="clipped"
        overflow
        clipped-left
        disable-route-watcher
        v-model="drawer"
        app
        dark
      >
        <v-list dense>
          <template v-for="(item, i) in items">
            <v-layout
              row
              v-if="item.heading"
              align-center
              :key="i"
            >
              <v-flex xs6>
                <v-subheader v-if="item.heading">
                  {{ item.heading }}
                </v-subheader>
              </v-flex>
              <v-flex xs6 class="text-xs-center">
                <a href="#!" class="body-2 black--text">EDIT</a>
              </v-flex>
            </v-layout>
            <v-list-group v-else-if="item.children" v-model="item.model">
              <v-list-tile slot="item" @click="miniVariant = false">
                <v-list-tile-action>
                  <v-icon dark>{{ item.model ? item.icon : item['icon-alt'] }}</v-icon>
                </v-list-tile-action>
                <v-list-tile-content>
                  <v-list-tile-title>
                    {{ item.text }}
                  </v-list-tile-title>
                </v-list-tile-content>
                <v-list-tile-content>
                  <v-list-tile-title class="text-lg-right">
                    <v-icon dark>{{ item.model ? 'keyboard_arrow_up' : 'keyboard_arrow_down' }}</v-icon>
                  </v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
              <v-list-tile
                v-for="(child, i) in item.children"
                :key="i"
              >
                <v-list-tile-action v-if="child.icon">
                  <v-icon dark>{{ child.icon }}</v-icon>
                </v-list-tile-action>
                <v-list-tile-content>
                  <v-list-tile-title>
                    {{ child.text }}
                  </v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list-group>
            <v-list-tile v-else @click="pagerouter({name:item.name})">
              <v-list-tile-action>
                  <img v-bind:src="'./static/img/'+item.icon">
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title>
                  {{ item.text }}
                </v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
      </v-navigation-drawer>
      <v-toolbar
        fixed
        app
        clipped-left
        class="cyan darken-2"
        dark>
        <v-toolbar-title>
          <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
        </v-toolbar-title>
        <!--
              <v-btn icon @click.native.stop="miniVariant = !miniVariant">
                <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"></v-icon>
              </v-btn>

              <v-btn icon @click.native.stop="clipped = !clipped">
                <v-icon>web</v-icon>
              </v-btn>
              <v-btn icon @click.native.stop="fixed = !fixed">
                  <v-icon>remove</v-icon>
              </v-btn>
              -->
        <v-toolbar-title>
          <img src="./assets/icon.png" alt="玖道科技" height="40" width="40"/>
        </v-toolbar-title>
        <v-toolbar-title v-text="title"></v-toolbar-title>
        <v-spacer></v-spacer>
        <div>
          <v-menu offset-y>
            <v-btn class="warning" fab small dark slot="activator">
              <v-icon>account_circle</v-icon>
            </v-btn>
            <v-list>
              <v-list-tile key="退出登陆" @click="logOut">
                <v-list-tile-title>退出登陆</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
          管理员
        </div>
      </v-toolbar>
      <main>
        <v-content>
          <v-container fluid>
            <v-layout>
              <breadcrumbs></breadcrumbs>
            </v-layout>
            <!-- route outlet -->
            <!-- component matched by the route will render here-->
            <transition name="fade" mode="out-in">
              <router-view>
              </router-view>
            </transition>
          </v-container>
        </v-content>
      </main>
      <!--
      <v-footer :fixed="fixed" class="grey">
        <v-card-text class="text-xs-center">
          <span>&copy; 2017 上海玖道信息科技股份有限公司</span>
        </v-card-text>

      </v-footer>
      -->
      <!--公共弹框组件-->
      <!--<v-snackbar-->
      <!--:timeout="timeout"-->
      <!--:top="y === 'top'"-->
      <!--:bottom="y === 'bottom'"-->
      <!--:right="x === 'right'"-->
      <!--:left="x === 'left'"-->
      <!--:color="color"-->
      <!--:multi-line="mode === 'multi-line'"-->
      <!--:vertical="mode === 'vertical'"-->
      <!--v-model="snackbarVisibleC"-->
      <!--&gt;-->
      <!--{{ text }}-->
      <!--<v-btn dark flat @click.native="snackbarVisibleC = false">Close</v-btn>-->
      <!--</v-snackbar>-->

      <v-btn fab dark fixed bottom right class="cyan mb-5" v-show="fab" @click="toTop">
        <v-icon>keyboard_arrow_up</v-icon>
      </v-btn>
    </div>
  </v-app>
</template>

<script>
  import {mapGetters, mapState, mapMutations} from 'vuex';
  import BreadCrumbs from '@/components/common/breadCrumbs'
  import {axios} from '@/assets/js/Utils';

  export default {
    data() {
      return {
        fab: false,
        clipped: true,
        drawer: false,
        fixed: true,
        items: this.$session.get('user').superMenus,
//        items: [
//          {icon: 'dashboard', text: '用户Portal', name: 'UserPortal'},
//          {icon: 'settings', text: '服务器信息管理', name: 'serverInfo'},
//          {icon: 'settings', text: '网络设备管理', name: 'deviceInfo'},
//          {icon: 'settings', text: '线路管理', name: 'communicationLineInfo'},
//          {icon: 'settings', text: '重要端口管理', name: 'portInfo'},
//          {icon: 'person', text: '联系人', name: 'userInfo'},
//          {icon: 'people', text: '管理组', name: 'manageGroupInfo'},
//          {icon: 'settings', text: '代理注册', name: 'agentInfo'},
//          {icon: 'settings', text: '设备库', name: 'venderInfo'},
//          {icon: 'settings', text: '数据字典', name: 'codeInfo'},
//          {icon: 'settings', text: '指标管理', name: 'zMibInfo'},
//          {icon: 'settings', text: '生效', name: 'releaseInfo'},
//          {icon: 'settings', text: '策略管理', name: 'operationPolicyInfo'},
//          {icon: 'settings', text: '级别阈值微调', name: 'policyCustom'},
//          {icon: 'settings', text: '维护期', name: 'policyMaintenance'},
//          {icon: 'settings', text: '监控时段', name: 'policyPeriod'},
//          {icon: 'settings', text: '数据源管理', name: 'superDatasource'},
//          {icon: 'person', text: '用户', name: 'superUser'},
//          {icon: 'people', text: '用户组', name: 'superUserGroup'},
//          {icon: 'settings', text: 'Logo设置', name: 'superLogo'},
//          {icon: 'settings', text: '级别设定', name: 'superSeverity'},
//          {icon: 'settings', text: '程序', name: 'superAutomation'},
//          {icon: 'settings', text: '日志管理', name: 'superLogManagement'}
//        ],
        miniVariant: true,
        right: false,
        rightDrawer: false,
        title: 'JIM'
      }
    },
    created() {
      window.addEventListener('scroll', this.onScroll);
    },
    destroyed() {
      window.removeEventListener('scroll', this.onScroll);
    },
    computed: ({
      ...mapState('snackbar', [
        'y', 'x', 'mode', 'timeout', 'text', 'color'
      ]),
      snackbarVisibleC: {
        get() {
          return this.$store.state.snackbar.snackbarVisible;
        },
        set(value) {
          this.closeSnackbar();
        }
      }
    }),
    components: {
      'breadcrumbs': BreadCrumbs
    },
    methods: {
      onScroll() {
        if (typeof window === 'undefined') return;
        const top = window.pageYOffset ||
          document.documentElement.offsetTop ||
          0;
        this.fab = top > 300
      },
      toTop() {
        window.scrollTo(0, 0)
      },
      logOut() {
        axios().post('/logout').then((response) => {
          this.$session.destroy();
          this.$router.push("/");
        });
      },
      hiddenMenu(){
        if(this.drawer){
          this.drawer = false;
        }
      }
    }
  }
</script>

<style lang="stylus">
  @import './stylus/main'
  @import "~material-design-icons-iconfont/dist/fonts/material-icons.css"
  @import "./assets/css/common/common.css"
  @import "./assets/css/ztree/zTreeStyle.css"
</style>


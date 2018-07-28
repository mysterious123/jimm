<template>
  <div>
    <!--公共弹框组件-->
    <v-snackbar
      :timeout="timeout"
      :top="y === 'top'"
      :bottom="y === 'bottom'"
      :right="x === 'right'"
      :left="x === 'left'"
      :color="color"
      :multi-line="mode === 'multi-line'"
      :vertical="mode === 'vertical'"
      v-model="snackbarVisibleC"
      style="z-index:999;"
    >
      {{ text }}
    </v-snackbar>
    <router-view></router-view>
  </div>
</template>
<script>
  import {mapGetters, mapState, mapMutations} from 'vuex'
//  import simplebar from 'simplebar'
  import store from '@/store'

  export default {
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
    })
  }
</script>

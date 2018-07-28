<script>
  import Vue from 'vue'
  import Vuetify from 'vuetify'
  import {axios} from '@/assets/js/Utils';
  import {cacheCode} from '@/assets/js/Utils';

  Vue.use(Vuetify)

  export default {
    extends: Vue.component("v-select"),
    props: {
      "v-data-url":{
        default: "/codeInfo"
      },
      "v-data-params":{
        required: true
      },
      "item-text":{
        default: "name"
      },
      "item-value":{
        default: "code"
      }
    },
    watch:{
      isActive(isActive){
        if(!isActive){
          this.$emit("blur");
        }
      },
      vDataParams(newVal,oldVal){
        if(!_.isEqual(newVal, oldVal)){
          axios().get(this.vDataUrl,{
            params: this.vDataParams
          }).then((response) => {
            this.$emit("update",response.data);
          });
        }
      }
    },
    mounted(){
      this.$on("click",function () {
          console.log(0);
      });
      if(this.vDataUrl){
        this.getData(this.vDataUrl);
      }
    },
    methods:{
      bindData(data){
        var vm = this;
        _.remove(vm.items, true);
        data.forEach(function (v,i) {
          vm.items.push(v);
        })
        this._mounted();
      },
      //后台查询数据方法
      _getData(vDataUrl){
        var vm = this;
        axios().get(vDataUrl,{
          params: this.vDataParams
        }).then(function (response) {
          vm.bindData(response.data);
        });
      },
      //父组件方法
      _mounted(){
        //v-select的mounted方法似乎是没有办法和java一样super调用父方法暂时复制过来
        this.$vuetify.load(() => {
          if (this._isDestroyed) return
          this.content = this.$refs.menu.$refs.content
          this.genSelectedItems()
        })
      },
      getData(vDataUrl){
        //如果查询的是code则判断是否可以从缓存中取
        if(vDataUrl == '/codeInfo' && window.jlog_code){
          var bindData = _.filter(window.jlog_code, this.vDataParams)
          if(bindData.length > 0) {
            this.bindData(bindData);
          }else{
            this._getData(vDataUrl);
          }
        }else{
          this._getData(vDataUrl);
        }
      }
    }
  }
</script>

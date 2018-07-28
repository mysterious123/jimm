<template>
  <v-breadcrumbs style="padding-left:0px;" icons divider="chevron_right" v-if="breadcrumbsItem.length">
    <v-breadcrumbs-item
      v-for="(crumb,key) in breadcrumbsItem" key="key"
    >
      <span @click.stop="linkProp(crumb)" style="">{{ crumb.meta.breadcrumb}}</span>
    </v-breadcrumbs-item>
  </v-breadcrumbs>
</template>

<script>
  import router from '@/router'
  export default {
    methods:{
      // Return the correct prop data
      linkProp:function (crumb) {
        // If it's a named route, we'll base the route
        // off of that instead
        if (crumb.name || (crumb.handler && crumb.handler.name)) {
          router.push({
            name: crumb.name || crumb.handler.name,
            params: this.$route.params
          });
        }else{
          router.push({
            path: (crumb.handler && crumb.handler.fullPath)
              ? crumb.handler.fullPath
              : crumb.path,
            params: this.$route.params
          });
        }

      }
    },
    computed:{
      breadcrumbsItem:function () {
        var crumbs = [];
        var matched = this.$route.matched;
        matched.forEach(function (route) {
          var hasBreadcrumb = route.meta && route.meta.breadcrumb;
          if(hasBreadcrumb){
            crumbs.push(route)
          }
        })
        return crumbs;
      }
    }
  }
</script>

<style>
  .breadcrumbs__item{
    padding-left: 0px;
    padding-right: 0px;
  }
</style>

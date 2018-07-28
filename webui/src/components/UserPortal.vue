<template>
  <div class="hello">
    <h2>{{ msg }}</h2>
  </div>
</template>
<!--<template>-->
      <!--<v-layout row wrap>-->
        <!--<v-flex xs12 sm2>-->
          <!--<v-card color="blue darken-1" class="white&#45;&#45;text">-->
            <!--<v-container fluid grid-list-lg>-->
              <!--<v-layout row>-->
                <!--<v-flex xs4>-->
                  <!--<div>-->
                    <!--&lt;!&ndash;<div class="headline">Halycon Days</div>&ndash;&gt;-->
                    <!--&lt;!&ndash;<div>Ellie Goulding</div>&ndash;&gt;-->
                    <!--<span>资源管理</span>-->
                  <!--</div>-->
                <!--</v-flex>-->
                <!--<v-flex xs8>-->
                  <!--<v-card-media-->
                    <!--src="/static/doc-images/cards/halcyon.png"-->
                    <!--height="60px"-->
                    <!--contain-->
                  <!--&gt;</v-card-media>-->
                <!--</v-flex>-->
              <!--</v-layout>-->
            <!--</v-container>-->
          <!--</v-card>-->
        <!--</v-flex>-->
      <!--</v-layout>-->
<!--</template>-->

<script>
export default {
  name: 'userPortal',
  data () {
    return {
      msg: '用户Portal,coming soon!'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>

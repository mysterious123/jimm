<template>
  <div
    id="e3"
    class="lighten-3"
  >
    <v-container
      fluid
      style="min-height: 0;"
      grid-list-lg
    >
      <v-layout row wrap>
        <v-flex xs4 v-for="card in cards" :key="card.name" @click="pagerouter({name:card.url})">
          <v-card :color="card.color" class="white--text">
            <v-card-title primary-title>
              <div class="headline">{{card.name}}</div>
            </v-card-title>
            <p class="text-xs-center" style="margin-top: -30px">
              <span style="font-size: 5em;cursor:pointer" >{{card.count}}</span>
            </p>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
  import {axios} from '@/assets/js/Utils';
  import _axios from "axios";

  export default {
    mounted(){
      this.queryCount();
    },
    data() {
      return {
        cards: [
          {
            name: 'MIB',
            count: 0,
            color: "green",
            url: "zMibInfo"
          }, {
            name: 'LOG',
            count: 0,
            color: "cyan",
            url: "zLogInfo"
          }, {
            name: 'AGENT',
            count: 0,
            color: "blue-grey darken-2",
            url: "zAgentInfo"
          }
        ]
      }
    },
    methods: {
      queryCount() {
        _axios.all([axios().get("/zMibInfo/queryCount"), axios().get("/zLogInfo/queryCount"), axios().get("/zAgentInfo/queryCount")])
          .then(_axios.spread((mib, log, agent )=>{
            this.cards[0].count = mib.data;
            this.cards[1].count = log.data;
            this.cards[2].count = agent.data;
          }));
      }
    }
  }
</script>

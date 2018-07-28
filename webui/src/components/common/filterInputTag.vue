<script>
  export default {
    name: 'InputTag',

    props: {
      tags: {
        type: Array,
        default: () => []
      },
      searchText: {
        type: String
      },
      placeholder: {
        type: String,
        default: ''
      },
      onChange: {
        type: Function
      },
      onFilterSelect:{
        type:Function
      },
      validate: {
        type: String,
        default: ''
      }
    },

    data () {
      return {
        message: ''
      }
    },

    methods: {
      addNew (tag) {
        if (tag && this.tags.indexOf(tag) === -1) {
          this.tags.push(tag)
          this.tagChange()
        }
      },
      remove (index) {
        this.tags.splice(index, 1)
        this.tagChange()
      },

      tagChange () {
        if (this.onChange) {
          // avoid passing the observer
          this.onChange(JSON.parse(JSON.stringify(this.tags)));
        }
      },

      filterSelect(){
        if(this.onFilterSelect){
          this.onFilterSelect();
        }
      },
      emitValue(){
        this.$emit('input',this.message);
      }
    },

    computed:{
      thisPlaceHolder:function () {
        if(this.tags.length > 0){
          return '';
        }else{
          return this.placeholder;
        }
      }
    }
  }
</script>

<template>

  <div class="vue-input-tag-wrapper">
    <v-btn @click.native.stop="filterSelect()" icon><v-icon>filter_list</v-icon></v-btn>
    <span v-for="(tag, index) in tags" v-bind:key="index">
      <span><v-chip v-model="tag.chip" disabled close @input="remove(index)">{{ tag.value }}</v-chip></span>
    </span>
    <input type="text" v-bind:placeholder="thisPlaceHolder" v-model="message" @input="emitValue" class="new-tag"/>
  </div>

</template>

<style type="text/css">

  .vue-input-tag-wrapper {
    border-bottom: 1px solid dimgrey;
    overflow: hidden;
    padding-left: 4px;
    padding-top: 4px;
    cursor: text;
    text-align: left;
    /*-webkit-appearance: textfield;*/
  }

  .vue-input-tag-wrapper .input-tag {
    background-color: #cde69c;
    border-radius: 2px;
    color: #638421;
    display: inline-block;
    font-size: 13px;
    font-weight: 400;
    margin-bottom: 4px;
    margin-right: 4px;
    padding: 3px;
  }

  .vue-input-tag-wrapper .placeholder{
    font-size:16px;
    color:dimgrey;
    font-weight: 400;
  }

  .vue-input-tag-wrapper .new-tag {
    background: transparent;
    border: 0;
    color: #777;
    font-size: 13px;
    font-weight: 400;
    margin-bottom: 6px;
    margin-top: 1px;
    outline: none;
    padding: 4px;
    padding-left: 0;
    width: 240px;
  }

  .vue-input-tag-wrapper.read-only {
    cursor: default;
  }

</style>

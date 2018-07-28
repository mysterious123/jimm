<template>
  <v-select
    v-bind="$attrs"
    append-icon=""
    chips
    tags
    single-line
    v-model="selectValue">
    <template slot="selection" slot-scope="data">
      <slot name="item">
        <v-chip
          close
          @input="data.parent.selectItem(data.item)"
          class="chip--select-multi"
        >
          {{ data.item.value }}
        </v-chip>
      </slot>
    </template>
  </v-select>
</template>
<script>
  export default {
    props: ['value'],
    data() {
      return {
        selectValue: this.value.map((v) => {
          return {value: '' + v}
        })
      }
    },
    watch: {
      selectValue(value) {
        var flag = false;
        value.forEach((v) => {
          if (typeof v != 'object') {
            flag = true;
          }
        })
        this.$emit("input", value.map((v) => {
          return v.value ? v.value : v
        }));
        if(flag) {
          this.selectValue = value.map((v) => {
            return {value: v.value ? v.value : v}
          });
        }
      }
    }
  }
</script>

<!--
自定义过滤器条件选择动态table
属性：
    filterFields:[
          {
          type:''//'string','number','time'
          value:''
          text:''
          }
    ]

    v-model:用于数据双向绑定,格式固定为
    [{
            filterField:'',
            filterCondition:'',
            filterValue:''
    }]

    formatterValue:(可选)绑定父组件中格式化结果的事件(在上述格式不满足的情况下使用)
-->

<template>
  <v-layout wrap>
    <v-flex xs12>
      <v-data-table
        v-bind:headers="headers"
        :items="customerFilter"
        hide-actions
        class="elevation-1"
      >
        <template slot="items" slot-scope="props">
          <td width="30%">
            <v-select
              v-bind:items="filterFields"
              v-model="props.item.filterField"
              label=""
              single-line
              bottom
              @input="emitValue"
            ></v-select>
          </td>
          <td width="20%">
            <v-select
              v-bind:items="filterConditions[props.item.filterField]"
              v-model="props.item.filterCondition"
              label=""
              single-line
              bottom
              @input="emitValue"
            ></v-select>
          </td>
          <td width="40%">
            <date-picker :date="props.item.dateFilterValue" @input="emitValue" v-if="filterFieldType[props.item.filterField] && filterFieldType[props.item.filterField].type=='date'" :option="dateStartPickerOption"></date-picker>
            <v-text-field
              v-else
              v-model="props.item.filterValue"
              name=""
              label=""
              @input="emitValue"
            ></v-text-field>
          </td>
          <td width="10%"><v-btn icon @click.native.stop="removeFilterRulesColumn(props.item.index)"><v-icon>delete</v-icon></v-btn></td>
        </template>
      </v-data-table>
    </v-flex>
    <v-flex xs12>
      <v-btn class="primary" dark light @click.native="addFilterRulesColumn"><v-icon>add</v-icon>添加规则</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>

  import myDatepicker from '@/components/common/vuetify-datepicker-es6';
  import {getDatePickerOptions} from '@/assets/js/Utils';
  const dateRangePickerFormatter = 'YYYY/MM/DD HH:mm:ss';

  const conditionObj = {
    'keyword':[{
      value:'=',
      text:'等于'
    },{
      value:'!=',
      text:'不等于'
    }],
    'long':[{
      value:'=',
      text:'等于'
    },{
      value:'>',
      text:'大于'
    },{
      value:'>=',
      text:'大于等于'
    },{
      value:'<',
      text:'小于'
    },{
      value:'<=',
      text:'小于等于'
    }],
    'date':[{
      value:'=',
      text:'等于'
    },{
      value:'>',
      text:'大于'
    },{
      value:'>=',
      text:'大于等于'
    },{
      value:'<',
      text:'小于'
    },{
      value:'<=',
      text:'小于等于'
    }]
  };

  export default {
    props:['filterFields','value'],
    data(){
        return{
          headers: [
            { text: '字段', value: 'field',align:'center' },
            { text: '条件', value: 'condition',align:'center'  },
            { text: '值', value: 'value',align:'center'  },
            { text: '', value: 'op',align:'center'  }
          ],
          columnIndex :0,
          customerFilter:[],
          dateStartPickerOption: getDatePickerOptions({type:'min',formatter:dateRangePickerFormatter})
        }
    },
    methods:{
      addFilterRulesColumn:function () {//新增过滤器，添加规则
        this.customerFilter.push({
          index:this.columnIndex,
          filterField:'',
          filterCondition:'',
          filterValue:'',
          dateFilterValue:{
            time:''
          },
          filterFieldType:'',
          filterFieldFormat:''
        });
        this.columnIndex++;
      },
      removeFilterRulesColumn:function (index) {//新增过滤器，列移除
        this.customerFilter =this.customerFilter.filter(function (val) {
          return val.index != index;
        });
        this.$emit('input',this.emitCustomerFilter);
      },
      emitValue:function () {
        this.$emit('input',this.emitCustomerFilter);
        this.$emit('formatterValue');
      }
    },
    watch:{
      'value':function (val) {
        this.customerFilter = val;
      }
    },
    computed:{
      filterConditions:function () {
        var thisFilterCondition = {};
        this.filterFields.forEach(function (item) {
          thisFilterCondition[item.value] = conditionObj[item.type];
        });
        return thisFilterCondition;
      },
      filterFieldType:function () {
        var thisFilterFieldType = {};
        this.filterFields.forEach(function (item) {
          thisFilterFieldType[item.value] = {
            type:item.type,
            formatter:item.formatter
          };
        });
        return thisFilterFieldType;
      },
      emitCustomerFilter:function () {//最终提交的过滤器条件
        var thisEmitValue = [];
        var thisFilterFieldType = this.filterFieldType;
        this.customerFilter.forEach(function (item) {
          var paramType = '';
          var paramFormat = '';
          var paramFilterValue = '';
          if(thisFilterFieldType[item.filterField]){
            paramType = thisFilterFieldType[item.filterField].type;
            paramFormat = thisFilterFieldType[item.filterField].formatter;
            if(paramType == 'date'){
              paramFilterValue = item.dateFilterValue.time;
            }else{
              paramFilterValue = item.filterValue;
            }
          }
          thisEmitValue.push({
            index:item.index,
            filterField:item.filterField,
            filterCondition:item.filterCondition,
            filterValue:paramFilterValue,
            dateFilterValue:item.dateFilterValue,
            filterFieldType:paramType,
            filterFieldFormat:paramFormat
          });
        });
        return thisEmitValue;
      }
    },
    components:{
      'date-picker': myDatepicker
    }
  }
</script>

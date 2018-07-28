<template>
  <div>
    <!--弹出新增数据框-->
    <v-layout row justify-center>
      <v-dialog v-model="create_dialog" max-width="800px">
        <v-card>
          <v-card-title>
            <span class="headline">新增定时任务</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 md12>
                  <v-text-field v-validate="'required'" data-vv-name="Job自定义名称" data-vv-scope="schedule_create"
                                :error-messages="vee_errors.collect('Job自定义名称','schedule_create')[0]||[]"
                                label="Job自定义名称" required v-model="newSchedule.name"></v-text-field>
                </v-flex>
                <v-flex xs12 md6>
                  <async-select
                    label="Job类名"
                    v-model="newSchedule.jobClass"
                    v-validate="'required'"
                    :error-messages="vee_errors.collect('Job类名','schedule_create')[0]||[]"
                    data-vv-name="Job类名"
                    data-vv-scope="schedule_create"
                    :v-data-params="{type : 'job_class_name'}"
                  ></async-select>
                </v-flex>
                <v-flex xs12 md6>
                  <async-select
                    label="重复性"
                    v-model="newSchedule.recurrence"
                    v-validate="'required'"
                    :error-messages="vee_errors.collect('重复性','schedule_create')[0]||[]"
                    data-vv-name="重复性"
                    data-vv-scope="schedule_create"
                    :v-data-params="{type : 'job_recurrence'}"
                  ></async-select>
                </v-flex>
                <v-flex xs12 md6>
                  <v-text-field label="Job描述" v-model="newSchedule.descr"></v-text-field>
                </v-flex>
                <v-flex xs12 md6>
                  <v-text-field label="参数" v-model="newSchedule.argument"></v-text-field>
                </v-flex>
                <v-flex xs12 md6>
                  <div>
                    <date-picker :option="dateStartPickerOption" :limit="limit" v-model="newSchedule.startTime"></date-picker>
                  </div>
                </v-flex>
                <v-flex xs12 md6>
                  <div>
                    <date-picker :option="dateEndPickerOption" :limit="limit" v-model="newSchedule.endTime"></date-picker>
                  </div>
                </v-flex>
                <v-flex xs12 md12>
                  <div v-if="recurrence_flag_advanced">
                    <v-layout wrap>
                      <v-flex xs12 md3>
                        <v-text-field label="秒" required v-model="newSchedule.second"></v-text-field>
                      </v-flex>
                      <v-flex xs12 md3>
                        <v-text-field label="分" required v-model="newSchedule.minute"></v-text-field>
                      </v-flex>
                      <v-flex xs12 md3>
                        <v-text-field label="时" required v-model="newSchedule.hour"></v-text-field>
                      </v-flex>
                      <v-flex xs12 md3>
                        <v-text-field label="Day Of Month" required v-model="newSchedule.dom"></v-text-field>
                      </v-flex>
                      <v-flex xs12 md3>
                        <v-text-field label="月" required v-model="newSchedule.month"></v-text-field>
                      </v-flex>
                      <v-flex xs12 md3>
                        <v-text-field label="Day Of Week" required v-model="newSchedule.dow"></v-text-field>
                      </v-flex>
                      <v-flex xs12 md3>
                        <v-text-field label="年" v-model="newSchedule.year"></v-text-field>
                      </v-flex>
                    </v-layout>
                  </div>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" @click.native="createSchedule()" :loading="createScheduleLoading">
              <v-icon>done</v-icon>
              新增
            </v-btn>
            <v-btn color="error" @click.native="create_dialog = false">
              <v-icon>cancel</v-icon>
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>

    <!--弹出修改数据框-->
    <v-layout row justify-center>
      <v-dialog v-model="update_dialog" max-width="800px">
        <v-card>
          <v-card-title>
            <span class="headline">编辑定时任务</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 md12>
                  <v-text-field label="Job自定义名称" required v-model="newSchedule.name"></v-text-field>
                </v-flex>
                <v-flex xs12 md6>
                  <async-select
                    label="Job类名"
                    v-model="newSchedule.jobClass"
                    v-validate="'required'"
                    :error-messages="vee_errors.collect('Job类名','schedule_update')[0]||[]"
                    data-vv-name="Job类名"
                    data-vv-scope="schedule_update"
                    :v-data-params="{type : 'job_class_name'}"
                  ></async-select>
                </v-flex>
                <v-flex xs12 md6>
                  <async-select
                    label="重复性"
                    v-model="newSchedule.recurrence"
                    v-validate="'required'"
                    :error-messages="vee_errors.collect('重复性','schedule_update')[0]||[]"
                    data-vv-name="重复性"
                    data-vv-scope="schedule_update"
                    :v-data-params="{type : 'job_recurrence'}"
                  ></async-select>
                </v-flex>
                <v-flex xs12 md6>
                  <v-text-field label="Job描述" v-model="newSchedule.descr"></v-text-field>
                </v-flex>
                <v-flex xs12 md6>
                  <v-text-field label="参数" v-model="newSchedule.argument"></v-text-field>
                </v-flex>
                <v-flex xs12 md6>
                  <div>
                    <date-picker v-model="newSchedule.startTime" :option="dateStartPickerOption" :limit="limit"></date-picker>
                  </div>
                </v-flex>
                <v-flex xs12 md6>
                  <div>
                    <date-picker v-model="newSchedule.endTime" :option="dateEndPickerOption" :limit="limit"></date-picker>
                  </div>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="秒" required v-model="newSchedule.second"></v-text-field>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="分" required v-model="newSchedule.minute"></v-text-field>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="时" required v-model="newSchedule.hour"></v-text-field>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="Day Of Month" required v-model="newSchedule.dom"></v-text-field>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="月" required v-model="newSchedule.month"></v-text-field>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="Day Of Week" required v-model="newSchedule.dow"></v-text-field>
                </v-flex>
                <v-flex xs12 md3>
                  <v-text-field label="年" v-model="newSchedule.year"></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" @click.native="updateSchedule()" :loading="updateScheduleLoading">
              <v-icon>done</v-icon>
              保存
            </v-btn>
            <v-btn color="error" @click.native="update_dialog = false">
              <v-icon>cancel</v-icon>
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>

    <!--弹出删除确认框-->
    <v-layout row justify-center style="position: relative;">
      <v-dialog v-model="delete_dialog" lazy absolute max-width="500px">
        <v-card>
          <v-card-title>
            <div class="headline">确定删除这些任务吗？</div>
          </v-card-title>
          <v-card-text>删除后不可恢复。</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="success" @click.native="deleteSchedule()" :loading="deleteScheduleLoading">
              <v-icon>done</v-icon>
              删除
            </v-btn>
            <v-btn color="error" @click.native="delete_dialog = false">
              <v-icon>cancel</v-icon>
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>

    <v-card>
      <!--顶部工具栏-->
      <v-toolbar class="cyan" dark>
        <v-toolbar-title>定时任务</v-toolbar-title>
      </v-toolbar>
      <v-card-title>
        <v-spacer></v-spacer>
        <v-text-field
          append-icon="search"
          label="输入关键字搜索"
          single-line
          hide-details
          v-model="search"
        ></v-text-field>
        <v-btn icon v-on:click="startSchedule()" :disabled="startDisabled" title="开始任务" :loading="startScheduleLoading" >
          <i class="material-icons">play_arrow</i>
        </v-btn>
        <v-btn icon v-on:click="stopSchedule()" :disabled="stopDisabled" title="暂停任务" :loading="pauseScheduleLoading" >
          <i class="material-icons">pause</i>
        </v-btn>
        <v-btn icon v-on:click="showCreate()" :disabled="createDisabled" title="新增任务" >
          <v-icon>add</v-icon>
        </v-btn>
        <v-btn icon v-on:click="showUpdate()" :disabled="updateDisabled" title="编辑任务" >
          <v-icon>edit</v-icon>
        </v-btn>
        <v-btn icon v-on:click="showDelete()" :disabled="deleteDisabled" title="删除任务" >
          <v-icon>delete</v-icon>
        </v-btn>
      </v-card-title>

      <!--数据表-->
      <v-data-table
        v-model="selected"
        v-bind:headers="headers"
        v-bind:items="items"
        v-bind:search="search"
        :loading="loading"
        select-all
        :pagination.sync="dataTableConfig.dataTable_pagination"
        :no-data-text="dataTableConfig.dataTable_noDataText"
        :no-result-tex="dataTableConfig.dataTable_noResultsText"
        :rows-per-page-text="dataTableConfig.dataTable_rowsPerPageText"
        selected-key="id"
        class="elevation-1"
      >
        <template slot="headers" slot-scope="props">
          <tr>
            <th>
              <v-checkbox
                primary
                hide-details
                @click.native="toggleAll"
                :input-value="props.all"
                :indeterminate="props.indeterminate"
              ></v-checkbox>
            </th>
            <th v-for="header in props.headers" :key="header.text"
                :class="['column sortable', dataTableConfig.dataTable_pagination.descending ? 'desc' : 'asc', header.value === dataTableConfig.dataTable_pagination.sortBy ? 'active' : '']"
                @click="changeSort(header.value)"
            >
              <v-icon>arrow_upward</v-icon>
              {{ header.text }}
            </th>
          </tr>
        </template>
        <template slot="items" slot-scope="props">
          <tr :active="props.selected" @click="props.selected = !props.selected">
            <td class="text-xs-center">
              <v-checkbox
                primary
                hide-details
                :input-value="props.selected"
              ></v-checkbox>
            </td>
            <td class="text-xs-center">{{ props.item.jobClass }}</td>
            <td class="text-xs-center">{{ props.item.name }}</td>
            <td class="text-xs-center">{{ props.item.descr }}</td>
            <td class="text-xs-center">
              <div
                :style="{fontSize:props.item.statePoint.fontSize,color:props.item.statePoint.color,display:'inline'}">●
              </div>
              <div style="display:inline"> {{ props.item.state }}</div>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
  import {axios} from '@/assets/js/Utils';
  import myDatepicker from '@/components/common/vuetify-datepicker-es6';
  import {getDatePickerOptions} from '@/assets/js/Utils';
  import asyncSelect from '@/components/common/asyncSelect';
  import {Validator} from 'vee-validate';

  var newEmptySchedule = {
    jobClass: '',
    name: '',
    descr: '',
    argument: '',
    recurrence: '',
    startTime: '',
    endTime: '',
    second: '',
    minute: '',
    hour: '',
    dom: '',
    month: '',
    dow: '',
    year: ''
  };

  export default {
    mounted() {
      //初次加载完页面后，发请求查询数据列表并展示
      this.get_all_schedules();
    },
    data() {
      return {
        loading: true,//loading框
        selected: [],
        headers: [
          {
            text: 'job类名（含包路径）',
            align: 'left',
            sortable: false,
            value: 'jobClass'
          },
          {text: '名称', value: 'name'},
          {text: '描述', value: 'descr'},
          {text: '运行状态', value: 'state'}
        ],
        dataTableConfig: this.getCommonComponentConfig().dataTableConfig,
        //sysRoleHash: this.getSysRoleHash(),
        items: [],
        newSchedule: _.cloneDeep(newEmptySchedule),//创建一个新的，空的任务对象
        create_dialog: false,//新增窗口显示控制开关
        update_dialog: false,//更新窗口显示控制开关
        delete_dialog: false,//删除窗口显示控制开关
        recurrence_flag_advanced: false,//cron表达式模式相关控件显示控制开关
        startDisabled: true,//开始按钮可用状态（值相反）
        stopDisabled: true,//停止按钮可用状态（值相反）
        createDisabled: false,//新增按钮可用状态（值相反）
        updateDisabled: true,//编辑按钮可用状态（值相反）
        deleteDisabled: true,//删除按钮可用状态（值相反）
        search: '',
        //时间选择器属性
        dateStartPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '开始执行时间'
        }),
        dateEndPickerOption: getDatePickerOptions({
          type: 'min',
          placeholder: '结束执行时间'
        }),
        limit: [
          {
            type: 'fromto',
            from: '2016-12-31',
            to: '2020-01-01'
          }],
        createScheduleLoading:false,
        updateScheduleLoading:false,
        deleteScheduleLoading:false,
        startScheduleLoading:false,
        pauseScheduleLoading:false

      }
    },
    components: {
      'date-picker': myDatepicker,
      "async-select": asyncSelect
    },
    methods: {
      toggleAll() {
        if (this.selected.length) this.selected = []
        else this.selected = this.items.slice()
      },
      changeSort(column) {
        if (this.dataTableConfig.dataTable_pagination.sortBy === column) {
          this.dataTableConfig.dataTable_pagination.descending = !this.dataTableConfig.dataTable_pagination.descending
        } else {
          this.dataTableConfig.dataTable_pagination.sortBy = column
          this.dataTableConfig.dataTable_pagination.descending = false
        }
      },
      get_all_schedules() {
        this.selected = [];
        this.loading = true;
        axios().get('/schedule/')
          .then((response)=> {
            this.loading = false;
            this.items = response.data.content;
            this.items.forEach((item)=> {
              this.$set(item, 'statePoint', {
                fontSize: '24px',
                color: 'green'
              })
              if (item.state == 'NORMAL') {//正常运行
                item.state = '正常执行';
                item.statePoint.color = 'green';
              } else if (item.state == 'NONE') {
                item.state = '未执行';
                item.statePoint.color = 'grey';
              } else if (item.state == 'PAUSED') {
                item.state = '暂停中';
                item.statePoint.color = 'yellow';
              } else if (item.state == 'COMPLETE') {
                item.state = '已完成';
                item.statePoint.color = 'blue';
              } else {
                item.state = '其他';
                item.statePoint.color = 'red';
              }
            });
          })
          .catch((error)=> {
            this.loading = false;
          });
      },
      showCreate() {
        this.newSchedule = _.cloneDeep(newEmptySchedule);
        this.create_dialog = true;
      },
      showUpdate() {
        if (this.selected.length != 1) {
          this.simpleInfo('请选择一个任务！');
        } else {
          axios().get('/schedule/selectOneById?id=' + this.selected[0].id)
            .then((response)=> {
              this.update_dialog = true;
              this.newSchedule = response.data.content;
            });
        }
      },
      showDelete() {
        if (this.selected.length == 0) {
          this.simpleInfo('请至少选择一个任务！');
        } else {
          this.delete_dialog = true;
        }
      },
      createSchedule() {
        this.$validator.validateAll("schedule_create").then((result) => {
          if (result) {
            this.createScheduleLoading = true;
            axios().post('/schedule/', {
              jobClass: this.newSchedule.jobClass,
              name: this.newSchedule.name,
              descr: this.newSchedule.descr,
              argument: this.newSchedule.argument,
              recurrence: this.newSchedule.recurrence,
              startTime: this.newSchedule.startTime,
              endTime: this.newSchedule.endTime,
              second: this.newSchedule.second,
              minute: this.newSchedule.minute,
              hour: this.newSchedule.hour,
              dom: this.newSchedule.dom,
              month: this.newSchedule.month,
              dow: this.newSchedule.dow,
              year: this.newSchedule.year
            })
              .then((response)=> {
                if (response.data.success) {
                  this.simpleSuccess('新增成功！')
                  this.create_dialog = false;
                  this.newSchedule = _.cloneDeep(newEmptySchedule);
                  this.get_all_schedules();
                } else {
                  this.simpleError('新增失败');
                }
                this.createScheduleLoading = false;
              })
              .catch((error)=> {
                this.createScheduleLoading = false;
              });
          }
        });
      },
      updateSchedule() {
        this.$validator.validateAll("schedule_update").then((result) => {
          if (result) {
            this.updateScheduleLoading = true;
            axios().put('/schedule/', {
              jobClass: this.newSchedule.jobClass,
              name: this.newSchedule.name,
              descr: this.newSchedule.descr,
              argument: this.newSchedule.argument,
              recurrence: this.newSchedule.recurrence,
              startTime: this.newSchedule.startTime,
              endTime: this.newSchedule.end,
              second: this.newSchedule.second,
              minute: this.newSchedule.minute,
              hour: this.newSchedule.hour,
              dom: this.newSchedule.dom,
              month: this.newSchedule.month,
              dow: this.newSchedule.dow,
              year: this.newSchedule.year,
              id: this.selected[0].id
            })
              .then((response)=> {
                if (response.data.success) {
                  this.simpleSuccess('保存成功！')
                  this.update_dialog = false;
                  this.newSchedule = _.cloneDeep(newEmptySchedule);
                  this.get_all_schedules();
                } else {
                  this.simpleError('保存失败');
                }
                this.updateScheduleLoading = false;
              })
              .catch((error)=> {
                this.updateScheduleLoading = false;
              });
          }
        });
      },
      deleteSchedule() {
        this.deleteScheduleLoading = true;
        axios().post('/schedule/batchDelete', this.selected)
          .then((response)=> {
            if (response.data.success) {
              this.simpleSuccess('删除成功！')
              this.selected = [];
              this.delete_dialog = false;
              this.get_all_schedules();
            }
            this.deleteScheduleLoading = false;
          })
          .catch((error)=> {
            this.deleteScheduleLoading = false;
          });
      },
      startSchedule() {
        this.startScheduleLoading = true;
        axios().post('/schedule/start', this.selected)
          .then((response)=> {
            if (response.data.success) {
              this.simpleSuccess('启动成功');
              this.get_all_schedules();
            } else {
              this.simpleError('启动失败');
            }
            this.startScheduleLoading = false;
          })
          .catch((error)=> {
            this.startScheduleLoading = false;
          });
      },
      stopSchedule() {
        this.pauseScheduleLoading = true;
        axios().post('/schedule/stop', this.selected)
          .then((response)=> {
            if (response.data.success) {
              this.simpleSuccess('暂停成功');
              this.get_all_schedules();
            } else {
              this.simpleError('暂停失败');
            }
            this.pauseScheduleLoading = false;
          })
          .catch((error)=> {
            this.pauseScheduleLoading = false;
          });
      }
    },
    watch: {
      selected: {
        handler: function (newVal) {//多选框内容改变监听
          var vmE = this;
          vmE.startDisabled = false;
          vmE.stopDisabled = false;
          vmE.updateDisabled = true;
          vmE.deleteDisabled = true;
          if (newVal == '') {
            vmE.stopDisabled = true;
            vmE.startDisabled = true;
          }
          if (newVal.length == 1) {
            vmE.updateDisabled = false;
          }
          if (newVal.length > 0) {
            vmE.deleteDisabled = false;
          }
          newVal.forEach(function (item) {
            if (item.state == '未执行' || item.state == '暂停中') {
              vmE.stopDisabled = true;
            }
            if (item.state == '正常执行') {
              vmE.startDisabled = true;
            }
            if (item.state == '已完成' || item.state == '异常') {
              vmE.stopDisabled = true;
              vmE.startDisabled = true;
            }
          })
        },
        deep: true
      },
      newSchedule: {
        handler: function (newVal) {//
          if (newVal.recurrence == 'ADVANCED') {
            this.recurrence_flag_advanced = true;
          } else {
            this.recurrence_flag_advanced = false;
          }
        },
        deep: true
      }
    }
  }
</script>

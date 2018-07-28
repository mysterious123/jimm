package model.schedule;

import org.apache.ibatis.type.Alias;
import model.BaseModel;
import schedule.ERecurrence;

@Alias("Schedule")
public class Schedule extends BaseModel {

    /**
     *主键
     */
    private Integer id;
    /**
     *job类名（含包路径）
     */
    private String jobClass;
    /**
     *参数
     */
    private String argument;
    /**
     *重复性 0:手动执行（立刻执行，只执行一次）;1:自动执行（在指定的开始时间执行，只执行一次）;2:带有cron表达式管理（频次不确定）
     */
    private ERecurrence recurrence = ERecurrence.MANUAL;
    /**
     *开始时间
     */
    private String startTime;
    /**
     *结束时间
     */
    private String endTime;
    /**
     *秒
     */
    private String second = "*";
    /**
     *分
     */
    private String minute = "*";
    /**
     *时
     */
    private String hour = "*";
    /**
     *dayofmonth
     */
    private String dom = "*";
    /**
     *月
     */
    private String month = "*";
    /**
     *dayofweek
     */
    private String dow = "?";
    /**
     *年
     */
    private String year;
    /**
     *名称
     */
    private String name;
    /**
     *描述
     */
    private String descr;
    /**
     *创建时间
     */
    private Long createTime = System.currentTimeMillis();
    /**
     *更新时间
     */
    private Long updateTime = System.currentTimeMillis();
    /**
     *创建用户id
     */
    private String createUser;
    /**
     *更新用户id
     */
    private String updateUser;
    /**
     * 状态
     */
    private String state;

    public void setId(Integer id){
        this.id = id;
    }
    public void setJobClass(String jobClass){
        this.jobClass = jobClass;
    }
    public void setArgument(String argument){
        this.argument = argument;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public void setSecond(String second){
        this.second = second;
    }
    public void setMinute(String minute){
        this.minute = minute;
    }
    public void setHour(String hour){
        this.hour = hour;
    }
    public void setDom(String dom){
        this.dom = dom;
    }
    public void setMonth(String month){
        this.month = month;
    }
    public void setDow(String dow){
        this.dow = dow;
    }
    public void setYear(String year){
        this.year = year;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescr(String descr){
        this.descr = descr;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }

    public Integer getId(){
        return this.id;
    }

    public String getJobClass(){
        return this.jobClass;
    }

    public String getArgument(){
        return this.argument;
    }



    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public String getSecond(){
        return this.second;
    }

    public String getMinute(){
        return this.minute;
    }

    public String getHour(){
        return this.hour;
    }

    public String getDom(){
        return this.dom;
    }

    public String getMonth(){
        return this.month;
    }

    public String getDow(){
        return this.dow;
    }

    public String getYear(){
        return this.year;
    }

    public String getName(){
        return this.name;
    }

    public String getDescr(){
        return this.descr;
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public long getUpdateTime(){
        return this.updateTime;
    }

    public String getCreateUser(){
        return this.createUser;
    }

    public String getUpdateUser(){
        return this.updateUser;
    }

    public ERecurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(ERecurrence recurrence) {
        this.recurrence = recurrence;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", jobClass='" + jobClass + '\'' +
                ", argument='" + argument + '\'' +
                ", recurrence=" + recurrence +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", second='" + second + '\'' +
                ", minute='" + minute + '\'' +
                ", hour='" + hour + '\'' +
                ", dom='" + dom + '\'' +
                ", month='" + month + '\'' +
                ", dow='" + dow + '\'' +
                ", year='" + year + '\'' +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}

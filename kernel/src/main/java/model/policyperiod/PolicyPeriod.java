package model.policyperiod;

import org.apache.ibatis.type.Alias;
import model.BaseModel;

@Alias("PolicyPeriod")
public class PolicyPeriod extends BaseModel {

    /**
     *主键
     */
    private String id;
    /**
     *时段策略名称
     */
    private String periodName;
    /**
     *周一开始
     */
    private String startperiod1;
    /**
     *周二开始
     */
    private String startperiod2;
    /**
     *周三开始
     */
    private String startperiod3;
    /**
     *周四开始
     */
    private String startperiod4;
    /**
     *周五开始
     */
    private String startperiod5;
    /**
     *周六开始
     */
    private String startperiod6;
    /**
     *周日开始
     */
    private String startperiod7;
    /**
     *周一结束
     */
    private String endperiod1;
    /**
     *周二结束
     */
    private String endperiod2;
    /**
     *周三结束
     */
    private String endperiod3;
    /**
     *周四结束
     */
    private String endperiod4;
    /**
     *周五结束
     */
    private String endperiod5;
    /**
     *周六结束
     */
    private String endperiod6;
    /**
     *周日结束
     */
    private String endperiod7;
    /**
     *是否过滤
     */
    private String period1status;
    /**
     *是否过滤
     */
    private String period2status;
    /**
     *是否过滤
     */
    private String period3status;
    /**
     *是否过滤
     */
    private String period4status;
    /**
     *是否过滤
     */
    private String period5status;
    /**
     *是否过滤
     */
    private String period6status;
    /**
     *是否过滤
     */
    private String period7status;
    /**
     *属性
     */
    private String nature;
    /**
     *创建用户
     */
    private String createUser;

    public void setId(String id){
        this.id = id;
    }
    public void setPeriodName(String periodName){
        this.periodName = periodName;
    }
    public void setStartperiod1(String startperiod1){
        this.startperiod1 = startperiod1;
    }
    public void setStartperiod2(String startperiod2){
        this.startperiod2 = startperiod2;
    }
    public void setStartperiod3(String startperiod3){
        this.startperiod3 = startperiod3;
    }
    public void setStartperiod4(String startperiod4){
        this.startperiod4 = startperiod4;
    }
    public void setStartperiod5(String startperiod5){
        this.startperiod5 = startperiod5;
    }
    public void setStartperiod6(String startperiod6){
        this.startperiod6 = startperiod6;
    }
    public void setStartperiod7(String startperiod7){
        this.startperiod7 = startperiod7;
    }
    public void setEndperiod1(String endperiod1){
        this.endperiod1 = endperiod1;
    }
    public void setEndperiod2(String endperiod2){
        this.endperiod2 = endperiod2;
    }
    public void setEndperiod3(String endperiod3){
        this.endperiod3 = endperiod3;
    }
    public void setEndperiod4(String endperiod4){
        this.endperiod4 = endperiod4;
    }
    public void setEndperiod5(String endperiod5){
        this.endperiod5 = endperiod5;
    }
    public void setEndperiod6(String endperiod6){
        this.endperiod6 = endperiod6;
    }
    public void setEndperiod7(String endperiod7){
        this.endperiod7 = endperiod7;
    }
    public void setPeriod1status(String period1status){
        this.period1status = period1status;
    }
    public void setPeriod2status(String period2status){
        this.period2status = period2status;
    }
    public void setPeriod3status(String period3status){
        this.period3status = period3status;
    }
    public void setPeriod4status(String period4status){
        this.period4status = period4status;
    }
    public void setPeriod5status(String period5status){
        this.period5status = period5status;
    }
    public void setPeriod6status(String period6status){
        this.period6status = period6status;
    }
    public void setPeriod7status(String period7status){
        this.period7status = period7status;
    }
    public void setNature(String nature){
        this.nature = nature;
    }
    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }

    public String getId(){
        return this.id;
    }

    public String getPeriodName(){
        return this.periodName;
    }

    public String getStartperiod1(){
        return this.startperiod1;
    }

    public String getStartperiod2(){
        return this.startperiod2;
    }

    public String getStartperiod3(){
        return this.startperiod3;
    }

    public String getStartperiod4(){
        return this.startperiod4;
    }

    public String getStartperiod5(){
        return this.startperiod5;
    }

    public String getStartperiod6(){
        return this.startperiod6;
    }

    public String getStartperiod7(){
        return this.startperiod7;
    }

    public String getEndperiod1(){
        return this.endperiod1;
    }

    public String getEndperiod2(){
        return this.endperiod2;
    }

    public String getEndperiod3(){
        return this.endperiod3;
    }

    public String getEndperiod4(){
        return this.endperiod4;
    }

    public String getEndperiod5(){
        return this.endperiod5;
    }

    public String getEndperiod6(){
        return this.endperiod6;
    }

    public String getEndperiod7(){
        return this.endperiod7;
    }

    public String getPeriod1status(){
        return this.period1status;
    }

    public String getPeriod2status(){
        return this.period2status;
    }

    public String getPeriod3status(){
        return this.period3status;
    }

    public String getPeriod4status(){
        return this.period4status;
    }

    public String getPeriod5status(){
        return this.period5status;
    }

    public String getPeriod6status(){
        return this.period6status;
    }

    public String getPeriod7status(){
        return this.period7status;
    }

    public String getNature(){
        return this.nature;
    }

    public String getCreateUser(){
        return this.createUser;
    }

}

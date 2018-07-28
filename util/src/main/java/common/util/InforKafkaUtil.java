package common.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Created with IDEA
 * @author:LiWangZhou
 * @Date:2018/7/23/023
 * @Time:10:10
 **/
public class InforKafkaUtil {
    public static final int TEN = 10;

    /**判断当前日期星期位置
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }
    /** json字符串转字符对象
     *
     * @param record
     * @return JsonObject
     */
    public static JsonObject dataPrecision(ConsumerRecord<Long, String> record) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        JsonParser jsonParser = new JsonParser();
        // 设置精确到小数点数位
        numberFormat.setMaximumFractionDigits(0);
        JsonElement jsonElement = jsonParser.parse(record.value().trim());
        return  jsonElement.getAsJsonObject();
    }
    /**
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String timeChange (String time) throws ParseException{
        String ts = time;
        ts = ts.replace("Z", " UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date dt = sdf.parse(ts);
        TimeZone tz = sdf.getTimeZone();
        Calendar c = sdf.getCalendar();
        String str = getString(c);
        return str.substring(str.indexOf(":")-2, str.lastIndexOf(":"));
    }
    /**时间格式转换
     *
     * @param c
     * @return
     */
    private static String getString(Calendar c)
    {
        StringBuffer result = new StringBuffer();
        result.append(c.get(Calendar.YEAR));
        result.append("-");
        if(c.get(Calendar.MONTH)<TEN){
            result.append("0"+(c.get(Calendar.MONTH) +1));
        }
        result.append("-");
        result.append(c.get(Calendar.DAY_OF_MONTH));
        result.append(" ");
        if(c.get(Calendar.HOUR_OF_DAY)<TEN){
            result.append("0"+c.get(Calendar.HOUR_OF_DAY));
        }
        result.append(c.get(Calendar.HOUR_OF_DAY));
        result.append(":");
        result.append(c.get(Calendar.MINUTE));
        result.append(":");
        result.append(c.get(Calendar.SECOND));
        return result.toString();
    }
    /** 判断监测时间区间
     *
     * @param no
     * @param time1
     * @param time2
     * @return
     */
    public static  Boolean time(String no,String time1,String time2){
        if (no.equals(null)|| time1.equals(null) || time2.equals(null)){
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now =null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(no);
            beginTime = df.parse(time1);
            endTime = df.parse(time2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean flag = belongCalendar(now, beginTime, endTime);
        return flag;

    }
    /**
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static  boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


}

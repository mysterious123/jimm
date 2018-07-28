package schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by xj on 2018/5/10.
 *
 * 心跳告警
 */
public class HeartBeatAlarmScheduleJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}

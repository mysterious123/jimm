/*
 * Copyright (C) Unpublished JiuDaoTech Software, Inc. All rights reserved.
 * JiuDaoTech Software, Inc., Confidential and Proprietary.
 *
 * This software is subject to copyright protection
 *   under the laws of the Public of China and other countries.
 *
 * Unless otherwise explicitly stated, this software is provided
 *   by JiuDaoTech "AS IS".
 */
package service.schedule.impl;


import dao.BaseMapper;
import model.schedule.Schedule;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.schedule.ScheduleService;

import javax.annotation.PreDestroy;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Daixiaoyi
 * @since 2017/8/23 16:36
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    private static final String ARGUMENT_KEY = "arg";
    private DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    BaseMapper<Schedule> baseMapper;


    @Autowired(required = false)
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @PreDestroy
    public void destroy() throws SchedulerException {
    }

    @Override
    public int createSchedule(Schedule schedule) {
        schedule.setStatmentId("model.schedule.Schedule.insert");
        return baseMapper.insert(schedule);
    }

    @Override
    public Schedule getScheduleById(Schedule schedule) {
        schedule.setStatmentId("model.schedule.Schedule.select");
        return baseMapper.selectOne(schedule);
    }

    @Override
    public Schedule getScheduleByName(Schedule schedule) {
        schedule.setStatmentId("model.schedule.Schedule.selectByName");
        return baseMapper.selectOne(schedule, "schedule");
    }

    @Override
    public int updateSchedule(Schedule schedule) {
        schedule.setStatmentId("model.schedule.Schedule.update");
        return baseMapper.update(schedule);
    }

    @Override
    public int deleteSchedule(Schedule schedule) {
        schedule.setStatmentId("model.schedule.Schedule.delete");
        return baseMapper.delete(schedule);
    }

    public Class<?> findJobClass(String name) {
        try {
            Class<?> clazz = Class.forName(name);
            if (ClassUtils.isAssignable(clazz, Job.class)) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {
        }

        return null;
    }

    @Override
    public void schedule(Schedule schedule) throws SchedulerException {
        switch (schedule.getRecurrence()) {
            case MANUAL:
                once(schedule.getName(), schedule.getJobClass(), schedule.getArgument(), null);
                break;

            case ONCE:
                Date startTime = DateTime.parse(schedule.getStartTime(), format).toDate();
                once(schedule.getName(), schedule.getJobClass(), schedule.getArgument(), startTime);
                break;

            case ADVANCED:
                cron(schedule);
                break;

            default:
                break;
        }
    }

    public void once(String name, String classname, String argument, Date startTime) throws SchedulerException {
        Trigger trigger = onceTrigger(name, startTime);
        schedule(name, classname, argument, trigger);
    }

    public void cron(String name, String classname, String argument, String expr) throws SchedulerException {
        Trigger trigger = cronTrigger(classname, expr, null, null);
        schedule(name, classname, argument, trigger);
    }

    public boolean exists(String name) throws SchedulerException {
        JobKey key = jobKey(name);
        return scheduler.checkExists(key);
    }

    @Override
    public void pause(String name) throws SchedulerException {
        JobKey key = jobKey(name);
        scheduler.pauseJob(key);
    }

    @Override
    public void resume(String name) throws SchedulerException {
        JobKey key = jobKey(name);
        scheduler.resumeJob(key);
    }

    @Override
    public void delete(String name) throws SchedulerException {
        JobKey key = jobKey(name);
        scheduler.deleteJob(key);
    }

    @Override
    public List<Schedule> getSchedules(Schedule schedule) {
        schedule.setStatmentId("model.schedule.Schedule.selectAll");
        baseMapper.selectList(schedule).forEach(item -> {
            try {
                item.setState(scheduler.getTriggerState(TriggerKey.triggerKey(item.getName())).toString());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        });
        return baseMapper.selectList(schedule);
    }

    @Override
    public void batchDelete(List<Schedule> schedules) {
        schedules.forEach(schedule -> {
            schedule.setStatmentId("model.schedule.Schedule.delete");
            baseMapper.delete(schedule);
        });
    }

    @Override
    public String cronExpression(String second, String minute, String hour, String dom, String month, String dow, String year) {
        String expr = String.format(
                "%s %s %s %s %s %s",
                second,
                minute,
                hour,
                dom,
                month,
                dow);
        if (StringUtils.isNotBlank(year)) {
            expr = expr + " " + year.trim();
        }
        return expr;
    }

    @Override
    public boolean verifyCronExpression(String expression) {
        boolean valid = CronExpression.isValidExpression(expression);
        if (valid) {
            try {
                CronExpression cronExpression = new CronExpression(expression);
                valid = cronExpression.getNextValidTimeAfter(new Date()) != null;
            } catch (ParseException e) {
                logger.error("", e);
                valid = false;
            }
        }
        return valid;
    }

    /**
     * 先构造一个通过cron表达式管理的trigger触发器，再执行schedule
     *
     * @param schedule 任务实体类
     * @throws SchedulerException
     */
    private void cron(Schedule schedule) throws SchedulerException {
        String name = schedule.getName();
        String expr = cronExpression(
                schedule.getSecond(),
                schedule.getMinute(),
                schedule.getHour(),
                schedule.getDom(),
                schedule.getMonth(),
                schedule.getDow(),
                schedule.getYear());
        Trigger trigger = cronTrigger(name, expr, StringUtils.isEmpty(schedule.getStartTime()) ? null : DateTime.parse(schedule.getStartTime(), format).toDate(), StringUtils.isEmpty(schedule.getEndTime()) ? null : DateTime.parse(schedule.getEndTime(), format).toDate());
        schedule(name, schedule.getJobClass(), schedule.getArgument(), trigger);
    }

    /**
     * 执行schedule
     *
     * @param name      任务名称
     * @param classname 任务对应job类名
     * @param argument  任务运行参数
     * @param trigger   触发器
     * @throws SchedulerException
     */
    private void schedule(String name, String classname, String argument, Trigger trigger) throws SchedulerException {
        Class<?> cls = findJobClass(classname);
        if (cls == null) {
            throw new SchedulerException("Job not found: " + classname);
        }

        JobDataMap map = new JobDataMap();
        @SuppressWarnings("unchecked")
        Class<? extends Job> jobClass = (Class<? extends Job>) cls;
        JobBuilder builder = newJob(jobClass);

        map.put(ARGUMENT_KEY, argument);
        builder.withIdentity(name).usingJobData(map);
        logger.info("载入schedule：" + name);
        scheduler.scheduleJob(builder.build(), trigger);
    }

    /**
     * 构造一个只触发一次的trigger触发器，如果开始时间未指定，则默认为立刻执行
     *
     * @param name      任务名称
     * @param startTime 开始时间
     * @return
     */
    private Trigger onceTrigger(String name, Date startTime) {
        TriggerBuilder<Trigger> builder = newTrigger();
        builder.withIdentity(name);
        if (startTime != null) {
            builder.startAt(startTime);
        }
        return builder.build();
    }

    /**
     * 构造一个通过cron表达式管理的trigger触发器，也可以设置开始和结束时间
     *
     * @param name      任务名称
     * @param expr      cron表达式字符串
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public Trigger cronTrigger(String name, String expr, Date startTime, Date endTime) {
        TriggerBuilder<Trigger> builder = newTrigger();

        builder.withIdentity(name).withSchedule(cronSchedule(expr));
        if (startTime != null) {
            builder.startAt(startTime);
        }
        if (endTime != null) {
            builder.endAt(endTime);
        }

        return builder.build();
    }
}

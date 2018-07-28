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
package service.schedule;

import model.schedule.Schedule;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author Daixiaoyi
 * @since 2017/8/23 16:36
 */
public interface ScheduleService {
    /**
     * 创建任务
     *
     * @param schedule
     * @return
     */
    public int createSchedule(Schedule schedule);

    /**
     * 根据id查询任务
     *
     * @param schedule
     * @return
     */
    public Schedule getScheduleById(Schedule schedule);

    /**
     * 根据名称查询任务
     *
     * @param schedule
     * @return
     */
    public Schedule getScheduleByName(Schedule schedule);

    /**
     * 更新任务
     *
     * @param schedule
     * @return
     */
    public int updateSchedule(Schedule schedule);

    /**
     * 删除任务
     *
     * @param schedule
     * @return
     */
    public int deleteSchedule(Schedule schedule);

    /**
     * 查询所有任务
     *
     * @param schedule
     * @return
     */
    public List<Schedule> getSchedules(Schedule schedule);

    /**
     * 批量删除任务
     *
     * @param schedules
     */
    public void batchDelete(List<Schedule> schedules);

    /**
     * 执行任务
     *
     * @param schedule
     * @throws SchedulerException
     */
    public void schedule(Schedule schedule) throws SchedulerException;

    /**
     * 暂停任务
     *
     * @param name
     * @throws SchedulerException
     */
    public void pause(String name) throws SchedulerException;

    /**
     * 重启任务
     *
     * @param name
     * @throws SchedulerException
     */
    public void resume(String name) throws SchedulerException;

    /**
     * 删除任务
     *
     * @param name
     * @throws SchedulerException
     */
    public void delete(String name) throws SchedulerException;

    /**
     * 将参数拼装为cron表达式
     *
     * @param second
     * @param minute
     * @param hour
     * @param dom
     * @param month
     * @param dow
     * @param year
     * @return
     */
    public String cronExpression(String second, String minute, String hour, String dom, String month, String dow, String year);

    /**
     * 验证cron表达式是否合法
     *
     * @param expression
     * @return
     */
    public boolean verifyCronExpression(String expression);

}

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
package schedule;

import org.quartz.*;
import org.quartz.simpl.PropertySettingJobFactory;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


/**
 * <p>
 * spring扩展：在创建job时，优先从springBean管理中获取job
 * </p>
 *
 */
public class SpringBeanJobFactory extends PropertySettingJobFactory implements JobFactory {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<?> jobClass = jobDetail.getJobClass();
        Job job = newJob(jobClass);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(scheduler.getContext());
        jobDataMap.putAll(bundle.getJobDetail().getJobDataMap());
        jobDataMap.putAll(bundle.getTrigger().getJobDataMap());
        setBeanProps(job, jobDataMap);
        return job;
    }

    private Job newJob(Class<?> cls) throws SchedulerException {
        try {
            return (Job) ctx.getBean(cls);
        } catch (NoSuchBeanDefinitionException e) {
            try {
                return (Job) cls.newInstance();
            } catch (Exception ex) {
                throw new SchedulerException(ex);
            }
        }
    }

}

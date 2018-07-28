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
package web.schedule;


import model.ResponseDTO;
import model.schedule.Schedule;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.ERecurrence;
import secure.annotation.ApiMetadata;
import service.schedule.ScheduleService;

import java.util.Date;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Daixiaoyi
 * @since 2017/8/23 16:36
 */
@RequestMapping("/schedule")
@Controller
@ApiMetadata(desc = "定时任务管理", permission = "schedule")
public class ScheduleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScheduleService scheduleService;

    private ResponseDTO responseDTO;

    @ResponseBody
    @GetMapping
    @ApiMetadata(desc = "查询", permission = "selectList")
    public Object list() throws Exception {
        responseDTO = new ResponseDTO();
        Schedule schedule = new Schedule();
        schedule.setPaging(false);
        List<Schedule> scheduleList = scheduleService.getSchedules(schedule);
        responseDTO.setSuccess(true);
        responseDTO.setContent(scheduleList);
        return responseDTO;
    }

    @ResponseBody
    @PostMapping
    @ApiMetadata(desc = "新增", permission = "insert")
    public Object create(@RequestBody Schedule schedule) {
        responseDTO = new ResponseDTO();
        try {
            if (validate(schedule, false)) {
                schedule.setCreateTime(System.currentTimeMillis());
                schedule.setUpdateTime(System.currentTimeMillis());
                schedule.setCreateUser("1");
                schedule.setUpdateUser("1");
                scheduleService.createSchedule(schedule);
                if (schedule.getRecurrence() != ERecurrence.MANUAL) {
                    scheduleService.schedule(scheduleService.getScheduleById(schedule));
                }
                responseDTO.setSuccess(true);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseDTO;
    }

    @ResponseBody
    @PutMapping
    @ApiMetadata(desc = "更新", permission = "update")
    public Object update(@RequestBody Schedule schedule) {
        responseDTO = new ResponseDTO();
        try {
            Schedule oldSchedule = scheduleService.getScheduleById(schedule);
            if (schedule != null) {
                boolean skipValidateName = false;
                if (oldSchedule.getName().equals(schedule.getName())) {
                    skipValidateName = true;
                }
                if (validate(schedule, skipValidateName)) {
                    SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
                    schedule.setUpdateTime(System.currentTimeMillis());
                    schedule.setUpdateUser("1");
                    scheduleService.delete(schedule.getName());
                    scheduleService.updateSchedule(schedule);
                    if (schedule.getRecurrence() != ERecurrence.MANUAL) {
                        scheduleService.schedule(scheduleService.getScheduleById(schedule));
                    }
                    responseDTO.setSuccess(true);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseDTO;
    }

    @ResponseBody
    @DeleteMapping
    @ApiMetadata(desc = "删除", permission = "delete")
    public Object delete(@RequestParam String id) {
        responseDTO = new ResponseDTO();
        Schedule schedule = new Schedule();
        schedule.setId(Integer.valueOf(id));
        try {
            schedule = scheduleService.getScheduleById(schedule);
            if (schedule != null) {
                scheduleService.deleteSchedule(schedule);
                scheduleService.delete(schedule.getName());
                responseDTO.setSuccess(true);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseDTO;
    }

    @ResponseBody
    @PostMapping("batchDelete")
//    @ApiInfo(desc="批量删除")
    public Object batchDelete(@RequestBody List<Schedule> schedules) {
        responseDTO = new ResponseDTO();
        scheduleService.batchDelete(schedules);
        responseDTO.setSuccess(true);
        return responseDTO;
    }

    @ResponseBody
    @PostMapping("start")
    @ApiMetadata(desc = "启动", permission = "start")
    public Object start(@RequestBody List<Schedule> schedules) {
        responseDTO = new ResponseDTO();
        schedules.forEach(schedule -> {
            schedule = scheduleService.getScheduleById(schedule);
            if (schedule != null) {
                try {
                    if (schedule.getRecurrence() == ERecurrence.MANUAL) {
                        scheduleService.schedule(schedule);
                    } else {
                        scheduleService.resume(schedule.getName());
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
        responseDTO.setSuccess(true);
        return responseDTO;
    }

    @ResponseBody
    @PostMapping("stop")
    @ApiMetadata(desc = "暂停", permission = "stop")
    public Object stop(@RequestBody List<Schedule> schedules) {
        responseDTO = new ResponseDTO();
        schedules.forEach(schedule -> {
            schedule = scheduleService.getScheduleById(schedule);
            if (schedule != null) {
                try {
                    scheduleService.pause(schedule.getName());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
        responseDTO.setSuccess(true);
        return responseDTO;
    }

    @ResponseBody
    @GetMapping("selectOneById")
    public Object selectOne(Schedule schedule) {
        responseDTO = new ResponseDTO();
        schedule = scheduleService.getScheduleById(schedule);
        responseDTO.setSuccess(true);
        responseDTO.setContent(schedule);
        return responseDTO;
    }

    @ResponseBody
    @GetMapping("getNextFireTime")
//    @ApiInfo(desc="查询下次执行时间")
    public Object getNextFireTime(String cron){
        String nextFireTime = "";
        if(!scheduleService.verifyCronExpression(cron)){
            nextFireTime = "表达式校验失败";
        }else{
            Trigger trigger = newTrigger().withSchedule(cronSchedule(cron)).build();
            nextFireTime = new DateTime(trigger.getFireTimeAfter(new Date())).toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
            logger.info("根据cron表达式："+cron+" 查询下次执行时间:" + nextFireTime);
        }
        return nextFireTime;
    }

    private boolean validate(Schedule schedule, Boolean skipValidateName) {
        boolean valid = true;

        if (!skipValidateName && scheduleService.getScheduleByName(schedule) != null) {
            valid = false;
            responseDTO.setMessage("名称重复");
            return valid;
        }

        ERecurrence recurrence = schedule.getRecurrence();
        if (recurrence == null) {
            recurrence = ERecurrence.MANUAL;
            schedule.setRecurrence(schedule.getRecurrence());
        }
        if (recurrence == ERecurrence.MANUAL || recurrence == ERecurrence.ONCE) {
            schedule.setSecond("*");
            schedule.setMinute("*");
            schedule.setHour("*");
            schedule.setDom("*");
            schedule.setMonth("*");
            schedule.setDow("?");
            schedule.setYear(null);
        }
        if (recurrence != ERecurrence.MANUAL && recurrence != ERecurrence.ONCE) {
            String expr = scheduleService.cronExpression(schedule.getSecond(),
                    schedule.getMinute(), schedule.getHour(), schedule.getDom(),
                    schedule.getMonth(), schedule.getDow(), schedule.getYear());
            if (!scheduleService.verifyCronExpression(expr)) {
                if (recurrence == ERecurrence.ADVANCED) {
                    responseDTO.setMessage("Cron表达式填写错误");
                } else {
                    responseDTO.setMessage("周期填写错误");
                }
                valid = false;
            }

        }
        return valid;
    }
}

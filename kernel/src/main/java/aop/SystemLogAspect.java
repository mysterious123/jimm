package aop;

import model.superlogmanagement.SuperLogManagement;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import service.superlogmanagement.SuperLogManagementService;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 日志管理
 *
 * Created by xj on 2017/12/14.
 */
@Aspect
@Component
public class SystemLogAspect {
    @Resource
    SuperLogManagementService superLogManagementService;
    @Pointcut("execution (* web.*.*.*(..))")
     public  void controllerAspect() {

    }
    @After("controllerAspect()")
    public  void after(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        long datetime = DateTime.now().getMillis();
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        SuperLogManagement log = new SuperLogManagement();
        log.setOperateContent(targetName + ":" + methodName);
        log.setCreateTime(datetime);
        log.setOperateResult("0");
        log.setUserName(user.getUserName());
        superLogManagementService.insert(log);

    }
    /** 记录异常日志
     *
     * @param joinPoint
     * @param e
     *
     **/
    @AfterThrowing(pointcut = "controllerAspect()", throwing="e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        long datetime = DateTime.now().getMillis();
        SuperUser user =  (SuperUser) SecurityUtils.getSubject().getPrincipal();
        SuperLogManagement log = new SuperLogManagement();
        log.setOperateContent(targetName + ":" + methodName);
        log.setCreateTime(datetime);
        log.setOperateResult("1");
        log.setUserName(user.getUserName());
        superLogManagementService.insert(log);
    }
}

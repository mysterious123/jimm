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
package aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 应用加载器监听(做一些缓存设置等)
 *
 * @author CSJ
 */
public class ApplicationBootstrapListener implements ApplicationListener<ApplicationContextEvent> {

    private static final Logger L = LoggerFactory.getLogger(ApplicationBootstrapListener.class);

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        /*
         * <pre>
         * 在web 项目中（spring MVC），系统会存在两个容器，
         *  一个是root application context
         *  另一个是projectName-servlet contextØ
         * 就会造成onApplicationEvent方法被执行两次
         * </pre>
         */
        if (event.getApplicationContext().getParent() == null) {
            // 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        }

    }

}

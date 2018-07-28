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
package web;

import common.util.CryptoUtil;
import model.superuser.SuperUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import secure.authc.AccountLoginToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yx
 * Date: 2017/11/18
 * Time: 15:21
 * <p>
 * Description:main enter point
 */
@Controller
public class DefaultController {

    private static final Logger L = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/login")
    @ResponseBody
    public Object Login(@RequestBody SuperUser user) {
        Map<String, Object> resMap = new HashMap<>(16);
        AccountLoginToken token = new AccountLoginToken(user);
        try {
            SecurityUtils.getSubject().login(token);
            user = (SuperUser) SecurityUtils.getSubject().getPrincipal();
            resMap.put("user", user);
            resMap.put("success", true);
        } catch (AuthenticationException e) {
            resMap.put("success", false);
            L.error(e.getMessage());
        }
        return resMap;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout() throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null && currentUser.getSession() != null) {
            L.info("退出登陆成功");
            currentUser.logout();
        }
    }
}

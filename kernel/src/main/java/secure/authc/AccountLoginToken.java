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

/************************************************************************
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ************************************************************************/
package secure.authc;

import model.superuser.SuperUser;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * <p>
 * 扩展具有Account对象 Token
 *
 * @author CSJ
 */
public class AccountLoginToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 1L;
    private SuperUser user;

    public AccountLoginToken(String username, char[] password, boolean rememberMe, String host, SuperUser account) {
        super(username, password, rememberMe, host);
        this.user = account;
    }

    public AccountLoginToken(String username, String password, SuperUser account) {
        super(username, password);
        this.user = account;
    }

    public AccountLoginToken(SuperUser user) {
        super(user.getUserName(), user.getPassword());
        this.user = user;
    }

    public SuperUser getUser() {
        return user;
    }

    public void setUser(SuperUser user) {
        this.user = user;
    }

}

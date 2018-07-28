/*
 * Copyright (C) Unpublished JiuDaoTech Software, Inc. All rights reserved.
 * JiuDaoTech Software, Inc., Confidential and Proprietary.
 *
 * This software is subject to copyright protection
 *   under the laws of the Public of China and other countries.
 *
 * Unless otherwise explicitly stated, this software is provided
 *   by JiuDaoTech "AS IS".
 *
 */

package schedule;

/**
 * <p>
 * 执行频次枚举，schedule相关业务
 * </p>
 *
 */
public enum ERecurrence {
    //手动执行（立刻执行，只执行一次），自动执行（在指定的开始时间执行，只执行一次），带有cron表达式管理（频次不确定）
    MANUAL(0), ONCE(1), ADVANCED(2);

    private int recurrence;

    private ERecurrence(int i) {
        this.recurrence = i;
    }

    public int getRecurrence() {
        return recurrence;
    }
}

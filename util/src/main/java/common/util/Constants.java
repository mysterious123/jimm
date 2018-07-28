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

package common.util;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gzy
 * @since 2017/10/16 16:20
 */
public class Constants {

    public static final Map<Object, String> TYPES;

    static {
        TYPES = new HashMap<>();
        TYPES.put(null, "keyword");
        TYPES.put("string", "keyword");
        TYPES.put("ip", "keyword");
        TYPES.put("number", "long");
        TYPES.put("date", "date");
    }

    public static String genIndexName(){
        return DateTime.now().toString("yyyyMMdd");
    }
}

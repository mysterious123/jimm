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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * \* User: gzy
 * \* Date: 2017/8/3
 * \* Time: 15:30
 * \* Description:正则匹配工具
 * \
 */
public class RegexUtil implements Serializable {

    // Extract fileds from named groups regular expressions (get group names)
    public static List<String> extractKeysFromNamedGroupsRegex(String regex) throws Exception {
        List<String> result = new ArrayList<>();
        List<String> conflictKeys = new ArrayList<>();
        String groupDetection = "\\(\\?<([^>]+)>[^)]+\\)";
        Pattern pattern = Pattern.compile(groupDetection);
        Matcher matcher = pattern.matcher(regex);
        while (matcher.find()) {
            if (matcher.groupCount() == 1) {
                result.add(matcher.group(1));
            }
        }
        if (result.isEmpty()) {
            String message = "Failed to extract fields from regex: " + regex;
            throw new Exception(message);
        }
        return result;
    }

    // Get named groups to map
    public static Map<String, String> findNamedGroups(String input, String regex) throws Exception {
        List<String> namedGroupNames = extractKeysFromNamedGroupsRegex(regex);
        Map<String, String> map = new HashMap<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            for (String namedGroupName : namedGroupNames) {
                String result = matcher.group(namedGroupName);
                map.put(namedGroupName, result);
            }
        }
        return map;
    }

    public static Map<String, String> findNamedGroups(String input, List<String> regexes) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        for (String regex : regexes) {
            map.putAll(findNamedGroups(input, regex));
        }
        return map;
    }
}

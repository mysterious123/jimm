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

package validate.validator;

import common.util.RegexUtil;
import org.apache.commons.lang3.ArrayUtils;
import validate.annotation.Contain;
import validate.annotation.RegexRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author yx
 * @since 2017/9/13 下午3:41
 */
public class RegexRuleValidator implements ConstraintValidator<RegexRule, String> {
    private int namedGroup = 0;

    @Override
    public void initialize(RegexRule constraintAnnotation) {
        namedGroup = constraintAnnotation.namedGroup();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            List<String> namedGroups = RegexUtil.extractKeysFromNamedGroupsRegex(value);
            if (namedGroups.size() != namedGroup) {
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

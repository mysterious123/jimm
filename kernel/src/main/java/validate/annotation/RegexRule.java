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

package validate.annotation;

import validate.validator.RegexRuleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yx
 * @since 2017/9/25 下午3:50
 * 用于校验正则是否符合规则
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = RegexRuleValidator.class)
@Documented
public @interface RegexRule {
    String message() default "{regexRule}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int namedGroup () default 1;
}

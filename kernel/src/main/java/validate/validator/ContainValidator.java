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

import org.apache.commons.lang3.ArrayUtils;
import validate.annotation.Contain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yx
 * @since 2017/9/13 下午3:41
 */
public class ContainValidator implements ConstraintValidator<Contain, String> {
    String[] range;
    @Override
    public void initialize(Contain constraintAnnotation) {
        range = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }else if(ArrayUtils.contains(range,value)){
            return true;
        }else {
            return false;
        }
    }
}

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

package dao;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * Common DAO interface for MyBatis
 *
 * @author CSJ
 */
public interface BaseMapper<T> {
    /**
     * insert a new record into DB
     *
     * @param entity 实体模型
     */
    int insert(T entity);

    /**
     * delete a record form DB
     *
     * @param entity 实体模型
     */
    int delete(T entity);

    /**
     * update a record form DB
     *
     * @param entity 实体模型
     */
    int update(T entity);

    /**
     * query a record form DB
     *
     * @param entity 实体模型
     */
    T selectOne(T entity);


    /**
     * query a record form DB with different retrun value
     *
     * @param entity    实体模型
     * @param outEntity 返回实体类型
     */
    <O> O selectOne(T entity, Class<O> outEntity);

    /**
     * query count number form DB
     *
     * @param entity 实体模型
     */
    int count(T entity);

    /**
     * query a record to Map
     *
     * @param entity 实体模型
     * @param mapKey 映射KEY
     */
    Map<String, Object> selectMap(T entity, String mapKey);

    /**
     * query records form DB
     *
     * @param entity 实体模型
     */
    List<T> selectList(T entity);

    /**
     * query records form DB with different retrun value
     *
     * @param entity    实体模型
     * @param outEntity 返回实体类型
     */
    <O> List<O> selectList(T entity, Class<O> outEntity);

    /**
     * insert a new record into DB
     *
     * @param entity 实体模型
     */
    int insert(T entity, Object extraParam);

    /**
     * delete a record form DB
     *
     * @param entity 实体模型
     */
    int delete(T entity, Object extraParam);

    /**
     * update a record form DB
     *
     * @param entity 实体模型
     */
    int update(T entity, Object extraParam);

    /**
     * query a record form DB
     *
     * @param entity     实体模型
     * @param extraParam 额外参数
     */
    T selectOne(T entity, Object extraParam);

    /**
     * query a record form DB with different retrun value
     *
     * @param entity     实体模型
     * @param extraParam 额外参数
     * @param outEntity  返回实体类型
     */
    <O> O selectOne(T entity, Object extraParam, Class<O> outEntity);

    /**
     * query count number form DB
     *
     * @param entity     实体模型
     * @param extraParam 额外参数
     */
    int count(T entity, Object extraParam);

    /**
     * query a record to Map
     *
     * @param entity     实体模型
     * @param mapKey     映射KEY
     * @param extraParam 额外参数
     */
    Map<String, Object> selectMap(T entity, String mapKey, Object extraParam);

    /**
     * query records form DB
     *
     * @param entity     实体模型
     * @param extraParam 额外参数
     */
    List<T> selectList(T entity, Object extraParam);

    /**
     * query records form DB  with different return value
     *
     * @param entity     实体模型
     * @param extraParam 额外参数
     * @param outEntity  返回实体类型
     */
    <O> List<O> selectList(T entity, Object extraParam, Class<O> outEntity);

    /**
     * get sql session template
     *
     * @return SqlSessionTemplate
     */
    SqlSessionTemplate getSqlSessionTemplate();

}

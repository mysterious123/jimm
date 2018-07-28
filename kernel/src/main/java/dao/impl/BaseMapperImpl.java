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

package dao.impl;

import dao.BaseMapper;
import model.BaseModel;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * MyBatis Common DAO implements
 *
 * @author CSJ
 */
@Repository("baseMapper")
public class BaseMapperImpl<T extends BaseModel> implements BaseMapper<T> {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int insert(T entity) {
        return sqlSessionTemplate.insert(entity.getStatmentId(), entity);
    }

    @Override
    public int delete(T entity) {
        return sqlSessionTemplate.delete(entity.getStatmentId(), entity);
    }

    @Override
    public int update(T entity) {
        return sqlSessionTemplate.update(entity.getStatmentId(), entity);
    }

    @Override
    public T selectOne(T entity) {
        return sqlSessionTemplate.selectOne(entity.getStatmentId(), entity);
    }

    @Override
    public int count(T entity) {
        return sqlSessionTemplate.selectOne(entity.getStatmentId(), entity);
    }

    @Override
    public Map<String, Object> selectMap(T entity, String mapKey) {
        return sqlSessionTemplate.selectMap(entity.getStatmentId(), entity, mapKey);
    }

    @Override
    public List<T> selectList(T entity) {
        if (entity.isPaging()) {
            RowBounds rowBounds = getRowBounds(entity);
            return sqlSessionTemplate.selectList(entity.getStatmentId(), entity, rowBounds);
        }
        return sqlSessionTemplate.selectList(entity.getStatmentId(), entity);
    }

    @Override
    public int insert(T entity, Object extraParam) {
        return sqlSessionTemplate.insert(entity.getStatmentId(), extraParam);
    }

    @Override
    public int delete(T entity, Object extraParam) {
        return sqlSessionTemplate.delete(entity.getStatmentId(), extraParam);
    }

    @Override
    public int update(T entity, Object extraParam) {
        return sqlSessionTemplate.update(entity.getStatmentId(), extraParam);
    }

    @Override
    public T selectOne(T entity, Object extraParam) {
        return sqlSessionTemplate.selectOne(entity.getStatmentId(), extraParam);
    }

    @Override
    public int count(T entity, Object extraParam) {
        return sqlSessionTemplate.selectOne(entity.getStatmentId(), extraParam);
    }

    @Override
    public Map<String, Object> selectMap(T entity, String mapKey, Object extraParam) {
        return sqlSessionTemplate.selectMap(entity.getStatmentId(), extraParam, mapKey);
    }

    @Override
    public List<T> selectList(T entity, Object extraParam) {
        if (entity.isPaging()) {
            RowBounds rowBounds = getRowBounds(entity);
            return sqlSessionTemplate.selectList(entity.getStatmentId(), extraParam, rowBounds);
        }
        return sqlSessionTemplate.selectList(entity.getStatmentId(), extraParam);
    }

    private RowBounds getRowBounds(T entity) {
        int offset = entity.getStart();
        int limit = entity.getLimit();
        return new RowBounds(offset, limit);
    }

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public <O> O selectOne(T entity, Class<O> outEntity) {
        return sqlSessionTemplate.selectOne(entity.getStatmentId(), entity);
    }

    @Override
    public <O> List<O> selectList(T entity, Class<O> outEntity) {
        if (entity.isPaging()) {
            RowBounds rowBounds = getRowBounds(entity);
            return sqlSessionTemplate.selectList(entity.getStatmentId(), entity, rowBounds);
        }
        return sqlSessionTemplate.selectList(entity.getStatmentId(), entity);
    }

    @Override
    public <O> O selectOne(T entity, Object extraParam, Class<O> outEntity) {
        return sqlSessionTemplate.selectOne(entity.getStatmentId(), extraParam);
    }

    @Override
    public <O> List<O> selectList(T entity, Object extraParam, Class<O> outEntity) {
        if (entity.isPaging()) {
            RowBounds rowBounds = getRowBounds(entity);
            return sqlSessionTemplate.selectList(entity.getStatmentId(), extraParam, rowBounds);
        }
        return sqlSessionTemplate.selectList(entity.getStatmentId(), extraParam);
    }
}

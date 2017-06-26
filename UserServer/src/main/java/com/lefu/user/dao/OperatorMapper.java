package com.lefu.user.dao;

import java.util.List;

import com.lefu.user.entity.Operator;

public interface OperatorMapper {
	
	List<Operator> selectByOperator(Operator operator);
	
    int deleteByPrimaryKey(Long id);

    int insert(Operator record);

    int insertSelective(Operator record);

    Operator selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);
}
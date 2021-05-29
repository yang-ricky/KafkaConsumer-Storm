package com.KafkaConsumer_Storm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.KafkaConsumer_Storm.entity.DeptGet;

@Mapper
public interface DeptGetDao {
	boolean insert(List<DeptGet> entityList) throws Exception;//批量插入数据
}

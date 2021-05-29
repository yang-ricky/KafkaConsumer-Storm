package com.KafkaConsumer_Storm.service;

import java.util.List;

import com.KafkaConsumer_Storm.entity.DeptGet;



public interface DeptGetService {
	boolean insert(List<DeptGet> entityList); 
}
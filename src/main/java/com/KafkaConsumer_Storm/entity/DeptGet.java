package com.KafkaConsumer_Storm.entity;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"id","age","name"})
@Component
public class DeptGet {
	private int id;
	private String name;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString() {
		return JSON.toJSONString(this);
	}
}

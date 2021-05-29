package com.KafkaConsumer_Storm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.KafkaConsumer_Storm.storm.Topology;
import com.KafkaConsumer_Storm.util.GetSpringBean;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		GetSpringBean springBean=new GetSpringBean();
		springBean.setApplicationContext(context);
		Topology app = context.getBean(Topology.class);
		logger.info("启动1");
		//String[] str = {"Storm-Remote"};
		String[] str = null;
		app.runStorm(str);
		logger.info("启动2");
	}
}
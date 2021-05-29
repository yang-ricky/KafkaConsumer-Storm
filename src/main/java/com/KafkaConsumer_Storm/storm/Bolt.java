package com.KafkaConsumer_Storm.storm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.KafkaConsumer_Storm.constant.Constants;
import com.KafkaConsumer_Storm.entity.DeptGet;
import com.KafkaConsumer_Storm.service.DeptGetService;
import com.KafkaConsumer_Storm.util.GetSpringBean;
import com.alibaba.fastjson.JSON;
public class Bolt extends BaseRichBolt{

		private static final long serialVersionUID = 6542256546124282695L;
		private static final Logger logger = LoggerFactory.getLogger(Bolt.class);
		private DeptGetService infoGetService;
		
		@SuppressWarnings("rawtypes")
	//	@Override
		public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
			infoGetService=GetSpringBean.getBean(DeptGetService.class);
		}
	  
	//	@Override
		public void execute(Tuple tuple) {
			String msg=tuple.getStringByField(Constants.FIELD);
			try{
				List<DeptGet> listUser =JSON.parseArray(msg,DeptGet.class);
				//移除age小于10的数据
				if(listUser!=null&&listUser.size()>0){
					Iterator<DeptGet> iterator = listUser.iterator();
					 while (iterator.hasNext()) {
						 DeptGet user = iterator.next();
						 if (user.getAge()<10) {
							 logger.warn("Bolt移除的数据:{}",user);
							 iterator.remove();
						 }
					 }
					if(listUser!=null&&listUser.size()>0){
						infoGetService.insert(listUser);
					}
				}
			}catch(Exception e){
				logger.error("Bolt的数据处理失败!数据:{}",msg,e);
			}
		}


		@Override
		public void cleanup() {
		}

	//	@Override
		public void declareOutputFields(OutputFieldsDeclarer arg0) {
				
		}
		
	
}

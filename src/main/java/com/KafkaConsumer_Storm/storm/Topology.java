package com.KafkaConsumer_Storm.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.KafkaConsumer_Storm.constant.Constants;

import java.util.Arrays;


/**
 * @author yang
 */
@Component
public class Topology {
	private  final Logger logger = LoggerFactory.getLogger(Topology.class);

	public  void runStorm(String[] args) {
		// 定义一个拓扑
		TopologyBuilder builder = new TopologyBuilder();
		// 设置1个Executeor(线程)，默认一个
		builder.setSpout(Constants.KAFKA_SPOUT, new Spout(), 1);
		// shuffleGrouping:表示是随机分组
		// 设置1个Executeor(线程)，和两个task
		builder.setBolt(Constants.INSERT_BOLT, new Bolt(), 1).setNumTasks(1).shuffleGrouping(Constants.KAFKA_SPOUT);
		Config conf = new Config();

		//设置一个应答者
		conf.setNumAckers(1);
		//设置一个work
		conf.setNumWorkers(1);
		try {
			// 有参数时，表示向集群提交作业，并把第一个参数当做topology名称
			// 没有参数时，本地提交
			if (args != null && args.length > 0) {
				logger.info("运行远程模式");
				conf.put(Config.NIMBUS_SEEDS, Arrays.asList("47.103.138.95"));
				conf.put(Config.NIMBUS_THRIFT_PORT, 6627);
				conf.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("47.103.138.95"));
				conf.put(Config.STORM_ZOOKEEPER_PORT, 2181);
				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} else {
				// 启动本地模式
				logger.info("运行本地模式");
				LocalCluster cluster = new LocalCluster();
				cluster.submitTopology("TopologyApp", conf, builder.createTopology());
			}
		} catch (Exception e) {
			logger.error("storm启动失败!程序退出!",e);
			System.exit(1);
		}
		logger.info("storm启动成功...");
	}
}

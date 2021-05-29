package com.KafkaConsumer_Storm.sreviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KafkaConsumer_Storm.dao.DeptGetDao;
import com.KafkaConsumer_Storm.entity.DeptGet;
import com.KafkaConsumer_Storm.service.DeptGetService;



@Service
public class DeptGetServiceImpl implements DeptGetService{

	@Autowired
	private DeptGetDao deptGetDao;
	
//	@Override
	public boolean insert(List<DeptGet> entityList) {
		// TODO 自动生成的方法存根
		boolean flag = false;
		try {
			deptGetDao.insert(entityList);
			flag = true;
			System.out.println("数据写入成功！");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据写入失败！");
		}
		return flag;
	}

}
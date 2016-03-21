package com.test;

 
import model.TbsAModel;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.TbsAService;
import service.TestService;

import org.junit.Test;
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"classpath:spring-*.xml"/*,"classpath:spring-mybatis.xml"*/})
public class Testa {

	   // @Autowired
		//TestService<String>  testService;
		@Autowired
		private TbsAService<TbsAModel> tbsAService; 
	
	    @Test
		public void tbsLoginLogUpdates(){
			/*TbsLoginLogModel b=new TbsLoginLogModel();
			b.setId("1306241313291464857");
			TbsLoginLogModel b1=new TbsLoginLogModel();
			b1.setId("1306241313292928726");
			List<TbsLoginLogModel> ts=new ArrayList<TbsLoginLogModel>();
			ts.add(b);
			ts.add(b1);*/
	    	System.out.println("123");
			//testService.getSeq("");
			/*try {
				tbsLoginLogService.updatesTest(ts);
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
}

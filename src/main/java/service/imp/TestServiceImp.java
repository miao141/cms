package service.imp;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TestService;
import dao.BaseMapper;
import dao.TestMapper;
import db.RoutingDatasource;
/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b> <br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ  <br>
 */
@Service("testService")
public class TestServiceImp<T> extends BaseServiceImp<T> implements TestService<T>{/**/
	@Autowired
    private TestMapper<T> mapper;
 
    
	public BaseMapper<T> getMapper() {
	    return (BaseMapper<T>) mapper;
	}

	 
	public long getSeq(String seqName) {
		// TODO Auto-generated method stub
		return mapper.getSeq(seqName);
	}

 
 

}

package service.imp;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TbcConTempService;
import dao.TbcConTempMapper;
/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b> <br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ  <br>
 */
@Service("tbcConTempService")
public class TbcConTempServiceImp<T> extends BaseServiceImp<T> implements TbcConTempService<T>{
	@Autowired
    private TbcConTempMapper<T> mapper;
		
	public TbcConTempMapper<T> getMapper() {
	    return mapper;
	}
	

}

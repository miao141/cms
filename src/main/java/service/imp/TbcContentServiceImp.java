package service.imp;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TbcContentService;
import dao.TbcContentMapper;
/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b> <br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ  <br>
 */
@Service("tbcContentService")
public class TbcContentServiceImp<T> extends BaseServiceImp<T> implements TbcContentService<T>{
	@Autowired
    private TbcContentMapper<T> mapper;
		
	public TbcContentMapper<T> getMapper() {
	    return mapper;
	}
	

}

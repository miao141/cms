package service;

 
import org.apache.ibatis.annotations.Param;

import dao.TestMapper;
import db.RoutingDatasource;

/**
 * 
 * <br>
 * <b>功能：</b>定义在这里由 TestServiceImp来实现 私有的 <br>
 * <b>作者：</b> <br>
 * <b>日期：</b> 2013-5-24 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ  <br>
 */

public interface TestService<T>  extends BaseService<T> , TestMapper<T> {
	  //@RoutingDatasource("spider")
}

package service;

import dao.TbsUserMapper;

/**
 * 
 * <br>
 * <b>功能：</b>定义在这里由 TbsUserServiceImp来实现 私有的 <br>
 * <b>作者：</b> <br>
 * <b>日期：</b> 2013-5-24 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ  <br>
 */
public interface TbsUserService<T>  extends BaseService<T> , TbsUserMapper<T> {

}
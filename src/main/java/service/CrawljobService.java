package service;

import dao.CrawljobMapper;
public interface CrawljobService<T>  extends BaseService<T> , CrawljobMapper<T> {	 
	}

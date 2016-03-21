package service.imp;

import dao.Crawl_webMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.Crawl_webService;
@Service("crawl_webService")
public class Crawl_webServiceImp<T>  extends BaseServiceImp<T> implements Crawl_webService<T> {	 
	@Autowired 
	private Crawl_webMapper<T> mapper; 
	 
	 public Crawl_webMapper<T> getMapper() {
	  return mapper;
	 }
	};

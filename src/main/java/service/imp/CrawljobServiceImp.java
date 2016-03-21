package service.imp;

import dao.CrawljobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CrawljobService;
@Service("crawljobService")
public class CrawljobServiceImp<T>  extends BaseServiceImp<T> implements CrawljobService<T> {	 
	@Autowired 
	private CrawljobMapper<T> mapper; 
	 
	 public CrawljobMapper<T> getMapper() {
	  return mapper;
	 }
	};

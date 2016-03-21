package service.imp;

import dao.CrawlruleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CrawlruleService;
@Service("crawlruleService")
public class CrawlruleServiceImp<T>  extends BaseServiceImp<T> implements CrawlruleService<T> {	 
	@Autowired 
	private CrawlruleMapper<T> mapper; 
	 
	 public CrawlruleMapper<T> getMapper() {
	  return mapper;
	 }
	};

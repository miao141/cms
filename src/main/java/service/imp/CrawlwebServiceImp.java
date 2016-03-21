package service.imp;

import dao.CrawlwebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CrawlwebService;
@Service("crawlwebService")
public class CrawlwebServiceImp<T>  extends BaseServiceImp<T> implements CrawlwebService<T> {	 
	@Autowired 
	private CrawlwebMapper<T> mapper; 
	 
	 public CrawlwebMapper<T> getMapper() {
	  return mapper;
	 }
	};

package service.imp;

import dao.TransferruleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TransferruleService;
@Service("transferruleService")
public class TransferruleServiceImp<T>  extends BaseServiceImp<T> implements TransferruleService<T> {	 
	@Autowired 
	private TransferruleMapper<T> mapper; 
	 
	 public TransferruleMapper<T> getMapper() {
	  return mapper;
	 }
	};

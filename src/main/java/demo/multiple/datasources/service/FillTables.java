package demo.multiple.datasources.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.multiple.datasources.primary.entity.FirstTableEntity;
import demo.multiple.datasources.primary.repo.FirstTableRepo;
import demo.multiple.datasources.second.dao.SecondTableDao;
import demo.multiple.datasources.third.dao.ThirdTableDao;

@Service
public class FillTables {

	@Autowired
	private SecondTableDao secondTableDao;
	
	@Autowired
	private ThirdTableDao thirdTableDao;
	
	@Autowired
	private FirstTableRepo firstTableRepo;

	@Transactional(transactionManager="secondTransactionManager")
	public void fill(){
		firstTableRepo.save(FirstTableEntity.createNew());
		secondTableDao.insert();
		thirdTableDao.insert();
	}
	
}	

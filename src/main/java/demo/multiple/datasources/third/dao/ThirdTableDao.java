package demo.multiple.datasources.third.dao;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ThirdTableDao {

	@Autowired
	@Qualifier("thirdJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(transactionManager="thirdTransactionManager")
	public void insert(){
		String SQL = "insert into third_table (id, name) values (?, ?)";
		Random r = new Random();
		Integer id = r.nextInt();
		String name = "name_" + id;
		jdbcTemplate.update( SQL, id, name);
	}
	
}

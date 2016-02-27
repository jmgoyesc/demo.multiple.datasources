package demo.multiple.datasources.second.dao;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SecondTableDao {

	@Autowired
	@Qualifier("secondJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(transactionManager="secondTransactionManager")
	public void insert(){
		String SQL = "insert into second_table (id, name) values (?, ?)";
		Random r = new Random();
		Integer id = r.nextInt();
		String name = "name_" + id;
		jdbcTemplate.update( SQL, id, name);
	}
	
}

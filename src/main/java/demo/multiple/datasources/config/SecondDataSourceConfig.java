package demo.multiple.datasources.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SecondDataSourceConfig {

	@Bean(name="secondDatasource")
	@ConfigurationProperties(prefix="datasource.postgresql.second")
	public DataSource secondDataSource(){
	    return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource)	{
    	return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("secondDatasource") DataSource dataSource) {
    	DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
    	return txManager;
    }
	
}

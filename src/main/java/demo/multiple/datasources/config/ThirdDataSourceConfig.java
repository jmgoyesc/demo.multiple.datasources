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
public class ThirdDataSourceConfig {

	@Bean(name="thirdDatasource")
	@ConfigurationProperties(prefix="datasource.postgresql.third")
	public DataSource secondDataSource(){
	    return DataSourceBuilder.create().build();
	}

	@Bean(name = "thirdJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("thirdDatasource") DataSource dataSource)	{
    	return new JdbcTemplate(dataSource);
    }

    @Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("thirdDatasource") DataSource dataSource) {
    	DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
    	return txManager;
    }
	
}

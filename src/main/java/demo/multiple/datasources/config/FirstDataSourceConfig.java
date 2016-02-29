package demo.multiple.datasources.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FirstDataSourceConfig {

	@Primary
	@Bean(name="firstDatasource")
	@ConfigurationProperties(prefix="datasource.postgresql.primary")
	public DataSource secondDataSource(){
	    return DataSourceBuilder.create().build();
	}

	@Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("firstDatasource") DataSource dataSource) {
    	DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
    	return txManager;
    }

}

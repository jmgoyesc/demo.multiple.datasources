package demo.multiple.datasources.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfig {

	@Bean(name = "globalTransactionManager")
	public ChainedTransactionManager build(
			@Qualifier("transactionManager") PlatformTransactionManager first,
			@Qualifier("secondTransactionManager") PlatformTransactionManager second,
			@Qualifier("thirdTransactionManager") PlatformTransactionManager third){
		ChainedTransactionManager ctm = new ChainedTransactionManager(first, second, third);
		return ctm;
	}
	
}

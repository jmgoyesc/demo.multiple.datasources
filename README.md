# demo.multiple.datasources

This demo is to figure out how manage a transaction using 3 different datasources using PostgreSQL.

Databases:
* First database (first.db) uses a user (first.user) to connect and insert to one table (first_table). 
* Second database (second.db) uses a user (second.user) to connect and insert to one table (second_table)
* Third database (third.db) uses a user (third.user) to connect and insert to one table (third.table)

Tables:
* first_table contains an id (integer type) and name (text type)
* second_table contains an id (integer type) and name (text type)
* thrid_table contains an id (integer type) and name (character(1) type)

##How does it works

This is an spring-boot application which one contains a Rest service called /fill (http://localhost:8080/fill). This rest service calls a busines service named FillTables where it tries to insert a new row for each table (first_table, second_table, third_table). Using that business service, ideally application could insert in first_table and second_table tables, but it can not insert in third_table. After that, it should generate an RuntimeException because the value which is trying to insert in table thrid_table in column name is too long. Therefore, business service should rollback previous inserts for other tables.

##Setting the databases

I had used PostgreSQL. Run the following sql scripts as shows.

[Connected as superuser]

* Step 1: users.sql
* Step 2: db.sql

[connected as first.user]

* Step 3: first.db.table.sql

[connected as second.user]

* Step 4: second.db.tables.sql

[connected as third.user]

* Step 5: third.db.tables.sql


##How can you test it?

Using a rest client (I used postman google chrome extension), you should call http://localhost:8080/fill and you will get an error:

    {
      "timestamp": 1456553834236,
      "status": 500,
      "error": "Internal Server Error",
      "exception": "org.springframework.dao.DataIntegrityViolationException",
      "message": "PreparedStatementCallback; SQL [insert into third_table (id, name) values (?, ?)]; ERROR: el valor es demasiado largo para el tipo character(1); nested exception is org.postgresql.util.PSQLException: ERROR: el valor es demasiado largo para el tipo character(1)",
      "path": "/fill"
    }

After that, you should check each table to verify that nothing was inserted.


##What did I do?

I configured 3 datasources (1 JPA, 2 jdbcTemplate), 3 local transaction managers, 1 global transaction manager, 1 entity, 1 repository, 2 DAO, 1 business service, 1 rest controller, and 1 application starter.

###datasources and local transaction managers

* FirstDataSourceConfig.java

Contains a primary datasource (@Bean(name="firstDatasource")) related to datasource.postgresql.primary properties. It also defines a transacation manager (@Bean(name = "transactionManager")) for the current datasource

* SecondDataSourceConfig.java

Contains a secondary datasource (@Bean(name="secondDatasource")) related to datasource.postgresql.second properties. It also defines a transacation manager (@Bean(name = "secondTransactionManager")) for the current datasource

* ThirdDataSourceConfig.java

Contains a secondary datasource (@Bean(name="thirdDatasource")) related to datasource.postgresql.third properties. It also defines a transacation manager (@Bean(name = "thirdTransactionManager")) for the current datasource


###Global transaction manager

* ChainedTransactionConfig.java

Creates a new ChainedTransactionManager (@Bean(name = "globalTransactionManager")) which one encapsulating the other transaction managers (transactionManager, secondTransactionManager, and thirdTransactionManager)


###Business service

* FillTables.java

Contains a method named fill() which one is annotated with @Transactional(transactionManager="globalTransactionManager").


##References
* http://www.javaworld.com/article/2077963/open-source-tools/distributed-transactions-in-spring--with-and-without-xa.html?page=2
* http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos/
* http://spring.io/blog/2011/08/15/configuring-spring-and-jta-without-full-java-ee/
* http://docs.spring.io/spring/docs/current/spring-framework-reference/html/transaction.html

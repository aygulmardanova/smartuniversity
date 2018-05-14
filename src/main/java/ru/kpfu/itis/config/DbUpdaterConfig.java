package ru.kpfu.itis.config;

import liquibase.configuration.GlobalConfiguration;
import liquibase.configuration.LiquibaseConfiguration;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbUpdaterConfig {

    private static final Logger logger = LoggerFactory.getLogger(DbUpdaterConfig.class);

    @Value("${dbupdater.enabled:true}")
    private Boolean springLiquibaseEnabled;

    @Value("${liquibase.contexts:production}")
    private String liquibaseContexts;

    @Value("${liquibase.test.data.enabled:false}")
    private Boolean liquibaseTestDataEnabled;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "liquibase")
    public Object springLiquibase() {
        if (springLiquibaseEnabled) {
            logger.info("spring springLiquibase is enabled");
            SpringLiquibase springLiquibase = new SpringLiquibase();
            springLiquibase.setDataSource(dataSource);
            springLiquibase.setChangeLog("classpath:liquibase/baseChangelog.xml");
            springLiquibase.setDefaultSchema("liquibase");
            if (liquibaseTestDataEnabled) {
              springLiquibase.setContexts("test, "+liquibaseContexts);
            } else{
                springLiquibase.setContexts(liquibaseContexts);
            }
            LiquibaseConfiguration.getInstance().getConfiguration(GlobalConfiguration.class).setDatabaseChangeLogLockWaitTime(60L);
            return springLiquibase;
        }
        return new Object();
    }

}
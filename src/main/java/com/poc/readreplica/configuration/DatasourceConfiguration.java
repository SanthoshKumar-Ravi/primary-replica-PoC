package com.poc.readreplica.configuration;

import com.poc.readreplica.router.DataSourceRouter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatasourceConfiguration {
    private static final String PRIMARY_DATASOURCE_PREFIX = "spring.primary.datasource";
    private static final String READONLY_DATASOURCE_PREFIX = "spring.read-only.datasource";

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSourceRouter dataSource() {
        final DataSource primaryDataSource = buildDataSource("PrimaryHikariPool", PRIMARY_DATASOURCE_PREFIX);
        final DataSource readonlyDataSource = buildDataSource("ReadOnlyHikariPool", READONLY_DATASOURCE_PREFIX);
        DataSourceRouter dataSourceRouter = new DataSourceRouter();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceRouter.Route.PRIMARY, primaryDataSource);
        dataSourceMap.put(DataSourceRouter.Route.READ_ONLY, readonlyDataSource);

        dataSourceRouter.setTargetDataSources(dataSourceMap);
        dataSourceRouter.setDefaultTargetDataSource(primaryDataSource);

        return dataSourceRouter;
    }

    private DataSource buildDataSource(String poolName, String dataSourcePrefix) {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName(poolName);
        hikariConfig.setJdbcUrl(environment.getProperty(String.format("%s.url", dataSourcePrefix)));
        hikariConfig.setUsername(environment.getProperty(String.format("%s.username", dataSourcePrefix)));
        hikariConfig.setPassword(environment.getProperty(String.format("%s.password", dataSourcePrefix)));
        hikariConfig.setDriverClassName(environment.getProperty(String.format("%s.driver", dataSourcePrefix)));

        return new HikariDataSource(hikariConfig);
    }
}

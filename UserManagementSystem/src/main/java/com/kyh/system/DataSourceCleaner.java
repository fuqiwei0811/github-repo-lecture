package com.kyh.system;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

/*
* アプリケーションがシャットダウンされる際に、
* DataSource（HikariCP）が適切にクローズされ、リソースが解放されます。
*/
@Component
public class DataSourceCleaner {

    private final HikariDataSource dataSource;

    public DataSourceCleaner(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PreDestroy
    public void cleanup() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}

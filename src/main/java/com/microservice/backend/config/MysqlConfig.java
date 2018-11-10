package com.microservice.backend.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
/**
 *
 * 解决spring-data-jpa 建表编码问题
 */
public class MysqlConfig extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}


package com.example.news.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public interface ConnectionDao {
    @Bean
    //查找并返回数据源对象
    default DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=News;encrypt=true;trustServerCertificate=true");
        dataSource.setUsername("sa");
        dataSource.setPassword("123456");
        return dataSource;
    }

    default Connection getConnection(){
        DataSource dataSource = getDataSource();
        Connection connection = null;
        try{
            connection=dataSource.getConnection();
        }catch(SQLException sqle){
            System.out.println("异常:"+sqle);
        }
        return connection;
    }

}

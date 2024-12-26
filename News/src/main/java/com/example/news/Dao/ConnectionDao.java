package com.example.news.Dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDao {
    //查找并返回数据库源对象
    static DataSource getDataSource() {
        DataSource dataSource = null;
        try{
            Context context = new InitialContext();
            dataSource=(DataSource)context.lookup("java:comp/env/jdbc/webstoreDS");
        }catch(NamingException ne){
            System.out.println("异常: " + ne);
        }
        return dataSource;
    }
    //返回连接对象方法
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

package com.example.shop.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface Dao {
    public static DataSource getDataSource(){
        DataSource dataSource=null;
        try {
            Context context=new InitialContext();
            dataSource=(DataSource) context.lookup("java:comp/env/jdbc/webstoreDS");
        }catch (NamingException ne){
            System.out.println("异常"+ne);
        }
        return  dataSource;
    }
    public default Connection getConnection(){
        DataSource dataSource=getDataSource();
        Connection conn=null;
        try {
            conn=dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("异常"+e);
        }
        return conn;
    }
}

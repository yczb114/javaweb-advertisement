package com.example.admanagement.DAOPackage;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface DAO {
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try{
            Context contetxt = new InitialContext();
            dataSource=(DataSource)contetxt.lookup("java:comp/env/jdbc/advertisementinfo");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
    public default Connection getConnection() throws Exception{
        DataSource ds = getDataSource();
        Connection con = null;
        try{
            con = ds.getConnection();
            System.out.println("111");
        }catch(SQLException e){
            System.out.println("error"+e.getMessage());
        }
        System.out.println("222");
        return con;
    }
}

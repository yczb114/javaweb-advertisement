package com.example.admanagement.DAOPackage;

public interface AdminDao extends DAO {
    boolean checkAdmin(String adminName, String adminPassword);
}

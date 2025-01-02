package com.example.admanagement.DAOPackage;

import java.util.List;

public interface AdminDao extends DAO {
    boolean checkAdmin(String adminName, int adminPassword);

    List<InternetWebmaster> getAllWebmaster();
}

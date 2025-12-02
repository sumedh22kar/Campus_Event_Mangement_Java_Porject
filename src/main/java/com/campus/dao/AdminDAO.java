package com.campus.dao;

import java.sql.*;

public class AdminDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/campus_event_db";
    private static final String USER = "root";
    private static final String PASS = "admin";

    public boolean login(String username, String password) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM admin WHERE username=? AND password=?"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
}

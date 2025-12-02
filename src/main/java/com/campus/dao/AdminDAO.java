package com.campus.dao;

import java.sql.*;
import java.util.*;

public class AdminDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/campus_event_db";
    private static final String USER = "root";
    private static final String PASS = "admin";

    // LOGIN
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

    // ADD EVENT
    public void addEvent(String name, String date, String venue, int capacity) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO event(name, event_date, venue, capacity) VALUES (?,?,?,?)"
            );
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, venue);
            ps.setInt(4, capacity);
            ps.executeUpdate();
            con.close();
            System.out.println("Event Added Successfully!");
        } catch (Exception e) {
            System.out.println("Add Event Error: " + e.getMessage());
        }
    }

    // SHOW ALL EVENTS
    public void showEvents() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM event");

            System.out.println("\n===== Event List =====");
            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("event_id") +
                    " | Name: " + rs.getString("name") +
                    " | Date: " + rs.getString("event_date") +
                    " | Venue: " + rs.getString("venue") +
                    " | Capacity: " + rs.getInt("capacity")
                );
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Show Events Error: " + e.getMessage());
        }
    }
    
    
 // DELETE EVENT
    public void deleteEvent(int eventId) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM event WHERE event_id = ?"
            );
            ps.setInt(1, eventId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Event deleted successfully!");
            } else {
                System.out.println("No event found with the given ID.");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Delete Event Error: " + e.getMessage());
        }
    }
    
    //View Upcoming Events
    public void viewUpcomingEvents() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM event WHERE event_date > CURDATE() ORDER BY event_date"
            );
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Upcoming Events -----");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("event_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getDate("event_date") + " | " +
                    rs.getString("venue") + " | " +
                    rs.getInt("capacity")
                );
            }
            if (!found) System.out.println("No such events");
            con.close();
        } catch (Exception e) {
            System.out.println("Update Error " + e.getMessage());
        }
    }
    //update by name
    public boolean updateEvent(String oldName, String newName,  String newDate, String newVenue, int newCapacity) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE event SET name=?, event_date=?, venue=?, capacity=? WHERE name=?"
            );
            ps.setString(1, newName);
            ps.setString(2, newDate);
            ps.setString(3, newVenue);
            ps.setInt(4, newCapacity);
            ps.setString(5, oldName);

            int rows = ps.executeUpdate();
            con.close();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Update Event Error: " + e.getMessage());
            return false;
        }
    }




    
}

package com.campus.dao;

import java.sql.*;
import java.util.*;
import com.campus.model.Event;

public class EventDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/campus_event_db";
    private static final String USER = "root";
    private static final String PASS = "admin";

    // Add Event
    public void addEvent(Event e) throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO event(name, event_date, venue, capacity) VALUES (?,?,?,?)"
        );

        ps.setString(1, e.getName());
        ps.setString(2, e.getEventDate());
        ps.setString(3, e.getVenue());
        ps.setInt(4, e.getCapacity());
        ps.executeUpdate();
        con.close();
    }

    // View All Events
    public List<Event> getAllEvents() throws Exception {
        List<Event> list = new ArrayList<>();
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM event");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Event e = new Event();
            e.setEventId(rs.getInt("event_id"));
            e.setName(rs.getString("name"));
            e.setEventDate(rs.getString("event_date"));
            e.setVenue(rs.getString("venue"));
            e.setCapacity(rs.getInt("capacity"));
            list.add(e);
        }
        con.close();
        return list;
    }

    // Delete Event by ID
    public boolean deleteEvent(int id) throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement("DELETE FROM event WHERE event_id=?");
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    // Update Event by name
    public boolean updateEvent(String oldName, String newName, String event_date, String newDate, String newVenue, int newCapacity) {
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
            return rows > 0; // true if updated
        } catch (Exception e) {
            System.out.println("Update Event Error: " + e.getMessage());
            return false;
        }
    }



    // View Upcoming Events (date > today)
    public List<Event> getUpcomingEvents() throws Exception {
        List<Event> list = new ArrayList<>();
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM event WHERE event_date > CURDATE()"
        );
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Event e = new Event();
            e.setEventId(rs.getInt("event_id"));
            e.setName(rs.getString("name"));
            e.setEventDate(rs.getString("event_date"));
            e.setVenue(rs.getString("venue"));
            e.setCapacity(rs.getInt("capacity"));
            list.add(e);
        }
        con.close();
        return list;
    }
}

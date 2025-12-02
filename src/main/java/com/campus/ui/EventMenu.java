package com.campus.ui;

import java.util.Scanner;
import com.campus.dao.AdminDAO;

public class EventMenu {

    private AdminDAO dao = new AdminDAO();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n===== EVENT MANAGEMENT MENU =====");
            System.out.println("1. Add Event");
            System.out.println("2. View All Events");
            System.out.println("3. View Upcoming Events");
            System.out.print("4. Enter Event ID to delete: ");
            System.out.println("5. Update Events by details");
            System.out.println("6. Download Report ");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); // flush input

            switch (ch) {
                case 1:
                    addEventUI();
                    break;

                case 2:
                	System.out.println("2. View All Events");
                    dao.showEvents();
                    break;
                    
                case 3:
                	System.out.println("3. View Upcoming Events");
                    dao.viewUpcomingEvents();
                    break;

                case 4:
                    System.out.print("4. Enter Event ID to delete: ");
                    int id = sc.nextInt();
                    dao.deleteEvent(id);
                    break;
                    
                case 5:
                	 dao.showEvents();
                    System.out.println("5. Update Event");
                    System.out.print("Enter Event Name you want to update: ");
                    String oldName = sc.nextLine().trim();

                    System.out.print("Enter new Event Name ");
                    String newName = sc.nextLine().trim();

                    System.out.print("Enter new Venue");
                    String newVenue = sc.nextLine();

                    System.out.print("Enter new Date");
                    String newDate = sc.nextLine();
                    
                    System.out.print("Enter new Capacity ");
                    int newCapacity = sc.nextInt();

                    boolean updated = dao.updateEvent(oldName, newName, newDate, newVenue, newCapacity);

                    if (updated) {
                        System.out.println("Event Updated Successfully!");
                    } else {
                        System.out.println("⚠ Event not found!");
                    }
                    break;

                    
                    
                	
                	//updateEvent(String name, String venue, int capacity
                	
                case 6:
                    System.out.println("6. Download Report ");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void addEventUI() {
        System.out.print("Event Name: ");
        String name = sc.nextLine();
        System.out.print("Event Date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("Venue: ");
        String venue = sc.nextLine();
        System.out.print("Capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        dao.addEvent(name, date, venue, capacity);
    }
}

package com.campus.ui;

import java.util.*;
import com.campus.dao.AdminDAO;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AdminDAO dao = new AdminDAO();

        System.out.println("===== Campus Event Management System =====");
        System.out.print("\n 1. Student: \n 2. Admin\n Chhose Login:  \n");
        int choice=sc.nextInt();
        sc.nextLine();
        switch(choice) {
        	case 1: 
        		System.out.println("Logged in as Student");
        		 System.out.print("Username: ");
        	        String username = sc.nextLine();
        	        System.out.print("Password: ");
        	        String password = sc.nextLine();
        	        EventMenu eventMenu = new EventMenu();
                    eventMenu.menu(); //just for testing later there will another menu for StudMaster
        	        break;
        	case 2: 
        		System.out.println("Admin Login");
        		System.out.print("Username: ");
                 username = sc.nextLine();
                System.out.print("Password: ");
                password = sc.nextLine();
                if (dao.login(username, password)) {
                    System.out.println("\nLogin Successful!");
                   
                    
                    // Redirect to event menu
                 //Dont forgot to later add EventMenu
                    eventMenu = new EventMenu();
                    eventMenu.menu();        // <<--- open event menu
                } else {
                    System.out.println("Invalid Credentials!");
                }
                break;
        	case 3: 
        		System.out.println("Logged in as Student Master");
        		 System.out.print("Username: ");
        	        username = sc.nextLine();
        	        System.out.print("Password: ");
        	        password = sc.nextLine();
        	        break;
        		
        		
        }
        

        sc.close();
    }
}

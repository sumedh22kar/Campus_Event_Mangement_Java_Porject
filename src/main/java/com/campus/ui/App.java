package com.campus.ui;

import java.util.Scanner;
import com.campus.dao.AdminDAO;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminDAO dao = new AdminDAO();

        System.out.println("===== Campus Event Management System =====");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (dao.login(username, password)) {
            System.out.println("Login Successful! Welcome Admin.");
            // Next: show admin dashboard
        } else {
            System.out.println("Invalid Credentials!");
        }
        sc.close();
    }
}

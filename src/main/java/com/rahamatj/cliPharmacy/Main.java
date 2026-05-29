package com.rahamatj.cliPharmacy;

import java.sql.*;
import java.util.*;

public class Main {

    // Database URL (static, shared)
    private static final String URL = "jdbc:sqlite:pharmacy.db";

    // Create medicines table using provided connection
    private static void createMedicineTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS medicines (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            price REAL NOT NULL,
                            expiry TEXT NOT NULL,
                            quantity INTEGER NOT NULL
                        )
                    """);
        }
    }

    // Add a medicine record using the supplied connection
    private static void addMedicine(Connection conn, Scanner scanner) {


        System.out.println("Enter name of medicine: ");
        String name = scanner.nextLine();
        System.out.println("Enter price of medicine: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter expiry of medicine (dd/mm/yyyy): ");
        String expiry = scanner.nextLine();
        System.out.println("Enter quantity of medicine: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO medicines(name, price, expiry, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, expiry);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
            System.out.println("Medicine added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
                Connection conn = DriverManager.getConnection(URL)) {

            // Ensure the medicines table exists before the loop
            createMedicineTable(conn);

            String choice;
            do {
                System.out.println("\n1. Add medicine (1)");
                System.out.println("2. Show all medicines (2)");
                System.out.println("3. Quit (q)");
                System.out.println("Enter your choice: ");
                choice = scanner.nextLine();

                if (choice.equals("1")) {
                    addMedicine(conn, scanner);
                } else if (choice.equals("2")) {
                    printAllMedicines(conn);
                }
            } while (!choice.equalsIgnoreCase("q"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllMedicines(Connection conn) throws SQLException {
        String sql = "SELECT * FROM medicines";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("name") +
                                " - " +
                                rs.getDouble("price") +
                                " - " +
                                rs.getString("expiry") +
                                " - " +
                                rs.getInt("quantity"));
            }
        }
    }
}
package com.example.contact;
import io.github.cdimascio.dotenv.Dotenv;

import org.flywaydb.core.Flyway;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static ContactDAO contactDAO;
    private static Scanner scanner = new Scanner(System.in);

    static {
        Dotenv dotenv = Dotenv.load();
        DB_URL = dotenv.get("DB_URL");
        DB_USER = dotenv.get("DB_USER");
        DB_PASSWORD = dotenv.get("DB_PASSWORD");
        contactDAO = new ContactDAO(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void main(String[] args) {
        // 1. Run Database Migration with Flyway
        Flyway flyway = Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load();
        flyway.migrate();
        System.out.println("Database updated successfully.");

        // 2. Run application loop
        runApp();
    }

    private static void runApp() {
        while (true) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                handleChoice(choice);
                if (choice == 9) break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number.");
            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void handleChoice(int choice) throws SQLException {
        switch (choice) {
            case 1: addContact(); break;
            case 2: showAllContacts(); break;
            case 3: updateContact(); break;
            case 4: deleteContact(); break;
            case 5: createGroup(); break;
            case 6: showAllGroups(); break;
            case 7: addContactToGroup(); break;
            case 8: showContactsInGroup(); break;
            case 9: System.out.println("Goodbye!"); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void printMenu() {
        System.out.println("\n--- CONTACT MANAGEMENT ---");
        System.out.println("1. Add new contact | 2. View all contacts | 3. Update contact | 4. Delete contact");
        System.out.println("5. Create new group   | 6. View all groups   | 7. Add contact to group | 8. View contacts in group");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addContact() throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        contactDAO.addContact(new Contact(0, name, phone, email));
        System.out.println("-> Contact added successfully!");
    }

    private static void showAllContacts() throws SQLException {
        List<Contact> contacts = contactDAO.getAllContacts();
        System.out.println("\n--- CONTACT LIST ---");
        if (contacts.isEmpty()) System.out.println("Contact list is empty.");
        else contacts.forEach(contact -> System.out.println(contact));
    }

    private static void updateContact() throws SQLException {
        System.out.print("Enter ID of the contact to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        contactDAO.updateContact(new Contact(id, name, phone, email));
        System.out.println("-> Updated successfully!");
    }

    private static void deleteContact() throws SQLException {
        System.out.print("Enter ID of the contact to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        contactDAO.deleteContact(id);
        System.out.println("-> Contact deleted!");
    }

    private static void createGroup() throws SQLException {
        System.out.print("Enter new group name: ");
        String groupName = scanner.nextLine();
        contactDAO.createGroup(new Group(0, groupName));
        System.out.println("-> Group created successfully!");
    }

    private static void showAllGroups() throws SQLException {
        List<Group> groups = contactDAO.getAllGroups();
        System.out.println("\n--- GROUP LIST ---");
        if (groups.isEmpty()) System.out.println("No groups yet.");
        else groups.forEach(System.out::println);
    }

    private static void addContactToGroup() throws SQLException {
        System.out.print("Enter contact ID: ");
        int contactId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter group ID: ");
        int groupId = Integer.parseInt(scanner.nextLine());
        contactDAO.addContactToGroup(contactId, groupId);
        System.out.println("-> Added contact to group!");
    }

    private static void showContactsInGroup() throws SQLException {
        System.out.print("Enter group ID to view: ");
        int groupId = Integer.parseInt(scanner.nextLine());
        List<Contact> contacts = contactDAO.getContactsByGroup(groupId);
        System.out.println("\n--- CONTACTS IN GROUP ID " + groupId + " ---");
        if (contacts.isEmpty()) System.out.println("This group has no contacts yet.");
        else contacts.forEach(contact -> System.out.println(contact));
    }
}

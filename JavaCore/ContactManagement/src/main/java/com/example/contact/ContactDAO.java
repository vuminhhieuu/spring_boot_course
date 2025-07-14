package com.example.contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public ContactDAO(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Các phương thức cho Contact
    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO CONTACTS (name, phone_number, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhoneNumber());
            pstmt.setString(3, contact.getEmail());
            pstmt.executeUpdate();
        }
    }

    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM CONTACTS";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contacts.add(new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone_number"), rs.getString("email")));
            }
        }
        return contacts;
    }

    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE CONTACTS SET name = ?, phone_number = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhoneNumber());
            pstmt.setString(3, contact.getEmail());
            pstmt.setInt(4, contact.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM CONTACTS WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Các phương thức cho Group
    public void createGroup(Group group) throws SQLException {
        String sql = "INSERT INTO GROUPS (group_name) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, group.getGroupName());
            pstmt.executeUpdate();
        }
    }

    public List<Group> getAllGroups() throws SQLException {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM GROUPS";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                groups.add(new Group(rs.getInt("id"), rs.getString("group_name")));
            }
        }
        return groups;
    }

    public void addContactToGroup(int contactId, int groupId) throws SQLException {
        String sql = "INSERT INTO contact_groups (contact_id, group_id) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            pstmt.setInt(2, groupId);
            pstmt.executeUpdate();
        }
    }

    public List<Contact> getContactsByGroup(int groupId) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT c.* FROM contacts c JOIN contact_groups cg ON c.id = cg.contact_id WHERE cg.group_id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                contacts.add(new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone_number"), rs.getString("email")));
            }
        }
        return contacts;
    }
}
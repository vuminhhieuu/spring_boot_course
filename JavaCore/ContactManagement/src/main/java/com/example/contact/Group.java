package com.example.contact;
public class Group {
    private int id;
    private String groupName;

    public Group(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public int getId() { return id; }
    public String getGroupName() { return groupName; }
}
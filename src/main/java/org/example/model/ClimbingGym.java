package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;

public class ClimbingGym {
    private String owner;
    private String address;
    private String name;
    private ArrayList<Member> members;

    public ArrayList<Member> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "ClimbingGym{" +
                "owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", members=" + members.toString() +
                '}';
    }
}

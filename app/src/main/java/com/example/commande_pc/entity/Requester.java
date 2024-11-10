package com.example.commande_pc.entity;

import com.example.commande_pc.database.SqliteDatabaseHelper;

import java.util.ArrayList;
import java.util.Date;

public class Requester extends User{
    private static long role_id;
    static {
        Requester.role_id = Role.findRoleByRoleName("requester").getId();
    }
    public Requester(String lastName, String firstName, String email, long id, String password, Date created_at, Date updated_at) {
        super(lastName, firstName, email, id, password, created_at, updated_at);
    }
    public Requester(String lastName, String firstName, String email, String password) {
        super(lastName, firstName, email, -1, password, null, null);
    }

    @Override
    public Role getRole() {
        if(this.role != null) return this.role;
        this.role =  Role.findRoleById(Requester.role_id);
        return this.role;
    }
    @Override
    public long getRoleId() {
        return  Requester.role_id;
    }
    public ArrayList<Item> getItems() {
        SqliteDatabaseHelper dbHelper = new SqliteDatabaseHelper();
        ArrayList<Item> items = dbHelper.getItems();
        dbHelper.close();
        return items;
    }
}

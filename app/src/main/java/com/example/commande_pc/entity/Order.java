package com.example.commande_pc.entity;

import java.util.Date;

public class Order {
    private long id;
    private long requester_id;
    private Date createdAt;
    private final String state="En attente d'acceptation";
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequester_id() {
        return requester_id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Order(long id, long requester_id, Date createdAt) {
        this.id = id;
        this.requester_id = requester_id;
        this.createdAt = createdAt;
    }
    public String getState() {
        return state;
    }
}

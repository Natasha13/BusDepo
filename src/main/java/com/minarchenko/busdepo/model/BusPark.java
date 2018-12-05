package com.minarchenko.busdepo.model;

public class BusPark {
    private int id;
    private int bus_id;
    private int user_id;
    private int route_id;
    private boolean accepted;

    public BusPark(int id, int bus_id, int user_id, int route_id, boolean accepted) {
        this.id = id;
        this.bus_id = bus_id;
        this.user_id = user_id;
        this.route_id = route_id;
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "BusPark{" +
                "id=" + id +
                ", bus_id=" + bus_id +
                ", user_id=" + user_id +
                ", route_id=" + route_id +
                ", accepted=" + accepted +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

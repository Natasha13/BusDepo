package com.minarchenko.busdepo.model;

/**
 *  Database entity with BusPark data in it.
 *  Stored in table:
 *  bus (  id INT NOT NULL AUTO_INCREMENT, bus_id INT NOT NULL UNIQUE ,
 *  user_id INT NOT NULL  UNIQUE ,route_id INT NOT NULL  ,
 *  accepted BOOLEAN NOT NULL , FOREIGN KEY (bus_id) REFERENCES buses (Id) ON DELETE CASCADE,
 *  FOREIGN KEY (user_id) REFERENCES users (Id) ON DELETE CASCADE,
 *  FOREIGN KEY (route_id) REFERENCES routes (Id) ON DELETE CASCADE,
 *  PRIMARY KEY (id))
 */
public class BusPark {
    private int id;
    private Bus bus;
    private Route route;
    private User user;
    boolean accepted;

    public BusPark(int id, Bus bus, Route route, User user, boolean accepted) {
        this.id = id;
        this.bus = bus;
        this.route = route;
        this.user = user;
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "BusPark{" +
                "id=" + id +
                ", bus=" + bus +
                ", route=" + route +
                ", user=" + user +
                ", accepted=" + accepted +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

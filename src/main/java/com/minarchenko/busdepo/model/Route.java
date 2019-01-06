package com.minarchenko.busdepo.model;

public class Route {
    private int id;
    private String routeName;

    public Route(int id, String routeName) {
        this.id = id;
        this.routeName = routeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (id != route.id) return false;
        return routeName != null ? routeName.equals(route.routeName) : route.routeName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                '}';
    }
}

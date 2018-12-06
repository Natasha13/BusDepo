package com.minarchenko.busdepo.model;

public class Bus {
    private int id;
    private String busNumber;

    public Bus(int id, String busNumber) {
        this.id = id;
        this.busNumber = busNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bus{" +
                ", id=" + id +
                "busNumber='" + busNumber + '\'' +
                '}';
    }
}

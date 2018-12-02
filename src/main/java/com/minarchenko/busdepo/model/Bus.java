package com.minarchenko.busdepo.model;

public class Bus {
    private String busNumber;
    private int id;

    public Bus(String busNumber, int id) {
        this.busNumber = busNumber;
        this.id = id;
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
                "busNumber='" + busNumber + '\'' +
                ", id=" + id +
                '}';
    }
}

package com.minarchenko.busdepo.model;

/**
 * Database entity with Bus data in it.
 * Stored in table:
 * bus (id INT NOT NULL AUTO_INCREMENT, bus_number varchar(30))
 */
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bus bus = (Bus) o;

        if (id != bus.id) return false;
        return busNumber != null ? busNumber.equals(bus.busNumber) : bus.busNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (busNumber != null ? busNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", busNumber='" + busNumber + '\'' +
                '}';
    }
}

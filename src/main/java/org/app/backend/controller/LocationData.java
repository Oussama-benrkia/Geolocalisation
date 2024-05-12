package org.app.backend.controller;

public class LocationData {
    private double x;
    private double y;

    public LocationData() {
    }

    public LocationData(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
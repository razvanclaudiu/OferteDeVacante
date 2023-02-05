package com.example.vacantion.domain;

public class Location extends Entity<Double>{

    private String locationName;

    public Location(String locationName)
    {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationName='" + locationName + '\'' +
                '}';
    }
}

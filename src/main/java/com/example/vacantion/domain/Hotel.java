package com.example.vacantion.domain;

public class Hotel extends Entity<Double>{

    private Double locationId;

    private String hotelName;

    private Integer noOfRooms;

    private Double pricePerNight;
    private Type type;

    public Hotel(Double locationId, String hotelName, Integer noOfRooms, Double pricePerNight, Type type) {
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noOfRooms = noOfRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public Double getLocationId() {
        return locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "locationId=" + locationId +
                ", hotelName='" + hotelName + '\'' +
                ", noOfRooms=" + noOfRooms +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}

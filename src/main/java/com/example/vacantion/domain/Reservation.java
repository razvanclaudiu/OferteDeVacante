package com.example.vacantion.domain;

import java.time.LocalDate;

public class Reservation extends Entity<Double> {

    private Long clientId;
    private Double hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer noNights;

    public Reservation(Long clientId, Double hotelId, LocalDate startDate, LocalDate endDate, Integer noNights) {
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noNights = noNights;
    }

    public Long getClientId() {
        return clientId;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    @Override
    public String toString() {
        return "Reservation{" + "clientId=" + clientId + ", hotelId=" + hotelId + ", startDate=" + startDate + ", endDate=" + endDate + ", noNights=" + noNights + '}';
    }
}

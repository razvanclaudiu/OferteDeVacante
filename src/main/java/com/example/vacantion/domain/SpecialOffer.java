package com.example.vacantion.domain;

import java.time.LocalDate;

public class SpecialOffer extends Entity<Double>{

    private Double hotelId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer percentage;

    public SpecialOffer(Double hotelId, LocalDate startDate, LocalDate endDate, Integer percentage) {
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
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

    public Integer getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "SpecialOffer{" +
                "hotelId=" + hotelId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", percentage=" + percentage +
                '}';
    }

}

package com.example.vacantion.service;

import com.example.vacantion.domain.*;
import com.example.vacantion.repository.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Service {

    private LocationDBRepository locationDBRepository;

    private HotelDBRepository hotelDBRepository;

    private SpecialOfferDBRepository specialOfferDBRepository;

    private ClientDBRepository clientDBRepository;

    private ReservationDBRepository reservationDBRepository;

    private String notification = "";

    private String hobbynotif="";

    private int idnotif=0;

    public Service(LocationDBRepository locationDBRepository, HotelDBRepository hotelDBRepository, SpecialOfferDBRepository specialOfferDBRepository, ClientDBRepository clientDBRepository, ReservationDBRepository reservationDBRepository) {
        this.locationDBRepository = locationDBRepository;
        this.hotelDBRepository = hotelDBRepository;
        this.specialOfferDBRepository = specialOfferDBRepository;
        this.clientDBRepository = clientDBRepository;
        this.reservationDBRepository = reservationDBRepository;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getHobbynotif() {
        return hobbynotif;
    }

    public void setHobbynotif(String hobbynotif) {
        this.hobbynotif = hobbynotif;
    }

    public int getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(int idnotif) {
        this.idnotif = idnotif;
    }

    public List<Hotel> getAllHotels() {
        return hotelDBRepository.getAll().values().stream().toList();
    }

    public List<Location> getAllLocations() {
        return locationDBRepository.getAll().values().stream().toList();
    }

    public List<Hotel> getAllHotelsByLocation(Location newValueLocation) {
        return hotelDBRepository.getAll().values().stream().filter(hotel -> hotel.getLocationId().equals(newValueLocation.getId())).toList();
    }

    public List<SpecialOffer> getAllSpecialOffersByDateIntervalAndHotelId(LocalDate datestart, LocalDate dateend, Double id)
    {
        System.out.println("datestart: " + datestart + " dateend: " + dateend + " id: " + id);
        if(datestart == null && dateend == null)
            return specialOfferDBRepository.getAll().values().stream().filter(specialOffer -> specialOffer.getHotelId().equals(id)).toList();
        if(datestart == null)
            return specialOfferDBRepository.getAll().values().stream().filter(specialOffer -> specialOffer.getEndDate().isBefore(dateend) && specialOffer.getHotelId().equals(id)).toList();
        if(dateend == null)
            return specialOfferDBRepository.getAll().values().stream().filter(specialOffer -> specialOffer.getStartDate().isAfter(datestart) && specialOffer.getHotelId().equals(id)).toList();
        return specialOfferDBRepository.getAll().values().stream().filter(specialOffer -> specialOffer.getStartDate().isAfter(datestart) && specialOffer.getEndDate().isBefore(dateend) && specialOffer.getHotelId().equals(id)).toList();
    }

    public List<Client> getAllClients()
    {
        return clientDBRepository.getAll().values().stream().toList();
    }

    //return offers with percentage < fidelity grade and end date > current date
    public List<SpecialOffer> getAllOffersOfClient(String name, LocalDate currentDate) {
        Client client = clientDBRepository.getAll().values().stream().filter(client1 -> client1.getName().equals(name)).findFirst().get();
        return specialOfferDBRepository.getAll().values().stream().filter(specialOffer -> specialOffer.getEndDate().isAfter(currentDate) && specialOffer.getPercentage() < client.getFidelityGrade()).toList();
    }

    public void createReservation(Client client, Hotel hotel, LocalDate startDate, LocalDate endDate, Integer noNights) throws RepositoryException {
        reservationDBRepository.add(new Reservation(client.getId(), hotel.getId(), startDate, endDate, noNights));

    }

    public Client findClientById(Integer id) {
        return clientDBRepository.getEntity(Long.valueOf(id));
    }

    public Hotel findHotelById(Double id) {
        return hotelDBRepository.getEntity(Double.valueOf(id));
    }
}
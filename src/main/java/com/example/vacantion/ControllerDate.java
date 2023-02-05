package com.example.vacantion;

import com.example.vacantion.domain.Client;
import com.example.vacantion.domain.Hotel;
import com.example.vacantion.domain.SpecialOffer;
import com.example.vacantion.repository.RepositoryException;
import com.example.vacantion.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControllerDate {



    ObservableList<SpecialOffer> modelSearch = FXCollections.observableArrayList();

    public Service service;

    public Stage stage;

    public Hotel hotel;

    private Client client;

    @FXML
    ListView<SpecialOffer> specialList;

    @FXML
    DatePicker dateStart;

    @FXML
    DatePicker dateEnd;


    @FXML
    private void initialize() {
        final LocalDate[] start = {null};
        final LocalDate[] end = {null};

        specialList.setItems(modelSearch);
        dateStart.valueProperty().addListener((observable, oldValue, newValue) -> {
            start[0] = newValue;
            modelSearch.setAll(getAllSpecialOffersByDateIntervalAndHotelId(start[0], end[0], hotel.getId()));
        });
        dateEnd.valueProperty().addListener((observable, oldValue, newValue) -> {
            end[0] = newValue;
            modelSearch.setAll(getAllSpecialOffersByDateIntervalAndHotelId(start[0], end[0], hotel.getId()));
        });

        specialList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                 int daysBetween = (int) ChronoUnit.DAYS.between(start[0], end[0]);
                try {
                    service.createReservation(client, hotel, start[0], end[0],daysBetween);
                    service.setNotification("Un client cu acelasi hobby ca tine a facut o rezercare la hotelul " + hotel.getHotelName());
                    service.setHobbynotif(client.getHobby().toString());
                    service.setIdnotif(Math.toIntExact(client.getId()));
                } catch (RepositoryException e) {
                    throw new RuntimeException(e);
                }
        });
    }

    private List<SpecialOffer> getAllSpecialOffersByDateIntervalAndHotelId(LocalDate localDate, LocalDate localDate1, Double id) {
        return service.getAllSpecialOffersByDateIntervalAndHotelId(localDate, localDate1, id);
    }

    public void setService(Service service, Stage stage, Hotel hotel, Client client){
        this.service = service;
        this.stage = stage;
        this.hotel = hotel;
        this.client = client;
        modelSearch.setAll(getAllSpecialOffersByDateIntervalAndHotelId(null, null, hotel.getId()));
    }


}

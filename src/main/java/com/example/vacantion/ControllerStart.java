package com.example.vacantion;

import com.example.vacantion.domain.Client;
import com.example.vacantion.domain.Hotel;
import com.example.vacantion.domain.Location;
import com.example.vacantion.domain.SpecialOffer;
import com.example.vacantion.service.Service;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ControllerStart {

    public class MyRunnableTask implements Runnable {
        public void run() {

            Platform.runLater(() -> {
                if(service.getHobbynotif() == client.getHobby().toString() && service.getIdnotif() != client.getId())
                notification.setText(service.getNotification());
            });
        }
    }

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> future = executor.scheduleAtFixedRate(new MyRunnableTask(), 0, 1, TimeUnit.SECONDS);

    ObservableList<Hotel> modelSearch = FXCollections.observableArrayList();
    ObservableList<SpecialOffer> modelSearch1 = FXCollections.observableArrayList();

    public Service service;
    public Stage stage;

    public Client client;
    private LocalDate currentDate;

    @FXML
    TableView<Hotel> hotelList;

    @FXML
    TableColumn<Hotel,String> nameC;

    @FXML
    TableColumn<Hotel,Integer> numC;
    @FXML
    TableColumn<Hotel,Integer> priceC;

    @FXML
    ComboBox<Location> locationComboBox;

    @FXML
    Label numeUtilizator;

    @FXML
    Label notification;

    @FXML
    TableView<SpecialOffer> utilizatorOferte;

    @FXML
    TableColumn<SpecialOffer,String> sphotel;

    @FXML
    TableColumn<SpecialOffer,String> splocatie;

    @FXML
    TableColumn<SpecialOffer,LocalDate> spstart;

    @FXML
    TableColumn<SpecialOffer,LocalDate> spend;


    @FXML
    public void initialize() {
        nameC.setCellValueFactory(new PropertyValueFactory<Hotel, String>("hotelName"));
        numC.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("noOfRooms"));
        priceC.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("pricePerNight"));
        hotelList.setItems(modelSearch);

        //sphotel.setCellValueFactory(new PropertyValueFactory<SpecialOffer, String>("hotelName"));
        //splocatie.setCellValueFactory(new PropertyValueFactory<SpecialOffer, String>("locationName"));
        spstart.setCellValueFactory(new PropertyValueFactory<SpecialOffer, LocalDate>("startDate"));
        spend.setCellValueFactory(new PropertyValueFactory<SpecialOffer, LocalDate>("endDate"));
        utilizatorOferte.setItems(modelSearch1);
        locationComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValueLocation, newValueLocation) -> {
            modelSearch.setAll(getAllHotelsByLocation(newValueLocation));
        });

        hotelList.getSelectionModel().selectedItemProperty().addListener((observable, oldValueHotel, newValueHotel) -> {
            FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("date.fxml"));

            Stage stage1 = new Stage();
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 600);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stage1.setScene(scene);
            stage1.show();

            ControllerDate controller1 = fxmlLoader.getController();
            controller1.setService(service,stage1,newValueHotel,client);

        });
    }

    private List<Hotel> getAllHotelsByLocation(Location newValueLocation) {
        return service.getAllHotelsByLocation(newValueLocation);
    }

    private List<Hotel> getAllHOtels(){
        return service.getAllHotels();
    }

    private List<SpecialOffer> getAllOffersOfClient(){
        return service.getAllOffersOfClient(client.getName(), currentDate);
    }

    public void setService(Service service, Stage stage, Client client, LocalDate currentDate) {
        this.service = service;
        this.stage = stage;
        this.client = client;
        this.currentDate = currentDate;
        numeUtilizator.setText(client.getName());
        modelSearch.setAll(getAllHOtels());
        modelSearch1.setAll(getAllOffersOfClient());
        locationComboBox.setItems(FXCollections.observableArrayList(service.getAllLocations()));

    }
}

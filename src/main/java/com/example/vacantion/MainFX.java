package com.example.vacantion;
import java.io.IOException;
import java.time.LocalDate;

import com.example.vacantion.repository.*;
import com.example.vacantion.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException, RepositoryException {

        LocationDBRepository locationDBRepository = new LocationDBRepository("jdbc:postgresql://localhost:5432/vacantion", "postgres", "razvan");
        HotelDBRepository hotelDBRepository = new HotelDBRepository("jdbc:postgresql://localhost:5432/vacantion", "postgres", "razvan");
        SpecialOfferDBRepository specialOfferDBRepository = new SpecialOfferDBRepository("jdbc:postgresql://localhost:5432/vacantion", "postgres", "razvan");
        ClientDBRepository clientDBRepository = new ClientDBRepository("jdbc:postgresql://localhost:5432/vacantion", "postgres", "razvan");
        ReservationDBRepository reservationDBRepository = new ReservationDBRepository("jdbc:postgresql://localhost:5432/vacantion", "postgres", "razvan");
        Service service = new Service(locationDBRepository, hotelDBRepository, specialOfferDBRepository, clientDBRepository, reservationDBRepository);

        LocalDate currentDate = LocalDate.now();

        service.getAllClients().forEach(client -> {
            FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("start.fxml"));
            Stage stage1 = new Stage();
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1000, 600);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1.setScene(scene);
            stage1.show();

            ControllerStart controller1 = fxmlLoader.getController();
            controller1.setService(service, stage1, client,currentDate);
        });

    }
    public static void main(String[] args) {
        launch();
    }
}
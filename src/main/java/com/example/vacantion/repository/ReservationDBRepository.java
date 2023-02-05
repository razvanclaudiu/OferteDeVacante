package com.example.vacantion.repository;

import com.example.vacantion.domain.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReservationDBRepository implements Repository<Double, Reservation> {

    private final String url;
    private final String username;
    private final String password;

    public ReservationDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Reservation getEntity(Double aDouble) {
        return null;
    }

    @Override
    public Map<Double, Reservation> getAll()  {
        Map<Double, Reservation> reservations = new HashMap<>();
        String sql = "select * from reservations";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double id = resultSet.getDouble("id");
                long clientId = resultSet.getInt("clientid");
                double hotelId = resultSet.getInt("hotelid");
                LocalDate startDate = resultSet.getDate("startcate").toLocalDate();
                LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                int noNights = resultSet.getInt("noNights");
                Reservation reservation = new Reservation(clientId, hotelId, startDate, endDate, noNights);
                reservation.setId(id);
                reservations.put(id, reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void add(Reservation entity) throws RepositoryException {
        String sql = "insert into reservations (clientId, hotelId, startDate, endDate, noNights) values (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, Math.toIntExact(entity.getClientId()));
            ps.setDouble(2, entity.getHotelId());
            ps.setDate(3, Date.valueOf(entity.getStartDate()));
            ps.setDate(4, Date.valueOf(entity.getEndDate()));
            ps.setInt(5, entity.getNoNights());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Double aDouble) throws RepositoryException {

    }

    @Override
    public void update(Reservation entity) throws RepositoryException {

    }
}

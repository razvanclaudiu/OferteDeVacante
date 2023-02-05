package com.example.vacantion.repository;

import com.example.vacantion.domain.Hotel;
import com.example.vacantion.domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SpecialOfferDBRepository implements Repository<Double, SpecialOffer> {

    private final String url;
    private final String username;
    private final String password;

    public SpecialOfferDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public SpecialOffer getEntity(Double aDouble) {
        return null;
    }

    @Override
    public Map<Double, SpecialOffer> getAll() {
        Map<Double, SpecialOffer> specialOffers = new HashMap<>();
        String sql = "select * from specialoffer";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double id = resultSet.getDouble("id");
                double hotelId = resultSet.getDouble("hotel_id");
                LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                int percentage = resultSet.getInt("percentage");
                SpecialOffer specialOffer = new SpecialOffer(hotelId, startDate, endDate, percentage);
                specialOffer.setId(id);
                specialOffers.put(id, specialOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialOffers;
    }

    @Override
    public void add(SpecialOffer entity) throws RepositoryException {

    }

    @Override
    public void remove(Double aDouble) throws RepositoryException {

    }

    @Override
    public void update(SpecialOffer entity) throws RepositoryException {

    }
}

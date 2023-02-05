package com.example.vacantion.repository;

import com.example.vacantion.domain.Hotel;
import com.example.vacantion.domain.Type;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HotelDBRepository implements Repository<Double, Hotel>{

    private final String url;
    private final String username;
    private final String password;

    public HotelDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Hotel getEntity(Double integer)  {
        return null;
    }

    @Override
    public Map<Double,Hotel> getAll()  {
        Map<Double,Hotel> hotels = new HashMap<>();
        String sql = "select * from hotels";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double id = resultSet.getDouble("id");
                String name = resultSet.getString("name");
                Type type = Type.valueOf(resultSet.getString("type"));
                Integer noRooms = resultSet.getInt("noRooms");
                Double pricePerNight = resultSet.getDouble("price");
                Double locationId = resultSet.getDouble("location_id");
                Hotel hotel = new Hotel(locationId,name,noRooms,pricePerNight,type);
                hotel.setId(id);
                hotels.put(id, hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    @Override
    public void add(Hotel entity) {

    }

    @Override
    public void remove(Double integer)  {

    }

    @Override
    public void update(Hotel entity) {

    }
}

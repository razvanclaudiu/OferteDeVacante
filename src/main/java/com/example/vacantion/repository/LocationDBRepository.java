package com.example.vacantion.repository;

import com.example.vacantion.domain.Entity;
import com.example.vacantion.domain.Location;


import java.sql.*;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class LocationDBRepository implements Repository<Double, Location>{

    private final String url;
    private final String username;
    private final String password;

    public LocationDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Location getEntity(Double aDouble)  {
        String sql = "select * from locations where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setDouble(1, aDouble);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                double id = resultSet.getDouble("id");
                String name = resultSet.getString("name");
                Location loc = new Location(name);
                loc.setId(id);
                return loc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Double,Location> getAll()  {
        Map<Double, Location> locations = new HashMap<>();
        String sql = "select * from locations";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double id = resultSet.getDouble("id");
                String name = resultSet.getString("name");
                Location loc = new Location(name);
                loc.setId(id);
                locations.put(id, loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public void add(Location entity) {

    }

    @Override
    public void remove(Double aDouble)  {

    }

    @Override
    public void update(Location entity) {

    }
    






}

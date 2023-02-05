package com.example.vacantion.repository;

import com.example.vacantion.domain.Client;
import com.example.vacantion.domain.Hobbies;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClientDBRepository implements Repository<Long, Client>{

    private final String url;
    private final String username;
    private final String password;

    public ClientDBRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Map<Long,Client> getAll()  {
        Map<Long, Client> clients = new HashMap<>();
        String sql = "select * from clients";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int fidelityGrade = resultSet.getInt("fidelityGrade");
                int age = resultSet.getInt("age");
                String hobby = resultSet.getString("hobby");
                Client client = new Client(name, fidelityGrade, age, Hobbies.valueOf(hobby));
                client.setId(id);
                clients.put(id, client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void add(Client entity) throws RepositoryException {

    }

    @Override
    public void remove(Long aLong) throws RepositoryException {

    }

    @Override
    public void update(Client entity) throws RepositoryException {

    }

    @Override
    public Client getEntity(Long aLong)  {
        String sql = "select * from clients where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, aLong);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int fidelityGrade = resultSet.getInt("fidelityGrade");
                int age = resultSet.getInt("age");
                String hobby = resultSet.getString("hobby");
                Client client = new Client(name, fidelityGrade, age, Hobbies.valueOf(hobby));
                client.setId(id);
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.config.DBConnection;
import com.example.demo.model.User;

@Repository
public class UserRepository {

    @Autowired
    DataSource dbConection;

    public User saveNewUser(User user)  {
        // used try-with-resources format
        try(Connection connection = dbConection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id, name, city, email) VALUES (?, ?, ?, ?)");) {
           // connection = dbConection.getConnection();
           // preparedStatement = connection.prepareStatement("INSERT INTO users (id, name, city, email) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                // System.out.println("User added successfully.");
                user = findUserById(user.getId());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(int id) {
        try (Connection connection = dbConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id =?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery()) {
    
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUserById(int id) {
        User user = null;
        try (Connection connection = dbConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User updateUserByEmail(String name, String city, String email) {
        try (Connection connection = dbConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, city = ?, email = ? WHERE email = ?")) {
            
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findUserByEmail(email);
    }

    public User findUserByEmail(String email) {
        User user = null;
        try(Connection connection = dbConection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("email")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
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

import com.example.demo.model.User;

@Repository
public class UserRepository {

    @Autowired
    DataSource dbConection;

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public User saveNewUser(User user) throws SQLException {
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO users (id, name, city, email) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
        return user;
    }

    public void deleteById(int id) throws SQLException{
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            preparedStatement.close();
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String email = resultSet.getString("email");
                User user = new User(id, name, city, email);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return users;
    }

    public User findUserById(int id) throws SQLException {
        User user = null;
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String email = resultSet.getString("email");
                user = new User(userId, name, city, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }

    public User updateUserByEmail(String name, String city, String email) throws SQLException {
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, city = ? WHERE email = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            preparedStatement.close();
            connection.close();
        }
        return findUserByEmail(email);
    }

    public User findUserByEmail(String email) throws SQLException{
        User user = null;
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String email1 = resultSet.getString("email");
                user = new User(userId, name, city, email1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }

    public User findUserByIdAndEmail(int id, String email) throws SQLException{
        User user = null;
        try {
            connection = dbConection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ? AND email = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(id, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setCity(resultSet.getString("city"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }
}

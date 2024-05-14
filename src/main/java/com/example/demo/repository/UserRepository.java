package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public User saveNewUser(User user){
        try{
            Connection con = dbConection.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO users (id, name, city) VALUES (?, ?, ?)");
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getCity());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
        
    }

    public void deleted(int id){
        try {
            Connection con = dbConection.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM users WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection con = dbConection.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                User user = new User(id, name, city);
                users.add(user);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            Connection con = dbConection.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                user = new User(userId, name, city);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return user;
    }

    public User updateUser(int id,String name,String city) {
        User user = new User(id,name,city);
        try {
            Connection con = dbConection.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE users SET name = ?, city = ? WHERE id = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

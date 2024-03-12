package com.example.app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.app.utils.DBConnection;
import com.example.app.interfaces.Iservice;
import com.example.app.utils.AlertUtil;
import javafx.scene.paint.Color;

public class User implements Iservice {

    private int id;
    private String username;
    private String email;
    private String password;
    private String role;

    private static final Connection connection = DBConnection.getInstance().getConnection();

    public User(String email, String username, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
// Getters and setters



    //to string
    @Override
    public String toString() {
        return String.format("ID: %s | Username: %s | Email: %s | Role: %s", id, username, email, role);
    }
    @Override
    public boolean add(Object o) {
        if (!(o instanceof User)) {
            return false; // Return false if the object is not a User
        }

        User user = (User) o;

        StringBuilder errorMessage = new StringBuilder();

        // Check if email or username already exists
        String query = "SELECT COUNT(*) FROM users WHERE email = ? OR username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        errorMessage.append("Email or username already exists.\n");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }

        // Validate email format
        if (!isValidEmail(user.getEmail())) {
            errorMessage.append("Invalid email format.\n");
        }

        // Validate password format
        if (!isValidPassword(user.getPassword())) {
            errorMessage.append("Invalid password format.\n");
        }

        // If there are any error messages, display them and return false
        if (errorMessage.length() > 0) {
            AlertUtil.showAlert(errorMessage.toString().trim(), Color.RED);
            return false;
        }

        // Insert the user into the database
        query = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
            return false;
        }

        return true; // Return true if the user was successfully added
    }


    @Override
    public void getById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    this.id = resultSet.getInt("id");
                    this.username = resultSet.getString("username");
                    this.email = resultSet.getString("email");
                    this.role = resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }

    @Override
    public void delete(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            String query = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, user.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception
            }
        }
    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public boolean update(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            if ( AnotherUserExists(user)) {
                AlertUtil.showAlert("Email or username already in use.", Color.RED);
                return false;
            }
            String query = "UPDATE users SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getRole());
                statement.setInt(5, user.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[!@#$&*%^.\\-_])(?=\\S+$).{8,}$");
    }

    private boolean emailExists(String email) {
        String query = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return false;
    }

    private boolean usernameExists(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return false;
    }
    private boolean AnotherUserExists(User user) {
        String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND id != ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId()); // Set the user ID
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
            System.out.println(e.getMessage());
        }
        return false;
    }


}

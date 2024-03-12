package com.example.app;

import com.example.app.models.User;
import com.example.app.utils.DBConnection;
import com.example.app.utils.PasswordHasher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {

    @FXML
    private ListView<User> userListView;

    private Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllUsers();
    }

    @FXML
    void loadAllUsers() {
        userListView.getItems().clear();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                userListView.getItems().add(new User(id, username, email, role));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            showErrorAlert("Erreur lors du chargement des utilisateurs", e.getMessage());
        }
    }

    private Dialog<User> createDialog(String title, String headerText, List<String> prompts, String buttonMessage) {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);

        ButtonType addButton = new ButtonType(buttonMessage, ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        List<TextField> textFields = Arrays.asList(
                new TextField(), new TextField(), new PasswordField(), new TextField()
        );

        for (int i = 0; i < textFields.size(); i++) {
            textFields.get(i).setPromptText(prompts.get(i));
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < textFields.size(); i++) {
            grid.addRow(i, new Label(prompts.get(i) + ":"), textFields.get(i));
        }

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> textFields.get(0).requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new User(textFields.get(1).getText(), textFields.get(0).getText(),
                        textFields.get(2).getText(), textFields.get(3).getText());
            }
            return null;
        });

        return dialog;
    }

    @FXML
    void handleAddUser(ActionEvent event) {
        Dialog<User> dialog = createDialog("Ajouter un nouvel utilisateur", null,
                Arrays.asList("Nom d'utilisateur", "Email", "Mot de passe", "Rôle"), "Ajouter");

        Optional<User> result = dialog.showAndWait();
        result.ifPresent(user -> {
            try {
                String hashedPassword = PasswordHasher.passwordHash(user.getPassword());
                user.setPassword(hashedPassword); // Définir le mot de passe haché dans l'objet User
                if (user.add(user)) {
                    loadAllUsers();
                    showInfoAlert("Utilisateur ajouté avec succès");
                }
            } catch (Exception e) {
                showErrorAlert("Erreur lors de l'ajout de l'utilisateur", e.getMessage());
            }
        });
    }

    @FXML
    void handleUpdateUser(ActionEvent event) {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showErrorAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à mettre à jour");
            return;
        }

        // Créer et afficher la boîte de dialogue de mise à jour
        Dialog<User> dialog = createUpdateDialog(selectedUser);
        Optional<User> result = dialog.showAndWait();

        // Traiter le résultat de la boîte de dialogue
        result.ifPresent(user -> {
            user.setId(selectedUser.getId());
            try {
                user.setPassword(PasswordHasher.passwordHash(user.getPassword()));
                if (selectedUser.update(user)) {
                    loadAllUsers();
                    showInfoAlert("Utilisateur mis à jour avec succès");
                }
            } catch (Exception e) {
                showErrorAlert("Erreur lors de la mise à jour de l'utilisateur", e.getMessage());
            }
        });
    }

    private Dialog<User> createUpdateDialog(User user) {
        Dialog<User> dialog = createDialog("Mettre à jour l'utilisateur", null,
                Arrays.asList("Nom d'utilisateur", "Email", "Nouveau mot de passe", "Rôle"), "Mettre à jour");

        // Définir les valeurs initiales pour la mise à jour
        TextField usernameField = (TextField) dialog.getDialogPane().lookup(".text-field");
        TextField emailField = (TextField) dialog.getDialogPane().lookupAll(".text-field").toArray()[1];
        TextField roleField = (TextField) dialog.getDialogPane().lookupAll(".text-field").toArray()[3];
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        roleField.setText(user.getRole());

        return dialog;
    }

    @FXML
    void handleDeleteUser(ActionEvent event) {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                selectedUser.delete(selectedUser);
                userListView.getItems().remove(selectedUser);
                showInfoAlert("Utilisateur supprimé avec succès");
            } catch (Exception e) {
                showErrorAlert("Erreur lors de la suppression de l'utilisateur", e.getMessage());
            }
        } else {
            showErrorAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à supprimer");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Fonctions de validation

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[!@#$&*%^.\\-_])(?=\\S+$).{8,}$");
    }
}

package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.models.Restaurant;
import tn.esprit.services.ServiceRestaurant;

public class UpdateRestaurantController {

    @FXML
    private TextField adresseRes;

    @FXML
    private Button btnConfirmer;

    @FXML
    private TextArea descriptionRes;

    @FXML
    private TextField nomRes;

    @FXML
    private TextField telRes;

    private int id;

    public void setId(int id) {
        this.id = id;
    }


    @FXML
    void confirmerRes(ActionEvent event) {

        Restaurant rest = new Restaurant();
        rest.setNom_Resto(nomRes.getText());
        rest.setAdresse_Resto(adresseRes.getText());
        rest.setTel_Resto(Integer.parseInt(telRes.getText()));
        rest.setDescription(descriptionRes.getText());
        System.out.println("Id     : " + id);

        rest.setId_Resto(id);

        ServiceRestaurant sr = new ServiceRestaurant();
        sr.update(rest);
    }

}

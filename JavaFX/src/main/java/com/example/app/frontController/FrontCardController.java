package com.example.app.frontController;

import com.example.app.models.Facturation;
import com.example.app.models.SceneSwitch;
import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class FrontCardController implements Initializable {
    @FXML
    private ImageView qrcodeImageView;

    @FXML
    private Label DateEmission;

    @FXML
    private VBox cardV;

    @FXML
    private Label montantFacture;

    @FXML
    private Label numFacture;

    @FXML
    private Label status;

    private Facturation F1 ;

    private AnchorPane A;





    @FXML
    void AddRec(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../frontAddRec.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Add_Reclamation addrec=fxmlLoader.getController();
            addrec.setFac(F1);
            addrec.setstage(0);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void giveanchorpane(AnchorPane AP){

         this.A=AP;
    }



    public void setData(Facturation F ) {

        F1=F;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(F.getDate_emission());

        // Extracting day, month, and year
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
        int year = calendar.get(Calendar.YEAR);


        numFacture.setText(String.valueOf(F.getNum_facture()));

        montantFacture.setText(String.valueOf(F.getMontant_tot()));
        DateEmission.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        if (F.isStatus()) {
           status.setText("payé");
        } else {
            status.setText("nonpayé");
        }
    }
    @FXML
    void afficherListRec(ActionEvent event)throws IOException {

        try {
            System.out.println("Value of x: " + F1.getNum_facture());
            // Assuming you have a method in SceneSwitch to switch scenes
            frontListRec.setX(F1.getNum_facture());
            new SceneSwitch(A, "../frontListRec.fxml");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void showPdf(ActionEvent event) {


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(F1.getDate_emission());

        // Extracting day, month, and year
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
        int year = calendar.get(Calendar.YEAR);

        System.out.println(F1.getId_client()+" "+F1.getMontant_tot());
        // Create content list
        List<String> content = new ArrayList<>();
        String etat="";
// Add header row to the content list
        if (F1.isStatus()){
            etat="Payée";
        }else {etat="non-payée";}
        content.add("facture-id     client-id     date-d'emission         etat  ");
        content.add( F1.getNum_facture()+"                   "+F1.getId_client()+"                    "+String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year)+"         "+etat);
        // Create a new PDF document
        PDDocument document = new PDDocument();

// Add a new page to the document
        PDPage page = new PDPage();
        document.addPage(page);

// Create a new font for the header
        PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA) ;

// Add content to the PDF document
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Add the title
            contentStream.beginText();
            contentStream.setFont(font, 36);
            contentStream.newLineAtOffset(50, 650);
            contentStream.showText("votre Facture");
            contentStream.endText();

            // Add the table data
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.newLineAtOffset(50, 600);

            for (int i = 0; i < content.size(); i++) {
                contentStream.showText(content.get(i));
                contentStream.newLineAtOffset(0, -20);
            }

            contentStream.endText();
            contentStream.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

// Save the PDF document to a file or stream


        try {
            document.save("C:/Users/gghar/OneDrive/Bureau/facture.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView finalCardBox = qrcodeImageView;
        qrcodeImageView.setOnMouseEntered(mouseDragEvent -> {

            finalCardBox.setScaleX(finalCardBox.getScaleX() + 0.2);
            finalCardBox.setScaleY(finalCardBox.getScaleY() + 0.2);

            qrcodeImageView.setOnMouseClicked(i -> {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../qrCode.fxml"));
                Parent root = null;
                try {
                    QrCodeController.F1 = this.F1;
                    root = (Parent) fxmlLoader.load();
                    QrCodeController qrCodeController=fxmlLoader.getController();
                    qrCodeController.GenerateQrCode();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }


                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();


            });
        });
        qrcodeImageView.setOnMouseExited(mouseDragEvent -> {

            finalCardBox.setScaleX(finalCardBox.getScaleX() - 0.2);
            finalCardBox.setScaleY(finalCardBox.getScaleY() - 0.2);
        });
    }
}





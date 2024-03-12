package com.example.app.frontController;

import com.example.app.models.Facturation;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeController {

    @FXML
    private ImageView frontQrCode;

    public static Facturation F1;
    // This method generates a QR code based on the provided text
    void GenerateQrCode() throws WriterException, IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(F1.getDate_emission());

        // Extracting day, month, and year
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
        int year = calendar.get(Calendar.YEAR);
        String dateF=String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        System.out.println(dateF);
        String etat="";
        if (F1.isStatus())
        {etat="payée";}else{etat="non payée";}
        String qrCodeText = "Numero de facture :   "+ F1.getNum_facture()+"\n Date D emission : "+ dateF +" \n Montant totale : "+ F1.getMontant_tot() +"   Etat : "+etat;
        String fileName = "JD.png";

        // Assuming the 'images' directory is in the resources folder
        String filePath = "/com/example/app/images/" + fileName;

        int size = 125;
        String fileType = "png";

        // Use resource loading for the file
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        File qrFile = new File("C:\\Users\\khalil\\Desktop\\JavaFX\\src\\main\\resources\\com\\example\\app\\pidev1\\images", fileName);

        // Ensure that the directory structure exists
        qrFile.getParentFile().mkdirs();

        createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println("DONE");
    }

    // This method creates a QR image based on the provided parameters
    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }
}

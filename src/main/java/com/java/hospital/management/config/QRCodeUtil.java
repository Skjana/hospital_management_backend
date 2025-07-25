package com.java.hospital.management.config;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;


public class QRCodeUtil {

    public static byte[] generateQRCodeImageWithHeader(String text, int width, int height, String hospitalName)
            throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        int headerHeight = 50;
        int finalImageHeight = height + headerHeight;
        BufferedImage finalImage = new BufferedImage(width, finalImageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = finalImage.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, finalImageHeight);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(hospitalName);
        int x = (width - textWidth) / 2;
        int y = (headerHeight + fontMetrics.getAscent()) / 2 - 5;
        g.drawString(hospitalName, x, y);
        g.drawImage(qrImage, 0, headerHeight, null);
        g.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(finalImage, "PNG", outputStream);
        return outputStream.toByteArray();
    }

    public static String generateBase64QRCodeWithHeader(String text, int width, int height, String hospitalName)
            throws WriterException, IOException {
        byte[] imageBytes = generateQRCodeImageWithHeader(text, width, height, hospitalName);
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}



package com.java.hospital.management.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmsService {
    private static final String USERNAME = "my_username";
    private static final String API_KEY = "my_textit_api_key";

    public void sendOtpSms(String phoneNumber, String otp) {
        try {
            String message = "Your OTP is " + otp;
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String urlStr = "https://www.textit.biz/sendmsg?id=" + USERNAME
                    + "&pw=" + API_KEY
                    + "&to=" + phoneNumber
                    + "&text=" + encodedMessage;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String responseLine;
            StringBuilder response = new StringBuilder();

            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine);
            }
            in.close();
            System.out.println("SMS Response: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send SMS.");
        }
    }
}

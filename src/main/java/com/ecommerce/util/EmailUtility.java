package com.ecommerce.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtility {

    private static final String EMAIL =
            "";

    private static final String APP_PASSWORD =
            "";

    public static boolean sendOTP(
            String receiverEmail,
            String otp
    ) {

        boolean status = false;

        try {

            Properties properties =
                    new Properties();

            properties.put(
                    "mail.smtp.host",
                    "smtp.gmail.com"
            );

            properties.put(
                    "mail.smtp.port",
                    "587"
            );

            properties.put(
                    "mail.smtp.auth",
                    "true"
            );

            properties.put(
                    "mail.smtp.starttls.enable",
                    "true"
            );

            Session session =
                    Session.getInstance(
                            properties,
                            new Authenticator() {

                                @Override
                                protected PasswordAuthentication
                                getPasswordAuthentication() {

                                    return new PasswordAuthentication(
                                            EMAIL,
                                            APP_PASSWORD
                                    );
                                }
                            }
                    );

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(
                            EMAIL
                    )
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(
                            receiverEmail
                    )
            );

            message.setSubject(
                    "E-Commerce Login OTP"
            );

            message.setText(
                    "Your OTP is: "
                    + otp
            );

            Transport.send(
                    message
            );

            status = true;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public static boolean sendOrderConfirmation(
            String receiverEmail,
            String customerName,
            int orderId,
            String products,
            double totalAmount
    ) {

        boolean status = false;

        try {

            Properties properties =
                    new Properties();

            properties.put(
                    "mail.smtp.host",
                    "smtp.gmail.com"
            );

            properties.put(
                    "mail.smtp.port",
                    "587"
            );

            properties.put(
                    "mail.smtp.auth",
                    "true"
            );

            properties.put(
                    "mail.smtp.starttls.enable",
                    "true"
            );

            Session session =
                    Session.getInstance(
                            properties,
                            new Authenticator() {

                                @Override
                                protected PasswordAuthentication
                                getPasswordAuthentication() {

                                    return new PasswordAuthentication(
                                            EMAIL,
                                            APP_PASSWORD
                                    );
                                }
                            }
                    );

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(
                            EMAIL
                    )
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(
                            receiverEmail
                    )
            );

            message.setSubject(
                    "Order Confirmation #" + orderId
            );

            String emailBody =
                    "Hello "
                    + customerName
                    + ",\n\n"

                    + "Your order has been placed successfully.\n\n"

                    + "Order ID: "
                    + orderId
                    + "\n\n"

                    + "Products:\n"
                    + products
                    + "\n\n"

                    + "Total Amount: ₹"
                    + totalAmount
                    + "\n\n"

                    + "Status: PENDING\n\n"

                    + "Thank you for shopping with us.";

            message.setText(
                    emailBody
            );

            Transport.send(
                    message
            );

            status = true;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public static boolean sendDeliveryNotification(
            String receiverEmail,
            String customerName,
            int orderId
    ) {

        boolean status = false;

        try {

            Properties properties =
                    new Properties();

            properties.put("mail.smtp.host",
                    "smtp.gmail.com");

            properties.put("mail.smtp.port",
                    "587");

            properties.put("mail.smtp.auth",
                    "true");

            properties.put(
                    "mail.smtp.starttls.enable",
                    "true"
            );

            Session session =
                    Session.getInstance(
                            properties,
                            new Authenticator() {

                                @Override
                                protected PasswordAuthentication
                                getPasswordAuthentication() {

                                    return new PasswordAuthentication(
                                            EMAIL,
                                            APP_PASSWORD
                                    );
                                }
                            }
                    );

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(
                            EMAIL
                    )
            );

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(
                            receiverEmail
                    )
            );

            message.setSubject(
                    "Order Delivered #" + orderId
            );

            message.setText(
                    "Hello "
                    + customerName
                    + ",\n\n"

                    + "Your order has been delivered successfully.\n\n"

                    + "Order ID: "
                    + orderId
                    + "\n\n"

                    + "Status: DELIVERED\n\n"

                    + "Thank you for shopping with us."
            );

            Transport.send(
                    message
            );

            status = true;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}

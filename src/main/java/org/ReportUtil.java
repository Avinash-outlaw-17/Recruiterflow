package org;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class ReportUtil {
    public static void sendEmailWithReport(String filePath) throws Exception {
        final String from = "SenderEmail@gmail.com";
        final String password = "Password";
        final String to = "Reciever@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Automation Test Report");

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Test completed. Find the screenshot attached.");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(new File(filePath));
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(filePath);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}

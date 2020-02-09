import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendEmail {
    private static String user = "";
    private static String password = "";
    private static String from = "";
    private static String to = "";
    public static void send() {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try {
            String time = formatter.format(date);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            msg.setSubject("ALERT: Water softener.");
            msg.setText("Water softener is currently undergoing bi-weekly regeneration cycle. " +
                    "Started at " + time + ". ");
            msg.setSentDate(date);
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect("smtp.gmail.com", user, password);
            t.sendMessage(msg, msg.getAllRecipients());
            System.out.println(t.getLastServerResponse());
            t.close();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

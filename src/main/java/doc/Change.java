package doc;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Change extends HttpServlet { String text;
    public void doPost(HttpServletRequest request, HttpServletResponse response)  {
        String email=request.getParameter("email");
        String text_type=request.getParameter("type");
       if(text_type.equals("feed")){
            text="You have got a new idea shared";
        }
        else if(text_type.equals("change")){
           text = " href=https://servlets1.appspot.com/confirm.html?email=" + email ;
       }
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("anywhere@servlets1.appspotmail.com"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email, ""));
            msg.setSubject("Change your password by clicking the following link");
            msg.setText(text);
            Transport.send(msg);
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
            // ...
        } }}

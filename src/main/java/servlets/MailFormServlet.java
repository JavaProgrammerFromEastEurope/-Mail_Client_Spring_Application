package servlets;

import classes.Mail;
import xmlWrapper.ItemWrapper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.lang.System.out;

@WebServlet(name = "MailServlet", urlPatterns = "/MailClient", asyncSupported = true)
public class MailFormServlet extends HttpServlet {

    private static final String PATH = "resources/mail.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("run get method");

        req.getRequestDispatcher("/resources/jsp/mailClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println("run post method");
        Mail mail = initializeVariables(req);
        out.println(Thread.currentThread().getName());
        final AsyncContext asyncContext = req.startAsync(req, resp);

        new Thread(() -> {
            out.println(Thread.currentThread().getName());
            MailFormServlet.sendMail(asyncContext.getRequest(), mail);

            asyncContext.complete();
        }).start();
        doGet(req, resp);
    }

    private static synchronized void sendMail(ServletRequest req, Mail mail) {
        final Properties properties = new Properties();

        properties.put(getProperty("smtpSocketFactory"), getProperty("smtpSslSocketFactory"));
        properties.put(getProperty("smtpSocketFactoryPort"), getProperty("smtpPortNumber"));
        properties.put(getProperty("smtpPort"), getProperty("smtpPortNumber"));
        properties.put(getProperty("smtpAuth"), "true");

        switch (req.getParameter("radio")) {
            case "Google":
                properties.put(getProperty("smtpHost"), getProperty("gmailHost"));
                break;
            case "Yandex":
                properties.put(getProperty("smtpHost"), getProperty("yandexHost"));
                break;
            case "MailRU":
                properties.put(getProperty("smtpHost"), getProperty("mailRuHost"));
                break;
        }

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail.getIndexField("sender", 0),
                        mail.getIndexField("password", 0));
            }
        };
        out.println(mail.getIndexField("sender", 0)
                + " " + mail.getIndexField("password", 0)
                + " " + properties.getProperty(getProperty("smtpHost")));

        Transport transport = null;
        MimeMessage msg;
        MimeMultipart multipart = new MimeMultipart();
        Session session = Session.getDefaultInstance(properties, auth);

        try {
            msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mail.getIndexField("sender", 0)));
            msg.setRecipients(Message.RecipientType.TO, mail.getIndexField("receiver", 0));
            msg.setRecipients(Message.RecipientType.CC, mail.getIndexField("receiver", 0));
            msg.setRecipients(Message.RecipientType.BCC, mail.getIndexField("receiver", 0));

            msg.setSubject(mail.getSubjectMessage(), getProperty("encode"));

            msg.setSentDate(new Date());

            msg.setHeader("Content-Type", "text/plain;charset=" + getProperty("encode"));
            msg.setHeader("Content-Transfer-Encoding", "base64");

            // BODY
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(mail.getBodyMessage(), "text/plain; charset=" + getProperty("encode"));
            multipart.addBodyPart(mbp);
            msg.setContent(multipart);

            transport = session.getTransport("smtp");
            transport.connect();
            transport.sendMessage(msg, msg.getAllRecipients());
            mail.setStatus("true");
        } catch (Exception e) {
            mail.setStatus("false");
            e.printStackTrace();
        } finally {
            MailFormServlet.save(mail);
            try {
                assert false;
                transport.close();
            } catch (MessagingException me) {
                me.printStackTrace();
            }
        }
    }

    private Mail initializeVariables(HttpServletRequest req) {
        Mail mail = new Mail(req.getParameterMap());
        out.println(mail.toString());
        mail.print();
        return mail;
    }

    private static void save(Mail mail) {
        ItemWrapper itemWrapper = new ItemWrapper(mail);
        try {
            JAXBContext context = JAXBContext.newInstance(ItemWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(itemWrapper, new File("mail.xml"));
            out.println("Method XML is working");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
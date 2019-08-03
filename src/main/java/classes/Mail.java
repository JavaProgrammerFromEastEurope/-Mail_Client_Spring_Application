package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.lang.System.out;

@XmlType(propOrder = {"sender", "receiver", "password", "subjectMessage", "bodyMessage", "status"})
public class Mail {

    private String[] sender;
    private String[] receiver;
    private String[] password;
    private String[] subjectMessage;
    private String[] bodyMessage;
    private String status;

    private Map<String, String[]> mail;

    public Mail() {
    }

    public Mail(Map<String, String[]> mail) {
        this.mail = mail;
        initialize(mail);
    }

    private void initialize(Map<String, String[]> mail) {
        mail.forEach((key, value) -> {
            if (key.equals("sender")) {
                setSender(value);
            }
            if (key.equals("receiver")) {
                setReceiver(value);
            }
            if (key.equals("password")) {
                setPassword(value);
            }
            if (key.equals("subjectMessage")) {
                setSubjectMessage(value);
            }
            if (key.equals("bodyMessage")) {
                setBodyMessage(value);
            }
        });
    }

    public void setMail(Map<String, String[]> mail) {
        this.mail = mail;
    }

    public void setSender(String[] sender) {
        this.sender = sender;
    }

    public void setReceiver(String[] receiver) {
        this.receiver = receiver;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public void setSubjectMessage(String[] subjectMessage) {
        this.subjectMessage = subjectMessage;
    }

    public void setBodyMessage(String[] bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "sender")
    public String getSender() {
        return Arrays.toString(sender);
    }

    @XmlElement(name = "receiver")
    public String getReceiver() {
        return Arrays.toString(receiver);
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return Arrays.toString(password);
    }

    @XmlElement(name = "subjectMessage")
    public String getSubjectMessage() {
        return Arrays.toString(subjectMessage);
    }


    @XmlElement(name = "bodyMessage")
    public String getBodyMessage() {
        return Arrays.toString(bodyMessage);
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public String getIndexField(String fieldName, int index) {
        try {
            switch (fieldName) {
                case "sender":
                    return sender[index];
                case "receiver":
                    return receiver[index];
                case "password":
                    return password[index];
                case "subjectMessage":
                    return subjectMessage[index];
                case "bodyMessage":
                    return bodyMessage[index];
                default:
                    return "current field " + fieldName + " is not exist!";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return fieldName + ": - Array Index " + index + " Out Of Bounds";
        }
    }

    public void print() {
        mail.forEach((key, value) ->
                out.println(MessageFormat.format("{0} {1}", key, Arrays.toString(value))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail1 = (Mail) o;
        return Arrays.equals(sender, mail1.sender) &&
                Arrays.equals(receiver, mail1.receiver) &&
                Arrays.equals(password, mail1.password) &&
                Arrays.equals(subjectMessage, mail1.subjectMessage) &&
                Arrays.equals(bodyMessage, mail1.bodyMessage) &&
                Objects.equals(status, mail1.status) &&
                Objects.equals(mail, mail1.mail);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(status, mail);
        result = 31 * result + Arrays.hashCode(sender);
        result = 31 * result + Arrays.hashCode(receiver);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + Arrays.hashCode(subjectMessage);
        result = 31 * result + Arrays.hashCode(bodyMessage);
        return result;
    }

    @Override
    public String toString() {
        return "Mail{"
                + "sender=" + Arrays.toString(sender)
                + ", receiver=" + Arrays.toString(receiver)
                + ", password=" + Arrays.toString(password)
                + ", subjectMessage=" + Arrays.toString(subjectMessage)
                + ", bodyMessage=" + Arrays.toString(bodyMessage)
                + ", status=" + status
                + '}';
    }
}
package classes;

public class TestModel {
    public Mail getMail() {
        Mail mail = new Mail();
        mail.setStatus("active");
        String[] sender = {"Kile", "Mike", "Mikael"};
        String[] receiver = {"Kile", "Mike", "Mikael"};
        String[] password = {"Kile_65", "Mike_98", "Mikael_45"};
        String[] subjMessage = {"one", "two", "three"};
        String[] bodyMessage = {"I", "am", "jerk"};
        mail.setSender(sender);
        mail.setReceiver(receiver);
        mail.setPassword(password);
        mail.setSubjectMessage(subjMessage);
        mail.setBodyMessage(bodyMessage);
        return mail;
    }
}

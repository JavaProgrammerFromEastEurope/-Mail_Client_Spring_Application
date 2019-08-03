package xmlWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import classes.Mail;

@XmlRootElement(name = "Mail")
public class ItemWrapper {

    private Mail mail;

    public ItemWrapper() {}

    public ItemWrapper(Mail mail) {
        this.mail = mail;
    }

    public void setItems(Mail mail) {
        this.mail = mail;
    }

    @XmlElement(name = "mail")
    public Mail getItems() {
        return mail;
    }
}

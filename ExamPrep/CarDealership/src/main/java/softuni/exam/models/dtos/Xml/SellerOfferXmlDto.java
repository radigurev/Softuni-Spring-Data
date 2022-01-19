package softuni.exam.models.dtos.Xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerOfferXmlDto {
    @XmlElement
    private int id;

    public SellerOfferXmlDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

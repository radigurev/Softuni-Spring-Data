package softuni.exam.models.dtos.Xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersRootDto {
    @XmlElement(name = "offer")
    private List<OffersImportDto> offersDto;

    public OffersRootDto() {
    }

    public List<OffersImportDto> getOffersDto() {
        return offersDto;
    }

    public void setOffersDto(List<OffersImportDto> offersDto) {
        this.offersDto = offersDto;
    }
}

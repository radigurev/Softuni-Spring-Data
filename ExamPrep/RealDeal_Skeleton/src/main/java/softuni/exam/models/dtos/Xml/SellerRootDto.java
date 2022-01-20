package softuni.exam.models.dtos.Xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootDto {
    @XmlElement(name = "seller")
    private List<SellerImportDto> sellersDto;

    public SellerRootDto() {
    }

    public List<SellerImportDto> getSellersDto() {
        return sellersDto;
    }

    public void setSellersDto(List<SellerImportDto> sellersDto) {
        this.sellersDto = sellersDto;
    }
}

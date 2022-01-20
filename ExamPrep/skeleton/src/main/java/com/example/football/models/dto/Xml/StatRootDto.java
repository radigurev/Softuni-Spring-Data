package com.example.football.models.dto.Xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatRootDto {
    @XmlElement(name = "stat")
    private List<StatImportDto> statImportDtos;

    public StatRootDto() {
    }

    public List<StatImportDto> getStatImportDtos() {
        return statImportDtos;
    }

    public void setStatImportDtos(List<StatImportDto> statImportDtos) {
        this.statImportDtos = statImportDtos;
    }
}

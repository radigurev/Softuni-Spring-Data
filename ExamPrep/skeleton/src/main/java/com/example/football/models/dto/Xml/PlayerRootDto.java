package com.example.football.models.dto.Xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerRootDto {
        @XmlElement(name = "player")
    List<PlayerImportDto> playerImportDtos;

    public List<PlayerImportDto> getPlayerImportDtos() {
        return playerImportDtos;
    }

    public void setPlayerImportDtos(List<PlayerImportDto> playerImportDtos) {
        this.playerImportDtos = playerImportDtos;
    }
}

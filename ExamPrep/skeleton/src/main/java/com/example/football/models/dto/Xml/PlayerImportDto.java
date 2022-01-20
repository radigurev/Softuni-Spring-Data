package com.example.football.models.dto.Xml;

import com.example.football.config.LocalDateTimeAdapter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDto {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDate birthDate;
    @XmlElement
    private String position;
    @XmlElement
    private PlayerStatRootDto stat;
    @XmlElement
    private PlayerTeamRootDto team;
    @XmlElement
    private PlayerTownRootDto town;

    public PlayerImportDto() {
    }
    @Length(min = 3)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Length(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Pattern(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PlayerStatRootDto getStat() {
        return stat;
    }

    public void setStat(PlayerStatRootDto stat) {
        this.stat = stat;
    }

    public PlayerTeamRootDto getTeam() {
        return team;
    }

    public void setTeam(PlayerTeamRootDto team) {
        this.team = team;
    }

    public PlayerTownRootDto getTown() {
        return town;
    }

    public void setTown(PlayerTownRootDto town) {
        this.town = town;
    }
}

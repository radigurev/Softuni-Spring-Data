package com.example.football.models.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{
    private String name;
    private String StadiumName;
    private int fanBase;
    private String history;
    private Set<Player> players;
    private Town town;
    public Team() {
    }
    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "stadium_name")
    public String getStadiumName() {
        return StadiumName;
    }

    public void setStadiumName(String stadiumName) {
        StadiumName = stadiumName;
    }
    @Column(name ="fan_base")
    public int getFanBase() {
        return fanBase;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }
    @Column(columnDefinition = "TEXT")
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
    @OneToMany(mappedBy = "team")
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    @ManyToOne
    @JoinColumn(name = "town_id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}

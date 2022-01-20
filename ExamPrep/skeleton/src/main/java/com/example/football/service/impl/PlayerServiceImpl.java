package com.example.football.service.impl;

import com.example.football.models.dto.Xml.PlayerImportDto;
import com.example.football.models.dto.Xml.PlayerRootDto;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {
    private final static String PLAYER_PATH="src/main/resources/files/xml/players.xml";

    private final StatRepository statRepository;
    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public PlayerServiceImpl(StatRepository statRepository, PlayerRepository playerRepository, TownRepository townRepository, TeamRepository teamRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.statRepository = statRepository;
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count()>0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PLAYER_PATH)));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder sb=new StringBuilder();
        PlayerRootDto playerRootDto=this.xmlParser.parseXml(PlayerRootDto.class,PLAYER_PATH);
        for (PlayerImportDto playerImportDto : playerRootDto.getPlayerImportDtos()) {
            Optional<Player> byEmail=this.playerRepository.findByEmail(playerImportDto.getEmail());
            if(this.validationUtil.isValid(playerImportDto)&&byEmail.isEmpty()){
                Player player=this.modelMapper.map(playerImportDto,Player.class);
                Town town= townRepository.getByName(playerImportDto.getTown().getTownName());
                System.out.println();
                Team team=this.teamRepository.getByName(playerImportDto.getTeam().getTeamName());
                Stat stat=this.statRepository.getById(playerImportDto.getStat().getId());
                player.setStat(stat);
                player.setTeam(team);
                player.setTown(town);
                this.playerRepository.saveAndFlush(player);
                sb.append(String.format("Successfully imported Player %s %s - %s",player.getFirstName(),player.getLastName(),player.getPosition())).append(System.lineSeparator());
            }else
                sb.append("Invalid Player").append(System.lineSeparator());
        }
        
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb=new StringBuilder();
        Set<Player> players=this.playerRepository.exportPlayers();
        for (Player player : players) {
            sb.append(String.format("Player - %s %s",player.getFirstName(), player.getLastName()))
                    .append(System.lineSeparator())
                    .append(String.format("\tPosition - %s",player.getPosition()))
                    .append(System.lineSeparator())
                    .append(String.format("\tTeam - %s",player.getTeam().getName()))
                    .append(System.lineSeparator())
                    .append(String.format("\tStadium - %s",player.getTeam().getStadiumName()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}

package com.example.football.service.impl;

import com.example.football.models.dto.Json.TeamsImportDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all methods
@Service
public class TeamServiceImpl implements TeamService {
    private static final String TEAM_PATH="src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count()>0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TEAM_PATH)));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb=new StringBuilder();
        TeamsImportDto[] teamsImportDtos=this.gson.fromJson(this.readTeamsFileContent(),TeamsImportDto[].class);
        for (TeamsImportDto teamsImportDto : teamsImportDtos) {
            Optional<Team> byName=this.teamRepository.findByName(teamsImportDto.getName());
            if(this.validationUtil.isValid(teamsImportDto)&&byName.isEmpty()){
                Team team=this.modelMapper.map(teamsImportDto,Team.class);
                team.setTown(this.townRepository.getByName(teamsImportDto.getTownName()));
                this.teamRepository.saveAndFlush(team);
                sb.append(String.format("Successfully imported Team %s - %d",team.getName(),team.getFanBase())).append(System.lineSeparator());
            }else
                sb.append("Invalid Team").append(System.lineSeparator());
        }

        return sb.toString();
    }
}

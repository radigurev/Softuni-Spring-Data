package com.example.football.service.impl;

import com.example.football.models.dto.Json.townsImportDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {
    private final static String TOWN_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TOWN_PATH)));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        townsImportDto[] townsImportDtos = this.gson.fromJson(this.readTownsFileContent(), townsImportDto[].class);
        for (townsImportDto townsImportDto : townsImportDtos) {
            Optional<Town> byName = this.townRepository.findByName(townsImportDto.getName());
            if (this.validationUtil.isValid(townsImportDto) && byName.isEmpty() ) {
                Town town = this.modelMapper.map(townsImportDto, Town.class);
                this.townRepository.saveAndFlush(town);
                sb.append(String.format("Successfully imported Town %s - %d",town.getName(),town.getPopulation())).append(System.lineSeparator());
            }else
                sb.append("Invalid Town").append(System.lineSeparator());
        }


        return sb.toString();
    }
}

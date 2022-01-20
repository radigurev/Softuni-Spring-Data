package com.example.football.service.impl;

import com.example.football.models.dto.Xml.StatImportDto;
import com.example.football.models.dto.Xml.StatRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {
    public static final String STAT_PATH = "src/main/resources/files/xml/stats.xml";

    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final StatRepository statRepository;

    public StatServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, StatRepository statRepository) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.statRepository = statRepository;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(STAT_PATH)));
    }

    @Override
    public String importStats() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        StatRootDto statRootDto = this.xmlParser.parseXml(StatRootDto.class, STAT_PATH);
        for (StatImportDto statImportDto : statRootDto.getStatImportDtos()) {
            Optional<Stat> findByStat = this.statRepository
                    .findByPassingAndShootingAndEndurance(statImportDto.getPassing(), statImportDto.getShooting(), statImportDto.getEndurance());
            if (this.validationUtil.isValid(statImportDto) && findByStat.isEmpty()) {
                Stat stat = this.modelMapper.map(statImportDto, Stat.class);
                this.statRepository.saveAndFlush(stat);
                sb.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f", stat.getShooting(), stat.getPassing(), stat.getEndurance())).append(System.lineSeparator());
            } else
                sb.append("Invalid Stat").append(System.lineSeparator());

        }
        return sb.toString();
    }
}

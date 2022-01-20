package com.example.football.service.impl;

import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {


    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPlayersFileContent()  {
        return null;
    }

    @Override
    public String importPlayers()  {
        return null;
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}

package com.example.football.service;


import java.io.IOException;

//ToDo - Implement all methods
public interface TownService {

    boolean areImported() throws IOException;

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws IOException;

}

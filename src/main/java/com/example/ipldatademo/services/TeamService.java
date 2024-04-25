package com.example.ipldatademo.services;

import com.example.ipldatademo.models.Match;
import com.example.ipldatademo.models.Team;

import java.util.List;

public interface TeamService {
    Team findTeamDetailsByName(String teamName);
    List<Team>findAllTeams();


    List<Match> findAllMatchesByTeamNameAndYear(String teamName, Integer year);
}
package com.example.ipldatademo.services;

import com.example.ipldatademo.models.Match;
import com.example.ipldatademo.models.Team;
import com.example.ipldatademo.repositories.MatchRepository;
import com.example.ipldatademo.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    public Team findTeamDetailsByName(String teamName){
        Pageable getSortedByMatchDate = PageRequest.of(0, 4, Sort.by("date").descending());
        try{
            Team team =  teamRepository.findByTeamName(teamName).orElseThrow(EntityNotFoundException::new);
            List<Match> matches = matchRepository.findFirst3ByTeam1OrTeam2OrderByDateDesc(teamName, getSortedByMatchDate);
            team.setRecentMatches(matches);
            return team;
        }catch(Exception ex){
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Match> findAllMatchesByTeamNameAndYear(String teamName, Integer year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return matchRepository.getAllMatchesByTeamNameAndYear(teamName, startDate, endDate);
    }
}
package com.example.ipldatademo.controllers;

import com.example.ipldatademo.models.Match;
import com.example.ipldatademo.models.Team;
import com.example.ipldatademo.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {


    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping("/teams")
    public ResponseEntity<Iterable<Team>>getAllTeam(){
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @GetMapping("/teams/{teamName}")
    public ResponseEntity<Team> getTeam(@PathVariable(name = "teamName") String teamName) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.findTeamDetailsByName(teamName));
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getTeam(@PathVariable("teamName") String teamName,
                               @RequestParam("year") Integer year) {
        return teamService.findAllMatchesByTeamNameAndYear(teamName, year);
    }


}

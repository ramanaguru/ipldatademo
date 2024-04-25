package com.example.ipldatademo.batch;

import com.example.ipldatademo.models.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    //Step - 2 ==> Create an Intermediate Processor

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);


    @Override
    public Match process(MatchInput matchInput) throws Exception {

        Match match = new Match();

        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(match.getPlayerOfMatch());
        match.setVenue(matchInput.getVenue());

        if("bat".equalsIgnoreCase(matchInput.getToss_decision())){
            match.setTeam1(matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam1() : matchInput.getTeam2());
            match.setTeam2(matchInput.getToss_winner().equals(matchInput.getTeam2()) ? matchInput.getTeam2() : matchInput.getTeam1());
        }
        else{
            match.setTeam1(matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2(): matchInput.getTeam1());
            match.setTeam2(matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam1(): matchInput.getTeam2());
        }

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());


        return match;
    }
}

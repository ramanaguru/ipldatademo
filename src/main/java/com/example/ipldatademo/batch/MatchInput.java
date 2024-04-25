package com.example.ipldatademo.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchInput {

    // Step-1 => Create a Business Class , this is our business class

    private String id;
    private String city;
    private String date;
    private String player_of_match;
    private String venue;
    private String neutral_venue; //Seriously i don't know, .csv file found in internet  so i am mentioning here
    private String team1;
    private String team2;
    private String toss_decision;
    private String toss_winner;
    private String winner;
    private String result;
    private String result_margin;
    private String eliminator;
    private String method;
    private String umpire1;
    private String umpire2;


}

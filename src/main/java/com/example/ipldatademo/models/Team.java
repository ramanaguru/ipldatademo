package com.example.ipldatademo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String teamName;
    private long totalWins;
    private long totalMatches;

    @Transient
    private List<Match> recentMatches; // @Transient ==> to mark a property or field in an entity class as transient. This means that the field or property marked as transient should be excluded when the data persists in the database

    public Team(String teamName, long totalMatches, long l) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }
    @Override
    public String toString() {
        return "Team [teamName=" + teamName + ", totalMatches=" + totalMatches + ", totalWins=" + totalWins + "]";
    }
    public Team() {


    }

}
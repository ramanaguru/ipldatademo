package com.example.ipldatademo.repositories;

import com.example.ipldatademo.models.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.team1 = :teamName OR m.team2 = :teamName")
    List<Match> findFirst3ByTeam1OrTeam2OrderByDateDesc(String teamName, Pageable getSortedByMatchDate);

    @Query("SELECT m FROM Match m WHERE (m.team1 = :teamName OR m.team2 = :teamName) "
            + "AND m.date between :startDate and :endDate order by m.date desc")
    List<Match> getAllMatchesByTeamNameAndYear(String teamName, LocalDate startDate, LocalDate endDate);

}

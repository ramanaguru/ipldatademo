package com.example.ipldatademo.batch;

import com.example.ipldatademo.models.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
public class JobCompletionNotificationListener implements JobExecutionListener {
    //Step - 4 ==> last bit of batch configuration is a way to get notified when the job completes
    //When a method or class is annotated with @Transactional, Spring will manage transactions for the annotated methods or classes.

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamData = new HashMap<>();

            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .forEach(ob -> {
                        String teamName = (String) ob[0];
                        long totalMatches = (long) ob[1];
                        teamData.putIfAbsent(teamName, new Team(teamName, 0L, 0L));
                        Team team = teamData.get(teamName);
                        if (team != null) {
                            team.setTotalMatches(totalMatches);
                        }
                    });

            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(ob -> {
                        String teamName = (String) ob[0];
                        long totalMatches = (long) ob[1];
                        teamData.putIfAbsent(teamName, new Team(teamName, 0L, 0L));
                        Team team = teamData.get(teamName);
                        if (team != null) {
                            team.setTotalMatches(team.getTotalMatches() + totalMatches);
                        }
                    });

            em.createQuery("select m.winner, count(*) from Match m group by m.winner", Object[].class)
                    .getResultList()
                    .forEach(ob -> {
                        String teamName = (String) ob[0];
                        long totalWins = (long) ob[1];
                        teamData.putIfAbsent(teamName, new Team(teamName, 0L, 0L));
                        Team team = teamData.get(teamName);
                        if (team != null) {
                            team.setTotalWins(totalWins);
                        }
                    });

            // Filter out teams with totalMatches = 0
            teamData.values().removeIf(team -> team.getTotalMatches() == 0);

            teamData.values().forEach(team -> {
                em.persist(team);
                System.out.println(team);
            });
        }
    }
}

import React, { useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";
import { PieChart } from 'react-minimal-pie-chart';


import './TeamPage.css';
export const TeamPage =  () => {

    const [team, setTeam] = useState({ recentMatches:[] } );
    const {teamName} = useParams();

    useEffect(
        () => {
            const url = `http://localhost:8080/teams/${teamName}`;
            const fetchTeams  = async() =>{
                const response =await fetch(url)
                const data = await response.json();
                setTeam(data);
            };
            fetchTeams();
        },[teamName] // [empty means-->] Empty dependency array means the effect runs once after the initial render
    );

    if (!team || !team.teamName ){
        return<h1> Team Not Found</h1>
    }


    return (
        <div className="TeamPage">
            <div className="team-name-section">
                <h1 className="team-name">{team.teamName} </h1>
            </div>
            <div className="win-loss-section">
                WON / LOST
                <PieChart
                    className="custom-pie-chart"
                    data={[
                        { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#F41073' },
                        { title: 'Wins', value: team.totalWins, color: '#2DD475' },
                    ]}
                    onMouseOver={(event, segmentIndex, segment) => {
                        event.target.style.transform = 'scale(1.1)'; // Scale up the segment
                    }}
                    onMouseOut={(event, segmentIndex, segment) => {
                        event.target.style.transform = 'scale(1)'; // Reset scale
                    }}
                />
            </div>
            <div className="match-detail-section">
                <h3>Latest Matches</h3>
                <MatchDetailCard  teamName={team.teamName}    match={team.recentMatches[0]}/>
            </div>
            {team.recentMatches.slice(1).map(recentMatches => <MatchSmallCard key={recentMatches.id} teamName={team.teamName}  match={recentMatches}/> )}
            <div className="more-link">
                <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More >></Link>
            </div>

        </div>
    );
}


// export  default TeamPage; ==> avoid spelling  mistakes, rather than I am going to make specific export

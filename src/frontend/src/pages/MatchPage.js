import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import './MatchPage.css';
import { YearSelector } from "../components/YearSelector";

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const [isDataAvailable, setIsDataAvailable] = useState(true); // Flag to check if data is available
    const { teamName, year } = useParams();

    useEffect(() => {
        const url = `http://localhost:8080/teams/${teamName}/matches?year=${year}`;

        const fetchMatches = async () => {
            const response = await fetch(url);
            const data = await response.json();

            if (data && data.length === 0) {
                setIsDataAvailable(false); // Data is not available
            } else {
                setMatches(data);
                setIsDataAvailable(true); // Data is available
            }
        };

        fetchMatches();
    }, [teamName, year]);

    return (
        <div className="MatchPage">
            <div className="year-selector">
                <h3>Select Year</h3>
                <YearSelector teamName={teamName} />
            </div>

            <div>
                <h1 className="page-heading">{teamName} - {year} </h1>

                {/* Conditional rendering based on data availability */}
                {isDataAvailable ? (
                    matches.map(match => <MatchDetailCard key={match.id} teamName={teamName} match={match} />)
                ) : (
                    <div className="year-data-missing">
                        <h3>Oops!</h3>
                        <p>{`${teamName} data was absent for ${year}.`}</p>
                        <p>Maybe they were too busy in practicing! 😉</p>
                    </div>
                )}
            </div>
        </div>
    );
}
import React, {useEffect, useState} from "react";
import { TeamTile } from "../components/TeamTile";
import MumbaiIndiansLogo from "../assets/Mumbai_IndiansLOGO.png";
import PuneWarriorsLogo from "../assets/PuneWarriors.png"
import SrhLogo from "../assets/srh_logo.png"
import RrLogo from "../assets/RRLogo.png"
import KkrLOGO from "../assets/KkrLogo.png"
import RcbLogo from "../assets/RcbLogo.png"
import GLLogo from "../assets/GLlogo.png"
import  KeralaLogo from "../assets/KeralaLogo.png"
import delhiLogo from "../assets/delhiLogo.png"
import punjabLogo from "../assets/KingsPunjabLogo.png"
import deccanLogo from "../assets/DeccanLogo.png"
import risingpuneLogo from "../assets/risingpuneLogo.png"
import csklogo from "../assets/csklogos.png"

import './HomePage.css';


export const HomePage = () => {
    const [teams, setTeams] = useState([]);

    useEffect(() => {
        const url = `http://localhost:8080/teams`;

        const fetchAllTeams = async () => {
            const response = await fetch(url);
            const data = await response.json();

            const teamLogos = {

                "Mumbai Indians": MumbaiIndiansLogo,
                "Pune Warriors": PuneWarriorsLogo,
                "Sunrisers Hyderabad": SrhLogo,
                "Rajasthan Royals": RrLogo,
                "Kolkata Knight Riders": KkrLOGO,
                "Royal Challengers Bangalore": RcbLogo,
                "Gujarat Lions": GLLogo,
                "Kochi Tuskers Kerala": KeralaLogo,
                "Delhi Capitals": delhiLogo,
                "Kings XI Punjab": punjabLogo,
                "Deccan Chargers": deccanLogo,
                "Rising Pune Supergiants": risingpuneLogo,
                "Chennai Super Kings" : csklogo
            };

            const teamsWithImages = data.map(team => ({
                ...team,
                image: teamLogos[team.teamName]
            }));

            setTeams(teamsWithImages);
        };

        fetchAllTeams();
    }, []);

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name"> IPL DashBoard </h1>
            </div>
            <div className="team-grid">
                {teams.map(team => (
                    <TeamTile
                        key={team.teamName}
                        teamName={team.teamName}
                        image={team.image}
                    />
                ))}
            </div>
        </div>
    );
};
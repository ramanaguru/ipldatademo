import React from "react";
import { Link } from "react-router-dom";
import "./TeamTile.css";

export const TeamTile = ({ teamName, image }) => {
    let teamClass = "";

    switch (teamName) {
        case "Chennai Super Kings":
            teamClass = "csk";
            break;
        case "Mumbai Indians":
            teamClass = "Mi";
            break;
        case "Pune Warriors":
            teamClass = "pw";
            break;
        case "Sunrisers Hyderabad":
            teamClass = "srh";
            break;
        case "Rajasthan Royals":
            teamClass = "rr";
            break;
        case "Kolkata Knight Riders":
            teamClass = "kkr";
            break;
        case "Royal Challengers Bangalore":
            teamClass = "rcb";
            break;
        case "Gujarat Lions":
            teamClass = "gl";
            break;
        case "Kochi Tuskers Kerala":
            teamClass = "ktk";
            break;
        case "Delhi Capitals":
            teamClass = "dc";
            break;
        case "Kings XI Punjab":
            teamClass = "kxip";
            break;
        case "Deccan Chargers" :
            teamClass= "deccan";
            break;
        case "Rising Pune Supergiants":
            teamClass = "rps";
            break;
        default:
            teamClass = "default";
            break;
    }

    return (
        <Link to={`/teams/${teamName}`} className={`TeamTile ${teamClass}`}>
            <div className="image-wrapper shine">
                {image && <img src={image} alt={`${teamName} logo`} className="team-logo" />}
            </div>
            <h1>{teamName}</h1>
        </Link>
    );
}
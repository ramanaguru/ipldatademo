import './App.css';
import {BrowserRouter as Router, Routes, Route, Navigate,} from 'react-router-dom'
import {TeamPage} from "./pages/TeamPage";
import {MatchPage} from "./pages/MatchPage";
import {HomePage} from "./pages/HomePage";

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path = "/teams/:teamName" element={<TeamPage />} />
                    <Route path= "/teams/:teamName/matches/:year" element={<MatchPage/>} />
                    <Route path = "/teams" element={<HomePage/>} />
                    <Route path="/" element={<Navigate to="/teams" />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
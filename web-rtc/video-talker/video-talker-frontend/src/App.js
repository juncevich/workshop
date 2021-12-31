import './App.css';
import {useEffect} from "react";
import {connectWithWebSocket} from "./util/wssConnection/wssConnection";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Dashboard from "./pages/dashboard/Dashboard";
import LoginPage from "./pages/login/LoginPage";


function App() {

    useEffect(() => {
        connectWithWebSocket();
    }, [])
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/dashboard" element={<Dashboard/>}/>
                <Route path="/login" element={<LoginPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;

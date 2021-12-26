import React from 'react';
import './App.css';
import Nav from "./components/Nav";
import Menu from "./components/Menu";
import Dashboard from "./pages/Dashboard";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Users from "./pages/Users";
import Register from "./pages/Register";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Route path="/"  element={<Dashboard/>}/>
                <Route path="/users" element={<Users/>}/>
                <Route path="/register" element={<Register/>}/>

            </BrowserRouter>
        </div>
    );
}

export default App;

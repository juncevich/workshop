import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Route} from "react-router-dom";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Nav from "./components/Nav";
import axios from "axios";

function App() {

    const [user, setUser] = useState(null)
    useEffect(() => {
        (
            async () => {
                try {
                    const response = await axios.get('user');

                    const user = response.data;
                    setUser(user)
                } catch (e) {

                }
            }
        )()
    }, [])

    return (
        <div className="App">

            <BrowserRouter>
                <Nav user={{user}}/>

                <Route path="/" exact component={() => <Home user={user}/>}/>
                <Route path="/login" component={Login}/>
                <Route path="/register" component={Register}/>
            </BrowserRouter>
        </div>
    );
}

export default App;

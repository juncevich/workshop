import React from 'react';
import ReactDOM from 'react-dom';
import Routes from 'routes'
import {BrowserRouter as Router} from 'react-router-dom'
import TopBar from 'components/topBar'
const App = () => {
    return (
        <div>
            <Router>
                <TopBar/>
                <Routes/>
            </Router>
        </div>
    )
}
ReactDOM.render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>,
    document.getElementById('root')
);


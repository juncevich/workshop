import React from 'react';
import './App.css';

function App() {
    return (
        <div className="App">
            <form className="form-signin">
                <h1 className="h3 mb-3 font-weight-normal">Please sign in</h1>
                <input type="email" className="form-control" placeholder="Email" required/>
                <input type="password" className="form-control" placeholder="Password" required/>
                <button className="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
    );
}

export default App;

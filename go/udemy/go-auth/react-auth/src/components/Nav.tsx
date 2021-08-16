import React from 'react';
import {Link} from 'react-router-dom';
import axios from "axios";

const Nav = ({user}: { user: any }) => {
    let links;
    const logout = async () => {
        await axios.post('logout', {})
    }

    if (user) {
        links = (
            <ul className="navbar-nv my-2 my-lg-0">
                <li className="nav-item">
                    <Link to="/" onClick={logout} className="nav-link">Logout</Link>
                </li>
            </ul>
        )
    } else {
        links = (
            <ul className="navbar-nv my-2 my-lg-0">
                <li className="nav-item">
                    <Link to="/login" className="nav-link">Login</Link>
                </li>
                <li className="nav-item">
                    <Link to="/register" className="nav-link">Register</Link>
                </li>

            </ul>
        )

    }
    return (
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <ul className="navbar-nv mr-auto">
                <li className="nav-item">
                    <Link to="/" className="nav-link">Home</Link>
                </li>
            </ul>
            {links}
        </nav>
    );
};

export default Nav;
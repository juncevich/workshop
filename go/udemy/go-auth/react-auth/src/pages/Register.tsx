import React from 'react';

const Login = () => {
    return (
        <form className="form-signin">
            <h1 className="h3 mb-3 font-weight-normal">Please register</h1>
            <input  className="form-control" placeholder="First Name" required/>
            <input  className="form-control" placeholder="Last Name" required/>
            <input type="email" className="form-control" placeholder="Email" required/>
            <input type="password" className="form-control" placeholder="Password" required/>
            <input type="password" className="form-control" placeholder="Password Confirm" required/>
            <button className="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        </form>
    );
};

export default Login;
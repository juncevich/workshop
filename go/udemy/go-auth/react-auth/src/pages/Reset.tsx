import React, {SyntheticEvent, useState} from 'react';
import axios from "axios";
import {Redirect} from "react-router-dom";

const Reset = ({match}: { match: any }) => {
    const [password, setPassword] = useState('')
    const [passwordConfirm, setConfirmPassword] = useState('')
    const [redirect, setRedirect] = useState(false)

    const submit = async (e: SyntheticEvent) => {
        e.preventDefault()

        const token = match.params.token

        await axios.post('reset', {
            token,
            password,
            password_confirm: passwordConfirm
        })

        setRedirect(true)
    }

    if (redirect) {
        return <Redirect to={"/login"}/>
    }
    return (
        <form className="form-signin" onSubmit={submit}>
            <h1 className="h3 mb-3 font-weight-normal">Reset your password</h1>
            <input type="password" className="form-control mb-3" placeholder="Password" required
                   onChange={e => setPassword(e.target.value)}/>
            <input type="password" className="form-control mb-3" placeholder="Password confirm" required
                   onChange={e => setConfirmPassword(e.target.value)}/>
            <button className="btn btn-lg btn-primary btn-block" type="submit">Reset password</button>
        </form>
    );
};

export default Reset;

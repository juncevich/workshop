import React, {Dispatch, useEffect, useState} from 'react';
import Nav from "./Nav";
import Menu from "./Menu";
import axios from "axios";
import {Navigate} from "react-router-dom";
import {User} from "../models/User";
import {setUser} from "../redux/actions/setUserAction";
import {connect} from "react-redux";

const Wrapper = (props: any) => {
    const [redirect, setRedirect] = useState(false)

    useEffect(() => {
        (
            async () => {
                try {
                    const {data} = await axios.get('user');

                    props.setUser(new User(
                        data.id,
                        data.first_name,
                        data.last_name,
                        data.email,
                        data.role
                    ));
                } catch (e) {
                    setRedirect(true)
                }
            }
        )()
    }, [])

    if (redirect) {
        return <Navigate to="/login" replace/>;
    }
    return (
        <>
            <Nav/>
            <div className="container-fluid">
                <div className="row">
                    <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <Menu/>

                        {props.children}
                    </main>
                </div>
            </div>
        </>
    );

}
const mapStateToProps = (state: { user: User }) => {
    return {
        user: state.user
    };
}

const mapDispatchToProps = (dispatch: Dispatch<any>) => {
    return {
        setUser: (user: User) => dispatch(setUser(user))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Wrapper);
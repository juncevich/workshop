import React, {useEffect} from 'react';
import logo from '../../resources/logo.png';
import ActiveUsersList from './components/ActiveUserList';
import * as webRTCHandler from '../../util/webRTC/webRTCHandler';
import * as webRTCGroupHandler from '../../util/webRTC/webRTCGroupCallHandler';

import './Dashboard.css';
import DirectCall from "./components/DirectCall";
import DashboardInformation from "./components/DashboardInformation/DashboardInformation";
import {connect} from "react-redux";
import GroupCallRoomsList from "./components/GroupCallRoomsList/GroupCallRoomsList";

const Dashboard = ({username, callState}) => {
    useEffect(() => {
        webRTCHandler.getLocalStream();
        webRTCGroupHandler.connectWithMyPeer();
    }, []);

    return (
        <div className='dashboard_container background_main_color'>
            <div className='dashboard_left_section'>
                <div className='dashboard_content_container'>
                    <DirectCall/>
                    {callState !== callStates.CALL_IN_PROGRESS && <DashboardInformation username={username}/>}
                </div>
                <div className='dashboard_rooms_container background_secondary_color'>
                    <GroupCallRoomsList/>
                </div>
            </div>
            <div className='dashboard_right_section background_secondary_color'>
                <div className='dashboard_active_users_list'>
                    <ActiveUsersList/>
                </div>
                <div className='dashboard_logo_container'>
                    <img alt="" className='dashboard_logo_image' src={logo}/>
                </div>
            </div>
        </div>
    );
};

const mapStateToProps = ({call, dashboard}) => ({
    ...call,
    ...dashboard
});

export default connect(mapStateToProps)(Dashboard);

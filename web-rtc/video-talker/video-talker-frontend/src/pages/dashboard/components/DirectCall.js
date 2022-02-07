import React from 'react';
import {connect} from 'react-redux';
import LocalVideoView from './LocalVideoView';
import RemoteVideoView from './RemoteVideoView';
import CallingDialog from "./CallingDialog/CallingDialog";
import IncomingCallDialog from "./IncomingDialog/IncomingCallDialog";
import {callStates} from "../../../store/actions/callActions";

const DirectCall = (props) => {
    const {localStream, remoteStream, callState, callerUsername, callingDialogVisible} = props;

    return (
        <>
            <LocalVideoView localStream={localStream}/>
            {remoteStream && <RemoteVideoView remoteStream={remoteStream}/>}
            {/* <CallRejectedDialog /> */}
            {callState === callStates.CALL_REQUESTED && <IncomingCallDialog callerUsername={callerUsername}/>}
            {callingDialogVisible && <CallingDialog/>}
        </>
    );
};

function mapStoreStateToProps({call}) {
    return {
        ...call
    };
}

export default connect(mapStoreStateToProps, null)(DirectCall);


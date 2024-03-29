import React from 'react';
import LocalVideoView from './LocalVideoView';
import RemoteVideoView from './RemoteVideoView';
import CallingDialog from "./CallingDialog/CallingDialog";
import IncomingCallDialog from "./IncomingDialog/IncomingCallDialog";
import {
    callStates,
    setCallRejected,
    setLocalCameraEnabled,
    setLocalMicrophoneEnabled
} from "../../../store/actions/callActions";
import CallRejectedDialog from "./CallRejectedDialog/CallRejectedDialog";
import ConversationButtons from "./ConversationButtons/ConversationButtons";
import {connect} from "react-redux";
import Messenger from "./Messenger/Messenger";

const DirectCall = (props) => {
    const {
        localStream,
        remoteStream,
        callState,
        callerUsername,
        callingDialogVisible,
        callRejected,
        hideCallRejectedDialog,
        setDirectCallMessage
    } = props;

    return (
        <>
            <LocalVideoView localStream={localStream}/>
            {remoteStream && callState === callStates.CALL_IN_PROGRESS &&
                <RemoteVideoView remoteStream={remoteStream}/>}
            {callRejected.rejected && <CallRejectedDialog
                reason={callRejected.reason}
                hideCallRejectedDialog={hideCallRejectedDialog}
            />}
            {callState === callStates.CALL_REQUESTED && <IncomingCallDialog callerUsername={callerUsername}/>}
            {callingDialogVisible && <CallingDialog/>}
            {remoteStream && callState === callStates.CALL_IN_PROGRESS && <ConversationButtons {...props} />}
            {remoteStream && callState === callStates.CALL_IN_PROGRESS
                && <Messenger message={message} setDirectCallMessage={setDirectCallMessage}/>}
        </>
    );
};

function mapStoreStateToProps({call}) {
    return {
        ...call
    };
}

function mapDispatchToProps(dispatch) {
    return {
        hideCallRejectedDialog: (callRejectedDetails) => dispatch(setCallRejected(callRejectedDetails)),
        setCameraEnabled: (enabled) => dispatch(setLocalCameraEnabled(enabled)),
        setMicrophoneEnabled: (enabled) => dispatch(setLocalMicrophoneEnabled(enabled)),
        setDirectCallMessage: (received, content) => dispatch(setMessage(received, content))
    };
}

export default connect(mapStoreStateToProps, mapDispatchToProps)(DirectCall);


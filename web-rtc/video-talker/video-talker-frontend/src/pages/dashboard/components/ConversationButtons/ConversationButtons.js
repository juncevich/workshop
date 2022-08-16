import React from 'react';
import {MdCallEnd, MdMic, MdMicOff, MdVideocam, MdVideocamOff, MdVideoLabel, MdCamera} from 'react-icons/md'
import ConversationButton from "./ConversationButton";
import {switchForScreenSharingStream} from "../../../../util/webRTC/webRTCHandler";

const styles = {
    buttonContainer: {
        display: 'flex',
        position: 'absolute',
        bottom: '22%',
        left: '35%'
    },
    icon: {
        width: '25px',
        height: '25px',
        fill: '#e6e5e8'
    }
}

const ConversationButtons = (props) => {
    const {
        localStream,
        localCameraEnabled,
        localMicrophoneEnabled,
        setLocalCameraEnabled,
        setLocalMicrophoneEnabled,
        screenSharingActive
    } = props

    const handleMicButtonPressed = () => {
        const micEnabled = localMicrophoneEnabled
        localStream.getAudioTracks()[0].enabled = !micEnabled
        setLocalMicrophoneEnabled(!micEnabled)
    }

    const handleCameraButtonPressed = () => {
        const cameraEnabled = localCameraEnabled
        localStream.getVideoTracks()[0].enabled = !cameraEnabled
        setLocalCameraEnabled(!cameraEnabled)
    }

    const handleScreenSharingButtonPressed = () => {
        switchForScreenSharingStream()
    }

    return (
        <div style={styles.buttonContainer}>
            <ConversationButton onClickHandler={handleMicButtonPressed}>
                {localMicrophoneEnabled ? <MdMic style={styles.icon}/> : <MdMicOff style={styles.icon}/>}
            </ConversationButton>
            <ConversationButton>
                <MdCallEnd style={styles.icon}/>
            </ConversationButton>
            <ConversationButton onClickHandler={handleCameraButtonPressed}>
                {localCameraEnabled ? <MdVideocam style={styles.icon}/> : <MdVideocamOff style={styles.icon}/>}
            </ConversationButton>
            <ConversationButton onClickHandler={handleScreenSharingButtonPressed}>
                {screenSharingActive ? <MdCamera style={styles.icon}/> : <MdVideoLabel style={styles.icon}/>}
            </ConversationButton>
        </div>
    );
};

export default ConversationButtons;

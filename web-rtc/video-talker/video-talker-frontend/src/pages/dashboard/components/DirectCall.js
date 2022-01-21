import React from 'react';
import { connect } from 'react-redux';
import LocalVideoView from './LocalVideoView';
import RemoteVideoView from './RemoteVideoView';

const DirectCall = (props) => {
  const { localStream, remoteStream } = props;

  return (
      <>
        <LocalVideoView localStream={localStream} />
        {remoteStream && <RemoteVideoView remoteStream={remoteStream} />}
        {/* <CallRejectedDialog /> */}
        {/* <IncomingCallDialog /> */}
        {/* <CallingDialog /> */}
      </>
  );
};

function mapStoreStateToProps ({ call }) {
  return {
    ...call
  };
}

export default connect(mapStoreStateToProps, null)(DirectCall);

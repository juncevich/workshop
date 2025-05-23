import React from 'react';

const Home = ({user}: { user: any }) => {
    let message;
    if (message) {
        message = `Hi ${user.first_name} ${user.last_name}`
    } else {
        message = "You aer not logged in!"
    }

    return (
        <div className="container">
            <h1>{message}</h1>
        </div>
    );
};

export default Home;

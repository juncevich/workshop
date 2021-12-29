export const DASHBOARD_SET_USER_NAME = "DASHBOARD.SET_USERNAME";

export const setUsername = (username) => {
    return {
        type: DASHBOARD_SET_USER_NAME,
        username
    };
};

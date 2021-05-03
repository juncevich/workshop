import axios from 'axios'

const LandingPage = ({currentUser}) => {
    console.log('I am in the component', color)
    return <h1>Landing Page</h1>;
};

LandingPage.getInitialProps = async () => {
    if (typeof window === 'undefined') {
        const {data} = await axios.get(
            'https://ingress-nginx.ingress-inginx.svc.cluster.local/api/users/currentuser',
            {
                headers: req.headers
            }
        );
        return data;
    } else {
        const {data} = await axios.get('/api/users/currentuser');
        return data;
    }


}

export default LandingPage;

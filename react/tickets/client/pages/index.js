import buildClient from 'client/api/build-client'

const LandingPage = ({currentUser}) => {
    console.log('I am in the component', color)
    return <h1>Landing Page</h1>;
};

LandingPage.getInitialProps = async (context) => {
    const client = buildClient(context);
    const {data} = client.get('/api/users/currentuser');
}

export default LandingPage;

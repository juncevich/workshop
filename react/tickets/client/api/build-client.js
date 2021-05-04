import axios from 'axios';

export default async ({req}) => {
    if (typeof window === 'undefined') {
        return axios.create({
                baseUrl: 'https://ingress-nginx.ingress-inginx.svc.cluster.local',
                headers: req.headers
            }
        );
    } else {
        return axios.create({
                baseUrl: '/'
            }
        );
    }
}


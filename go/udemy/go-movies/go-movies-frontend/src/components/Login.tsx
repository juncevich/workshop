import {useState} from "react";
import Input from '../components/form/Input';
import {useNavigate, useOutletContext} from "react-router-dom";

const Login = () => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const {setJwtToken}: any = useOutletContext()
    const {setAlertMessage}: any = useOutletContext()
    const {setAlertClassName}: any = useOutletContext()
    const {toggleRefresh}: any = useOutletContext()

    const navigate = useNavigate()
    const handleSubmit = (event: any) => {
        event.preventDefault();

        let payload = {
            email: email,
            password: password
        }

        const requestOptions: RequestInit = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(payload)
        }

        fetch(`/authenticate`, requestOptions)
            .then(response => response.json())
            .then((data) => {
                if (data.error) {
                    setAlertMessage(data.message)
                    setAlertClassName("alert-danger")
                } else {
                    setJwtToken(data.token)
                    setAlertMessage("")
                    setAlertClassName("none")
                    toggleRefresh(true)
                    navigate("/")
                }
            })
            .catch(error => {
                setAlertMessage(error)
                setAlertClassName("alert-danger")
            })
    }
    return (
        <div className="col-md6 offset-md-3">
            <h2>Login</h2>
            <hr/>

            <form onSubmit={handleSubmit}>
                <Input title="Email Address"
                       type="email"
                       className="form-control"
                       name="email"
                       autoComplete="email-new"
                       onChange={(event: any) => setEmail(event.target.value)}
                />
                <Input title="Password"
                       type="password"
                       className="form-control"
                       name="password"
                       autoComplete="password-new"
                       onChange={(event: any) => setPassword(event.target.value)}
                />

                <hr/>
                <input type="submit" className="btn btn-primary" value="Login"/>
            </form>
        </div>
    )
}

export default Login;

import {useState} from "react";
import Input from '../components/form/Input';
import {useNavigate, useOutletContext} from "react-router-dom";

const Login = () => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const {setJwtToken}: any = useOutletContext()
    const {setAlertMessage}: any = useOutletContext()
    const {setAlertClassName}: any = useOutletContext()

    const navigate = useNavigate()
    const handleSubmit = (event: any) => {
        event.preventDefault();

        console.log("email/password", email, password);
        if (email === "admin@example.com") {
            setJwtToken("asdf");

            setAlertClassName("d-none")
            setAlertMessage("");
            navigate("/");
        } else {
            setAlertClassName("alert alert-danger")
            setAlertMessage("Invalid credentials");
        }
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
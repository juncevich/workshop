import {ChangeEventHandler, useEffect, useState} from "react";
import {useNavigate, useOutletContext, useParams} from "react-router-dom";
import Input from "./form/Input";
import any = jasmine.any;

const EditMovie = () => {

    const navigate = useNavigate();
    const {jwtToken}: any = useOutletContext()

    const hasError = (key: string) => {
        return  errors.indexOf(key) !== -1;
    }
    const [error, setError] = useState(null);
    const [errors , setErrors] = useState([""]);
    const [movie, setMovie] = useState({
        id: 0,
        title: "",
        release_date: "",
        runtime: "",
        mpaa_rating: "",
        description: ""
    });

    let {id} = useParams();

    useEffect(() => {
            if (jwtToken === "") {
                navigate("/login");
                return;
            }
        },
        [jwtToken, navigate])

    const handleSubmit = (event: any) => {
        event.preventDefault();
    }

    const handleChange = () => (event:any) => {
        let value = event.target.value;
        let name = event.target.name;
        setMovie({
            ...movie,
            [name]: value
        });
    }
    return (
        <div>
            <h2>Add/Edit movie</h2>
            <hr/>

            <pre>{JSON.stringify(movie, null, 3)}</pre>

            <form onSubmit={handleSubmit}>
                <input type="hidden" name="id" value={movie.id} id="id"></input>

                <Input
                    name={"title"}
                    title={"Title"}
                    type={"text"}
                    className={"form-control"}
                    onChange={handleChange("title")}
                    value={movie.title}
                    errorDiv={hasError("title") ? "text-danger" : "d-none"}
                    errorMsg={"Please enter a title"}
                    autoComplete={false}
                />
            </form>
        </div>
    )
}

export default EditMovie;

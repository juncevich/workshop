import React, {useEffect, useState} from "react";
import {useNavigate, useOutletContext, useParams} from "react-router-dom";
import Input from "./form/Input";
import Select from "./form/Select";
import TextArea from "./form/TextArea";

const EditMovie = () => {

    const navigate = useNavigate();
    const {jwtToken}: any = useOutletContext()

    const hasError = (key: string) => {
        return errors.indexOf(key) !== -1;
    }
    const [error, setError] = useState(null);
    const [errors, setErrors] = useState<string[]>([]);
    const [movie, setMovie] = useState({
        id: 0,
        title: "",
        release_date: "",
        runtime: "",
        mpaa_rating: "",
        description: ""
    });

    const mpaaOptions = [
        {id: "G", value: "G"},
        {id: "PG", value: "PG"},
        {id: "PG13", value: "PG13"},
        {id: "R", value: "R"},
        {id: "NC17", value: "NC17"},
        {id: "18A", value: "18A"},
    ]
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

    const handleChange = (event: any) => (event: any) => {
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

                <Input
                    title={"Release date"}
                    className={"form-control"}
                    type={"date"}
                    name={"release_date"}
                    value={movie.release_date}
                    onChange={handleChange("release_date")}
                    errorDiv={hasError("release_date") ? "text-danger" : "d-none"}
                    errorMsg={"Please enter a release date"}
                    autoComplete={false}
                />

                <Input
                    title={"Runtime"}
                    className={"form-control"}
                    type={"text"}
                    name={"runtime"}
                    value={movie.runtime}
                    onChange={handleChange("runtime")}
                    errorDiv={hasError("runtime") ? "text-danger" : "d-none"}
                    errorMsg={"Please enter a runtime"}
                    autoComplete={false}
                />

                <Select
                    title={"MPAA rating"}
                    name={"mpaa_rating"}
                    options={mpaaOptions}
                    onChange={handleChange("mpaa_rating")}
                    placeholder={"Choose ..."}
                    errorMsg={"Please choose"}
                    errorDiv={hasError("mpaa_rating") ? "text-danger" : "d-none"}
                />

                <TextArea
                    title={"Description"}
                    name={"description"}
                    value={movie.description}
                    rows={3}
                    onChange={handleChange("description")}
                    errorMsg={"Please enter a description"}
                    errorDiv={hasError("description") ? "text-danger" : "d-none"}
                />

            </form>
        </div>
    )
}

export default EditMovie;

import React, {useEffect, useState} from "react";
import {useNavigate, useOutletContext, useParams} from "react-router-dom";
import Input from "./form/Input";
import Select from "./form/Select";
import TextArea from "./form/TextArea";
import MovieType from "../types/Movie";
import Movie from "../types/Movie";
import Genres from "./Genres";
import Genre from "../types/Genre";
import Checkbox from "./form/Checkbox";

const EditMovie = () => {

    const navigate = useNavigate();
    const {jwtToken}: any = useOutletContext()

    const hasError = (key: string) => {
        return errors.indexOf(key) !== -1;
    }
    const [error, setError] = useState("");
    const [errors, setErrors] = useState<string[]>([]);
    const [movie, setMovie] = useState<MovieType>(
        {
            id: 0,
            title: "",
            release_date: "",
            runtime: 0,
            mpaa_rating: "",
            description: "",
            image: "",
            genres: [],
            genres_array: [],
        }
    );

    const mpaaOptions = [
        {id: "G", value: "G"},
        {id: "PG", value: "PG"},
        {id: "PG13", value: "PG13"},
        {id: "R", value: "R"},
        {id: "NC17", value: "NC17"},
        {id: "18A", value: "18A"},
    ]
    let {id} = useParams();

    if (id === undefined) {
        id = "0";
    }

    useEffect(() => {
            if (jwtToken === "") {
                navigate("/login");
                return;
            }

            if (id === "0") {
                setMovie({
                    id: 0,
                    title: "",
                    release_date: "",
                    runtime: 0,
                    mpaa_rating: "",
                    description: "",
                    image: "",
                    genres: [],
                    genres_array: Array<boolean>(13).fill(false),
                })

                const headers = new Headers();
                headers.append("Content-Type", "application/json");

                const requestOptions = {
                    method: 'GET',
                    headers: headers,
                };

                fetch("/genres", requestOptions)
                    .then(response => response.json())
                    .then(data => {
                        const checks: Array<Genre> = [];

                        data.forEach((g: Genre) => {
                            checks.push({id: g.id, checked: false, genre: g.genre})
                        })

                        setMovie(m => ({
                            ...m,
                            genres: checks,
                            genres_array: [],
                        }))
                    }).catch(error => {
                    console.log(error);
                })
            } else {
                // editing an existing movie
                const headers = new Headers();
                headers.append("Content-Type", "application/json");
                headers.append("Authorization", "Bearer " + jwtToken);

                const requestOptions = {
                    method: "GET",
                    headers: headers,
                }

                fetch(`/admin/movies/${id}`, requestOptions)
                    .then((response) => {
                        if (response.status !== 200) {
                            setError("Invalid response code: " + response.status)
                        }
                        return response.json();
                    })
                    .then((data) => {
                        // fix release date
                        data.movie.release_date = new Date(data.movie.release_date).toISOString().split('T')[0];

                        const checks: Genre[] = [];

                        for (const g of data.genres) {
                            if (data.movie.genres_array.indexOf(g.id) !== -1) {
                                checks.push({id: g.id, checked: true, genre: g.genre});
                            } else {
                                checks.push({id: g.id, checked: false, genre: g.genre});
                            }
                        }

                        // set state
                        setMovie({
                            ...data.movie,
                            genres: checks,
                        })
                    })
                    .catch(err => {
                        console.log(err);
                    })
            }
        },
        [id, jwtToken, navigate])

    const handleSubmit = (event: any) => {
        event.preventDefault();

        let errors: Array<string> = [];
        let required = [
            {field: movie.title, name: "title"},
            {field: movie.release_date, name: "release_date"},
            {field: movie.runtime, name: "runtime"},
            {field: movie.description, name: "description"},
            {field: movie.mpaa_rating, name: "mpaa_rating"},
        ]

        if (movie.genres_array.length === 0) {
            alert("You must select at least one genre");
            errors.push("genres")
        }

        required.forEach(function (obj) {
            if (obj.field === "") {
                errors.push(obj.name);
            }
        })

        setErrors(errors);

        if (errors.length > 0) {
            return false
        }

        // passed validation, so save changes
        const headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("Authorization", "Bearer " + jwtToken);

        // assume we are adding a new movie
        let method = "PUT";

        if (movie.id > 0) {
            method = "PATCH";
        }

        const requestBody = movie;
        // we need to covert the values in JSON for release date (to date)
        // and for runtime to int

        requestBody.release_date = movie.release_date;
        requestBody.runtime = movie.runtime

        let requestOptions: RequestInit = {
            body: JSON.stringify(requestBody),
            method: method,
            headers: headers,
            credentials: "include",
        }

        fetch(`/admin/movies/${movie.id}`, requestOptions)
            .then((response) => response.json())
            .then((data) => {
                if (data.error) {
                    console.log(data.error);
                } else {
                    navigate("/manage-catalogue");
                }
            })
            .catch(err => {
                console.log(err);
            })
    }

    const handleChange = (event: any) => (event: any) => {
        let value = event.target.value;
        let name = event.target.name;
        setMovie({
            ...movie,
            [name]: value
        });
    }

    const handleCheck = (event: any, position: number) => {
        console.log("handle check")
        console.log("value in handle check:", event.target.value)
        console.log("checked is :", event.target.checked)
        console.log("position is :", position)

        let tmpArr = movie.genres
        tmpArr[position].checked = !tmpArr[position].checked

        let tmpIDs = movie.genres_array
        if (!event.target.checked) {
            tmpIDs.splice(tmpIDs.indexOf(event.target.value))
        } else {
            tmpIDs.push(Boolean(parseInt(event.target.value, 10)))
        }

        setMovie(m => ({
            ...m,
            genres_array: tmpIDs,
        }))
    }

    const confirmDelete = () => {
        Swal.fire({
            title: 'Delete movie?',
            text: "You cannot undo this action!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                let headers = new Headers();
                headers.append("Authorization", "Bearer " + jwtToken)

                const requestOptions = {
                    method: "DELETE",
                    headers: headers,
                }

                fetch(`/admin/movies/${movie.id}`, requestOptions)
                    .then((response) => response.json())
                    .then((data) => {
                        if (data.error) {
                            console.log(data.error);
                        } else {
                            navigate("/manage-catalogue");
                        }
                    })
                    .catch(err => {console.log(err)});
            }
        })
    }

    if (error !== null) {
        return <div>Error: {error}</div>;
    } else {
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
                        value={movie.mpaa_rating}
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

                    <hr/>


                    <h3>Genres</h3>

                    {movie.genres && movie.genres.length > 1 &&
                        <>
                            {Array.from(movie.genres).map((g, i) => (
                                <Checkbox
                                    title={g.genre}
                                    name={"genre"}
                                    key={i}
                                    it={"genre-" + i}
                                    onChange={(event: any) => handleCheck(event, i)}
                                    value={g.id}
                                    checked={movie.genres[i].checked}
                                />

                            ))}
                        </>
                    }

                    <hr/>

                    <button className={"btn btn-primary"}>Save</button>

                    {movie.id > 0 && (
                        <a href="#!" className="btn btn-danger ms-2" onClick={confirmDelete}>
                            Delete Movie
                        </a>
                    )}
                </form>
            </div>
        );
    }
}

export default EditMovie;

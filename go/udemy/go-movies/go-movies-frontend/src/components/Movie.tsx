import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import MovieType from "../types/Movie";

const Movie = () => {

    const [movie, setMovie] = useState<MovieType>({
        id: 0,
        title: "",
        release_date: "",
        runtime: 0,
        mpaa_rating: "",
        description: ""
    });
    let {id} = useParams();

    useEffect(() => {
            let myMovie = {
                id: 1,
                title: "Highlander",
                release_date: "1986-03-07",
                runtime: 116,
                mpaa_rating: "R",
                description: "Super long description"
            }

            setMovie(myMovie)
        }, [id]
    )

    return (
        <div>
            <h2>Movie {movie.title}</h2>
            <small>
                <em>{movie.release_date}, {movie.runtime} minutes, rated {movie.mpaa_rating}</em>
            </small>
            <hr/>
            <p>{movie.description}</p>
        </div>
    )
}

export default Movie;

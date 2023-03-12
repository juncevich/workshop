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
        description: "",
        image: "",
        genres: [],
    });
    let {id} = useParams();

    useEffect(() => {
            const headers = new Headers();
            headers.append("Content-Type", "application/json");

            const requestOptions = {
                method: 'GET',
                headers: headers,
            };

            fetch(`/movies/${id}`, requestOptions)
                .then(response => response.json())
                .then(data => {
                    setMovie(data);
                })
                .catch(error => console.log('error', error));
        }, [id]
    )

    if (movie.genres) {
        movie.genres = Object.values(movie.genres);
    } else {
        movie.genres = [];
    }
    return (
        <div>
            <h2>Movie {movie.title}</h2>
            <small>
                <em>{movie.release_date}, {movie.runtime} minutes, rated {movie.mpaa_rating}</em>
            </small>
            <br/>
            {movie.genres.map((g) => (
                <span key={g.genre} className="badge bg-secondary me-2">{g.genre}</span>
            ))}
            <hr/>

            {movie.image !== "" &&
                <div className="mb-3">
                    <img src={`https://image.tmdb.org/t/p/w200/${movie.image}`} alt={"poster"}/>
                </div>
            }
            <p>{movie.description}</p>
        </div>
    )
}

export default Movie;

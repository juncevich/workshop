import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import Movie from "../types/Movie";


const Movies = () => {

    const [movies, setMovies] = useState<Movie[]>([])

    useEffect(() => {
            const headers = new Headers()
            headers.append("Content-Type", "application-json")

            const requestOptions = {
                method: "GET",
                headers: headers,
            }

            fetch(`http://localhost:8080/movies`, requestOptions)
                .then((response ) => response.json())
                .then((data) => {
                    setMovies(data)
                })
                .catch(err => {
                    console.log(err)
                })
        }, []
    );

    return (
        <div>
            <h2>Movies</h2>
            <hr/>
            <table className="table table-stripped table-hover">
                <thead>
                <tr>
                    <th>Movie</th>
                    <th>Release date</th>
                    <th>Rating</th>
                </tr>
                </thead>
                <tbody>
                {movies.map((movie: Movie) => (
                    <tr key={movie.id}>
                        <td>
                            <Link to={`/movies/${movie.id}`}>
                                {movie.title}
                            </Link>
                        </td>
                        <td>{movie.release_date}</td>
                        <td>{movie.mpaa_rating}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default Movies;

import {useEffect, useState} from "react";
import {Link, useNavigate, useOutletContext} from "react-router-dom";
import Movie from "../types/Movie";

const ManageCatalogue = () => {

    const [movies, setMovies] = useState<Movie[]>([]);
    const {jwtToken}: any = useOutletContext();
    const navigate = useNavigate();

    useEffect(() => {
            if (jwtToken) {
                navigate("/login")
                return
            }
            const headers = new Headers()
            headers.append("Content-Type", "application-json")
            headers.append("Authorization", `Bearer ${jwtToken}`)


            const requestOptions = {
                method: "GET",
                headers: headers,
            }

            fetch(`admin/movies`, requestOptions)
                .then((response) => response.json())
                .then((data) => {
                    setMovies(data)
                })
                .catch(err => {
                    console.log(err)
                })
        }, [jwtToken, navigate]
    );

    return (
        <div>
            <h2>Manage catalogue</h2>
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
                            <Link to={`admin/movies/${movie.id}`}>
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

export default ManageCatalogue;

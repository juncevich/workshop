import Genre from "./Genre";

type MovieType = {
    id: number;
    title: string;
    release_date: string;
    runtime: number;
    mpaa_rating: string;
    description: string;
    image: string;
    genres: Array<Genre>
    genres_array: Array<any>
};

export default MovieType;

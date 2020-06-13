package io.spring.movieinfoservice.models;

import javax.validation.constraints.Size;

public class Movie {

    String movieId;

    @Size(min = 2, message = "Movie Name should be atleast 2 chars")
    String name;
    String overView;

    public Movie(String movieId, String name, String overView) {
        this.movieId = movieId;
        this.name = name;
        this.overView = overView;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }
}

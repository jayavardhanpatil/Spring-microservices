package io.spring.movieinfoservice.resources;

import io.spring.movieinfoservice.models.Movie;
import io.spring.movieinfoservice.models.MovieSummary;
import io.spring.movieinfoservice.exception.MovieIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value ("${api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        //System.out.println("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+ apiKey);

        MovieSummary movieSummary = null;
        try {
             movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);

        }catch (Exception e){
            e.printStackTrace();
        }

        if (movieSummary == null) {
            throw new MovieIdNotFoundException("Id - "+movieId);
        }

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }

}

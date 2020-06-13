package io.spring.movieinfoservice.resources;

import io.spring.movieinfoservice.models.Movie;
import io.spring.movieinfoservice.models.MovieSummary;
import io.spring.movieinfoservice.exception.MovieIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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


    @PostMapping("")
    public ResponseEntity<Object> createMovieResourse(@Valid @RequestBody Movie body){

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand(body.getMovieId()).toUri();

        return ResponseEntity.created(location).build();
    }

}

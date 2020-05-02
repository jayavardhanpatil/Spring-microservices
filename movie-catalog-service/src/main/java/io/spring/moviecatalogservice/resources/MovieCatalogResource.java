package io.spring.moviecatalogservice.resources;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.spring.moviecatalogservice.models.CatalogItem;
import io.spring.moviecatalogservice.models.Movie;
import io.spring.moviecatalogservice.models.Rating;
import io.spring.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallBackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/"+userId, UserRating.class);

        return ratings.getUserRating().stream()
                .map(rating ->{
                    //System.out.println(restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), String.class));

                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getOverView(), rating.getRating());
        }).collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("Game of Thrones", "This is the best TV Show ever", 1)
//        );
    }

    public List<CatalogItem> getFallBackCatalog(@PathVariable("userId") String userId){
        return Arrays.asList(new CatalogItem("No Movie","", 0));
    }

}

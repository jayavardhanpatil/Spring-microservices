package io.spring.ratingsdataservice.resources;

import io.spring.ratingsdataservice.models.Rating;
import io.spring.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 5);
    }


    @RequestMapping("/users/{userId}")
    public UserRating getUserrating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("123", 4),
                new Rating("567", 5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}

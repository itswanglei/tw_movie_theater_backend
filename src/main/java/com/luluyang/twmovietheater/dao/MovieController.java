package com.luluyang.twmovietheater.dao;

import com.luluyang.twmovietheater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(
            @PathVariable("id") Integer id
    ) {
        return movieRepository.findById(id).get();
    }

    @GetMapping("/movies/genres")
    public Iterable<Movie> getMoviesByGenres(
            @RequestParam("genres") String genres
    ) {
        return movieRepository.findByGenresLike("%" + genres + "%");
    }

    @GetMapping("/movies/keyword")
    public Iterable<Movie> getMoviesBySearch(
            @RequestParam("key") String key
    ) {
        List<Movie> movies = movieRepository.findByTitleLike("%" + key + "%");
        movies.addAll(movieRepository.findByGenresLike("%" + key + "%"));
        return movies;
    }

    @GetMapping("/genres")
    public Iterable<String> getGenres() {
        List<String> genreStringList = movieRepository.findGenres();
        List<String> allGenres = new ArrayList<>();
        genreStringList.forEach(genres -> allGenres.addAll(Arrays.asList(genres.split(","))));
        return allGenres.stream().distinct().collect(Collectors.toList());
    }
}

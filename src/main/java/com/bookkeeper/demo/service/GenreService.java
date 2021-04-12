package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres(){
        System.out.println("service calling getAllGenres");
        List<Genre> genres = genreRepository.findAll();
        if(genres.isEmpty()){
            throw new InformationNotFoundException("There are no genres.");
        }
        return genres;
    }

    public Genre getGenre(Long genreId){
        System.out.println("service calling getGenre");
        Optional <Genre> genre = genreRepository.findById(genreId);
        if(genre.isPresent()){
            return genre.get();
        } else{
            throw new InformationNotFoundException("Genre with id "+ genreId + " not found");
        }
    }

    public Genre addGenre(Genre genreObject){
        System.out.println("Service is calling addGenre");
        Optional<Genre> genreChecker = genreRepository.findByName(genreObject.getName());
        if (genreChecker.isPresent()){
            throw new InformationExistsException("Genre with name " + genreObject.getName()
                    + " already exists in this database");
        } else{
            return genreRepository.save(genreObject);
        }
    }
}

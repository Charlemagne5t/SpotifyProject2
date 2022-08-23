package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.*;

import java.util.List;
import java.util.Set;

public interface BandDao {
    Band getById(Long id);

    Band findByName(String name);

    List<Band> getAll();

    void insert(Band band);

    void update(Long id, String name, int yearEstablished, String description);

    void delete(Band band);


    void addAlbum(Band band, Album album);
}

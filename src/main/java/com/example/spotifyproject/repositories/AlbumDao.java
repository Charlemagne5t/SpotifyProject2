package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.Song;

import java.util.List;
import java.util.Set;

public interface AlbumDao {
    Album getById(Long id);

    Album findByTitle(String title);

    List<Album> getAll();

    void insert(Album album);

    void update(Long id, String title, Band band);

    void delete(Album album);

    void addSong(Album album, Song song);
}

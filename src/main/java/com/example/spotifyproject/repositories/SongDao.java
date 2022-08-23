package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.Genre;
import com.example.spotifyproject.models.Song;

import java.time.Duration;
import java.util.List;

public interface SongDao {
    Song getById(Long id);

    Song findByTitle(String title);

    List<Song> getAll();

    void insert(Song song);

    void update(Long id, String title, Album album, Genre songGenre, Duration songDuration);

    void delete(Song song);
}

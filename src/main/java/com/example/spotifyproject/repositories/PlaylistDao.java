package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.Playlist;
import com.example.spotifyproject.models.Song;

import java.util.List;
import java.util.Set;

public interface PlaylistDao {

    Playlist getById(Long id);

    Playlist findByName(String name);

    List<Playlist> getAll();

    void insert(Playlist playlist);

    void update(Long id, String name);

    void delete(Playlist playlist);

    void addSong(Long playlistId, Long songId);


}

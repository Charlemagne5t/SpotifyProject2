package com.example.spotifyproject.controllers;


import com.example.spotifyproject.models.Playlist;
import com.example.spotifyproject.repositories.PlaylistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaylistRestController {
    private final PlaylistDao playlistDao;

    @Autowired
    public PlaylistRestController(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    @PostMapping("/addPlaylist")
    public void addAlbum(@RequestBody Playlist playlist) {
        playlistDao.insert(playlist);
    }

    @GetMapping("/showAllPlaylists")
    public ResponseEntity<List<Playlist>> showAllPlaylists() {
        List<Playlist> playlists = playlistDao.getAll();
        return ResponseEntity
                .ok()
                .body(playlists);
    }

    @GetMapping("/getPlaylistById")
    public ResponseEntity<Playlist> getPlaylist(@RequestParam Long id) {
        Playlist playlist = playlistDao.getById(id);
        return ResponseEntity.ok().body(playlist);
    }

    @GetMapping("/findPlaylistByName")
    public ResponseEntity<Playlist> findPlaylist(@RequestParam String name) {
        Playlist playlist = playlistDao.findByName(name);
        return ResponseEntity.ok().body(playlist);
    }

    @PutMapping("/updatePlaylist")
    public void updatePlaylist(@RequestBody Playlist playlist) {
        playlistDao.update(playlist.getId(), playlist.getName());

    }

    @DeleteMapping("/deletePlaylist")
    public void deleteAlbum(@RequestBody Playlist playlist) {
        playlistDao.delete(playlist);

    }

    @PostMapping("/addSongToPlaylist")
    public void addSong(@RequestParam Long playlistId,
                        @RequestParam Long songId){
        playlistDao.addSong(playlistId, songId);
    }
}

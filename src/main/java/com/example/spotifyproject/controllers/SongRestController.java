package com.example.spotifyproject.controllers;

import com.example.spotifyproject.models.Song;
import com.example.spotifyproject.repositories.SongDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongRestController {
    private final SongDao songDao;
    @Autowired
    public SongRestController(SongDao songDao){
        this.songDao = songDao;
    }

    @PostMapping("/addSong")
    public void  addSong(@RequestBody Song song){
        songDao.insert(song);
    }

    @GetMapping("showAllSongs")
    public ResponseEntity<List<Song>> showAllSongs(){
        List<Song> songList = songDao.getAll();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(songList);
    }

    @GetMapping("/getSong")
    public ResponseEntity<Song> getSong(@RequestParam Long id){
        Song song = songDao.getById(id);
        return  ResponseEntity.ok().body(song);
    }

    @GetMapping("/findSong")
    public ResponseEntity<Song> findSong(@RequestParam String title){
        Song song = songDao.findByTitle(title);
        return ResponseEntity.ok().body(song);
    }

    @PutMapping("/updateSong")
    public void updateSong(@RequestBody Song song){
        songDao.update(song.getId(), song.getTitle(), song.getAlbum(), song.getSongGenre(), song.getSongDuration());
    }

    @DeleteMapping("/deleteSong")
    public void  deleteSong(@RequestBody Song song){
        songDao.delete(song);
    }
}

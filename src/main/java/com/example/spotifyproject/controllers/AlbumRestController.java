package com.example.spotifyproject.controllers;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Song;
import com.example.spotifyproject.repositories.AlbumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumRestController {
    private final AlbumDao albumDao;

    @Autowired
    public AlbumRestController(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    @PostMapping("/addAlbum")
    public void addAlbum(@RequestBody Album album) {
        albumDao.insert(album);
    }

    @GetMapping("/showAllAlbums")
    public ResponseEntity<List<Album>> showAllAlbums() {
        List<Album> albumList = albumDao.getAll();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(albumList);
    }

    @GetMapping("/getAlbum")
    public ResponseEntity<Album> getAlbum(@RequestParam Long id) {
        Album album = albumDao.getById(id);
        return ResponseEntity.ok().body(album);
    }

    @GetMapping("/findAlbum")
    public ResponseEntity<Album> findAlbum(@RequestParam String title) {
        Album album = albumDao.findByTitle(title);
        return ResponseEntity.ok().body(album);
    }

    @PutMapping("/updateAlbum")
    public void updateAlbum(@RequestBody Album album) {
        albumDao.update(album.getId(), album.getTitle(), album.getBand());

    }

    @DeleteMapping("/deleteAlbum")
    public void deleteAlbum(@RequestBody Album album) {
        albumDao.delete(album);

    }

    @PostMapping("/addSongToAlbum")
    public void addSongToAlbum(@RequestBody Album album,
                               @RequestBody Song song){
        albumDao.addSong(album, song);
    }
}

package com.example.spotifyproject.controllers;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.Song;
import com.example.spotifyproject.repositories.BandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BandRestController {
    private final BandDao bandDao;

    @Autowired
    public BandRestController(BandDao bandDao) {
        this.bandDao = bandDao;
    }

    @PostMapping("/addBand")
    public void addBand(@RequestBody Band band) {
        bandDao.insert(band);
    }

    @GetMapping("/showAllBands")
    public ResponseEntity<List<Band>> showAllBands() {
        List<Band> bandList = bandDao.getAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bandList);
    }

    @GetMapping("/getBand")
    public ResponseEntity<Band> getBand(@RequestParam Long id) {
        Band band = bandDao.getById(id);
        return ResponseEntity.ok().body(band);
    }

    @GetMapping("/findBand")
    public ResponseEntity<Band> findBand(@RequestParam String name) {
        Band band = bandDao.findByName(name);
        return ResponseEntity.ok().body(band);
    }

    @PutMapping("/updateBand")
    public void updateBand(@RequestBody Band band) {
        bandDao.update(band.getId(), band.getName(), band.getYearEstablished(), band.getDescription());

    }

    @DeleteMapping("/deleteBand")
    public void deleteBand(@RequestBody Band band) {
        bandDao.delete(band);

    }

    @PostMapping("/addAlbumToBand")
    public void addAlbumToBand(@RequestBody Band band,
                               @RequestBody Album album){
        bandDao.addAlbum(band, album);
    }

}

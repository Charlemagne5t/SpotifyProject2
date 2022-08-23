package com.example.spotifyproject.services;

import com.example.spotifyproject.models.*;
import com.example.spotifyproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class DatabaseService {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private AlbumDao albumDao;
    private BandDao bandDao;
    private PlaylistDao playlistDao;
    private SongDao songDao;
    private UserDao userDao;


    @Autowired
    public DatabaseService(AlbumDao albumDao, BandDao bandDao, PlaylistDao playlistDao, SongDao songDao,UserDao userDao){
        this.albumDao = albumDao;
        this.bandDao = bandDao;
        this.playlistDao = playlistDao;
        this.songDao = songDao;
        this.userDao = userDao;
    }

    public DatabaseService() {
    }


    public void initializeUser() {
        for (int i = 0; i < 10; i++) {
            String username = "User" + (i + 1);
            LocalDate dateOfBirth = LocalDate.of(1980 + i, 1, 1);
            String email = i + 123 + "@gmail.com";
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);
            userDao.insert(user);
        }
    }

    public void initializePlaylist(){
        for (long i = 0; i < 10; i++) {
            String name = "Playlist № " + i + 1;
            User user = userDao.getById(i + 1);
            Playlist playlist = new Playlist();
            playlist.setName(name);
            playlist.setUser(user);
            playlistDao.insert(playlist);

        }
        }

    public void initializeBand() {
        for (int i = 0; i < 10; i++) {
            String name = "Band" + (i + 1);
            String description = "Best band ever " + i * i;
            Band band = new Band();
            band.setName(name);
            band.setYearEstablished(1971 + i);
            band.setDescription(description);
            bandDao.insert(band);
        }
    }
    public void initializeAlbum() {
        for (long i = 0; i < 10; i++) {
            String title = "Album" + i + 100;
            Band band = bandDao.getById(i + 1);
            Album album = new Album();
            album.setTitle(title);
            album.setBand(band);
            albumDao.insert(album);
        }
    }

    public void initializeSong() {
        for (long i = 0; i < 10; i++) {
            String title = "song" + (i + 1);
            Genre songGenre = Genre.CLASSICAL;
            if (i % 2 == 0) {
                songGenre = Genre.POP;
            } else {
                songGenre = Genre.METAL;
            }
            Album album = albumDao.getById(i + 1);
            Song song = new Song();
            song.setTitle(title);
            song.setSongGenre(songGenre);
            song.setAlbum(album);
            songDao.insert(song);
        }
    }

    public void fillPlaylists(){
        for (long i = 0; i < 10 ; i++) {
            playlistDao.addSong(i+1, 1L);
            playlistDao.addSong(i+1, 2L);
            playlistDao.addSong(i+1, 3L);
            playlistDao.addSong(i+1, 4L);
            playlistDao.addSong(i+1, 5L);

        }
    }

    public void clearUsers() {
        for (Long i = 1L; i < 15L; i++) {
            if (userDao.getById(i) != null) {
                User user = userDao.getById(i);
                userDao.delete(user);
            }
        }
    }
    public void clearPlaylists() {
        for (Long i = 1L; i < 15L; i++) {
            if (playlistDao.getById(i) != null) {
                Playlist playlist = playlistDao.getById(i);
                playlistDao.delete(playlist);
            }
        }
    }

    public void clearBands() {
        for (Long i = 1L; i < 15L; i++) {
            if (bandDao.getById(i) != null) {
                Band band = bandDao.getById(i);
                bandDao.delete(band);
            }
        }
    }

}

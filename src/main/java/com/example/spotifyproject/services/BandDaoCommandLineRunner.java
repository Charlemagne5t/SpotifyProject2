package com.example.spotifyproject.services;

import com.example.spotifyproject.models.*;
import com.example.spotifyproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//@Profile("TEST")
@Component
public class BandDaoCommandLineRunner implements CommandLineRunner {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    private BandDao bandDao;
    private SongDao songDao;
    private AlbumDao albumDao;
    private UserDao userDao;
    private PlaylistDao playlistDao;
    private DatabaseService databaseService;
    @Autowired
    public BandDaoCommandLineRunner(BandDao bandDao,
     SongDao songDao,
     AlbumDao albumDao,
     UserDao userDao,
     PlaylistDao playlistDao,
     DatabaseService databaseService){
        this.bandDao = bandDao;
        this.albumDao = albumDao;
        this.songDao = songDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.databaseService = databaseService;
    }

    @Override
    public void run(String... args) throws Exception {
//        databaseService.initializeUser();
//        databaseService.initializePlaylist();
//        databaseService.initializeBand();
//        databaseService.initializeAlbum();
//        databaseService.initializeSong();
//        databaseService.fillPlaylists();
//
//       Song song = entityManager.find (Song.class, 5L);
//
//        Playlist playlist = new Playlist();
//        playlist.setName("SUPERPLAYLIST3");
//        playlist.getSongs().add(entityManager.merge(song));
//        User user = new User();
//        user.setEmail("SUPEREMAIL3");
//        user.setName("SUPERUSER3");
//        user.getPlaylists().add(playlist);
//        userDao.insert(user);



//        System.out.println(playlistDao.getAll());
//        System.out.println( playlistDao.getAll().size());
//        Song song = new Song();
//        song.setTitle("GreatestSongEverMadeTRUE");
//        song.setSongGenre(Genre.POP);
//        albumDao.addSong(entityManager.find (Album.class, 5L),
//                song);
//        System.out.println(albumDao.getById(1L).);
//        System.out.println(bandDao.getById(1L).getAlbums());
//        System.out.println(songDao.getById(5L));
//        System.out.println(userDao.getById(2L));
//        System.out.println(playlistDao.getById(1L));

        //System.out.println(bandDao.getById(1L).getAlbums().size());
    }
}

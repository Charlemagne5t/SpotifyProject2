package com.example.spotifyproject.repositoriesTest;

import com.example.spotifyproject.models.Playlist;
import com.example.spotifyproject.models.User;
import com.example.spotifyproject.repositories.PlaylistDao;
import com.example.spotifyproject.repositories.UserDao;
import com.example.spotifyproject.services.DatabaseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("TEST")
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PlaylistRepositoryCRUDTest {


    private final PlaylistDao playlistDao;
    private  final UserDao userDao;
    private final DatabaseService databaseService;

    @Autowired
    public PlaylistRepositoryCRUDTest(DatabaseService databaseService, UserDao userDao, PlaylistDao playlistDao){
        this.databaseService = databaseService;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
    }

    @BeforeAll
    public void setUpDatabase() {
        databaseService.initializeUser();
        databaseService.initializePlaylist();
        databaseService.initializeBand();
        databaseService.initializeAlbum();
        databaseService.initializeSong();
        databaseService.fillPlaylists();
    }

    @Test
    public void testGetPlaylistByIdHappyFlow() {
        Playlist playlist = playlistDao.getById(4L);

        assertAll(
                () -> assertEquals("Playlist № 31", playlist.getName()),
                () -> assertEquals("User4", playlist.getUser().getName()),
                () -> assertEquals(5, playlist.getSongs().size())
        );
    }

    @Test
    public void testFindByNameHappyFlow() {
        Playlist playlist = playlistDao.findByName("Playlist № 21");

        assertAll(
                () -> assertEquals(3L, playlist.getId()),
                () -> assertEquals("User3", playlist.getUser().getName()),
                () -> assertEquals(5, playlist.getSongs().size())
        );
    }

    @Test
    @Transactional
    public void testInsertUserHappyFlow() {
        String playlistName = "TestPlaylist";
        Playlist testPlaylist = new Playlist();
        testPlaylist.setName(playlistName);
        User user = userDao.getById(5L);
        testPlaylist.setUser(user);
        playlistDao.insert(testPlaylist);

        Playlist playlist = playlistDao.getById(11L);

        assertAll(
                () -> assertEquals(11L, playlist.getId()),
                () -> assertEquals("TestPlaylist", playlist.getName()),
                () -> assertEquals(5L, playlist.getUser().getId())
        );
    }

    @Test
    public void testGetAllUsersHappyFlow() {
        List<Playlist> playlists = playlistDao.getAll();

        assertAll(
                () -> assertEquals(10, playlists.size())
        );
    }

    @Test
    @Transactional
    public void testDeleteUserHappyFlow() {
        Playlist playlist = playlistDao.getById(5L);
        playlistDao.delete(playlist);

        assertNull(playlistDao.getById(5L));
    }

    @Test
    @Transactional
    public void testUpdateUserHappyFlow() {

        playlistDao.update(7L, "TestPlaylist");
        assertEquals("TestPlaylist", playlistDao.getById(7L).getName());

    }

    @Test
    @Transactional
    public void testAddSongToPlaylistHappyFlow() {
        playlistDao.addSong(3L, 7L);

        assertEquals(6, playlistDao.getById(3L).getSongs().size());

    }

}

package com.example.spotifyproject.repositoriesTest;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.User;
import com.example.spotifyproject.repositories.AlbumDao;
import com.example.spotifyproject.repositories.BandDao;
import com.example.spotifyproject.repositories.UserDao;
import com.example.spotifyproject.services.DatabaseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("TEST")
//@DirtiesContext
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class BandRepositoryCRUDTest {

    private final BandDao bandDao;
    private final DatabaseService databaseService;
    private  final AlbumDao albumDao;

    @Autowired
    public BandRepositoryCRUDTest(AlbumDao albumDao, DatabaseService databaseService, BandDao bandDao) {
        this.databaseService = databaseService;
        this.bandDao = bandDao;
        this.albumDao = albumDao;
    }

//    @BeforeAll
//    public void setUpDatabase() {
//        databaseService.initializeUser();
//        databaseService.initializePlaylist();
//        databaseService.initializeBand();
//        databaseService.initializeAlbum();
//        databaseService.initializeSong();
//        databaseService.fillPlaylists();
//    }

    /* public void initializeBand() {
        for (int i = 0; i < 10; i++) {
            String name = "Band" + (i + 1);
            String description = "Best band ever " + i * i;
            Band band = new Band();
            band.setName(name);
            band.setYearEstablished(1971 + i);
            band.setDescription(description);
            bandDao.insert(band);*/

    @Test
    @Rollback
    public void testGetBandByIdHappyFlow() {
        databaseService.initializeUser();
        databaseService.initializePlaylist();
        databaseService.initializeBand();
        databaseService.initializeAlbum();
        databaseService.initializeSong();
        databaseService.fillPlaylists();

        Band band = bandDao.getById(1L);

        assertAll(
                () -> assertEquals("Band1", band.getName()),
                () -> assertEquals("Best band ever 0", band.getDescription()),
                () -> assertEquals(1971, band.getYearEstablished())
               // () -> assertEquals(1, band.getAlbums().size())
        );
    }

//    @Test
//    public void testFindByNameHappyFlow() {
//        User user = userDao.findByName("User7");
//
//        assertAll(
//                () -> assertEquals("User7", user.getName()),
//                () -> assertEquals(LocalDate.of(1986, 1, 1), user.getDateOfBirth()),
//                () -> assertEquals("129@gmail.com", user.getEmail())
//        );
//    }
//
//    @Test
//    @Transactional
//    public void testInsertUserHappyFlow() {
//        String username = "TestUser";
//        LocalDate dateOfBirth = LocalDate.of(1999, 5, 5);
//        String email = 1234567 + "@gmail.com";
//        User testUser = new User();
//        testUser.setName(username);
//        testUser.setEmail(email);
//        testUser.setDateOfBirth(dateOfBirth);
//        userDao.insert(testUser);
//
//        User user = userDao.getById(11L);
//
//        assertAll(
//                () -> assertEquals(11L, user.getId()),
//                () -> assertEquals("TestUser", user.getName()),
//                () -> assertEquals(LocalDate.of(1999, 5, 5), user.getDateOfBirth()),
//                () -> assertEquals("1234567@gmail.com", user.getEmail())
//        );
//    }
//
//    @Test
//    public void testGetAllUsersHappyFlow() {
//        List<User> userList = userDao.getAll();
//
//        assertAll(
//                () -> assertEquals(10, userList.size())
//        );
//    }
//
//    @Test
//    @Transactional
//    public void testDeleteUserHappyFlow() {
//        User user = userDao.getById(3L);
//        userDao.delete(user);
//
//        assertNull(userDao.getById(3L));
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateUserHappyFlow() {
//        userDao.update(10L, "Robert", LocalDate.ofEpochDay(1999 - 12 - 30), "UPDATED_EMAIL");
//
//        assertEquals("Robert", userDao.getById(10L).getName());
//        assertEquals(LocalDate.ofEpochDay(1999 - 12 - 30), userDao.getById(10L).getDateOfBirth());
//        assertEquals("UPDATED_EMAIL", userDao.getById(10L).getEmail());
//
//    }
}

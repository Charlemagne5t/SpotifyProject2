package com.example.spotifyproject.repositoriesTest;

import com.example.spotifyproject.models.User;
import com.example.spotifyproject.repositories.*;
import com.example.spotifyproject.services.DatabaseService;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("TEST")
@DirtiesContext
//@TestExecutionListeners({DirtiesContextBeforeModesTestExecutionListener.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class UserRepositoryCRUDTest {

    private final UserDao userDao;
    private final DatabaseService databaseService;

    @Autowired
    public UserRepositoryCRUDTest(DatabaseService databaseService, UserDao userDao) {
        this.databaseService = databaseService;
        this.userDao = userDao;
    }

    @BeforeAll
           public void init(){
        databaseService.initializeUser();
        databaseService.initializePlaylist();
        databaseService.initializeBand();
        databaseService.initializeAlbum();
        databaseService.initializeSong();
        databaseService.fillPlaylists();
    }


    @Test
    @Rollback
    public void testGetUserByIdHappyFlow() {
        User user = userDao.getById(5L);

        assertAll(
                () -> assertEquals("User5", user.getName()),
                () -> assertEquals(LocalDate.of(1984, 1, 1), user.getDateOfBirth()),
                () -> assertEquals("127@gmail.com", user.getEmail())
                , () -> assertEquals(1, user.getPlaylists().size())
        );
    }



    @Test
    @Rollback
    public void testFindByNameHappyFlow() {
        User user = userDao.findByName("User7");

        assertAll(
                () -> assertEquals("User7", user.getName()),
                () -> assertEquals(LocalDate.of(1986, 1, 1), user.getDateOfBirth()),
                () -> assertEquals("129@gmail.com", user.getEmail())
        );
    }

    @Test
    @Transactional
    public void testInsertUserHappyFlow() {
        String username = "TestUser";
        LocalDate dateOfBirth = LocalDate.of(1999, 5, 5);
        String email = 1234567 + "@gmail.com";
        User testUser = new User();
        testUser.setName(username);
        testUser.setEmail(email);
        testUser.setDateOfBirth(dateOfBirth);
        userDao.insert(testUser);

        User user = userDao.getById(11L);

        assertAll(
                () -> assertEquals(11L, user.getId()),
                () -> assertEquals("TestUser", user.getName()),
                () -> assertEquals(LocalDate.of(1999, 5, 5), user.getDateOfBirth()),
                () -> assertEquals("1234567@gmail.com", user.getEmail())
        );
    }

    @Test
    public void testGetAllUsersHappyFlow() {
        List<User> userList = userDao.getAll();

        assertAll(
                () -> assertEquals(10, userList.size())
        );
    }

    @Test
    @Transactional
    public void testDeleteUserHappyFlow() {
        User user = userDao.getById(3L);
        userDao.delete(user);

        assertNull(userDao.getById(3L));
    }

    @Test
    @Transactional
    public void testUpdateUserHappyFlow() {
        userDao.update(10L, "Robert", LocalDate.ofEpochDay(1999 - 12 - 30), "UPDATED_EMAIL");

        assertEquals("Robert", userDao.getById(10L).getName());
        assertEquals(LocalDate.ofEpochDay(1999 - 12 - 30), userDao.getById(10L).getDateOfBirth());
        assertEquals("UPDATED_EMAIL", userDao.getById(10L).getEmail());

    }
}

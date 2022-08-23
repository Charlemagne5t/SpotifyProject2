package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;

@Transactional
@Repository
public class SongDaoImplementation implements SongDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Song getById(Long id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    public Song findByTitle(String title) {
        return (Song) entityManager.createQuery("SELECT song  FROM Song song WHERE song.title LIKE :title")
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public List<Song> getAll() {
        return (List<Song>) entityManager.createQuery("SELECT song FROM Song song").getResultList();
    }

    @Override
    public void insert(Song song) {
        if(song.getAlbum() !=null){
        Long albumId = song.getAlbum().getId();
        Album album = entityManager.find(Album.class, albumId);
            entityManager.persist(song);
            album.getSongs().add(song);
        }else entityManager.merge(song);
    }

    @Override
    public void update(Long id, String title, Album album, Genre songGenre, Duration songDuration) {
        Song song = entityManager.find(Song.class, id);
        song.setTitle(title);
        song.setAlbum(album);
        song.setSongGenre(songGenre);
        song.setSongDuration(songDuration);
        //entityManager.persist(song);
    }

    @Override
    public void delete(Song song) {
        Song songInDatabase = entityManager.find(Song.class, song.getId());
        entityManager.remove(songInDatabase);
    }
}

package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class AlbumDaoImplementation implements AlbumDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Album getById(Long id) {
        return entityManager.find(Album.class, id);
    }

    @Override
    public Album findByTitle(String title) {
        return (Album) entityManager.createQuery("SELECT album FROM Album album WHERE album.title=:title")
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public List<Album> getAll() {
        return (List<Album>) entityManager.createQuery("SELECT album FROM Album album").getResultList();
    }

    @Override
    public void insert(Album album) {
        Long id = album.getBand().getId();
        Band band = entityManager.find(Band.class, id);
        entityManager.persist(album);
        band.getAlbums().add(album);

    }

    @Override
    public void update(Long id, String title, Band band) {
        Album album = entityManager.find(Album.class, id);
        album.setTitle(title);
        album.setBand(band);
        //entityManager.persist(album);

    }

    @Override
    public void delete(Album album) {
        Album albumInDatabase = entityManager.find(Album.class, album.getId());
        albumInDatabase.getSongs().clear();
        entityManager.remove(albumInDatabase);
    }
    @Override
    public void addSong(Album album, Song song){
        Album albumGettingSong = entityManager.find(Album.class, album.getId());
        if(song.getId() != null){
           Song songTransferred = entityManager.find(Song.class, song.getId());
           albumGettingSong.addSong(songTransferred);
        }else {
            entityManager.persist(song);
            albumGettingSong.addSong(song);
        }
    }

}

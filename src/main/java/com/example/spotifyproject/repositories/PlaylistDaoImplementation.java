package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.Band;
import com.example.spotifyproject.models.Playlist;
import com.example.spotifyproject.models.Song;
import com.example.spotifyproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class PlaylistDaoImplementation implements PlaylistDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Playlist getById(Long id) {
        return entityManager.find(Playlist.class, id);
    }

    @Override
    public Playlist findByName(String name) {
        return (Playlist) entityManager.createQuery("SELECT playlist  FROM Playlist playlist WHERE playlist.name LIKE :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Playlist> getAll() {
        return (List<Playlist>) entityManager.createQuery("SELECT playlist FROM Playlist playlist").getResultList();
    }

    @Override
    public void insert(Playlist playlist) {
        if(playlist.getId() == null) {
            Long id = playlist.getUser().getId();
            User user = entityManager.find(User.class, id);
            user.getPlaylists().add(playlist);
            entityManager.persist(playlist);
            for (Song song : playlist.getSongs()) {
                song.getPlaylists().add(playlist);
            }
        }else{
            entityManager.merge(playlist);
        }

    }

    @Override
    public void update(Long id, String name) {
        Playlist playlist = entityManager.find(Playlist.class, id);
        playlist.setName(name);
        //entityManager.persist(playlist);
    }

    @Override
    public void delete(Playlist playlist) {
        Playlist playlistInDatabase = entityManager.find(Playlist.class, playlist.getId());
        entityManager.remove(playlistInDatabase);

    }
    @Override
    public void addSong(Long playlistId, Long songId){
       Playlist playlist = entityManager.find(Playlist.class, playlistId);
       Song song = entityManager.find(Song.class, songId);
       playlist.getSongs().add(song);
       //song.getPlaylists().add(playlist);
    }

}

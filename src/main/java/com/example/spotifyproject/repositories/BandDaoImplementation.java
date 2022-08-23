package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.Album;
import com.example.spotifyproject.models.Band;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class BandDaoImplementation implements BandDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Band getById(Long id) {
        return entityManager.find(Band.class, id);
    }

    @Override
    public Band findByName(String name) {
        return (Band) entityManager.createQuery("SELECT band FROM Band band WHERE band.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Band> getAll() {
        return (List<Band>) entityManager.createQuery("SELECT band FROM Band band").getResultList();
    }

    @Override
    public void insert(Band band) {
        if(band.getId() == null){
            entityManager.persist(band);
        }else
            entityManager.merge(band);

    }

    @Override
    public void update(Long id, String name, int yearEstablished, String description) {
        Band band = entityManager.find(Band.class, id);
        band.setName(name);
        band.setYearEstablished(yearEstablished);
        band.setDescription(description);
        //entityManager.persist(band);
    }

    @Override
    public void delete(Band band) {
        Band bandInDatabase = entityManager.find(Band.class, band.getId());
        band.getAlbums().forEach(x -> x.getSongs().clear());
        band.getAlbums().clear();
        entityManager.remove(bandInDatabase);
    }

    @Override
    public void addAlbum(Band band, Album album){
        Band bandGettingAlbum = entityManager.find(Band.class, band.getId());
        if(album.getId() != null){
            Album albumTransferred = entityManager.find(Album.class, album.getId());
            bandGettingAlbum.addAlbum(albumTransferred);
        }else {
            entityManager.persist(album);
            bandGettingAlbum.addAlbum(album);
        }
    }

}

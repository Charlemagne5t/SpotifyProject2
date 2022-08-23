package com.example.spotifyproject.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bands")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private int yearEstablished;

    private String description;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bands_id")
    @JsonManagedReference(value = "albums-band")
    private List<Album> albums = new ArrayList<>();

    @Version
    private int version;

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
        album.setBand(this);

    }

    public void removeAlbumFromBand(Album album) {
        getAlbums().remove(album);
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Band band = (Band) o;

        return name.equals(band.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", yearEstablished=" + yearEstablished +
                ", description='" + description + '\'' +
                ", albums=" + albums.stream().map(Album::getTitle).toList() +
                '}';
    }
}

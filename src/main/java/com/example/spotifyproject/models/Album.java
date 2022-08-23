package com.example.spotifyproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JsonBackReference(value = "albums-band")
    private Band band;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "albums_id")
    @JsonIgnoreProperties("album")
    private List<Song> songs = new ArrayList<>();
    // private byte[] coverage;
    // private int releaseYear;
    @Version
    private int version;

    public void addSong(Song song) {
        songs.add(song);
        song.setAlbum(this);
    }

    public void removeSongFromAlbum(Song song) {
        songs.remove(song);
        song.setAlbum(null);
    }


    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

//    public byte[] getCoverage() {
//        return coverage;
//    }
//
//    public void setCoverage(byte[] coverage) {
//        this.coverage = coverage;
//    }


    public Long getId() {
        return id;
    }

//    public int getReleaseYear() {
//        return releaseYear;
//    }
//
//    public void setReleaseYear(int releaseYear) {
//        this.releaseYear = releaseYear;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        return title.equals(album.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                "title='" + title + '\'' +
                ", band=" + band.getName() +
                ", songs=" + songs.stream().map(Song::getTitle).toList() +
                '}';
    }
}


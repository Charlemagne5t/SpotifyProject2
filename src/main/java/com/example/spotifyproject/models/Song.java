package com.example.spotifyproject.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "songs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "songs_playlists",
            joinColumns = @JoinColumn(name = "songs_id"),
            inverseJoinColumns = @JoinColumn(name = "playlists_id")
    )
    //@JsonIgnoreProperties("song")
    private List<Playlist> playlists = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("song")
    private Album album;

    @Enumerated(EnumType.STRING)
    private Genre songGenre;

    private Duration songDuration;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public Album getAlbum() {
        return album;
    }

    public Genre getSongGenre() {
        return songGenre;
    }

    public Duration getSongDuration() {
        return songDuration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setSongGenre(Genre songGenre) {
        this.songGenre = songGenre;
    }

    public void setSongDuration(Duration songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return title.equals(song.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                "title='" + title + '\'' +
                ", album=" + album.getTitle() +
                ", band=" + album.getBand().getName() +
                ", songGenre=" + songGenre +
                ", songDuration=" + songDuration +
                '}';
    }
}

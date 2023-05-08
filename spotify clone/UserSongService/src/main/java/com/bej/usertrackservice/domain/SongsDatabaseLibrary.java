package com.bej.usertrackservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SongsDatabaseLibrary {

    @Id
    private String songId;
    private String songName;
    private String genre;
    private String leadSinger;
    private String album;
    private int length;

    private String artist;

    public SongsDatabaseLibrary() {
    }

    public SongsDatabaseLibrary(String songId, String songName, String genre, String leadSinger, String album, int length, String artist) {
        this.songId = songId;
        this.songName = songName;
        this.genre = genre;
        this.leadSinger = leadSinger;
        this.album = album;
        this.length = length;
        this.artist = artist;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLeadSinger() {
        return leadSinger;
    }

    public void setLeadSinger(String leadSinger) {
        this.leadSinger = leadSinger;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

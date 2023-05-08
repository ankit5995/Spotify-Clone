package com.bej.usertrackservice.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Playlist {

    @Id
    private String playListName;
    private List<Song> songList;



    public Playlist() {
    }

    public Playlist(List<Song> songList, String playListName) {
        this.songList = songList;
        this.playListName = playListName;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
}



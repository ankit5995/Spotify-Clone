package com.bej.usertrackservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class User {
    @Id
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private List<Playlist> playLists;

    public User() {
    }

    public User(String email, String userName, String password, String phoneNumber, List<Song> songList,
                List<Playlist> playLists,Playlist playList) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.playLists = playLists;

    }

    public List<Playlist> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<Playlist> playLists) {
        this.playLists = playLists;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

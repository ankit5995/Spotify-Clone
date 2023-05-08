package com.bej.usertrackservice.controller;

import com.bej.usertrackservice.domain.Playlist;
import com.bej.usertrackservice.domain.SongsDatabaseLibrary;
import com.bej.usertrackservice.exception.*;
import com.bej.usertrackservice.service.UserSongService;
import com.bej.usertrackservice.domain.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v2")
public class UserSongsController {
private UserSongService userSongService;
private ResponseEntity<?> responseEntity;
@Autowired
    public UserSongsController(UserSongService userSongService) {
        this.userSongService = userSongService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
    try {
        responseEntity =  new ResponseEntity<>(userSongService.registerUser(user), HttpStatus.CREATED);
    }
    catch(UserAlreadyExistsException e)
    {
        throw new UserAlreadyExistsException();
    }
    return responseEntity;
    }

    @PostMapping("/user/savePlaylist")
    public ResponseEntity<?> createPlaylistAndAddSongs(@RequestBody Playlist playList, HttpServletRequest request) throws PlayListAlreadyExistException, UserNotFoundException{
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(userSongService.createPlayListAndAddSongToPlayList(email,playList),HttpStatus.CREATED);
        }catch (PlayListAlreadyExistException playListAlreadyExistException){
            throw new PlayListAlreadyExistException();

        }catch (UserNotFoundException userNotFoundException){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    // to save track in different collection
    @PostMapping("/saveSong")
    public ResponseEntity<?> saveSongToCollection(@RequestBody SongsDatabaseLibrary songsDatabaseLibrary) {
            return new ResponseEntity<>(this.userSongService.saveTrack(songsDatabaseLibrary), HttpStatus.OK);

    }

    @GetMapping("/songs")
    public ResponseEntity<?> getAllTracksFromCollection() throws SongNotFoundException {
        try{
            return new ResponseEntity<>(userSongService.getAllTracks(), HttpStatus.OK);
        }catch(SongNotFoundException songNotFoundException)
            {
                throw new SongNotFoundException();
            }
    }

    @GetMapping("/songs/{songId}")
    public ResponseEntity<?> getOneSongFromLibrary(@PathVariable String songId) throws SongNotFoundException {
        try {

            return new ResponseEntity<>(userSongService.getSingleTrackFromLibrary(songId),HttpStatus.OK);
        } catch (SongNotFoundException songNotFoundException) {
            throw new SongNotFoundException();
        }
    }

    @GetMapping("/user/playlistNames")
    public ResponseEntity<?> getPlayListsOfUser(HttpServletRequest request) throws PlayListNotFoundException{
        try {

            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);

            return new ResponseEntity<>(userSongService.getAllPlaylistsForUser(email),HttpStatus.OK);
        }catch (PlayListNotFoundException playListNotFoundException){
            throw new PlayListNotFoundException();
        }
    }


    @GetMapping("/user/{playlistName}/{songId}")
    public ResponseEntity<?> getOneSongFromPlayList(@PathVariable String playlistName,HttpServletRequest request,@PathVariable String songId) throws SongNotFoundException {
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);

            return new ResponseEntity<>(userSongService.getSingleTrackFromPlaylist(email,songId,playlistName),HttpStatus.OK);
        } catch (SongNotFoundException songNotFoundException) {
            throw new SongNotFoundException();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getOneUser(HttpServletRequest request) {
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);

            return new ResponseEntity<>(userSongService.getUser(email),HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/{playlist}/songs")
    public ResponseEntity<?> getAllSongsOfPlaylistOfUserByPlayListName(@PathVariable String playlist, HttpServletRequest request) {
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);

            return new ResponseEntity<>(userSongService.getAllSongsOfPlaylistOfUserByPlaylistName(email,playlist),HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @DeleteMapping("/user/{playlistName}")
    public ResponseEntity<?> deleteUserSongFromList(@PathVariable String playlistName,HttpServletRequest request) throws PlayListNotFoundException
    {

        try {

            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(userSongService.deleteSinglePlayListFromPlaylists(email, playlistName), HttpStatus.OK);
        } catch (PlayListNotFoundException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @DeleteMapping("/user/{playlistName}/{songId}")
    public ResponseEntity<?> deleteSingleSongFromPlayList(@PathVariable String playlistName, @PathVariable String songId,HttpServletRequest request) throws PlayListNotFoundException, SongNotFoundException
    {
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        try {
            responseEntity = new ResponseEntity<>(userSongService.deleteSingleTrackFromPlayList(email,songId,playlistName), HttpStatus.OK);
        } catch (PlayListNotFoundException playListNotFoundException) {
            throw new PlayListNotFoundException();
        }catch (SongNotFoundException songNotFoundException){
            throw new SongNotFoundException();
        }
        return responseEntity;
    }
}


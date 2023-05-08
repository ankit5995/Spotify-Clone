package com.bej.usertrackservice.service;

import com.bej.usertrackservice.domain.Playlist;
import com.bej.usertrackservice.domain.Song;
import com.bej.usertrackservice.domain.SongsDatabaseLibrary;
import com.bej.usertrackservice.domain.User;
import com.bej.usertrackservice.exception.*;

import java.util.List;
import java.util.Optional;

public interface UserSongService {
    User registerUser(User user) throws UserAlreadyExistsException;
    User createPlayListAndAddSongToPlayList(String email, Playlist playlistName) throws PlayListAlreadyExistException,UserNotFoundException;

    public User deleteSingleTrackFromPlayList(String email, String trackId, String playlistName) throws SongNotFoundException, PlayListNotFoundException;
    public User deleteSinglePlayListFromPlaylists(String email, String playlistName) throws PlayListNotFoundException;
    List<String> getAllPlaylistsForUser(String email) throws PlayListNotFoundException;
    Song getSingleTrackFromPlaylist(String email, String trackId, String playListName) throws SongNotFoundException;
    SongsDatabaseLibrary getSingleTrackFromLibrary(String trackId) throws SongNotFoundException;
    List<SongsDatabaseLibrary> getAllTracks() throws SongNotFoundException;
    SongsDatabaseLibrary saveTrack(SongsDatabaseLibrary songsDatabaseLibrary) ;

    User getUser(String email);

    List<Song> getAllSongsOfPlaylistOfUserByPlaylistName(String email, String playlistName);

}

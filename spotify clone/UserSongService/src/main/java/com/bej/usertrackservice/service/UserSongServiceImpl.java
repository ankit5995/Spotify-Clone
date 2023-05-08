package com.bej.usertrackservice.service;

import com.bej.usertrackservice.domain.Playlist;
import com.bej.usertrackservice.domain.Song;
import com.bej.usertrackservice.domain.SongsDatabaseLibrary;
import com.bej.usertrackservice.exception.*;
import com.bej.usertrackservice.proxy.UserProxy;
import com.bej.usertrackservice.repository.SongLibraryRepository;
import com.bej.usertrackservice.repository.UserSongRepository;
import com.bej.usertrackservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserSongServiceImpl implements UserSongService {
    private UserSongRepository userSongRepository;

    @Autowired
    private SongLibraryRepository songLibraryRepository;
    @Autowired
    public UserSongServiceImpl(UserSongRepository userSongRepository) {
        this.userSongRepository = userSongRepository;
    }

    @Autowired
    private UserProxy userProxy;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userSongRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        User userFormDataBase = userSongRepository.save(user);
        if (!(userFormDataBase.getEmail().isEmpty())) {
            ResponseEntity<?> responseEntity = userProxy.saveUser(userFormDataBase);
            System.out.println(responseEntity.getBody());
        }
        return userFormDataBase;
    }

    @Override
    public User createPlayListAndAddSongToPlayList(String email, Playlist playlistName) throws PlayListAlreadyExistException, UserNotFoundException {
        if(userSongRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userSongRepository.findByEmail(email);
        if(user.getPlayLists() == null)
        {

            user.setPlayLists(Arrays.asList(playlistName));
        }
        else {
            List<Playlist> playlists = user.getPlayLists();
            Iterator<Playlist> iterator = playlists.listIterator();
            int flag = 0;

            for (int i = 0; i < playlists.size(); i++) {
                if (playlists.get(i).getPlayListName().equals(playlistName.getPlayListName())) {
                    playlists.get(i).getSongList().add(playlistName.getSongList().get(0));
                    user.setPlayLists(playlists);
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                playlists.add(playlistName);
                user.setPlayLists(playlists);
            }
        }
        return userSongRepository.save(user);
    }

    @Override
    public User deleteSingleTrackFromPlayList(String email, String trackId, String playlistName) throws SongNotFoundException, PlayListNotFoundException{

        User user = userSongRepository.findByEmail(email);
        if(user.getPlayLists() == null)
        {
            throw new PlayListNotFoundException();
        }
        else {
            List<Playlist> playlists = user.getPlayLists();

            for (int i = 0; i < playlists.size(); i++) {
                if (playlists.get(i).getPlayListName().equals(playlistName)) {

                    List<Song> songList = playlists.get(i).getSongList();
                    for (int j = 0; j < songList.size(); j++) {

                        if (songList.get(j).getSongId().equals(trackId)) {
                            playlists.get(i).getSongList().remove(songList.get(j));
                            user.setPlayLists(playlists);
                            break;
                        }
                    }
                    break;
                }

            }
        }
        return userSongRepository.save(user);
    }

    @Override
    public User deleteSinglePlayListFromPlaylists(String email, String playlistName) throws PlayListNotFoundException {
        User user = userSongRepository.findByEmail(email);
        if(user.getPlayLists() == null)
        {
            throw new PlayListNotFoundException();
        }
        else {
            List<Playlist> playlists = user.getPlayLists();

            for (int i = 0; i < playlists.size(); i++) {
                if (playlists.get(i).getPlayListName().equals(playlistName)) {
                    playlists.remove(playlists.get(i));
                    user.setPlayLists(playlists);
                    break;
                }
            }
        }
        return userSongRepository.save(user);
    }

    @Override
    public List<SongsDatabaseLibrary> getAllTracks() throws SongNotFoundException {
        return this.songLibraryRepository.findAll();
    }

    @Override
    public List<String> getAllPlaylistsForUser(String email) throws PlayListNotFoundException{

        List<String> playListNames = new ArrayList<>();
        User user = userSongRepository.findByEmail(email);

        List<Playlist> playlistList = user.getPlayLists();
        for (int i = 0; i < playlistList.size(); i++) {
            playListNames.add(playlistList.get(i).getPlayListName());
        }

        return playListNames;

    }

    @Override
    public Song getSingleTrackFromPlaylist(String email, String trackId, String playListName) throws SongNotFoundException {
        User user = userSongRepository.findByEmail(email);

        List<Song> songs = null;
        Song song = null;

        for (int i = 0; i < user.getPlayLists().size(); i++) {
            if (user.getPlayLists().get(i).getPlayListName().equals(playListName)) {
                songs = user.getPlayLists().get(i).getSongList();
                break;
            }
        }
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongId().equals(trackId)) {
                song = songs.get(i);
                break;
            }else {
                throw new SongNotFoundException();
            }
        }
        return song;
    }

    @Override
    public SongsDatabaseLibrary getSingleTrackFromLibrary(String trackId) throws SongNotFoundException {
        return this.songLibraryRepository.findById(trackId).get();
    }

    @Override
    public SongsDatabaseLibrary saveTrack(SongsDatabaseLibrary songsDatabaseLibrary){

        return this.songLibraryRepository.save(songsDatabaseLibrary);

    }

    @Override
    public User getUser(String email) {
        return this.userSongRepository.findByEmail(email);
    }

    @Override
    public List<Song> getAllSongsOfPlaylistOfUserByPlaylistName(String email, String playlistName) {
        Playlist playList = new Playlist();
        List<Song> songList = new ArrayList<>();
        User user = userSongRepository.findByEmail(email);

        for (int i = 0; i < user.getPlayLists().size(); i++) {
            if (user.getPlayLists().get(i).getPlayListName().equals(playlistName)) {
                playList = user.getPlayLists().get(i);
                songList = playList.getSongList();
                break;
            }
        }

        return songList;
    }
}

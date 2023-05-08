import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Song } from '../models/song';

import { map, Observable, of, switchMap, throwError } from 'rxjs';
import { Playlist } from '../models/playlist';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class TrackService {
  private playlist:String= "";

  URL: string = "http://localhost:9002/api/v2/songs";
  URL1: string = "http://localhost:9002/api/v2/user/savePlaylist";
  URL2: string = "http://localhost:9002/api/v2/user/playlistNames";
  URL3: string = "http://localhost:9002/api/v2/user/"+this.playlist+"/songs";



  constructor(private http: HttpClient) { }



  getTracks(): Observable<Array<Song>> {
    return this.http.get<Array<Song>>(this.URL);
  }

  getTrackById(id: string): Observable<Song> {
    return this.http.get<Array<Song>>(this.URL).pipe(
      map(tracks => tracks.find(track => track.songId === id)),
      switchMap(track => {
        if (track) {
          return of(track);
        } else {
          return throwError(`Track with ID ${id} not found`);
        }
      })
    );
  }

  savePlaylist(playlist:any){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('jwt'));
    return this.http.post(this.URL1, playlist,{'headers':requestHeader});
  }

  getPlaylistNamesOfUser(){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('jwt'));
    return this.http.get<Array<String>>(this.URL2,{'headers':requestHeader});
  }

  getSongsOfPlaylist(playlist : any){
    this.playlist = playlist;
    this.URL3 = "http://localhost:9002/api/v2/user/"+this.playlist+"/songs"
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('jwt'));
    return this.http.get<Array<Song>>(this.URL3,{'headers':requestHeader});
  }

  deleteSongOfPlaylist(playlistName: any, songId: any){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('jwt'));
    return this.http.delete("http://localhost:9002/api/v2/user/"+playlistName+"/"+songId,{'headers':requestHeader})
  }

  deletePlaylist(playlistName: any){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('jwt'));
    return this.http.delete("http://localhost:9002/api/v2/user/"+playlistName,{'headers':requestHeader})
  }
}

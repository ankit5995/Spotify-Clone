import { Component, OnInit } from '@angular/core';
import { Playlist } from '../models/playlist';
import { RouteService } from '../services/route.service';
import { TrackService } from '../services/track.service';
import { Song } from '../models/song';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-playlist',
  templateUrl: './create-playlist.component.html',
  styleUrls: ['./create-playlist.component.css']
})

export class CreatePlaylistComponent implements OnInit{


  arr = new Map<string,boolean>;


  songs : Array<Song> = [];
  constructor(
    private trackService: TrackService,
    private route: RouteService,
    private activateRoute: ActivatedRoute

  ){}

  ngOnInit(): void {
    this.trackService.getTracks().subscribe(data => this.songs = data)
    this.activateRoute.paramMap.subscribe(param => {
      this.playlist.playListName = param.get("id") ?? ""
    })
  }

  selectTrack(track:any){
    this.playlist.songList?.push(track)
    this.arr.set(track.id,true)
  }

  removeTrack(id:any){
    this.playlist.songList = this.playlist.songList?.filter(x => x.songId !== id)
    this.arr.set(id,false)
    console.log(this.arr.get(id))
  }

  isAdded(id:any){
    return this.arr.get(id)
  }

  navigateTo() {
    this.route.navigateToHome();
  }



  playlist : Playlist = {
    playListName: "",
    songList:[]
  }

  savePlaylist(){
    console.log(this.playlist)
    console.log(this.playlist.songList)
    this.trackService.savePlaylist(this.playlist).subscribe(data => alert("added successful"));
  }
}

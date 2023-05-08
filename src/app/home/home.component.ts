import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { TrackService } from '../services/track.service';
import { Song } from '../models/song';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    private trackService: TrackService,
    private activateRoute: ActivatedRoute

  ){}

  tracks: Array<Song> = [];
  name : String ="";
  insidePlaylist : boolean = false;


  ngOnInit(): void {
    this.getAllSongs();
    this.methodToGetPlaylistNames();
  }

  getAllSongs(){
    this.insidePlaylist = false;
    this.trackService.getTracks().subscribe({
      next: data => {
        this.tracks = data;
        console.log(this.tracks);
      },
      error: () => console.log("Error with the request!")
    })
  }


  navigateTo(id: any) {
    this.router.navigate(['/player', id]);
  }


  getSongOfPlaylist(name: any){
        this.trackService.getSongsOfPlaylist(name).subscribe({
          next: data => {this.tracks = data;
                          this.name = name;
                         this.insidePlaylist = true;},
          error: () => console.log("Error with request")
        })

    }


  playlistNames: Array<String> = [];

  methodToGetPlaylistNames(): void {
    this.trackService.getPlaylistNamesOfUser().subscribe({
      next: data=> {
        this.playlistNames = data;
        console.log(this.playlistNames);
      },
      error: () => console.log("Error with the request")
    })
  }

  deleteSongFromPlaylist( songId:any){
    console.log(this.name);
    console.log(songId)
    this.trackService.deleteSongOfPlaylist(this.name,songId).subscribe({
      next: data=> {
        // this.tracks = this.tracks.filter((track)=>{ track.songId != songId})
        // console.log(this.tracks);
        this.getSongOfPlaylist(this.name);
        alert("deleted successfully")
      },
      error: () => console.log("Error with the request")
    })
  }

  deletePlaylist(){
    this.trackService.deletePlaylist(this.name).subscribe({
      next: data =>{
        this.methodToGetPlaylistNames();
        alert("deleted successfully!!!")
      },
      error: () => console.log("Error with the request")
    })

  }

  
}

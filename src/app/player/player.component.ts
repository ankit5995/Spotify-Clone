import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TrackService } from '../services/track.service';
import { Song } from '../models/song';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent {

  constructor(
    private router: Router,
    private trackService: TrackService,
    private activateRoute: ActivatedRoute
  ){}

  song: Song = {
    songId: "",
    songName: "",
    genre: "",
    leadSinger: "",
    artist: "",
    length: 0,
    album: ""
  }

  id = "";
  songPlaying:boolean = false;
  isSongLoaded: boolean = false;

  ngOnInit(): void {
    this.activateRoute.paramMap.subscribe(param => {
      this.id = param.get("id") ?? ""

      this.trackService.getTrackById(this.id).subscribe({
        next: data => {this.song = data},
        error: () => console.log("Error with request")
      })
    })
  }


  audio = new Audio();

  playSound(){
    console.log("asdasd"+this.isSongLoaded)
      if(!this.isSongLoaded){
        this.audio.src="../assets/"+this.id+".wav";
        this.audio.load();
        this.audio.play();
        this.isSongLoaded = true;
      }
      else{
        this.audio.play();
        this.songPlaying = true;

        console.log(this.songPlaying);
      }



  }

  pauseSound(){
    this.audio.pause();
  }

  navigateTo() {
    this.audio.srcObject = null;
    this.router.navigate(['/']);

  }
}

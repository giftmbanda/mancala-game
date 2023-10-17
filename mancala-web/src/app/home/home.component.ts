import { Component, OnInit } from '@angular/core';
import { HomeService } from '../service/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  gameDataSource: any;
  constructor(private homeService: HomeService) {
    this.createGame();
  }

  ngOnInit() { }

  public createGame(): any {
    this.homeService.getGame().subscribe(res => {
      if (res.id) {
        this.gameDataSource = res;
        console.log(this.gameDataSource);
      } else {
        console.log(res.result);
      }
    })
  };

}

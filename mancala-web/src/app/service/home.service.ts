import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { catchError, map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  GAME_URL: string = 'http://localhost:8080/game';

  constructor(private http: HttpClient) { }
  ngOnInit() { }


  public getGame(): Observable<any> {
    return this.http.post<any>(`${this.GAME_URL}/create`, {}).pipe(map((res: any) => {
      return res;
    }), catchError(err => {
      return err;
    }))
  }

  public updateGame(gameId: string, pitId: string): Observable<any> {
    return this.http.get<any>(`${this.GAME_URL}/${gameId}pit/${pitId}`, {}).pipe(map((res: any) => {
      return res;
    }), catchError(err => {
      return err;
    }))
  }

}

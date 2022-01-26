import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = environment.apiURL + "users";
  constructor(private http: HttpClient) { }

  public signUp(body: any) : Observable<any>{
    return this.http.post(this.usersURL, body);
  }

  public getUsers() : Observable<User[]>{
    return this.http.get<User[]>(this.usersURL);
  }
}

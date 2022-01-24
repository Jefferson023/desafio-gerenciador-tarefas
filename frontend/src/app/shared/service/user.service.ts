import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = environment.apiURL + "users";
  constructor(private http: HttpClient) { }

  public signUp(form: any) : Observable<any>{
    return this.http.post(this.usersURL, form);
  }
}

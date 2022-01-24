import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { ResponseAuth } from 'src/app/model/ResponseAuth';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private authURL= environment.apiURL + "auth";
  constructor(private http: HttpClient) { }

  public login(body: any) : Observable<ResponseAuth> {
    return this.http.post<ResponseAuth>(this.authURL, body).pipe(map(res => {
      localStorage.setItem("bearerToken", res.token);
      return res;
    }))
  }

  public logout(){
    localStorage.removeItem("bearerToken");
  }
  
  public isLogged() {
    let token = localStorage.getItem('bearerToken');
    if (token && token.length > 0) {
      return true;
    } else {
      return false;
    }
  }
}

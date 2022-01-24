import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './shared/service/authentication.service';

@Component({
  selector: 'app-root',
  template: `
    <h1 *ngIf="isLogged()"><a href = "" (click) = "logout()" style = "margin-bottom: 40px; display: flex;
    justify-content: flex-end;">Logout</a></h1>
  
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
  constructor(private authService: AuthenticationService, private router: Router){}
  logout(){
    this.authService.logout();
  }
  isLogged(){
    return this.authService.isLogged();
  }
}

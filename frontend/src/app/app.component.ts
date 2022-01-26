import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './shared/service/authentication.service';

@Component({
  selector: 'app-root',
  template: `
    <h1 *ngIf="isLogged()" style = "display: flex; justify-content: flex-start; margin-bottom: 50px;">
      <a href = "" style = "padding-right: 20px;">Listagem</a>
      <a href = "tarefas/cadastro">Cadastro</a>
      <a href = "" (click) = "logout()" style = "position: absolute; right: -1px;">Logout</a>
    </h1>
  
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
  constructor(private authService: AuthenticationService, private router: Router) { }
  logout() {
    this.authService.logout();
  }
  isLogged() {
    return this.authService.isLogged();
  }
}

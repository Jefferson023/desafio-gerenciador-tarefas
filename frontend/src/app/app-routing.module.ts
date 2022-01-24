import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AuthorizationGuard } from './shared/guard/authorization.guard';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  {
    path: '',
    canActivate: [AuthorizationGuard],
    canActivateChild: [AuthorizationGuard],
    children: [
      {
        path: 'tarefas',
        loadChildren: () => import('./pages/tarefas/tarefas.module').then(m => m.TarefasModule)
      },
    ]
  }
]    

const routerOptions: ExtraOptions = {
      anchorScrolling: 'enabled'
    };

@NgModule({
      imports: [RouterModule.forRoot(routes, routerOptions)],
      exports: [RouterModule]
    })
export class AppRoutingModule { }

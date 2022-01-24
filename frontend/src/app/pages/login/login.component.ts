import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/shared/service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;

  constructor(
    formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService : AuthenticationService
  ) {
    this.loginForm = formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
    if (this.authService.isLogged()){
      this.router.navigate(['/']);
    }
  }

  submit() {
    this.submitted = true;
    if (this.loginForm.valid){
      console.log(this.loginForm.value);
      this.authService.login(this.loginForm.value).subscribe(res =>{
        this.router.navigate(['/']);
      })
    }
  }

}

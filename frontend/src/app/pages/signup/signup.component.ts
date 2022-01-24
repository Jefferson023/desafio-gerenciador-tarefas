import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/shared/service/authentication.service';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{

  signForm: FormGroup;
  submitted = false;
  success = false;
  constructor(
    formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private authService: AuthenticationService
  ) {
    this.signForm = formBuilder.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  submit() {
    this.submitted = true;
    if (this.signForm.valid){
      this.userService.signUp(this.signForm.value).subscribe( res => {
        this.success = true;
        this.submitted = false;
        this.signForm.reset();
      }, err => {
        console.log(err);
      })
    }else {
      this.success = false;
    }
  }

  ngOnInit() {
    if (this.authService.isLogged()){
      this.router.navigate(['/']);
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Task } from 'src/app/model/Task';
import { User } from 'src/app/model/User';
import { TaskService } from 'src/app/shared/service/task.service';
import { UserService } from 'src/app/shared/service/user.service';
import { ValidateDeadline } from 'src/app/shared/Validators/deadline.validator';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styles: [
  ]
})
export class CadastroComponent {

  responsavelList: User[] = [];
  prioridadeList = ["Alta", "Média", "Baixa"];
  datemask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];

  isLoading = true;
  taskForm: FormGroup;

  success = false;

  submitted = false;

  constructor(formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    private userService: UserService) {
    let task : Task = this.router.getCurrentNavigation()?.extras.state?.task;
  
    this.taskForm = formBuilder.group({
      numero: new FormControl(task?.numero || ''),
      titulo: new FormControl(task?.titulo || '', [Validators.required]),
      descricao: new FormControl(task?.descricao || '', [Validators.required]),
      responsavel: new FormControl(task?.responsavel || '', [Validators.required]),
      prioridade: new FormControl(task?.prioridade || '', [Validators.required]),
      deadline: new FormControl(task?.deadline || '', [ValidateDeadline])
    });

    this.userService.getUsers().subscribe(res => {
      this.responsavelList = res;
      this.isLoading = false;
    },
      err => console.log(err));
    
  }

  compareUsers(object1: User, object2: User){
    return object1 && object2 && object1.username == object2.username;
  }

  submit() {
    this.success = false;
    this.submitted = true;
  
    if (this.taskForm.valid){
      
      let form = this.taskForm.value;
      form.deadline = new Date(form.deadline).toISOString();

      if (form.numero){

        this.taskService.updateTask(form).subscribe(res => {
          this.success = true;
          this.submitted = false;
          this.taskForm.reset();
        }, err => console.log(err))

      }else {

        this.taskService.registerTask(form).subscribe(res => {
          this.success = true;
          this.submitted = false;
          this.taskForm.reset();
        }, err => console.log(err))
      }
    }
  }

}

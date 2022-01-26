import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { Task } from 'src/app/model/Task';
import { User } from 'src/app/model/User';
import { TaskService } from 'src/app/shared/service/task.service';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styles: [
  ]
})
export class ListagemComponent implements OnInit {
  taskList : Task[] = [];
  responsavelList : User[] = [];
  displayedColumns: string[] = ['numero', 'titulo', 'responsavel', 'acoes'];
  situacaoList  = [{key: "Em andamento", value: 'EM_ANDAMENTO'}, {key: "Concluída", value: 'CONCLUIDA'}];
  isLoading = true;
  searchForm: FormGroup;
  length = 0;

  readonly SITUACAO_CONCLUIDA = "CONCLUIDA";

  constructor(formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    private userService: UserService) {
      this.searchForm = formBuilder.group({
        numero: new FormControl(''),
        tituloDescricao: new FormControl(''),
        responsavel: new FormControl(''),
        situacao: new FormControl(''),
        page: new FormControl('0'),
        size: new FormControl('5')
      });
      this.userService.getUsers().subscribe(res => this.responsavelList = res, 
        err => console.log(err));
    }

  ngOnInit(): void {
    this.submit();
  }

  paginate(event: PageEvent){
    this.searchForm.get("page")?.setValue(event.pageIndex);
    this.searchForm.get("size")?.setValue(event.pageSize);
    this.submit();
  }

  editTask(task : Task) {
    this.router.navigate(["cadastro"], {relativeTo: this.route, state: { task: task }});
  }

  deleteTask(task : Task){
    this.taskService.deleteTask(task.numero).subscribe(res => {
      console.log("removido")
      this.submit();
    }, 
    err => console.log(err));
  }

  concluirTask(task: Task){
    task.situacao = this.SITUACAO_CONCLUIDA;
    this.taskService.updateTask(task).subscribe(res => {
      console.log("atualizada");
    }, err => console.log(err));
  }

  submit(){
    this.isLoading = true;
    console.log(this.searchForm.value);
    this.taskService.searchTask(this.searchForm.value).subscribe((res) => {
      this.taskList = res.content;
      this.isLoading = false;
      this.length = res.totalElements;
    }, 
    err => console.log(err));
  }

}

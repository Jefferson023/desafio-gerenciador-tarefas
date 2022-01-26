
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Page } from 'src/app/model/Page';
import { Task } from 'src/app/model/Task';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private tasksUrl = environment.apiURL + "tasks";
  constructor(private http: HttpClient) { }

  public searchTask(body: any) : Observable<Page<Task>>{
    let httpParams = new HttpParams({ fromObject: body })
    return this.http.get<Page<Task>>(this.tasksUrl, {params: httpParams});
  }

  public registerTask(body: any): Observable<Task> {
    return this.http.post<Task>(this.tasksUrl, body);
  }

  public updateTask(body: any){
    return this.http.put<Task>(this.tasksUrl, body);
  }

  public deleteTask(numero: number){
    return this.http.delete(this.tasksUrl+`/${numero}`);
  }
}

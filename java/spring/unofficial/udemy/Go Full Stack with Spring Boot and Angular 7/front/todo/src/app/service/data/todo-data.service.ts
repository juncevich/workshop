import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Todo } from 'src/app/list-todos/list-todos.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(
    private httpModule: HttpClient
  ) { }

  public retrieveAllTodos(username) {
    return this.httpModule.get<Todo[]>(`http://localhost:8080/users/${username}/todos`);
  }

  deleteTodo(username, id) {
    return this.httpModule.delete(`http://localhost:8080/users/${username}/todos/${id}`);
  }

  retrieveTodo(username, id) {
    return this.httpModule.get<Todo>(`http://localhost:8080/users/${username}/todos/${id}`);
  }
}

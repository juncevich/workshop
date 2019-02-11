import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Todo } from 'src/app/list-todos/list-todos.component';
import { API_URL, TODO_JPA_API_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(
    private httpModule: HttpClient
  ) { }

  public retrieveAllTodos(username) {
    return this.httpModule.get<Todo[]>(`${TODO_JPA_API_URL}/users/${username}/todos`);
  }

  deleteTodo(username, id) {
    return this.httpModule.delete(`${API_URL}/users/${username}/todos/${id}`);
  }

  retrieveTodo(username, id) {
    return this.httpModule.get<Todo>(`${TODO_JPA_API_URL}/users/${username}/todos/${id}`);
  }

  updateTodo(username, id, todo) {
    return this.httpModule.put(`${API_URL}/users/${username}/todos/${id}`, todo);
  }

  createTodo(username, todo) {
    return this.httpModule.post(`${API_URL}/users/${username}/todos`, todo);
  }

}

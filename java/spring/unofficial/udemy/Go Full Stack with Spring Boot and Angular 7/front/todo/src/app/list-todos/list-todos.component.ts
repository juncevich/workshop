import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';

export class Todo {
  constructor(
    public id: number,
    public description: string,
    public done: Boolean,
    public targetDate: Date
  ) {

  }

}

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  todos: Todo[];
  message: String;
  // todos = [
  //   new Todo(1, 'Learn to dance', false, new Date()),
  //   new Todo(2, 'Become an Expert at Angular', false, new Date()),
  //   new Todo(3, 'Visit India', false, new Date())
  // ];

  // todo = {
  //   id: 1,
  //   description: 'Learn to dance'
  // }

  constructor(
    private todoService: TodoDataService

  ) { }

  ngOnInit() {
    this.refreshTodos();
  }

  refreshTodos() {
    this.todoService.retrieveAllTodos('alex').subscribe(
      response => {
        console.log(response);
        this.todos = response;
      }
    );
  }
  deleteTodo(id) {
    console.log(`delete ${id}`);
    this.todoService.deleteTodo('alex', id).subscribe(
      response => {
        console.log(response);
        this.message = `Delete of Todo ${id} Successfull!`;
        this.refreshTodos();
      }
    );
  }
}

import {map} from 'rxjs/operators';
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class TaskService {


    constructor(private http: Http) {
    }

    getTasks() {
        return this
            .http
            .get('/api/tasks')
            .pipe(map(response => response.json()));
    }
}

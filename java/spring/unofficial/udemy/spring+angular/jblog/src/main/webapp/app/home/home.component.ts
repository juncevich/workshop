import { Component, OnInit } from '@angular/core';
import { Post } from 'app/shared/model/post.model';
import { PostService } from 'app/entities/post';
import { HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    posts: Post[] = [];

    constructor(private postService: PostService) {}

    loadAll() {
        this.postService.query().subscribe(
            (res: HttpResponse<Post[]>) => {
                console.log(res.body);
                this.posts = res.body;
            },
            error => {
                console.log(error);
            }
        );
    }

    ngOnInit() {
        console.log('ngOnInit...');
        this.loadAll();
    }
}

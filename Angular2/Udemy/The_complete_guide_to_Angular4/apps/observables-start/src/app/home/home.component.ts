import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

import {Observer} from 'rxjs/Observer';
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  numberObsSubscription: Subscription;
  customObsSubscription: Subscription;

  ngOnDestroy(): void {
    this.numberObsSubscription.unsubscribe();
    this.customObsSubscription.unsubscribe();
  }

  constructor() {
  }

  ngOnInit() {
    const myNumbers = Observable.interval(1000).map((data: number) => {
      return data * 2;
    });
    this.numberObsSubscription = myNumbers.subscribe(
      (number: number) => {
        console.log(number);
      }
    );

    const myObservable = Observable.create((observer: Observer<string>) => {
        setTimeout(
          () => {
            observer.next('first page');
          }, 2000
        );
        setTimeout(
          () => {
            observer.next('second page');
          }, 4000
        );
        setTimeout(
          () => {
            observer.complete();
            // observer.error('This does not work');
          }, 5000
        );
        setTimeout(
          () => {
            observer.next('third page');
          }, 4000
        );
      }
    );

    this.customObsSubscription = myObservable.subscribe(
      (data: string) => {
        console.log(data);
      },
      (error: string) => {
        console.log(error);
      },
      () => {
        console.log('Completed!');
      }
    );

  }

}

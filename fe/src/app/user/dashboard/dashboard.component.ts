import { catchError } from 'rxjs/operators';
import { Observable, throwError, of, Subscription } from 'rxjs';
import { UserDefineService } from './../user-define.service';
import { UserService } from './../user.service';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {
  subscription: Subscription[] = [];
  constructor(
    private userSerive: UserService,
    private userDefineService: UserDefineService
  ) { }

  ngOnInit(): void {
    this.observableApi();
  }

  ngOnDestroy() {
    this.subscription.forEach(i => {
      i.unsubscribe();
    })
  }

  createObservable() {
    return new Observable<any>(ob => {
      ob.next(this.getIdUser());
      ob.complete();
    });
  }

  observableApi() {
    this.subscription.push(
      this.createObservable().subscribe({
        next: value => this.getInfoUser(value).subscribe(res => {
          console.log(res);
        }, err => {
          console.log(err);
        }),
        error: err => console.log(err),
        complete: () => console.log('Observer Done')
      })
    )
  }

  getIdUser() {
    return this.userDefineService.getIdUser();
  }

  getInfoUser(id) {
    console.log(id);
    return this.userSerive.getInfoUser(id);
  }

}

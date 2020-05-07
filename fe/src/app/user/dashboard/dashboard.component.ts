import { Observable } from 'rxjs';
import { UserDefineService } from './../user-define.service';
import { UserService } from './../user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(
    private userSerive: UserService,
    private userDefineService: UserDefineService
  ) { }

  ngOnInit(): void {
    // this.getUserInfo();
    const observable = new Observable<any>(ob => {
      ob.next(this.getId());
      ob.complete();
    });
    observable.subscribe(val => {
      this.getUser(val).subscribe(res => {
        console.log(res);
      }, err => {
        console.log(err);
      })
    }, err => {
      console.log(err);
    }, () => {
      console.log('Observable Subscribe Done => Call Api Get User Infomation');
    });
  }

  getId() {
    return this.userDefineService.getIdUser();
  }

  getUser(id) {
    return this.userSerive.getInfoUser(id);
  }

  getIdUser() {
    return new Promise((resolve, reject) => {
      const id = this.userDefineService.getIdUser();
      if (id) {
        resolve(id)
      } else {
        reject(null);
      }
    });
  }

  async getUserInfo() {
    await this.getIdUser().then(
      res => {
        this.userSerive.getInfoUser(res).subscribe(res => {
          console.log(res);
        }, err => {
          console.log(err);
        });
      }
    )
  }

}

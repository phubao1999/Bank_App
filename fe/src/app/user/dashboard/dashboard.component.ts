import { UserDefineService } from './../user-define.service';
import { UserService } from './../user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  id;
  constructor(
    private userSerive: UserService,
    private userDefineService: UserDefineService
  ) { }

  ngOnInit(): void {
    this.getUserInfo();
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

import { Router } from '@angular/router';
import { Component, OnInit, DoCheck } from '@angular/core';

@Component({
  selector: 'app-user-layout',
  templateUrl: './user-layout.component.html',
  styleUrls: ['./user-layout.component.scss']
})
export class UserLayoutComponent implements OnInit, DoCheck {
  listitems = [
    {
      icon: 'fas fa-comments',
      title: 'Chat',
      link: '/chat'
    },
    {
      icon: 'fas fa-tachometer-alt',
      title: 'Dash Board',
      link: '/dash-board'
    },
    {
      icon: 'fas fa-money-check',
      title: 'Tranffer Money',
      link: 'tranffer-money'
    },
    {
      icon: 'fas fa-bookmark',
      title: 'Transaction History',
      link: 'trans-history'
    },
    {
      icon: 'fas fa-users',
      title: 'Update Profile',
      link: 'update-profile'
    }
  ];
  activeFirst;
  userName: String;
  constructor(
    public router: Router
  ) { }

  ngOnInit() {
    this.getInfo();
  }

  ngDoCheck(): void {
    this.activeFirst = window.location.pathname;
  }

  toggleMenu() {
    const menu = document.querySelector(".nav-bottom");
    const outLet = document.querySelector(".out-let");
    menu.classList.toggle("hide");
    outLet.classList.toggle("move");
  }

  toNewPage(values) {
    this.router.navigate([`${values.link}`]);
  }

  back() {
    this.router.navigate(['/']);
  }

  getInfo() {
    const infoUser = JSON.parse(localStorage.getItem('user-info'));
    this.userName = infoUser['username'];
  }

  logout() {
    localStorage.removeItem('user-info');
    localStorage.removeItem('token');
    location.reload();
  }

}

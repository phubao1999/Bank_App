import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-layout',
  templateUrl: './user-layout.component.html',
  styleUrls: ['./user-layout.component.scss']
})
export class UserLayoutComponent implements OnInit {
  listitems = [
    {
      icon: 'fas fa-users',
      title: 'Dash Board',
      link: '/dash-board'
    }
  ];
  constructor(
    public router: Router
  ) { }

  ngOnInit() {
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

}

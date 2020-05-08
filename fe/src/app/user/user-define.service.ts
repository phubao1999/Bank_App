import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserDefineService {

  constructor() { }

  getIdUser() {
    const infoUser = localStorage.getItem('user-info');
    const infoUserObj = JSON.parse(infoUser);
    return infoUserObj['id_user'];
  }
}

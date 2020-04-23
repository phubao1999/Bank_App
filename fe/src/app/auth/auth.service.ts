import { environment as config } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  login(body) {
    return this.http.post(`${config.hostServer}/user/login`, body);
  }

  register(body) {
    return this.http.post(`${config.hostServer}/user/register`, body);
  }

}

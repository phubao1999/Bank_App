import { environment as config } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  register(body) {
    return this.http.post(`${config.API_SERVER}/user/register`, body);
  }

  createOtp(body) {
    return this.http.post(`${config.API_SERVER}/user/send-otp`, body);
  }
}

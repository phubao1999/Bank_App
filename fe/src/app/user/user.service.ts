import { HttpClient } from '@angular/common/http';
import { BaseService } from './../shared/services/helpers/base.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService extends BaseService {

  constructor(
    public http: HttpClient
  ) { 
    super(http);
  }

  getInfoUser(id) {
    return this.getData(`user/info?id=${id}`);
  }

}

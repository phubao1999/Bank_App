import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { environment as config } from '../../../../environments/environment';
import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { tap, map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable()
export class BaseService {

    OBJECT_ERROR = { code: 400, message: 'Please check your internet connection and try again' };

    constructor(protected http: Http) { }

    private getUrlApi() {
        return config.hostServer;
    }

    protected getData(path: string): Observable<any> {
        const options = this.getHeaders();
        return this.http.get(`${this.getUrlApi()}/${path}`, options)
            .pipe(
                map(res => {
                    return res.json();
                }),
                catchError(err => this.getError(err))
            );
    }

    protected postData(path: string, body?: any, headersPairs?: any): Observable<any> {
        const options = this.getHeaders(headersPairs);
        return this.http.post(`${this.getUrlApi()}/${path}`, body, options)
            .pipe(
                map(res => {
                    return res.json();
                }),
                catchError(err => this.getError(err))
            );
    }


    protected delete(path, headersPairs?: any) {
        const options = this.getHeaders(headersPairs);
        return this.http.delete(`${this.getUrlApi()}/${path}`, options)
            .pipe(
                map(res => {
                    return res.json();
                }),
                catchError(err => this.getError(err))
            );
    }

    getError(err) {
        if (!err.json().message) {
            return throwError(this.OBJECT_ERROR);
        }
        return throwError(err.json());
    }

    private getHeaders(headersPairs?: any) {
        const headers = new Headers();
        const token = localStorage.getItem('token');
        if (token) {
            headers.append('token', token);
        }
        headers.append('money', 'USD');
        headers.append('locale', localStorage.getItem('locale') || 'en');
        headers.append('Content-Type', 'application/json');
        if (headersPairs) {
            _.forEach(headersPairs, (value, key) => {
                headers.append(key, value);
            });
        }
        return new RequestOptions({ headers });

    }
}

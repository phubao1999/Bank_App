import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment as config } from '../../../environments/environment';

@Injectable()
export class BaseService {

    OBJECT_ERROR = { code: 400, message: 'Please check your internet connection and try again' };

    constructor(protected http: HttpClient) { }

    private getUrlApi() {
        return config.API_SERVER;
    }

    protected getData(path: string): Observable<any> {
        const options = this.getHeaders();
        return this.http.get(`${this.getUrlApi()}/${path}`, options)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(err => this.getError(err))
            );
    }

    protected postData(path: string, body?: any, headersPairs?: any): Observable<any> {
        const options = this.getHeaders(headersPairs);
        return this.http.post(`${this.getUrlApi()}/${path}`, body, options)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(err => this.getError(err))
            );
    }


    protected delete(path, headersPairs?: any) {
        const options = this.getHeaders(headersPairs);
        return this.http.delete(`${this.getUrlApi()}/${path}`, options)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(err => this.getError(err))
            );
    }

    getError(err) {
        if (!err.message) {
            return throwError(this.OBJECT_ERROR);
        }
        return throwError(err);
    }

    private getHeaders(headersPairs?: any) {
        const httpOptions = {
            headers: new HttpHeaders()
        }
        const token = localStorage.getItem('token');
        if (token) {
            const realToken = `Bearer ${token}`;
            httpOptions.headers = httpOptions.headers.set('Authorization', realToken);
        }
        httpOptions.headers = httpOptions.headers.set('locale', localStorage.getItem('locale') || 'ja');
        httpOptions.headers = httpOptions.headers.set('Content-Type', 'application/json');

        if (headersPairs) {
            headersPairs.forEach(e => {
                httpOptions.headers = httpOptions.headers.set(e.key, e.value);
            });
        }
        return httpOptions;
    }
}

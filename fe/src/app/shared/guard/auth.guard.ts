import { Observable, Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';


@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private router: Router) { }
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> | boolean {
        const subject = new Subject<boolean>();
        if (localStorage.getItem('token')) {
            return true;
        } else {
            this.router.navigate(['/auth']);
            return false;
        }
    }
}

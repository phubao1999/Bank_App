import { AuthComponent } from './auth.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

export const routes: Routes = [
    {
        path: '', component: AuthComponent
    }
];

export const AuthRouting: ModuleWithProviders = RouterModule.forChild(routes);

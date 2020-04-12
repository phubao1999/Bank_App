import { UserLayoutComponent } from './../shared/layout/user-layout/user-layout.component';
import { UserComponent } from './user.component';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '', component: UserLayoutComponent,
        children: [
            {path: '', component: UserComponent}
        ]
    }
];

export const UserRouting: ModuleWithProviders = RouterModule.forChild(routes);

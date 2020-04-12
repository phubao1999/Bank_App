import { AdminLayoutComponent } from './../shared/layout/admin-layout/admin-layout.component';
import { AdminComponent } from './admin.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';

export const routes: Routes = [
    {
        path: '', component: AdminLayoutComponent,
        children: [
            { path: '', component: AdminComponent }
        ]
    }
];

export const AdminRouting: ModuleWithProviders = RouterModule.forChild(routes);

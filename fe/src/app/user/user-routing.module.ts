import { AuthGuard } from './../shared/guard/auth.guard';
import { UserLayoutComponent } from './../shared/layout/user-layout/user-layout.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dash-board',
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'dash-board',
    component: UserLayoutComponent,
    loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [AuthGuard]
  }
];

export const UserRouting: ModuleWithProviders = RouterModule.forChild(routes);

import { AuthGuard } from './shared/guard/auth.guard';
import { AuthComponent } from './auth/auth.component';
import { UserLayoutComponent } from './shared/layout/user-layout/user-layout.component';
import { PageErrorComponent } from './page-error/page-error/page-error.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

export const APP_ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  },
  {
    path: 'auth',
    component: AuthComponent,
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: '**',
    component: PageErrorComponent,
  }
];
export const Routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);

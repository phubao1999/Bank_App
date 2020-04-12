import { AuthComponent } from './auth/auth.component';
import { UserLayoutComponent } from './shared/layout/user-layout/user-layout.component';
import { UserComponent } from './user/user.component';
import { environment } from './../environments/environment';
import { PageErrorComponent } from './page-error/page-error/page-error.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { routes as adminRouter } from './admin/admin-routing.module';
import { routes as userRouter } from './user/user-routing.module';

export const APP_ROUTES: Routes = [
  {
    path: '',
    component: UserLayoutComponent
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

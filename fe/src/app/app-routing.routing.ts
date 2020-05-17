import { UserLayoutComponent } from './shared/layout/user-layout/user-layout.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageErrorComponent } from './page-error/page-error/page-error.component';
import { AuthComponent } from './auth/auth.component';

const APP_ROUTES: Routes = [
  {
    path: '',
    component: UserLayoutComponent,
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
// export const Routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);
@NgModule({
  imports: [RouterModule.forRoot(APP_ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

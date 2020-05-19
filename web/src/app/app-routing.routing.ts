import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PrimaryLayoutComponent } from './shared/layout/primary-layout/primary-layout.component';
import { AuthLayoutComponent } from './shared/layout/auth-layout/auth-layout.component';


const APP_ROUTES: Routes = [
  {
    path: '',
    component: PrimaryLayoutComponent,
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  },
  {
    path: 'auth',
    component: AuthLayoutComponent,
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(APP_ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

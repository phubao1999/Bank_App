import { UserComponent } from './user.component';
import { ChatComponent } from './chat/chat.component';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './../shared/guard/auth.guard';
import { UserLayoutComponent } from './../shared/layout/user-layout/user-layout.component';

const routes: Routes = [
  {
    path: '',
    component: UserComponent
  },
  {
    path: 'dash-board',
    loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'chat',
    loadChildren: () => import('./chat/chat.module').then(m => m.ChatModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'tranffer-money',
    loadChildren: () => import('./tranffer-monney/tranffer-money.module').then(m => m.TranfferMoneyModule),
    canActivate: [AuthGuard]
  }
];

// export const UserRouting: ModuleWithProviders = RouterModule.forChild(routes);
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

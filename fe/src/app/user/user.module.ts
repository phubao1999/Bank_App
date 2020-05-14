import { UserRouting } from './user-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import { TranfferMonneyComponent } from './tranffer-monney/tranffer-monney.component';
import { ChatComponent } from './chat/chat.component';

@NgModule({
  declarations: [
    DashboardComponent,
    UpdateProfileComponent,
    TransactionHistoryComponent,
    TranfferMonneyComponent,
    ChatComponent
  ],
  imports: [
    CommonModule,
    UserRouting
  ]
})
export class UserModule { }

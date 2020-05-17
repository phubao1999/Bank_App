import { RouterModule } from '@angular/router';
import { environment as config } from './../environments/environment';
import { AuthGuard } from './shared/guard/auth.guard';
import { AppRoutingModule } from './app-routing.routing';
import { AdminModule } from './admin/admin.module';
import { UserModule } from './user/user.module';
import { SharedServicesModule } from './shared/shared-service.module';
import { SharedModule } from './shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { PageErrorComponent } from './page-error/page-error/page-error.component';
import { AdminComponent } from './admin/admin.component';
import { AuthComponent } from './auth/auth.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import { SocketIoModule, SocketIoConfig } from 'ngx-socket-io';

const configSocket: SocketIoConfig = { url: config.socketServer, options: {} };

@NgModule({
  declarations: [
    AppComponent,
    PageErrorComponent,
    AdminComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    SharedServicesModule,
    UserModule,
    AdminModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatInputModule,
    SocketIoModule.forRoot(configSocket),
    RouterModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './core/footer/footer.component';
import { HeaderComponent } from './core/header/header.component';
import { PrimaryLayoutComponent } from './layout/primary-layout/primary-layout.component';
import { AuthLayoutComponent } from './layout/auth-layout/auth-layout.component';



@NgModule({
  declarations: [
    FooterComponent,
    HeaderComponent,
    PrimaryLayoutComponent,
    AuthLayoutComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    FooterComponent,
    HeaderComponent,
    PrimaryLayoutComponent,
    AuthLayoutComponent
  ]
})
export class SharedModule { }

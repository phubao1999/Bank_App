import { ChatService } from './chat.service';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatRoutingModule } from './chat-routing.module';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    FormsModule,
  ],
  providers: [ChatService]
})
export class ChatModule { }

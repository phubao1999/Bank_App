import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
  text;
  chatArr: string[] = [];
  value;
  binding;
  constructor(
    private chatService: ChatService
  ) { }

  ngOnInit(): void {
    this.getMess();
  }

  inputValue(e) {
    this.text = e.target.value;
    if (e.keyCode === 13) {
      this.sendValue();
    }
  }

  sendValue() {
    this.emitValue();
  }

  emitValue() {
    this.chatService.emitMess(this.text);
  }

  getMess() {
    this.chatService.getMess().subscribe(res => {
      this.chatArr.push(res);
      console.log(this.chatArr);
      this.resetTextArea();
    });
  }

  resetTextArea() {
    const text = document.getElementById('area');
    return text['value'] = '';
  }
}
import { Observable } from 'rxjs';
import { ChatService } from './chat.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
  text;
  chatArr: [] = [];
  value;
  constructor(
    private chatService: ChatService
  ) { }

  ngOnInit(): void {
    this.chatService.setupSocketConnection();
  }

  inputValue(e) {
    this.text = e.target.value;
    if (e.keyCode === 13) {
      this.sendValue();
    }
  }

  sendValue() {
    this.createObservale().subscribe({
      next: () => {
        console.log('Step 2');
        this.getMess();
      }, error: err => {
        console.log(err);
      }, complete: () => {
        this.resetTextArea();
      }
    });
  }

  createObservale() {
    return new Observable<any>(ob => {
      ob.next(this.emitValue());
      ob.complete();
    });
  }

  emitValue() {
    this.chatService.emitMess(this.text);
  }

  getMess() {
    console.log(this.chatService.getMess());
  }

  resetTextArea() {
    const text = document.getElementById('area');
    return text['value'] = '';
  }
}

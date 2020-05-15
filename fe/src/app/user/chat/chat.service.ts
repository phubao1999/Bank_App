import { environment as config } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import * as io from 'socket.io-client';

@Injectable()
export class ChatService {
  socket;
  constructor() { }

  setupSocketConnection() {
    return io(config.socketServer);
    // this.socket.emit('emit message', 'Hello World');
    // this.socket.on('my broadcast', (data: string) => {
    //   console.log(data);
    // });
  }

  emitMess(body) {
    this.setupSocketConnection().emit('emit message', body);
  }

  getMess() {
    const mess = this.setupSocketConnection().on('my broadcast', (data: string) => {
      return data;
    });
    return mess;
  }
}

import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Socket } from 'ngx-socket-io';

@Injectable()
export class ChatService {
  socket;
  constructor(
    private socketModule: Socket
  ) { }

  emitMess(body) {
    this.socketModule.emit('emit message', body);
  }

  getMess() {
    return this.socketModule.fromEvent('my broadcast').pipe(
      map(data => data.toString())
    );
  }
}

const express = require('express');
const app = express();
const http = require('http').createServer(express);
const io = require('socket.io')(http);
const port = 3000;

app.get('/', (req, res) => {
    res.render('Hello World!');
});

io.on('connection', socket => {
    console.log('User Is Connected');
    socket.on('disconnect', () => {
        console.log('User Is Disconnect');
    })
    socket.on('emit message', msg => {
        console.log(`Message: ${msg}`);
        io.emit('my broadcast', `Server: ${msg}`);
    });
});

http.listen(port, () => {
    console.log(`Server Is Listening On Port ${port}`);
})
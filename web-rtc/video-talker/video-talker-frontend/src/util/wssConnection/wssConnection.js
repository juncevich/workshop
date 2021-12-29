import socketClient from 'socket.io-client'

const SERVER = 'http://localhost:5001'

let socket;

export const connectWithWebSocket =() => {
    socket = socketClient(SERVER)

    socket.on('connection', ()=> {
        console.log('Succesfully connected with ws server')
        console.log(socket.id)
    })
}

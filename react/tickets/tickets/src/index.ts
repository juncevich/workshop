import * as mongoose from "mongoose";
import {app} from "./app";


const start = async () => {
    let jwtKey = process.env.JWT_KEY;
    if (!jwtKey) {
        throw new Error('JWT key must be defined!')
    }
    try {
        await mongoose.connect('mongodb://auth-mongo-srv:27017/auth', {
            useNewUrlParser: true,
            useUnifiedTopology: true,
            useCreateIndex: true
        })
        console.log('Connected to Mongo DB.')
    } catch (err) {
        console.error(err);
    }
    app.listen(3000, () => {
        console.log('Listening on port 3000!');
    })

};

start();

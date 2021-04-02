import express from 'express';
import {json} from 'body-parser';
import {currentUserRouter} from "./routes/current-user";
import {signInRouter} from "./routes/signin";
import {signUpRouter} from "./routes/signup";
import {signOutRouter} from "./routes/signout";
import {errorHandler} from "./middlewares/error-handler";
import {NotFoundError} from "./errors/not-found-error";
import 'express-async-errors';
import mongoose from "mongoose";
import cookieSession from "cookie-session";

const app = express();
app.set('trust proxy', true);
app.use(json())
app.use(
    cookieSession({
        signed: false,
        secure: true
    })
)

app.use(currentUserRouter);
app.use(signInRouter);
app.use(signUpRouter);
app.use(signOutRouter);

app.all('*', async (req, res) => {
    throw new NotFoundError();
})

app.use(errorHandler);

const start = async () => {
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


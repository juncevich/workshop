import express from 'express';
import 'express-async-errors';
import {json} from 'body-parser';
import cookieSession from 'cookie-session';
import {createTicketRouter} from "./routes/new";
import {NotFoundError, } from "../../auth/src/errors/not-found-error";
import {currentUser} from "../../auth/src/middlewares/current-user";
import {errorHandler} from "../../auth/src/middlewares/error-handler";

const app = express();
app.set('trust proxy', true);
app.use(json());
app.use(
    cookieSession({
        signed: false,
        secure: process.env.NODE_ENV !== 'test'
    })
);
app.use(currentUser)
app.use(createTicketRouter)

app.all('*', async (req, res) => {
    throw new NotFoundError();
});

app.use(errorHandler);

export {app};

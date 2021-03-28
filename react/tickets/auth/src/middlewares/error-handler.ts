import {NextFunction, Request, Response} from "express";

import {RequestValidationError} from "../errors/request-validation-error";
import {DatabaseConnectionError} from "../errors/database-connection-error";

export const errorHandler = (err: Error, req: Request, res: Response, next: NextFunction) => {

    if (err instanceof RequestValidationError){
        console.log("Handling request as RequestValidationError")
    }

    if (err instanceof DatabaseConnectionError){
        console.log("Handling request as DatabaseConnectionError")
    }
    console.log('Something wrong', err);

    res.status(400).send({message: 'Something went wrong'});


};

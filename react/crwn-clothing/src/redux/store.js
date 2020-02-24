import {applyMiddleware, createStore} from "redux";
import {persistStore} from "redux-persist";

import rootReducer from './root-reducer';
import createSagaMiddleware from 'redux-saga'
import {fetchCollectionsStart} from "./shop/shop.sagas";

const sagaMiddleware = createSagaMiddleware();
const middlewares = [sagaMiddleware];

export const store = createStore(rootReducer, applyMiddleware(...middlewares));

sagaMiddleware.run(fetchCollectionsStart);
export const persistor = persistStore(store);

export default {store, persistor};


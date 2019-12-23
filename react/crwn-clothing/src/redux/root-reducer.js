import {combineReducers} from "redux";

import userReducer from "./user/user.reducer";
import cartReducer from "./cart/cart.reducer";
import direcotryReducer from "./directory/directory.reducer";
import {persistReducer} from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
    key: 'root',
    storage,
    whitelist: ['cart']
};

const rootReducer = combineReducers({
    user: userReducer,
    cart: cartReducer,
    directory: direcotryReducer
});
export default persistReducer(persistConfig, rootReducer);
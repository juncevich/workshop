import './App.css';
import {useEffect} from "react";
import {connectWithWebSocket} from "./util/wssConnection/wssConnection";

function App() {

    useEffect(() => {
        connectWithWebSocket();
    }, [])
    return (
        <div className="App">
            Hello world!
        </div>
    );
}

export default App;

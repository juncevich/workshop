import {CORE_CONCEPTS} from './data.js'

import Header from './components/Header/Header.jsx'
import CoreConcept from './components/CoreConcept.jsx'
import TapButton from "./components/TapButton";


function App() {
    return (
        <div>
            <Header/>
            <main>
                <section id="core-concepts">
                    <ul>
                        <CoreConcept {...CORE_CONCEPTS[0]}/>
                        <CoreConcept {...CORE_CONCEPTS[1]}/>
                        <CoreConcept {...CORE_CONCEPTS[2]}/>
                        <CoreConcept {...CORE_CONCEPTS[3]}/>
                    </ul>
                </section>
                <section id="examples">
                    <h2>Examples</h2>
                    <menu>
                        <TapButton>Components</TapButton>
                        <TapButton>JSX</TapButton>
                        <TapButton>Props</TapButton>
                        <TapButton>State</TapButton>
                    </menu>
                </section>
            </main>
        </div>
    );
}

export default App;

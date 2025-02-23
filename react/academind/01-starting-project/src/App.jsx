import {CORE_CONCEPTS} from './data.js'

import Header from './components/Header/Header.jsx'
import CoreConcept from './components/CoreConcept.jsx'


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
                <h2>Test Time to get started!</h2>
            </main>
        </div>
    );
}

export default App;

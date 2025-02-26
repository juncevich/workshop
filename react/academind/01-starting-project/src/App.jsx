import {useState} from 'react'

import {CORE_CONCEPTS, EXAMPLES} from './data.js'

import Header from './components/Header/Header.jsx'
import CoreConcept from './components/CoreConcept.jsx'
import TapButton from "./components/TapButton";


function App() {
    const [selectedTopic, setSelectedTopic] = useState();

    function handleSelect(selectedButton) {
        setSelectedTopic(selectedButton);
        console.log(selectedButton);
    }

    let tabContent = <p>Please select a topic</p>
    if (selectedTopic) {
        tabContent = (
            <div id="tab-content">

                <h3>{EXAMPLES[selectedTopic].title}</h3>
                <p>{EXAMPLES[selectedTopic].description}</p>
                <pre>
                            <code>
                                {EXAMPLES[selectedTopic].code}
                            </code>
                        </pre>
            </div>
        )
    }

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
                        <TapButton onSelect={() => handleSelect('components')}>Components</TapButton>
                        <TapButton onSelect={() => handleSelect('jsx')}>JSX</TapButton>
                        <TapButton onSelect={() => handleSelect('props')}>Props</TapButton>
                        <TapButton onSelect={() => handleSelect('state')}>State</TapButton>
                    </menu>
                    {tabContent}

                </section>
            </main>
        </div>
    );
}

export default App;

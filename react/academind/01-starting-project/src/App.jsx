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
                        {CORE_CONCEPTS.map(coreConcept => <CoreConcept key={coreConcept.title} {...coreConcept}/>)}
                    </ul>
                </section>
                <section id="examples">
                    <h2>Examples</h2>
                    <menu>
                        <TapButton isSelected={selectedTopic === 'components'}
                                   onSelect={() => handleSelect('components')}>Components</TapButton>
                        <TapButton isSelected={selectedTopic === 'jsx'}
                                   onSelect={() => handleSelect('jsx')}>JSX</TapButton>
                        <TapButton isSelected={selectedTopic === 'props'}
                                   onSelect={() => handleSelect('props')}>Props</TapButton>
                        <TapButton isSelected={selectedTopic === 'state'}
                                   onSelect={() => handleSelect('state')}>State</TapButton>
                    </menu>
                    {tabContent}

                </section>
            </main>
        </div>
    );
}

export default App;

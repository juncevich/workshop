import TapButton from "./TapButton";
import {useState} from "react";
import {EXAMPLES} from "../data";
import Section from "./Section";
import Tabs from "./Tabs";

export default function Examples() {
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
        <Section title="Examples" id="examples">
            <Tabs buttons={
                <>
                    <TapButton isSelected={selectedTopic === 'components'}
                               onClick={() => handleSelect('components')}>Components</TapButton>
                    <TapButton isSelected={selectedTopic === 'jsx'}
                               onClick={() => handleSelect('jsx')}>JSX</TapButton>
                    <TapButton isSelected={selectedTopic === 'props'}
                               onClick={() => handleSelect('props')}>Props</TapButton>
                    <TapButton isSelected={selectedTopic === 'state'}
                               onClick={() => handleSelect('state')}>State</TapButton>
                </>
            }>
                {tabContent}
            </Tabs>
        </Section>
    );
}

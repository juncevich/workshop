import TapButton from "./TapButton";

export default function Tabs({children, buttons}) {
    return (
        <>
            <menu>
                {buttons}
            </menu>
            {children}
        </>
    );
}

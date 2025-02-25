export default function TapButton({children}) {
    function handleClick() {
        console.log('Test');
    }


    return (
        <li>
            <button onClick={handleClick}>{children}</button>
        </li>
    );
}

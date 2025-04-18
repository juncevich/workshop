const Select = (props: any) => {
    return (
        <div className="mb-3">
            <label className="form-label" htmlFor={props.name}>
                {props.title}
            </label>
            <select
                className="form-select"
                name={props.name}
                id={props.name}
                value={props.value}
                onChange={props.onChange}
            >
                <option value="">{props.placeholder}</option>
                {props.options.map((option: any) => (
                    <option
                        key={option.id}
                        value={option.id}
                    >
                        {option.value}
                    </option>
                ))}
            </select>
            <div className={props.errorDiv}>{props.errorMsg}</div>
        </div>
    )
}

export default Select

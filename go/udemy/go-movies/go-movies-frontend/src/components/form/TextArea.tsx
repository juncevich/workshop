const TextArea = (props: any) => {
    return (
        <div className="mb-3">
            <label className="form-label" htmlFor={props.name}>{props.title}</label>
            <textarea
                className="form-control"
                id={props.name}
                name={props.name}
                value={props.value}
                onChange={props.onChange}
                rows={props.rows}
            />

            <div className={props.errorDiv}>{props.errorMsg}</div>
        </div>
    )
}

export default TextArea
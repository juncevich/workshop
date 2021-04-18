export default () => {
    return <form>
        <h1>Sign up</h1>
        <div className="form-group">
            <label>Email address</label>
            <input className="form-control"/>
        </div>

        <div className="form-group">
            <label>Password</label>
            <input className="form-control" type="password"/>
        </div>
        <button className='btn btn-primary'>Signup</button>
    </form>;
}
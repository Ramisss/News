import React from 'react';
import {useHistory, withRouter} from "react-router-dom";

const EnterTo = () => {
const history = useHistory()
    function signIn() {
        history.push('/login')
    }

    function signUp() {
        history.push('/register')
    }

    return (
        <div>
           <button onClick={signUp}>Sign up</button>
           <button onClick={signIn}>Sign in</button>
        </div>
    );
};

export default EnterTo;

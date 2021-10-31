import React, {Component} from 'react';
import RegUserService from "../service/RegUserService";


class SignUp extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: '',
            firstName: '',
            userName: '',
            email: '',
            phoneNumber: '',
            password: '',
            age: ''

        }
        this.handleFirstName = this.handleFirstName.bind(this);
        this.handleUserName = this.handleUserName.bind(this);
        this.handleEmail = this.handleEmail.bind(this);
        this.handlePassword = this.handlePassword.bind(this);
        this.handlePhoneNumber = this.handlePhoneNumber.bind(this);
        this.saveUser = this.saveUser.bind(this);

    }


    handleFirstName = (e) => {
        this.setState({firstName: e.target.value})
    }


    handleUserName = (e) => {
        this.setState({userName: e.target.value})
        console.log(this.state.userName)
    }

    handleEmail = (e) => {
        this.setState({email: e.target.value})
        console.log(this.state.email)

    }

    handlePassword = (e) => {
        this.setState({password: e.target.value})
    }

    handleAge = (e) => {
        this.setState({age: e.target.value})
    }

    handlePhoneNumber = (e) => {
        this.setState({phoneNumber: e.target.value})
    }

    saveUser = (e) => {
        // e.preventDefault();
        let user = {
            firstName: this.state.firstName,
            userName: this.state.userName,
            email: this.state.email,
            phoneNumber: this.state.phoneNumber,
            password: this.state.password,
            age: this.state.age
        }
        console.log(user)
        JSON.stringify(user);
        RegUserService.addUser(user).then(res => {
            // localStorage.setItem('clickToken', 'Bearer '+JSON.stringify(res.data))
            this.props.history.push('/login')
            alert("go to login");
            console.log(res);
        })
    }

    // componentDidMount(e) {
    //     e.preventDefault();
    //
    // }


    render() {
        return (
            <div className="col-md-12">

                <div className="row pt-4">
                    <div className="card col-md-6 offset-md-3 offset-md-3 ">

                        <div className="card-body ">
                            <form>

                                <div className="form-group">
                                    <label>First name: </label>
                                    <input placeholder="First name" name="firstName" required={true}
                                           className="form-control"
                                           value={this.state.firstName} onChange={this.handleFirstName}/>
                                </div>

                                <div className="form-group">
                                    <label> User name: </label>
                                    <input placeholder="User name" name="userName" required={true}
                                           className="form-control"
                                           value={this.state.userName} onChange={this.handleUserName}/>
                                </div>

                                <div className="form -group">
                                    <label>Email: </label>
                                    <input placeholder="email" type="email" name="email" required
                                           className="form-control"
                                           value={this.state.email} onChange={this.handleEmail}/>

                                </div>

                                <div className="form-group">
                                    <label>Phone number: </label>
                                    <input placeholder="phone number" name="phoneNumber"
                                           className="form-control"
                                           value={this.state.phoneNumber} onChange={this.handlePhoneNumber}/>
                                </div>

                                <div className="form-group">
                                    <label>Password: </label>
                                    <input placeholder="Password" name="password" required className="form-control"
                                           value={this.state.password} onChange={this.handlePassword}/>
                                </div>

                                <div className="form-group">
                                    <label>Your age: </label>
                                    <input placeholder="your age" name="age" required className="form-control"
                                           value={this.state.age} onChange={this.handleAge}/>
                                </div>

                                <button className="btn btn-success" onClick={this.saveUser}>
                                    Sign In
                                </button>

                                <button className="btn btn-danger" >
                                    Cancel
                                </button>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}

export default SignUp;
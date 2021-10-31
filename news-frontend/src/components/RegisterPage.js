import React from 'react';

const RegisterPage = () => {
    return (
        <div>
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
                                    <label>Last name: </label>
                                    <input placeholder="Last name" name="lastName" required className="form-control"
                                           value={this.state.lastName} onChange={this.handleLastName}/>
                                </div>

                                <div className="form -group">
                                    <label>Email: </label>
                                    <input placeholder="email"  type="email" name="email" required className="form-control"
                                           value={this.state.email} onChange={this.handleEmail}/>

                                </div>

                                <div className="form-group">
                                    <label>Password: </label>
                                    <input placeholder="Password" name="password" required className="form-control"
                                           value={this.state.password} onChange={this.handlePassword}/>
                                </div>

                                <div className="form-group">
                                    <label>Phone number: </label>
                                    <input placeholder="phone number" name="phoneNumber"
                                           className="form-control"
                                           value={this.state.phoneNumber} onChange={this.handlePhoneNumber}/>

                                </div>

                                <button className="btn btn-success">
                                    Sign In
                                </button>

                                <button className="btn btn-danger" onClick={this.saveUser}>
                                    Cancel
                                </button>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
};

export default RegisterPage;

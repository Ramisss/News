// import React, {useState} from "react";
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import RegisterPage from "./components/RegisterPage";
import LoginPage from "./components/LoginPage";
import SignUp from "./components/SignUp";



function App() {

  return (
      <div className="wrapper">
          <h1>Application</h1>
          <BrowserRouter>
              <Switch>
                  <Route path="/register" >
                      <SignUp />
                  </Route>
                  <Route path="/login">
                      <LoginPage />
                  </Route>
              </Switch>
          </BrowserRouter>
      </div>
  );
}

export default App;

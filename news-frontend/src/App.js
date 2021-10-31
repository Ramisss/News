import React from "react";
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import RegisterPage from "./components/RegisterPage";
import LoginPage from "./components/LoginPage";


function App() {
  return (
      <div className="wrapper">
          <h1>Application</h1>
          <BrowserRouter>
              <Switch>
                  <Route path="/register">
                      <RegisterPage />
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

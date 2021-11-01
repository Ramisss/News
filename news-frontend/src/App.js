import { Route, Switch} from 'react-router-dom';
import SignUp from "./components/SignUp";
import SignIn from "./components/SignIn";
import Dashboard from "./components/Dashboard";
import EnterTo from "./components/EnterTo";



function App() {

  return (
      <div className="wrapper">
          <h1>Application</h1>
          <EnterTo/>

              <Switch>
                  <Route path="/register" >
                      <SignUp />
                  </Route>
                  <Route path="/login">
                      <SignIn/>
                  </Route>
                  <Route path="/dashboard">
                      <Dashboard/>
                  </Route>
              </Switch>
      </div>
  );
}

export default App;

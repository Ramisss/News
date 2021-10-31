import axios from "axios";

const BASE_URL = "http://localhost:8081/api/auth/register";
const BASE_URL_LOGIN = "http://localhost:8081/api/auth/login";


class RegUserService {

    addUser(user) {
        console.log(user)
        return axios.post(BASE_URL, user)
    }


    logUser(e){
        console.log(e)
            return axios.post(BASE_URL_LOGIN, {email:e.target.value.email,password:e.target.value}).then(res=>{
                console.log("res.payload")
                console.log(res.payload)

            })
    }


}

export default new RegUserService();
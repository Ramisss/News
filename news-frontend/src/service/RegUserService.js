import axios from "axios";

const BASE_URL = "http://localhost:8081/api/auth/register";
const BASE_URL_LOGIN = "http://localhost:8081/api/auth/login";


class RegUserService {

    addUser(user) {
        console.log(user)
        return axios.post(BASE_URL, user)
    }


    logUser(user){
        console.log( 'Это user',user)
            return axios.post(BASE_URL_LOGIN,user)

    }


}

export default new RegUserService();
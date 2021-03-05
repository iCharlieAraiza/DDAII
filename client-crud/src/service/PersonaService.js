import axios from 'axios'

const USER_REST_API_URL = 'http://172.16.1.157:8080/api/1';

const getUsers = ()=>{
    return axios.get(USER_REST_API_URL);
}

const greeting = ()=>{
    return 'hola';
}

const UserService ={
    getUsers,
    greeting
}


export default UserService;
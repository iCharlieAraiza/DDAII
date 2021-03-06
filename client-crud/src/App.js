import logo from './logo.svg';
import './App.css';
import Contacts from './components/Contacts';
import {React, useState, useEffect} from 'react';
import UserService from './service/PersonaService'



function App() {

  const initialValue = {
    nombre: "",
    edad: 0,
    genero:"Femenino",
    estatus:true
  }
  const [value, useValue] = useState();

  
  useEffect(()=>{
    UserService.getUsers().then((res)=>{
      console.log(res);
     }).catch(e=>console.log(e));
})
  
  
  return (
    <div className="row">
      <div className="col-md-8 offset-md-1">
        <Contacts value={value} useValue={useValue}></Contacts>
      </div>
    </div>
  );
}

export default App;

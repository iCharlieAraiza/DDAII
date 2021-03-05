import {React, useState, useEffect} from 'react';
import UserService from '../service/PersonaService';


import ContactForm from "./ContactForm"
import './style.css'

const Contacts = ()=>{

    const initialValue = {
        nombre: '',
        edad: 0,
        genero: '',
        estatus: ''
    }
    
    const [value, setValue] = useState(initialValue);

    useEffect(()=>{
        UserService.getUsers().then((response)=>{
            setValue(response);
        });
    });

    return(
        <>
            <div className="jumbotron jumbotron-fluid">
                <div className="container">
                    <h1 className="display-4">Lista de personas</h1>
                    <p className="lead">Ejemplo de muestra de una REST API usando React y Java Spring Boot.</p>
                </div>
            </div>
            <div className="row">
                <div className="col-md-5">
                    <ContactForm/>
                </div>
                <div className="col-md-7">
                    <div className="display-6">Personas</div>
                    <div className="card my-2 p-4">
                        <div className="tag-field">
                            <span className="strong">Name: </span>
                            <span>{value.nombre}</span>
                        </div>
                        <div className="tag-field">
                            <span className="strong">Edad: </span>
                            <span>{value.edad}</span>
                        </div>
                        <div className="tag-field">
                            <span className="strong">Sexo: </span>
                        </div>
                        <div className="tag-field">
                            <span className="strong">Estado: </span>
                        </div>
                        <div className="tag-field button-field">
                                <a href="" className="edit"><i className="fas fa-edit"></i></a>
                                <a href="" className="remove"><i className="fas fa-minus-circle"></i></a>
                        </div>

                    </div>
                </div>
            </div>
        </>
    )
}

export default Contacts;
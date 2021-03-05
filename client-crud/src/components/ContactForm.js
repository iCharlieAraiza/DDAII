import React, {useState, useEffect} from 'react';
import UserService from '../service/PersonaService'

const ContactForm = () => {
    
    const initialFieldValues = {
        nombre: '',
        edad: 0,
        genero: '',
        estatus: ''
    }

    const [formValues, setFormValues] = useState(initialFieldValues);
    
    let json;

    useEffect(()=>{
        UserService.getUsers().then((respone)=>{
            console.log("hola",respone.data);
        })
    })
    return(
    <>
        <form autoComplete="off">
            <div className="form-container-input">
                <label>Nombre</label>
                <input type="text" name="nombre" value={formValues.nombre} className="form-input" placeholder="Nombre de la persona"/>
            </div>

            <div className="form-container-input">
                <label>Edad</label>
                <input type="number" name="edad" className="form-input" placeholder="Edad" min="0"/>
            </div>

            <div className="form-container-input">
                <label>Sexo</label>
                <select name="genero">
                    <option value="masculino">Masculino</option>
                    <option value="femenino">Femenino</option>
                </select>
            </div>

            <div className="form-container-input">
                <label>Estatus</label>
                <select name="estatus">
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select>
            </div>

            <div className="form-container-input">
                <input type="button" value="Enviar" className="button"/>
            </div>


        </form>
    </>
    )
}

export default ContactForm;
import React from "react";
import api from "../api/contacts";
import axios from "axios"

class EditContact extends React.Component {
  constructor(props) {
    super(props);

    const { id, nombre, edad, genero, estatus } = props.location.state.contact;
    this.state = {
      id,
      nombre,
      edad,
      genero,
      estatus
    };

    const updateContactHandler = async (contact) => {
      const response = await api.put(`/api/`+response.data.id, contact);
      const { id, nombre, edad, genero } = response.data;
      this.setState(
        contact.map((contact) => {
          return contact.id === id ? { ...response.data } : contact;
        })
      );
    };

  }

  update = (e) => {
    e.preventDefault();
    if (this.state.nombre === "" || this.state.edad === "") {
      alert("ALl the fields are mandatory!");
      return;
    }
    this.props.updateContactHandler(this.state);
    this.setState({ id:e.target.id, nombre: e.target.nombre, edad: e.target.edad });

    //axios.put('/api/update', this.state);

    //this.props.updateContactHandler(this.state);

    //api.put(`/api/${this.state.id}`, this.state);

    console.log(this.state);


    this.props.history.push("/");
  };
  render() {
    return (
      <div className="ui main">
        <h2>Edit Contact</h2>
        <form className="ui form" onSubmit={this.update}>
          <div className="field">
            <label>Nombre</label>
            <input
              type="text"
              name="nombre"
              placeholder="Name"
              value={this.state.nombre}
              onChange={(e) => this.setState({ nombre: e.target.value })}
            />
          </div>
          <div className="field">
            <label>Edad</label>
            <input
              type="number"
              name="edad"
              min="0"
              placeholder="0"
              value={this.state.edad}
              onChange={(e) => this.setState({ edad: e.target.value })}
            />
          </div>
          <button className="ui button blue">Actualizar</button>
        </form>
      </div>
    );
  }
}

export default EditContact;

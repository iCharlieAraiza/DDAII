import React from "react";

class AddContact extends React.Component {
  state = {
    nombre: "",
    edad: 0,
    genero:"",
    estatus:true
  };

  add = (e) => {
    e.preventDefault();
    if (this.state.nombre === "" || this.state.edad === "") {
      alert("Es necesario agregar todos los campos!");
      return;
    }
    this.props.addContactHandler(this.state);
    this.setState({ nombre: "", edad: "", genero:"Masculino",estatus:true });
    this.props.history.push("/");
  };
  render() {
    return (
      <div className="ui main">
        <h2>Agregar persona</h2>
        <form className="ui form" onSubmit={this.add}>
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
              value={this.state.edad}
              onChange={(e) => this.setState({ edad: e.target.value })}
            />
          </div>

          <div className="field">
            <label>Sexo</label>
            
            <select name="genero" value={this.state.value} onChange={(e) => this.setState({ genero: e.target.value })} >
                <option selected value="Masculino">Masculino</option>
                <option value="Femenino">Femenino</option>
            </select>

          </div>

          <div className="field">
            <label>Estatus</label> 
            <select name="estatus" value={this.state.value} onChange={(e) => this.setState({ estatus: e.target.value })} >
                <option selected value="true">Activo</option>
                <option value="false">Inactivo</option>
            </select>

          </div>


          <button className="ui button blue">Add</button>
        </form>
      </div>
    );
  }
}

export default AddContact;

import ContactForm from "./ContactForm"
import './style.css'

const Contacts = ()=>{
    return(
        <>
            <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Lista de personas</h1>
                <p class="lead">Ejemplo de muestra de una REST API usando React y Java Spring Boot.</p>
            </div>
            </div>
            <div className="row">
                <div className="col-md-5">
                    <ContactForm/>
                </div>
                <div className="col-md-7">
                    <div>Lista de personas</div>
                </div>
            </div>
        </>
    )
}

export default Contacts;

import store from "Model/store"
import styles from "Styles/styles"

function template(school) {
    return `
        ${styles}
        <div class="w3-modal-content w3-card-4 w3-animate-opacity" id="dlg">
            <header class="w3-container w3-teal">
                 <span id="close" class="w3-button w3-display-topright">&times;</span>
                  <h2>Edit School ${school.name}</h2>
            </header>

            <div class="w3-container">
                <p>ID: ${school.id}</p>
                <p>Name: ${school.name}</p>
            </div>
            
            <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
                <button id="save" class="w3-button w3-green" type="submit">Speichern</button>
                <button id="close-button" type="button" class="w3-button w3-red" onclick="e => this.close()">Abbrechen</button>
            </div>
            
            <footer class="w3-container w3-teal">
                <p>TODO: Add edit field for name and implement save</p>
            </footer>
        </div>
    `
}

class SchoolDialog extends HTMLElement {
    connectedCallback() {
        store.model
            .map(model => model.currentSchoolId)
            .filter(id => !!id)
            .subscribe(schoolId => this.render(schoolId))
    }
    render(schoolId) {
        const school = store.state.schools.find(school => school.id == schoolId)
        const shadowRoot = this.attachShadow({mode: "open"})
        shadowRoot.innerHTML = template(school)
        const close = this.shadowRoot.getElementById("close")
        close.addEventListener("click", e => this.close())
        const closeButton = this.shadowRoot.getElementById("close-button")
        closeButton.addEventListener("click", e => this.close())
        this.shadowRoot.getElementById("save").addEventListener("click", e => this.save(e))
    }
    close() {
        const dlg = this.shadowRoot.getElementById("dlg")
        console.log("dialog is", dlg)
        this.style.display = "none"
    }
    open() {
        console.log("open dialog...")
        this.style.display = "block"
    }
    save(e) {
        setTimeout(() => alert("TODO: save the data"), 20)
        this.close()
    }
}

customElements.define("school-dialog", SchoolDialog)

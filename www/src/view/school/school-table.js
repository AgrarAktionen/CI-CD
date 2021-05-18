import styles from "Styles/styles"
import store from "../../model/store"
import { loadSchools } from "../../rest/school/school-service"
import { html } from "Lib/html"


const template = () => html`
    ${styles}
    <style>
        tr:hover {
            cursor: pointer;
        }
    </style>
    <table id="table" class="w3-table w3-striped">
        <caption class="w3-xlarge w3-light-grey">Schools</caption>
        <thead>
            <tr>
                <th class="w3-right">Id</th>
                <th>Schulname</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
`
const rowTemplate = school => html`
    <td class="w3-right">${school.id}</td>
    <td>${school.name}</td>
`

class SchoolTable extends HTMLElement {
    connectedCallback() {
        const shadowRoot = this.attachShadow({mode: "open"})
        shadowRoot.appendChild(document.importNode(template().content, true))
        this.table = shadowRoot.getElementById("table")
        store.model
            .map(model => model.schools)
            .distinctUntilChanged()
            .filter(school => !!school)
            .subscribe(schools => this.render(schools))

        loadSchools()
    }
    /** remove all existing bodies for re-render */
    clear() {
        while (this.table.tBodies && this.table.tBodies.length) {
            this.table.tBodies[0].remove()
        }
    }
    render(schools) {
        const body = this.shadowRoot.querySelector("tbody")
        body.innerHTML = ""
        schools.forEach(school => {
            const row = body.insertRow()
            const rowData = document.importNode(rowTemplate(school).content, true)
            row.onclick = e => this.schoolClicked(school)
            row.appendChild(rowData)
        })
    }
    schoolClicked(school) {
        const event = new CustomEvent("school-selected", {bubbles: true, composed: true, detail: {school}})
        this.dispatchEvent(event)
    }
}

customElements.define("school-table", SchoolTable)

import styles from "Styles/styles"
import store from "../../model/store"
import { loadSchools } from "../../rest/school/school-service"
import { html } from "Lib/html"

const template = html`
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
                <th>Id</th>
                <th>Schulname</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
`

class SchoolTable extends HTMLElement {

    async connectedCallback() {
        const shadowRoot = this.attachShadow({mode: "open"})
        const table = document.importNode(this.template(styles).content, true)
        shadowRoot.appendChild(table)
        this.table = shadowRoot.getElementById("table")
        store.model
            .map(model => model.schools)
            .distinctUntilChanged()
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
        if (schools.length > 0) {
            this.clear()
            if (!this.table.tBodies.length) {
                this.table.createTBody()
            }
            const body = this.table.tBodies[0]
            schools.map(school => this.addRow(body, school))
        }
    }
    addRow(body, school) {
        const row = body.insertRow()
        row.insertCell().innerText = `${school.id}`
        row.insertCell().innerText = school.name
        row.onclick = () => this.schoolClicked(school)
    }
    schoolClicked(school) {
        const event = new CustomEvent("school-selected", {bubbles: true, composed: true, detail: {school}})
        this.dispatchEvent(event)
    }
    template(styles) {
        const content = String.raw`
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
                        <th>Id</th>
                        <th>Schulname</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        `
        return html(content)
    }
}

customElements.define("school-table", SchoolTable)

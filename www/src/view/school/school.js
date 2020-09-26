import {html, render} from "lit-html"
import {loadSchools} from "Rest/school/school-service"
import {schoolObservable} from "Model/observables"
import styles from "Styles/styles"

let row = school => html`
    <tr>
        <td>${school.id}</td><td>${school.name}</td>
    </tr>
`
let rows = schools => schools.map(school =>row(school))

class School extends HTMLElement {
    constructor() {
        super()
        this.attachShadow({mode: 'open'})
    }
    async connectedCallback() {
        schoolObservable
            .map(school => school.schools)
            .distinctUntilChanged()
            .subscribe(schools => this.render(schools))

        loadSchools()
    }
    render(schools) {
        if (schools) {
            render(this.template(schools), this.shadowRoot)
        }
    }
    template(schools) {
        return html`
            ${styles}
            <table class="w3-table-all w3-hoverable">
                <thead>
                    <tr class="w3-light-grey">
                        <th>Id</th><th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    ${schools.map(school => this.row(school))}
                </tbody>
            </table>
        `
    }
    row(school) {
        return html`
            <tr @click=${e => this.schoolClicked(school, e)}>
                <td>${school.id}</td><td>${school.name}</td>
            </tr>
        `
    }
    schoolClicked(school, e) {
        console.log("school selected", school)
    }
}
function clicked(e) {
    console.log("clicked...")
}
customElements.define("my-school", School)
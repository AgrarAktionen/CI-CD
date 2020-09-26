import {html, render} from "lit-html"
import {loadSchools} from "Rest/school/school-service"
import {schoolObservable} from "Model/observables"

let row = school => html`
    <tr>
        <td>${school.id}</td><td>${school.name}</td>
    </tr>
`
let rows = schools => schools.map(school =>row(school))

let template = schools => html`
    <table>
        <thead>
        <tr>
            <th>Id</th><th>Name</th>
        </tr>
        </thead>
        <tbody>
            ${rows(schools)}
        </tbody>
    </table>
`
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
            render(template(schools), this.shadowRoot)
        }
    }
}
function clicked(e) {
    console.log("clicked...")
}
customElements.define("my-school", School)
import {html, render} from "lit-html"
import {loadSchools} from "Rest/school/school-service"
import {schoolObservable} from "Model/observables"
import styles from "Styles/styles"
import {setCurrentSchool} from "../../model/school/school-action-creator";

class SchoolTable extends HTMLElement {
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
            <style>
                .link {
                    cursor: pointer
                }
            </style>
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
            <tr class="link" @click=${e => this.schoolClicked(school, e)}>
                <td>${school.id}</td><td>${school.name}</td>
            </tr>
        `
    }
    schoolClicked(school, e) {
        console.log("school selected", school.id)
        setCurrentSchool(school.id)
    }
}
function clicked(e) {
    console.log("clicked...")
}
customElements.define("school-table", SchoolTable)
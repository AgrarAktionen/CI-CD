import {loadSchools} from "Rest/school/school-service"
import {schoolObservable} from "Model/observables"

class SchoolTable extends HTMLTableElement {
    async connectedCallback() {
        schoolObservable
            .map(school => school.schools)
            .distinctUntilChanged()
            .subscribe(schools => this.render(schools))

        loadSchools()
    }
    /** remove all existing bodies for re-render */
    clear() {
        while (this.tBodies.length) {
            this.tBodies[0].remove()
        }
    }
    render(schools) {
        this.clear()
        if (!this.tBodies.length) {
            this.createTBody()
        }
        const body = this.tBodies[0]
        schools.map(school => this.insertRow(body, school))
    }
    insertRow(body, school) {
        const row = body.insertRow()
        row.insertCell().innerText = school.id
        row.insertCell().innerText = school.name
        row.style = "cursor:pointer"
        row.onclick = () => this.schoolClicked(school)
    }
    schoolClicked(school) {
        const event = new CustomEvent("school-selected", {bubbles: true, composed: true, detail: {school}})
        console.log("raise event", event)
        this.dispatchEvent(event)
    }
}

customElements.define("school-table", SchoolTable, {extends: "table"})

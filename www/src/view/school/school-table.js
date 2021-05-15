
import store from "../../model/store"
import { loadSchools } from "../../rest/school/school-service"

class SchoolTable extends HTMLElement {
    async connectedCallback() {
        this.table = this.getElementsByTagName("table")[0]
        store.model
            .map(model => model.schools)
            .distinctUntilChanged()
            .subscribe(schools => this.render(schools))

        loadSchools()
    }
    /** remove all existing bodies for re-render */
    clear() {
        while (this.table.tBodies.length) {
            this.tBodies[0].remove()
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
        //row.style = "cursor:pointer"
        row.onclick = () => this.schoolClicked(school)
    }
    schoolClicked(school) {
        const event = new CustomEvent("school-selected", {bubbles: true, composed: true, detail: {school}})
        console.log("raise event", event)
        this.dispatchEvent(event)
    }
}


customElements.define("school-table", SchoolTable)

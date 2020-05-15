import {html, render} from 'https://unpkg.com/lit-html?module'

let template = school => html`<div @click=${clicked}>Schule: ${school.name}</div>`

class School extends HTMLElement {
    constructor() {
        super()
        this.shadow = this.attachShadow({mode: 'open'})
    }
    async connectedCallback() {
        let school = null
        try {
            school = await this.loadSchool()
            render(template(school), this.shadow)
        } catch(e) {
            console.log("Exception gefangen", e)
            alert(`Fehler: ${e.message}`)
        }

        console.log("school=", school)
    }
    async loadSchool() {
        const url = "http://localhost:8080/schools/1"
        const response = await fetch(url)
        const school = await response.json()
        return school
    }
}
function clicked(e) {
    console.log("clicked...")
}
customElements.define("my-school", School)
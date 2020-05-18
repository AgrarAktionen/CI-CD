import {html, render} from 'https://unpkg.com/lit-html?module'

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
        this.shadow = this.attachShadow({mode: 'open'})
    }
    async connectedCallback() {
        let schools = null
        try {
            schools = await this.loadSchools()
            render(template(schools), this.shadow)
        } catch(e) {
            console.log("Exception gefangen", e)
            alert(`Fehler: ${e.message}`)
        }

        console.log("schools=", schools)
    }
    async loadSchools() {
        const url = "http://localhost:8080/schools"
        const response = await fetch(url)
        const schools = await response.json()
        return schools
    }
}
function clicked(e) {
    console.log("clicked...")
}
customElements.define("my-school", School)
class School extends HTMLElement {
    constructor() {
        super()
        console.log("school constructor")
    }
    async connectedCallback() {
        const school = await this.loadSchool()
        console.log("school=", school)
    }
    async loadSchool() {
        const url = "http://localhost:8080/school"
        const response = await fetch(url)
        const school = await response.json()
        return school
    }
}

customElements.define("my-school", School)
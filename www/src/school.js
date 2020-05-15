class School extends HTMLElement {
    constructor() {
        super()
        console.log("school constructor")
        this.shadow = this.attachShadow({mode: 'open'})
    }
    async connectedCallback() {
        let school = null
        try {
            school = await this.loadSchool()
            this.fillElement()
        } catch(e) {
            console.log("Exception gefangen", e)
            alert(`Fehler: ${e.message}`)
        }

        console.log("school=", school)
    }
    fillElement() {
        let div = document.createElement("div")
        div.innerHTML = "Hello, world!"
        div.setAttribute("id", "mydiv")
        this.shadow.append(div)
    }
    async loadSchool() {
        const url = "http://localhost:8080/schools"
        const response = await fetch(url)
        const school = await response.json()
        return school
    }
}

customElements.define("my-school", School)
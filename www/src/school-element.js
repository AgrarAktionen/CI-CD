import schoolService from "./school-service.js"

class SchoolElement extends HTMLElement {

    constructor() {
        super()
        console.log("school-element constructor()")
    }
    async connectedCallback()  {
        console.log("connected...")
        const school = await schoolService.loadSchool()
        this.displayPersons(school.persons)
    }
    displayPersons(persons) {
        persons.forEach(p => {
          const div = document.createElement("div")
          div.innerHTML = `person: ${p.firstName} ${p.lastName}`
          this.appendChild(div)
        })
    }
}
customElements.define("bhif17-school", SchoolElement)

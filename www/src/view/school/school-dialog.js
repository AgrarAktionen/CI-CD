import {render, html} from "lit-html"
import {schoolObservable} from "Model/observables"
import store from "Model/store"

class SchoolDialog extends HTMLElement {
    connectedCallback() {
        this.attachShadow({mode: "open"})
        schoolObservable
            .map(school => school.currentSchoolId)
            .filter(id => !!id)
            .subscribe(schoolId => this.render(schoolId))
    }
    render(schoolId) {
        const school = store.getState().school.schools[schoolId]
    }
}
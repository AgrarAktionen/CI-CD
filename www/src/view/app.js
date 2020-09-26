import {render, html} from "lit-html"
import "./school/school"

class Application extends HTMLElement {
    constructor() {
        super()
        this.attachShadow({ mode: "open" })
    }
    connectedCallback() {
        this.render()
    }
    template() {
        return html`
            <h1>My First Application</h1>
            <my-school></my-school>
        `
    }
    render() {
        render(this.template(), this.shadowRoot)
    }
}
customElements.define("my-app", Application)
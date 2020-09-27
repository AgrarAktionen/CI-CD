import {render, html} from "lit-html"
import "./school/school-table"
import styles from "Styles/styles"

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
            ${styles}
            <div class="w3-container">
                <h1>Schools</h1>
                <school-table></school-table>
            </div>
        `
    }
    render() {
        render(this.template(), this.shadowRoot)
    }
}
customElements.define("my-app", Application)
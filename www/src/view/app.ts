import store from "../model/store"
import "./school/school-dialog"
import "./school/school-table"
const schoolTableTemplate = `
    <div>
        <school-table id="school-table" class="w3-table w3-striped">
            <table id="table" class="w3-table w3-striped">
                <caption class="w3-xlarge w3-light-grey">Schools</caption>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Schulname</th>
                    </tr>
                </thead>
            </table>
        </school-table>
    </div>
`
const dialogTemplate = `
    <school-dialog id="school-dialog" class="w3-modal"></school-dialog>
`

function start() {
    console.log("start")
    const tableTemplateElement = document.createElement("template")
    tableTemplateElement.innerHTML = schoolTableTemplate
    const table = document.importNode(tableTemplateElement.content, true)
    const dialogTemplateElement = document.createElement("template")
    dialogTemplateElement.innerHTML = dialogTemplate
    const dialog = document.importNode(dialogTemplateElement.content, true)
    
    const content = document.getElementById("content")
    content.addEventListener("school-selected", editSchool)
    content.appendChild(table)
}
start()

function editSchool(e: CustomEvent) {
    const school = e.detail.school
    store.currentSchoolId = school.id
    const schoolDialog = document.getElementById("school-dialog")
    schoolDialog.style.display = "block"
}

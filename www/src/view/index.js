import "./school/school-table"
import "./school/school-dialog"
import {setCurrentSchoolId} from "Model/school/school-action-creator"

const schoolTableTemplate = `
<h1>Schools</h1>
<table is="school-table" class="w3-table-all">
    <caption class="w3-xlarge w3-light-blue">Schools</caption>
    <thead>
        <tr>
            <th>Id</th>
            <th>Schulname</th>
        </tr>
    </thead>
</table>
`
const dialogTemplate = `
    <div id="school-dialog" is="school-dialog" class="w3-modal"></div>
`
const tableTemplateElement = document.createElement("template")
tableTemplateElement.innerHTML = schoolTableTemplate
const table = document.importNode(tableTemplateElement.content, true)

const dialogTemplateElement = document.createElement("template")
dialogTemplateElement.innerHTML = dialogTemplate
const dialog = document.importNode(dialogTemplateElement.content, true)

const content = document.getElementById("content")
content.addEventListener("school-selected", editSchool)

content.appendChild(table)
content.appendChild(dialog)

function editSchool(e) {
    const school = e.detail.school
    setCurrentSchoolId(school.id)
    console.dir(dialog)
    document.getElementById("school-dialog").open()
}

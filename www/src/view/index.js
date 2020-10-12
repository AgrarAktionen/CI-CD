import "./school/school-table"

const html = `
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

const templateElement = document.createElement("template")
templateElement.innerHTML = html
const table = document.importNode(templateElement.content, true)
document.getElementById("content").appendChild(table)

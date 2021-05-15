import store from "../model/store"
import "./school/school-dialog"
import "./school/school-table"

window.addEventListener('WebComponentsReady', function() {
    console.log("web components ready")
    const schoolTable = document.querySelector("school-table")
    schoolTable.addEventListener("school-selected", editSchool)
})

function editSchool(e: CustomEvent) {
    const school = e.detail.school
    store.currentSchoolId = school.id
    const schoolDialog = document.getElementById("school-dialog")
    schoolDialog.style.display = "block"
}

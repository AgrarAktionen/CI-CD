
window.addEventListener("DOMContentLoaded", async e => {
    console.log("DOM ist da")
    const school = await loadSchool()
    displayPersons(school.persons)
    console.log("Die Schule ist:", school)
})
console.log("hello world!")

async function loadSchool() {
    const response = await fetch("http://localhost:8080/schools/1")
    const school = await response.json()
    return school
}
function displayPersons(persons) {
    const main = document.getElementById("main")
    persons.forEach(p => {
      const div = document.createElement("div")
      div.innerHTML = `person: ${p.firstName} ${p.lastName}`
      main.appendChild(div)
    })
}
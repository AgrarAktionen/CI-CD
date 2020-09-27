import {setSchools} from "Model/school/school-action-creator"

export async function loadSchools() {
    const response = await fetch("/api/schools")
    const schools = await response.json()
    setSchools(schools)
}
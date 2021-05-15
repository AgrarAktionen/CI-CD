import { School } from "../../model/school/school"
import store from "../../model/store"

export async function loadSchools() {
    const response = await fetch("./api/school")
    store.schools = await response.json() as School[]
}
import { School } from "./school/school"

export interface Model {
    schools: School[]
    currentSchoolId?: number
}
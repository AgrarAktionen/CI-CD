
class SchoolService {
    constructor() {
        console.log("SchoolService()")
    }
    async loadSchool() {
        const response = await fetch("http://localhost:8080/schools/1")
        const school = await response.json()
        return school
    }
}

const schoolService = new SchoolService()
export default schoolService
/** 
 * Read Only State design pattern example.
 * (c) Christian Aberger (2021) - http://www.aberger.at
 */
import produce from "immer"
import { BehaviorSubject, Observable} from "rx"

import { Model } from "./model"
import { School } from "./school/school"

const initialState: Model = {
    schools: [],
    currentSchoolId: null
}
class Store {
    private subject = new BehaviorSubject<Model>(initialState)

    set schools(schools: School[]) {
        this.next(produce(this.state, draft => {
            draft.schools = schools.reduce((array, school) => {
                array[school.id] = school
                return array
            }, [])
        }))
    }
    set school(school: School) {
        const model = produce(this.state, draft => {
            draft.schools[school.id] = school
        })
        this.next(model)
    }
    set currentSchoolId(id: number) {
        this.next(produce(this.state, model => {model.currentSchoolId = id}))
    }
    set state(_notAllowed: Model) {
        throw new Error("state is read only")
    }
    get state() {
        return this.subject.getValue()
    }
    get model(): Observable<Model> {
        return this.subject
    }
    private sorted(schools: School[]) {
        return schools.slice().sort((l, r) => l.id - r.id)
    }
    private next(state: Model) {
        this.subject.onNext(state)
    }
}
export default new Store()


import { SET_SCHOOLS, SET_CURRENT_SCHOOL_ID } from "./school-action"
import state from "../state"

const intialState = Object.assign({}, state.school)

const schoolReducer = (currentState = intialState, action) => {
    let state = currentState
    switch(action.type) {
        case SET_SCHOOLS:
            state = {...state, schools: action.schools}
            break
        case SET_CURRENT_SCHOOL_ID:
            state = {...state, currentSchoolId: action.currentSchoolId}
            break
        default:
            break
    }
    return state
}

export default schoolReducer
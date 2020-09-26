import { SET_SCHOOLS } from "./school-action"
import state from "../state"

const intialState = Object.assign({}, state.school)

const schoolReducer = (currentState = intialState, action) => {
    let state = currentState
    switch(action.type) {
        case SET_SCHOOLS:
            state = {...state, schools: action.schools}
            break
        default:
            break
    }
    return state
}

export default schoolReducer
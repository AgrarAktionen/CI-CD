import state from "../state"

const intialState = Object.assign({}, state.application)

const reducer = (currentState = intialState, action) => {
    let state = currentState
    switch(action.type) {
        default:
            break
    }
    return state
}

export default reducer
import { combineReducers } from "./redux"
import school from "./school/school-reducer"
import application from "./application/applicationReducer"

const reducer = combineReducers({school, application})
export default reducer

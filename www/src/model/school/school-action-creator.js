import store from "../store.js"
import {SET_SCHOOLS} from "./school-action"

/** action creator */
export function setSchools(schools) {
    return store.dispatch(async dispatch => {
        dispatch({type: SET_SCHOOLS, schools})
    })
}


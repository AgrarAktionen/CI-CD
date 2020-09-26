import {applyMiddleware, createStore, compose} from "./redux.js"
import thunk from "redux-thunk/es"
import state from "./state.js"
import reducer from "./reducer"

let store
if (!store) {
    store = setup()
}

/** set up all things for the store
 */
function setup() {
    let composeEnhancers = compose
    if (process.env.NODE_ENV == "development") {
        composeEnhancers = typeof window === 'object' && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ?  window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({}) : compose
    }
    const enhancer = composeEnhancers(
        applyMiddleware(thunk)
    )

    const store = createStore(reducer, state, enhancer)
    return store
}

export default store
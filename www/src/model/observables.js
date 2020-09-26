import {Observable} from "Lib/rxjs"
import store from "./store"
export const storeObservable = Observable
    .from(store)
    .distinctUntilChanged()
    .share()

export const schoolObservable = storeObservable
    .map(state => state.school)
    .distinctUntilChanged()
    .share()

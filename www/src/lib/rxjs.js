/** configure "usual" observable methods */
import {Observable} from "rxjs-es/Observable"
import {Subject} from "rxjs-es/Subject"

import "rxjs-es/add/observable/from"
import "rxjs-es/add/operator/map"
import "rxjs-es/add/operator/filter"
import "rxjs-es/add/operator/distinctUntilChanged"
import "rxjs-es/add/operator/share"
import "rxjs-es/add/operator/merge"

export {Observable, Subject}
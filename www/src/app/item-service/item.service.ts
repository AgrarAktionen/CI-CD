import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Item} from '../interface/item/item';

const URL = "http://localhost:8080/api/item"
const URLAPI = "https://student.cloud.htl-leonding.ac.at/20170011/api/item"

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<Item[]>(URLAPI); 
  }
  getID(id: Number) {
    var URLID = "http://localhost:8080/api/item/" + id.toString();
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170011/api/item" + id.toString();
    return this.client.get<Item>(URLAP);
  }
}

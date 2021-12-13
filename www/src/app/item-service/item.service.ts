import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Item} from '../interface/item/item';

const URL = "http://localhost:8080/api/item"
const URLAPI = "https://student.cloud.htl-leonding.ac.at/20170033/api/item/inserted"

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
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170033/api/item/" + id.toString();
    return this.client.get<Item>(URLAP);
  }
  getPrimeKategorie() {
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170033/api/categories/getPrimeCategories";
    return this.client.get<any[]>(URLAP);
  }
  getSecondKategorie(kategorie: string) {
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170033/api/categories/getSecondCategories/" + kategorie;
    return this.client.get<any[]>(URLAP);
  }
  getThirdKategorie(kategorie: string) {
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170033/api/categories/getThirdCategories/" + kategorie;
    return this.client.get<any[]>(URLAP);
  }
  getFourthKategorie(kategorie: string) {
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170033/api/categories/getFourthCategories/" + kategorie;
    return this.client.get<any[]>(URLAP);
  }
  getKategorieItem(kategorie: string) {
    var URLAP = "https://student.cloud.htl-leonding.ac.at/20170033/api/categories/" + kategorie;
    return this.client.get<any[]>(URLAP);
  }
}

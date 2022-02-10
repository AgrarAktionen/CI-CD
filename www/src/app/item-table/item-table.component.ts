import { Component, Injectable, OnInit } from '@angular/core';
import { Item } from '../interface/item/item';
import { ItemService } from '../item-service/item.service';
import { Router } from '@angular/router';
import { Page } from '../page/item-table.component';


@Component({
  selector: 'item-table',
  templateUrl: './item-table.component.html',
  styleUrls: ['./item-table.component.scss']
})
@Injectable({
  providedIn: 'root'
})
export class ItemTableComponent implements OnInit {

  itemsGesamt: Item [] = []
  items: Item [] = []
  itemsAnzeige: Item [] = []
  isHere: Boolean = false;
  kategorien: string [] = []
  kategorieGrad: number = 1;
  kategoriePfad: string [] = [];
  kategorieName: string = '';

  p: number = 1;
  seitenzahl: number = 10;

  tempProzent: string = ''
  tempPreis: string = ''

  istProzentGroesser: number = 1000000
  istPreisGroesser: number = 1000000

  indexOf: number = 0
  kategorienPfadAnzeige: string = ''

  constructor(private itemService: ItemService, private router: Router, private page: Page) { 
    
  }

  ngOnInit(): void {
    this.p = this.page.p
    this.itemService.getAll().subscribe(itemsGesamt => this.itemsGesamt = itemsGesamt)
    this.itemService.getAll().subscribe(items => this.items = items)
    this.itemService.getAll().subscribe(itemsAnzeige => {
      this.itemsAnzeige = itemsAnzeige
      itemsAnzeige.forEach(element => {
        if(element.beschreibungsfeld != "") {  
          element.beschreibungsfeld = element.beschreibungsfeld.substring(0, 80);
          element.beschreibungsfeld += "..."
        }
      });
    })
    this.itemService.getPrimeKategorie().subscribe(kategorien => {
      this.kategorien = kategorien
      this.kategorieReihenfolge()
    })
    this.kategoriePfad[0] = ''
    this.kategoriePfad[1] = ''
    this.kategoriePfad[2] = ''
    this.kategoriePfad[3] = ''
  }

  kategorieReihenfolge() {
    this.indexOf = this.kategorien.indexOf("mehr...")
    if(this.indexOf != -1) {
      this.kategorien.splice(this.indexOf, 1)
      this.kategorien.push("mehr...")
    }
  }

  changepage(event: Event) {
    window.scrollTo(0, 0)

    this.page.p = Number(event)
  }

  preisBis(preis: string, prozent: string) {
    preis = preis.replace('€', '')
    prozent = prozent.replace('%', '')
    this.itemsAnzeige = []
    if(preis != '') {
      this.itemsGesamt.forEach(element => {
        this.tempPreis = element.bruttopreis.toString()
        this.tempPreis = this.tempPreis.replace(',', '.')
        this.tempProzent = element.percentage.toString()

        if(prozent == '') {  
          if(Number(this.tempPreis) <= Number(preis)) { 
            this.itemsAnzeige.push(element)
          }
        } else {
          if(Number(this.tempPreis) <= Number(preis) && Number(this.tempProzent) >= Number(prozent)) { 
            this.itemsAnzeige.push(element)
          }
        }
      });
      this.textKürzen()
      this.items = this.itemsAnzeige
    } else {
      if(prozent == '') {
        this.itemsAnzeige = this.itemsGesamt
      } else {
        this.itemsGesamt.forEach(element => {
          this.tempProzent = element.percentage.toString()
          if(Number(this.tempProzent) >= Number(prozent)) {
            this.itemsAnzeige.push(element)
          }
        })
      }
      this.textKürzen()
      this.items = this.itemsAnzeige
    }
    this.p = 1
  }

  prozentAb(prozent: string, preis: string) {
    prozent = prozent.replace('%', '')
    preis = preis.replace('€', '')
    this.itemsAnzeige = []
    if(prozent != '') {
      this.itemsGesamt.forEach(element => {
        this.tempPreis = element.bruttopreis.toString()
        this.tempPreis = this.tempPreis.replace(',', '.')
        this.tempProzent = element.percentage.toString()

        if(preis == '') {  
          if(Number(this.tempProzent) >= Number(prozent)) { 
            this.itemsAnzeige.push(element)
          }
        } else {
          if(Number(this.tempPreis) <= Number(preis) && Number(this.tempProzent) >= Number(prozent)) { 
            this.itemsAnzeige.push(element)
          }
        }
      });
      this.textKürzen()
      this.items = this.itemsAnzeige
    } else {
      if(preis == '') {
        this.itemsAnzeige = this.itemsGesamt
      } else {
        this.itemsGesamt.forEach(element => {
          this.tempPreis = element.bruttopreis.toString()
          this.tempPreis = this.tempPreis.replace(',', '.')
          if(Number(this.tempPreis) <= Number(preis)) {
            this.itemsAnzeige.push(element)
          }
        })
      }
      this.textKürzen()
      this.items = this.itemsAnzeige
    }
    this.p = 1
  }

  seitenanzahl(anzahl: number) {
   this.seitenzahl = anzahl;
  }
  textKürzen() {
    this.itemsAnzeige.forEach(element => {
      if(element.beschreibungsfeld != "") {  
        element.beschreibungsfeld = element.beschreibungsfeld.substring(0, 80);
        element.beschreibungsfeld += "..."
      }
    });
  }
  clickKategorie(kategorie: string) {
    if(this.kategorieGrad == 1) {
      this.kategorien = []
      this.kategoriePfad[0] = kategorie;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getSecondKategorie(this.kategoriePfad[0]).subscribe(kategorien => this.kategorien = kategorien)
      this.kategorieGrad++;
      this.kategorieName += kategorie;
    } else if(this.kategorieGrad == 2) {
      this.kategorien = []
      this.kategoriePfad[1] = kategorie;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0] + "/" + this.kategoriePfad[1]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getThirdKategorie(this.kategoriePfad[0] + "/" + this.kategoriePfad[1]).subscribe(kategorien => this.kategorien = kategorien)
      this.kategorieGrad++;
      this.kategorieName = '';
      this.kategorieName += kategorie;
    } else if(this.kategorieGrad == 3) {
      this.kategorien = []
      this.kategoriePfad[2] = kategorie;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0] + "/" + this.kategoriePfad[1] + "/" + this.kategoriePfad[2]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getFourthKategorie(this.kategoriePfad[0] + "/" + this.kategoriePfad[1] + "/" + this.kategoriePfad[2]).subscribe(kategorien => this.kategorien = kategorien)
      this.kategorieGrad++;
      this.kategorieName = '';
      this.kategorieName += kategorie;
    } 
    this.p = 1
  }

  kategorieZurueck() {
    if(this.kategorieGrad == 2) {
      this.kategorien = []
      this.itemsAnzeige = []
      this.itemService.getAll().subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getPrimeKategorie().subscribe(kategorien => {
        this.kategorien = kategorien
        this.kategorieReihenfolge()
      })
      this.kategoriePfad[0] = ''
      this.kategorieGrad--;
    } else if(this.kategorieGrad == 3) {
      this.kategorien = []
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.kategoriePfad[1] = ''
      this.kategorieGrad--;
      this.itemService.getSecondKategorie(this.kategoriePfad[0]).subscribe(kategorien => this.kategorien = kategorien)
    } else if(this.kategorieGrad == 4) {
      this.kategorien = []
      this.kategorieGrad--;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0] + "/" + this.kategoriePfad[1]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getThirdKategorie(this.kategoriePfad[0] + "/" + this.kategoriePfad[1]).subscribe(kategorien => {
        this.kategorien = kategorien
      })
      this.kategoriePfad[2] = ''
    }
    this.p = 1
    this.textKürzen()
  }

  clicked(item: Item) {
    this.router.navigate(['/item', item.itemId]);
  }
  suchen(begriff: string) {
    if(this.isHere == false) {
      this.isHere = true;
      if(begriff == '') {
        this.itemsAnzeige = this.items
      } else {    
        this.itemsAnzeige = []
        this.items.forEach(item => {
          if(item.artikelbezeichnung.toLowerCase().includes(begriff.toLowerCase())) { 
            this.itemsAnzeige.push(item)
          } else {
            this.itemsAnzeige = this.itemsAnzeige.filter(itemsAnzeige => itemsAnzeige.itemId != item.itemId);
          }
        });
      }
      this.p = 1
      this.isHere = false;
    }
  } 
  
  pageReset() {
    location.replace("/")
  }
}

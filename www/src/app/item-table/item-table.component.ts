import { Component, OnInit } from '@angular/core';
import { Item } from '../interface/item/item';
import { ItemService } from '../item-service/item.service';
import { Router } from '@angular/router';


@Component({
  selector: 'item-table',
  templateUrl: './item-table.component.html',
  styleUrls: ['./item-table.component.scss']
})

export class ItemTableComponent implements OnInit {

  items: Item [] = []
  itemsAnzeige: Item [] = []
  isHere: Boolean = false;
  kategorien: string [] = []
  kategorieGrad: number = 1;
  kategoriePfad: string [] = [];
  kategorieName: string = '';

  p: number = 1;
  seitenzahl: number = 10;

  constructor(private itemService: ItemService, private router: Router) { 
    
  }

  ngOnInit(): void {
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
    this.itemService.getPrimeKategorie().subscribe(kategorien => this.kategorien = kategorien)
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
      this.kategoriePfad[1] = "/" + kategorie;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0] + this.kategoriePfad[1]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getThirdKategorie(this.kategoriePfad[0] + this.kategoriePfad[1]).subscribe(kategorien => this.kategorien = kategorien)
      this.kategorieGrad++;
      this.kategorieName = '';
      this.kategorieName += "/" + kategorie;
    } else if(this.kategorieGrad == 3) {
      this.kategorien = []
      this.kategoriePfad[2] = "/" + kategorie;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0] + this.kategoriePfad[1] + this.kategoriePfad[2]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getFourthKategorie(this.kategoriePfad[0] + this.kategoriePfad[1] + this.kategoriePfad[2]).subscribe(kategorien => this.kategorien = kategorien)
      this.kategorieGrad++;
      this.kategorieName = '';
      this.kategorieName += "/" + kategorie;
    } 
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
      this.itemService.getPrimeKategorie().subscribe(kategorien => this.kategorien = kategorien)
      this.kategorieGrad--;
    } else if(this.kategorieGrad == 3) {
      this.kategorien = []
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.kategorieGrad--;
      this.itemService.getSecondKategorie(this.kategoriePfad[0]).subscribe(kategorien => this.kategorien = kategorien)
    } else if(this.kategorieGrad == 4) {
      this.kategorien = []
      this.kategorieGrad--;
      this.itemsAnzeige = []
      this.itemService.getKategorieItem(this.kategoriePfad[0] + this.kategoriePfad[1]).subscribe(itemsAnzeige => {
        this.itemsAnzeige = itemsAnzeige
        this.items = this.itemsAnzeige;
        this.textKürzen()
      })
      this.itemService.getThirdKategorie(this.kategoriePfad[0] + this.kategoriePfad[1]).subscribe(kategorien => this.kategorien = kategorien)
    }
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
          if(item.artikelbezeichnung.toLowerCase().includes(begriff.toLowerCase())) { // || item.beschreibungsfeld.includes(begriff)
            this.itemsAnzeige.push(item)
          } else {
            this.itemsAnzeige = this.itemsAnzeige.filter(itemsAnzeige => itemsAnzeige.itemId != item.itemId);
          }
        });
      }
      /*
      this.itemsAnzeige.forEach(element => {
        if(element.beschreibungsfeld != "") {  
          element.beschreibungsfeld = element.beschreibungsfeld.substring(0, 80);
          element.beschreibungsfeld += "..."
        }
      });
      */
      this.isHere = false;
    }
   
  } 
}

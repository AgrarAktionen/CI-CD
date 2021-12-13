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

  p: number = 1;
  seitenzahl: number = 10;

  constructor(private itemService: ItemService, private router: Router) { 
    
  }

  ngOnInit(): void {
    this.itemService.getAll().subscribe(items => this.items = items)
    this.itemService.getAll().subscribe(itemsAnzeige => {
      this.itemsAnzeige = itemsAnzeige
      itemsAnzeige.forEach(element => {
        element.beschreibungsfeld = element.beschreibungsfeld.substring(0, 80);
        element.beschreibungsfeld += "..."
      });
    })
  }

  seitenanzahl(anzahl: number) {
   this.seitenzahl = anzahl;
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
      this.isHere = false;
    }
   
  } 
}

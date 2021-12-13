import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/interface/item/item';
import { ItemService } from 'src/app/item-service/item.service';
import { StringDecoder } from 'string_decoder';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.scss']
})
export class ItemDetailComponent implements OnInit {

  public itemID!: Number;
  public item!: Item;

  constructor(private itemService: ItemService, private route: ActivatedRoute) { 
    this.itemID = Number(this.route.snapshot.paramMap.get('id'));
    this.itemService.getID(this.itemID).subscribe(item => this.item = item);
  }

  ngOnInit(): void {
    
  }

  public calc(bruttopreis: string, stattpreis: string) {
    
    var bp: string = bruttopreis.replace(/,/g, '.');
    var sp: string = stattpreis.replace(/,/g, '.');
    var result = 100-((Number(bp)/Number(sp))*100);
    result = Math.round(result);
    return result;
  }

  goToLink(url: string){
    window.open(url, "_blank");
  }

}

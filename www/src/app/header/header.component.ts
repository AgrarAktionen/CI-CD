import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemTableComponent } from '../item-table/item-table.component';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, private itemComponent: ItemTableComponent) { }

  ngOnInit(): void {
  }

  click() {
    this.router.navigate(['/user']);
  }
  start() {
    this.itemComponent.pageReset()
  }
}

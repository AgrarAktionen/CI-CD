import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemDetailComponent } from './item-detail/item-detail.component';
import { ItemTableComponent } from './item-table/item-table.component';
import { UserComponent } from './user-table/user/user.component';
import { UserDetailComponent } from './userDetail/user-detail/user-detail.component';
import { UserBearbeitenComponent } from './userBearbeiten/user-bearbeiten/user-bearbeiten.component';
import { UserHinzufuegenComponent } from './userHinzufuegen/user-hinzufuegen/user-hinzufuegen.component';

const routes: Routes = [ 
  {path: 'item/:id', component: ItemDetailComponent},
  {path: '', component: ItemTableComponent},
  {path: 'user', component: UserComponent},
  {path: 'user/:id', component: UserDetailComponent},
  {path: 'userBearbeiten/:id', component: UserBearbeitenComponent},
  {path: 'userHinzufuegen', component: UserHinzufuegenComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ItemDetailComponent,
ItemTableComponent]

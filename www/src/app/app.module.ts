import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ItemTableComponent } from './item-table/item-table.component';
import { ItemDetailComponent } from './item-detail/item-detail.component';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './user-table/user/user.component';
import { UserDetailComponent } from './userDetail/user-detail/user-detail.component';
import { UserBearbeitenComponent } from './userBearbeiten/user-bearbeiten/user-bearbeiten.component';
import { UserHinzufuegenComponent } from './userHinzufuegen/user-hinzufuegen/user-hinzufuegen.component';

import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    ItemTableComponent,
    ItemDetailComponent,
    HeaderComponent,
    UserComponent,
    UserDetailComponent,
    UserBearbeitenComponent,
    UserHinzufuegenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }

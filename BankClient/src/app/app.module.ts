import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';


import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PlatiUsluguComponent } from './plati-uslugu/plati-uslugu.component';

import { AppRoutingModule} from './app-routing/app-routing.module'
import { PlatiUsluguService } from './service/plati-uslugu.service';
import { BankaService } from './service/banka.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    PlatiUsluguComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    FormsModule,
    ModalModule.forRoot()
  ],
  providers: [
    PlatiUsluguService,
    BankaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

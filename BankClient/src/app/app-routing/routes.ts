import { Routes } from "@angular/router";

import { PlatiUsluguComponent} from "../plati-uslugu/plati-uslugu.component";
import { NevalidanLinkComponent } from "../nevalidan-link/nevalidan-link.component";


export const routes: Routes = [
    { path: 'plati-uslugu/:payment_url/:payment_id', component: PlatiUsluguComponent},
    { path: 'nevalidan-link', component: NevalidanLinkComponent}
]
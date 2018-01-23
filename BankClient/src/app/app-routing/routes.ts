import { Routes } from "@angular/router";

import { PlatiUsluguComponent} from "../plati-uslugu/plati-uslugu.component";


export const routes: Routes = [
    { path: 'plati-uslugu/:payment_url/:payment_id', component: PlatiUsluguComponent},
]
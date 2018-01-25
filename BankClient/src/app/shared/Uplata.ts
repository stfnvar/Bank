import { StatusUplate } from "./StatusUplate";
import { TipUplate } from "./TipUplate";

export class Uplata{
    id: number;
    trgovacId : string;
    lozinkaTrgovca: string;
    iznos: number;
    datumUplate: Date;
    errorUrl: string;
    osiguranjeId: number;
    status: StatusUplate;
    tipUplate: TipUplate;
    uplataLink: string;
    uplataIdDatabase: number;
}
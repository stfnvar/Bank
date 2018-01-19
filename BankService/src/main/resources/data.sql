insert into Banka(id, ime, adresa, telefon, email) values (1, "Rajfajzen", "Pere Perica 1", "021231231", "pera@pera.com");
insert into Banka(id, ime, adresa, telefon, email) values (2, "Intesa", "Sime Simica 1", "012231231", "sima@sima.com");
insert into Banka(id, ime, adresa, telefon, email) values (3, "Erste", "Zike Zikica 1", "023131231", "zika@zika.com");

insert into Klijent(id, ime, prezime, jmbg, adresa, broj_telefona, email, banka_id) values (1, "Pera", "Peric", "1234567890123", "Pera 123", "123453", "email@email.com", 1);
insert into Klijent(id, ime, prezime, jmbg, adresa, broj_telefona, email, banka_id) values (2, "Sima", "Simic", "1234567822123", "Sima 123", "123411", "email325@email.com", 1);
insert into Klijent(id, ime, prezime, jmbg, adresa, broj_telefona, email, banka_id) values (3, "Zika", "Zikic", "1234567822123", "Zika 123", "123488", "email23@email.com", 1);

insert into Racun(id, broj_racuna, aktivan, klijent_id) values (1, "1234567890", true, 1);
insert into Racun(id, broj_racuna, aktivan, klijent_id) values (2, "1444567890", true, 2);
insert into Racun(id, broj_racuna, aktivan, klijent_id) values (3, "1334567890", true, 3);
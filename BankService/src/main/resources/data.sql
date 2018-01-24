insert into Banka(id, ime, adresa, telefon, email, swift_code) values (1, "Rajfajzen", "Pere Perica 1", "021231231", "pera@pera.com", "111");
insert into Banka(id, ime, adresa, telefon, email, swift_code) values (2, "Intesa", "Sime Simica 1", "012231231", "sima@sima.com", "789");
insert into Banka(id, ime, adresa, telefon, email, swift_code) values (3, "Erste", "Zike Zikica 1", "023131231", "zika@zika.com", "123");

insert into Klijent(id, ime, prezime, jmbg, adresa, broj_telefona, email, banka_id, merchant_id, merchant_password) values (1, "Pera", "Peric", "1234567890123", "Pera 123", "123453", "email@email.com", 1, "1", "perinasifra");
insert into Klijent(id, ime, prezime, jmbg, adresa, broj_telefona, email, banka_id, merchant_id, merchant_password) values (2, "Sima", "Simic", "1234567822123", "Sima 123", "123411", "email325@email.com", 1, "2", "siminasifra" );
insert into Klijent(id, ime, prezime, jmbg, adresa, broj_telefona, email, banka_id, merchant_id, merchant_password) values (3, "Zika", "Zikic", "1234567822123", "Zika 123", "123488", "email23@email.com", 1, "3", "zikinasifra" );

insert into Racun(id, broj_racuna, aktivan, klijent_id, sigurnosni_kod, datum_vazenja, stanje_racuna) values (1, "111-4567-890", true, 1, "123", "2020-12-20 10:10:10", "20000");
insert into Racun(id, broj_racuna, aktivan, klijent_id, sigurnosni_kod, datum_vazenja, stanje_racuna) values (2, "111-4567-230", true, 2, "222", "2020-12-20 10:10:10", "300000");
insert into Racun(id, broj_racuna, aktivan, klijent_id, sigurnosni_kod, datum_vazenja, stanje_racuna) values (3, "111-4567-200", true, 3, "232", "2020-12-20 10:10:10", "80000");
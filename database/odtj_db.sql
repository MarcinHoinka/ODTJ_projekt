create database odtj_db;
use odtj_db;


CREATE TABLE instruktorzy (
    id_i INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    imie VARCHAR(50) NOT NULL,
    nazwisko VARCHAR(50),
    email VARCHAR(30) NOT NULL UNIQUE,
    telefon VARCHAR(9),
    id_gr VARCHAR(30)
);

#alter table instruktorzy drop id_gr;

CREATE TABLE kursanci (
    id_k INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    imie VARCHAR(50) NOT NULL,
    nazwisko VARCHAR(50),
    email VARCHAR(30) NOT NULL UNIQUE,
    telefon VARCHAR(9),
    id_gr VARCHAR(30)
);

#alter table kursanci drop id_gr;

CREATE TABLE logowanie (
    id_l INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    login VARCHAR(50) UNIQUE,
    haslo VARCHAR(50),
    permission VARCHAR(2),
    id_k INT,
    id_i INT,
    CONSTRAINT FK_id_i FOREIGN KEY (id_i)
        REFERENCES instruktorzy (id_i)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_id_k FOREIGN KEY (id_k)
        REFERENCES kursanci (id_k)
        ON UPDATE CASCADE ON DELETE CASCADE
);

insert into instruktorzy (imie, nazwisko, email, telefon) values ('Mario','Mariowski','mario@op.pl','603504988');
insert into instruktorzy (imie, nazwisko, email, telefon) values ('Tomek','Sroczynski','srok@op.pl','607727988');
insert into instruktorzy (imie, nazwisko, email, telefon) values ('Adam','Kowalski','adas@op.pl','601127988');

insert into kursanci (imie, nazwisko, email, telefon) values ('Krzysztof','Kowalski','kowal@op.pl','600727900');
insert into kursanci (imie, nazwisko, email, telefon) values ('Piotr','Nowak','nowak@op.pl','601727900');
insert into kursanci (imie, nazwisko, email, telefon) values ('Zenek','Zenski','zen@op.pl','611727900');
insert into kursanci (imie, nazwisko, email, telefon) values ('Edek','Jakis','ed@op.pl','601007900');

#INSERT ADMINA
insert into logowanie (login, haslo, permission, id_i) values ('admin','admin','0',1);
#INSERT INSTRUKTORA
insert into logowanie (login, haslo, permission, id_i) values ('instr','instr','1',2);

#INSERT USERÓW
insert into logowanie (login, haslo, permission, id_k) values ('kurs1','kurs1','2',3);
insert into logowanie (login, haslo, permission, id_k) values ('kurs2','kurs2','2',4);
insert into logowanie (login, haslo, permission, id_k) values ('kurs','kurs','2',1);
insert into logowanie (login, haslo, permission, id_k) values ('kurs','kurs','2',2);


CREATE TABLE szkolenia (
    id_sz INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    nazwa_sz VARCHAR(50),
    opis VARCHAR(200),
    czas_trwania INT
);

#alter table szkolenia drop nazwa;
#alter table szkolenia add nazwa_sz varchar(50);

insert into szkolenia (nazwa_sz, opis, czas_trwania) values ('Ekojazda 1','Podstawowe szkolenie z ekojazdy.','3');
insert into szkolenia (nazwa_sz, opis, czas_trwania) values ('Jazda Rajdowa','Podstawowe szkolenie z jazdy sportowej','6');

#2017-12-22 09:00:00

CREATE TABLE tory (
    id_tr INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    nazwa VARCHAR(50),
    adres VARCHAR(50),
    miasto VARCHAR(50),
    kod_pocztowy VARCHAR(6),
    telefon VARCHAR(10)
);

insert into tory (nazwa, adres, miasto, kod_pocztowy, telefon) values ('Tor Jastrząb', 'Rajdowa 2', 'Jastrzab', '05-498', '6703457889');
insert into tory (nazwa, adres, miasto, kod_pocztowy, telefon) values ('Tor Kielce', 'Miedziana', 'Miedziana Góra', '01-198', '6003457889');

CREATE TABLE grupy (
    id_gr INT AUTO_INCREMENT UNIQUE,
    nazwa_gr VARCHAR(50),
    data_sz DATETIME,
    id_i INT not null,
    id_sz INT not null,
    id_tr INT not null,
    PRIMARY KEY (id_gr),
    CONSTRAINT FKgrupy_id_i FOREIGN KEY (id_i)
        REFERENCES instruktorzy (id_i)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FKgrupy_id_sz FOREIGN KEY (id_sz)
        REFERENCES szkolenia (id_sz)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FKgrupy_id_tr FOREIGN KEY (id_tr)
        REFERENCES tory (id_tr)
        ON UPDATE CASCADE ON DELETE CASCADE
);
        
# constraint - więz integralności nie moze mieć takiej samej nazwy w kilku grupach
insert into grupy (nazwa_gr, data_sz, id_i,id_sz,id_tr) values ('Eko-1', '2017-12-22 08:30:00','2','1','2');
insert into grupy (nazwa_gr, data_sz, id_i,id_sz,id_tr) values ('Jazda Rajdowa', '2017-12-22 09:00:00','3','2','1');
insert into grupy (nazwa_gr, data_sz, id_i, id_sz, id_tr) values ('Aware', '2018-02-16 09:35:00','3','5','2');
insert into grupy (nazwa_gr, data_sz, id_i, id_sz, id_tr) values ('Aware-3', '2018-01-25 09:35:00','2','7','3');


#widok z opisem grupy


# widok tabeli grupy z opisami dla poszczególnych id
CREATE VIEW grupy_opis AS
SELECT 
	g.id_gr,
    g.nazwa_gr AS nazwa_grupy,
    g.data_sz AS data_szkolenia,
    i.id_i,
    #i.imie,
    #i.nazwisko,
    CONCAT(i.imie, ' ' ,i.nazwisko) AS instruktor,
    sz.id_sz,
    sz.nazwa_sz AS szkolenie,
    sz.czas_trwania AS czas_trwania,
    tr.id_tr,
    tr.nazwa AS tor
	FROM 
    grupy AS g
    JOIN
    instruktorzy AS i ON (i.id_i = g.id_i)
    JOIN
    szkolenia AS sz ON (sz.id_sz = g.id_sz)
    JOIN
    tory AS tr ON (tr.id_tr = g.id_tr);

# zapytania do widoku
select * from grupy_opis;
# opis całości bez idków (id_gr jako numer grupy)
SELECT id_gr, nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor,czas_trwania FROM grupy_opis as og; 

# widok do wywołania grup przypisanych do instruktorów
CREATE VIEW grupy_instruktora AS
SELECT id_gr, nazwa_grupy, data_szkolenia, szkolenie, tor, czas_trwania, id_i FROM grupy_opis;

# widok do wywołania grup przypisanych do instruktorów z id_l z danych logowania - do Instruktor Main w projekcie JFX
CREATE VIEW grupy_instruktora_log AS
SELECT go.id_gr, go.nazwa_grupy, go.data_szkolenia, go.szkolenie, go.tor, go.czas_trwania, go.id_i, l.id_l 
FROM grupy_opis as go
JOIN logowanie as l ON (go.id_i = l.id_i) ;


SELECT id_gr, nazwa_grupy, data_szkolenia, szkolenie, tor, czas_trwania FROM grupy_instruktora_log where id_l=2;

select * from grupy_instruktora; 
select * from grupy_instruktora where id_i ='3';
	

#CREATE VIEW grupy_opis_log AS
SELECT
	l.id_l,
	g.id_gr,
    g.nazwa_gr AS nazwa_grupy,
    g.data_sz AS data_szkolenia,
    i.id_i,
    #i.imie,
    #i.nazwisko,
    CONCAT(i.imie, ' ' ,i.nazwisko) AS instruktor,
    sz.id_sz,
    sz.nazwa_sz AS szkolenie,
    sz.czas_trwania AS czas_trwania,
    tr.id_tr,
    tr.nazwa AS tor
	FROM 
    grupy AS g
    JOIN
    logowanie as l ON (l.id_i = i.id_i)
    JOIN
    instruktorzy AS i ON (i.id_i = g.id_i)
    JOIN
    szkolenia AS sz ON (sz.id_sz = g.id_sz)
    JOIN
    tory AS tr ON (tr.id_tr = g.id_tr);





#tabela łącząca kursantów do róznych grup

CREATE TABLE kursanci_w_grupach(
  id_gr INT NOT NULL,
  #imie VARCHAR(50) NULL,
  #nazwisko VARCHAR(50) NULL,
  id_k INT NOT NULL,
 #nazwa_gr VARCHAR(50) NULL,
  
  CONSTRAINT FKkwg_id_gr
    FOREIGN KEY (id_gr)
    REFERENCES grupy (id_gr)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT FKkwg_id_k
    FOREIGN KEY (id_k)
    REFERENCES kursanci (id_k)
    ON UPDATE CASCADE ON DELETE CASCADE
    );

insert into kursanci_w_grupach (id_gr, id_k) values ('1','1');
insert into kursanci_w_grupach (id_gr, id_k) values ('1','3');
insert into kursanci_w_grupach (id_gr, id_k) values ('4','2');
insert into kursanci_w_grupach (id_gr, id_k) values ('4','4'); 
insert into kursanci_w_grupach (id_gr, id_k) values ('4','3'); 

#wyszukiwanie kursantów przypisanych do danej grupy
select id_k as kursanci from kursanci_w_grupach 
where id_gr = '4';

create view kursanci_w_gr_opis AS
SELECT
kwg.id_gr AS grupa,
g.nazwa_gr AS nazwa_grupy,
kwg.id_k,
k.imie,
k.nazwisko,
k.email,
k.telefon
FROM
kursanci_w_grupach as kwg
JOIN
grupy AS g ON (kwg.id_gr = g.id_gr)
JOIN 
kursanci as k ON (k.id_k=kwg.id_k);

select * from kursanci_w_gr_opis;
select grupa, nazwa_grupy, imie, nazwisko, email, telefon from kursanci_w_gr_opis; 

# zapytanie dla tabeli kursanci w grupie dla RootGrupy
SELECT grupa, imie, nazwisko, email, telefon FROM kursanci_w_gr_opis; 

select imie, nazwisko, email, telefon, grupa, nazwa_grupy from kursanci_w_gr_opis where grupa='4'; 
select imie, nazwisko, email, telefon from kursanci_w_gr_opis where grupa='4'; 

SELECT grupa, imie, nazwisko, email, telefon FROM kursanci_w_gr_opis WHERE grupa ='1';


#wyszukiwanie grup do których zapisano danego kursnata

CREATE VIEW grupy_kursanta AS
SELECT 
	kwg.id_k,
    g.id_gr,
    g.nazwa_gr AS nazwa_grupy,
    g.data_sz AS data_szkolenia,
    i.id_i,
    CONCAT(i.imie, ' ' ,i.nazwisko) AS instruktor,
    sz.id_sz,
    sz.nazwa_sz AS szkolenie,
    sz.czas_trwania AS czas_trwania,
    tr.id_tr,
    tr.nazwa AS tor
	FROM
    kursanci_w_grupach as kwg
    JOIN
    grupy AS g ON (kwg.id_gr = g.id_gr)
    JOIN
    instruktorzy AS i ON (i.id_i = g.id_i)
    JOIN
    szkolenia AS sz ON (sz.id_sz = g.id_sz)
    JOIN
    tory AS tr ON (tr.id_tr = g.id_tr);

# zapytania o wszystkie grupy dla danego kursanta;
select * from grupy_kursanta;
select * from grupy_kursanta where id_k = '3';
select id_gr, nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor, czas_trwania from grupy_kursanta where id_k = '3'; 

# jw. plus id_l do sprawdzania tożsamości z loginu

CREATE VIEW grupy_kursanta_log AS
SELECT 
	kwg.id_k,
    g.id_gr,
    g.nazwa_gr AS nazwa_grupy,
    g.data_sz AS data_szkolenia,
    CONCAT(i.imie, ' ' ,i.nazwisko) AS instruktor,
    sz.nazwa_sz AS szkolenie,
    sz.czas_trwania AS czas_trwania,
    tr.nazwa AS tor,
    l.id_l
	FROM
    kursanci_w_grupach as kwg
    JOIN
    logowanie as l ON (kwg.id_k = l.id_k)
    JOIN
    grupy AS g ON (kwg.id_gr = g.id_gr)
    JOIN
    instruktorzy AS i ON (i.id_i = g.id_i)
    JOIN
    szkolenia AS sz ON (sz.id_sz = g.id_sz)
    JOIN
    tory AS tr ON (tr.id_tr = g.id_tr);

select * from grupy_kursanta_log;
select id_gr, nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor, czas_trwania from grupy_kursanta_log;
select id_gr, nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor, czas_trwania from grupy_kursanta_log where id_l = '8';

#  --------------------- 
# INSTRUKTORZY 

SELECT * FROM instruktorzy;
SELECT id_i, concat(imie,' ',nazwisko) as instruktor from instruktorzy;

create view instruktorzy_cc AS
SELECT id_i, concat(imie,' ',nazwisko) as instruktor_c from instruktorzy; 
SELECT concat(id_i,' ',imie,' ',nazwisko) as instruktor from instruktorzy; 
select * from instruktorzy_cc;




# widoki łączące dane instruktorów z danymi logowania
CREATE VIEW log_instruktorzy AS
SELECT 
    i.id_i,
    i.imie,
    i.nazwisko,
	l.id_l,
    l.login,
    l.haslo,
    l.permission
	FROM 
    logowanie AS l
    JOIN
    instruktorzy AS i ON (i.id_i = l.id_i);

# widoki łączące dane kursantów z danymi logowania
CREATE VIEW log_kursanci AS
SELECT 
    k.id_k,
    k.imie,
    k.nazwisko,
	l.id_l,
    l.login,
    l.haslo,
    l.permission
	FROM 
    logowanie AS l
    JOIN
    kursanci AS k ON (k.id_k = l.id_k);




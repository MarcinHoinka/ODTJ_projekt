Projekt aplikacji do obsługi szkoleń w ośrodku doskonalenia techniki jazdy. 

Funkcjonalność: 
Panel logowania (username i hasło). 
	Logowanie sprawdza uprawnienia użytkownika i przenosi odpowiednio do panelu administratora, instruktora lub kursanta.

Panel administratora umożliwia:
	- dodawanie i kasowanie kursantów,
	- przypisywanie kursantów do grup, 
	- dodawanie i kasowanie instruktorów,
	- dodawanie i kasowanie torów (miejsc szkoleń),
	- dodawanie i kasowanie szkoleń,
	- wywświetlanie grup i przypisanych do nich kursantów.
	
	- tworzenie grup i przydział kursantów do grup,  - W PRZYGOTOWANIU
	
Panel instruktora:
	- wyświetla grupy do których został przypisany dany instruktor,
	- wyświetla kursantów z danych grup.

Panel kursant umożliwia: 
	- podgląd listy grup do których krusant jest zapisany.
	

	
Uruchomienie: 
Po rozpakowaniu repozytorium, uruchmić bazę danych z pliku /Database/ocena_projektu.sql na serwerze loklanym (np. przy pomocy MySQL Workbench). 

Projekt zaimportować do dowolnego środowiska JAVA IDE (Eclipse, InteliJ) i uruchomić program. 

Przykładowe konta do logowania w aplikacji: 
Admin: username: "admin", pass: "admin"
Instruktor: username: "instr", pass: "instr"
Kursant: username: "kursant", pass "kurs"  
Kursant2: username: "kurs", pass "kurs"  






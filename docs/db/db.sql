CREATE TABLE country (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL,
	countCity integer NOT NULL,
	dataSave datetime NOT NULL
);

CREATE TABLE city (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL,
	countryId integer NOT NULL REFERENCES country(id),
	countStreets integer NOT NULL,
	dataSave datetime NOT NULL
);

CREATE TABLE transport (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL,
	cityId integer NOT NULL REFERENCES city(id),
	routId integer NOT NULL REFERENCES route(id),
	yearRelease datetime NOT NULL,
	inspection integer NOT NULL,
	number integer NOT NULL
);

CREATE TABLE route (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL,
	countStops integer NOT NULL,
	duration varchar NOT NULL
);






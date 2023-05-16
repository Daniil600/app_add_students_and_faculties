CREATE TABLE car
(
    Id    serial unique ,
    brand text check (not null) default 'lada',
    model text check (not null) default 'zebra',
    price money check (not null) default 100
);

CREATE TABLE person
(
    Id     serial unique ,
    name   text check (not null),
    age    int4 check (not null),
    rights boolean check (not null),
    car_id int4 references car(id)
);


SELECT person.name, person.age, person.rights, car.id, brand, model, price
FROM person
         FULL JOIN car ON person.car_id = car.id
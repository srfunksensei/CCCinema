create table if not exists auditorium (
    id varchar(255) not null,
    seats_num integer not null,
    title varchar(255),
    primary key (id)
);


create table if not exists movie (
    id varchar(255) not null,
    actors varchar(255),
    description varchar(255),
    director varchar(255),
    duration integer not null,
    title varchar(255),
    primary key (id)
);


create table if not exists reservation (
    id varchar(255) not null,
    paid boolean not null,
    reserved boolean not null,
    username varchar(255),
    screening_id varchar(255) not null,
    primary key (id)
);


create table if not exists screening (
    id varchar(255) not null,
    starting_time timestamp,
    auditorium_id varchar(255),
    movie_id varchar(255) not null,
    primary key (id)
);


create table if not exists seat (
    id varchar(255) not null,
    seat_num varchar(255),
    row_num varchar(255),
    auditorium_id varchar(255) not null,
    primary key (id)
);


create table if not exists seat_reserved (
    id varchar(255) not null,
    reservation_id varchar(255) not null,
    screening_id varchar(255) not null,
    seat_id varchar(255) not null,
    primary key (id)
);


alter table screening
   add constraint UKepwqd3d6f54bwjc1xt3n1ky46 unique (movie_id, auditorium_id, starting_time);


alter table reservation
   add constraint FKsus9r7msj3uas10wxl1jvj8xb
   foreign key (screening_id)
   references screening;


alter table screening
   add constraint FKc5jmfksm86w9sooy5fdy6xa3c
   foreign key (auditorium_id)
   references auditorium;


alter table screening
   add constraint FKfp7sh76xc9m508stllspchnp9
   foreign key (movie_id)
   references movie;


alter table seat
   add constraint FKotpis64jx0uqh3k507csp9ire
   foreign key (auditorium_id)
   references auditorium;


alter table seat_reserved
   add constraint FKkc4tetul8ya0dqo7r8bygygy1
   foreign key (reservation_id)
   references reservation;


alter table seat_reserved
   add constraint FK8gxkhkm3e1qltvt5yiptrrh9u
   foreign key (screening_id)
   references screening;


alter table seat_reserved
   add constraint FKoif100090qur1dyga3dnoye41
   foreign key (seat_id)
   references seat;

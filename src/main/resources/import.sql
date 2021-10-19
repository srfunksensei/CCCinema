INSERT INTO movie(id, cast, description, director, duration, title) VALUES (1, " Mark Hamill, Harrison Ford, Carrie Fisher ", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a wookiee and two droids to save the galaxy from the Empire's world-destroying battle-station, while also attempting to rescue Princess Leia from the evil Darth Vader.", "George Lucas", 121, "Star Wars: Episode IV - A New Hope");
INSERT INTO movie(id, cast, description, director, duration, title) VALUES (2, " Mark Hamill, Harrison Ford, Carrie Fisher ", "After the rebels have been brutally overpowered by the Empire on their newly established base, Luke Skywalker takes advanced Jedi training with Master Yoda, while his friends are pursued by Darth Vader as part of his plan to capture Luke.", "George Lucas", 124, "Star Wars: Episode V - The Empire Strikes Back");
INSERT INTO movie(id, cast, description, director, duration, title) VALUES (3, " Mark Hamill, Harrison Ford, Carrie Fisher ", "After rescuing Han Solo from the palace of Jabba the Hutt, the rebels attempt to destroy the second Death Star, while Luke struggles to make Vader return from the dark side of the Force.", "George Lucas", 131, "Star Wars: Episode VI - Return of the Jedi");

INSERT INTO auditorium(id, name, seats_num) VALUES (1, "Nikola Tesla", 16);
INSERT INTO auditorium(id, name, seats_num) VALUES (2, "Mihajlo Pupin", 16);

INSERT INTO seat(id, num, row, auditorium_id) VALUES (1, 1, "A", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (2, 2, "A", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (3, 3, "A", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (4, 4, "A", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (5, 1, "B", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (6, 2, "B", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (7, 3, "B", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (8, 4, "B", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (9, 1, "C", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (10, 2, "C", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (11, 3, "C", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (12, 4, "C", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (13, 1, "D", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (14, 2, "D", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (15, 3, "D", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (16, 4, "D", 1);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (17, 1, "A", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (18, 2, "A", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (19, 3, "A", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (20, 4, "A", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (21, 1, "B", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (22, 2, "B", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (23, 3, "B", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (24, 4, "B", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (25, 1, "C", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (26, 2, "C", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (27, 3, "C", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (28, 4, "C", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (29, 1, "D", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (30, 2, "D", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (31, 3, "D", 2);
INSERT INTO seat(id, num, row, auditorium_id) VALUES (32, 4, "D", 2);

INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (1, DATE_ADD(now(), INTERVAL 1 HOUR), 1, 1);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (2, DATE_ADD(now(), INTERVAL 4 HOUR), 1, 2);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (3, DATE_ADD(now(), INTERVAL 7 HOUR), 1, 3);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (4, DATE_ADD(now(), INTERVAL 10 HOUR), 1, 1);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (5, DATE_ADD(now(), INTERVAL 1 HOUR), 2, 2);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (6, DATE_ADD(now(), INTERVAL 4 HOUR), 2, 3);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (7, DATE_ADD(now(), INTERVAL 7 HOUR), 2, 1);
INSERT INTO screening(id, start, auditorium_id, movie_id) VALUES (8, DATE_ADD(now(), INTERVAL 10 HOUR), 2, 2);



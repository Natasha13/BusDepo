CREATE TABLE buses(
  id INT NOT NULL AUTO_INCREMENT,
  bus_number varchar(30) not NULL,
  PRIMARY KEY (id)
);

INSERT INTO buses (bus_number) VALUES ('AA 1843 AA'),('AA 1649 AA'),('AA 0726 AA'),
  ('AA 18676 AA'),('AA 1654 AA'),('AA 0781 AA'),
  ('AA 1907 AA'),('AA 1531 AA'),('AA 1281 AA');

SELECT * FROM buses;
SELECT * FROM users;
SELECT * FROM routes;

CREATE TABLE users(
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL ,
  login VARCHAR(50) NOT NULL ,
  password varchar(50) not NULL,
  user_spesiality VARCHAR(25) NOT NULL ,
  PRIMARY KEY (id)
);

INSERT INTO users (user_name, login, password, user_spesiality) VALUES
  ('Viktor Kravec','viktorkravec','8638986871','driver'),
  ('Oleg Vinograd','olegvinograd','0867531981','driver');
#   ('Artem Lavrov','artemlavrov','2638893876','driver'),
#   ('Petr Svalyavchik','petrsvalyavchik','1820267624','driver'),
#   ('Danil Martiinenko','danilmartiinenko','2139307124','driver'),
#   ('Ivan Marchenko','ivanmarchenko','3088916136','admin'),
#   ('Illya Gordenskiy','illyagordenskiy','5112588429','driver'),
#   ('Anna Gashyk','annagashyk','3561141060','admin');

SELECT * FROM users;

CREATE TABLE routes(
  id INT NOT NULL AUTO_INCREMENT,
  route_name varchar(20) not NULL,
  PRIMARY KEY (id)
);

INSERT INTO routes (route_name) VALUES ('220'),('215K'),
  ('582D');

SELECT * FROM routes;

CREATE TABLE bus_park(
  id INT NOT NULL AUTO_INCREMENT,
  bus_id INT NOT NULL,
  user_id INT NOT NULL  ,
  route_id INT NOT NULL  ,
  accepted BOOLEAN NOT NULL ,
  FOREIGN KEY (bus_id) REFERENCES buses (Id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (Id) ON DELETE CASCADE,
  FOREIGN KEY (route_id) REFERENCES routes (Id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

INSERT INTO bus_park (bus_id, user_id, route_id, accepted) VALUES
  (1,1,1,TRUE),
  (2,2,1,TRUE ),
  (3,3,2,FALSE ),
  (4,5,3,TRUE ),
  (5,7,3,FALSE ),
  (6,8,2,TRUE );

SELECT * FROM bus_park;
SELECT * FROM buses;
SELECT * FROM users;
SELECT * FROM routes;

SELECT bp.id as id ,bus_id,b.bus_number bus_number, user_id, user_name, login, password, user_spesiality,
  route_id,route_name, accepted FROM bus_park bp
  LEFT JOIN buses b ON bp.bus_id = b.id
  LEFT JOIN users ON bp.user_id = users.Id LEFT JOIN routes ON bp.route_id = routes.Id;

SELECT b.id as bus_id,b.bus_number bus_number, users.id as user_id, users.user_name, users.login, users.password , users.user_spesiality,
    routes.id as route_id,routes.route_name
                    FROM buses b ,users ,routes
WHERE b.id=4 AND users.id=5 AND routes.id=3;

SELECT * FROM routes;
DELETE FROM bus_park WHERE id=11;

DROP TABLE if EXISTS bus_park;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS buses;
DROP TABLE IF EXISTS routes;

CREATE TABLE buses(
  id INT NOT NULL AUTO_INCREMENT,
  bus_number varchar(30) not NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users(
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL ,
  login VARCHAR(50) NOT NULL ,
  password varchar(500) not NULL,
  user_role VARCHAR(25) NOT NULL ,
  PRIMARY KEY (id)
);

CREATE TABLE routes(
  id INT NOT NULL AUTO_INCREMENT,
  route_name varchar(20) not NULL,
  PRIMARY KEY (id)
);

CREATE TABLE bus_park(
  id INT NOT NULL AUTO_INCREMENT,
  bus_id INT NOT NULL UNIQUE ,
  user_id INT NOT NULL  UNIQUE ,
  route_id INT NOT NULL  ,
  accepted BOOLEAN NOT NULL ,
  FOREIGN KEY (bus_id) REFERENCES buses (Id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (Id) ON DELETE CASCADE,
  FOREIGN KEY (route_id) REFERENCES routes (Id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

INSERT INTO buses (bus_number) VALUES ('AA 1843 AA'),('AA 1649 AA'),('AA 0726 AA'),
  ('AA 1867 AA'),('AA 1654 AA'),('AA 0781 AA'),
  ('AA 1907 AA'),('AA 1531 AA'),('AA 1281 AA');

INSERT INTO users (user_name, login, password, user_role) VALUES
  ('Admin','admin','f0a0173c9510753f1c17610ae500e3bac4e9c0721bd2ee285941057c72bffa85$1$f3f181074d371d4ac7a11a7b632e931e4b9a8724ebc755297ecf828e3e459d25','admin'),
  ('Viktor Kravec','viktorkravec','c43f20f94a8a000008936d414293b785931ff277b06968972ddbf152161e9d70$1$90e662692bd2dd95c2cb159c4f539af5107aab06a9f99a17d2441930472a6356','driver'),
  ('Oleg Vinograd','olegvinograd','87db8d518310a9cdf67be66c625a543ddd3f061d191fe2400a1ed1eab73af2b4$1$8723b1d31612a19849c5f810bbe7fb7c170221912b2afc61d54273d36b7f6764','driver'),
  ('Artem Lavrov','artemlavrov','6907f29635c9df99bac8dd8485a37b44ff6e701b8e24a3a41a74335ae99ac2fe$1$ddfaa78fe5f9a1a41894dd2e68f1d8a94511b623bf694bb993ca45bb92b31ef6','driver'),
  ('Petro Svalyavchik','petrosvalyavchik','cdb2ec5329c95b0fd01af1ce64276ee5d29442c6954c2dccbe557bb302543212$1$53d5d2e899028668b480062d3dc4b12d31044dcc29b3c54ae04dcafbeb2f7330','driver'),
  ('Danil Martinenko','danilmartinenko','c137fb37b6b6a99b9df9169da184c69459e8fc2c41ffd1a7c7d3bc6d191e6fb0$1$f16eb38dea18dbdc2f23d2340a72ae98891eb6f44f05fa58d9f45b518e204bd6','driver'),
  ('Ivan Marchenko','ivanmarchenko','b981ffba3febca94daeada83b76fccedcb1a0a017be0b44dbb8aaed90237c6e4$1$b53ce6ff2efa36d630c3f584b1e4b2fcb3045f3f262b33c8084aca513a400e03','admin'),
  ('Illya Gordenskiy','illyagordenskiy','689f0c882a7322cdd1a2ccfb00769d4641c6c564e655c69fc87cf492a93be7d0$1$950be1913eb59a9bb5b4ec4b18bd66879d5635cc2175e98cfd7f4665298f1b1a','driver'),
  ('Anna Gashyk','annagashyk','e204070a4f656197a3db6bef7ba3444fbe11cdfa3ff169db417ab89b2f7544be$1$22fd4c55727cbbc884b6457536efe6c883cec4e402b6073e9fcbac94265ab214','admin');


INSERT INTO routes (route_name) VALUES ('220'),('215K'),
  ('582D');

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

UPDATE buses set bus_number='AA 1867 AA' WHERE id=4;

SELECT bp.id AS id ,bus_id,b.bus_number bus_number, user_id, user_name, login,
  password, user_role, route_id,route_name, accepted FROM bus_park bp
  LEFT JOIN buses b ON bp.bus_id = b.id LEFT JOIN users ON bp.user_id = users.Id
  LEFT JOIN routes ON bp.route_id = routes.Id
LIMIT 10 OFFSET 0
;
SELECT count(*)
from users;


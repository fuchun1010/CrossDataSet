create table location(
  _id int primary key auto_increment,
  _name varchar(50)
);

create table person(
  _id int primary key auto_increment,
  _name varchar(50),
  _salory decimal(10,2) default 0.00,
  _location_id int references location(id)
);
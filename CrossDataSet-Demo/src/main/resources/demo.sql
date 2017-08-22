CREATE TABLE `tab_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `tab_jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `tab_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `location_id` int(11) references tab_location(id),
  `job_id` int(11) references tab_jobs(id),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into tab_location(`name`)
select '北京'
union
select '上海'
union
select '广州'
union
select '河南';

insert into tab_jobs(`name`, salary)
select 'driver', 4000
union
select 'teacher', 2500
union
select 'docter', 5000;

insert into tab_person(`name`, location_id ,job_id)
select 'lisi', 1, 1
union
select 'wangwu', 2, 1
union
select 'caocao', 4, 2;


select * from tab_location;

select * from tab_jobs;

select * from tab_person;

select t0.name, t1.name, t2.salary
from tab_person t0
inner join tab_location t1 on t0.location_id = t1.id
inner join tab_jobs t2 on t0.job_id = t2.id;

select t2.person_name, t2.location_name, t3.name
from (
   select t0.`job_id` as job_id, t0.name as person_name, t1.name as location_name
   from tab_person t0
   inner join tab_location t1 on t0.location_id = t1.id
) t2
inner join tab_jobs t3
on t3.id = t2.job_id;


select t0.name, t1.name, t2.salary
from tab_person t0
right join tab_location t1 on t0.location_id = t1.id
inner join tab_jobs t2 on t0.job_id = t2.id;

select t0.name, t1.name, t2.salary
from tab_person t0
right join tab_location t1 on t0.location_id = t1.id
inner join tab_jobs t2 on t0.job_id = t2.id;

select t0.name, t1.name, t2.salary
from tab_person t0
inner join tab_jobs t2 on t0.job_id = t2.id
right join tab_location t1 on t0.location_id = t1.id;

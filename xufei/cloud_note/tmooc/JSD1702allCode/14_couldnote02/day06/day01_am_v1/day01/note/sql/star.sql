select * from cn_note;

create table cn_star (
	cn_star_id varchar(50),
	cn_user_id varchar(50),
	cn_stars int,
	primary key(cn_star_id)
);

select cn_user_id from cn_user;

select * from cn_star;

select * from cn_note 
limit 0, 10;


select * from cn_note 
limit 10, 10;


select * from cn_note 
limit 20, 10;


create table p_person(
    id int not null AUTO_INCREMENT,
    name varchar(100),
    primary key(id)
);

insert into p_person (name) values ('李老师');
insert into p_person (id, name) values (null,'李老师');

select * from p_person;

create table p_comment(
    id int not null AUTO_INCREMENT,
    title varchar(100),
    post_id int,
    primary key(id)
);

create table p_post(
    id int not null AUTO_INCREMENT, 
    title varchar(100),
    person_id int,
    primary key(id)
);

insert into p_post (id, title, person_id)
    values (null, '今天天气不错', 1);
    
insert into p_post (id, title, person_id)
    values (null, '高考又来了', 1);
    
insert into p_comment(id, title, post_id)
    values ( null, '少穿了哪一件呀?', 1);
    
insert into p_comment(id, title, post_id)
    values (null, '冻成狗', 1);
insert into p_comment(id, title, post_id)
    values (null, '喜欢下雪', 1);
	









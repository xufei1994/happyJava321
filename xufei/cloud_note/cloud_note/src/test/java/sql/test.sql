select * from cn_note group by cn_user_id;

select cn_note_id from cn_note;
select * from  cn_note limit 20,10;

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
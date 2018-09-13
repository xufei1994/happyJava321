select * from cn_note;

create table cn_star (
	cn_star_id varchar(50),
	cn_user_id varchar(50),
	cn_stars int,
	primary key(cn_star_id)
);

select cn_user_id from cn_user;

select * from cn_star;




